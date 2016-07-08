package in.ac.iitkgp.acaddwhDatagen.gen.fact;

import java.util.ArrayList;
import java.util.List;

import in.ac.iitkgp.acaddwhDatagen.config.ProjectInfo;
import in.ac.iitkgp.acaddwhDatagen.read.DataReader;
import in.ac.iitkgp.acaddwhDatagen.util.DataWriter;
import in.ac.iitkgp.acaddwhDatagen.util.RandomGen;

public class TeachingQualityGen {

	public void generateAndWrite(ArrayList<String> courseKeysWithDept, ArrayList<String> teacherKeysWithDept,
			ArrayList<String> timeKeys, ArrayList<String> evalAreaKeys, String filePath) {
		StringBuffer content = new StringBuffer();

		ArrayList<String> elemsTeacher = null;
		ArrayList<Double> freqsTeacher = null;

		ArrayList<String> elemsTime = timeKeys;
		ArrayList<Double> freqsTime = new ArrayList<Double>();

		for (int i = 0; i < elemsTime.size(); ++i) {
			freqsTime.add(1.0);
		}

		for (String courseKeyWithDept : courseKeysWithDept) {
			String courseKey = courseKeyWithDept.split(",")[0];
			String deptKey = courseKeyWithDept.split(",")[1];

			elemsTeacher = new ArrayList<String>();
			freqsTeacher = new ArrayList<Double>();

			for (String teacherKeyWithDept : teacherKeysWithDept) {
				if (deptKey.equals(teacherKeyWithDept.split(",")[1])) {
					elemsTeacher.add(teacherKeyWithDept.split(",")[0]);
				}
			}

			for (int i = 0; i < elemsTeacher.size(); ++i) {
				freqsTeacher.add(1.0);
			}

			String[] elemsNoOfTeacher = { "1", "2" };
			Double[] freqsNoOfTeacher = { 78.5, 21.5 };

			int noOfTeachers = Integer.parseInt(RandomGen.getFromPSet(elemsNoOfTeacher, freqsNoOfTeacher));

			List<String> courseTeacherKeys = new ArrayList<String>();
			courseTeacherKeys.add(RandomGen.getFromPSet(elemsTeacher, freqsTeacher));

			if (noOfTeachers == 2) {
				String secondTeacherKey = null;
				do {
					secondTeacherKey = RandomGen.getFromPSet(elemsTeacher, freqsTeacher);
				} while (courseTeacherKeys.contains(secondTeacherKey));

				courseTeacherKeys.add(secondTeacherKey);
			}

			int noOfTimes = RandomGen.getIntInRange((int) Math.ceil(0.05 * ProjectInfo.getSizeDimTimes()),
					(int) Math.ceil(0.30 * ProjectInfo.getSizeDimTimes()));

			List<String> courseTimeKeys = new ArrayList<String>();

			do {
				String nextTimeKey = null;
				do {
					System.out.println("noOfTimes = " + noOfTimes);
					System.out.println(elemsTime);
					nextTimeKey = RandomGen.getFromPSet(elemsTime, freqsTime);
				} while (courseTimeKeys.contains(nextTimeKey));

				courseTimeKeys.add(nextTimeKey);
				--noOfTimes;
			} while (noOfTimes > 0);

			for (String timeKey : courseTimeKeys) {
				for (String courseTeacherKey : courseTeacherKeys) {

					int noOfEvaluation = RandomGen.getIntInRange(32, 148);

					for (String evalAreaKey : evalAreaKeys) {

						System.out.println("Initiating line " + courseKey + "-" + timeKey + "-" + courseTeacherKey + "-"
								+ evalAreaKey + "...");

						// CourseKey,TimeKey,TeacherKey,EvalAreaKey,NoOfEvaluation,AvgTeachingQuality

						double avgTeachingQuality = Double.parseDouble(RandomGen.getAverageTeachingQuality());

						content.append(courseKey + ",");
						content.append(timeKey + ",");
						content.append(courseTeacherKey + ",");
						content.append(evalAreaKey + ",");
						content.append(noOfEvaluation + ",");
						content.append(avgTeachingQuality + "\n");

						if (content.length() > 100000) {
							DataWriter.appendToFile(filePath, content.toString());
							content.delete(0, content.length());
						}
					}
				}
			}
		}

		DataWriter.appendToFile(filePath, content.toString());
		content.delete(0, content.length());

	}

	public static void main(String[] args) throws Exception {
		try {
			String filePath = ProjectInfo.getPathToStore() + "fact_teaching_quality_"
					+ ProjectInfo.getSizeFactTeachingQuality() + "_max" + ".csv";

			String filePathCourse = ProjectInfo.getPathToStore() + "dim_courses_" + ProjectInfo.getSizeDimCourses()
					+ ".csv";
			String filePathTeacher = ProjectInfo.getPathToStore() + "dim_teachers_" + ProjectInfo.getSizeDimTeachers()
					+ ".csv";
			String filePathTime = ProjectInfo.getPathToStore() + "dim_times_" + ProjectInfo.getSizeDimTimes() + ".csv";
			String filePathEvalArea = ProjectInfo.getPathToStore() + "dim_eval_areas_"
					+ ProjectInfo.getSizeDimEvalAreas() + ".csv";

			System.out.println("Reading " + filePathCourse + "...");
			ArrayList<String> courseKeysWithDept = new DataReader().getCourseKeysWithDept(filePathCourse);
			System.out.println(filePathCourse + " read");

			System.out.println("Reading " + filePathTeacher + "...");
			ArrayList<String> teacherKeysWithDept = new DataReader().getTeacherKeysWithDept(filePathTeacher);
			System.out.println(filePathTeacher + " read");

			System.out.println("Reading " + filePathTime + "...");
			ArrayList<String> timeKeys = new DataReader().getFKeys(filePathTime);
			System.out.println(filePathTime + " read");

			System.out.println("Reading " + filePathEvalArea + "...");
			ArrayList<String> evalAreaKeys = new DataReader().getFKeys(filePathEvalArea);
			System.out.println(filePathEvalArea + " read");

			System.out.println("Initialising file...");
			DataWriter.writeToFile(filePath, "");

			System.out.println("Generating content and writing...");
			new TeachingQualityGen().generateAndWrite(courseKeysWithDept, teacherKeysWithDept, timeKeys, evalAreaKeys,
					filePath);
			System.out.println("Content generated and written");

			System.out.println("File path: " + filePath);
		} catch (Exception e) {
			System.out.println("Aborted!");
			throw (e);
		}
	}

}
