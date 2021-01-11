package com.fjr.sigi._2019;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fjr.com.utils.FileUtils;
import fjr.cpns.kemenkeu.Parimo;

public class SigiBiromaru {
	
	PrintWriter out = null;
	public  SigiBiromaru (String input, String output, String startWith ) throws IOException {
		out = new PrintWriter(new FileOutputStream( "./"  + output  ));
		File f = new File( "./" + input ); 
		String content = FileUtils. getFileString(f); 
		Pattern p = Pattern.compile("\\s([0-9]{17})\\s"); 
		Matcher m = p.matcher(content);
		int a = 0;
		while(m.find()) {
			String kode_ktp = m.group(1); 
			String  sb = new StringBuilder(kode_ktp).reverse().substring(0, 16); 
			if(sb.startsWith(startWith)) {
				out.println(sb);
				out.flush();
			}
		}
		out.close();
		System.out.println("FINISH");
	}
	
	
	public static void main(String[] args) {
		try {
			new  SigiBiromaru(args[0], args[1], args[2]) ; 
		}catch(Exception e) {
			e.printStackTrace(); 
		}
	}
}
