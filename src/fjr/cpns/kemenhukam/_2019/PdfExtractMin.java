package fjr.cpns.kemenhukam._2019;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;

public class PdfExtractMin {

	public PdfExtractMin(String fileName, String outputName)  throws Exception{
		File f = new File( "./" + fileName );
		PdfReader reader = new PdfReader(f.getPath()); 
		PdfReaderContentParser parser = new PdfReaderContentParser(reader);
		TextExtractionStrategy strategy;
		PrintWriter out2 = new PrintWriter(new FileOutputStream(  "./"  + outputName  ));
		for(int i = 1; i  <=  reader.getNumberOfPages(); i++) {
			strategy = parser.processContent(i , new SimpleTextExtractionStrategy()); 
			String result = strategy.getResultantText(); 
			out2.println(result);
			out2.flush();
			
		}
		out2.close();
	}
	
	public static void main(String[] args ) throws Exception {
		new PdfExtractMin(args[0], args[1]);
	}
}
