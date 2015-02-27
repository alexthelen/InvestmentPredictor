package InvestmentPredictor.NeuralNetwork;

import java.math.BigDecimal;

public class Weight implements IWeight
{
	// Attributes -----------------------------------------------------
	private static final long serialVersionUID = -4829604365380322625L;
	private String _name;
	private BigDecimal _value;
	private BigDecimal _weightValue;
	
	// Constructors ---------------------------------------------------
	public Weight(String name, BigDecimal value, BigDecimal weightValue)
	{
		this._name = name;
		this._value = value;
		this._weightValue = weightValue;
	}
	
	// Getters & Setters -----------------------------------------------
	@Override
	public String GetName() { return this._name; }

	@Override
	public BigDecimal GetValue() { return this._value; }
	
	@Override
	public BigDecimal GetWeightValue() { return this._weightValue; }
	
	@Override
	public void SetValue(BigDecimal value) { this._value = value; }
	
	@Override
	public void SetWeightValue(BigDecimal weightValue) { this._weightValue = weightValue; }
}