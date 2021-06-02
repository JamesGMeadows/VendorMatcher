package james.meadows.hri.vendormatch;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class ExportVendors {

	ExcelFile excel;

	public ExportVendors() {
		excel = new ExcelFile();
	}

	public ExcelFile getExcel() {
		return excel;
	}

	private Row createHeader(Sheet sheet, String[] columns) {
		Row headerRow = sheet.createRow(0);
		CellStyle style = excel.getWorkbook().createCellStyle();
		Font headerFont = excel.getWorkbook().createFont();
		headerFont.setBold(true);
		headerFont.setColor((short) 0);
		style.setFont(headerFont);
		int counter = 0;
		for (String column : columns) {
			Cell cell = headerRow.createCell(counter);
			cell.setCellStyle(style);
			cell.setCellValue(column);
			counter++;
		}
		return headerRow;
	}

	public void createVendorSheet(ArrayList<Vendor> vendors, boolean unique) {
		String[] columns = VendorMatcher.getVendorColumns();
		Sheet sheet = excel.getWorkbook().createSheet(unique ? "Master List" : "Vendor Dump");

		this.createHeader(sheet, columns);
		ArrayList<Integer> vencodes = new ArrayList<Integer>();
		int skip = 0;
		for (int i = 0; i < vendors.size(); i++) {
			Row row = sheet.createRow(i + 1 + skip);
			Vendor data = vendors.get(i);
			if (unique) {
				if (vencodes.contains(data.vencode)) {
					skip--;
					continue;
				} else
					vencodes.add(data.vencode);

				if (Settings.enableConstraints) {
					if (!data.similarityAbove.isBlank() && !data.similarityBelow.isBlank()) {
						double score1 = Double.valueOf(data.similarityAbove.split(" ")[0]);
						double score2 = Double.valueOf(data.similarityBelow.split(" ")[0]);
						if (score1 != Settings.similarityCeiling && score2 != Settings.similarityCeiling) {
							skip--;
							continue;
						}
					}
				}
			}
			for (int j = 0; j < columns.length; j++) {
				Cell cell = row.createCell(j);
				if (j == 0) {
					if (data.hriCode == null) {
						cell.setCellValue("N/A");
					} else
						cell.setCellValue(data.hriCode);
				} else if (j == 1) {
					cell.setCellValue("VENB0000" + data.vencode);
				} else if (j == 2) {
					if (data.vendorID == null) {
						cell.setCellValue("N/A");
					} else
						cell.setCellValue(data.vendorID);
				} else if (j == 3) {
					if (data.vendorName == null) {
						cell.setCellValue("N/A");
					} else
						cell.setCellValue(data.vendorName);
				} else if (j == 4) {
					if (data.vendorAddress1 == null) {
						cell.setCellValue("N/A");
					} else
						cell.setCellValue(data.vendorAddress1);
				} else if (j == 5) {
					if (data.vendorAddress2 == null) {
						cell.setCellValue("N/A");
					} else
						cell.setCellValue(data.vendorAddress2);
				} else if (j == 6) {
					if (data.vendorAddress3 == null) {
						cell.setCellValue("N/A");
					} else
						cell.setCellValue(data.vendorAddress3);
				} else if (j == 7) {
					if (data.vendorCity == null) {
						cell.setCellValue("N/A");
					} else
						cell.setCellValue(data.vendorCity);
				} else if (j == 8) {
					if (data.vendorState == null) {
						cell.setCellValue("N/A");
					} else
						cell.setCellValue(data.vendorState);
				} else if (j == 9) {
					if (data.vendorZip == null) {
						cell.setCellValue("N/A");
					} else
						cell.setCellValue(data.vendorZip);
				} else if (j == 10) {
					if (data.vendorPhone == null) {
						cell.setCellValue("N/A");
					} else
						cell.setCellValue(data.vendorPhone);
				} else if (j == 11) {
					cell.setCellValue(data.similarityAbove);
				} else if (j == 12) {
					cell.setCellValue(data.similarityBelow);
				}
			}
		}

	}

}
