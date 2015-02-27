package InvestmentPredictor.NeuralNetwork;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.Random;

import org.apache.commons.lang3.SerializationUtils;

public class Neuron implements INeuron
{
	private static final long serialVersionUID = -7582020411926949338L;
	// Attributes -----------------------------------------------------
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
		BigDecimal priceAdjustment = new BigDecimal(0);
		
		for(IWeight weight : this.GetWeightList())
		{
			priceAdjustment = priceAdjustment.add(weight.GetValue().multiply(weight.GetWeightValue()));
		}
		
		this.processResult = fundPrice.add(priceAdjustment).doubleValue();
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
		Random randomGenerator = new Random();
		IWeight childWeight;
		int percentMutations = randomGenerator.nextInt(5);
		int totalMutations = (int)Math.round(percentMutations * 0.01 * this.GetWeightList().size());
		int mutateIndex;
		IWeight mutateWeight;
		double mutateWeightValue;
		double weightChange;
		
		for(int i = 0; i < this.GetWeightList().size(); i++)
		{
			childWeight = SerializationUtils.clone(this.GetWeightList().get(i));	
			child.AddWeight(childWeight);
		}
		
		for(int i = 0; i < totalMutations; i++)
		{
			mutateIndex = randomGenerator.nextInt(this.GetWeightList().size());
			weightChange = randomGenerator.nextDouble();
			
			if(randomGenerator.nextBoolean())
				weightChange *= -1;
			
			weightChange *= 0.01;
			mutateWeight = child.GetWeightList().get(mutateIndex);
			mutateWeightValue = mutateWeight.GetWeightValue().doubleValue() + weightChange;
			mutateWeight.SetWeightValue(new BigDecimal(mutateWeightValue));
		}
		
		return child;
	}
	
	public void AddWeight(IWeight weight) { this.GetWeightList().add(weight); }
	
	public <K, V> void UpdateWeightData(Hashtable<K, V> newData)
	{
		IWeight weight;
		BigDecimal newValue;
		
		for(int i = 0; i < this.GetWeightList().size(); i++)
		{
			weight = this.GetWeightList().get(i);
			
			if(newData.containsKey(weight.GetName()))
			{
				newValue = (BigDecimal) newData.get(weight.GetName());
				weight.SetValue(newValue);
			}
			else
				weight.SetValue(null);
		}
	}
	
	// Private Methods ------------------------------------------------
}
