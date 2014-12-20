package InvestmentPredictor.DataLayer;

import InvestmentPredictor.NeuralNetwork.INeuron;
import InvestmentPredictor.NeuralNetwork.IResult;

public interface IDataLayer 
{
	public Iterable<INeuron> GetNeurons(String identifier);
	public Iterable<String> GetWeightCategories();
	public Iterable<String> GetFunds();
	public void SaveResult(IResult data);
}
