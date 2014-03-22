package edu.jay.fyp.featureextractor.video;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import javax.swing.SwingUtilities;

public class VideoListener implements ActionListener {

	private final String video;
	public VideoListener(String video){
		this.video = video;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		final VideoPlayer videoPlayer = new VideoPlayer();
		 SwingUtilities.invokeLater(new Runnable() {
           @Override
           public void run() {
               videoPlayer.playVideo(video);
           }
       });
	}
}
