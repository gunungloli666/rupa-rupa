package com.fjr.forlap.dikti;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class CekNamaPerguruanTinggi {

//	 [{"id":"A1E8C356-48EF-4871-AF3E-85079443F952","nama":"002001   Institut Teknologi Bandung"}] 
//	"id":"8ED1D0CE-F122-4B37-A849-25F81B335395","nama":"001001   Universitas Gadjah Mada"
//	"id":"0D1E63E9-CBFB-4546-A242-875C310083A5","nama":"001002   Universitas Indonesia" 
//	 [{"id":"89C82DA3-7B28-4305-82C4-347DAE042847","nama":"002002   Institut Teknologi Sepuluh Nopember"}] 
	
	
	public static void main(String[] args) {
		
		HtmlPage page1 = null;
		WebClient webClient = new WebClient();
		webClient.getCookieManager().clearCookies();
		webClient.getOptions().setJavaScriptEnabled(false);
		webClient.getOptions().setCssEnabled(false);
		try {
			page1 = webClient.getPage
					( "https://forlap.ristekdikti.go.id/prodi/ajaxGetPT/Institut Teknologi Sep" ); 
			System.out.println( page1.asXml()); 
			
		}catch(Exception e) {
			e.printStackTrace();
		}

	}
}
