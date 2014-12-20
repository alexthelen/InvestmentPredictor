package InvestmentPredictor.DataLayer;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.json.XML;

public class XMLFile 
{
	// Attributes -----------------------------------------------------
	String _xmlFileName;
	File _xmlFile;
	DocumentBuilderFactory dbf;
	DocumentBuilder db;
	Document xmlDoc;
	
	// Constructors ---------------------------------------------------
	public XMLFile(String xmlFile)
	{
		this._xmlFileName = xmlFile;
		this.dbf = DocumentBuilderFactory.newInstance();
	}
	
	// Getters & Setters ----------------------------------------------
	public String GetXmlFileName() { return this._xmlFileName; }
	public void SetXmlFileName(String value) { this._xmlFileName = value; }
	
	// Public Methods -------------------------------------------------
	public JSONObject ReadFile(String[] rootElements)
	{
		this._xmlFile = new File(this._xmlFileName);		
		TransformerFactory transerformerFactory = TransformerFactory.newInstance();
		Transformer transformer;
		StringWriter writer = new StringWriter();
		String xmlString = "";
		JSONObject result = null;
		
		try 
		{			
			this.db = dbf.newDocumentBuilder();
			this.xmlDoc = db.parse(this._xmlFile);
			
			transformer = transerformerFactory.newTransformer();
			transformer.transform(new DOMSource(this.xmlDoc), new StreamResult(writer));
			
			xmlString = writer.getBuffer().toString().replace("\n|\r", "");		
			result = XML.toJSONObject(xmlString).getJSONObject("schema");
			
		} 
		catch (ParserConfigurationException | SAXException | IOException | JSONException | TransformerException e) 
		{
			e.printStackTrace();
		}
		
		return result;
	}
	
	// Private Methods ------------------------------------------------

}
