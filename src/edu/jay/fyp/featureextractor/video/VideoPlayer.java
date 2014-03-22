package edu.jay.fyp.featureextractor.video;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;
import weka.classifiers.mi.MDD;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;

public class VideoPlayer {

//    public static void main(final String[] args) {
        

//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new VideoPlayer().playVideo();
//            }
//        });
//    }

    public void playVideo() {
    	NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), "C:\\Program Files\\VideoLAN\\VLC");
        Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
        JFrame frame = new JFrame("vlcj Tutorial");
        MediaPlayerFactory mediaPlayerFactory = new MediaPlayerFactory();
        Canvas c = new Canvas();
        c.setBackground(Color.black);
        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());
        p.add(c, BorderLayout.CENTER);
        frame.add(p, BorderLayout.CENTER);
        EmbeddedMediaPlayer mediaPlayer =mediaPlayerFactory.newEmbeddedMediaPlayer();
        mediaPlayer.setVideoSurface(mediaPlayerFactory.newVideoSurface(c));
        frame.setLocation(100, 100);
        frame.setSize(1050, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        JFileChooser fc = new JFileChooser();
		fc.showOpenDialog(null);
		File file = fc.getSelectedFile();
		mediaPlayer.playMedia(file.getPath());
}
    
    public void playVideo(String dir){
    	NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), "C:\\Program Files\\VideoLAN\\VLC");
        Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
        final JFrame frame = new JFrame("vlcj Tutorial");
        MediaPlayerFactory mediaPlayerFactory = new MediaPlayerFactory();
        Canvas c = new Canvas();
        final EmbeddedMediaPlayer mediaPlayer =mediaPlayerFactory.newEmbeddedMediaPlayer();
        c.setBackground(Color.black);
        JPanel p = new JPanel();
        JButton button  = new JButton("Exit");
        button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mediaPlayer.stop();
				frame.setVisible(false);
			}
		});
        p.setLayout(new BorderLayout());
        p.add(c, BorderLayout.CENTER);
        frame.add(p, BorderLayout.CENTER);
        mediaPlayer.setVideoSurface(mediaPlayerFactory.newVideoSurface(c));
        frame.setLocation(100, 100);
        frame.setSize(1050, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(button, BorderLayout.SOUTH);
        frame.setVisible(true);
        mediaPlayer.playMedia(dir);
    }
}