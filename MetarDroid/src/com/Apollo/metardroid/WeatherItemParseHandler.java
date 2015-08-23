package com.Apollo.metardroid;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * ]] SAX tag handler
 * 
 * @author ApoolloTonKosmo
 * 
 */
public class WeatherItemParseHandler extends DefaultHandler {

	private List<WeatherItem> WeatherItems;

	// Used to reference item while parsing
	private WeatherItem currentItem;

	// Parsing title indicator
	private boolean parsingTitle;
	// A buffer used to build current title being parsed
	private StringBuffer currentTitleSb;

	// Parsing link indicator
	private boolean parsingLink;
	private StringBuffer currentLinkSb;

	private boolean parsingdescription;
	private StringBuffer currentDescSb;

	private boolean parsingdiamerisma;
	private StringBuffer currentDiamSb;

	private boolean parsingMetar;

	private boolean parsingsinthikes;

	private boolean parsingfire;

	private boolean parsingtemp;
	private StringBuffer currenttempsb;

	private boolean parsinglowOutsideTemp;
	private StringBuffer currentlowOutsideTempsb;

	private boolean parsinghiOutsideTemp;
	private StringBuffer currenthiOutsideTempsb;

	private boolean parsingheatindex;
	private StringBuffer currentheatindexsb;

	private boolean parsingwindchill;
	private StringBuffer currentwindchillsb;

	private boolean parsingoutsideDewPt;
	private StringBuffer currentoutsideDewPtsb;

	private boolean parsingoutsideHumidity;
	private StringBuffer currentoutsideHumiditysb;

	private boolean parsingdailyRain;
	private StringBuffer currentdailyRainsb;

	private boolean parsingwindSpeed;
	private StringBuffer currentwindSpeedsb;

	private boolean parsingbeaufort;
	private StringBuffer currentbeaufortsb;

	private boolean parsingwindDirection;
	private StringBuffer currentwindDirectionsb;

	private boolean parsingwindDirection_TIP;
	private StringBuffer currentwindDirection_TIPsb;

	private boolean parsingelevation;
	private StringBuffer currentelevationsb;

	private boolean parsingcamera;

	private boolean parsingglat;

	private boolean parsingglon;

	private boolean parsingpubDate;
	private StringBuffer currentpubDatesb;

	public WeatherItemParseHandler() {
		WeatherItems = new ArrayList<WeatherItem>();
	}

