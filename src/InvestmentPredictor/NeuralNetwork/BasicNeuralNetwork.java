package InvestmentPredictor.NeuralNetwork;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;

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
	
	public ArrayList<INeuron> GetNeuronList() { return this._neuronList; }
	
	// Public Methods --------------------------------------------------
	@Override
	public Iterable<INeuron> EvaluateNeurons() 
	{
		// TODO: Needs to rate neurons based on actual value vs what it predicted
		
		if(this.GetNeuronList() != null && this.GetNeuronList().size() > 1)
		{
			int lowerIndex = 0;
			int higherIndex = this.GetNeuronList().size() - 1;
			this.QuickSort(lowerIndex, higherIndex);
		}
		return this.GetNeuronList();
	}

	@Override
	public void EvolveNetwork()
	{
		int topIndex = (int) Math.round(this.GetNeuronList().size() * 0.1);
		int bottomIndex = (int) Math.round(this.GetNeuronList().size() * 0.9);
			
		for(int i = bottomIndex; i < this.GetNeuronList().size(); i++)
			this.DeleteNeuron(this.GetNeuronList().get(i));
				
		for(int i = 0; i < topIndex; i++)
			this.GetNeuronList().add(this.GetNeuronList().get(i).BirthChild(this.GetNextAvaibaleId()));
	}

	@Override
	public void DeleteNeuron(INeuron neuron) 
	{
		this.GetNeuronList().remove(neuron);
		neuron = null;
	}

	
	// Private Methods ------------------------------------------------
	private void QuickSort(int lowerIndex, int higherIndex) 
	{
        
        int i = lowerIndex;
        int j = higherIndex;
        int pivot = this.GetNeuronList().get(lowerIndex + (higherIndex - lowerIndex) / 2).GetRating();
        
        while (i <= j) 
        {
            while (this.GetNeuronList().get(i).GetRating() < pivot) 
                i++;
            
            while (this.GetNeuronList().get(j).GetRating() > pivot)
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
        INeuron temp = this.GetNeuronList().get(i);
        this.GetNeuronList().set(i, this.GetNeuronList().get(j));
        this.GetNeuronList().set(j, temp);
    }
	
	private int GetNextAvaibaleId()
	{	
		for(int i = 0; i < this.GetNeuronList().size(); i++)
		{
			for(int j = 0; j < this.GetNeuronList().size(); j++)
			{
				if(i == this.GetNeuronList().get(j).GetNeuronId())
					break;
				else if (j == this.GetNeuronList().size() -1)
					return i;
			}
		}
		
		return this.GetNeuronList().size();
	}
}
