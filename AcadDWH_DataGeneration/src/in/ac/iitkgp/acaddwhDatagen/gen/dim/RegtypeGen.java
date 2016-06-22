package in.ac.iitkgp.acaddwhDatagen.gen.dim;

import in.ac.iitkgp.acaddwhDatagen.config.ProjectInfo;
import in.ac.iitkgp.acaddwhDatagen.util.DataWriter;

public class RegtypeGen {

	public String generate() {
		StringBuffer content = new StringBuffer();

		String[] regtypes = { "Normal", "Audit", "Crash" };

		for (String regtype : regtypes) {
			System.out.println("Initiating line " + regtype + "...");

			String regtypeCode = regtype.substring(0, 4).toUpperCase();

			String regtypeDesc = regtype;

			content.append(regtypeCode + ",");
			content.append(regtypeDesc + "\n");
		}

		// content.deleteCharAt(content.length()-1);
		return content.toString();
	}

	public static void main(String[] args) {
		try {
			String filePath = ProjectInfo.getPathToStore() + "dim_regtypes_" + ProjectInfo.getSizeDimRegtypes()
					+ ".csv";

			System.out.println("Generating content...");
			String content = new RegtypeGen().generate();
			System.out.println("Content generated");

			System.out.println("Writing to file...");
			DataWriter.writeToFile(filePath, content);
			System.out.println("File path: " + filePath);
		} catch (Exception e) {
			System.out.println("Aborted!"); throw(e);
		}
	}

}
