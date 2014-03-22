package edu.jay.fyp.featureextractor.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

import org.apache.commons.lang3.StringEscapeUtils;

public class AudioAdder {

	private Connection connection;
	private final DBHelper dbHelper = new DBHelper();

	public AudioAdder() {
		try {
			connection = dbHelper.getConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void addAudio(String audioName, String audioPath, Map<String, String> meta) throws SQLException{
		audioPath = audioPath.replace("\\", "\\\\");
		String artist = meta.get("artist");
		String audioSampleRate = meta.get("audioSampleRate");
		String contentType = meta.get("Content-Type");
		String audioCompressor = meta.get("audioCompressor");
		String genre = meta.get("genre");
		String album = meta.get("album");
		String channels = meta.get("channels");
		String trackNumber = meta.get("trackNumber");
		String version = meta.get("version");
		String logComment = meta.get("logComment");
		String creator = meta.get("creator");
		String author = meta.get("author");
		String sampleRate = meta.get("samplerate");
		String releaseDate = meta.get("releaseDate");
		String audioChannelType = meta.get("audioChannelType");
		String title = meta.get("title");
		removeApos(artist);
		removeApos(audioSampleRate);
		removeApos(contentType);
		removeApos(audioCompressor);
		removeApos(genre);
		removeApos(album);
		removeApos(channels);
		removeApos(trackNumber);
		removeApos(version);
		removeApos(logComment);
		removeApos(creator);
		removeApos(author);
		removeApos(sampleRate);
		removeApos(releaseDate);
		removeApos(audioChannelType);
		removeApos(title);
		System.out.println(artist+" "+ audioSampleRate+" "+contentType+" "+audioCompressor+" "+genre+" "+album+" "+ channels+" "+trackNumber+" "+version+" "+logComment+" "+creator+" "+author+" "+sampleRate+" "+releaseDate+" "+audioChannelType+" "+title);
		
		String sql = "insert into songs values ('"+audioName+"','"+audioPath+"','"+releaseDate+"','"+audioChannelType+"','"+album+"','"+artist+"','"+channels+"','"+audioSampleRate+"','"+trackNumber+"','"+version+"','"+creator+"','"+audioCompressor+"','"+sampleRate+"','"+author+"','"+genre+"','"+contentType+"','"+title+"','"+logComment+"')";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.executeUpdate();
	}
	
	private String removeApos(String str){
		String ret = null; 
		if(str != null && str.contains("."))
			ret = str.replace("'", "\'");
		return ret;
	}
}