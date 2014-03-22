package edu.jay.fyp.featureextractor.database;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import edu.jay.fyp.featureextractor.image.NaiveSimilarityFinder;

public class ImageVideoMatch {

	private Connection connection;
	
	public Map<String,String> getVideos(String imagePath){
		
		Map<String,String> names = new ConcurrentHashMap<String,String>();
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		List<Future<Runnable>> futures = new ArrayList<Future<Runnable>>();
		try {
			connection = new DBHelper().getConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			PreparedStatement ps = connection.prepareStatement("select * from imagevideo");
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				String videopath = rs.getString(2);
				String videoName = rs.getString(1);
				File file = new File(videopath);
				String ppath = file.getParent();
				CheckingTask ctask = new CheckingTask(ppath, imagePath, names, videoName, videopath);
				Future<?> f = executorService.submit(ctask);
				futures.add((Future<Runnable>) f);
				executorService.execute(ctask);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (Future<Runnable> f : futures)
	      {
	         try {
				f.get();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
	      }

		return names;	
	}
	
	private boolean isClose(String path, String imagepath){
		
		NaiveSimilarityFinder nsm = new NaiveSimilarityFinder(path);
		try {
			if(nsm.isClose(imagepath))
				return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	private class CheckingTask implements Runnable{

		private final String dirpath, imagepath;
		private Map<String, String> names;
		private final String videoname, videopath;
		
		public CheckingTask(String dirpath, String imagepath,  Map<String, String> names, String videoname, String videopath){
			this.dirpath = dirpath;
			this.imagepath = imagepath;
			this.names = names;
			this.videoname = videoname;
			this.videopath = videopath;
		}
		@Override
		public void run() {
			if(isClose(dirpath, imagepath))
				names.put(videoname, videopath);
				
		}
		
	}

}
