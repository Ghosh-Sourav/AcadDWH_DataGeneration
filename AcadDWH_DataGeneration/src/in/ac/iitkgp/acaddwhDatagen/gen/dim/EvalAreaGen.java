package in.ac.iitkgp.acaddwhDatagen.gen.dim;

import java.text.DecimalFormat;

import in.ac.iitkgp.acaddwhDatagen.config.ProjectInfo;
import in.ac.iitkgp.acaddwhDatagen.util.DataWriter;
import in.ac.iitkgp.acaddwhDatagen.util.RandomGen;

public class EvalAreaGen {

	public String generate() {
		StringBuffer content = new StringBuffer();

		for (int i = 0; i < 32; ++i) {
			System.out.println("Initiating line " + i + "...");

			String evalAreaCode = new DecimalFormat("EA00").format(i + 1);

			String evalArea = RandomGen.getString(RandomGen.getIntInRange(8, 18));
			evalArea = evalArea.charAt(0) + evalArea.substring(1).toLowerCase();

			content.append(evalAreaCode + ",");
			content.append(evalArea + "\n");
		}

		return content.toString();
	}

	public static void main(String[] args) {
		try {
			String filePath = ProjectInfo.getPathToStore() + "dim_EvalArea_32" + ".csv";

			System.out.println("Generating content...");
			String content = new EvalAreaGen().generate();
			System.out.println("Content generated");

			System.out.println("Writing to file...");
			DataWriter.writeToFile(filePath, content);
			System.out.println("File path: " + filePath);
		} catch (Exception e) {
			System.out.println("Aborted!");
		}
	}

}
