package InvestmentPredictor;

public interface IDataLayer 
{
	public Iterable<INeuron> GetNeurons();
	public Iterable<ISecurity> GetSecurities();
	public Iterable<String> GetWeightCategories();
	public void SaveResult(IResult data);
}
