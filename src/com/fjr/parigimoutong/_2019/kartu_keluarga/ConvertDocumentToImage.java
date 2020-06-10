package com.fjr.parigimoutong._2019.kartu_keluarga;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;


public class ConvertDocumentToImage {
	
	public ConvertDocumentToImage( ) {
		
		try {
			
			File root = new File("."); 
			File ff[] =  root.listFiles(); 
			
			try {
				for( File currentFile : ff) {
					System.out.println( currentFile.getParent()) ; 
					String path = currentFile.getAbsolutePath() .replace("\\", "/" ); 
					int id1 = path.lastIndexOf("."); 
					path = path.substring(0, id1) + path.substring(id1)  + "/" + "kk.pdf" ; 
					System.out.println("path awal: " + path ); 
		  			PDDocument document = PDDocument.load(new File( path ));
					PDFRenderer pdfRenderer = new PDFRenderer(document);
					int a = 1;
					int numberOfPages = document.getNumberOfPages(); 
					System.out.println("jumlah halaman: "+ numberOfPages); 
					int last = numberOfPages  - 1;
					BufferedImage bim  = pdfRenderer.renderImageWithDPI(last,  300 , ImageType.RGB );
					int index = path.lastIndexOf("/"); 
					String outpath = path.substring( 0 , index )  + "/" + "converted.png" ;   
					ImageIO.write(bim , "png" , new File( outpath) ); 
					System.out.println("path akhir: "  + outpath);  
					document.close();
				}
				
			}catch(Exception e) {
				System.out.println("eror proses satu file"); 
				System.out.println(e.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static void main(String[] args) {
		new ConvertDocumentToImage(); 
	}

}
