package fjr.cpns.dikti;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fjr.com.utils.FileUtils;

public class ExtractRegex2018 {
	class Peserta{
		String nama; 
		String noDaftar;
		public String getNama() {
			return nama;
		}
		public void setNama(String nama) {
			this.nama = nama;
		}
		public String getNoDaftar() {
			return noDaftar;
		}
		public void setNoDaftar(String noDaftar) {
			this.noDaftar = noDaftar;
		} 
	}
	PrintWriter out = null; 
	ArrayList<Peserta> daftarPeserta = new ArrayList<>();
	public ExtractRegex2018() throws IOException {
		out = new PrintWriter(new FileOutputStream( "E:/birokrasi/lamaran kerja/CPNS 2019/kemendikbud - dikti/dikti 2018 log 1.txt"  ));
		File f = new File("E:/birokrasi/lamaran kerja/CPNS 2019/kemendikbud - dikti/dikti 2018.txt" ); 
		String content = FileUtils. getFileString(f).replaceAll(  "\\p{Z}", " "    );

		Pattern p = Pattern.compile("(([A-Z\\.\\`\\'\\,]+\\s)*([A-Z\\.\\`\\'\\,]+))\\s([0-9]{10})", Pattern.MULTILINE ); 
		Matcher m = p.matcher(content);
		int a = 0;
		while(m.find()) {
			Peserta  peserta = new Peserta(); 
			peserta.setNama(m.group(1));
			peserta.setNoDaftar(m.group(4)); 
			daftarPeserta.add(peserta); 
			a++; 
		}
		
		Collections.sort(daftarPeserta , new Comparator<Peserta>() {
			@Override
			public int compare(Peserta o1, Peserta o2) {
				String m1 = o1.getNoDaftar(); 
				String m2 = o2.getNoDaftar(); 
				return m1.compareTo(m2);
			}
		});
		
		for( Peserta peserta : daftarPeserta ) {
			out.println(peserta.getNoDaftar() + "|" + peserta.getNama()); 
			out.flush();
		}
		
		System.out.println(a); 
		out.close();
		System.out.println("FINISH");
	}
	
	public static void main(String[] args) {
		try {
			new ExtractRegex2018();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
