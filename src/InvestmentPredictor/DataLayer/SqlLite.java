package InvestmentPredictor.DataLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SqlLite
{
	// Attributes -----------------------------------------------------
	Connection connection;
	String sqlLiteConnectionString = "jdbc:sqlite:";
	
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
	
	public Boolean IsOpen()
	{
		Boolean result = false;
		try 
		{
			if(this.connection != null)
				if(!this.connection.isClosed())
					result = true;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		} 
		
		return result;
	}
	
	public void CloseConnection()
	{
		try 
		{			
			if(this.IsClosed())
				this.connection.close();
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		this.connection = null;
	}
	
	public Boolean IsClosed()
	{
		Boolean result = false;
		try 
		{
			if(this.connection == null)
				result = true;
			else if(this.connection.isClosed())
			result = true;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		} 
		
		return result;
	}
	
	public void ExecuteCreateTable(String tableName, String[] columns)
	{
		String createStatement = String.format("Create Table %s (", tableName);
		Statement statement;
		
		for(int i = 0; i < columns.length; i++)
			createStatement = createStatement.concat(columns[i]).concat(", ");
		
		createStatement = createStatement.substring(0, createStatement.length() - 2);
		createStatement = createStatement.concat(");");
		
		try
		{
			statement = this.connection.createStatement();
			statement.executeUpdate(createStatement);
			statement.close();
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void ExecuteDelete(String tableName, String whereClause)
	{
		String deleteStatement = String.format("Delete From %s where %s;", tableName, whereClause);	
		Statement statement;
		
		try
		{
			statement = this.connection.createStatement();
			statement.executeUpdate(deleteStatement);
			statement.close();
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}	
	}
	
	public void ExecuteDrop(String tableName)
	{
		String dropStatement = String.format("Drop Table If Exists %s;", tableName);	
		Statement statement;
		
		try
		{
			statement = this.connection.createStatement();
			statement.executeUpdate(dropStatement);
			statement.close();
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
		Statement statement;
		
		for(int i = 0; i < columns.length; i++)
		{
			columnString = columnString.concat(columns[i]).concat(", ");
			valueString = valueString.concat(values[i]).concat(", ");
		}
		
		columnString = columnString.substring(0, columnString.length() - 2);
		valueString = valueString.substring(0, valueString.length() - 2);
		insertStatement = String.format(insertStatement, tableName, columnString, valueString);
		
		try 
		{
			statement = this.connection.createStatement();
			statement.executeUpdate(insertStatement);
			statement.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}	
	}
	
	public void ExecuteUpdate(String tableName, String[] setValues, String whereClause)
	{
		String updateStatement = "Update %s Set %s Where %s;";
		String setValuesString = "";
		Statement statement;
		
		for(int i = 0; i < setValues.length; i++)
			setValuesString = setValuesString.concat(setValues[i]);
		
		updateStatement = String.format(updateStatement, tableName, setValuesString, whereClause);
		
		try 
		{
			statement = this.connection.createStatement();
			statement.executeUpdate(updateStatement);
			statement.close();
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
		Statement statement;
		
		for(int i = 0; i < columns.length; i++)
			selectColumns = selectColumns.concat(columns[i]).concat(", ");
		
		selectColumns = selectColumns.substring(0, selectColumns.length() - 2);
		selectStatement = String.format(selectStatement, selectColumns, tableName);
		
		if(whereClause != null && whereClause.trim() != "")
			selectStatement = selectStatement.concat(String.format(" Where %s;", whereClause));
		
		try
		{
			statement = this.connection.createStatement();
			results = this.ConvertResultSetToJSONArray(statement.executeQuery(selectStatement));
			statement.close();
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return results;
	}
	
	// Private Methods ------------------------------------------------
	private JSONArray ConvertResultSetToJSONArray(ResultSet rs) throws SQLException
	{
		JSONArray result = new JSONArray();
		int columns;
		String columnName;
		
		while(rs.next())
		{
			JSONObject record = new JSONObject();
			columns = rs.getMetaData().getColumnCount() + 1;
			
			for(int i = 1; i < columns; i++)
			{
				columnName = rs.getMetaData().getColumnName(i);
				
				try 
				{
					switch (rs.getMetaData().getColumnType(i)) 
					{
						case Types.VARCHAR:
						case Types.NVARCHAR:	
						case Types.CHAR:
						case Types.NCHAR:
							record.put(columnName, rs.getString(i));					
							break;
						case Types.BIGINT:
						case Types.INTEGER:
						case Types.SMALLINT:
						case Types.TINYINT:
							record.put(columnName, rs.getInt(i));
							break;
						case Types.DOUBLE:
						case Types.FLOAT:
							record.put(columnName, rs.getDouble(i));
							break;
						case Types.BOOLEAN:
							record.put(columnName, rs.getBoolean(i));
							break;
						case Types.DECIMAL:
							record.put(columnName, rs.getBigDecimal(i));
							break;					
						case Types.DATE:
							record.put(columnName, rs.getDate(i));
							break;
						default:
							record.put(columnName, rs.getObject(i));
							break;
					}
				} 
				catch (JSONException e) 
				{
					e.printStackTrace();
				}				
			}
			result.put(record);
		}
		
		rs.close();
		return result;
	}
}
