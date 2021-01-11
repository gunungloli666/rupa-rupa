package com.fjr.forlap.dikti;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class CekAlumni {
//	 <option value="1E3CBA8D-B74F-4DB9-B2DC-48019C82B836">
//     Fisika S2
//   </option>
//	A1E8C356-48EF-4871-AF3E-85079443F952
	
//    <select name="id_sms" id="id_sms" class="input-xlarge">
//    <option value="" selected="selected">
//      - semua -
//    </option>
//    <option value="E7B224BC-9587-4A36-89F1-CB14DDBF084F">
//      Arsitektur S3
	
	public CekAlumni() {
		HtmlPage page1 = null;
		WebClient webClient = new WebClient();
		webClient.getCookieManager().clearCookies();
		webClient.getOptions().setJavaScriptEnabled(false);
		webClient.getOptions().setCssEnabled(false);
		try {
			page1 = webClient.getPage
					( "https://forlap.ristekdikti.go.id/prodi/ajaxGetProdyByPT/A1E8C356-48EF-4871-AF3E-85079443F952/" ); 
			System.out.println( page1.asXml()); 
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {	
		new CekAlumni(); 
	}

}
