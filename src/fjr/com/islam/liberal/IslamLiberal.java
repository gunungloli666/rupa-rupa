package fjr.com.islam.liberal;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlHeading1;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class IslamLiberal {
	private PrintWriter out1 = null;
	public IslamLiberal() {
		HtmlPage page1 = null, page = null;
		WebClient webClient = new WebClient();
		webClient.getCookieManager().clearCookies();
		webClient.getOptions().setJavaScriptEnabled(false);
		webClient.getOptions().setCssEnabled(false);
		try {
			out1 = new PrintWriter(new FileOutputStream("D:/islam liberal.txt"));
			page = webClient.getPage("http://islamlib.com/timeline/"); 
			HtmlElement mainDiv = page.getFirstByXPath("//div[@id='main-content']"); 
			DomNodeList<HtmlElement> elem = mainDiv.getElementsByTagName("ul") ;
			for(HtmlElement ul_ : elem) {
				String atrib = ul_.getAttribute("class"); 
				if(atrib.contains("timeline")) {
					DomNodeList< HtmlElement> li_elem = ul_.getElementsByTagName("li");
					for( HtmlElement li : li_elem) {
						HtmlElement aa = li.getElementsByTagName("a").get(0); 
						String url = aa.getAttribute("href"); 
						page1 = webClient.getPage(url + "?pps=full_post" );
						HtmlHeading1 elem1 = page1.getFirstByXPath("//h1[contains(@class, 'post-title')]");
						HtmlElement author = page1.getFirstByXPath("//span[contains(@class, 'post-meta-author')]");
						HtmlElement date = page1.getFirstByXPath("//span[contains(@class , 'tie-date' )]");
						out1.print( "[" + elem1.asText() + "||" );
						out1.print( author.asText() + "|" );
						out1.println( date.asText() + "]" );
						out1.flush();
						DomElement entri = page1.getElementsByTagName("article").get(0);
						DomNodeList<HtmlElement> paragraf = entri.getElementsByTagName("p");
						Iterator<HtmlElement> mm = paragraf.iterator(); 
						while( mm .hasNext()) {
							HtmlElement p = mm.next(); 
							String atribute = p.getAttribute("class"); 
							if(atribute == null || atribute.isEmpty()) {
								String isi = p.asText(); 
								if( ! isi.isEmpty()) {
									out1.println(isi);
 								}
							}
						}
						out1.println();
						out1.flush();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out1 != null) {
				out1.close();
			}
		}

	}

	public static void main(String[] args) {
		new IslamLiberal();
	}

}
