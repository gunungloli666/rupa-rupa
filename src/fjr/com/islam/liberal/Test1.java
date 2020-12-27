package fjr.com.islam.liberal;

import java.io.FileOutputStream;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class Test1 {
	
	public static void main(String[] args) throws Exception {
		Document document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream("D:/test.pdf"));
		document.open();
		
		Font regular = new Font(FontFamily.HELVETICA, 22);
		Font bold = new Font(FontFamily.HELVETICA, 12, Font.ITALIC);
		Paragraph p = new Paragraph();
		p.add(new Chunk( "andi rizki" , regular));
		p.add(new Chunk(" gitaris" , bold)); 
		document.add(p); 
		
		document.close();
	}

}
