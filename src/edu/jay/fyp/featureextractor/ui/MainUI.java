package edu.jay.fyp.featureextractor.ui;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainUI {
	
	public static void main(String[] args){
		JFrame frame = new JFrame();
		frame.setLocation(100, 100);
        frame.setSize(1050, 600);
        
        JPanel panel = new JPanel(new GridLayout(10, 10));
        
        JButton rb1 = new JButton("Video from Image");
        JButton rb2 = new JButton("Video from video");
        JButton rb3 = new JButton("Audio from audio");
        
        rb1.addActionListener(new ImageVideoButtonListener());
        rb3.addActionListener(new AudioFromAudioListener());
        rb2.addActionListener(new VideofromVideoListener());
        
        panel.add(rb1);
        panel.add(rb2);
        panel.add(rb3);
        
        frame.add(panel);
        frame.setVisible(true);
	}
}
