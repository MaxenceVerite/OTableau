
/*
 *************************************
 ***  © Maxence Vérité 2018 **********
 *************************************
 */

package tool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectH2DB {

	public final static String URL = "jdbc:h2:file:./db/Dictionnaire";
	public final static String LOGIN = "user";
	public final static String PASS = "";
	private static Connection connect;

	static {
		try {
			Class.forName("org.h2.Driver");
			connect = DriverManager.getConnection(URL, LOGIN, PASS);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		return connect;
	}

}