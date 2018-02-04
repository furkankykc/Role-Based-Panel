package Utility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import com.ibatis.common.jdbc.ScriptRunner;

public class SqlRunner {

	public static void main(String[] args) throws ClassNotFoundException,
		SQLException, InstantiationException, IllegalAccessException {

		String roadRunnerPath = "galiPanel.sql";

		// Create MySql Connection
		 Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(
			"jdbc:mysql://localhost/", "root", "1234");
		Statement stmt = null;

		try {
			// Initialize object for ScripRunner
			ScriptRunner sr = new ScriptRunner(con, false, false);

			// Give the input file to Reader
			Reader reader = new BufferedReader(
                               new FileReader(roadRunnerPath));

			
			// Exctute script
			sr.runScript(reader);
		} catch (Exception e) {
			System.err.println("Failed to Execute" + ""
					+ " The error is " + e.getMessage());
		}
	}
}