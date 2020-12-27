package fjr.com.makassar;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fjr.com.utils.FileUtils;

public class Parimo {
	
	PrintWriter out = null;
	public Parimo() throws IOException {
		out = new PrintWriter(new FileOutputStream( "E:/birokrasi/lamaran kerja/CPNS 2018/pengumuman/pemkab bone/daftar-login.txt"    ));
		File f = new File( "E:/birokrasi/lamaran kerja/CPNS 2018/pengumuman/pemkab bone/bone.txt"  ); 
		String content = FileUtils. getFileString(f); 
//		Pattern p = Pattern.compile("([0-9]{1,5})\\s([0-9]{16})\\s([A-Za-z\\`\\'\\s\\.\\,]+)\\s([0-9]{2}\\/[0-9]{2}\\/[0-9]{4})\\s(.*)"); 

		Pattern p = Pattern.compile("([0-9]{1,5}\\,?[0-9]{1,5})\\s([0-9]{16})\\s"); 
		Matcher m = p.matcher(content);
		int a = 0;
		while(m.find()) {
			out.println(m.group(2)); 
			out.flush();
		}
		out.close();
		
		System.out.println("FINISH");

	}
	
	
	public static void main(String[] args) {
		try {
			new Parimo() ; 
		}catch(Exception e) {
			e.printStackTrace(); 
		}
	}

}
