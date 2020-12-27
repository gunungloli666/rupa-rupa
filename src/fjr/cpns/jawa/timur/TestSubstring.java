package fjr.cpns.jawa.timur;

import org.apache.commons.lang3.StringUtils;

public class TestSubstring {

	public static void main(String[] args) {
//		String xx = String.format("%-50s", "ABC");
		String xx = StringUtils.rightPad("masa sih", 20 , "*"); 
		System.out.println((xx)); 

	}
}
