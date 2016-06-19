package in.ac.iitkgp.acaddwhDatagen.gen.dim;

import java.util.HashSet;
import java.util.Set;

import in.ac.iitkgp.acaddwhDatagen.config.ProjectInfo;
import in.ac.iitkgp.acaddwhDatagen.util.DataWriter;
import in.ac.iitkgp.acaddwhDatagen.util.RandomGen;

public class DepartmentGen {

	public String generate() {
		StringBuffer content = new StringBuffer();
		Set<String> keys = new HashSet<String>();
		String key = null;

		for (int i = 0; i < 36; ++i) {
			System.out.println("Initiating line " + i + "...");
			while (key == null || keys.contains(key)) {
				key = RandomGen.getString(2);
			}
			keys.add(key);

			String deptCode = key;
			String deptName = RandomGen.getString(RandomGen.getIntInRange(10, 15));
			String deptDcsType = RandomGen.getString(5);

			content.append(deptCode + ",");
			content.append(deptName + ",");
			content.append(deptDcsType + "\n");

		}
		
		return content.toString();
	}

	public static void main(String[] args) {
		String filePath = ProjectInfo.getPathToStore() + "Department_36" + ".csv";

		System.out.println("Generating content...");
		String content = new DepartmentGen().generate();
		System.out.println("Content generated");

		System.out.println("Writing to file...");
		DataWriter.writeToFile(filePath, content);
		System.out.println("File path: " + filePath);
	}

}
