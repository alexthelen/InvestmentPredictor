package InvestmentPredictor;

import java.util.Date;

public interface IResult 
{
	public int GetFundID();
	public Date GetGenerationDate();
	public double GetEstimation();
	public double GetActualValue();
	
	void SetEstimation(double estimation);
	void SetActualValue(double value);
}
