package edu.jay.fyp.featureextractor.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import edu.jay.fyp.featureextractor.video.Videomatch;

public class StipDB {

	public String getParentFilePath(String stipPath){
		
		Videomatch videomatch = new Videomatch();
		String res = null;
		try {
			Connection connection = new DBHelper().getConnection();
			String query = "select * from videostip";
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				String parentStip = rs.getString(3);
				if(videomatch.isPartOf(stipPath, parentStip) == true){
					res = rs.getString(2);
					break;
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return res;
	}

}
