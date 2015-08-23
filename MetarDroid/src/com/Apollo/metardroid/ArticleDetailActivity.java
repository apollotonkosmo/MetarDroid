package com.Apollo.metardroid;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings.ZoomDensity;
import android.webkit.WebView;

public class ArticleDetailActivity extends Activity {
	private RssItem mItem;
	private ActionBar mActionBar;
	private WebView mWvArticle;
	private String mHtml;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_web);
		mItem = (RssItem) this.getIntent().getExtras().get("result");
		// actionbar
		mActionBar = getActionBar();
		mActionBar.setTitle(mItem.getTitle());
		mActionBar.setHomeButtonEnabled(true);
		mActionBar.setDisplayHomeAsUpEnabled(true);
		// webview
		mWvArticle = (WebView) findViewById(R.id.wvArticle);
		mWvArticle.getSettings().setDefaultZoom(ZoomDensity.FAR);
		mWvArticle.setVerticalScrollBarEnabled(false);
		mWvArticle.setHorizontalScrollBarEnabled(false);
		mWvArticle.getSettings().setJavaScriptEnabled(false);
		
		Document doc = Jsoup.parse(mItem.getDescription());
		
		String url = doc.select("iframe").attr("src");
		
		// fixing videos
		url=url.replaceAll("http://www.metar.gr///", "http://");
		
		
	

		StringBuilder sb = new StringBuilder();

		sb.append("<html>");
		sb.append("<head>");
		sb.append("<style type=\"text/css\">");
		sb.append(".p {width:90%;float:left;}");

		sb.append("</style>");
		sb.append("</head>");
		sb.append("<body style='background:#EEEEEE;'>");
		sb.append("<table >");

		sb.append("<tr>");
		sb.append("<td width=\"70%\"  valign=\"top\" colspan=\"2\">");
		sb.append("<strong>");
		sb.append("<span style=\"\">Σύνταξη άρθρου:"
				+ mItem.getAuthor().toString() + "</span>");
		sb.append("</strong>");
		sb.append("</td>");
		sb.append("</tr>");

		sb.append("<tr>");
		sb.append("<td valign=\"top\" colspan=\"2\" class=\"createdate\">"
				+ mItem.getPubDate());
		sb.append("</td>");
		sb.append("</tr>");
		sb.append("</table>");
		
		sb.append(mItem.getDescription());
		if(url!=null&&url!=""){
		sb.append("<center>");
		sb.append("<a href=\"" +url +"\"> Βίντεο</a>");
		sb.append("</center>");
		}
		sb.append("</body>");
		sb.append("</html>");
		mHtml = sb.toString();
		// fixing widths
		
		
		mHtml = mHtml.replaceAll("<iframe .+>", "");
		
		mHtml = mHtml
				.replaceAll("width=\"[0-9]+\"", "width=\"" + "100%" + "\"");
		mHtml = mHtml.replaceAll("height=\"[0-9]+\"", "heit=\"\"");
		
		
		// Document doc= Jsoup.parse(mHtml);

		mWvArticle.loadDataWithBaseURL(null, mHtml, "text/html", "utf-8", null);
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.article_detail, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem menuItem) {
		switch (menuItem.getItemId()) {
		case android.R.id.home:
			this.finish();

			return true;
	
		case (R.id.action_weather_share):
			// todo

			Intent intent = new Intent(Intent.ACTION_SEND);
			intent.setType("text/html");

			intent.putExtra(Intent.EXTRA_SUBJECT,
					"Metar.gr - " + mItem.getTitle());
			StringBuilder sb = new StringBuilder();
			
			sb.append(mItem.getLink());
			
			String s_email = sb.toString();
			intent.putExtra(Intent.EXTRA_TEXT, Html.fromHtml(s_email));

			startActivity(Intent.createChooser(intent, "Send Email"));
			return true;

		}
		return (super.onOptionsItemSelected(menuItem));
	}

}