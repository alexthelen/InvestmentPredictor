package InvestmentPredictor.NeuralNetwork;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Hashtable;

public interface INeuron extends Serializable
{	
	public int GetRating();
	public void SetRating(int rating);
	
	public int GetNeuronId();
	public void SetNeuronId(int id);
	
	public Date GetBirthDate();
	
	public String GetFundTicker();
	
	public INeuron BirthChild(int id);
	public double Process(BigDecimal fundPrice);
	public double Evaluate(double actualValue);
	public <K, V> void UpdateWeightData(Hashtable<K, V> newData);
}
