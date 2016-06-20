package in.ac.iitkgp.acaddwhDatagen.gen.dim;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import in.ac.iitkgp.acaddwhDatagen.config.ProjectInfo;
import in.ac.iitkgp.acaddwhDatagen.read.DataReader;
import in.ac.iitkgp.acaddwhDatagen.util.DataWriter;
import in.ac.iitkgp.acaddwhDatagen.util.RandomGen;

public class StudentGen {

	public String generate(ArrayList<String> splKeys) {
		StringBuffer content = new StringBuffer();
		Set<String> uniqueVals = new HashSet<String>();
		String uniqueVal = null;

		ArrayList<String> elemsSplK = splKeys;
		ArrayList<Double> freqsSplK = new ArrayList<Double>();

		for (int i = 0; i < elemsSplK.size(); ++i) {
			freqsSplK.add(1.0);
		}

		String[] elemsCategory = { "R", "D", "S" };
		Double[] freqsCategory = { 80.0, 10.0, 10.0 };

		ArrayList<String> elemsGender = new ArrayList<String>();
		ArrayList<Double> freqsGender = new ArrayList<Double>();
		elemsGender.add("M");
		freqsGender.add(60.0);
		elemsGender.add("F");
		freqsGender.add(40.0);

		for (int i = 0; i < 40000; ++i) {
			System.out.println("Initiating line " + (i + 1) + "...");

			String studentCode = null;
			String splKey = null;
			String admissionYear = null;
			do {
				splKey = RandomGen.getFromPSet(elemsSplK, freqsSplK);
				admissionYear = "" + RandomGen.getIntInRange(2010, 2016);
				studentCode = admissionYear.substring(2, 4) + splKey
						+ RandomGen.getFromPSet(elemsCategory, freqsCategory) + RandomGen.getNumber(2);
				uniqueVal = studentCode;
			} while (uniqueVals.contains(uniqueVal));
			uniqueVals.add(uniqueVal);

			String studentNoa = RandomGen.getString(RandomGen.getIntInRange(10, 15));
			studentNoa = studentNoa.charAt(0) + studentNoa.substring(1).toLowerCase();

			String studentGender = RandomGen.getFromPSet(elemsGender, freqsGender);

			content.append(studentCode + ",");
			content.append(splKey + ",");
			content.append(studentNoa + ",");
			content.append(studentGender + ",");
			content.append(admissionYear + "\n");

		}

		return content.toString();
	}

	public static void main(String[] args) {
		try {
			String filePath = ProjectInfo.getPathToStore() + "dim_Student_40000" + ".csv";

			String filePathSpl = ProjectInfo.getPathToStore() + "dim_Specialisation_224" + ".csv";

			System.out.println("Reading " + filePathSpl + "...");
			ArrayList<String> splKeys = new DataReader().getFKeys(filePathSpl);
			System.out.println(filePathSpl + " read");

			System.out.println("Generating content...");
			String content = new StudentGen().generate(splKeys);
			System.out.println("Content generated");

			System.out.println("Writing to file...");
			DataWriter.writeToFile(filePath, content);
			System.out.println("File path: " + filePath);
		} catch (Exception e) {
			System.out.println("Aborted!");
		}
	}

}
