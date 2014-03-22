package edu.jay.fyp.featureextractor.drivers;

import java.io.File;
import java.io.IOException;

import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.core.converters.ArffSaver;

public class DecisionTreeClassifier {

	
	public static void main(String[] args) {
		ArffLoader arffLoader = new ArffLoader();
		try {
			arffLoader.setFile(new File("D:\\fyp\\hollywood\\Arff features\\Final Training.arff"));
			Instances training = arffLoader.getDataSet();
			training.setClassIndex(training.numAttributes()-1);
			
			arffLoader.setFile(new File("D:\\fyp\\hollywood\\Arff features\\Final Testing.arff"));
			Instances testing = arffLoader.getDataSet();
			testing.setClassIndex(testing.numAttributes()-1);
			
			J48 tree = new J48();
			tree.buildClassifier(training);
			Instances labeled = new Instances(testing);
			
			for(int i = 0;i < testing.numInstances();i++){
				double classLabel = tree.classifyInstance(testing.instance(i));
				labeled.instance(i).setClassValue(classLabel);
			}
			ArffSaver arffSaver = new ArffSaver();
			arffSaver.setFile(new File("D:\\fyp\\hollywood\\Arff features\\labelledDT.arff"));
			arffSaver.setInstances(labeled);
			arffSaver.writeBatch();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
