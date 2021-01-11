package com.fjr.sscn;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ReadJSon {
	
	public static void main(String[] args) {
//		long a  = System.currentTimeMillis(); 
//		PrintWriter writer =  null ;
		try (
				PrintWriter writer =new PrintWriter(new FileOutputStream("E:/login password/daftar login lengkap/1/daftar_login_extract.txt")) ;
				BufferedReader reader = new BufferedReader(new FileReader("E:/login password/daftar login lengkap/1/daftar_login.txt"))
				){
			String data; 
			int err = 0;
			int iterasi = 0; 
			while( (data = reader.readLine())!= null) {
				String[] m = data.split("\\|"); 
				try {

					String json = m[3]; 
					JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
					String pip  = jsonObject.getAsJsonPrimitive("success").toString();
					if(pip.equals("true")) {
						JsonObject result = jsonObject.getAsJsonObject("result");
						JsonObject biodataMaster = result.getAsJsonObject("biodataMaster");
						String c = biodataMaster.getAsJsonPrimitive("namaKTP").getAsString();
						String email = biodataMaster.getAsJsonPrimitive("email").getAsString();
						
						JsonObject biodataData = result.getAsJsonObject("biodataData"); 
						String noTelp = biodataData.getAsJsonPrimitive("noTelp").getAsString(); 
						String noHp = biodataData.getAsJsonPrimitive("noHp").getAsString(); 
						String medsos = biodataData.getAsJsonPrimitive("medsos") != null ? 
								biodataData.getAsJsonPrimitive("medsos").getAsString() : ""; 
								
//						writer.println(c + "|" + m[1]+ "|" +m[2]+"|"+ noTelp + "|"+ noHp + 
//								"|" + email + "|" + medsos ); 
						JsonObject pendidikanData = result.getAsJsonObject("pendaftaran"); 
						String prodi = pendidikanData.getAsJsonObject("prodi").getAsJsonPrimitive("nama").getAsString();
						String namaUniv = pendidikanData.getAsJsonPrimitive("namaSekolah").getAsString();
						String noIjazah = pendidikanData.getAsJsonPrimitive("noIjazah").getAsString(); 
						System.out.println(m[1] + "|" + namaUniv + "|" + noIjazah + "|" + prodi); 
						if((iterasi++) > 100) {
							break;
						}

					}

				}catch(Exception e) {
					e.printStackTrace();
					err++; 
					System.out.println("si" + "|" + m[0]); 
				}
			}
			System.out.println(err); 
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	/*	long b = System.currentTimeMillis(); 
		System.out.println( ( b -a )/1000.0); 
	*/	
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
