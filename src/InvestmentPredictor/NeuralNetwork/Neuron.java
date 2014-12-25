package InvestmentPredictor.NeuralNetwork;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import com.rits.cloning.Cloner;

public class Neuron implements INeuron
{
	// Attributes -----------------------------------------------------
	private static final long serialVersionUID = 1L; // TODO Figure out what this should actually be for Java serialization
	private Date _birthDate;
	private ArrayList<IWeight> _weightList;
	private double processResult;
	private int _rating;
	private String _fundTicker;
	private int _neuronId;
	
	// Constructors ---------------------------------------------------
	public Neuron(String fundTicker, int neuronId, Date birthDate, ArrayList<IWeight> weightList)
	{
		this._fundTicker = fundTicker;
		this._neuronId = neuronId;
		this._birthDate = birthDate;
		this._weightList = weightList;
	}
	
	// Getters & Setters -----------------------------------------------	
	@Override
	public Date GetBirthDate() { return this._birthDate; }
	
	@Override
	public int GetRating(){ return this._rating; }
	
	@Override
	public void SetRating(int rating) { this._rating = rating; }
	
	@Override
	public String GetFundTicker() { return this._fundTicker; }
	
	@Override
	public int GetNeuronId() { return this._neuronId; }
	
	@Override
	public void SetNeuronId(int id) { this._neuronId = id; }
	
	public ArrayList<IWeight> GetWeightList() { return this._weightList; }
	
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

	@Override
	public INeuron BirthChild(int id)
	{
		Neuron child = new Neuron(this.GetFundTicker(), id, new Date(), new ArrayList<IWeight>());
		Cloner cloner = new Cloner();
		Random randomGenerator = new Random();
		IWeight childWeight;
		int percentMutations = randomGenerator.nextInt(5);
		int totalMutations = (int)Math.round(percentMutations * 0.01 * this._weightList.size());
		int mutateIndex;
		IWeight mutateWeight;
		double mutateWeightValue;
		double weightChange;
		
		for(int i = 0; i < this._weightList.size(); i++)
		{
			childWeight = cloner.deepClone(this._weightList.get(i));	
			child.AddWeight(childWeight);
		}
		
		for(int i = 0; i < totalMutations; i++)
		{
			mutateIndex = randomGenerator.nextInt(this._weightList.size());
			weightChange = randomGenerator.nextDouble();
			
			if(randomGenerator.nextBoolean())
				weightChange *= -1;
			
			weightChange *= 0.01;
			mutateWeight = child.GetWeightList().get(mutateIndex);
			mutateWeightValue = mutateWeight.GetWeightValue() + weightChange;
			mutateWeight.SetValue(mutateWeightValue);
		}
		
		return child;
	}
	
	public void AddWeight(IWeight weight) { this._weightList.add(weight); }
	
	// Private Methods ------------------------------------------------
}
