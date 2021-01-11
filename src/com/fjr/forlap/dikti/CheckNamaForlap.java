package com.fjr.forlap.dikti;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlButtonInput;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class CheckNamaForlap {
	
	
//	<option value="B817EEA8-A43B-459E-8828-CBCAD73DF145">Ilmu Ekonomi S3</option>
//	<option value="1C68B82C-1FA8-4AE5-A6E9-4728453ACEAE">Ilmu Pertanian S3</option>
//	<option value="29C9F466-620D-4176-B5EE-792087E86381">Ilmu Sosial S3</option>
//	<option value="36F46939-376D-424E-BE4A-E98ED1ECE325">Pendidikan Sains S3</option>
//	<option value="A8FD0BD6-227D-4653-A30C-EF4DD981C42A">Administrasi Publik S2</option>
//	<option value="50C6BDE2-11B1-4723-B444-5BD292661CD0">Agribisnis S2</option>
//	<option value="80BE6DD2-BC06-4F2B-BE1F-50839E81EE40">Akuntansi S2</option>
//	<option value="8B098345-A206-427F-8E63-06BD12FE2D37">Ilmu Hukum S2</option>
//	<option value="53A1B56D-FC4B-4C6D-A3E3-FA9DBE64F49A">Ilmu Pertanian S2</option>
//	<option value="EE56E061-6480-4A69-B071-1C22731F5DDC">Manajemen S2</option>
//	<option value="91B2A1A4-9BB8-4359-BF94-6CFF0386A66A">Pembangunan Wilayah Pedesaan S2</option>
//	<option value="A63FA5B5-22FD-4FFF-B92C-3C6C3851C641">Pendidikan Bahasa Indonesia S2</option>
//	<option value="B921C726-FEB6-495D-BEDF-C680D1AD4AA5">Pendidikan Bahasa Inggris S2</option>
//	<option value="03166338-4E08-414B-86ED-7C3C4D096A01">Pendidikan Ilmu Pengetahuan Sosial S2</option>
//	<option value="0647854D-D45A-428F-B568-D1C5E9480722">Pendidikan Matematika S2</option>
//	<option value="2F53E875-E318-4E86-ADDF-E4DD432AD801">Pendidikan Sains S2</option>
//	<option value="0A53692B-4447-44C7-83D5-D64DBD75E0CE">Pendidikan Sejarah S2</option>
//	<option value="D3256050-190C-4744-AD87-E8FF996A2D04">Teknik Sipil S2</option>
//	<option value="A07A5D00-BFFB-4DE4-B547-DEEAD40F290F">Pendidikan Profesi Guru Profesi</option>
//	<option value="A51EE913-35A4-4A80-A6E0-39002835E2C2">Profesi Dokter Profesi</option>
//	<option value="C6835485-880C-488F-99AF-566019369ACD">Agribisnis S1</option>
//	<option value="695C0BF9-F30B-4D34-810C-B732BBE50896">Agroteknologi S1</option>
//	<option value="57A3401D-0221-44A9-A382-BA75AAB6CBCA">Agroteknologi (Kampus Kab. Morowali) S1</option>
//	<option value="55AB5723-F244-47E0-869D-108D4383967C">Agroteknologi Kampus Kab. Tojo Una-una S1</option>
//	<option value="C709EAEC-929C-41A0-876E-5ADD1AAEE7EE">Akuakultur S1</option>
//	<option value="9852743F-4A9E-4E91-AA8C-A37CDE7BD072">Akuntansi S1</option>
//	<option value="CB0F4449-CA5F-4646-B9BE-1112A57D5BF6">Antropologi S1</option>
//	<option value="D94ED379-5969-4292-BC59-B355C34D3528">Arsitektur S1</option>
//	<option value="011E76CB-B903-44B6-806A-1633DCAA7FFB">Bimbingan Dan Konseling S1</option>
//	<option value="20C6A91D-9CC2-4EA4-9F97-03170878B56A">Biologi S1</option>
//	<option value="19DB47C2-9479-4348-BEC6-EDEE02A8E465">Ekonomi dan Pembangunan S1</option>
//	<option value="2ED9EB6A-52C3-4525-9173-3DFC5114D94D">Farmasi S1</option>
//	<option value="C4D9D320-1DA3-491D-BF53-473AD345803A">Fisika S1</option>
//	<option value="EC58869F-E528-4042-B7F8-25B3E0164365">Gizi S1</option>
//	<option value="C153F12E-8E50-4086-BE9F-7FD936C8C054">Ilmu Administrasi Publik S1</option>
//	<option value="EDA8BBE8-ECF0-410E-B4F5-D45BA34AFD17">Ilmu Hukum S1</option>
//	<option value="DC81E63D-2769-4EB4-8A0E-0DD6392E9F5A">Ilmu Komunikasi S1</option>
//	<option value="DD3F5D02-1F77-4B45-ACD4-42715174A7D4">Ilmu Pemerintahan S1</option>
//	<option value="225558DA-5575-4DB8-A55D-0F7D372EBD06">Kedokteran S1</option>
//	<option value="A0D0D2AB-59FF-4520-BBED-A6D346D6FF3B">Kehutanan S1</option>
//	<option value="C54DA406-0E5E-4B98-8E76-0BFD3F958037">Kesehatan Masyarakat S1</option>
//	<option value="D974672C-C424-423C-9A93-185FE3B90346">Kimia S1</option>
//	<option value="2F61EFF7-0141-435B-A4BF-3C11D875257E">Manajemen S1</option>
//	<option value="F7AF3CDB-EB58-4C83-BEBB-21D108231DE5">Manajemen (Kampus Kab.Morowali) S1</option>
//	<option value="FB6CEE41-97A8-4AB2-8E8C-9D57D073FE42">Manajemen Kampus Kab. Tojo Una-una S1</option>
//	<option value="633779BC-31F7-476E-B67D-C333B673A876">Matematika S1</option>
//	<option value="CE28202C-60D0-49AC-91C4-9991BBAC20DF">Pendidikan Bahasa Dan Sastra Indonesia S1</option>
//	<option value="11E642AB-6B35-4CF8-9B17-F17DE70E27EE">Pendidikan Bahasa Inggris S1</option>
//	<option value="DC373A91-378F-4A58-89CF-E2A8356FBF7E">Pendidikan Biologi S1</option>
//	<option value="23F8AFA4-693C-4D88-AFE0-406EAE1B06CE">Pendidikan Fisika S1</option>
//	<option value="BB519664-F9C1-4C75-8942-BEAADA18A626">Pendidikan Geografi S1</option>
//	<option value="D9FDFE6C-66B4-4A86-A7A5-F3CFEF226742">Pendidikan Guru Pendidikan Anak Usia Dini S1</option>
//	<option value="DF79062F-DD32-4F06-A26B-751A57A6BF7E">Pendidikan Guru Sekolah Dasar S1</option>
//	<option value="BABA1427-E406-4168-A1B9-A77E6F1581F7">Pendidikan Jasmani, Kesehatan Dan Rekreas S1</option>
//	<option value="951CA50A-085A-4E82-B3CF-764C4986CA43">Pendidikan Kimia S1</option>
//	<option value="19FF63C7-907A-42B5-9C09-A44AB15D3FE0">Pendidikan Matematika S1</option>
//	<option value="27A1DD12-B81A-4A36-87A4-12FC8D5AF19E">Pendidikan Pancasila Dan Kewarganegaraan S1</option>
//	<option value="8D339D8A-DB4A-4B86-93E4-DA8D8CCCC18D">Pendidikan Sejarah S1</option>
//	<option value="531523E5-3889-4928-8250-1EC6557FAB3E">Perencanaan Wilayah dan Kota S1</option>
//	<option value="E2B31CBD-17AC-44E6-A27C-585A0DE1DBBA">Peternakan S1</option>
//	<option value="8D4C4149-AD50-47EF-A442-7C5EB65E45A3">PSKGJ Pendidikan Bahasa Inggris S1</option>
//	<option value="1AEF3D44-E8A3-429F-BB1F-50A264BA8027">PSKGJ Pendidikan Bahasa Sastra Indonesia dan Daerah S1</option>
//	<option value="BC7BE1B5-8CE6-4CED-9CC3-6F4D5A9220CC">PSKGJ Pendidikan Biologi S1</option>
//	<option value="2A558E6F-57C8-4B1B-86A5-FCA25947D5E2">PSKGJ Pendidikan Fisika S1</option>
//	<option value="2C40DA32-327F-4649-A059-556987889515">PSKGJ Pendidikan Guru Sekolah Dasar(PGSD) S1</option>
//	<option value="B0034BAC-8EA0-48BB-9A01-BBD54525F870">PSKGJ Pendidikan Kimia S1</option>
//	<option value="2EC1B0C2-D9DE-4E4F-A9BC-833032D5CAD5">PSKGJ Pendidikan Matematika S1</option>
//	<option value="90416681-5165-4DA5-AEED-A0D1E1EE1DA3">PSKGJ Pendidikan Pancasila dan Kewarganegaraan S1</option>
//	<option value="CE39ED38-E5C1-4A2F-A0ED-0B125C3B72E1">PSKGJ Pendidikan Sejarah S1</option>
//	<option value="F9B080ED-4BAF-4C08-A629-EE65B50392B0">Sosiologi S1</option>
//	<option value="159B2E50-E379-43B1-944E-E990C60CCC23">Statistika S1</option>
//	<option value="7B541FC4-BBD7-4275-AA1F-B06FE5907495">Teknik Elektro S1</option>
//	<option value="9E0B7539-851A-431C-AA63-3242196A0CCA">Teknik Geofisika S1</option>
//	<option value="F89AF899-9267-406E-94F8-8F18D2E58DE1">Teknik Geologi S1</option>
//	<option value="3422106F-DBF1-4505-AA60-D7A58D5BB105">Teknik Informatika S1</option>
//	<option value="D86E74AE-E7F6-4BD2-99F2-0921FFF1A2C4">Teknik Mesin S1</option>
//	<option value="E3616E5E-36FB-405D-B1E5-2F9AED5A1D79">Teknik Sipil S1</option>
//	<option value="7EDE7901-AF9B-4954-9C84-47CBAEEFE065">Teknik Sipil (Kampus Kab. Morowali) S1</option>
//	<option value="BE27ABD6-A91D-4AE7-9AD8-E4689F4475E0">Teknik Sipil Kampus Kab Tojo Una-una S1</option>
//	<option value="A8955887-75F3-4FF9-925F-2D392435C310">Akuntansi D3</option>
//	<option value="DD520B99-1881-4E04-B435-DB8F7C709082">Keperawatan D3</option>
//	<option value="F4ADEDA2-0C5A-40C4-8335-89E31FE0B8EA">Konservasi Sumberdaya Hutan D3</option>
//	<option value="94A79C1D-9104-4060-8975-DAE868C1BBF2">Manajemen Pemasaran D3</option>
//	<option value="6FEDB336-4248-436C-879F-741D69CA9C41">Teknik Bangunan D3</option>
//	<option value="8FD788AC-741D-46DB-B7E2-A7BBDD508267">Teknik Listrik D3</option>
//	<option value="ED3F17BE-93C8-4C88-9289-0A7A5E5A1654">Teknik Mesin D3</option>
//	<option value="709952E1-D059-48A1-9A0D-CB15D11BDDB9">Teknik Sipil D3</option>

	static String m[] = {"x" , "b"};
	
	static String data[]  = {
			"B817EEA8-A43B-459E-8828-CBCAD73DF145",
			"1C68B82C-1FA8-4AE5-A6E9-4728453ACEAE",
			"29C9F466-620D-4176-B5EE-792087E86381",
			"36F46939-376D-424E-BE4A-E98ED1ECE325",
			"A8FD0BD6-227D-4653-A30C-EF4DD981C42A",
			"50C6BDE2-11B1-4723-B444-5BD292661CD0",
			"80BE6DD2-BC06-4F2B-BE1F-50839E81EE40",
			"8B098345-A206-427F-8E63-06BD12FE2D37",
			"53A1B56D-FC4B-4C6D-A3E3-FA9DBE64F49A",
			"EE56E061-6480-4A69-B071-1C22731F5DDC",
			"91B2A1A4-9BB8-4359-BF94-6CFF0386A66A",
			"A63FA5B5-22FD-4FFF-B92C-3C6C3851C641",
			"B921C726-FEB6-495D-BEDF-C680D1AD4AA5",
			"03166338-4E08-414B-86ED-7C3C4D096A01",
			"0647854D-D45A-428F-B568-D1C5E9480722",
			"2F53E875-E318-4E86-ADDF-E4DD432AD801",
			"0A53692B-4447-44C7-83D5-D64DBD75E0CE",
			"D3256050-190C-4744-AD87-E8FF996A2D04",
			"A07A5D00-BFFB-4DE4-B547-DEEAD40F290F",
			"A51EE913-35A4-4A80-A6E0-39002835E2C2",
			"C6835485-880C-488F-99AF-566019369ACD",
			"695C0BF9-F30B-4D34-810C-B732BBE50896",
			"57A3401D-0221-44A9-A382-BA75AAB6CBCA",
			"55AB5723-F244-47E0-869D-108D4383967C",
			"C709EAEC-929C-41A0-876E-5ADD1AAEE7EE",
			"9852743F-4A9E-4E91-AA8C-A37CDE7BD072",
			"CB0F4449-CA5F-4646-B9BE-1112A57D5BF6",
			"D94ED379-5969-4292-BC59-B355C34D3528",
			"011E76CB-B903-44B6-806A-1633DCAA7FFB",
			"20C6A91D-9CC2-4EA4-9F97-03170878B56A",
			"19DB47C2-9479-4348-BEC6-EDEE02A8E465",
			"2ED9EB6A-52C3-4525-9173-3DFC5114D94D",
			"C4D9D320-1DA3-491D-BF53-473AD345803A",
			"EC58869F-E528-4042-B7F8-25B3E0164365",
			"C153F12E-8E50-4086-BE9F-7FD936C8C054",
			"EDA8BBE8-ECF0-410E-B4F5-D45BA34AFD17",
			"DC81E63D-2769-4EB4-8A0E-0DD6392E9F5A",
			"DD3F5D02-1F77-4B45-ACD4-42715174A7D4",
			"225558DA-5575-4DB8-A55D-0F7D372EBD06",
			"A0D0D2AB-59FF-4520-BBED-A6D346D6FF3B",
			"C54DA406-0E5E-4B98-8E76-0BFD3F958037",
			"D974672C-C424-423C-9A93-185FE3B90346",
			"2F61EFF7-0141-435B-A4BF-3C11D875257E",
			"F7AF3CDB-EB58-4C83-BEBB-21D108231DE5",
			"FB6CEE41-97A8-4AB2-8E8C-9D57D073FE42",
			"633779BC-31F7-476E-B67D-C333B673A876",
			"CE28202C-60D0-49AC-91C4-9991BBAC20DF",
			"11E642AB-6B35-4CF8-9B17-F17DE70E27EE",
			"DC373A91-378F-4A58-89CF-E2A8356FBF7E",
			"23F8AFA4-693C-4D88-AFE0-406EAE1B06CE",
			"BB519664-F9C1-4C75-8942-BEAADA18A626",
			"D9FDFE6C-66B4-4A86-A7A5-F3CFEF226742",
			"DF79062F-DD32-4F06-A26B-751A57A6BF7E",
			"BABA1427-E406-4168-A1B9-A77E6F1581F7",
			"951CA50A-085A-4E82-B3CF-764C4986CA43",
			"19FF63C7-907A-42B5-9C09-A44AB15D3FE0",
			"27A1DD12-B81A-4A36-87A4-12FC8D5AF19E",
			"8D339D8A-DB4A-4B86-93E4-DA8D8CCCC18D",
			"531523E5-3889-4928-8250-1EC6557FAB3E",
			"E2B31CBD-17AC-44E6-A27C-585A0DE1DBBA",
			"8D4C4149-AD50-47EF-A442-7C5EB65E45A3",
			"1AEF3D44-E8A3-429F-BB1F-50A264BA8027",
			"BC7BE1B5-8CE6-4CED-9CC3-6F4D5A9220CC",
			"2A558E6F-57C8-4B1B-86A5-FCA25947D5E2",
			"2C40DA32-327F-4649-A059-556987889515",
			"B0034BAC-8EA0-48BB-9A01-BBD54525F870",
			"2EC1B0C2-D9DE-4E4F-A9BC-833032D5CAD5",
			"90416681-5165-4DA5-AEED-A0D1E1EE1DA3",
			"CE39ED38-E5C1-4A2F-A0ED-0B125C3B72E1",
			"F9B080ED-4BAF-4C08-A629-EE65B50392B0",
			"159B2E50-E379-43B1-944E-E990C60CCC23",
			"7B541FC4-BBD7-4275-AA1F-B06FE5907495",
			"9E0B7539-851A-431C-AA63-3242196A0CCA",
			"F89AF899-9267-406E-94F8-8F18D2E58DE1",
			"3422106F-DBF1-4505-AA60-D7A58D5BB105",
			"D86E74AE-E7F6-4BD2-99F2-0921FFF1A2C4",
			"E3616E5E-36FB-405D-B1E5-2F9AED5A1D79",
			"7EDE7901-AF9B-4954-9C84-47CBAEEFE065",
			"BE27ABD6-A91D-4AE7-9AD8-E4689F4475E0",
			"A8955887-75F3-4FF9-925F-2D392435C310",
			"DD520B99-1881-4E04-B435-DB8F7C709082",
			"F4ADEDA2-0C5A-40C4-8335-89E31FE0B8EA",
			"94A79C1D-9104-4060-8975-DAE868C1BBF2",
			"6FEDB336-4248-436C-879F-741D69CA9C41",
			"8FD788AC-741D-46DB-B7E2-A7BBDD508267",
			"709952E1-D059-48A1-9A0D-CB15D11BDDB9",
			"ED3F17BE-93C8-4C88-9289-0A7A5E5A1654"
	
	};
	
	
	public static void main(String[] args) throws Exception{
		HtmlPage page1 = null;
		WebClient webClient = new WebClient();
		webClient.getCookieManager().clearCookies();
		webClient.getOptions().setJavaScriptEnabled(false);
		webClient.getOptions().setCssEnabled(false);
		
		page1 = webClient.getPage("https://forlap.ristekdikti.go.id/mahasiswa"); 
		
		
		// "value":"8E5D195A-0035-41AA-AFEF-DB715A37B8DA","label":"001028   Universitas Tadulako"
		
		
		HtmlForm form = (HtmlForm) page1.getElementById("searchMhsForm"); 
		HtmlInput inputUniv = (HtmlInput) page1.getElementById("id_sp"); 
		inputUniv.setValueAttribute("8E5D195A-0035-41AA-AFEF-DB715A37B8DA"); 
		HtmlInput inputProdi  = (HtmlInput) page1.createElement("input"); 
		inputProdi.setAttribute("name", "id_sms"); 
		form.appendChild(inputProdi);  
		inputProdi.setValueAttribute(data[0]);
		
		HtmlInput kataKunci = (HtmlInput)page1.getElementById("keyword"); 
		kataKunci.setValueAttribute("Fajar Rizki"); 
		
		HtmlInput captcha1 = (HtmlInput) page1.getElementsByName("captcha_value_1").get(0); 
		String value = captcha1.getValueAttribute(); 
		HtmlInput captcha2 = (HtmlInput) page1.getElementsByName("captcha_value_2").get(0); 
		String value2 = captcha2.getValueAttribute(); 
		
		int a = Integer.parseInt(value); 
		int b = Integer.parseInt(value2); 
		String c = Integer.toString(a + b); 
		
		HtmlInput kodePengaman = (HtmlInput) page1.getElementById("kode_pengaman"); 
		kodePengaman.setValueAttribute(c);
		
		HtmlButton button = (HtmlButton) page1.createElement("button"); 
//		form.appendChild(button);
		
		HtmlPage page2 = button.click(); 
		
		System.out.println(page2.asXml()); 
		
	}

}
