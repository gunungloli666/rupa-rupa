package com.fjr.reset.sscn.password;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class Test1 {

	public void sendReq() throws ClientProtocolException, IOException {
		
		CloseableHttpClient client = HttpClients.createDefault();
	    HttpPost httpPost = new HttpPost("https://helpdesk.bkn.go.id/sscn/password_reseted");
	 
	    ArrayList<BasicNameValuePair> params = new ArrayList<>();
	    params.add(new BasicNameValuePair("nik", "7208081305880002"));
	    params.add(new BasicNameValuePair("nik_lupa" , "7208081305880002"));
	    params.add(new BasicNameValuePair("password1" , "dewi666"));
	    params.add(new BasicNameValuePair("password2" , "dewi666"));
	    params.add(new BasicNameValuePair("answer" , "sss"));

	    httpPost.setEntity(new UrlEncodedFormEntity(params));
	 
	    CloseableHttpResponse response = client.execute(httpPost);
	    System.out.println(	EntityUtils.toString(response.getEntity()) + "ss"); 
//	    assertThat(response.getStatusLine().getStatusCode(), equalTo(200));
	    client.close();

	}
	
	public static void main(String[ ] args) {
		try {
			new Test1().sendReq();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
