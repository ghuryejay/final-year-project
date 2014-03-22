package edu.jay.fyp.featureextractor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;
import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

public class Converters {

	private static final String DIR = "D:\\fyp\\root";
	
	public void toCSV(String filePath, int numberOfFeatures) throws IOException{
		FileInputStream input_document = new FileInputStream(new File(filePath));
		HSSFWorkbook my_xls_workbook = new HSSFWorkbook(input_document);
		HSSFSheet my_worksheet = my_xls_workbook.getSheetAt(0); 
		Iterator<Row> rowIterator = my_worksheet.iterator();
		String nameSave = filePath.substring(DIR.length()+1, filePath.length()-4);
		FileWriter my_csv=new FileWriter(DIR+"\\"+nameSave+".csv");
		CSVWriter my_csv_output=new CSVWriter(my_csv);
		while(rowIterator.hasNext()){
	        Row row = rowIterator.next(); 
	        int i=0;
	        String[] csvdata = new String[numberOfFeatures];
	        Iterator<Cell> cellIterator = row.cellIterator();
	        while(cellIterator.hasNext()) {
	                Cell cell = cellIterator.next();
	                switch(cell.getCellType()) { 
	                case Cell.CELL_TYPE_STRING:{
	                        csvdata[i]= cell.getStringCellValue();    
	                        break;
	                }
	                case Cell.CELL_TYPE_NUMERIC:{
	                	csvdata[i] = String.valueOf(cell.getNumericCellValue());
	                	break;
	                }
	                }
	                i=i+1;
	        }
	        my_csv_output.writeNext(csvdata);
		}
		my_csv_output.close();
	}
	
	public void toExcel(String filePath) throws IOException{
		Workbook wb = new HSSFWorkbook();
        CreationHelper helper = wb.getCreationHelper();
        Sheet sheet = wb.createSheet("new sheet");
        CSVReader reader = new CSVReader(new FileReader(filePath));
        String[] line;
        long r = 0;
        while ((line = reader.readNext()) != null) {
            Row row = sheet.createRow((int) r++);
            
            for (int i = 0; i < line.length; i++)
                row.createCell(i)
                   .setCellValue(helper.createRichTextString(line[i]));
        }
        System.out.println(r);
        String nameSave = filePath.substring(DIR.length()+1, filePath.length()-4);
        FileOutputStream fileOut = new FileOutputStream(DIR+"\\"+nameSave+".xls");
        wb.write(fileOut);
        fileOut.close();
	}
	
	public void CSV2Arff(String filePath){
			CSVLoader csvLoader = new CSVLoader();
			try {
				csvLoader.setSource(new File(filePath));
			} catch (IOException e) {
				e.printStackTrace();
			}
			String fname = filePath.substring(DIR.length()+1, filePath.length()-4);
			Instances data = null;
			try {
				data = csvLoader.getDataSet();
			} catch (IOException e) {
				e.printStackTrace();
			}
			ArffSaver arffSaver = new ArffSaver();
			arffSaver.setInstances(data);
			try {
				File f = new File(DIR+"\\"+fname+".arff");
				arffSaver.setFile(f);
				arffSaver.writeBatch();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	public void textToCsv(String filename){
		BufferedReader br = null;
		try {
				br = new BufferedReader(new FileReader(filename));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		List<String[]> lines = new ArrayList<String[]>();
		String[] line;
		String temp;
		try {
			while((temp = br.readLine()) != null){
				line = temp.split("\t");
				lines.add(line);
			}
			String outputFile = "D:\\fyp\\root\\output.csv";
			CSVWriter csvWriter = new CSVWriter(new FileWriter(outputFile));
			csvWriter.writeAll(lines);
			csvWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

