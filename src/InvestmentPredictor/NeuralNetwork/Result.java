package InvestmentPredictor.NeuralNetwork;

import java.util.Calendar;
import java.util.Date;

public class Result implements IResult 
{
	// Attributes -----------------------------------------------------
	String _fundId;
	Calendar _estimationDate;
	double _estimation;
	double _actualValue;
	
	// Constructors ---------------------------------------------------
	public Result(String fundId, Calendar estimationDate, double estimation)
	{
		this._fundId = fundId;
		this._estimationDate = estimationDate;
		this._estimation = estimation;
	}
	
	// Getters & Setters -----------------------------------------------
	@Override
	public String GetFundID() { return this._fundId; }

	@Override
	public double GetEstimation() { return this._estimation; }

	@Override
	public double GetActualValue() { return this._actualValue; }

	@Override
	public void SetEstimation(double estimation) { this._estimation = estimation; }

	@Override
	public void SetActualValue(double value) { this._actualValue = value; }
	
	@Override
	public Calendar GetEstimationDate() { return this._estimationDate; }

	// Public Methods -------------------------------------------------
	
	// Private Methods ------------------------------------------------
	
}
