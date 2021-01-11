package com.fjr.parigimoutong._2019.kartu_keluarga;

import java.io.FileOutputStream;
import java.io.InputStream;

import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class TestDownloader {

	public static void main(String[] args) {

		try {

			WebClient webClient = new WebClient();
			webClient.getCookieManager().clearCookies();
			webClient.getOptions().setJavaScriptEnabled(false);
			webClient.getOptions().setCssEnabled(false);
			HtmlPage page1 = webClient.getPage("https://sscndaftar.bkn.go.id/login");

			HtmlForm form = (HtmlForm) page1.getElementById("FormLogin");
			HtmlInput name = form.getInputByName("username");

			name.setValueAttribute("7208081305880002");
			HtmlInput passwordForm = form.getInputByName("password");
			passwordForm.setValueAttribute("09huiIll");
			HtmlButton button = (HtmlButton) page1.getElementById("btnSimpan");
			HtmlPage page2 = button.click();
			String result = page2.asText();
			if (!result.toLowerCase().contains("nik tidak ditemukan")
					&& !result.toLowerCase().contains("bad credential")) {
				
				System.out.println("helo world"); 
				Page page = webClient.getPage
						("https://sscndaftar.bkn.go.id/resume/buka.blob?jenisDokumen=2");
				InputStream inputStream = page.getWebResponse().getContentAsStream(); 
				FileOutputStream outputStream = new FileOutputStream("E:/hasil" + "/" + "ktp.jpg");
	            int read = 0;
	            byte[] bytes = new byte[1024];

	            while ((read = inputStream.read(bytes , 0, 1024)) != -1) {
	                outputStream.write(bytes, 0, read);
	            }
	            outputStream.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
