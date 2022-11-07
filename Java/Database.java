package Java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import com.mysql.cj.protocol.Resultset;

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

		//string,int resultset to map
		public Map<String,Integer> resultSetToMap (ResultSet rs){
			Map<String,Integer> dataSet = new TreeMap<>();
			try {
				while(rs.next()){
					dataSet.put(rs.getString(1),rs.getInt(2));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return dataSet;
		}

		// Hämtar MeasureValue från databasen
		public ResultSet getMeasureValue(int ESP_ID, int Sensor_ID) {
			ResultSet resultSet = null;
			String query = "SELECT MeasureTime,MeasureValue FROM Measure,Sensor,Card WHERE Sensor.ESP_ID = Card.ESP_ID AND Sensor.ESP_ID = ? AND Measure.Sensor_ID=Sensor.sensor_ID AND Measure.Sensor_ID = ? ";
			PreparedStatement statement = null;
			try {
				statement = conn.prepareStatement(query);
				statement.setInt(1, ESP_ID);
				statement.setInt(2, Sensor_ID);
				resultSet = statement.executeQuery();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return resultSet;
		}

		// Hämtar MeasureTime från databasen
		public ResultSet getMeasureTime() {
			String query = "SELECT MeasureTime FROM Measure";
			
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


		// Hämtar Sensor_ID från databasen
		public ResultSet getSensorID(int ESP_ID, int Sensor_ID) {
			String query = "SELECT Sensor_ID ";
			ResultSet resultSet = null;
			

			try (PreparedStatement statement = conn.prepareStatement(query) ){
				statement.setInt(1, ESP_ID);
				statement.setInt(1, Sensor_ID);
				resultSet = statement.executeQuery();
				return resultSet;

			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}

		}
		
		// Hämtar ESP_ID från databasen
		public ResultSet getEspID() {

			String query = "SELECT * FROM Card";
			
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

		// Hämtar Location_ID från databasen
		public ResultSet getLocationID() {
			String query = "SELECT Location_ID FROM Location";
			
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
		
		// Hämtar displayName från databasen
		public ResultSet displayName() {
			String query = "SELECT Display_Name FROM Location";
			
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

	

}
