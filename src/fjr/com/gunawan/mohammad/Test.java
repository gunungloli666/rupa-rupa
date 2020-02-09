package fjr.com.gunawan.mohammad;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import fjr.com.utils.FileUtils;

public class Test {
	
	public static void main(String[] args) {
	    Document document = new Document();

		try {
			PdfWriter.getInstance(document, new FileOutputStream("E:/makan.pdf"));
			document.open();
			Paragraph paragraph = new Paragraph("saya sedang makan di rumah");
			document.add(paragraph); 
			paragraph = new Paragraph("Ada di rumah setan" );
			document.add(paragraph); 

			document.close();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

}
