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

	public ArrayList<String> getAdmissionYears(String filePathStudent) throws Exception {
		ArrayList<String> dataValues = new ArrayList<String>();
		BufferedReader br = null;
		String line = null;

		try {
			br = new BufferedReader(new FileReader(filePathStudent));
			while ((line = br.readLine()) != null) {
				String[] values = line.split(",");
				System.out.println("Extracted year: " + values[4]);
				dataValues.add(values[4]);
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

		return dataValues;
	}

	public ArrayList<String> getCourseKeysWithDept(String filePathCourse) throws Exception {
		ArrayList<String> dataValues = new ArrayList<String>();
		BufferedReader br = null;
		String line = null;

		try {
			br = new BufferedReader(new FileReader(filePathCourse));
			while ((line = br.readLine()) != null) {
				String[] values = line.split(",");
				System.out.println("Extracted course: " + values[0] + ", department: " + values[3]);
				dataValues.add(values[0] + "," + values[3]);
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

		return dataValues;
	}

	public ArrayList<String> getTeacherKeysWithDept(String filePathEvalArea) throws Exception {
		ArrayList<String> dataValues = new ArrayList<String>();
		BufferedReader br = null;
		String line = null;

		try {
			br = new BufferedReader(new FileReader(filePathEvalArea));
			while ((line = br.readLine()) != null) {
				String[] values = line.split(",");
				System.out.println("Extracted teacher: " + values[0] + ", department: " + values[1]);
				dataValues.add(values[0] + "," + values[1]);
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

		return dataValues;
	}

}
