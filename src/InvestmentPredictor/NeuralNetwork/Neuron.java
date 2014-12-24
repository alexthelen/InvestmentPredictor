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
	private int _rating;
	
	// Constructors ---------------------------------------------------
	public Neuron(String identifier, Date birthDate, ArrayList<IWeight> weightList)
	{
		this._identifier = identifier;
		this._birthDate = birthDate;
		this._weightList = weightList;
	}
	
	// Getters & Setters -----------------------------------------------
	@Override
	public String GetIdentifier() { return this._identifier; }
	
	@Override
	public Date GetBirthDate() { return this._birthDate; }
	
	public void SetWeightList(ArrayList<IWeight> weightList) { this._weightList = weightList; }
	
	@Override
	public int GetRating(){ return this._rating; }
	
	@Override
	public void SetRating(int rating) { this._rating = rating; }
	
	// Public Methods --------------------------------------------------
	@Override
	public double Process(BigDecimal fundPrice) 
	{
		double priceAdjustment = 0;
		
		for(IWeight weight : this._weightList)
		{
			priceAdjustment = priceAdjustment + weight.GetValue() * weight.GetWeightValue();
		}
		
		this.processResult = fundPrice.doubleValue() + priceAdjustment;
		return this.processResult;
	}

	@Override
	public double Evaluate(double actualValue) 
	{
		double percentage = this.processResult / actualValue;
		double precentDifference = Math.abs(1 - percentage);
		return precentDifference;
	}

	// Private Methods ------------------------------------------------
}
