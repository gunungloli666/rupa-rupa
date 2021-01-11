package com.fjr.forlap.dikti;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlButtonInput;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;

public class TestSubmit {

	// <option value="1E3CBA8D-B74F-4DB9-B2DC-48019C82B836">
	// Fisika S2
	// </option>

	public static void main(String[] args) {

		HtmlPage page1 = null;
		WebClient webClient = new WebClient();
		webClient.getCookieManager().clearCookies();
		webClient.getOptions().setJavaScriptEnabled(false);
		webClient.getOptions().setCssEnabled(false);
		try {
			page1 = webClient.getPage("https://forlap.ristekdikti.go.id/mahasiswa");
			HtmlForm form = (HtmlForm) page1.getElementById("searchMhsForm");
			HtmlInput inputPt = form.getInputByName("id_sp");
			inputPt.setValueAttribute("A1E8C356-48EF-4871-AF3E-85079443F952");

			HtmlElement element = (HtmlElement) page1.createElement("input");
			element.setAttribute("name", "id_sms");
			element.setAttribute("value", "1E3CBA8D-B74F-4DB9-B2DC-48019C82B836");
			form.appendChild(element);
			
			HtmlInput inputKeyword = form.getInputByName("keyword");
			inputKeyword.setValueAttribute("Andi Rizaldi"); 

			HtmlInput cap1 = form.getInputByName("captcha_value_1");
			HtmlInput cap2 = form.getInputByName("captcha_value_2");
			String a = cap1.getValueAttribute() ;
			String b = cap2.getValueAttribute(); 
			int c = Integer.valueOf(a)  + Integer.valueOf(b); 
			
			HtmlInput inputCaptcha = form.getInputByName("kode_pengaman");
			inputCaptcha.setValueAttribute(Integer.toString(c)); 

			HtmlButtonInput buttonClik =  form.getInputByValue("Cari Mahasiswa");
			HtmlPage result = buttonClik.click();

			System.out.println(result.asXml());  
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
