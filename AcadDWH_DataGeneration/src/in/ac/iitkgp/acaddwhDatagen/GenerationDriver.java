package in.ac.iitkgp.acaddwhDatagen;

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

public class GenerationDriver {

	public static void main(String[] args) {
		try {
			System.out.println("*** Phase 1 ***");
			DepartmentGen.main(args);
			System.out.println("*** Phase 2 ***");
			SpecialisationGen.main(args);
			System.out.println("*** Phase 3 ***");
			StudentGen.main(args);
			System.out.println("*** Phase 4 ***");
			TeacherGen.main(args);
			System.out.println("*** Phase 5 ***");
			CourseGen.main(args);
			System.out.println("*** Phase 6 ***");
			EvalAreaGen.main(args);
			System.out.println("*** Phase 7 ***");
			RegtypeGen.main(args);
			System.out.println("*** Phase 8 ***");
			TimeGen.main(args);
			
			//StudentCharacteristicsGen.main(args);
			System.out.println("*** Phase 9 ***");
			StuLearningGen.main(args);
			System.out.println("*** Phase 10 ***");
			SemPerformanceGen.main(args);
			System.out.println("*** Phase 11 ***");
			SplPerformanceGen.main(args);
			System.out.println("*** Phase 12 ***");
			TeachingQualityGen.main(args);
			
			System.out.println("*** All Phases Successfully Completed! ***");
			
		} catch (Exception e) {
			System.out.println("Driver stopped due to the following exception:");
			e.printStackTrace();
		}
	}

}
