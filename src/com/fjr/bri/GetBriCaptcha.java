package com.fjr.bri;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
//import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebResponse;
	
public class GetBriCaptcha {
	
	public static void main(String[] args) throws Exception {
		if(args.length > 2) {
			throw new Exception("can not continue");
		}
		
		WebClient webClient = new WebClient();
		webClient.getCookieManager().clearCookies();
		webClient.getOptions().setJavaScriptEnabled(false);
		webClient.getOptions().setCssEnabled(false);
		
		
		Connection connection = null;
		try {
			Class.forName("org.postgresql.Driver") ;
			 connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/olah_data", "postgres" , "09huiI" ); 
			
			PreparedStatement stmt = connection.prepareStatement("insert into md5_captcha_bri(md5_col, location , argument ) "
					+ "values(?,? , ?)");  
			File f = new File("E:/captcha bri/"+args[0] +"/"); 
			if(f.exists()) {
				FileUtils.deleteDirectory(f);
			}
			f.mkdir();
			
			for(int i=1 ; i < 15000 ; i++) {
				String lokasi = "E:/captcha bri/"+args[0]+"/"+Integer.toString(i)+".jpeg";
				try {
					Page p = webClient.getPage("https://ib.bri.co.id/ib-bri/login/captcha"); 
					WebResponse res = p.getWebResponse(); 
					InputStream response = res.getContentAsStream();
					IOUtils.copy(response, new FileOutputStream(lokasi)) ;  
//					System.out.println(DigestUtils.md5Hex(p.getWebResponse().getContentAsString())); 
					System.out.println(res.getContentAsString()); 
					stmt.setString(1, DigestUtils.md5Hex(res.getContentAsString())); 
					stmt.setString(2, lokasi);			
					stmt.setString(3, args[1]); 
					stmt.executeUpdate();
				}catch(Exception e) {
					e.printStackTrace();
					continue;
				}
			}

		}finally {
			if(connection!= null) {
				connection.close();
			}
		}
	}
	
	public void downloadToDir(String link, String dir) {
		try ( BufferedInputStream inputStream = new BufferedInputStream(
				new URL(link).openStream());
				FileOutputStream fileOS = new FileOutputStream(dir)) {
			byte data[] = new byte[1024];
			int byteContent;
			while ((byteContent = inputStream.read(data, 0, 1024)) != -1) {
				fileOS.write(data, 0, byteContent);
			}
		} catch (IOException e) {
		}
	}
}
