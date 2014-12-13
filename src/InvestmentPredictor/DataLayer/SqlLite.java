package InvestmentPredictor.DataLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONArray;

public class SqlLite
{
	// Attributes -----------------------------------------------------
	Connection connection;
	String sqlLiteConnectionString = "jdbc:sqlite:";
	Statement statement;
	
	// Constructors ---------------------------------------------------
	public SqlLite(String dbName)
	{
		this.sqlLiteConnectionString = this.sqlLiteConnectionString.concat(dbName);
	}
	
	// Getters & Setters ----------------------------------------------
	
	// Public Methods -------------------------------------------------
	public void OpenConnection()
	{
		try 
		{
			connection = DriverManager.getConnection(this.sqlLiteConnectionString);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void CloseConnection()
	{
		try 
		{
			this.statement.close();
			this.connection.close();
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void ExecuteCreateTable(String tableName, String[] columns)
	{
		String createStatement = String.format("Create Table %s (", tableName);
		
		for(int i = 0; i < columns.length; i++)
			createStatement = createStatement.concat(columns[i]).concat(", ");
		
		createStatement = createStatement.substring(0, createStatement.length() - 2);
		createStatement = createStatement.concat(");");
		
		try
		{
			this.statement = this.connection.createStatement();
			this.statement.executeUpdate(createStatement);
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void ExecuteDelete(String tableName, String whereClause)
	{
		String deleteStatement = String.format("Delete From %s where %s;", tableName, whereClause);	
		try
		{
			this.statement = this.connection.createStatement();
			this.statement.executeUpdate(deleteStatement);
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}	
	}
	
	public void ExecuteInsert(String tableName, String[] columns, String[] values)
	{
		String insertStatement = "Insert Into %s (%s) Values (%s);";
		String columnString = "";
		String valueString = "";
		
		for(int i = 0; i < columns.length; i++)
		{
			columnString = columnString.concat(columns[i]);
			valueString = valueString.concat(values[i]);
		}
		
		insertStatement = String.format(insertStatement, columnString, valueString);
		
		try 
		{
			this.statement = this.connection.createStatement();
			this.statement.executeUpdate(insertStatement);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}	
	}
	
	public void ExecuteUpdate(String tableName, String[] setValues, String whereClause)
	{
		String updateStatement = String.format("Update %s Set %s Where %s;", tableName, setValues, whereClause);
		String setValuesString = "";
		
		for(int i = 0; i < setValues.length; i++)
			setValuesString = setValuesString.concat(setValues[i]);
		
		updateStatement = String.format(updateStatement, setValuesString, whereClause);
		
		try 
		{
			this.statement = this.connection.createStatement();
			this.statement.executeUpdate(updateStatement);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public JSONArray ExecuteSelect(String tableName, String[] columns)
	{
		return this.ExecuteSelect(tableName, columns, null);
	}
	
	public JSONArray ExecuteSelect(String tableName, String[] columns, String whereClause)
	{
		JSONArray results = new JSONArray();
		String selectStatement = "Select %s From %s";
		String selectColumns = "";
		
		for(int i = 0; i < columns.length; i++)
			selectColumns = selectColumns.concat(columns[i]);
		
		selectStatement = String.format(selectStatement, selectColumns, tableName);
		
		if(whereClause != null && whereClause.trim() != "")
			selectStatement = selectStatement.concat(String.format("Where %s;", whereClause));
		
		try
		{
			this.statement = this.connection.createStatement();
			results = (JSONArray) this.statement.executeQuery(selectStatement);
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return results;
	}
	
	// Private Methods ------------------------------------------------
}
