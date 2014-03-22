package edu.jay.fyp.featureextractor.video;

import weka.classifiers.trees.RandomForest;
import edu.jay.fyp.featureextractor.ArffBuilder;
import edu.jay.fyp.featureextractor.BatchExecutor;
import edu.jay.fyp.featureextractor.BatchFileForVideosGenerator;
import edu.jay.fyp.featureextractor.ColumnRemover;
import edu.jay.fyp.featureextractor.Converters;
import edu.jay.fyp.featureextractor.CustomClassifier;
import edu.jay.fyp.featureextractor.RemoveLines;


public class Processor {

	public void execute(String path){
		BatchFileForVideosGenerator bvg = new BatchFileForVideosGenerator();
		BatchExecutor batchExecutor = new BatchExecutor();
		Converters converters = new Converters();
		RemoveLines removeLines = new RemoveLines();
		ColumnRemover columnRemover = new ColumnRemover();
		ArffBuilder arffBuilder = new ArffBuilder();
		String batchPath = bvg.generateBatchFileForSingleVideo(path,"D:\\fyp\\hollywood");
		batchExecutor.executeBatch(batchPath);
		removeLines.delete("D:\\fyp\\hollywood\\videoclips\\output.txt", 1, 3);
		converters.textToCsv("D:\\fyp\\hollywood\\videoclips\\output.txt");
		columnRemover.removeColumns("D:\\fyp\\root\\output.csv");
		arffBuilder.buildArff();
		CustomClassifier<RandomForest> classifier = new CustomClassifier<RandomForest>();
		classifier.classify(RandomForest.class);
	}

}
