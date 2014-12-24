package InvestmentPredictor.NeuralNetwork;

public class Weight implements IWeight 
{
	// Attributes -----------------------------------------------------
	private String _name;
	private double _value;
	private double _weightValue;
	
	// Constructors ---------------------------------------------------
	public Weight(String name, double value, double weightValue)
	{
		this._name = name;
		this._value = value;
		this._weightValue = weightValue;
	}
	
	// Getters & Setters -----------------------------------------------
	@Override
	public String GetName() { return this._name; }

	@Override
	public double GetValue() { return this._value; }
	
	@Override
	public double GetWeightValue() { return this._weightValue; }
	
	// 
	@Override
	public void SetValue(double value) { this._value = value; }
}