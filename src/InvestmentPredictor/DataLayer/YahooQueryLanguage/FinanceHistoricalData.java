package InvestmentPredictor.DataLayer.YahooQueryLanguage;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.json.JSONException;
import org.json.JSONObject;

public class FinanceHistoricalData 
{
	// Attributes -----------------------------------------------------
	private String _symbol;
	private Calendar _date;
	private BigDecimal _openPrice;
	private BigDecimal _highPrice;
	private BigDecimal _lowPrice;
	private BigDecimal _closePrice;
	private int _volume;
	private BigDecimal _adjClose;
		
	// Constructors ---------------------------------------------------
	public FinanceHistoricalData(String symbol, Calendar date, BigDecimal openPrice, BigDecimal highPrice, BigDecimal lowPrice, BigDecimal closePrice, int volume, BigDecimal adjClose)
	{
		this._symbol = symbol;
		this._date = date;
		this._openPrice = openPrice;
		this._highPrice = highPrice;
		this._lowPrice = lowPrice;
		this._closePrice = closePrice;
		this._volume = volume;
		this._adjClose = adjClose;
	}
	
	public FinanceHistoricalData(JSONObject jsonObject) throws JSONException
	{			
		this._symbol = jsonObject.getString("Symbol");
		
		String stringDate = jsonObject.getString("Date");
		int year = Integer.parseInt(stringDate.substring(0, 4));
		int month = Integer.parseInt(stringDate.substring(5, 7)) - 1;
		int day = Integer.parseInt(stringDate.substring(8, 10));
		this._date =  new GregorianCalendar(year, month, day);;
		
		this._openPrice = new BigDecimal(jsonObject.getString("Open"));;
		this._highPrice = new BigDecimal(jsonObject.getString("High"));;
		this._lowPrice = new BigDecimal(jsonObject.getString("Low"));;
		this._closePrice = new BigDecimal(jsonObject.getString("Close"));;
		this._volume = Integer.parseInt(jsonObject.getString("Volume"));;
		this._adjClose = new BigDecimal(jsonObject.getString("Adj_Close"));;
	}
		
	// Getters & Setters ---------------------------------------------- 
	public String GetSymbol() { return this._symbol; }
	public Calendar GetDate() { return this._date; }
	public BigDecimal GetOpenPrice() { return this._openPrice; }
	public BigDecimal GetHighPrice() { return this._highPrice; }
	public BigDecimal GetLowPrice() { return this._lowPrice; }
	public BigDecimal GetClosePrice() { return this._closePrice; }
	public int GetVolume() { return this._volume; }
	public BigDecimal GetAdjClose() { return this._adjClose; }
	
	// Public Methods -------------------------------------------------
	
		
	// Private Methods ------------------------------------------------
}
