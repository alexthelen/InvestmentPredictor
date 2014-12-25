package InvestmentPredictor.NeuralNetwork;

import java.math.BigDecimal;



public interface INeuralNetwork 
{
	public IResult GetResult(BigDecimal fundPrice);
	public Iterable<INeuron> EvaluateNeurons();
	public void EvolveNetwork();
	public void DeleteNeuron(INeuron neuron);
}
