package InvestmentPredictor.NeuralNetwork;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

public class Neuron implements INeuron
{
	// Attributes -----------------------------------------------------
	private static final long serialVersionUID = 1L; // TODO Figure out what this should actually be for Java serialization
	private String _identifier;
	private Date _birthDate;
	private ArrayList<IWeight> _weightList;
	private double processResult;
	
	// Constructors ---------------------------------------------------
	public Neuron(ArrayList<IWeight> weightList)
	{
		this._weightList = weightList;
	}
	
	// Getters & Setters -----------------------------------------------
	@Override
	public String GetIdentifier() { return this._identifier; }
	
	@Override
	public Date GetBirthDate() { return this._birthDate; }
	
	// Public Methods --------------------------------------------------
	@Override
	public double Process(BigDecimal fundPrice) 
	{
		double priceAdjustment = 0;
		
		for(IWeight weight : this._weightList)
		{
			priceAdjustment = priceAdjustment + weight.GetValue() * weight.GetWeightValue();
		}
		
		return fundPrice.doubleValue() + priceAdjustment;
	}

	@Override
	public double Evaluate() 
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void Update(int rating) 
	{
		// TODO Auto-generated method stub

	}

	// Private Methods ------------------------------------------------
}
