package com.Apollo.metardroid;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.Apollo.metardroid.RssItem;

/**
 * SAX tag handler
 * 
 * @author ApolloTonkosmo
 * 
 */
public class RssParseHandler extends DefaultHandler {

	private List<RssItem> rssItems;

	// Used to reference item while parsing
	private RssItem currentItem;

	
	private boolean parsingTitle;
	private StringBuffer currentTitleSb;

	
	private boolean parsingLink;
	
	private StringBuffer currentAuthorSb;
	private boolean parsingAuthor;
	
	private StringBuffer currentPubDateSb;
	private boolean parsingPubDate;

	private boolean parsingDescription;
	private StringBuffer currentDescSb;

	public RssParseHandler() {
		rssItems = new ArrayList<RssItem>();
	}

	public List<RssItem> getItems() {
		return rssItems;
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if ("item".equals(qName)) {
			currentItem = new RssItem();
		} else if ("title".equals(qName)) {
			parsingTitle = true;
			currentTitleSb = new StringBuffer();
		} else if ("link".equals(qName)) {
			parsingLink = true;

		} else if ("description".equals(qName)) {
			parsingDescription = true;
			currentDescSb = new StringBuffer();
		}else if ("author".equals(qName)) {
			parsingAuthor = true;
			currentAuthorSb = new StringBuffer();
		}else if ("pubDate".equals(qName)) {
			parsingPubDate = true;
			currentPubDateSb = new StringBuffer();
		}
	}
	

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if ("item".equals(qName)) {
			rssItems.add(currentItem);
			currentItem = null;
		} else if ("title".equals(qName)) {
			parsingTitle = false;
			// Set item's title when we parse item->title tag not the channel
			// title tag
			if (currentItem != null) {
				// Set item's title here
				currentItem.setTitle(currentTitleSb.toString());
			}

		} else if ("link".equals(qName)) {
			parsingLink = false;
		} else if ("description".equals(qName)) {
			parsingDescription = false;
			if (currentItem != null) {
				currentItem.setDescription(currentDescSb.toString());
			}
		}else if ("author".equals(qName)) {
			parsingAuthor = false;
			if (currentItem != null) {
				currentItem.setAuthor(currentAuthorSb.toString());
			}
		}else if ("pubDate".equals(qName)) {
			parsingPubDate = false;
			if (currentItem != null) {
				currentItem.setPubDate(currentPubDateSb.toString());
			}
		}
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		if (parsingTitle) {
			if (currentItem != null) {
				// Here we append the title to the buffer due to network issues.
				// Sometimes this characters method is called multiple times for
				// a tag contents.
				currentTitleSb.append(new String(ch, start, length));
			}
		} else if (parsingLink) {
			if (currentItem != null) {
				currentItem.setLink(new String(ch, start, length));
				parsingLink = false;
			}
		} else if (parsingDescription) {
			if (currentItem != null) {
				currentDescSb.append(new String(ch, start, length));

			}
		}else if (parsingAuthor) {
			if (currentItem != null) {
				currentAuthorSb.append(new String(ch, start, length));

			}
		}else if (parsingPubDate) {
			if (currentItem != null) {
				currentPubDateSb.append(new String(ch, start, length));

			}
		}
	}

}
