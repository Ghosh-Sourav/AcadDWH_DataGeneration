package in.ac.iitkgp.acaddwhDatagen.config;

public class ProjectInfo {
	private static String pathToStore = "G:/AcadDWH/Datagen/";

	private static long sizeDimDepartments = 68;
	private static long sizeDimSpecialisations = 400;
	private static long sizeDimStudents = 300000;
	private static long sizeDimTeachers = 20000;
	private static long sizeDimCourses = 10000;
	private static long sizeDimEvalAreas = 32;
	private static long sizeDimRegtypes = 3;
	private static int _firstOfStartingYear = 1966;
	private static int _firstOfEndingYear = 2015;
	private static long sizeDimTimes = 2 * (_firstOfEndingYear - _firstOfStartingYear);

	private static long sizeFactSemPerformance = 300000;
	private static long sizeFactSplPerformance = 20000;
	private static long sizeFactStuLearning = 0;
	private static long sizeFactTeachingQuality = 38880000;

	public static String getPathToStore() {
		return pathToStore;
	}

	public static long getSizeDimDepartments() {
		return sizeDimDepartments;
	}

	public static long getSizeDimSpecialisations() {
		return sizeDimSpecialisations;
	}

	public static long getSizeDimStudents() {
		return sizeDimStudents;
	}

	public static long getSizeDimTeachers() {
		return sizeDimTeachers;
	}

	public static long getSizeDimCourses() {
		return sizeDimCourses;
	}

	public static long getSizeDimEvalAreas() {
		return sizeDimEvalAreas;
	}

	public static long getSizeDimRegtypes() {
		return sizeDimRegtypes;
	}

	public static int get_firstOfStartingYear() {
		return _firstOfStartingYear;
	}

	public static int get_firstOfEndingYear() {
		return _firstOfEndingYear;
	}

	public static long getSizeDimTimes() {
		return sizeDimTimes;
	}

	public static long getSizeFactSemPerformance() {
		return sizeFactSemPerformance;
	}

	public static long getSizeFactSplPerformance() {
		return sizeFactSplPerformance;
	}

	public static long getSizeFactStuLearning() {
		return sizeFactStuLearning;
	}

	public static long getSizeFactTeachingQuality() {
		return sizeFactTeachingQuality;
	}
}