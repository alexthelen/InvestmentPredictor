package InvestmentPredictor;

import java.io.Serializable;
import java.util.Date;

public interface INeuron extends Serializable
{
	public double Process();
	public double Evaluate();
	public void Update(int rating);
	public Date GetBirthDate();
}
