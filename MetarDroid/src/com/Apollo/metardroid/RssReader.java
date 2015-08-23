package com.Apollo.metardroid;

import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import com.Apollo.metardroid.RssItem;

/**
 * Class reads RSS data.
 * 
 * @author Apollotonkosmo
 *
 */
public class RssReader {
	
	private String rssUrl;

	/**
	 * Constructor
	 * 
	 * @param rssUrl
	 */
	public RssReader(String rssUrl) {
		this.rssUrl = rssUrl;
	}

	/**
	 * Get RSS items.
	 * 
	 * @return
	 */
	public List<RssItem> getItems() throws Exception {
		// SAX parse RSS data
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser = factory.newSAXParser();

		RssParseHandler handler = new RssParseHandler();
		
		saxParser.parse(rssUrl, handler);

		return handler.getItems();
		
	}

	
	public List<WeatherItem> getStations() throws Exception {
		// SAX parse RSS data
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser = factory.newSAXParser();

		WeatherItemParseHandler handler = new WeatherItemParseHandler();
		
		saxParser.parse(rssUrl, handler);

		return handler.getItems();
		
	}
}
