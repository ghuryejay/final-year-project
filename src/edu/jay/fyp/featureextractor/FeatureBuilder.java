package edu.jay.fyp.featureextractor;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import weka.clusterers.SimpleKMeans;
import weka.core.Instances;
import weka.core.SerializationHelper;
import weka.core.converters.ConverterUtils.DataSource;

public class FeatureBuilder {

	public void buildFeature(String file) throws Exception{
		Map<Integer, ArrayList<Integer>> values = new HashMap<Integer, ArrayList<Integer>>();
		//to load built model from directory
		int videoCounter = 0;
		SimpleKMeans kmeans = (SimpleKMeans) SerializationHelper.read(new FileInputStream("D:\\fyp\\hollywood\\Models\\200C100I.model"));
		Instances training = DataSource.read(file);
		int numInstances = training.numInstances();
		ArrayList<Integer> aFeature = new ArrayList<Integer>();
		for(int i = 0;i < 200;i++)
			aFeature.add(0);
		for(int i = 0;i < numInstances;i++){
			int clusteNumber = kmeans.clusterInstance(training.instance(i));
			int t = aFeature.get(clusteNumber);
			t++;
			aFeature.set(clusteNumber, t);
		}
		values.put(videoCounter, aFeature);
		videoCounter++;
		ExcelWriter writer = new ExcelWriter();
		writer.writeToExcel(values);
	}

}

