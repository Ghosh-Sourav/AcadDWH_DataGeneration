package in.ac.iitkgp.acaddwhDatagen.gen.fact;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import in.ac.iitkgp.acaddwhDatagen.config.ProjectInfo;
import in.ac.iitkgp.acaddwhDatagen.read.DataReader;
import in.ac.iitkgp.acaddwhDatagen.util.DataWriter;
import in.ac.iitkgp.acaddwhDatagen.util.GradeConversion;
import in.ac.iitkgp.acaddwhDatagen.util.RandomGen;

public class StuLearningGen {

	public String generate(ArrayList<String> courseKeysWithDept, ArrayList<String> studentKeysWithAdmYr,
			ArrayList<String> timeKeysWithStartingYear, ArrayList<String> regtypeKeys) {
		StringBuffer content = new StringBuffer();

		int imprFactor = 0, impr1 = 0, impr2 = 0;

		ArrayList<String> elemsCourseKeysOfAllDept = new ArrayList<String>();
		ArrayList<Double> freqsCourseKeysOfAllDept = new ArrayList<Double>();

		for (String courseKeyWithDept : courseKeysWithDept) {
			String courseKey = courseKeyWithDept.split(",")[0];

			elemsCourseKeysOfAllDept.add(courseKey);
			freqsCourseKeysOfAllDept.add(1.0);
		}

		long progressLineCount = 0;
		for (String studentKeyWithAdmYr : studentKeysWithAdmYr) {
			String studentKey = studentKeyWithAdmYr.split(",")[0];
			String deptKeyOfStudent = studentKey.substring(2, 4);
			int admYr = Integer.parseInt(studentKeyWithAdmYr.split(",")[1]);

			if (progressLineCount == 100) {
				break;
			}

			System.out.println("Initiating line " + studentKey + " (" + (++progressLineCount) + "/"
					+ studentKeysWithAdmYr.size() + ") ...");

			String characteristic = RandomGen.getCharacteristic();

			String[] elemsNoOfYearsPossible = { "2", "4", "5" };
			Double[] freqsNoOfYearsPossible = { 35.0, 55.0, 10.0 };
			int noOfYearsSelected = Integer
					.parseInt(RandomGen.getFromPSet(elemsNoOfYearsPossible, freqsNoOfYearsPossible));

			List<String> timeKeysSelected = new ArrayList<String>();
			int startingTimeIndex = -1;

			for (int i = 0; i < timeKeysWithStartingYear.size(); ++i) {
				String timeKeyWithStartingYear = timeKeysWithStartingYear.get(i);
				System.out.println("timeKeyWithStartingYear=" + timeKeyWithStartingYear);
				String timeKey = timeKeyWithStartingYear.split(",")[0];
				int startingYear = Integer.parseInt(timeKeyWithStartingYear.split(",")[1]);
				if (timeKey.endsWith("AUT") && startingYear == admYr) {
					startingTimeIndex = i;
					break;
				}
			}

			if (startingTimeIndex != -1) {
				int timeIndex = startingTimeIndex;
				for (int i = 1; i <= 2 * noOfYearsSelected && timeIndex < timeKeysWithStartingYear.size(); ++i) {
					String timeKey = timeKeysWithStartingYear.get(timeIndex).split(",")[0];
					timeKeysSelected.add(timeKey);
					++timeIndex;
				}
			}

			for (int currentTimeKeyIndex = 0; currentTimeKeyIndex < timeKeysSelected.size(); ++currentTimeKeyIndex) {
				String timeKeySelected = timeKeysSelected.get(currentTimeKeyIndex);

				String[] elemsNoOfCoursesPossible = { "1", "3", "4", "5", "6", "7" };
				Double[] freqsNoOfCoursesPossible = { 0.0, 1.0, 2.0, 2.0, 2.0, 2.0 }; // Intuition:
																						// Twice
																						// the
																						// number
																						// of
																						// batches
																						// having
																						// n
																						// number
																						// of
																						// courses
																						// (0.0
																						// for
																						// "1"
																						// is
																						// handled
																						// later
																						// only
																						// for
																						// last
																						// yr.)
				int noOfCoursesSelected;
				if (currentTimeKeyIndex == timeKeysSelected.size() - 2
						|| currentTimeKeyIndex == timeKeysSelected.size() - 1) {
					noOfCoursesSelected = 1;
				} else {
					noOfCoursesSelected = Integer
							.parseInt(RandomGen.getFromPSet(elemsNoOfCoursesPossible, freqsNoOfCoursesPossible));
				}

				Set<String> courseKeysSelected = new HashSet<String>();

				/*
				 * COURSE SELECTION => Select n-1 courses from same dept., and 1
				 * course from same/different dept. with 33.33% probability in
				 * favour of different (n = noOfCoursesSelected)
				 */
				/* COURSE SELECTION: BEGIN */

				ArrayList<String> elemsCourseKeysOfSameDept = new ArrayList<String>();
				ArrayList<Double> freqsCourseKeysOfSameDept = new ArrayList<Double>();

				for (String courseKeyWithDept : courseKeysWithDept) {
					String courseKey = courseKeyWithDept.split(",")[0];
					String deptKey = courseKeyWithDept.split(",")[1];

					if (deptKey.equals(deptKeyOfStudent)) {
						elemsCourseKeysOfSameDept.add(courseKey);
						freqsCourseKeysOfSameDept.add(1.0);
					}
				}

				if (noOfCoursesSelected == 1) {
					String courseSelected = RandomGen.getFromPSet(elemsCourseKeysOfSameDept, freqsCourseKeysOfSameDept);
					courseKeysSelected.add(courseSelected);
				} else {
					while (noOfCoursesSelected > courseKeysSelected.size() + 1) { // While
																					// n-1
																					// courses
																					// are
																					// not
																					// selected
						String courseSelected = RandomGen.getFromPSet(elemsCourseKeysOfSameDept,
								freqsCourseKeysOfSameDept);
						courseKeysSelected.add(courseSelected);
					}
					while (noOfCoursesSelected != courseKeysSelected.size()) {// While
																				// nth
																				// course
																				// is
																				// not
																				// selected
						if (RandomGen.getIntInRange(0, 2) == 0) {
							String courseSelected = RandomGen.getFromPSet(elemsCourseKeysOfAllDept,
									freqsCourseKeysOfAllDept);
							courseKeysSelected.add(courseSelected);
						} else {
							String courseSelected = RandomGen.getFromPSet(elemsCourseKeysOfSameDept,
									freqsCourseKeysOfSameDept);
							courseKeysSelected.add(courseSelected);
						}
					}
				}

				/* COURSE SELECTION: END */

				for (String courseKeySelected : courseKeysSelected) {

					ArrayList<String> elemsRegtypeKeys = regtypeKeys;
					ArrayList<Double> freqsRegtypeKeys = new ArrayList<Double>();

					for (int i = 0; i < elemsRegtypeKeys.size(); ++i) {
						freqsRegtypeKeys.add(1.0);
					}

					String regtypeKey = RandomGen.getFromPSet(elemsRegtypeKeys, freqsRegtypeKeys);

					String numGrade = RandomGen.getNumGrade(characteristic);
					String grade = GradeConversion.getGrade(numGrade);
					String percentAttended = RandomGen.getPercentAttended();

					content.append(courseKeySelected + ",");
					content.append(timeKeySelected + ",");
					content.append(studentKey + ",");
					content.append(regtypeKey + ",");
					content.append(grade + ",");
					content.append(numGrade + ",");
					content.append(imprFactor + ",");
					content.append(impr1 + ",");
					content.append(impr2 + ",");
					content.append(percentAttended + "\n");
				}
			}

		}

		return content.toString();
	}

