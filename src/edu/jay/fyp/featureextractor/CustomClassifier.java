package edu.jay.fyp.featureextractor;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import weka.classifiers.Classifier;
import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.core.converters.ArffSaver;

public class CustomClassifier<T extends Classifier> {
	
	private T classifier;
	
	public void classify(Class<T> cls){
		try {
			classifier = cls.newInstance();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		}
		ArffLoader arffLoader = new ArffLoader();
		try {
			arffLoader.setFile(new File("D:\\fyp\\hollywood\\Arff features\\Final Training.arff"));
			Instances training = arffLoader.getDataSet();
			training.setClassIndex(training.numAttributes()-1);
			
			arffLoader.setFile(new File("D:\\fyp\\root\\finaltraining.arff"));
			Instances testing = arffLoader.getDataSet();
			testing.setClassIndex(testing.numAttributes()-1);
			
			classifier.buildClassifier(training);
			Instances labeled = new Instances(testing);
			
			for(int i = 0;i < testing.numInstances();i++){
				double classLabel = classifier.classifyInstance(testing.instance(i));
				labeled.instance(i).setClassValue(classLabel);
				System.out.println(labeled.instance(i).attribute(200));
			}
			ArffSaver arffSaver = new ArffSaver();
			String cname = cls.toString();
			arffSaver.setFile(new File("D:\\fyp\\hollywood\\Arff features\\"+cname+"labelled"+new Random().nextInt()+".arff"));
			arffSaver.setInstances(labeled);
			arffSaver.writeBatch();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
