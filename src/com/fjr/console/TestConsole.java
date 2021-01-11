package com.fjr.console;

public class TestConsole {

	
	public static void main(String[] args) throws InterruptedException {
		String c =  "="; 
		for(int i= 0; i < 30 ; i++) {
			System.out.print("\r" + c  ); 
			c = c.concat("=");
			Thread.sleep(200);
		}
		System.out.println(); 
		System.out.println("FINISH");
	}
}
