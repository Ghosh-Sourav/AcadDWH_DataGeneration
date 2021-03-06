package in.ac.iitkgp.acaddwhDatagen.config;

public class ProjectInfo {
	private static String pathToStoreWindows = "G:/AcadDWH/Datagen/";
	private static String pathToStoreLinux = "/home/mt1/15CS60R16/AcadDWH/Datagen/";

	// private static long sizeDimDepartments = 68;
	// private static long sizeDimSpecialisations = 400;
	// private static long sizeDimStudents = 300000;
	// private static long sizeDimTeachers = 20000;
	// private static long sizeDimCourses = 10000;
	// private static long sizeDimEvalAreas = 32;
	// private static long sizeDimRegtypes = 3;
	// private static int _firstOfStartingYear = 1966;
	// private static int _firstOfEndingYear = 2015;
	// private static long sizeDimTimes = 2 * (_firstOfEndingYear -
	// _firstOfStartingYear + 1);
	//
	// private static long sizeIntermediateStudentCharacteristics = 300000;
	//
	// private static long sizeFactStuLearning = 210000000;
	// private static long sizeFactSemPerformance = 300000;
	// private static long sizeFactSplPerformance = 20000;
	// private static long sizeFactTeachingQuality = 6804000;

	private static String instituteKey = "INSJ/";
	
	private static long sizeDimDepartments = 38;
	private static long sizeDimSpecialisations = 185;
	private static long sizeDimStudents = 395346;
	private static long sizeDimTeachers = 16532;
	private static long sizeDimCourses = 7953;
	private static long sizeDimEvalAreas = 17;
	private static long sizeDimRegtypes = 3;
	private static int _firstOfStartingYear = 1991;
	private static int _firstOfEndingYear = 2015;
	private static long sizeDimTimes = 2 * (_firstOfEndingYear - _firstOfStartingYear + 1);

	private static long sizeIntermediateStudentCharacteristics = 200000;

	private static long sizeFactStuLearning = 138371100;
	private static long sizeFactSemPerformance = 395346;
	private static long sizeFactSplPerformance = 4625;
	private static long sizeFactTeachingQuality = 1437356;

	public static String getPathToStore() {
		if (System.getProperty("os.name").contains("Windows")) {
			return pathToStoreWindows + instituteKey;
		} else {
			return pathToStoreLinux + instituteKey;
		}
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

	public static long getSizeIntermediateStudentCharacteristics() {
		return sizeIntermediateStudentCharacteristics;
	}

	public static long getSizeFactStuLearning() {
		return sizeFactStuLearning;
	}

	public static long getSizeFactSemPerformance() {
		return sizeFactSemPerformance;
	}

	public static long getSizeFactSplPerformance() {
		return sizeFactSplPerformance;
	}

	public static long getSizeFactTeachingQuality() {
		return sizeFactTeachingQuality;
	}

	public static void main(String[] args) {
		System.getProperties().list(System.out);
		System.out.println(getPathToStore());
	}

}