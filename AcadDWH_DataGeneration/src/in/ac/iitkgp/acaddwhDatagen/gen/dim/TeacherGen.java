package in.ac.iitkgp.acaddwhDatagen.gen.dim;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import in.ac.iitkgp.acaddwhDatagen.config.ProjectInfo;
import in.ac.iitkgp.acaddwhDatagen.read.DataReader;
import in.ac.iitkgp.acaddwhDatagen.util.DataWriter;
import in.ac.iitkgp.acaddwhDatagen.util.RandomGen;

public class TeacherGen {

	public String generate(ArrayList<String> deptKeys) {
		StringBuffer content = new StringBuffer();
		Set<String> uniqueVals = new HashSet<String>();
		String uniqueVal = null;

		ArrayList<String> elemsDK = deptKeys;
		ArrayList<Double> freqsDK = new ArrayList<Double>();

		for (int i = 0; i < elemsDK.size(); ++i) {
			freqsDK.add(1.0);
		}

		String[] elemsDesignation = { "Associate Professor", "Professor", "Visiting Professor" };
		Double[] freqsDesignation = { 65.0, 30.0, 5.0 };

		for (int i = 0; i < 3000; ++i) {
			System.out.println("Initiating line " + (i + 1) + "...");

			String teacherCode = null;
			String deptKey = null;
			do {
				deptKey = RandomGen.getFromPSet(elemsDK, freqsDK);
				teacherCode = deptKey + RandomGen.getNumber(3);
				uniqueVal = teacherCode;
			} while (uniqueVals.contains(uniqueVal));
			uniqueVals.add(uniqueVal);

			String teacherDesg = RandomGen.getFromPSet(elemsDesignation, freqsDesignation);

			content.append(teacherCode + ",");
			content.append(deptKey + ",");
			content.append(teacherDesg + "\n");

		}

		return content.toString();
	}

	public static void main(String[] args) {
		try {
			String filePath = ProjectInfo.getPathToStore() + "dim_Teacher_3000" + ".csv";

			String filePathDept = ProjectInfo.getPathToStore() + "dim_Department_56" + ".csv";

			System.out.println("Reading " + filePathDept + "...");
			ArrayList<String> deptKeys = new DataReader().getFKeys(filePathDept);
			System.out.println(filePathDept + " read");

			System.out.println("Generating content...");
			String content = new TeacherGen().generate(deptKeys);
			System.out.println("Content generated");

			System.out.println("Writing to file...");
			DataWriter.writeToFile(filePath, content);
			System.out.println("File path: " + filePath);
		} catch (Exception e) {
			System.out.println("Aborted!");
		}
	}

}
