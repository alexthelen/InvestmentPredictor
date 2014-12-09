package InvestmentPredictor.DataLayer;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
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
	
	private String yqlFinanceHistData = "select * from yahoo.finance.historicaldata";
	
	private Iterable<String> _securityTickers;
	private Date _startDate = new Date();
	private Date _endDate = new Date();
	
	YahooFinance(Iterable<String> securityTickers)
	{
		this._securityTickers = securityTickers;
	}
	
	YahooFinance(Iterable<String> securityTickers, Date startDate, Date endDate)
	{
		this._securityTickers = securityTickers;
		this._startDate = startDate;
		this._endDate = endDate;
	}
	
	void SetStartDate(Date value){ this._startDate = value; }
	void SetEndDate(Date value){this._endDate = value; }
	
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
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String whereClause = String.format(" where startDate = '%s' and endDate = '%s'", df.format(this._startDate), df.format(this._endDate));
		
		for(String ticker : this._securityTickers)
		{
			whereClause = whereClause.concat(String.format(" and symbol = '%s'", ticker));
		}
		
		return this.yqlFinanceHistData.concat(whereClause);
	}
}
