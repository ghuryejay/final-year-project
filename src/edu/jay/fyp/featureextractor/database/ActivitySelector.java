package edu.jay.fyp.featureextractor.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ActivitySelector {

	private Connection connection;

	public List<String> getVideos(String activity) {
		DBHelper dbHelper = new DBHelper();
		List<String> paths = new ArrayList<String>();
		try {
			connection = dbHelper.getConnection();
			String sql = "select videopath from videos where activity like '%"+ activity +"%'";
			PreparedStatement ps = connection
					.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				paths.add(rs.getString(1));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return paths;
	}

}
