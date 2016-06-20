package in.ac.iitkgp.acaddwhDatagen.gen.dim;

import java.util.ArrayList;
import java.util.List;

import in.ac.iitkgp.acaddwhDatagen.config.ProjectInfo;
import in.ac.iitkgp.acaddwhDatagen.util.DataWriter;

public class TimeGen {

	public String generate() {
		StringBuffer content = new StringBuffer();

		List<String> acadsemesters = new ArrayList<String>();
		List<String> acadsessions = new ArrayList<String>();

		acadsemesters.add("Autumn");
		acadsemesters.add("Spring");

		for (int i = 2010; i < 2021; ++i) {
			acadsessions.add(i + "-" + (i + 1));
		}

		for (String acadsession : acadsessions) {
			for (String acadsemester : acadsemesters) {
				System.out.println("Initiating line " + acadsession + "_" + acadsemester + "...");

				String timeCode = acadsession.substring(2, 4) + acadsession.substring(7, 9)
						+ acadsemester.substring(0, 3).toUpperCase();

				content.append(timeCode + ",");
				content.append(acadsemester + ",");
				content.append(acadsession + "\n");
			}
		}

		return content.toString();
	}

	public static void main(String[] args) {
		try {
			String filePath = ProjectInfo.getPathToStore() + "dim_Time_20" + ".csv";

			System.out.println("Generating content...");
			String content = new TimeGen().generate();
			System.out.println("Content generated");

			System.out.println("Writing to file...");
			DataWriter.writeToFile(filePath, content);
			System.out.println("File path: " + filePath);
		} catch (Exception e) {
			System.out.println("Aborted!");
		}
	}

}
