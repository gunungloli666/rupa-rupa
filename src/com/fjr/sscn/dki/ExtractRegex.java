package com.fjr.sscn.dki;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ExtractRegex {
	
	public ExtractRegex() throws IOException, ClassNotFoundException, SQLException {
		File f = new File("E:/birokrasi/lamaran kerja/CPNS 2018/pengumuman/rekap_lulus_semua_satker.txt"); 
		Class.forName("org.postgresql.Driver") ;
		Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/olah_data", "postgres" , "09huiI" ); 
		String content = getFileString(f); 
		Pattern p = Pattern.compile("([0-9]{1,3}\\.?([0-9]{1,3})?)\\s([A-Za-z\\`\\'\\s\\.\\,]+)\\s([0-9]{12}\\*{4})\\s([0-9]{2}\\/[0-9]{2}\\/[0-9]{4})\\s([0-9]{10})"); 
		Matcher m = p.matcher(content); 
		int a = 1; 
		while(m.find()) {
			String id = m.group(1); 
			String nama = m.group(3); 
			String nik  = m.group(4); 
			String tanggal = m.group(5); 
			String no_peserta = m.group(6); 
			
			String nik_ = nik.substring(6,12); 
			int tgl_ktp = Integer.valueOf(nik_.substring(0,2)); 
			if( tgl_ktp > 40) {
				tgl_ktp  = tgl_ktp - 40 ;
			}
			String tgl_remove = tanggal.replace("/", ""); 
			
			String tgl_ktp_str = Integer.toString(tgl_ktp);
			if(tgl_ktp_str.length() == 1) {
				tgl_ktp_str = "0".concat(tgl_ktp_str);
			}
			String bulan_ktp_str = nik_.substring(2,4); 
			String tahun_ktp_str = "19".concat(nik_.substring(4,6) ) ;
			
			String tgl_final = bulan_ktp_str.concat(tgl_ktp_str).concat(tahun_ktp_str);
			
			int status = 0; 
			if(tgl_final.equals(tgl_remove)) {
				status = 1; 
			}
			try {
				PreparedStatement stmt = connection.prepareStatement("insert into ktp_ganjil_2018(nama, nik, tanggal_lahir, keterangan , no_peserta) "
						+ "values(?,?,?, ? , ?)"); 
				stmt.setString(1, nama); 
				stmt.setString(2, nik );
				stmt.setString(3, tanggal);
				stmt.setInt(4, status);
				stmt.setString(5, no_peserta); 
				stmt.executeUpdate();

			}catch(Exception e) {
				e.printStackTrace();
			}
		}


	}
	
	
	
	public static void main(String[] args) {
		try {
			new ExtractRegex();
		}catch(Exception e) {
			e.printStackTrace(); 
		}
	}
	
	public void putToTable() throws Exception{
		File f = new File("E:/birokrasi/lamaran kerja/CPNS 2018/pengumuman/rekap_lulus_semua_satker.txt"); 
//		Class.forName("com.mysql.jdbc.Driver") ;
//		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/olah_data", "root" , "" ); 

		Class.forName("org.postgresql.Driver") ;
		Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/olah_data", "postgres" , "09huiI" ); 

		
		String content = getFileString(f); 
	
		Pattern p = Pattern.compile("([0-9]{1,3}\\.?([0-9]{1,3})?)\\s([A-Za-z\\`\\'\\s\\.\\,]+)\\s([0-9]{12}\\*{4})\\s([0-9]{2}\\/[0-9]{2}\\/[0-9]{4})\\s[0-9]{10}"); 
		Matcher m = p.matcher(content); 
		int a = 1; 
		while(m.find()) {
			String id = m.group(1); 
			String nama = m.group(3); 
			String nik  = m.group(4); 
			String tanggal = m.group(5); 
			
			try {
				PreparedStatement stmt = connection.prepareStatement("insert into cpns_2018(nama, nik, tanggal_lahir) "
						+ "values(?,?,?)"); 
				stmt.setString(1, nama); 
				stmt.setString(2, nik );
				stmt.setString(3, tanggal);
				
				stmt.executeUpdate();

			}catch(Exception e) {
				System.out.println(id); 
			}
//			System.out.println(m .group()); 
//			if(a++ > 100) {
//				break; 
//			}
		}

	}
	
	public String getFileString(File file) throws IOException {
	      byte[] buffer = new byte[(int) file.length()];
	      FileInputStream fileIn = null;
	      try {
	        fileIn = new FileInputStream(file);
	        fileIn.read(buffer);
	      } finally {
	        if (fileIn != null) {
	          try {
	            fileIn.close();
	          } catch (IOException ex) {
	          }
	        }
	      }

	      return new String(buffer);
	  }

}
