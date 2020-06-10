package com.fjr.sulawesi_selatan._2019;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;


public class ConvertDocumentToImage {
	
	public ConvertDocumentToImage() {
		
		try {
			PDDocument document = PDDocument.load(new File("E:/birokrasi/lamaran kerja/CPNS 2019/sulawesi selatan/sulsel.pdf"));
			PDFRenderer pdfRenderer = new PDFRenderer(document);
			int a = 1;
			for (int page = 0; page < document.getNumberOfPages(); ++page)
			{ 
			    BufferedImage bim = pdfRenderer.renderImageWithDPI(page, 300, ImageType.RGB);
			    ImageIO.write(bim , "png" , 
			    		new File("E:/birokrasi/lamaran kerja/CPNS 2019/sulawesi selatan/hasil/" + Integer.toString(a++) + ".png"));  
			}
			document.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static void main(String[] args) {
		new ConvertDocumentToImage(); 
	}

}
