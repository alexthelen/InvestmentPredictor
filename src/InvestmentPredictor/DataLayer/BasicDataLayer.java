package InvestmentPredictor.DataLayer;

import java.util.ArrayList;

import InvestmentPredictor.INeuron;
import InvestmentPredictor.DataLayer.YahooQueryLanguage.YahooFinance;
import InvestmentPredictor.NeuralNetwork.IResult;

public class BasicDataLayer implements IDataLayer 
{

	private YahooFinance yahooFinance;
	private XMLFile xmlFile = new XMLFile();
	private SqlLite sqlLite = new SqlLite("investmenetPredictor.db");
	private MicrosoftSqlServer microsoftSqlServer = new MicrosoftSqlServer();
	
	public BasicDataLayer()
	{
		
	}
	
	@Override
	public Iterable<INeuron> GetNeurons() 
	{
		// TODO Auto-generated method stub
		// Use sqlLite
		return null;
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

}
