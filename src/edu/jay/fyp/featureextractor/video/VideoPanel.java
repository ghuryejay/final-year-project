package edu.jay.fyp.featureextractor.video;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class VideoPanel {

	public void generatePanel(List<String> videos) {
		JFrame frame = new JFrame("Video Lister");
		JPanel panel = new JPanel(new GridLayout(10, 10));
		for (int i = 1; i <= videos.size(); i++) {
			JButton button = new JButton("Video" + i);
			button.setFont(new Font("Times New Roman", Font.BOLD, 14));
			ActionListener actionListener = new VideoListener(videos.get(i - 1));
			button.addActionListener(actionListener);
			button.setSize(20, 20);
			panel.add(button);
		}
		frame.add(panel);
		frame.setLocation(100, 100);
		frame.setSize(1050, 600);
		frame.setVisible(true);
	}

}
