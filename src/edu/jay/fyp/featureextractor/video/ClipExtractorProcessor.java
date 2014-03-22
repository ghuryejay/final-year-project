package edu.jay.fyp.featureextractor.video;

import edu.jay.fyp.featureextractor.BatchExecutor;
import edu.jay.fyp.featureextractor.BatchFileForVideosGenerator;
import edu.jay.fyp.featureextractor.RemoveLines;
import edu.jay.fyp.featureextractor.database.StipDB;


public class ClipExtractorProcessor {

	public void process(String path){
		BatchFileForVideosGenerator bvg = new BatchFileForVideosGenerator();
		BatchExecutor batchExecutor = new BatchExecutor();
		RemoveLines removeLines = new RemoveLines();
		String batchPath = bvg.generateBatchFileForSingleVideo(path,"D:\\fyp\\clipExtractor\\smallclips");
		batchExecutor.executeBatch(batchPath);
		String stippath = "D:\\fyp\\clipExtractor\\smallclips\\output.txt";
		removeLines.delete(stippath, 1, 3);
		StipDB stipDB = new StipDB();
		String videoPath = stipDB.getParentFilePath(stippath);
		if(videoPath != null){
			VideoPlayer videoPlayer = new VideoPlayer();
			videoPlayer.playVideo(videoPath);
		}
		else {
			System.out.println("Video not found");
		}
	}

}
