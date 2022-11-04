package Java;

import java.sql.Connection;
import java.sql.DriverManager;
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
	
		public String getSensorID() {
			return "{}";
		}
	
		public String getEspID() {
			return "{}";
		}
	
		public String getLocationID() {
			return "{}";
		}
	
		public String displayName() {
			return "{}";
		}

	

}
