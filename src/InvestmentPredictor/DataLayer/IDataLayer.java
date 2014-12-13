package InvestmentPredictor.DataLayer;

import InvestmentPredictor.INeuron;
import InvestmentPredictor.NeuralNetwork.IResult;

public interface IDataLayer 
{
	public Iterable<INeuron> GetNeurons();
	public Iterable<String> GetWeightCategories();
	public void SaveResult(IResult data);
}
