package edu.jay.fyp.featureextractor.audio;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;

public class AudioPlayer {

//    public static void main(final String[] args) {
//        NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), "C:\\Program Files\\VideoLAN\\VLC");
//        Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
//
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new AudioPlayer(args);
//            }
//        });
//    }

    public void playSong(String path) {
        final JFrame frame = new JFrame("Audio Player");
        NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), "C:\\Program Files\\VideoLAN\\VLC");
        Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
        MediaPlayerFactory mediaPlayerFactory = new MediaPlayerFactory();

        Canvas c = new Canvas();
        c.setBackground(Color.black);
        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());
        p.add(c, BorderLayout.CENTER);
        frame.add(p, BorderLayout.CENTER);
        
        final EmbeddedMediaPlayer mediaPlayer = mediaPlayerFactory.newEmbeddedMediaPlayer();
        mediaPlayer.setVideoSurface(mediaPlayerFactory.newVideoSurface(c));
        JPanel buttonPanel = new JPanel(new GridLayout(1,3));
        final JButton playButton = new JButton("Play");
        final JButton pauseButton = new JButton("Pause");
        JButton exitButton = new JButton("Exit");
        playButton.addActionListener(new ActionListener() {
        	
        	@Override
        	public void actionPerformed(ActionEvent arg0) {
        		mediaPlayer.play();
        		pauseButton.setEnabled(true);
        		playButton.setEnabled(false);
        	}
        });
        pauseButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mediaPlayer.pause();
				pauseButton.setEnabled(false);
				playButton.setEnabled(true);
			}
		});
        exitButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mediaPlayer.stop();
				frame.setVisible(false);
			}
		});
        buttonPanel.add(playButton);
        buttonPanel.add(pauseButton);
        buttonPanel.add(exitButton);
        frame.setLocation(100, 100);
        frame.setSize(400,400);
        frame.add(buttonPanel,BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        mediaPlayer.playMedia(path);
    }
}
