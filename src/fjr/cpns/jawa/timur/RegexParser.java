package fjr.cpns.jawa.timur;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fjr.com.utils.FileUtils;

public class RegexParser {

	public RegexParser() throws Exception{
//		Pattern p = Pattern.compile("");  
		putToTable();
	}
	
	public void putToTable() throws Exception{
		File f = new File( "E:/birokrasi/lamaran kerja/CPNS 2018/pengumuman/pemprov jawa timur/pengumuman_hasil_seleksi_administrasi_pemprov_jatim_2018.txt" ); 
//		Class.forName("com.mysql.jdbc.Driver") ;
//		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/olah_data", "root" , "" ); 

		Class.forName("org.postgresql.Driver") ;
		Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/olah_data", "postgres" , "09huiI" ); 

		
		String content = FileUtils. getFileString(f); 
	
		Pattern p = Pattern.compile("([0-9]{1,3}\\,?([0-9]{1,3})?)\\s([A-Za-z\\`\\'\\s\\.\\,]+)\\s([0-9]{2}\\/[0-9]{2}\\/[0-9]{4})\\s([0-9]{10})\\s(.*)"); 

//		Pattern p = Pattern.compile("([0-9]{1,3}\\,?([0-9]{1,3})?)\\s([A-Za-z\\`\\'\\s\\.\\,]+)"); 

		
		Matcher m = p.matcher(content); 
		int a = 1; 
		PreparedStatement stmt = connection.prepareStatement("insert into jawa_timur_2018( nama, tanggal_lahir, no_peserta , jabatan ) "
				+ "values(?,?,? , ? )"); 

		while(m.find()) {
//			String id = m.group(1); 
			String nama = m.group(3); 
			String tanggal = m.group(4); 
			String no_peserta  = m.group(5); 
			String jabatan = m.group(6); 
			if(nama.length() > 50 ) {
				nama = nama.substring(0, 50); 
			}
			if( jabatan.length() > 50) {
				jabatan = jabatan.substring(0, 50); 
			}
			
			
			try {
				stmt.setString(1, nama); 
				stmt.setString(2, tanggal);
				stmt.setString(3, no_peserta);
				stmt.setString(4, jabatan);
				
				stmt.executeUpdate();

			}catch(Exception e) {
				System.out.println(no_peserta); 
			}
//			System.out.println(m .group()); 
//			if(a++ > 100) {
//				break; 
//			}
		}

	}

	
	
	public static void main(String[] args) {
		try {
			new RegexParser(); 
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
