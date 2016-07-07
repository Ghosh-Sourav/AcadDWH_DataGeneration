package in.ac.iitkgp.acaddwhDatagen;

import in.ac.iitkgp.acaddwhDatagen.config.ProjectInfo;
import in.ac.iitkgp.acaddwhDatagen.gen.dim.CourseGen;
import in.ac.iitkgp.acaddwhDatagen.gen.dim.DepartmentGen;
import in.ac.iitkgp.acaddwhDatagen.gen.dim.EvalAreaGen;
import in.ac.iitkgp.acaddwhDatagen.gen.dim.RegtypeGen;
import in.ac.iitkgp.acaddwhDatagen.gen.dim.SpecialisationGen;
import in.ac.iitkgp.acaddwhDatagen.gen.dim.StudentGen;
import in.ac.iitkgp.acaddwhDatagen.gen.dim.TeacherGen;
import in.ac.iitkgp.acaddwhDatagen.gen.dim.TimeGen;
import in.ac.iitkgp.acaddwhDatagen.gen.fact.SemPerformanceGen;
import in.ac.iitkgp.acaddwhDatagen.gen.fact.SplPerformanceGen;
import in.ac.iitkgp.acaddwhDatagen.gen.fact.StuLearningGen;
import in.ac.iitkgp.acaddwhDatagen.gen.fact.TeachingQualityGen;
import in.ac.iitkgp.acaddwhDatagen.util.DataWriter;

public class GenerationDriver {

	public static void main(String[] args) {
		String filePath = ProjectInfo.getPathToStore() + "LOG_phaseStats.log";
		try {
			DataWriter.writeToFile(filePath, "");

			DataWriter.appendToFile(filePath, "*** Phase 1 ***");
			DepartmentGen.main(args);
			DataWriter.appendToFile(filePath, "*** Phase 2 ***");
			SpecialisationGen.main(args);
			DataWriter.appendToFile(filePath, "*** Phase 3 ***");
			StudentGen.main(args);
			DataWriter.appendToFile(filePath, "*** Phase 4 ***");
			TeacherGen.main(args);
			DataWriter.appendToFile(filePath, "*** Phase 5 ***");
			CourseGen.main(args);
			DataWriter.appendToFile(filePath, "*** Phase 6 ***");
			EvalAreaGen.main(args);
			DataWriter.appendToFile(filePath, "*** Phase 7 ***");
			RegtypeGen.main(args);
			DataWriter.appendToFile(filePath, "*** Phase 8 ***");
			TimeGen.main(args);

			// StudentCharacteristicsGen.main(args);
			DataWriter.appendToFile(filePath, "*** Phase 9 ***");
			StuLearningGen.main(args);
			DataWriter.appendToFile(filePath, "*** Phase 10 ***");
			SemPerformanceGen.main(args);
			DataWriter.appendToFile(filePath, "*** Phase 11 ***");
			SplPerformanceGen.main(args);
			DataWriter.appendToFile(filePath, "*** Phase 12 ***");
			TeachingQualityGen.main(args);

			DataWriter.appendToFile(filePath, "*** All Phases Successfully Completed! ***");

		} catch (Exception e) {
			DataWriter.appendToFile(filePath, "Driver stopped due to the following exception:\n" + e.getMessage());
			System.out.println("Driver stopped due to the following exception:");
			e.printStackTrace();
		}
	}

}
