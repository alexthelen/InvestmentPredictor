package InvestmentPredictor.NeuralNetwork;

import java.util.Date;

import InvestmentPredictor.IResult;

public class Result implements IResult {

	@Override
	public int GetFundID() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Date GetGenerationDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double GetEstimation() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double GetActualValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void SetEstimation(double estimation) {
		// TODO Auto-generated method stub

	}

	@Override
	public void SetActualValue(double value) {
		// TODO Auto-generated method stub

	}

}
