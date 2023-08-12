package Repository;

import java.sql.Connection;
import java.sql.DriverManager;

import Config.DBConstant;

public class DBConnection {
	public static Connection Getconnection(){ // create connection from JDBC to MySQL
		Connection connection = null;
		try {
			connection= DriverManager.getConnection(DBConstant.url,DBConstant.user,DBConstant.password);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return connection;
	}
}
