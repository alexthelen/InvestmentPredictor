package InvestmentPredictor.NeuralNetwork;

import java.io.Serializable;
import java.math.BigDecimal;

public interface IWeight extends Serializable
{
	public String GetName();
	public BigDecimal GetValue();
	public BigDecimal GetWeightValue();
	public void SetValue(BigDecimal value);
	public void SetWeightValue(BigDecimal weightValue);
}
