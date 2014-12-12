package InvestmentPredictor.DataLayer;

import java.util.ArrayList;

import InvestmentPredictor.IDataLayer;
import InvestmentPredictor.INeuron;
import InvestmentPredictor.IResult;
import InvestmentPredictor.DataLayer.YahooQueryLanguage.YahooFinance;

public class BasicDataLayer implements IDataLayer 
{

	private YahooFinance yahooFinance;
	private XMLFile xmlFile = new XMLFile();
	private SqlLite sqlLite = new SqlLite();
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
