package in.ac.iitkgp.acaddwhDatagen.read;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

	public ArrayList<String> getStudentKeysWithAdmYr(String filePathStudent) throws Exception {
		ArrayList<String> dataValues = new ArrayList<String>();
		BufferedReader br = null;
		String line = null;

		try {
			br = new BufferedReader(new FileReader(filePathStudent));
			while ((line = br.readLine()) != null) {
				String[] values = line.split(",");
				System.out.println("Extracted student: " + values[0] + ", admission year: " + values[4]);
				dataValues.add(values[0] + "," + values[4]);
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

	public ArrayList<String> getTimeKeysWithStartingYear(String filePathTime) throws Exception {
		ArrayList<String> dataValues = new ArrayList<String>();
		BufferedReader br = null;
		String line = null;

		try {
			br = new BufferedReader(new FileReader(filePathTime));
			while ((line = br.readLine()) != null) {
				String[] values = line.split(",");
				System.out.println("Extracted time: " + values[0] + ", starting year: " + values[2].split("-")[0]);
				dataValues.add(values[0] + "," + values[2].split("-")[0]);
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

	public Map<String, String> getCgpaAndCountAndFailedMap(String filePathStuLearning) throws Exception {
		Map<String, String> cgpaAndCountAndFailedMap = new HashMap<String, String>();
		Map<String, Integer> cgpaMap = new HashMap<String, Integer>();
		Map<String, Integer> countMap = new HashMap<String, Integer>();
		Map<String, Integer> failedMap = new HashMap<String, Integer>();

		BufferedReader br = null;
		String line = null;

		try {
			br = new BufferedReader(new FileReader(filePathStuLearning));
			while ((line = br.readLine()) != null) {
				String[] values = line.split(",");
				String studentKey = values[2];
				int numGrade = Integer.parseInt(values[5]);
				System.out.println("Extracted student: " + studentKey + ", numGrade: " + numGrade);

				if (cgpaMap.containsKey(studentKey)) {
					int prevGrade = cgpaMap.get(studentKey);
					int prevCount = countMap.get(studentKey);
					cgpaMap.put(studentKey, prevGrade + numGrade);
					countMap.put(studentKey, prevCount + 1);

					if (numGrade < 6) {
						if (failedMap.containsKey(studentKey)) {
							int prevFailed = failedMap.get(studentKey);
							failedMap.put(studentKey, prevFailed + 1);
						} else {
							failedMap.put(studentKey, 1);
						}
					}

				} else {
					cgpaMap.put(studentKey, numGrade);
					countMap.put(studentKey, 1);
				}
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

		for (String studentKey : cgpaMap.keySet()) {
			double cgpa = cgpaMap.get(studentKey);
			int count = countMap.get(studentKey);
			int failed = 0;
			if (failedMap.containsKey(studentKey)) {
				failed = failedMap.get(studentKey);
			}

			String cgpaAndCount = new DecimalFormat("#.00").format(cgpa / count) + "_" + count + "_" + failed;
			cgpaAndCountAndFailedMap.put(studentKey, cgpaAndCount);
		}

		return cgpaAndCountAndFailedMap;
	}

}
