package in.ac.iitkgp.acaddwhDatagen.util;

import java.text.DecimalFormat;
import java.util.ArrayList;

import in.ac.iitkgp.acaddwhDatagen.config.ProjectInfo;

public class RandomGen {
	private static final String ALPHA_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final String NUMERIC_STRING = "0123456789";

	public static String getString(int count) {
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
			int character = (int) (Math.random() * ALPHA_STRING.length());
			builder.append(ALPHA_STRING.charAt(character));
		}
		return builder.toString();
	}

	public static String getNumber(int count) {
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
			int character = (int) (Math.random() * NUMERIC_STRING.length());
			builder.append(NUMERIC_STRING.charAt(character));
		}
		return builder.toString();
	}

	public static int getIntInRange(int min, int max) {
		int value;
		do {
			value = (int) Math.floor(Math.random() * (max - min + 1) + min);
		} while (value > max);

		return value;
	}

	public static String getFromPSet(ArrayList<String> elems, ArrayList<Double> freqs) {
		String randomElem = null;
		ArrayList<Double> cumfreqs = new ArrayList<Double>();
		double cumsum = 0, randomX;

		for (Double freq : freqs) {
			cumsum += freq;
			cumfreqs.add(cumsum);
		}
		randomX = Math.ceil(Math.random() * cumsum);

		for (int i = 0; i < cumfreqs.size(); ++i) {
			if (randomX <= cumfreqs.get(i)) {
				randomElem = elems.get(i);
				break;
			}
		}
		// System.out.println("Elem = " + elems);
		// System.out.println("Freq = " + freqs);
		// System.out.println("CFre = " + cumfreqs);
		// System.out.println("RandomX = " + randomX);
		// System.out.println("Rand Elem = " + randomElem);

		return randomElem;
	}

	public static String getFromPSet(String[] elemsArray, Double[] freqsArray) {
		ArrayList<String> elems = new ArrayList<String>();
		ArrayList<Double> freqs = new ArrayList<Double>();

		for (String elem : elemsArray) {
			elems.add(elem);
		}
		for (Double freq : freqsArray) {
			freqs.add(freq);
		}

		return getFromPSet(elems, freqs);
	}

	public static String getCgpa() {
		ArrayList<String> elems = new ArrayList<String>();
		ArrayList<Double> freqs = new ArrayList<Double>();
		elems.add("9.5");
		freqs.add(4.0);
		elems.add("9.0");
		freqs.add(8.0);
		elems.add("8.5");
		freqs.add(16.0);
		elems.add("8.0");
		freqs.add(18.0);
		elems.add("7.5");
		freqs.add(19.0);
		elems.add("7.0");
		freqs.add(15.0);
		elems.add("6.5");
		freqs.add(10.0);
		elems.add("6.0");
		freqs.add(8.0);
		elems.add("5.5");
		freqs.add(2.0);

		double cgpa = Double.parseDouble(getFromPSet(elems, freqs));
		cgpa += Math.random() * 0.5;
		cgpa = Math.round(cgpa * 100);
		if (cgpa != 100 && getIntInRange(0, 1) == 0) {
			cgpa += 1;
		}
		cgpa /= 100;

		return (new DecimalFormat("#.00").format(cgpa));
	}
	
	public static String getAverageTeachingQuality() {
		ArrayList<String> elems = new ArrayList<String>();
		ArrayList<Double> freqs = new ArrayList<Double>();
		elems.add("9.5");
		freqs.add(8.0);
		elems.add("9.0");
		freqs.add(8.0);
		elems.add("8.5");
		freqs.add(18.0);
		elems.add("8.0");
		freqs.add(20.0);
		elems.add("7.5");
		freqs.add(17.0);
		elems.add("7.0");
		freqs.add(14.0);
		elems.add("6.5");
		freqs.add(11.0);
		elems.add("6.0");
		freqs.add(4.0);

		double cgpa = Double.parseDouble(getFromPSet(elems, freqs));
		cgpa += Math.random() * 0.5;
		cgpa = Math.round(cgpa * 100);
		if (cgpa != 100 && getIntInRange(0, 1) == 0) {
			cgpa += 1;
		}
		cgpa /= 100;

		return (new DecimalFormat("#.00").format(cgpa));
	}

	public static void main(String[] args) {

		StringBuffer content = new StringBuffer();
		for (int i = 0; i < 100000; i++) {
			System.out.println("Inititaing line " + i + "...");
			content.append(getIntInRange(100, 110) + "\n");
		}
		String filePath = ProjectInfo.getPathToStore() + "test_FILE" + ".csv";
		DataWriter.writeToFile(filePath, content.toString());

	}
}
