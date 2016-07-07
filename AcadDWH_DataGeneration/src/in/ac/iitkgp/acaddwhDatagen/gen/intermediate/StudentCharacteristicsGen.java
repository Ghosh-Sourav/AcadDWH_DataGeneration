package in.ac.iitkgp.acaddwhDatagen.gen.intermediate;

import java.util.ArrayList;

import in.ac.iitkgp.acaddwhDatagen.config.ProjectInfo;
import in.ac.iitkgp.acaddwhDatagen.read.DataReader;
import in.ac.iitkgp.acaddwhDatagen.util.DataWriter;
import in.ac.iitkgp.acaddwhDatagen.util.RandomGen;

public class StudentCharacteristicsGen {

	public String generate(ArrayList<String> studentKeys) {
		StringBuffer content = new StringBuffer();

		for (String studentKey : studentKeys) {
			System.out.println("Initiating line " + studentKey + "...");

			String characteristic = RandomGen.getCharacteristic();

			content.append(studentKey + ",");
			content.append(characteristic + "\n");
		}

		return content.toString();
	}

	public static void main(String[] args) throws Exception {
		try {
			String filePath = ProjectInfo.getPathToStore() + "intermediate_student_characteristics_"
					+ ProjectInfo.getSizeIntermediateStudentCharacteristics() + ".csv";

			String filePathStudent = ProjectInfo.getPathToStore() + "dim_students_" + ProjectInfo.getSizeDimStudents()
					+ ".csv";

			System.out.println("Reading " + filePathStudent + "...");
			ArrayList<String> studentKeys = new DataReader().getFKeys(filePathStudent);
			System.out.println(filePathStudent + " read");

			System.out.println("Generating content...");
			String content = new StudentCharacteristicsGen().generate(studentKeys);
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
