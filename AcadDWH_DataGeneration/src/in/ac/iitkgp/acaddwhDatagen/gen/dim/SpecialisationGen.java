package in.ac.iitkgp.acaddwhDatagen.gen.dim;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import in.ac.iitkgp.acaddwhDatagen.config.ProjectInfo;
import in.ac.iitkgp.acaddwhDatagen.read.DataReader;
import in.ac.iitkgp.acaddwhDatagen.util.DataWriter;
import in.ac.iitkgp.acaddwhDatagen.util.RandomGen;

public class SpecialisationGen {

	public String generate(ArrayList<String> deptKeys) {
		StringBuffer content = new StringBuffer();
		Set<String> keys = new HashSet<String>();
		Set<String> uniqueVals = new HashSet<String>();
		String key = null, uniqueVal = null;

		ArrayList<String> elemsDK = deptKeys;
		ArrayList<Double> freqsDK = new ArrayList<Double>();

		for (int i = 0; i < elemsDK.size(); ++i) {
			freqsDK.add(1.0);
		}

		ArrayList<String> elemsSDL = new ArrayList<String>();
		ArrayList<Double> freqsSDL = new ArrayList<Double>();
		elemsSDL.add("B.Tech,3");
		freqsSDL.add(10.0);
		elemsSDL.add("B.S.,5");
		freqsSDL.add(1.0);
		elemsSDL.add("M.Tech,5");
		freqsSDL.add(8.0);
		elemsSDL.add("B.Tech + M.Tech,5");
		freqsSDL.add(2.0);
		elemsSDL.add("M.S.,5");
		freqsSDL.add(3.0);
		elemsSDL.add("Ph.D.,6");
		freqsSDL.add(6.0);
		elemsSDL.add("M.Tech + Ph.D.,6");
		freqsSDL.add(3.0);

		for (int i = 0; i < ProjectInfo.getSizeDimSpecialisations(); ++i) {
			System.out.println("Initiating line " + (i + 1) + "...");

			String splName = RandomGen.getString(RandomGen.getIntInRange(10, 15));
			splName = splName.charAt(0) + splName.substring(1).toLowerCase();

			String deptKey = null;
			String splDegree_AND_Level = null;
			do {
				deptKey = RandomGen.getFromPSet(elemsDK, freqsDK);
				splDegree_AND_Level = RandomGen.getFromPSet(elemsSDL, freqsSDL);
				uniqueVal = deptKey + "_" + splDegree_AND_Level;
			} while (uniqueVals.contains(uniqueVal));
			uniqueVals.add(uniqueVal);

			while (key == null || keys.contains(key)) {
				key = deptKey + RandomGen.getIntInRange(40, 70);
			}
			keys.add(key);

			String splCode = key;

			content.append(splCode + ",");
			content.append(splName + ",");
			content.append(deptKey + ",");
			content.append(splDegree_AND_Level + "\n");

		}

		return content.toString();
	}

	public static void main(String[] args) throws Exception {
		try {
			String filePath = ProjectInfo.getPathToStore() + "dim_specialisations_" +ProjectInfo.getSizeDimSpecialisations()+ ".csv";

			String filePathDept = ProjectInfo.getPathToStore() + "dim_departments_"+ProjectInfo.getSizeDimDepartments() + ".csv";

			System.out.println("Reading " + filePathDept + "...");
			ArrayList<String> deptKeys = new DataReader().getFKeys(filePathDept);
			System.out.println(filePathDept + " read");

			System.out.println("Generating content...");
			String content = new SpecialisationGen().generate(deptKeys);
			System.out.println("Content generated");

			System.out.println("Writing to file...");
			DataWriter.writeToFile(filePath, content);
			System.out.println("File path: " + filePath);
		} catch (Exception e) {
			System.out.println("Aborted!"); throw(e);
		}
	}

}
