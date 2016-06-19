package in.ac.iitkgp.acaddwhDatagen.util;

public class RandomGen {
	private static final String ALPHA_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public static String getString(int count) {
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
			int character = (int) (Math.random() * ALPHA_STRING.length());
			builder.append(ALPHA_STRING.charAt(character));
		}
		return builder.toString();
	}
	
	public static int getIntInRange(int min, int max) {
		int value = (int) Math.round(Math.random() * (max-min) + min);
		return value;
	}
	
	public static void main(String[] args) {
		for (int i = 0; i<100; i++){
			System.out.println(getIntInRange(10, 15));
		}
	}
}
