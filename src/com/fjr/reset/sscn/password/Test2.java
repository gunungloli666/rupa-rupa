package com.fjr.reset.sscn.password;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class Test2 {
	public void sendReq() throws ClientProtocolException, IOException {
		
		CloseableHttpClient client = HttpClients.createDefault();
	    HttpPost httpPost = new HttpPost("https://sscndaftar.bkn.go.id/login/login");
	 
	    ArrayList<BasicNameValuePair> params = new ArrayList<>();
	    params.add(new BasicNameValuePair("username", "7208081305880002"));
	    params.add(new BasicNameValuePair("password" , "09huiIll"));
//	    params.add(new BasicNameValuePair("password1" , "dewi666"));
//	    params.add(new BasicNameValuePair("password2" , "dewi666"));
	    httpPost.setEntity(new UrlEncodedFormEntity(params));
	 
	    CloseableHttpResponse response = client.execute(httpPost);
	    System.out.println(	EntityUtils.toString(response.getEntity())); 
//	    assertThat(response.getStatusLine().getStatusCode(), equalTo(200));
	    client.close();

	}
	
	public static void main(String[ ] args) {
		try {
			new Test2().sendReq();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
