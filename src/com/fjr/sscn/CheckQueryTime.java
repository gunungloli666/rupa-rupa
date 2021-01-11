package com.fjr.sscn;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//import com.mysql.jdbc.PreparedStatement;

public class CheckQueryTime {
	
	public static void main(String[] args) {
		PrintWriter out = null;
		try {
			 out = new PrintWriter(new FileOutputStream("D:/cek_query.txt")); 

			long a = System.currentTimeMillis();
			 
			Class.forName("org.postgresql.Driver") ;
			Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/olah_data", "postgres" , "09huiI" ); 
			
			PreparedStatement stmt = connection.prepareStatement("select nama,nik , no_peserta from ktp_ganjil_2018 where keterangan = 1 limit 60000") ;
			ResultSet resultset = stmt.executeQuery();
	 
			while( resultset.next()) {
				String nama = resultset.getString("nama"); 
				String nik = resultset.getString("nik"); 
				String no_peserta = resultset.getString( "no_peserta" ); 
				
				out.println(String.format("%-50s %-15s %-10s", nama , nik, no_peserta)); 
				out.flush();
			}
			
			long b = System.currentTimeMillis(); 
			System.out.println((b -a) / 1000.0); 
			
			connection.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if( out!= null) {
				out.close();
			}
		}
		
	}

}
