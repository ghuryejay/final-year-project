package edu.jay.fyp.featureextractor.drivers;

import java.io.File;
import java.io.IOException;

import weka.core.Instances;
import weka.core.converters.ArffLoader;

public class TestCommonBetweenTwo {

	public static void main(String[] args) {
		ArffLoader arffLoader1 = new ArffLoader();
		ArffLoader arffLoader2 = new ArffLoader();
		try {
			arffLoader1.setFile(new File("D:\\fyp\\hollywood\\Arff features\\class weka.classifiers.trees.RandomForestlabelled-1505835645.arff"));
			arffLoader2.setFile(new File("D:\\fyp\\hollywood\\Arff features\\Testing_Annotated.arff"));
			Instances bayesData = arffLoader2.getDataSet();
			Instances dtData = arffLoader1.getDataSet();
			int count = 0;
			int n = bayesData.numInstances();
			int numAttr = bayesData.numAttributes();
			for(int i = 0;i < n;i++){
				if(bayesData.instance(i).value(200) == dtData.instance(i).value(200))
					count++;
			}
			System.out.println(count);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
