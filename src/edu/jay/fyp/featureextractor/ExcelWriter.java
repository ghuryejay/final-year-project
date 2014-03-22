package edu.jay.fyp.featureextractor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class ExcelWriter {
	
	public void writeToExcel(Map<Integer,ArrayList<Integer>> values){
		HSSFWorkbook  hssfWorkbook = new HSSFWorkbook();
		HSSFSheet sheet = hssfWorkbook.createSheet("sample");
		int rowNum = 0, colNum;
		for(int i : values.keySet()){
			Row row = sheet.createRow(rowNum++);
			ArrayList<Integer> list = values.get(i);
			colNum = 0;
			for(int j : list){
				Cell cell = row.createCell(colNum++);
				cell.setCellValue(j);
			}
		}
		try {
		    FileOutputStream out = 
		            new FileOutputStream(new File("D:\\fyp\\root\\finaltraining.xls"));
		    hssfWorkbook.write(out);
		    out.close();
		    System.out.println("Excel written successfully..");
		     
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}

}
