package fjr.com.gunawan.mohammad;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.gargoylesoftware.htmlunit.javascript.host.Element;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.pdf.PdfWriter;

import fjr.com.utils.FileUtils;

public class CreatePdfDoc {

	public static void main(String args[]) throws Exception {
		com.itextpdf.text.Rectangle paperSize = new com.itextpdf.text.Rectangle( 432 , 648); 
	    Document document = new Document(paperSize);
//	    PrintWriter out  = new PrintWriter(new FileOutputStre;am( "E:/scrap blog/gunawan muhammad/mamat.txt" )); 
		try {
			PdfWriter.getInstance(document, new FileOutputStream("E:/scrap blog/gunawan muhammad/makan.pdf"));
			document.open();
			String input = FileUtils.getFileString(new File("E:/scrap blog/gunawan muhammad/mamat.txt")); 
//			input = input.replaceAll("(\\r\\n){2}", "\r\n" );
//			out.println(input); 
			String[] temp = input.split("\\n"); 
			int a = 0; 
			for(String baris : temp) {
				if( baris.startsWith("[")) {
					baris = baris.replaceAll("\\[|\\]", "");
					String[] judul = baris.split("\\|"); 

					Chunk tanggal   = null , title  = null ; 
					Font f_regular = new Font(FontFamily.HELVETICA, 15, Font.BOLD);
					Font f_italic = new Font(FontFamily.TIMES_ROMAN, 12, Font.BOLDITALIC);
					if( judul.length == 2) {
						title = new Chunk(judul[0] + "\n" ,  f_regular); 
						tanggal = new Chunk(judul[1] , f_italic); 
					}else {
						title = new Chunk(judul[0] + "\n" , f_regular); 
						tanggal = new Chunk("" , f_italic); 
					}

					Paragraph paragraph = new Paragraph(16);
				    paragraph.setSpacingAfter(5f); 
				    paragraph.add(title); 
				    paragraph.add(tanggal);
					document.add(paragraph);
				}else {
				    Paragraph paragraph = new Paragraph(baris);
				    paragraph.setSpacingAfter(5f); 
				    paragraph.setFont(FontFactory.getFont(FontFactory.TIMES_ROMAN, 11f)) ;
				    paragraph.setFirstLineIndent(20f); 
//				    paragraph.setAlignment(com.itextpdf.text.Element.ALIGN_JUSTIFIED); 
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
