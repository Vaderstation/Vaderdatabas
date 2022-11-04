package Java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {
    private static final String jdbcUsername = "hbg13";
	private static final String jdbcPassword = "pjd666zk";	
	private static final String jdbcString = "jdbc:mysql://puccini.cs.lth.se/hbg13";
	
	private Connection conn = null;

	public void connect() {
			try {
				conn = DriverManager.getConnection (jdbcString, jdbcUsername, jdbcPassword);
			}
			catch (SQLException e) {
				System.err.println(e);
				e.printStackTrace();
			}
		}

		public String getMeasureValue() {
			return "{}";
		}
	
		public String getMeasureTime() {
			return "{}";
		}


		// Hämtar Sensor_ID från databasen
		public ResultSet getSensorID() {
			String query = "SELECT Sensor_ID FROM Sensor";
			
			ResultSet resultSet = null;
			PreparedStatement statement = null;

			try {
				statement = conn.prepareStatement(query);
				resultSet = statement.executeQuery();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return resultSet;
		}
		
		// Hämtar ESP_ID från databasen
		public ResultSet getEspID() {

			String query = "SELECT ESP_ID FROM Card";
			
			ResultSet resultSet = null;
			PreparedStatement statement = null;

			try {
				statement = conn.prepareStatement(query);
				resultSet = statement.executeQuery();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return resultSet;
		}

	
		public String getLocationID() {
			return "{}";
		}
	
		public String displayName() {
			return "{}";
		}

	

}
