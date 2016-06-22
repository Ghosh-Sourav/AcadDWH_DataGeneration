package in.ac.iitkgp.acaddwhDatagen.gen.dim;

import java.util.ArrayList;
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

		ArrayList<String> elems = new ArrayList<String>();
		ArrayList<Double> freqs = new ArrayList<Double>();
		elems.add("Department");
		freqs.add(3.0);
		elems.add("School");
		freqs.add(2.0);
		elems.add("Center");
		freqs.add(1.0);

		for (int i = 0; i < ProjectInfo.getSizeDimDepartments(); ++i) {
			System.out.println("Initiating line " + (i + 1) + "...");
			while (key == null || keys.contains(key)) {
				key = RandomGen.getString(2);
			}
			keys.add(key);

			String deptCode = key;
			String deptName = RandomGen.getString(RandomGen.getIntInRange(10, 15));
			deptName = deptName.charAt(0) + deptName.substring(1).toLowerCase();

			String deptDcsType = RandomGen.getFromPSet(elems, freqs);

			content.append(deptCode + ",");
			content.append(deptName + ",");
			content.append(deptDcsType + "\n");

		}

		return content.toString();
	}

	public static void main(String[] args) {
		try {
			String filePath = ProjectInfo.getPathToStore() + "dim_departments_" + ProjectInfo.getSizeDimDepartments()
					+ ".csv";

			System.out.println("Generating content...");
			String content = new DepartmentGen().generate();
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
