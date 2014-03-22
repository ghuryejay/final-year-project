package edu.jay.fyp.featureextractor.video;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import edu.jay.fyp.featureextractor.database.ActivitySelector;

public class ActivityButtonListener implements ActionListener{

	private final String activity;
	
	public ActivityButtonListener(String activity){
		this.activity = activity;
	}
	
	@Override
	public void actionPerformed(ActionEvent paramActionEvent) {
		ActivitySelector activitySelector = new ActivitySelector();
		List<String> videos = new ArrayList<String>();
		videos = activitySelector.getVideos(this.activity);
		VideoPanel videoPanel = new VideoPanel();
		videoPanel.generatePanel(videos);
	}

	
}
