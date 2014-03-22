package edu.jay.fyp.featureextractor.audio;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.jay.fyp.featureextractor.FileExtractor;
import edu.jay.fyp.featureextractor.database.AudioAdder;

public class ProcessAudio {

	private static final String dir = "D:\\fyp\\songs";
	public static void main (String[] args){
		FileExtractor fileExtractor = new FileExtractor(dir, ".mp3");
		Map<String, String> meta = new HashMap<String, String>();
		List<String> paths = new ArrayList<String>();
		paths = fileExtractor.getFiles();
		AudioMetaGenerator audioMetaGenerator = new AudioMetaGenerator();
		AudioAdder audioAdder = new AudioAdder();
		for(String path : paths){
			String fileName = path.substring(dir.length()+1,path.length());
			meta = audioMetaGenerator.getMetaData(path);
			meta.remove("Author");
			try {
				audioAdder.addAudio(fileName,path, meta);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
