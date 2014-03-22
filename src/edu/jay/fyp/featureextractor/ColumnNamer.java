package edu.jay.fyp.featureextractor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class ColumnNamer {

	public void nameColumns(final String filePath, final int numFeatures, final boolean addFeature)
	{
		try {
			FileInputStream fos = new FileInputStream(new File(filePath));
			HSSFWorkbook workbook = new HSSFWorkbook(fos);
			HSSFSheet sheet = workbook.getSheetAt(0);
			sheet.shiftRows(0, 0, 1);
			sheet.createRow(0);
			Row row = sheet.getRow(0);
			for(int i = 1;i <= numFeatures;i++){
				row.createCell(i-1);
				Cell cell = row.getCell(i-1);
				cell.setCellValue("Feature "+i);
			}
			if(addFeature){
				row = sheet.getRow(1);
				Cell cell = row.createCell(200);
				cell.setCellValue("?");
			}
			FileOutputStream os = new FileOutputStream(filePath);
			workbook.write(os);
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
