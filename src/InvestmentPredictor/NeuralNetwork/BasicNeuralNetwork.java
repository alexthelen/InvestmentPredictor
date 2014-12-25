package InvestmentPredictor.NeuralNetwork;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import InvestmentPredictor.DataLayer.IDataLayer;

public class BasicNeuralNetwork implements INeuralNetwork 
{
	// Attributes -----------------------------------------------------
	private String _fundTicker;
	private ArrayList<INeuron> _neuronList;
	
	// Constructors ---------------------------------------------------
	public BasicNeuralNetwork(String fundTicker, ArrayList<INeuron> neuronList)
	{
		this._fundTicker = fundTicker;
		this._neuronList = neuronList;
	}
	
	// Getters & Setters -----------------------------------------------
	@Override
	public IResult GetResult(BigDecimal fundPrice) 
	{
		double estimation = 0;
		int topNeuronIndex = (int)Math.round(this._neuronList.size() * 0.2);
		
		for(INeuron neuron : this._neuronList.subList(0, topNeuronIndex))
			estimation += neuron.Process(fundPrice);
			
		estimation /= topNeuronIndex;
		return new Result(this.GetFundTicker(), new GregorianCalendar(), estimation);
	}
	
	public String GetFundTicker() { return this._fundTicker; }
	public void SetFundTicker(String fundTicker) { this._fundTicker = fundTicker; }
	
	// Public Methods --------------------------------------------------
	@Override
	public Iterable<INeuron> EvaluateNeurons() 
	{
		if(this._neuronList != null && this._neuronList.size() > 1)
		{
			int lowerIndex = 0;
			int higherIndex = this._neuronList.size() - 1;
			this.QuickSort(lowerIndex, higherIndex);
		}
		return this._neuronList;
	}

	@Override
	public void EvolveNetwork()
	{
		int topIndex = (int) Math.round(this._neuronList.size() * 0.1);
		int bottomIndex = (int) Math.round(this._neuronList.size() * 0.9);
		int nextId;
			
		for(int i = bottomIndex; i < this._neuronList.size(); i++)
			this.DeleteNeuron(this._neuronList.get(i));
				
		for(int i = 0; i < topIndex; i++)
			this._neuronList.add(this._neuronList.get(i).BirthChild(this.GetNextAvaibaleId()));
	}

	@Override
	public void DeleteNeuron(INeuron neuron) 
	{
		this._neuronList.remove(neuron);
		neuron = null;
	}

	// Private Methods ------------------------------------------------
	private void QuickSort(int lowerIndex, int higherIndex) 
	{
        
        int i = lowerIndex;
        int j = higherIndex;
        int pivot = this._neuronList.get(lowerIndex + (higherIndex - lowerIndex) / 2).GetRating();
        
        while (i <= j) 
        {
            while (this._neuronList.get(i).GetRating() < pivot) 
                i++;
            
            while (this._neuronList.get(j).GetRating() > pivot)
                j--;
            
            if (i <= j) 
            {
            	this.ExchangeNeurons(i, j);
                i++;
                j--;
            }
        }

        if (lowerIndex < j)
            this.QuickSort(lowerIndex, j);
        
        if (i < higherIndex)
            this.QuickSort(i, higherIndex);
    }
	
	private void ExchangeNeurons(int i, int j) 
	{
        INeuron temp = this._neuronList.get(i);
        this._neuronList.set(i, this._neuronList.get(j));
        this._neuronList.set(j, temp);
    }
	
	private int GetNextAvaibaleId()
	{	
		for(int i = 0; i < this._neuronList.size(); i++)
		{
			for(int j = 0; j < this._neuronList.size(); j++)
			{
				if(i == this._neuronList.get(j).GetNeuronId())
					break;
				else if (j == this._neuronList.size() -1)
					return i;
			}
		}
		
		return this._neuronList.size();
	}
}
