package InvestmentPredictor.NeuralNetwork;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public interface INeuron extends Serializable
{
	public double Process(BigDecimal fundPrice);
	public double Evaluate(double actualValue);
	public int GetRating();
	public void SetRating(int rating);
	public Date GetBirthDate();
	public INeuron BirthChild();
}
