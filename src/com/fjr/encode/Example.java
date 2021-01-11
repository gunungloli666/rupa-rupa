package com.fjr.encode;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class Example {
	public Example() {
		File fInput = new File("D:/input.txt");
		try {
			FileOutputStream fos = new FileOutputStream("D:/mami-lala.bmp");
			String fString = getFileString(fInput);
			byte[] decodedString = Base64.getMimeDecoder().decode(fString); 
			fos.write(decodedString);
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new Example();
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