package InvestmentPredictor.NeuralNetwork;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public interface INeuron extends Serializable
{
	public String GetIdentifier();
	public double Process(BigDecimal fundPrice);
	public double Evaluate();
	public void Update(int rating);
	public Date GetBirthDate();
}
