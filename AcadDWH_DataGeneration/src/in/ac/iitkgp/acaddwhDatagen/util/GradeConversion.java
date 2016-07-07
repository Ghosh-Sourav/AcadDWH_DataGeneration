package in.ac.iitkgp.acaddwhDatagen.util;

public class GradeConversion {

	public static String getGrade(String numGrade) {
		String grade = null;

		switch (numGrade) {
		case "10":
			grade = "EX";
			break;
		case "9":
			grade = "A";
			break;
		case "8":
			grade = "B";
			break;
		case "7":
			grade = "C";
			break;
		case "6":
			grade = "D";
			break;
		case "5":
			grade = "P";
			break;
		default:
			grade = "F";
			break;
		}

		return grade;

	}

}
