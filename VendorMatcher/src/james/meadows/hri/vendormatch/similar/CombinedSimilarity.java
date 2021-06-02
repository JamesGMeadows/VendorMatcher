package james.meadows.hri.vendormatch.similar;

import james.meadows.hri.vendormatch.Vendor;

public class CombinedSimilarity {

	
	public static double getCombined(Vendor vendor1, Vendor vendor2) {
		double score = 0;
		double address = combinedAddress(vendor1, vendor2);
		score+= getScore(address,1,1);
		
		double zip = ZipSimilarity.zipSimilarity(vendor1.vendorZip, vendor2.vendorZip);
		score+= getScore(zip,1,1);
		
		double location = LocationSimilarity.getLocationSimilarity(vendor1, vendor2);
		score+= getScore(location,1,1);
		
		double name = NameSimilarity.getNameSimilarity(vendor1, vendor2);
		score+= getScore(name,2,2);
		
		double id = IDSimilarity.getIdSimilarity(vendor1, vendor2);
		score+= getScore(id,2,2);
		
		vendor1.similarityBelow = score + " ( " + address + ", " + zip + ", " + location + ", " + name + ", " + id + " )";
		vendor2.similarityAbove = score + " ( " + address + ", " + zip + ", " + location + ", " + name + ", " + id + " )";
		
		return score;
	}
	
	public static double getScore(double percent, double weightCorrect, double weightWrong) {
		if (percent == -1)return 0;
		if (percent >= 100) {
			return 1 * weightCorrect;
		}
		else if (percent >= 50) {
			return .5;
		}
		else if (percent >= 25) {
			return 0;
		}
		return -.5 * weightWrong;
	}
	
	public static double combinedAddress(Vendor vendor1, Vendor vendor2) {
		double[] array = new double[9];
		array[0] = AddressSimilarity.AddressSimilarity(vendor1.vendorAddress1, vendor2.vendorAddress1);
		array[1] = AddressSimilarity.AddressSimilarity(vendor1.vendorAddress1, vendor2.vendorAddress2);
		array[2] = AddressSimilarity.AddressSimilarity(vendor1.vendorAddress1, vendor2.vendorAddress3);
		
		array[3] = AddressSimilarity.AddressSimilarity(vendor1.vendorAddress2, vendor2.vendorAddress1);
		array[4] = AddressSimilarity.AddressSimilarity(vendor1.vendorAddress2, vendor2.vendorAddress2);
		array[5] = AddressSimilarity.AddressSimilarity(vendor1.vendorAddress2, vendor2.vendorAddress3);
		
		array[6] = AddressSimilarity.AddressSimilarity(vendor1.vendorAddress3, vendor2.vendorAddress1);
		array[7] = AddressSimilarity.AddressSimilarity(vendor1.vendorAddress3, vendor2.vendorAddress2);
		array[8] = AddressSimilarity.AddressSimilarity(vendor1.vendorAddress3, vendor2.vendorAddress3);
		
		double highest = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i] > highest) {
				highest = array[i];
			}
		}
		return highest;
	}
}
