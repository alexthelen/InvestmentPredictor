package InvestmentPredictor;

import java.io.File;
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
		ArrayList<String> fundList = (ArrayList<String>) this.data.GetFunds();
		ArrayList<IResult> resultList = new ArrayList<IResult>(); 
		ArrayList<INeuron> neuronList;
		
		for(String f : fundList)
		{
			neuronList = (ArrayList<INeuron>) this.data.GetNeurons(f, new GregorianCalendar());
			
			if(neuronList.isEmpty())
				this.SeedNewNetwork(f);
					
			network = new BasicNeuralNetwork(f, neuronList);
			this.UpdateNetworkWeights();
		}	
		
		for(INeuralNetwork n : networkList)
		{
			n.EvaluateNeurons();
			n.EvolveNetwork();
			//resultList.add(n.GetResult(fundPrice));
			//Get network results and store in list
		}
		
		//Use data to save results
	}
	
	// Private Methods -------------------------------------------------
	private void SeedNewNetwork(String fundTicker)
	{
		
	}
	
	private void UpdateNetworkWeights()
	{
		//Use data to collect external data and feed it to network
		//This will involve updating all the weights for each neuron
	}
}
