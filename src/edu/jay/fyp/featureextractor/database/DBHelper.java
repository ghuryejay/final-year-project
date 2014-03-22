package edu.jay.fyp.featureextractor.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper {

	private Connection connect = null;
	private static final String url = "jdbc:mysql://localhost:3306/fyp";
	private static final String user = "root";
	public Connection getConnection() throws ClassNotFoundException
	{
		Class.forName("com.mysql.jdbc.Driver");
		 try {
			connect = DriverManager.getConnection(url, user,"");
			return connect;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
