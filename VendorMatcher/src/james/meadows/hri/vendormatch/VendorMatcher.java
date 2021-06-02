package james.meadows.hri.vendormatch;

import java.util.ArrayList;
import java.util.HashMap;

import james.meadows.hri.vendormatch.similar.CombinedSimilarity;

public class VendorMatcher {

	public static ArrayList<Vendor> vendors = new ArrayList<Vendor>();
	public static ExportVendors export =new ExportVendors();
	public static int vencounter = 1;
	
	public static String[] getVendorColumns() {
		return new String[] {"CompanyCode", "MasterCode","VendorID","VendorName","VendorAddress1","VendorAddress2","VendorAddress3","VendorCity","VendorState","VendorZip","VendorPhone", "SimilarityAbove", "SimilarityBelow"};
	}
	
	public static void outputUniqueList() {
		ArrayList<Vendor> output = new ArrayList<Vendor>();
		System.out.println("Matching by similarity for Master List. This may take a couple minutes.");
		Vendor start = vendors.get(0);		
		output.add(start);
		nextMostSimilar(start,output, false);
		System.out.println("Done Matching!");
		
		export.createVendorSheet(output, true);
		System.out.println("Finished creating Vendor Master List.");
	}
	
	public static void matchBySimilarity(boolean changeVencode, ArrayList<Vendor> output) {
		System.out.println("Matching by similarity. This may take a couple minutes.");
		Vendor start = vendors.get(0);
		if (changeVencode)start.vencode = vencounter;
		
		output.add(start);
		nextMostSimilar(start,output, changeVencode);
		System.out.println("Done Matching!");
		export.createVendorSheet(output, false);
		System.out.println("Finished creating Vendor Dump.");
		
	}
	
	public static void nextMostSimilar(Vendor start, ArrayList<Vendor> output, boolean changeVencode) {
		Vendor similar = findMostSimilarVendor(start, output, changeVencode);
		if (similar == null)return;
		double similarity = CombinedSimilarity.getCombined(start, similar);
		if (similarity >= 1) {
			if (changeVencode) {
				similar.vencode = start.vencode;
			}
		}
		else {
			if (changeVencode) {
				vencounter++;
				similar.vencode = vencounter;
			}
		}
			
		output.add(similar);
		nextMostSimilar(similar, output, changeVencode);
	}
	
	
	public static Vendor findMostSimilarVendor(Vendor vendor, ArrayList<Vendor> output, boolean changeVencode) {
		Vendor highestVal = null;
		double highestSim = -100;
		for (Vendor v : vendors) {
				if (output.contains(v))continue;
				if (!changeVencode) {
					if (containsVencode(v.vencode, output))continue;
				}
				double similarity = CombinedSimilarity.getCombined(vendor, v);
				if (similarity > highestSim) {
					highestVal = v;
					highestSim = similarity;
				}
			}
		return highestVal;
	}
	
	public static boolean containsVencode(int vencode, ArrayList<Vendor> output) {
		for (Vendor vendor : output) {
			if (vendor.vencode == vencode) return true;
		}
		return false;
	}
}
