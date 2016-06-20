package in.ac.iitkgp.acaddwhDatagen.read;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DataReader {

	public ArrayList<String> getFKeys(String filePath) throws Exception {
		ArrayList<String> keys = new ArrayList<String>();
		BufferedReader br = null;
		String line = null;

		try {
			br = new BufferedReader(new FileReader(filePath));
			while ((line = br.readLine()) != null) {
				String[] values = line.split(",");
				System.out.println("Extracted key: " + values[0]);
				keys.add(values[0]);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return keys;
	}

}
