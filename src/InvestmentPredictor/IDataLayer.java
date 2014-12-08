package InvestmentPredictor;

public interface IDataLayer 
{
	public Iterable<INeuron> GetNeurons();
	public Iterable<ISecurity> GetSecurities();
	public void SaveResult(IResult data);
}
