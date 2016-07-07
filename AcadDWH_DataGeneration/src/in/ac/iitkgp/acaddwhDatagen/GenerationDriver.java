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
import in.ac.iitkgp.acaddwhDatagen.gen.intermediate.StudentCharacteristicsGen;

public class GenerationDriver {

	public static void main(String[] args) {
		try {
			DepartmentGen.main(args);
			SpecialisationGen.main(args);
			StudentGen.main(args);
			TeacherGen.main(args);
			CourseGen.main(args);
			EvalAreaGen.main(args);
			RegtypeGen.main(args);
			TimeGen.main(args);
			
			//StudentCharacteristicsGen.main(args);
			StuLearningGen.main(args);
			SemPerformanceGen.main(args);
			SplPerformanceGen.main(args);
			TeachingQualityGen.main(args);
			
		} catch (Exception e) {
			System.out.println("Driver stopped!");
		}
	}

}
