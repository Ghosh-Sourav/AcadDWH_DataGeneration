package in.ac.iitkgp.acaddwhDatagen.gen.fact;

import java.util.ArrayList;
import java.util.Collections;

import in.ac.iitkgp.acaddwhDatagen.config.ProjectInfo;
import in.ac.iitkgp.acaddwhDatagen.read.DataReader;
import in.ac.iitkgp.acaddwhDatagen.util.DataWriter;
import in.ac.iitkgp.acaddwhDatagen.util.RandomGen;

public class SplPerformanceGen {

	public String generate(ArrayList<String> splKeys, ArrayList<String> timeKeys, ArrayList<String> admissionYears) {
		StringBuffer content = new StringBuffer();

		ArrayList<String> suffixOfAdmissionYears = new ArrayList<String>();
		for (String admissionYear : admissionYears) {
			suffixOfAdmissionYears.add(admissionYear.substring(2, 4));
		}

		for (String timeKey : timeKeys) {
			if (timeKey.endsWith("SPR")) {
				for (String splKey : splKeys) {

					System.out.println("Initiating line " + splKey + "-" + timeKey + "...");

					long admStuCnt, onrollStuCnt, gradStuCnt, dropoutStuCnt, percentPlaced;
					double avgSalary;

					admStuCnt = (long) ((Collections.frequency(suffixOfAdmissionYears, timeKey.substring(0, 2)) / 56)
							* ((double) (RandomGen.getIntInRange(95, 105))
									/ 100)); /*
												 * Takes into account randomness
												 * in the distribution of
												 * admissions in a given year
												 * among specialisations
												 */

					onrollStuCnt = (long) (admStuCnt * 3 * (double) (RandomGen.getIntInRange(80, 120))
							/ 100); /*
									 * Estimates onrollStuCnt from thrice the
									 * admStuCnt in a given year
									 */

					double dropOutPercentage = (double) (RandomGen.getIntInRange(5, 35))
							/ 10; /* Generates a random dropdown percentage */

					long toGraduate = (long) (admStuCnt * (double) (RandomGen.getIntInRange(90, 110)) / 100);
					dropoutStuCnt = (long) (dropOutPercentage * toGraduate / 100);
					gradStuCnt = toGraduate - dropoutStuCnt;

					percentPlaced = RandomGen.getIntInRange(70, 100);
					avgSalary = RandomGen.getIntInRange(20, 50) * 1000;

					content.append(splKey + ",");
					content.append(timeKey + ",");
					content.append(admStuCnt + ",");
					content.append(onrollStuCnt + ",");
					content.append(gradStuCnt + ",");
					content.append(dropoutStuCnt + ",");
					content.append(percentPlaced + ",");
					content.append(avgSalary + "\n");
				}
			}
		}

		return content.toString();
	}

	public static void main(String[] args) throws Exception {
		try {
			String filePath = ProjectInfo.getPathToStore() + "fact_spl_performance_"
					+ ProjectInfo.getSizeFactSplPerformance() + ".csv";

			String filePathSpl = ProjectInfo.getPathToStore() + "dim_specialisations_"
					+ ProjectInfo.getSizeDimSpecialisations() + ".csv";
			String filePathTime = ProjectInfo.getPathToStore() + "dim_times_" + ProjectInfo.getSizeDimTimes() + ".csv";
			String filePathStudent = ProjectInfo.getPathToStore() + "dim_students_" + ProjectInfo.getSizeDimStudents()
					+ ".csv";

			System.out.println("Reading " + filePathSpl + "...");
			ArrayList<String> splKeys = new DataReader().getFKeys(filePathSpl);
			System.out.println(filePathSpl + " read");

			System.out.println("Reading " + filePathTime + "...");
			ArrayList<String> timeKeys = new DataReader().getFKeys(filePathTime);
			System.out.println(filePathTime + " read");

			System.out.println("Reading " + filePathStudent + "...");
			ArrayList<String> admissionYears = new DataReader().getAdmissionYears(filePathStudent);
			System.out.println(filePathStudent + " read");

			System.out.println("Generating content...");
			String content = new SplPerformanceGen().generate(splKeys, timeKeys, admissionYears);
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