	public List<WeatherItem> getItems() {
		return WeatherItems;
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if ("item".equals(qName)) {
			currentItem = new WeatherItem();
		} else if ("title".equals(qName)) {
			parsingTitle = true;
			currentTitleSb = new StringBuffer();
		} else if ("link".equals(qName)) {
			parsingLink = true;
			currentLinkSb = new StringBuffer();

		} else if ("description".equals(qName)) {
			parsingdescription = true;
			currentDescSb = new StringBuffer();
		} else if ("diamerisma".equals(qName)) {
			parsingdiamerisma = true;
			currentDiamSb = new StringBuffer();
		} else if ("metar".equals(qName)) {
			parsingMetar = true;

		} else if ("sinthikes".equals(qName)) {
			parsingsinthikes = true;

		} else if ("fire".equals(qName)) {
			parsingfire = true;

		} else if ("temp".equals(qName)) {
			parsingtemp = true;
			currenttempsb = new StringBuffer();

		} else if ("lowOutsideTemp".equals(qName)) {
			parsinglowOutsideTemp = true;
			currentlowOutsideTempsb = new StringBuffer();

		} else if ("hiOutsideTemp".equals(qName)) {
			parsinghiOutsideTemp = true;
			currenthiOutsideTempsb = new StringBuffer();

		} else if ("heatindex".equals(qName)) {
			parsingheatindex = true;
			currentheatindexsb = new StringBuffer();

		} else if ("windchill".equals(qName)) {
			parsingwindchill = true;
			currentwindchillsb = new StringBuffer();

		} else if ("outsideDewPt".equals(qName)) {
			parsingoutsideDewPt = true;
			currentoutsideDewPtsb = new StringBuffer();

		} else if ("outsideHumidity".equals(qName)) {
			parsingoutsideHumidity = true;
			currentoutsideHumiditysb = new StringBuffer();

		} else if ("dailyRain".equals(qName)) {
			parsingdailyRain = true;
			currentdailyRainsb = new StringBuffer();
		} else if ("windSpeed".equals(qName)) {
			parsingwindSpeed = true;
			currentwindSpeedsb = new StringBuffer();

		} else if ("beaufort".equals(qName)) {
			parsingbeaufort = true;
			currentbeaufortsb = new StringBuffer();

		} else if ("windDirection".equals(qName)) {
			parsingwindDirection = true;
			currentwindDirectionsb = new StringBuffer();

		} else if ("windDirection_TIP".equals(qName)) {
			parsingwindDirection_TIP = true;
			currentwindDirection_TIPsb = new StringBuffer();

		} else if ("elevation".equals(qName)) {
			parsingelevation = true;
			currentelevationsb = new StringBuffer();
		} else if ("camera".equals(qName)) {
			parsingcamera = true;

		} else if ("glat".equals(qName)) {
			parsingglat = true;

		} else if ("glon".equals(qName)) {
			parsingglon = true;

		} else if ("pubDate".equals(qName)) {
			parsingpubDate = true;
			currentpubDatesb = new StringBuffer();

		}

	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if ("item".equals(qName)) {
			WeatherItems.add(currentItem);
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
			if (currentItem != null) {
				currentItem.setLink(currentLinkSb.toString());
			}
		} else if ("description".equals(qName)) {
			parsingdescription = false;
			if (currentItem != null) {
				currentItem.setDescription(currentDescSb.toString());
			}
		} else if ("diamerisma".equals(qName)) {
			parsingdiamerisma = false;
			if (currentItem != null) {
				currentItem.setDiamerisma(currentDiamSb.toString());

			}
		} else if ("metar".equals(qName)) {
			parsingMetar = false;

		} else if ("sinthikes".equals(qName)) {
			parsingsinthikes = false;

		} else if ("fire".equals(qName)) {
			parsingfire = false;

		} else if ("temp".equals(qName)) {
			parsingtemp = false;
			if (currenttempsb != null) {
				currentItem.setTemp(currenttempsb.toString());
			}
		} else if ("lowOutsideTemp".equals(qName)) {
			parsinglowOutsideTemp = false;
			if (currentlowOutsideTempsb != null) {
				currentItem.setLowoutsidetemp(currentlowOutsideTempsb
						.toString());
			}
		} else if ("hiOutsideTemp".equals(qName)) {
			parsinghiOutsideTemp = false;
			if (currenthiOutsideTempsb != null) {
				currentItem.setHighoutsidetemp(currenthiOutsideTempsb
						.toString());

			}
		} else if ("heatindex".equals(qName)) {
			parsingheatindex = false;
			if (currentheatindexsb != null) {
				currentItem.setHeatindex(currentheatindexsb.toString());
			}
		} else if ("windchill".equals(qName)) {
			parsingwindchill = false;
			if (currentwindchillsb != null) {
				currentItem.setWindchill(currentwindchillsb.toString());
			}
		} else if ("outsideDewPt".equals(qName)) {
			parsingoutsideDewPt = false;
			if (currentoutsideDewPtsb != null) {
				currentItem.setOutsidedewpt(currentoutsideDewPtsb.toString());
			}
		} else if ("outsideHumidity".equals(qName)) {
			parsingoutsideHumidity = false;
			if (currentoutsideHumiditysb != null) {
				currentItem.setOutsidehumidity(currentoutsideHumiditysb
						.toString());
			}
		} else if ("dailyRain".equals(qName)) {
			parsingdailyRain = false;
			if (currentdailyRainsb != null) {
				currentItem.setDailyrain(currentdailyRainsb.toString());
			}
		} else if ("windSpeed".equals(qName)) {
			parsingwindSpeed = false;
			if (currentwindSpeedsb != null) {
				currentItem.setWindspeed(currentwindSpeedsb.toString());
			}
		} else if ("beaufort".equals(qName)) {
			parsingbeaufort = false;
			if (currentbeaufortsb != null) {
				currentItem.setBeaufort(currentbeaufortsb.toString());
			}

		} else if ("windDirection".equals(qName)) {
			parsingwindDirection = false;
			if (currentwindDirectionsb != null) {
				currentItem.setWindDirection(currentwindDirectionsb.toString());
			}
		} else if ("windDirection_TIP".equals(qName)) {
			parsingwindDirection_TIP = false;
			if (currentwindDirection_TIPsb != null) {
				currentItem.setWindDirection_TIP(currentwindDirection_TIPsb
						.toString());
			}
		} else if ("elevation".equals(qName)) {
			parsingelevation = false;
			if (currentelevationsb != null) {
				currentItem.setElevation(currentelevationsb.toString());
			}
		} else if ("camera".equals(qName)) {
			parsingcamera = false;

		}

		else if ("glat".equals(qName)) {
			parsingglat = false;

		} else if ("glon".equals(qName)) {
			parsingglon = false;

		} else if ("pubDate".equals(qName)) {
			parsingpubDate = false;
			if (currentItem != null) {
				currentItem.setPubDate(currentpubDatesb.toString());
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
				currentLinkSb.append(new String(ch, start, length));

			}

		} else if (parsingdescription) {
			if (currentItem != null) {
				currentDescSb.append(new String(ch, start, length));

			}
		} else if (parsingdiamerisma) {
			if (currentItem != null) {
				currentDiamSb.append(new String(ch, start, length));

			}
		} else if (parsingMetar) {
			if (currentItem != null) {
				currentItem.setMetar(new String(ch, start, length));
			}
			parsingMetar = false;

		} else if (parsingsinthikes) {
			if (currentItem != null) {
				currentItem.setSinthikes(new String(ch, start, length));
			}
			parsingsinthikes = false;
		} else if (parsingfire) {
			if (currentItem != null) {
				currentItem.setFire(new String(ch, start, length));
			}

		} else if (parsingtemp) {
			if (currenttempsb != null) {
				currenttempsb.append(new String(ch, start, length));

			}
		} else if (parsinglowOutsideTemp) {
			if (currentlowOutsideTempsb != null) {

				currentlowOutsideTempsb.append(new String(ch, start, length));

			}
		} else if (parsinghiOutsideTemp) {
			if (currenthiOutsideTempsb != null) {

				currenthiOutsideTempsb.append(new String(ch, start, length));

			}
		} else if (parsingheatindex) {
			if (currentheatindexsb != null) {

				currentheatindexsb.append(new String(ch, start, length));

			}
		} else if (parsingwindchill) {
			if (currentwindchillsb != null) {

				currentwindchillsb.append(new String(ch, start, length));

			}
		} else if (parsingoutsideDewPt) {
			if (currentoutsideDewPtsb != null) {

				currentoutsideDewPtsb.append(new String(ch, start, length));

			}
		} else if (parsingoutsideHumidity) {
			if (currentoutsideHumiditysb != null) {

				currentoutsideHumiditysb.append(new String(ch, start, length));

			}
		} else if (parsingdailyRain) {
			if (currentdailyRainsb != null) {

				currentdailyRainsb.append(new String(ch, start, length));

			}
		} else if (parsingwindSpeed) {
			if (currentwindSpeedsb != null) {

				currentwindSpeedsb.append(new String(ch, start, length));

			}
		} else if (parsingbeaufort) {
			if (currentbeaufortsb != null) {

				currentbeaufortsb.append(new String(ch, start, length));

			}
		}

		else if (parsingwindDirection) {
			if (currentwindDirectionsb != null) {

				currentwindDirectionsb.append(new String(ch, start, length));

			}
		} else if (parsingwindDirection_TIP) {
			if (currentwindDirection_TIPsb != null) {

				currentwindDirection_TIPsb
						.append(new String(ch, start, length));

			}
		}

		else if (parsingelevation) {
			if (currentelevationsb != null) {

				currentelevationsb.append(new String(ch, start, length));

			}
		} else if (parsingcamera) {
			if (currentItem != null) {
				currentItem.setCamera(new String(ch, start, length));
			}
			parsingcamera = false;

		} else if (parsingglat) {
			if (currentItem != null) {
				currentItem.setGlat(new String(ch, start, length));
			}
			parsingglat = false;

		} else if (parsingglon) {
			if (currentItem != null) {
				currentItem.setGlon(new String(ch, start, length));
			}
			parsingglon = false;

		} else if (parsingpubDate) {
			if (currentpubDatesb != null) {
				currentpubDatesb.append(new String(ch, start, length));
			}

		}
	}

}
