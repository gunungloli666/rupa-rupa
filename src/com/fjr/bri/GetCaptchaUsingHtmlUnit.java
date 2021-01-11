package com.fjr.bri;

import java.io.FileOutputStream;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;

public class GetCaptchaUsingHtmlUnit {
	
	
	public static void main(String[] args) throws Exception{
		WebClient webClient = new WebClient();
		webClient.getCookieManager().clearCookies();
		webClient.getOptions().setJavaScriptEnabled(false);
		webClient.getOptions().setCssEnabled(false);
		
		Page p = webClient.getPage("https://ib.bri.co.id/ib-bri/login/captcha"); 
		InputStream response = p.getWebResponse().getContentAsStream();
		
		IOUtils.copy(response, new FileOutputStream("E:/captcha bri/13.jpeg")); 
		
	}

}
