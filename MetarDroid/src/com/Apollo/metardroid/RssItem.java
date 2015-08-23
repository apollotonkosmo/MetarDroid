package com.Apollo.metardroid;

import java.io.Serializable;

/**
 * This code encapsulates RSS item data.
 * Our application needs title and link data.
 * 
 * @author Apoollotonkosmo
 *
 */
public class RssItem implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// item title
	private String title;
	// item link
	private String link;
	// item description
	private String description;
	//author
	private String author;
	private String pubDate;
	
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

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
	
	@Override
	public String toString() {
		return title;
	}
	
}
