package com.fjr.forlap.dikti;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class TestPost {
//	
//	<div class="controls">
//	<select name="id_sms" id="id_sms" class="input-xlarge">
//<option value="">- semua -</option>
//<option value="B817EEA8-A43B-459E-8828-CBCAD73DF145">Ilmu Ekonomi S3</option>
//<option value="1C68B82C-1FA8-4AE5-A6E9-4728453ACEAE">Ilmu Pertanian S3</option>

//	<option value="8FD788AC-741D-46DB-B7E2-A7BBDD508267">Teknik Listrik D3</option>
//	<option value="ED3F17BE-93C8-4C88-9289-0A7A5E5A1654">Teknik Mesin D3</option>
//	<option value="709952E1-D059-48A1-9A0D-CB15D11BDDB9">Teknik Sipil D3</option>
	
	public static void main(String[] args) throws Exception {
		 // "id":"8E5D195A-0035-41AA-AFEF-DB715A37B8DA","nama":"001028   Universitas Tadulako"}
		final URL url = new URL("https://forlap.ristekdikti.go.id/prodi/ajaxGetProdyByPT/" + "8E5D195A-0035-41AA-AFEF-DB715A37B8DA");
//		final URL url = new URL("https://forlap.ristekdikti.go.id/prodi/ajaxGetPT/univ") ;

//		final URL url = new URL("https://forlap.ristekdikti.go.id/ajax/listPT/tadulako") ;

		final URLConnection urlConnection = url.openConnection();
		urlConnection.setDoOutput(true);
		urlConnection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
		urlConnection.connect();
//		final OutputStream outputStream = urlConnection.getOutputStream();
//		outputStream.write(("{\"fNamn\": \"" + stringData + "\"}").getBytes("UTF-8"));
//		outputStream.flush();
		final InputStream inputStream = urlConnection.getInputStream();
		BufferedReader reader =   new BufferedReader(new InputStreamReader(inputStream));
		String data;
		while( (data = reader.readLine()) != null) {
			System.out.println(data); 
		}
	}
}
