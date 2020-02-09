package fjr.com.gunawan.mohammad;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Iterator;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class GunawanMuhammad {
	
	private PrintWriter out1 =  null; 
	
	public GunawanMuhammad() {
		HtmlPage page1 = null;
		WebClient webClient = new WebClient();
		webClient.getCookieManager().clearCookies();
		webClient.getOptions().setJavaScriptEnabled(false);
		webClient.getOptions().setCssEnabled(false);
		try {
			out1 = new PrintWriter(new FileOutputStream (  "./gunawan muhammad.txt"  )  ); 
			for(int i =1; i<=110; i++) {
				String pageNumber = Integer.toString(i); 
				page1= webClient.getPage( "https://caping.wordpress.com/page/" + pageNumber + "/" );
				DomNodeList<DomElement> list =  page1.getElementsByTagName("div");	
				Iterator<DomElement> iterator = list.iterator();
				while(iterator.hasNext()) {
					DomElement elem = iterator.next() ; 
					String attr = elem.getAttribute("class"); 
					if(attr.startsWith("post-")) {
						HtmlElement judul = elem.getElementsByTagName("h2").get(0); 
						HtmlElement subjudul1 = judul.getElementsByTagName("a").get(0);  
						HtmlElement subjudul2 = judul.getElementsByTagName("em").get(0);
						out1.println("[" + subjudul1.asText() + "|" + subjudul2.asText() + "]" );
						out1.println();
						DomNodeList<HtmlElement> pp =  elem.getElementsByTagName("p");
						Iterator<HtmlElement>  iterator2 = pp.iterator(); 
						while(iterator2.hasNext()) {
							HtmlElement innerp = iterator2.next(); 
							String isi = innerp.asText(); 
							if(!isi.isEmpty()) {
								out1.println(isi.replaceAll(  "\\p{Z}", " "    )); 
								out1.println(); 
							}
						}
						out1.println(); 
						out1.println(); 
						out1.flush();
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(out1 != null) {
				out1.close();
			}
		}
	}
	
	public static void main(String[] args) {
		new GunawanMuhammad();
	}
}
