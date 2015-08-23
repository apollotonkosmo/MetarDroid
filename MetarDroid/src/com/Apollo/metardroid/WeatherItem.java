package com.Apollo.metardroid;

import java.io.Serializable;

/**
 * This code encapsulates Weather station item data.
 * 
 * 
 * @author ApolloTonKosmo
 * 
 */

public class WeatherItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */

	// item title
	private String title;
	// item link
	private String link;
	// item description
	private String description;
	// diamerisma
	private String diamerisma;
	// metar
	private String metar;
	// sinthikes
	private String sinthikes;
	// fire
	private String fire;
	// temp
	private String temp;
	// lowoutside temp
	private String lowoutsidetemp;
	// highoutsidetemp
	private String highoutsidetemp;
	// heatindex
	private String heatindex;
	// windchill
	private String windchill;
	// ousidedewpt
	private String outsidedewpt;
	// outsidehumidity
	private String outsidehumidity;
	// dailyrain
	private String dailyrain;
	// windspeed
	private String windspeed;
	// beaufort
	private String beaufort;
	// windDirection
	private String windDirection;
	// windDirection_TIP
	private String windDirection_TIP;
	// elevation
	private String elevation;
	// camera
	private String camera;
	// glat
	private String glat;
	// glon
	private String glon;
	// publish date
	private String pubDate;

	public String getPubDate() {
		return pubDate;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getDiamerisma() {
		return diamerisma;
	}

	public void setDiamerisma(String diamerisma) {
		this.diamerisma = diamerisma;
	}

	public String getMetar() {
		return metar;
	}

	public void setMetar(String metar) {
		this.metar = metar;
	}

	public String getSinthikes() {
		return sinthikes;
	}

	public void setSinthikes(String sinthikes) {
		this.sinthikes = sinthikes;
	}

	public String getFire() {
		return fire;
	}

	public void setFire(String fire) {
		this.fire = fire;
	}

	public String getTemp() {
		return temp;
	}

	public void setTemp(String temp) {
		this.temp = temp;
	}

	public String getLowoutsidetemp() {
		return lowoutsidetemp;
	}

	public void setLowoutsidetemp(String lowoutsidetemp) {
		this.lowoutsidetemp = lowoutsidetemp;
	}

	public String getHighoutsidetemp() {
		return highoutsidetemp;
	}

	public void setHighoutsidetemp(String highoutsidetemp) {
		this.highoutsidetemp = highoutsidetemp;
	}

	public String getHeatindex() {
		return heatindex;
	}

	public void setHeatindex(String heatindex) {
		this.heatindex = heatindex;
	}

	public String getWindchill() {
		return windchill;
	}

	public void setWindchill(String windchill) {
		this.windchill = windchill;
	}

	public String getOutsidedewpt() {
		return outsidedewpt;
	}

	public void setOutsidedewpt(String outsidedewpt) {
		this.outsidedewpt = outsidedewpt;
	}

	public String getOutsidehumidity() {
		return outsidehumidity;
	}

	public void setOutsidehumidity(String outsidehumidity) {
		this.outsidehumidity = outsidehumidity;
	}

	public String getDailyrain() {
		return dailyrain;
	}

	public void setDailyrain(String dailyrain) {
		this.dailyrain = dailyrain;
	}

	public String getWindspeed() {
		return windspeed;
	}

	public void setWindspeed(String windspeed) {
		this.windspeed = windspeed;
	}

	public String getBeaufort() {
		return beaufort;
	}

	public void setBeaufort(String beaufort) {
		this.beaufort = beaufort;
	}

	public String getWindDirection() {
		return windDirection;
	}

	public void setWindDirection(String windDirection) {
		this.windDirection = windDirection;
	}

	public String getWindDirection_TIP() {
		return windDirection_TIP;
	}

	public void setWindDirection_TIP(String windDirection_TIP) {
		this.windDirection_TIP = windDirection_TIP;
	}

	public String getElevation() {
		return elevation;
	}

	public void setElevation(String elevation) {
		this.elevation = elevation;
	}

	public String getCamera() {
		return camera;
	}

	public void setCamera(String camera) {
		this.camera = camera;
	}

	public String getGlat() {
		return glat;
	}

	public void setGlat(String glat) {
		this.glat = glat;
	}

	public String getGlon() {
		return glon;
	}

	public void setGlon(String glon) {
		this.glon = glon;
	}

	@Override
	public String toString() {
		return title;
	}
}
