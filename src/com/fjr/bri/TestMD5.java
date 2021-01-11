package com.fjr.bri;

import org.apache.commons.codec.digest.DigestUtils;

public class TestMD5 {
	
	public static void main(String[] args) {
		for(int i = 0 ; i < 10 ; i++) {
			String x  = DigestUtils.md5Hex("siang"); 
			System.out.println(x); 
		}
	}

}
