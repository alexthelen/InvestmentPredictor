package InvestmentPredictor;

public class InvestmentPredictor 
{

	public static void main(String[] args) 
	{
		Controller controller = new Controller();
		
		//if command line arg has -a
		controller.ArchiveData();
		
		//if command line arg has -b
		controller.BuildDatabase();
		
		//if command line arg has -p
		controller.ProcessNeuralNetworks();
	}
}
