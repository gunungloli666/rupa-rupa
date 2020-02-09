package fjr.com.gunawan.mohammad;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.regex.Pattern;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import fjr.com.utils.FileUtils;

public class CreatePdfDoc {

	public static void main(String args[]) throws Exception {

	    Document document = new Document();
//	    PrintWriter out  = new PrintWriter(new FileOutputStream( "E:/scrap blog/gunawan muhammad/mamat.txt" )); 
		try {
			PdfWriter.getInstance(document, new FileOutputStream("E:/scrap blog/gunawan muhammad/hasil77.pdf"));
			document.open();
			String input = FileUtils.getFileString(new File("E:/scrap blog/gunawan muhammad/mamat.txt")); 
//			input = input.replaceAll("(\\r\\n){2}", "\r\n" );
//			out.println(input); 
			String[] temp = input.split("\\n"); 
			int a = 0; 
			for(String baris : temp) {
				if( baris.startsWith("[")) {
				    Paragraph paragraph = new Paragraph(baris);
				    paragraph.setSpacingAfter(7f); 
//				    paragraph.setFont(FontFactory.getFont());
					document.add(paragraph);
				}else {
				    Paragraph paragraph = new Paragraph(baris);
				    paragraph.setSpacingAfter(7f); 
					document.add(paragraph);
				}
			}
			document.close();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
//		out.close();
	}

}
