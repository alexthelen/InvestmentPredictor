package InvestmentPredictor.DataLayer;

import java.util.ArrayList;

import InvestmentPredictor.IDataLayer;
import InvestmentPredictor.INeuron;
import InvestmentPredictor.IResult;
import InvestmentPredictor.ISecurity;

public class BasicDataLayer implements IDataLayer 
{

	private ArrayList<IDataSource> dataSources = new ArrayList<IDataSource>();
	private YahooFinance yahooFinance;
	private XMLFile xmlFile = new XMLFile();
	private SqlLite sqlLite = new SqlLite();
	private MicrosoftSqlServer microsoftSqlServer = new MicrosoftSqlServer();
	
	public BasicDataLayer()
	{
		dataSources.add(yahooFinance);
		dataSources.add(xmlFile);
		dataSources.add(sqlLite);
		dataSources.add(microsoftSqlServer);
	}
	
	@Override
	public Iterable<INeuron> GetNeurons() 
	{
		// TODO Auto-generated method stub
		// Use sqlLite
		return null;
	}

	@Override
	public Iterable<ISecurity> GetSecurities() 
	{
		// TODO Auto-generated method stub
		// Use yahooFinance
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
