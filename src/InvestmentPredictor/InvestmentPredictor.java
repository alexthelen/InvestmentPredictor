package InvestmentPredictor;

import InvestmentPredictor.NeuralNetwork.BasicNeuralNetwork;
import InvestmentPredictor.DataLayer.BasicDataLayer;

public class InvestmentPredictor 
{

	public static void main(String[] args) 
	{
		BasicDataLayer data = new BasicDataLayer("..\\src\\InvestmentPredictor\\DataLayer\\investmentPredictor.xml", "InvestmentPredictor.db");
		BasicNeuralNetwork network = new BasicNeuralNetwork();
		
		//if the db file doesn't exist call data.BuildDatabase
		
		//Use data to get the funds
		//Use data to get the stored neurons for each fund and give them to network
		//Use data to collect external data and feed it to network
		
		//Evaluate neuron performance from previous day
		//Evolve network
		
		//Get network results
		//Use data to save results
	}
}
