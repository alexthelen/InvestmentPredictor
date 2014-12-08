package InvestmentPredictor.DataLayer;

import java.util.ArrayList;

import InvestmentPredictor.IDataLayer;
import InvestmentPredictor.INeuron;
import InvestmentPredictor.IResult;
import InvestmentPredictor.ISecurity;

public class DataLayer implements IDataLayer 
{

	ArrayList<IDataSource> dataSources;
	
	public DataLayer()
	{
		dataSources = new ArrayList<IDataSource>();
	}
	
	@Override
	public Iterable<INeuron> GetNeurons() 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<ISecurity> GetSecurities() 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void SaveResult(IResult data) 
	{
		// TODO Auto-generated method stub

	}

}
