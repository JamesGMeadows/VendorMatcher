package james.meadows.hri.vendormatch.similar;

import james.meadows.hri.vendormatch.Vendor;

public class LocationSimilarity {

	public static double getLocationSimilarity(Vendor vendor1, Vendor vendor2) {
		double percent = 0;
		if (vendor1.vendorCity == null || vendor2.vendorCity == null)return -1;
		if (vendor1.vendorState == null || vendor2.vendorState == null)return -1;
		
		String city1 = vendor1.vendorCity.replaceAll(" ", "");
		String city2 = vendor2.vendorCity.replaceAll(" ", "");
		String state1 = vendor1.vendorState.replaceAll(" ", "");
		String state2 = vendor2.vendorState.replaceAll(" ", "");
		
		if (!city1.isBlank()|| !city2.isBlank()) {
			if (city1.equalsIgnoreCase(city2))percent +=51;
		}
		if (!state1.isBlank()|| !state2.isBlank()) {
			if (state1.equalsIgnoreCase(state2))percent +=51;
		}
		return percent;
	}
}
