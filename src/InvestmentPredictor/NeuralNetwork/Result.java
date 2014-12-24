package InvestmentPredictor.NeuralNetwork;

import java.util.Date;

public class Result implements IResult 
{
	// Attributes -----------------------------------------------------
	int _fundId;
	int _neuronId;
	Date _generationDate;
	double _estimation;
	double _actualValue;
	
	// Constructors ---------------------------------------------------
	public Result(int fundId, int neuronId, Date generationDate, double estimation)
	{
		this._fundId = fundId;
		this._generationDate = generationDate;
		this._estimation = estimation;
	}
	
	// Getters & Setters -----------------------------------------------
	@Override
	public int GetFundID() { return this._fundId; }
	
	@Override
	public int GetNeuronID() { return this._neuronId; }

	@Override
	public Date GetGenerationDate() { return this._generationDate; }

	@Override
	public double GetEstimation() { return this._estimation; }

	@Override
	public double GetActualValue() { return this._actualValue; }

	@Override
	public void SetEstimation(double estimation) { this._estimation = estimation; }

	@Override
	public void SetActualValue(double value) { this._actualValue = value; }

	// Public Methods -------------------------------------------------
	
	// Private Methods ------------------------------------------------
	
}
