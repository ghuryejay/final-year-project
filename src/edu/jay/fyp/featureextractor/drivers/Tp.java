package edu.jay.fyp.featureextractor.drivers;

import java.io.IOException;
import java.util.List;

import edu.jay.fyp.featureextractor.database.ActivitySelector;

public class Tp {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		ActivitySelector activitySelector = new ActivitySelector();
		List<String> paths = activitySelector.getVideos("SitUp");
		for(String path : paths){
			System.out.println(path);
		}
	}

}
