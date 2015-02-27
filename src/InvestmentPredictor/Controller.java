package InvestmentPredictor;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import InvestmentPredictor.DataLayer.BasicDataLayer;
import InvestmentPredictor.NeuralNetwork.BasicNeuralNetwork;
import InvestmentPredictor.NeuralNetwork.INeuralNetwork;
import InvestmentPredictor.NeuralNetwork.INeuron;
import InvestmentPredictor.NeuralNetwork.IResult;

class Controller
{
	// Attributes -----------------------------------------------------
	String xmlFilePath;
	String dbName;
	BasicDataLayer data;
		
	// Constructors ---------------------------------------------------
	Controller() 
	{ 
		this.xmlFilePath = "..\\src\\InvestmentPredictor\\DataLayer\\investmentPredictor.xml";
		this.dbName = "InvestmentPredictor.db";
		this.data = new BasicDataLayer(this.xmlFilePath, this.dbName);
	}
		
	// Getters & Setters -----------------------------------------------
	
	// Public Methods --------------------------------------------------
	void ArchiveData()
	{
		GregorianCalendar archiveDate = new GregorianCalendar();
		archiveDate.add(GregorianCalendar.DATE, -7);
		this.data.ArchiveOldNeurons(archiveDate);
	}
	
	void BuildDatabase()
	{
		File sqliteDbFile = new File(this.dbName);
		
		if(!sqliteDbFile.exists())
			this.data.BuildSqliteDatabase();
	}
	
	void ProcessNeuralNetworks()
	{
		BasicNeuralNetwork network;
		ArrayList<INeuralNetwork> networkList = new ArrayList<INeuralNetwork>();
		ArrayList<String> fundList = this.convertIterableToArrayList(this.data.GetFunds());
		ArrayList<IResult> resultList = new ArrayList<IResult>(); 
		ArrayList<INeuron> neuronList;
		BigDecimal fundPrice;
		
		for(String f : fundList)
		{
			neuronList = this.convertIterableToArrayList(this.data.GetNeurons(f, new GregorianCalendar()));
			
			//TODO: Get the fundPrice for the day foreach fund. Can use 
			//yahooFinance.GetFinanceHistoricalData or create a method in 
			//BasicDataLayer to do this.
			
			if(neuronList.isEmpty())
				this.SeedNewNetwork(f);
					
			network = new BasicNeuralNetwork(f, neuronList);
		}	
		
		for(INeuralNetwork n : networkList)
		{
			n.EvaluateNeurons();
			n.EvolveNetwork();
			//resultList.add(n.GetResult(fundPrice));
		}
		
		//Use data to save results
	}
	
	// Private Methods -------------------------------------------------
	private void SeedNewNetwork(String fundTicker)
	{
		
	}
	
	private <E> ArrayList<E> convertIterableToArrayList(Iterable<E> iterable)
	{
		ArrayList<E> result = new ArrayList<E>();
		while (iterable.iterator().hasNext()){
		    result.add(iterable.iterator().next());
		}
		return result;
	}
}
