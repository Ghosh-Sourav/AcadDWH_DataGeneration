package in.ac.iitkgp.acaddwhDatagen.gen.fact;

import java.util.ArrayList;
import java.util.Map;

import in.ac.iitkgp.acaddwhDatagen.config.ProjectInfo;
import in.ac.iitkgp.acaddwhDatagen.read.DataReader;
import in.ac.iitkgp.acaddwhDatagen.util.DataWriter;
import in.ac.iitkgp.acaddwhDatagen.util.RandomGen;

public class SemPerformanceGen {

	public String generate(ArrayList<String> splKeys, ArrayList<String> studentKeys,
			Map<String, String> cgpaAndCountAndFailedMap) {
		StringBuffer content = new StringBuffer();

		ArrayList<String> elemsSpl = splKeys;
		ArrayList<Double> freqsSpl = new ArrayList<Double>();

		for (int i = 0; i < elemsSpl.size(); ++i) {
			freqsSpl.add(1.0);
		}

		for (String studentKey : studentKeys) {
			System.out.println("Initiating line " + studentKey + "...");

			String splKey = RandomGen.getFromPSet(elemsSpl, freqsSpl);
			String cgpa = "";

			int courseRegistered = 0, creditRegistered = 0, courseFailed = 0;

			if (cgpaAndCountAndFailedMap.containsKey(studentKey)) { // For
																	// debugging
																	// with
																	// incomplete
																	// map input

				cgpa = cgpaAndCountAndFailedMap.get(studentKey).split("_")[0];
				courseRegistered = Integer.parseInt(cgpaAndCountAndFailedMap.get(studentKey).split("_")[1]);
				creditRegistered = (int) (((double) RandomGen.getIntInRange(30, 40)) / 10) * courseRegistered;
				courseFailed = Integer.parseInt(cgpaAndCountAndFailedMap.get(studentKey).split("_")[2]);

			}
			// if (Double.parseDouble(cgpa) < 6.8) {
			// courseFailed = RandomGen.getIntInRange(0, 1);
			// if (Double.parseDouble(cgpa) < 6.0) {
			// courseFailed = RandomGen.getIntInRange(1, 2);
			// }
			// }

			content.append(splKey + ",");
			content.append(studentKey + ",");
			content.append(cgpa + ",");
			content.append(courseRegistered + ",");
			content.append(creditRegistered + ",");
			content.append(courseFailed + "\n");
		}

		return content.toString();
	}

	public static void main(String[] args) throws Exception {
		try {
			String filePath = ProjectInfo.getPathToStore() + "fact_sem_performance_"
					+ ProjectInfo.getSizeFactSemPerformance() + ".csv";

			String filePathSpl = ProjectInfo.getPathToStore() + "dim_specialisations_"
					+ ProjectInfo.getSizeDimSpecialisations() + ".csv";
			String filePathStudent = ProjectInfo.getPathToStore() + "dim_students_" + ProjectInfo.getSizeDimStudents()
					+ ".csv";
			String filePathStuLearning = ProjectInfo.getPathToStore() + "fact_stu_learning_"
					+ ProjectInfo.getSizeFactStuLearning() + "_max.csv";

			System.out.println("Reading " + filePathSpl + "...");
			ArrayList<String> splKeys = new DataReader().getFKeys(filePathSpl);
			System.out.println(filePathSpl + " read");

			System.out.println("Reading " + filePathStudent + "...");
			ArrayList<String> studentKeys = new DataReader().getFKeys(filePathStudent);
			System.out.println(filePathStudent + " read");

			System.out.println("Reading " + filePathStudent + "...");
			Map<String, String> cgpaAndCountAndFailedMap = new DataReader()
					.getCgpaAndCountAndFailedMap(filePathStuLearning);
			System.out.println(filePathStudent + " read");

			System.out.println("Generating content...");
			String content = new SemPerformanceGen().generate(splKeys, studentKeys, cgpaAndCountAndFailedMap);
			System.out.println("Content generated");

			System.out.println("Writing to file...");
			DataWriter.writeToFile(filePath, content);
			System.out.println("File path: " + filePath);
		} catch (Exception e) {
			System.out.println("Aborted!");
			throw (e);
		}
	}

}
