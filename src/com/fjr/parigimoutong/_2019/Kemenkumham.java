package com.fjr.parigimoutong._2019;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fjr.com.utils.FileUtils;
import fjr.cpns.kemenkeu.Parimo;

public class Kemenkumham {
	
	PrintWriter out = null;
	public  Kemenkumham () throws IOException {
		out = new PrintWriter(new FileOutputStream( "E:/birokrasi/lamaran kerja/CPNS 2019/kabupaten parigi moutong/daftar-login.txt"   ));
		File f = new File( "E:/birokrasi/lamaran kerja/CPNS 2019/kabupaten parigi moutong/parimo 2019.txt" ); 
		String content = FileUtils. getFileString(f); 
		Pattern p = Pattern.compile("([0-9]{1,5})\\s([0-9]{17})"); 
		Matcher m = p.matcher(content);
		int a = 0;
		while(m.find()) {
			String kode_ktp = m.group(2); 
			String  sb = new StringBuilder(kode_ktp).reverse().substring(0, 16); 
			out.println(sb);
			out.flush();

		}
		out.close();
		
		System.out.println("FINISH");

	}
	
	
	public static void main(String[] args) {
		try {
			new  Kemenkumham() ; 
		}catch(Exception e) {
			e.printStackTrace(); 
		}
	}


}