	public static void main(String[] args) throws Exception {
		try {
			String filePath = ProjectInfo.getPathToStore() + "fact_stu_learning_"
					+ ProjectInfo.getSizeFactTeachingQuality() + "_max" + ".csv";

			String filePathCourse = ProjectInfo.getPathToStore() + "dim_courses_" + ProjectInfo.getSizeDimCourses()
					+ ".csv";
			String filePathStudent = ProjectInfo.getPathToStore() + "dim_students_" + ProjectInfo.getSizeDimStudents()
					+ ".csv";
			String filePathTime = ProjectInfo.getPathToStore() + "dim_times_" + ProjectInfo.getSizeDimTimes() + ".csv";
			String filePathRegtype = ProjectInfo.getPathToStore() + "dim_regtypes_" + ProjectInfo.getSizeDimRegtypes()
					+ ".csv";

			System.out.println("Reading " + filePathCourse + "...");
			ArrayList<String> courseKeysWithDept = new DataReader().getCourseKeysWithDept(filePathCourse);
			System.out.println(filePathCourse + " read");

			System.out.println("Reading " + filePathStudent + "...");
			ArrayList<String> studentKeysWithAdmYr = new DataReader().getStudentKeysWithAdmYr(filePathStudent);
			System.out.println(filePathStudent + " read");

			System.out.println("Reading " + filePathTime + "...");
			ArrayList<String> timeKeysWithStartingYear = new DataReader().getTimeKeysWithStartingYear(filePathTime);
			System.out.println(filePathTime + " read");

			System.out.println("Reading " + filePathRegtype + "...");
			ArrayList<String> regtypeKeys = new DataReader().getFKeys(filePathRegtype);
			System.out.println(filePathRegtype + " read");

			System.out.println("Generating content...");
			String content = new StuLearningGen().generate(courseKeysWithDept, studentKeysWithAdmYr,
					timeKeysWithStartingYear, regtypeKeys);
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
