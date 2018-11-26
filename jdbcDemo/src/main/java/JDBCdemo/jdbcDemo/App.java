package JDBCdemo.jdbcDemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Hello world!
 *
 */
public class App 
{
	public static void main( String[] args ) throws SQLException
	{
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "Trainee1", "!QAZSE4");

		Statement statement = connection.createStatement();
		String query = "SELECT * FROM BROKERS";
		ResultSet resultStatement = statement.executeQuery(query);

		System.out.println(String.format("%2s | %10s | %10s", "id", "firstname", "lastname"));
		System.out.println("-------------------------------");
		while(resultStatement.next()) {
			int id = resultStatement.getInt("broker_id");
			String firstname = resultStatement.getString("first_name");
			String lastname = resultStatement.getString("last_name");

			String result = String.format("%2d | %10s | %10s", id, firstname, lastname);
			System.out.println(result);
		}
	}
}
