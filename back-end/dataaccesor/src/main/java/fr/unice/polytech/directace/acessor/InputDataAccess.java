package fr.unice.polytech.directace.acessor;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import java.sql.*;
import java.util.Properties;


/**
 * OutputDataAccess class
 * Interface to retrieve sensor values from the message queue
 */
public class InputDataAccess {

	private Connection connection;


	/**
	 * Create a new connection to the database
	 */
	public InputDataAccess() throws Exception {
		Properties properties = new Properties();
		properties.load(getClass().getClassLoader().getResourceAsStream("database.properties"));

		String connectionStr = "jdbc:postgresql://" + properties.get("hostname") + ":" + properties.get("port")
				+ "/" + properties.get("dbname");

		connection = DriverManager.getConnection(
				connectionStr,
				(String) properties.get("username"),
				(String) properties.get("password"));
	}


	/**
	 * Save a sensor data into the database
	 *
	 * @param name The sensor name
	 * @param time The data date
	 * @param value The data value
	 */
	//TODO: Handle database errors and restore the message in the message queue to avoid loosing messages
	public MatchLog getLastMatchLog (String eventName) {

		String sql = "SELECT * FROM \"public\".\"MatchLog\" WHERE event='"+eventName+"' ORDER BY time_ms DESC LIMIT 1";
        MatchLog matchLog=new MatchLog();
		try {
			PreparedStatement ps = connection.prepareStatement(sql);

            ResultSet queryRes = ps.executeQuery();
            if(queryRes.next()){
                matchLog.setId(queryRes.getString(1));
                matchLog.setEventName(queryRes.getString(2));
                matchLog.setDate(queryRes.getString(3));
                matchLog.setValue(queryRes.getString(4));
                matchLog.setPlayerID(queryRes.getString(5));
                matchLog.setMatchID(queryRes.getString(6));
            }


		} catch (SQLException exc) {
			exc.printStackTrace();
			
		}
        return matchLog;
	}
}
