package edu.jay.fyp.featureextractor.video;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;

@SuppressWarnings("unused")
public class ActivityDisplayer {

	public static void main(String[] args) {
		JLabel label = new JLabel("Please Select Any 1 Of The Following Activities:",JLabel.CENTER);
	    label.setFont(new Font("Times New Roman", Font.BOLD, 24));
	    JFrame frame = new JFrame("Activity Lister");
	    label.setForeground(Color.GRAY);
		List<String> activities = new ArrayList<String>();
		activities.add("SitUp");
		activities.add("StandUp");
		activities.add("Kiss");
		activities.add("GetOutCar");
		activities.add("HugPerson");
		activities.add("SitDown");
		activities.add("HandShake");
		activities.add("AnswerPhone");
		JPanel outerPanel = new JPanel(new BorderLayout());
		JPanel panel = new JPanel(new GridLayout(4,2,8,8));
		outerPanel.add(label,BorderLayout.NORTH);
		outerPanel.add(panel,BorderLayout.CENTER);
		for (String activity : activities) {
			JButton button = new JButton(activity);
			button.setFont(new Font("Times New Roman", Font.BOLD, 14));
			ActivityButtonListener activityButtonListener = new ActivityButtonListener(activity);
			button.addActionListener(activityButtonListener);
			panel.add(button);
		}
		frame.setLocation(100, 100);
		frame.setSize(1050, 500);
		frame.setContentPane(outerPanel);
		frame.setVisible(true);
	}

}