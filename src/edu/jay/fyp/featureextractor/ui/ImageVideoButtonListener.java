package edu.jay.fyp.featureextractor.ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

import edu.jay.fyp.featureextractor.database.ImageVideoMatch;
import edu.jay.fyp.featureextractor.video.VideoPlayer;

public class ImageVideoButtonListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		JFrame frame = new JFrame();
		frame.setLocation(100, 100);
        frame.setSize(1050, 600);
        JPanel panel = new JPanel(new GridLayout(10, 10));
        JFileChooser fc = new JFileChooser();
		fc.showOpenDialog(null);
		String path1 = null;
		File file = fc.getSelectedFile();
		String path = file.getAbsolutePath();
		ImageVideoMatch ivm = new ImageVideoMatch();
		Map<String, String> videos = ivm.getVideos(path);
		for(String name : videos.keySet()){
			final String videoPath = videos.get(name);
			JButton button = new JButton(name);
			button.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					VideoPlayer player = new VideoPlayer();
					player.playVideo(videoPath);
				}
			});
			panel.add(button);
		}
		frame.add(panel);
		frame.setVisible(true);
	}

}
