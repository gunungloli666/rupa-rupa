package com.fjr.cek.pertanyaan.pengaman;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class CekRubahPassword1 {
	
	BufferedReader reader = null; 
	
	PrintWriter out  = null, out1 = null ; 
	
	public CekRubahPassword1(String input, String output) {
		String content = null; 
		HtmlPage page1 = null;
		WebClient webClient = new WebClient();
		webClient.getCookieManager().clearCookies();
		webClient.getOptions().setJavaScriptEnabled(false);
		webClient.getOptions().setCssEnabled(false);
		try {
			out = new PrintWriter(new FileOutputStream ( "./"+ output  )); 
			out1 = new PrintWriter(new FileOutputStream( "./log.txt" )); 		
			reader = new BufferedReader(new FileReader( "./" + input ));
			while((content = reader.readLine() ) != null) {		
				out1.println(content); 
				out1.flush();
				page1 = webClient.getPage("https://sscndaftar.bkn.go.id/login");
				String nik = content;
				String temps[] = nik.split("\\|"); 
				nik = temps[0]; 
				String password = "09huiIll"; 
		
				HtmlForm form = (HtmlForm) page1.getElementById("FormLogin"); 
				HtmlInput name = form.getInputByName("username"); 

				name.setValueAttribute(nik); 
				HtmlInput passwordForm = form.getInputByName("password"); 
				passwordForm.setValueAttribute(password); 
				HtmlButton button = (HtmlButton) page1.getElementById("btnSimpan"); 
				HtmlPage page2 = button.click();
				String result = page2.asText(); 
				if( result.toLowerCase().contains("bad credential")) {
					out.println(nik);  
					out.flush();
					continue; 
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(out != null) {
				out.close();
			}
			if(out1 != null) {
				out1.close();
			}
		}
		
		System.out.println("FINISH"); 
	}
	
	public static void main(String[] args) {
		new CekRubahPassword1(args[0],args[1]);  
	}
}
