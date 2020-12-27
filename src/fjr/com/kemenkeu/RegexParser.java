package fjr.com.kemenkeu;

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
//	E:\birokrasi\lamaran kerja\CPNS 2018\pengumuman\kementerian keuangan
	public void putToTable() throws Exception{
		File f = new File( "E:/birokrasi/lamaran kerja/CPNS 2018/pengumuman/kementerian keuangan/lampiran-peng-03-hasil-seleksi-administrasi-rekrutmen-cpns-kementerian-keuangan-tahun-2018.txt" ); 

		Class.forName("org.postgresql.Driver") ;
		Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/olah_data", "postgres" , "09huiI" ); 

		
		String content = FileUtils. getFileString(f); 
		Pattern p = Pattern.compile("([0-9]{1,5})\\s([0-9]{16})\\s([A-Za-z\\`\\'\\s\\.\\,]+)\\s([0-9]{2}\\/[0-9]{2}\\/[0-9]{4})\\s(.*)"); 
		
		Matcher m = p.matcher(content); 
		int a = 1; 
		PreparedStatement stmt = connection.prepareStatement("insert into kemenkeu_2018( nik , nama, tanggal , jabatan ) "
				+ "values(?,?,? , ? )"); 

		while(m.find()) {
			String nik = m.group(2); 
			String nama = m.group(3).replace("\n", " "); 
			String tanggal = m.group(4) ; 
			String jabatan = m.group(5); 
			if(nama.length() > 50 ) {
				nama = nama.substring(0, 50); 
			}
			if( jabatan.length() > 50) {
				jabatan = jabatan.substring(0, 50); 
			}
			try {
				stmt.setString(1, nik ); 
				stmt.setString(2, nama );
				stmt.setString(3, tanggal);
				stmt.setString(4, jabatan);
				
				stmt.executeUpdate();

			}catch(Exception e) {
				System.out.println(nik); 
			}
//			System.out.println(m .group()); 
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
