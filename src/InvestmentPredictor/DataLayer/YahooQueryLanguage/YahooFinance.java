package InvestmentPredictor.DataLayer.YahooQueryLanguage;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;
import org.json.JSONObject;

public class YahooFinance
{
	// Attributes -----------------------------------------------------
	private String baseUrl = "https://query.yahooapis.com/v1/public/yql?q=";
	private String fullUrlString;
	private URL fullUrl;
	private InputStream inStream;
	private JSONTokener tokener;
	
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	private String yqlFinanceHistData = "select * from yahoo.finance.historicaldata";
	
	private Calendar _startDate;
	private Calendar _endDate;
	
	// Constructors ---------------------------------------------------
	public YahooFinance()
	{
		this._startDate = new GregorianCalendar();
		this._endDate = new GregorianCalendar();
	}
	
	public YahooFinance(Calendar startDate, Calendar endDate)
	{
		this._startDate = startDate;
		this._endDate = endDate;
	}
	
	// Getters & Setters ---------------------------------------------- 
	void SetStartDate(Calendar value){ this._startDate = value; }
	void SetEndDate(Calendar value){this._endDate = value; }
	
	// Public Methods -------------------------------------------------
	public Iterable<FinanceHistoricalData> GetFinanceHistoricalData(Iterable<String> securityTickers)
	{
		ArrayList<FinanceHistoricalData> results = new ArrayList<FinanceHistoricalData>();
		String finalQuery = this.BuildYqlQuery(securityTickers);
		JSONObject yqlResult = this.RetrieveData(finalQuery);
		JSONArray quotes;
		JSONObject currentQuote;
		
		String symbol, stringDate;		
		Calendar date;
		int year, month, day, volume;	
		BigDecimal openPrice, highPrice, lowPrice, closePrice, adjClose;
		
		try 
		{
			quotes = yqlResult.getJSONObject("query").getJSONObject("results").getJSONArray("quote");
			
			for(int i = 0; i < quotes.length(); i++)
			{
				currentQuote = quotes.getJSONObject(i);
				symbol = currentQuote.getString("Symbol");
				
				stringDate = currentQuote.getString("Date");
				year = Integer.parseInt(stringDate.substring(0, 4));
				month = Integer.parseInt(stringDate.substring(5, 7));
				day = Integer.parseInt(stringDate.substring(8, 10));
				date = new GregorianCalendar(year, month, day);
				
				openPrice = new BigDecimal(currentQuote.getString("Open"));
				highPrice = new BigDecimal(currentQuote.getString("High"));
				lowPrice = new BigDecimal(currentQuote.getString("Low"));
				closePrice = new BigDecimal(currentQuote.getString("Close"));
				volume = Integer.parseInt(currentQuote.getString("Volume"));
				adjClose = new BigDecimal(currentQuote.getString("Adj_Close"));
				results.add(new FinanceHistoricalData(symbol, date, openPrice, highPrice, lowPrice, closePrice, volume, adjClose));
			}
		} 
		catch (JSONException e) 
		{
			e.printStackTrace();
		}
			
		return results;
	}
	
	// Private Methods ------------------------------------------------
	private JSONObject RetrieveData(String finalQuery) 
	{	
		JSONObject result = null;
		try 
		{
			this.fullUrlString = this.baseUrl + URLEncoder.encode(finalQuery, "UTF-8") + "&format=json&env=store://datatables.org/alltableswithkeys";
			this.fullUrl = new URL(this.fullUrlString);
			this.inStream = this.fullUrl.openStream();
			this.tokener = new JSONTokener(this.inStream);
			result = new JSONObject(this.tokener);
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
		
		return result;
	}
	
	private String BuildYqlQuery(Iterable<String> securityTickers)
	{
		String whereClause = String.format(" where startDate = '%s' and endDate = '%s' and (", df.format(this._startDate.getTime()), df.format(this._endDate.getTime()));
		
		for(String ticker : securityTickers)
		{
			whereClause = whereClause.concat(String.format("symbol = '%s' or ", ticker));
		}
		
		whereClause = whereClause.substring(0, whereClause.length() - 4);
		whereClause = whereClause.concat(")");
		return this.yqlFinanceHistData.concat(whereClause);
	}
}
