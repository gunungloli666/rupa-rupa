package fjr.com.islam.liberal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfWriter;

import fjr.com.utils.FileUtils;

public class CreatePdfDoc {
	
	public static void main(String[] args) throws IOException {
		com.itextpdf.text.Rectangle paperSize = new com.itextpdf.text.Rectangle( 432 , 648); 

	    Document document = new Document(paperSize);
		try {
			PdfWriter.getInstance(document, new FileOutputStream("D:/islam liberal.pdf"));
			document.open();
			String input = FileUtils.getFileString(new File("D:/islam liberal.txt")) ; 
			String[] temp = input.split("\\n"); 
			int a = 0; 
			for(String baris : temp) {
				if( baris.startsWith("[")) {
					baris = baris.replaceAll("\\[|\\]", "");
					String[] judul = baris.split("\\|\\|"); 
					String judulFull = ""; 
					Chunk tanggal   = null , title  = null ; 
					Font f_regular = new Font(FontFamily.HELVETICA, 15, Font.BOLD);
					Font f_italic = new Font(FontFamily.TIMES_ROMAN, 12, Font.BOLDITALIC);
					if( judul.length == 2) {
						title = new Chunk(judul[0] + "\n" ,  f_regular); 
						String tanggalBaru = judul[1].replace("|", ", ");
						tanggal = new Chunk(tanggalBaru , f_italic); 
					}else {
						title = new Chunk(judul[0] + "\n" , f_regular); 
						tanggal = new Chunk("" , f_italic); 
						judulFull = baris;
					}
				    Paragraph paragraph = new Paragraph(16);
				    paragraph.setSpacingAfter(5f); 
				    paragraph.add(title); 
				    paragraph.add(tanggal);
					document.add(paragraph);
				}else {
					if( ! baris.isEmpty()) {
//					    Paragraph paragraph = new Paragraph(baris);
//					    paragraph.setSpacingAfter(7f); 
//					    paragraph.setFirstLineIndent(20f); 
//						document.add(paragraph);
						
						Font f_isi = new Font(FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
						Chunk isi  = new Chunk(baris  + "\n" , f_isi); 
						
					    Paragraph paragraph = new Paragraph(baris);
					    paragraph.setSpacingAfter(0f); 
//					    paragraph.setFont(FontFactory.getFont(FontFactory.TIMES_ROMAN, 11f)) ;
					    paragraph.setFirstLineIndent(20f); 
//					    paragraph.add(isi);
//					    paragraph.setAlignment(com.itextpdf.text.Element.ALIGN_JUSTIFIED); 
						document.add(paragraph);

					}
				}
			}
			document.close();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
