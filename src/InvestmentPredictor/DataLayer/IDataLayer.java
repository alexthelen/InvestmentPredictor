package InvestmentPredictor.DataLayer;

import java.util.Calendar;

import InvestmentPredictor.NeuralNetwork.INeuron;
import InvestmentPredictor.NeuralNetwork.IResult;

public interface IDataLayer 
{
	public Iterable<INeuron> GetNeurons(String fundTicker, Calendar date);
	public void SaveNeurons(String fundTicker, Calendar date, Iterable<INeuron> neurons);
	public Iterable<String> GetWeightCategories();
	public Iterable<String> GetFunds();
	public void SaveResult(IResult data);
}
