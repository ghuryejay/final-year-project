package edu.jay.fyp.featureextractor.drivers;

import java.io.IOException;

import edu.jay.fyp.featureextractor.ColumnNamer;
import edu.jay.fyp.featureextractor.Converters;

public class ColumnRemoverDriver {

	private static final String DIR = "D:\\fyp\\hollywood\\csv features";
	private static final String excelPath = "D:\\fyp\\hollywood\\excel features";
	private static final String finalCSV = "D:\\fyp\\hollywood\\final CSV";
	public static void main(String[] args) {
		Converters converters = new Converters();
		//try {
			//converters.toExcel(DIR, excelPath);
			//ColumnNamer columnNamer = new ColumnNamer();
			//columnNamer.nameColumns(excelPath, ".xls");
			//converters.toCSV(excelPath, finalCSV);
			converters.CSV2Arff(finalCSV);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}

}
