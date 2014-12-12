package InvestmentPredictor.DataLayer.YahooQueryLanguage;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Calendar;

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
