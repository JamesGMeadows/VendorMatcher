package james.meadows.hri.vendormatch;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public class Main {
	public static void main(String[] args) {
		Long start = System.currentTimeMillis();
		
		runVendorProgram();
		
		Long finish = System.currentTimeMillis();
		Long dif = finish - start;
		System.out.println("Finished in " + dif + " milliseconds.");
	}

	public static void runVendorProgram() {
		try {
			VendorExcelReader.readExcel();
		} catch (InvalidFormatException | IOException e) {
			e.printStackTrace();
		}
	}
}
