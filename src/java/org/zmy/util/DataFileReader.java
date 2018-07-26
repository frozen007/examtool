package org.zmy.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class DataFileReader {
	
	public static String[] getLines(String file) {
		String[] rec = null;
		ArrayList<String> lineLst = new ArrayList<String>();
		try {
			String line = null;
			BufferedReader reader = new BufferedReader(new FileReader(file));
			line = reader.readLine();
			while (line != null) {
				if (!line.startsWith("#")) {
					lineLst.add(line);
				}
				line = reader.readLine();
			}
			reader.close();
			rec = new String[lineLst.size()];
			rec = lineLst.toArray(rec);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return rec;
	}

}
