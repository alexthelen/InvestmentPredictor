package InvestmentPredictor.NeuralNetwork;



public interface INeuralNetwork 
{
	public IResult GetResult();
	public Iterable<INeuron> EvaluateNeurons();
	public void EvolveNetwork();
	public void DeleteNeuron(INeuron neuron);
}
