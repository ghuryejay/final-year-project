package edu.jay.fyp.featureextractor;

import java.io.IOException;

public class ArffBuilder {

	private static final String DIR = "D:\\fyp\\root";
	public void buildArff(){
		String csvPath = DIR + "\\output-new.csv";
		Converters converters = new Converters();
		FeatureBuilder featureBuilder = new FeatureBuilder();
		ColumnNamer columnNamer = new ColumnNamer();
		try {
			converters.toExcel(csvPath);
			columnNamer.nameColumns(DIR + "\\output-new.xls",162,false);
			converters.toCSV(DIR + "\\output-new.xls",162);
			converters.CSV2Arff(DIR + "\\output-new.csv");
			featureBuilder.buildFeature(DIR+"\\output-new.arff");
			columnNamer.nameColumns(DIR+"\\finaltraining.xls",201,true);
			converters.toCSV(DIR+"\\finaltraining.xls",201);
			converters.CSV2Arff(DIR+"\\finaltraining.csv");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
