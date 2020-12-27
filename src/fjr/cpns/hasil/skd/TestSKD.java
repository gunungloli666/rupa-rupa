package fjr.cpns.hasil.skd;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;


import fjr.com.utils.FileUtils;

public class TestSKD {
	class PesertaSKD{
		private String noPeserta; 
		private String nama;
		private String totalSKD; 
		private String keterangan;
		private String twk; 
		private String tiu; 
		private String tkp;
		public String getNoPeserta() {
			return noPeserta;
		}
		public void setNoPeserta(String noPeserta) {
			this.noPeserta = noPeserta;
		}
		public String getNama() {
			return nama;
		}
		public void setNama(String nama) {
			this.nama = nama;
		}
		public String getTotalSKD() {
			return totalSKD;
		}
		public void setTotalSKD(String totalSKD) {
			this.totalSKD = totalSKD;
		}
		public String getKeterangan() {
			return keterangan;
		}
		public void setKeterangan(String keterangan) {
			this.keterangan = keterangan;
		}
		public String getTwk() {
			return twk;
		}
		public void setTwk(String twk) {
			this.twk = twk;
		}
		public String getTiu() {
			return tiu;
		}
		public void setTiu(String tiu) {
			this.tiu = tiu;
		}
		public String getTkp() {
			return tkp;
		}
		public void setTkp(String tkp) {
			this.tkp = tkp;
		} 
	}
	
	
	Pattern p = Pattern.compile("[0-9]{1,3}\\s([0-9]{17})\\s([A-Z\\.\\`\\'\\,\\s\\n\\r]+)"
			+ "\\s([0-9]{7})\\s([0-9]{2,3})\\s([0-9]{2,3})\\s([0-9]{2,3})"
			+ "\\s([0-9]{2,3})\\s(.+)");  
	
	PrintWriter out;
	
	public TestSKD(String fileinput, String fileoutput)  {
		try {
		File ff = new File( "./" + fileinput  ); 
		out = new PrintWriter(new File("./" + fileoutput  )); 
		String input = FileUtils.getFileString(ff); 
		Matcher m = p.matcher(input); 
		int  i = 0; 
		ArrayList<PesertaSKD> daftarPeserta = new ArrayList<>(); 
		while( m .find()) {
			PesertaSKD peserta = new PesertaSKD(); 

			peserta.setNoPeserta(m.group(1)); 
			peserta.setNama(m.group(2).replaceAll("[\\r\\n]", ""));
			peserta.setTwk(m.group(5));
			peserta.setTiu(m.group(4)); 
			peserta.setTkp(m.group(6));
			peserta.setTotalSKD(m.group(7)); 
			peserta.setKeterangan(m.group(8)); 
			
			daftarPeserta.add(peserta); 

		}
		
		
		Collections.sort(daftarPeserta, new  Comparator<PesertaSKD>() {
			@Override
			public int compare(PesertaSKD pesertaA, PesertaSKD pesertaB) {
				Integer tiuA = Integer.parseInt(pesertaA.getTiu());
				Integer tiuB = Integer.parseInt(pesertaB.getTiu()); 
				int tiuStatus = tiuA.compareTo(tiuB); 
				if(tiuStatus != 0) {
					return tiuStatus * -1 ;
				}else {
					Integer totalA = Integer.parseInt(pesertaA.getTotalSKD()); 
					Integer totalB = Integer.parseInt(pesertaB.getTotalSKD());
					return totalA.compareTo(totalB) * -1 ;
				}
			}
		});
		
		out.print( StringUtils.rightPad("No peserta",  17) + "   ");
		out.print(StringUtils.rightPad("Nama", 40 , " "));
		out.print(StringUtils.rightPad( "TWK" ,  5 , " ")); 
		out.print(StringUtils.rightPad("TIU",  5 , " ")); 
		out.print(StringUtils.rightPad("TKP" ,  5 , " ")); 
		out.print(StringUtils.rightPad("TOT",  5 , " ")); 
		out.print(StringUtils.rightPad("Keterangan " ,  7 , " ") );
		out.println(); 
		
		for( PesertaSKD p : daftarPeserta) {
			out.print( p.getNoPeserta() + "   ");
			out.print(StringUtils.rightPad(p.getNama(), 40 , " "));
			out.print(StringUtils.rightPad(p.getTwk(),  5 , " ")); 
			out.print(StringUtils.rightPad(p.getTiu(),  5 , " ")); 
			out.print(StringUtils.rightPad(p.getTkp(),  5 , " ")); 
			out.print(StringUtils.rightPad(p.getTotalSKD(),  5 , " ")); 
			out.print(StringUtils.rightPad(p.getKeterangan() ,  7 , " ") );
			out.println(); 
		}
		}catch(Exception e) {
			e.printStackTrace(); 
		}finally {
			if( out!= null) {
				out.close();
			}
		}
		
	}
	
	
	public static void main(String[] args) throws IOException {
		new TestSKD(args[0], args[1]);  
	}
}
