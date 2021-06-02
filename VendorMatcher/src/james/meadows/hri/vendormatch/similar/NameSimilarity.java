package james.meadows.hri.vendormatch.similar;

import james.meadows.hri.vendormatch.Vendor;

public class NameSimilarity {
	
	private static String[] exclude1 = new String[]{" ", "  " ,",",".", "!", "-", "_"};
	private static String[] exclude2 = new String[]{"inc","co","company","llc","supply","business","services","exp", "&", "rental"};

	public static double getNameSimilarity(Vendor vendor1, Vendor vendor2) {
		double percent = 0;
		
		String name1 = vendor1.vendorName;
		String name2 = vendor2.vendorName;
		if (name1 == null || name2 == null)return -1;
		if (name1.isBlank() || name2.isBlank())return -1;
		name1 = AddressSimilarity.replaceArray(name1.toLowerCase(), exclude1);
		name1 = AddressSimilarity.replaceArray(name1.toLowerCase(), exclude2);
		
		name2 = AddressSimilarity.replaceArray(name2.toLowerCase(), exclude1);
		name2 = AddressSimilarity.replaceArray(name2.toLowerCase(), exclude2);

		if (name1.equalsIgnoreCase(name2))return 100;
		
		if (name1.contains(name2) || name2.contains(name1))return 50;
		
		if (name1.startsWith(name2) || name2.startsWith(name1))return 50;
		
		int length1 = name1.length()/2;
		int length2 = name2.length()/2;
		if (name2.length() > length1){
			if (name1.substring(0, length1).equals(name2.substring(0, length1))) {
				return 25;
			}
		}
		if (name1.length() > length2){
			if (name2.substring(0, length2).equals(name1.substring(0, length2))) {
			return 25;
			}
		}
		
		return percent;
	}
}
