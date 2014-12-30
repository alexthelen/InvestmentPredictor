package InvestmentPredictor.NeuralNetwork;

import java.io.Serializable;

public interface IWeight extends Serializable
{
	public String GetName();
	public double GetValue();
	public double GetWeightValue();
	public void SetValue(double value);
	public void SetWeightValue(double weightValue);
}
