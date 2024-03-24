package com.dsp.shared.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtils {
	
	public static String save(byte[] imageBytes, String path, String fileName, FileType type) {
		String fullPath = path + File.separator + fileName + type.value;
		try {
			File file = new File("static/" + fullPath);
	        file.createNewFile();
			Files.write(Paths.get("static/" + fullPath), imageBytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fullPath;
	}

}
