package InvestmentPredictor.NeuralNetwork;

public interface IWeight 
{
	public String GetName();
	public double GetValue();
	public double GetWeightValue();
	public void SetValue(double value);
}
