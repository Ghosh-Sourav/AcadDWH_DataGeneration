package in.ac.iitkgp.acaddwhDatagen.gen.dim;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import in.ac.iitkgp.acaddwhDatagen.config.ProjectInfo;
import in.ac.iitkgp.acaddwhDatagen.read.DataReader;
import in.ac.iitkgp.acaddwhDatagen.util.DataWriter;
import in.ac.iitkgp.acaddwhDatagen.util.RandomGen;

public class CourseGen {

	public String generate(ArrayList<String> deptKeys) {
		StringBuffer content = new StringBuffer();
		Set<String> keys = new HashSet<String>();
		String key = null;

		ArrayList<String> elemsDK = deptKeys;
		ArrayList<Double> freqsDK = new ArrayList<Double>();

		for (int i = 0; i < elemsDK.size(); ++i) {
			freqsDK.add(1.0);
		}

		String[] elemsCourseTypes = { "Core", "Elective" };
		Double[] freqsCourseTypes = { 20.0, 80.0 };

		String[] elemsCourseTPO = { "Theory", "Practical", "Others" };
		Double[] freqsCourseTPO = { 62.0, 42.0, 6.0 };

		String[] elemsCourseLevel = { "UG", "PG" };
		Double[] freqsCourseLevel = { 70.0, 30.0 };

		for (int i = 0; i < ProjectInfo.getSizeDimCourses(); ++i) {
			System.out.println("Initiating line " + (i + 1) + "...");

			String courseName = RandomGen.getString(RandomGen.getIntInRange(14, 20));
			courseName = courseName.charAt(0) + courseName.substring(1).toLowerCase();

			String courseType = RandomGen.getFromPSet(elemsCourseTypes, freqsCourseTypes);
			String courseDept = RandomGen.getFromPSet(elemsDK, freqsDK);

			int courseCrd = 0, courseLectureHour = 0, courseTutorialHour = 0, coursePracticalHour = 0;
			String courseTPO = RandomGen.getFromPSet(elemsCourseTPO, freqsCourseTPO);

			switch (courseTPO) {
			case "Theory": {
				courseLectureHour = RandomGen.getIntInRange(2, 3);
				courseTutorialHour = RandomGen.getIntInRange(0, 1);
				coursePracticalHour = 0;
				courseCrd = courseLectureHour + courseTutorialHour + coursePracticalHour;
				break;
			}
			case "Practical": {
				courseLectureHour = 0;
				courseTutorialHour = 0;
				coursePracticalHour = 6;
				courseCrd = 4;
				break;
			}
			case "Others": {
				courseLectureHour = 0;
				courseTutorialHour = 0;
				coursePracticalHour = 3;
				courseCrd = 2;
				break;
			}
			}

			String courseLevel = RandomGen.getFromPSet(elemsCourseLevel, freqsCourseLevel);

			while (key == null || keys.contains(key)) {
				key = courseDept;

				if ("UG".equals(courseLevel)) {
					key = key + RandomGen.getIntInRange(1, 5);
				} else if ("PG".equals(courseLevel)) {
					key = key + RandomGen.getIntInRange(6, 8);
				}

				if ("Theory".equals(courseTPO)) {
					key = key + 0;
				} else if ("Practical".equals(courseTPO)) {
					key = key + 9;
				} else if ("Others".equals(courseTPO)) {
					key = key + 8;
				}

				key = key + RandomGen.getNumber(3);
			}
			keys.add(key);
			String courseCode = key;

			content.append(courseCode + ",");
			content.append(courseName + ",");
			content.append(courseType + ",");
			content.append(courseDept + ",");
			content.append(courseCrd + ",");
			content.append(courseLectureHour + ",");
			content.append(courseTutorialHour + ",");
			content.append(coursePracticalHour + ",");
			content.append(courseLevel + "\n");

		}

		return content.toString();
	}

	public static void main(String[] args) throws Exception {
		try {
			String filePath = ProjectInfo.getPathToStore() + "dim_courses_" + ProjectInfo.getSizeDimCourses() + ".csv";

			String filePathDept = ProjectInfo.getPathToStore() + "dim_departments_"
					+ ProjectInfo.getSizeDimDepartments() + ".csv";

			System.out.println("Reading " + filePathDept + "...");
			ArrayList<String> deptKeys = new DataReader().getFKeys(filePathDept);
			System.out.println(filePathDept + " read");

			System.out.println("Generating content...");
			String content = new CourseGen().generate(deptKeys);
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
