package InvestmentPredictor.NeuralNetwork;

import java.io.Serializable;
import java.util.Date;

public interface INeuron extends Serializable
{
	public String GetIdentifier();
	public double Process();
	public double Evaluate();
	public void Update(int rating);
	public Date GetBirthDate();
}
