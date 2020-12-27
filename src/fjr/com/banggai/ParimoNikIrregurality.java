package fjr.com.banggai;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ParimoNikIrregurality {
	
	BufferedReader reader = null; 
	public ParimoNikIrregurality() {
		
		int num = 0; 
		try {
			String content = null; 
			reader = new BufferedReader(new FileReader("E:/birokrasi/lamaran kerja/CPNS 2018/pengumuman/parigi moutong/hasil_login.txt"));
			while((content = reader.readLine() ) != null) {
				String[] temp = content.split("\\|"); 
				if( temp.length == 3) {
					String nik = temp[1];
					String old_tanggal = temp[2]; 
					
					String tanggal_nik = convertNikWanita(nik, true); 
					
					String[] temp_tanggal = old_tanggal.split("\\/"); 
					String new_tanggal = temp_tanggal[1].concat(temp_tanggal[0].concat(temp_tanggal[2])); 
					
					
					if( ! tanggal_nik.equals(new_tanggal)) {
						System.out.println(nik); 
						num++;
					}

				}
				
			}
		}catch(Exception e) {
			e.printStackTrace(); 
		}finally {
			if(reader!= null) {
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		System.out.println( num); 
	}
	
	
	
	public String convertNikWanita(String nik , boolean includeYearPrefix) {		
		String nik_ = nik.substring(6,12); 
		int tgl_ktp = Integer.valueOf(nik_.substring(0,2)); 
		if( tgl_ktp > 40) {
			tgl_ktp  = tgl_ktp - 40 ;
		}
		String tgl_ktp_str = Integer.toString(tgl_ktp);
		if(tgl_ktp_str.length() == 1) {
			tgl_ktp_str = "0".concat(tgl_ktp_str);
		}
		String bulan_ktp_str = nik_.substring(2,4); 
		String tahun_ktp_str = nik_.substring(4,6)  ;
		if( includeYearPrefix) {
			tahun_ktp_str = "19".concat( tahun_ktp_str ) ;
		}
		
		String tgl_final = tgl_ktp_str.concat( bulan_ktp_str ).concat( tahun_ktp_str );
		
		return tgl_final; 
	}
	
	
	public static void main(String[] args) {
		
		new ParimoNikIrregurality(); 
		
	}

}
