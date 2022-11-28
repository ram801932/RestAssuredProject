package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelData {

	XSSFWorkbook workbook;
	HashMap<String, String> map;
	String excelPath;

	public ReadExcelData(String excelPath) {
		this.excelPath = excelPath;
	}
	
	public HashMap<String, String> getMap() {
		// File file = new File("C:\\Users\\003FH7744\\Documents\\IBM Reskill Program - FST\\TestData.xlsx");

		File file = new File(excelPath);
		FileInputStream fis;
		try {
			fis = new FileInputStream(file);
			workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet("Sheet1");
			// System.out.println("Name: "+
			// sheet.getRow(1).getCell(0).getStringCellValue());
			map = new HashMap<>();
			map.put("author", sheet.getRow(1).getCell(0).getStringCellValue());
			map.put("title", sheet.getRow(1).getCell(1).getStringCellValue());
			map.put("isAvailable", sheet.getRow(1).getCell(2).getStringCellValue());
			workbook.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}

}
