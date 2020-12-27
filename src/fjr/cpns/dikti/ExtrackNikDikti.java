package fjr.cpns.dikti;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fjr.com.utils.FileUtils;

public class ExtrackNikDikti {
	PrintWriter out = null; 
	public ExtrackNikDikti(String input, String output,  String prefix) throws IOException {
		out = new PrintWriter(new FileOutputStream( "./" + output  ));
		File f = new File("./" + input ); 
		String content = FileUtils. getFileString(f); 
		Pattern p = Pattern.compile("([0-9]{1,5})\\s([0-9]{17})\\s([A-Za-z\\`\\'\\s\\.\\,]+)\\sUMUM"); 
		Matcher m = p.matcher(content);
		int a = 0;
		while(m.find()) {
			String kode_ktp = m.group(2); 
			String nama = m.group(3); 
			String  sb = new StringBuilder(kode_ktp).reverse().substring(0, 16); 
			if(sb.startsWith( prefix )) { // untuk sulawesi saja
				out.println(sb + "|" + nama);
				out.flush();
			}
		}
		out.close();
		System.out.println("FINISH");
	}
	
	
	public static void main(String[] args) {
		try {
			new ExtrackNikDikti(args[0], args[1], args[2]); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

}
