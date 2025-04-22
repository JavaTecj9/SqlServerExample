package com.mcnz.jdbc.sqlserver;

// Import HikariCP, a fast connection pooling library used to manage database connections
import com.zaxxer.hikari.HikariDataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Import SQL-related libraries
import javax.sql.DataSource;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLOutput;

@SpringBootApplication
public class SqlServerExample {

	// This is the JDBC connection string for connecting to your local SQL Server instance
	// Replace TODO with your actual database name
	static final String JDBC_URL = "jdbc:sqlserver://localhost:1433;database=TODO;encrypt=false;user=sa;password=Godislove9104$";

	public static void main(String[] args) throws SQLException {
		// Runs when the program starts

		// First, make sure the TASK table exists
		initializeTable();

		// Add two sample task records into the table
		createOperation("Shop for groceries");
		createOperation("Clean gutters");

		// Read and print out all records from the TASK table
		retrieveOperation();

		// Delete all records in the table
		deleteAll();
	}

	// Deletes all records from the TASK table
	private static void deleteAll() throws SQLException {
		String deleteAllSql = "delete from TASK"; // SQL command
		var statement = getDataSource().getConnection().createStatement(); // Get a connection and create a statement
		statement.execute(deleteAllSql); // Run the delete command
		System.out.println("Deleted !!"); // Print confirmation
	}

	// Updates a specific task in the TASK table (not used in main, but available)
	private static void updateOperation() throws SQLException {
		// SQL with a placeholder (?) for the new name
		String updateSql = "update TASK set name = ? where name = 'Learn to Read'";

		// Prepare the SQL statement with a parameter
		var preparedStatement = getDataSource().getConnection().prepareStatement(updateSql);

		// Set the parameter value (replacing '?') to "Learn JDBC"
		preparedStatement.setString(1, "Learn JDBC");

		// Execute the update command
		preparedStatement.execute();
		System.out.println("Record updated!!");
	}

	// Retrieves all tasks from the TASK table and prints them out
	private static void retrieveOperation() throws SQLException {
		var selectAllSql = "select * from TASK"; // SQL to get all rows
		var statement = getDataSource().getConnection().createStatement(); // Create a statement
		var resultSet = statement.executeQuery(selectAllSql); // Execute the query

		// Loop through the results
		while (resultSet.next()) {
			// Print the value from the "name" column
			System.out.println("To Do Item: " + resultSet.getString("name"));
		}
		System.out.println("Result Set Processed!");
	}

	// Inserts a new task into the TASK table
	private static void createOperation(String task) throws SQLException {
		String insertSql = "insert into Task (name) values (?)"; // SQL with a placeholder
		var preparedStatement = getDataSource().getConnection().prepareStatement(insertSql); // Prepare statement
		preparedStatement.setString(1, task); // Set the task string in the placeholder
		preparedStatement.execute(); // Run the insert command
		System.out.println("Record created !!");
	}

	// Creates the TASK table if it doesn't already exist
	private static void initializeTable() throws SQLException {
		// SQL checks for existence of the table before creating it
		String createTableSql =
				"IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='TASK' AND xtype='U') " +
						"BEGIN " +
						"CREATE TABLE TASK (id INT NOT NULL IDENTITY(1,1) PRIMARY KEY, name VARCHAR(50)) " +
						"END";

		// Get a connection and create a statement
		var connection = getDataSource().getConnection();
		var statement = connection.createStatement();
		statement.execute(createTableSql); // Run the table creation command
		System.out.println("Table has been created!!");
	}

	// Creates and configures a HikariCP DataSource with the connection string
	public static DataSource getDataSource() {
		var dataSource = new HikariDataSource(); // Create a new Hikari DataSource
		dataSource.setJdbcUrl(JDBC_URL); // Set the JDBC URL
		return dataSource; // Return the configured DataSource
	}
}
