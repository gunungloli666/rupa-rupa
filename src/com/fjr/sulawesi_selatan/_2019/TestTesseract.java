package com.fjr.sulawesi_selatan._2019;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class TestTesseract {
	
	public TestTesseract() {
		try {
			int a = 1; 
			while( a <= 116 ) {
				System.out.println("file ke-" + a); 
				Process process = new ProcessBuilder("C:\\Program Files\\Tesseract-OCR\\tesseract.exe", 
						"E:\\birokrasi\\lamaran kerja\\CPNS 2019\\sulawesi selatan\\hasil\\" + Integer.toString(a) + ".png" , 
						"E:\\birokrasi\\lamaran kerja\\CPNS 2019\\sulawesi selatan\\teks\\"+ Integer.toString(a) )
						.start();
				InputStream is = process.getInputStream();
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(isr);
				String line;
				while ((line = br.readLine()) != null) {
				  System.out.println(line);
				}
				a = a+1; 
			}
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("FINISH"); 
	}
	
	
	public static void main(String[] args) {
		new TestTesseract(); 
	}

}
