package fjr.cpns.dikti;

import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class Test {

	
	public Test() {
		HtmlPage page1 = null;
		WebClient webClient = new WebClient();
		webClient.getCookieManager().clearCookies();
		webClient.getOptions().setJavaScriptEnabled(false);
		webClient.getOptions().setCssEnabled(false);

		try {
			
			page1 = webClient.getPage("https://sscndaftar.bkn.go.id/login");
//			String old_tanggal = convertNikWanita(nik, false); 
			
			HtmlForm form = (HtmlForm) page1.getElementById("FormLogin"); 
			HtmlInput name = form.getInputByName("username"); 

			name.setValueAttribute("7208081305880002"); 
			HtmlInput passwordForm = form.getInputByName("password"); 
			passwordForm.setValueAttribute("t?pPwer)8"); 
			HtmlButton button = (HtmlButton) page1.getElementById("btnSimpan"); 
			HtmlPage page2 = button.click();
			String result = page2.asText(); 
			if( ! result.toLowerCase().contains("nik tidak ditemukan") && 
					! result.toLowerCase().contains("bad credential")) {
				Page json = webClient.getPage("https://sscndaftar.bkn.go.id/resume/resume.json");
				String jsonContent = json.getWebResponse().getContentAsString();
				if(jsonContent.contains("UNIVERSITAS TADULAKO")) {
					System.out.println("yes"); 
				}
			}

		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new Test(); 
	}
}
