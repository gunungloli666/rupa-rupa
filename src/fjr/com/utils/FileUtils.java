package fjr.com.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtils {

	public static String readAllBytes(String filePath) {
		String content = "";
		try {
			content = new String(Files.readAllBytes(Paths.get(filePath)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return content;
	}

	public static String getFileString(File file) throws IOException {
		byte[] buffer = new byte[(int) file.length()];
		FileInputStream fileIn = null;
		try {
			fileIn = new FileInputStream(file);
			fileIn.read(buffer);
		} finally {
			if (fileIn != null) {
				try {
					fileIn.close();
				} catch (IOException ex) {
				}
			}
		}

		return new String(buffer);
	}
}
