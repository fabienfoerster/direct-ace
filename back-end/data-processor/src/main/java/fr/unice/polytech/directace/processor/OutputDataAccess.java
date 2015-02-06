package fr.unice.polytech.directace.processor;

import java.sql.*;
import java.util.Properties;


/**
 * OutputDataAccess class
 * Interface to retrieve sensor values from the message queue
 */
public class OutputDataAccess {

	private Connection connection;


	/**
	 * Create a new connection to the database
	 */
	public OutputDataAccess () throws Exception {
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
	public boolean saveMatchLog (String id,String name, String time, String value, String playerID, String matchID) {

		String sql = "INSERT INTO \"public\".\"MatchLog\" (id, event, time_ms, val, player_id, match_id) VALUES (?,?,?,?,?,?)";

		int nb;
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,id);
			ps.setString(2, name);
			ps.setString(3, time);
			ps.setString(4, value);
			ps.setString(5, playerID);
			ps.setString(6, matchID);
			nb = ps.executeUpdate();
		} catch (SQLException exc) {
			exc.printStackTrace();
			return false;
		}
        return (nb == 1);
	}
}
