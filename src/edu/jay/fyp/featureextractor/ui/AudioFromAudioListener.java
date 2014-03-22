package edu.jay.fyp.featureextractor.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Map;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import edu.jay.fyp.featureextractor.audio.AudioMetaGenerator;
import edu.jay.fyp.featureextractor.audio.AudioPlayer;
import edu.jay.fyp.featureextractor.database.AudioMetadataMatcher;

public class AudioFromAudioListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		JFrame frame = new JFrame();
		frame.setLocation(100, 100);
		frame.setSize(1050, 600);

		JFileChooser fc = new JFileChooser();
		fc.showOpenDialog(null);
		File file = fc.getSelectedFile();
		String filePath = file.getAbsolutePath();
		AudioMetaGenerator generator = new AudioMetaGenerator();
		Map<String, String> metaData = generator.getMetaData(filePath);
		AudioMetadataMatcher metadataMatcher = new AudioMetadataMatcher();
		String desiredPath = metadataMatcher.getAudioPath(metaData);
		AudioPlayer player = new AudioPlayer();
		player.playSong(desiredPath);
	}

}
