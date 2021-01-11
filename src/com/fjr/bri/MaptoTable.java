package com.fjr.bri;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import org.apache.commons.codec.digest.DigestUtils;

public class MaptoTable {

	public MaptoTable() throws Exception {
		Class.forName("org.postgresql.Driver") ;
		Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/olah_data", "postgres" , "09huiI" ); 
		PreparedStatement stmt = connection.prepareStatement("insert into md5_bri(md5 , lokasi, keterangan) "
				+ "values(?,?,?)"); 
		
		File f = new File("E:/captcha bri/coba/");
		File ff[] = f.listFiles();
		for (File c : ff) {			
			try (FileInputStream is = new FileInputStream(c)) {
				String mm = DigestUtils.md5Hex(is);
				stmt.setString(1, mm);
				stmt.setString(2, c.getAbsolutePath()); 
				stmt.setInt(3, 0); 
				stmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) {
		try {
			new MaptoTable();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
