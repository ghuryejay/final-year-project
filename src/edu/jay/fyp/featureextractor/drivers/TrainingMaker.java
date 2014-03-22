package edu.jay.fyp.featureextractor.drivers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import au.com.bytecode.opencsv.CSVReader;

public class TrainingMaker {

	public static void main(String[] args) {
		try {
			CSVReader csvReader = new CSVReader(new FileReader("D:\\fyp\\output.txt"),'\t');
			List<String[]> lines = csvReader.readAll();
			FileInputStream fos = new FileInputStream(new File("D:\\fyp\\new.xls"));
			HSSFWorkbook workbook = new HSSFWorkbook(fos);
			HSSFSheet sheet = workbook.getSheetAt(0);
			int i;
			for(i = 1;i < 153;i++){
				Row row = sheet.getRow(i);
				Cell cell = row.createCell(200);
				String[] line = lines.get(i-1);
				cell.setCellValue(line[0]);	
			}
			FileOutputStream os = new FileOutputStream("D:\\fyp\\new.xls");
			workbook.write(os);
			fos.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
