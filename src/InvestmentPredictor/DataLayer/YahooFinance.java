package InvestmentPredictor.DataLayer;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONTokener;
import org.json.JSONObject;

import InvestmentPredictor.IDataElement;

class YahooFinance implements IDataSource 
{
	private String baseUrl = "http://query.yahooapis.com/v1/public/yql?q=";
	private String fullUrlString;
	private URL fullUrl;
	private InputStream inStream;
	private JSONTokener tokener;
	private JSONObject result;
	
	private String yqlfinanceHistData = "select * from yahoo.finance.historicaldata where symbol = 'YHOO' and startDate = '2009-09-11' and endDate = '2010-03-10'";
	
	private Date startDate = new Date();
	private Date endDate = new Date();
	
	YahooFinance()
	{
		
	}
	
	void SetStartDate(Date value){ this.startDate = value; }
	void SetEndDate(Date value){this.endDate = value; }
	
	@Override
	public void RetrieveData() 
	{
		String finalQuery = this.BuildYqlQuery();
		
		try 
		{
			this.fullUrlString = this.baseUrl + URLEncoder.encode(finalQuery, "UTF-8");
			this.fullUrl = new URL(this.fullUrlString);
			this.inStream = this.fullUrl.openStream();
			this.tokener = new JSONTokener(this.inStream);
			this.result = new JSONObject(this.tokener);
		} 
		catch (IOException | JSONException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			try 
			{ 
				this.inStream.close(); 
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	}

	@Override
	public Iterable<IDataElement> GetData() 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void SaveData(Iterable<IDataElement> data) 
	{
		// TODO Auto-generated method stub

	}
	
	private String BuildYqlQuery()
	{
		return null;
	}
}
