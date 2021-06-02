package james.meadows.hri.vendormatch.similar;

public class ZipSimilarity {
	
	public static String[] replace1 = new String[] {"-"," ","."};

	public static double zipSimilarity(String zip1, String zip2) {
		if (zip1 == null || zip2 == null)return -1;
		if (zip1.isBlank() || zip2.isBlank())return -1;
	
		zip1 = AddressSimilarity.replaceArray(zip1.toLowerCase(), replace1);
		zip2 = AddressSimilarity.replaceArray(zip2.toLowerCase(), replace1);
		
		if (zip1.equalsIgnoreCase(zip2))return 100;
		
		if (zip1.contains(zip2) || zip2.contains(zip1))return 100;
		if (zip1.startsWith(zip2) || zip2.startsWith(zip1))return 51;
		if (zip1.length() > 5 && zip2.length() > 5) {
			if (zip1.substring(0, 5).equals(zip2.substring(0, 5)))return 25;
		}
		return 0;
	}
}
