package InvestmentPredictor;

public interface INeuralNetwork 
{
	public IResult GetResult();
	public Iterable<INeuron> EvaluateNeurons();
	public INeuron GenerateNeuron();
	public void DeleteNeuron(INeuron neuron);
}
