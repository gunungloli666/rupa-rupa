package fjr.cpns.parimo;

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
		out = new PrintWriter(new FileOutputStream( "E:/birokrasi/lamaran kerja/CPNS 2018/pengumuman/parigi moutong/hasil_login.txt"    ));
		File f = new File( "E:/birokrasi/lamaran kerja/CPNS 2018/pengumuman/parigi moutong/HASILSELEKSI_CPNS_PARIMO_TAHUN_2018.txt"  ); 
		String content = FileUtils. getFileString(f); 
//		Pattern p = Pattern.compile("([0-9]{1,5})\\s([0-9]{16})\\s([A-Za-z\\`\\'\\s\\.\\,]+)\\s([0-9]{2}\\/[0-9]{2}\\/[0-9]{4})\\s(.*)"); 

		Pattern p = Pattern.compile("([0-9]{1,5}\\,?[0-9]{1,5})\\s([A-Za-z\\`\\'\\s\\.\\,\\s]+)\\s([0-9]{16})\\s([0-9]{2}\\/[0-9]{2}\\/[0-9]{4})"); 
		Matcher m = p.matcher(content);
		int a = 0;
		while(m.find()) {
			out.println(m.group(2) + "|" + m.group(3)+ "|" + m.group(4));
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
