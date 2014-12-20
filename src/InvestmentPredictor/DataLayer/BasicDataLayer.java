package InvestmentPredictor.DataLayer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import InvestmentPredictor.DataLayer.YahooQueryLanguage.YahooFinance;
import InvestmentPredictor.NeuralNetwork.INeuron;
import InvestmentPredictor.NeuralNetwork.IResult;
import InvestmentPredictor.NeuralNetwork.Neuron;

public class BasicDataLayer implements IDataLayer 
{
	// Attributes -----------------------------------------------------
	private YahooFinance yahooFinance;
	private XMLFile xmlFile;
	private SqlLite sqlLite;
	private MicrosoftSqlServer microsoftSqlServer;
	private JSONObject xmlFileData;
	
	// Constructors ---------------------------------------------------
	public BasicDataLayer(String xmlFileLocation, String sqliteDbName)
	{
		this.xmlFile = new XMLFile(xmlFileLocation);
		this.xmlFileData = this.xmlFile.ReadFile(new String[] { "Funds", "Securities" });
		
		this.sqlLite = new SqlLite(sqliteDbName);
	}
	
	// Public Methods -------------------------------------------------
	@Override
	public Iterable<INeuron> GetNeurons(String identifier) 
	{
		ArrayList<INeuron> results = new ArrayList<INeuron>();
		String[] columns = new String[] { "SerializedNeuron" };
		String where = String.format("FundTicker = '%s'", identifier);
		JSONArray queryResults = this.sqlLite.ExecuteSelect("Neuron", columns, where);
		byte[] rawData;
		INeuron neuron;
		
		try 
		{
			for(int i = 0; i < queryResults.length(); i++)
			{
				rawData = (byte[])queryResults.get(i);
				neuron = (INeuron)deserialize(rawData);
				results.add(neuron);
			}
		} 
		catch (ClassNotFoundException | IOException | JSONException e) 
		{
			e.printStackTrace();
		}
		
		return results;
	}

	@Override
	public void SaveResult(IResult data) 
	{
		// TODO Auto-generated method stub
		// Use microsoftSqlServer
	}

	@Override
	public Iterable<String> GetWeightCategories() 
	{
		// TODO Auto-generated method stub
		// Use XMLFile
		return null;
	}

	@Override
	public Iterable<String> GetFunds() 
	{
		ArrayList<String> results = new ArrayList<String>();
		JSONObject fundObject;
		JSONArray fundArray;
		
		try 
		{
			fundObject = this.xmlFileData.getJSONObject("Funds");
			fundArray = fundObject.getJSONArray("Fund");
			
			for(int i = 0; i < fundArray.length(); i++)
				results.add(fundArray.getJSONObject(i).getString("Ticker"));

		} 
		catch (JSONException e) 
		{
			e.printStackTrace();
		}
			
		return results;
	}
	
	
	public void BuildSqliteDatabase()
	{
		String tableName;
		String[] tableColumns;
		
		this.sqlLite.OpenConnection();

		tableName = "Neuron";
		tableColumns = new String[] { "FundTicker", "SerializedNeuron" };
		this.sqlLite.ExecuteCreateTable(tableName, tableColumns);
		
		this.sqlLite.CloseConnection();
	}
	
	// Private Methods ------------------------------------------------
	private Object deserialize(byte[] bytes) throws ClassNotFoundException, IOException
	{
		InputStream stream = new ByteArrayInputStream(bytes);
		ObjectInput serialStream = new ObjectInputStream(stream);
		return serialStream.readObject();
	}
	
	private byte[] serialize(Serializable obj) throws IOException
	{
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		ObjectOutput serialStream = new ObjectOutputStream(stream);
		serialStream.writeObject(obj);
		return stream.toByteArray();
	}
}
