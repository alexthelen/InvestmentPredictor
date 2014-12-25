package InvestmentPredictor.NeuralNetwork;

import java.util.Calendar;
import java.util.Date;

public interface IResult 
{
	public String GetFundID();
	public double GetEstimation();
	public double GetActualValue();
	public Calendar GetEstimationDate();
	void SetEstimation(double estimation);
	void SetActualValue(double value);
}
