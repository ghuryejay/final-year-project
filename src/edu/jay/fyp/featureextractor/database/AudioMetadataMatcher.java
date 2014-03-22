package edu.jay.fyp.featureextractor.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class AudioMetadataMatcher {

	
	private Connection connection;
	private final DBHelper dbHelper = new DBHelper();
	
	public AudioMetadataMatcher(){
		try {
			connection = dbHelper.getConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public String getAudioPath(Map<String, String> meta){
		
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
		String path = null;
		
		String query = "select * from songs";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				int matchCount = 0;
				String artistDB = rs.getString("artist");
				String audiosampleRateDB = rs.getString("audioSampleRate");
				String contentTypeDB = rs.getString("Content-Type");
				String audioCompressorDB = rs.getString("audioCompressor");
				String genreDB = rs.getString("genre");
				String albumDB = rs.getString("album");
				String channelsDB = rs.getString("channels");
				String trackNumberDB = rs.getString("trackNumber");
				String versionDB = rs.getString("version");
				String logCommentDB = rs.getString("logComment");
				String creatorDB = rs.getString("creator");
				String authorDB = rs.getString("author");
				String sampleRateDB = rs.getString("samplerate");
				String releaseDateDB = rs.getString("releaseDate");
				String audioChannelTypeDB = rs.getString("audioChannelType");
				String titleDB = rs.getString("title");
				
				if(matchStrings(artistDB, artist))
					matchCount++;
				if(matchStrings(audioSampleRate, audioChannelTypeDB))
					matchCount++;
				if(matchStrings(contentType, contentTypeDB))
					matchCount++;
				if(matchStrings(audioCompressor, audioCompressorDB))
					matchCount++;
				if(matchStrings(genreDB, genre))
					matchCount++;
				if(matchStrings(album, albumDB))
					matchCount++;
				if(matchStrings(channels, channelsDB))
					matchCount++;
				if(matchStrings(trackNumberDB, trackNumber))
					matchCount++;
				if(matchStrings(version, versionDB))
					matchCount++;
				if(matchStrings(logComment, logCommentDB))
					matchCount++;
				if(matchStrings(creatorDB, creator))
					matchCount++;
				if(matchStrings(authorDB, author))
					matchCount++;
				if(matchStrings(sampleRate, sampleRateDB))
					matchCount++;
				if(matchStrings(releaseDate, releaseDateDB))
					matchCount++;
				if(matchStrings(audioChannelType, audioChannelTypeDB))
					matchCount++;
				if(matchStrings(title, titleDB));
					matchCount++;
				if(matchCount >= 11){
					path = rs.getString("songpath");
					break;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return path;
	}
	
	private boolean matchStrings(String a, String b){
		if(a.equals(null) && b.equals(null))
			return true;
		if(a.equals(null))
			return false;
		if(b.equals(null))
			return false;
		if(a.equals(b))
			return true;
		else
			return false;
	}
}
