package edu.jay.fyp.featureextractor.drivers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.core.converters.ArffSaver;
import edu.jay.fyp.featureextractor.FileExtractor;

public class FeatureDelete {

	private static final String DIR = "D:\\fyp\\hollywood\\Arff features\\test merge";
	private static final String EXT = ".arff";
	public static void main(String[] args) {
		FileExtractor fileExtractor = new FileExtractor(DIR, EXT);
		ArrayList<String> files = new ArrayList<String>();
		files = fileExtractor.getFiles();
		for(String file : files){
			ArffLoader arffLoader = new ArffLoader();
			try {
				arffLoader.setFile(new File(file));
				Instances data = arffLoader.getDataSet();
				int n = data.numInstances();
				int cnt = 0;
				Random random = new Random();
				while(cnt < 300000){
					int toDelete = random.nextInt(n);
					data.delete(toDelete);
					cnt++;
					n = data.numInstances();
				}
				ArffSaver arffSaver = new ArffSaver();
				arffSaver.setInstances(data);
				File f = new File("D:\\fyp\\hollywood\\Arff features\\"+"newfeature_version2.arff");
				arffSaver.setFile(f);
				arffSaver.writeBatch();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
