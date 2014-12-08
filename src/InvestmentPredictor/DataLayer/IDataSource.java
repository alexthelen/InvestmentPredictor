package InvestmentPredictor.DataLayer;

import InvestmentPredictor.IDataElement;

interface IDataSource 
{
	void RetrieveData();
	Iterable<IDataElement> GetData();
	void SaveData(Iterable<IDataElement> data);
}
