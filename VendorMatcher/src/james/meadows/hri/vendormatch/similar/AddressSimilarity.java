package james.meadows.hri.vendormatch.similar;

public class AddressSimilarity {

	//eliminate based on different city, state
	public static double AddressSimilarity(String address1, String address2) {
		if (address1 == null || address2 == null)return -1;
		if (address1.isBlank() || address2.isBlank())return -1;
		if (address1.equalsIgnoreCase(address2))return 100;
	
		double percent = 0;
		String[] replace1 = new String[] {".",","};
		String[] replace2 = new String[] {"p.o. box","p.o.box","pobox","po box","st.","street","st","avenue","ave","rd.","rd","drive","dr.","dr", "blvd","suite","suit","south","north","east","west",".",","};
		address1 = replaceArray(address1.toLowerCase(),replace1);
		address1 = replaceArray(address1.toLowerCase(),replace2);
		address2 = replaceArray(address2.toLowerCase(),replace1);
		address2 = replaceArray(address2.toLowerCase(),replace2);
		if (address1.equalsIgnoreCase(address2))return 100;
		int sameWords = sameWords(address1, address2);
		if (sameWords > 0)percent += 51;

	
		return percent;
	}
	
	public static int sameWords(String address1, String address2) {
		String[] split1 = address1.split(" ");
		String[] split2 = address2.split(" ");
		
		int count = 0;
		for (String s1 : split1) {
			for (String s2 : split2) {
				if (s1.isBlank() || s2.isBlank())continue;
				if (s1.equalsIgnoreCase(s2))count++;
			}
		}
		return count;
	}
	
	public static String replaceArray(String string1, String[] replace) {
		for (String r : replace) {
			string1 = string1.replace(r, "");
		}
		return string1;
	}
}
