package fjr.cpns.jawa.timur;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;

public class PdfExtract {

	public PdfExtract()  throws Exception{
		
//		E:\birokrasi\lamaran kerja\CPNS 2018\pengumuman\pemprov jawa timur
		File f = new File("E:/birokrasi/lamaran kerja/CPNS 2018/pengumuman/pemprov jawa timur/pengumuman_hasil_seleksi_administrasi_pemprov_jatim_2018.pdf" );
		
		PdfReader reader = new PdfReader(f.getPath()); 
		PdfReaderContentParser parser = new PdfReaderContentParser(reader);
		TextExtractionStrategy strategy;
		PrintWriter out2 = new PrintWriter(new FileOutputStream( "E:/birokrasi/lamaran kerja/CPNS 2018/pengumuman/pemprov jawa timur/pengumuman_hasil_seleksi_administrasi_pemprov_jatim_2018.txt" ));
		for(int i = 1; i  <=  reader.getNumberOfPages(); i++) {
			strategy = parser.processContent(i , new SimpleTextExtractionStrategy()); 
			String result = strategy.getResultantText(); 
			out2.println(result);
			out2.flush();
			
		}
		
		out2.close();
	}
	
	public static void main(String[] args ) throws Exception {
		new PdfExtract();
	}
}
