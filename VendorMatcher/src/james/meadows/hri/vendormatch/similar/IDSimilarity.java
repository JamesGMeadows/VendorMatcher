package james.meadows.hri.vendormatch.similar;

import james.meadows.hri.vendormatch.Vendor;

public class IDSimilarity {
	
	static String[] replace1 = new String[] {" ", "  ", "-", "!",".",",","#","&","@","$", "- EXP", "-EXP", "EXP"};
	
	public static double getIdSimilarity(Vendor vendor1, Vendor vendor2) {
		double percent = 0;
		String id1 = vendor1.vendorID;
		String id2 = vendor2.vendorID;
		if (id1 == null || id2 == null)return -1;
		if (id1.isBlank() || id2.isBlank())return -1;
		id1 = AddressSimilarity.replaceArray(id1.toLowerCase(), replace1);
		id2 = AddressSimilarity.replaceArray(id2.toLowerCase(), replace1);
		if (id1.equalsIgnoreCase(id2))return 100;
		if (id1.contains(id2) || id2.contains(id1))return 50;
		
		if (id1.startsWith(id2) || id2.startsWith(id1))return 50;
		
		int length1 = id1.length()/2;
		int length2 = id2.length()/2;
		if (id2.length() > length1){
			if (id1.substring(0, length1).equals(id2.substring(0, length1))) {
				return 25;
			}
		}
		if (id1.length() > length2){
			if (id2.substring(0, length2).equals(id1.substring(0, length2))) {
			return 25;
			}
		}
		

		return percent;
	}
}
