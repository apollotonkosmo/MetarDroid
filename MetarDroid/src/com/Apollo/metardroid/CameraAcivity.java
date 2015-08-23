package com.Apollo.metardroid;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings.ZoomDensity;
import android.webkit.WebView;

public class CameraAcivity extends Activity {

	private WeatherItem mItem;
	private android.app.ActionBar mActionBar;
	private String mHtml;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mItem = (WeatherItem) this.getIntent().getExtras().get("result");
		mActionBar = getActionBar();
		mActionBar.setHomeButtonEnabled(true);
		mActionBar.setDisplayHomeAsUpEnabled(true);
		mActionBar.setTitle(mItem.getTitle());

		setContentView(R.layout.activity_camera);
		WebView wv = (WebView)findViewById(R.id.wv_camera);
		wv.getSettings().setDefaultZoom(ZoomDensity.FAR);
		wv.setVerticalScrollBarEnabled(false);
		wv.setHorizontalScrollBarEnabled(false);
		wv.setBackgroundColor(Color.BLACK);
		wv.clearCache(true);
		StringBuilder sb = new StringBuilder();
		sb.append("<html>");
		sb.append("<body style='background:black;'>");
		sb.append("<center> <img src=" + "\""
				+ mItem.getCamera().toString() + "\"" + "width='100%'" + "/>"
				+ "<br></center>");

		sb.append("</body>");
		sb.append("</html>");
		mHtml = sb.toString();

		wv.getSettings().setDefaultTextEncodingName("UTF-8");
		wv.loadDataWithBaseURL(null, mHtml, "text/html", "utf-8", null);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.weather_detail, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem menuItem) {
		switch (menuItem.getItemId()) {
		case android.R.id.home:
			this.finish();
			return true;
//		case (R.id.action_weather_fav):
//		
//			return true;
		case (R.id.action_weather_share):
			

			Intent intent = new Intent(Intent.ACTION_SEND);
			intent.setType("text/html");

			intent.putExtra(
					Intent.EXTRA_SUBJECT,
					"Metar.gr - " + mItem.getTitle() + " - "
							+ mItem.getDescription() + " - "
							+ mItem.getDiamerisma());
			StringBuilder sb = new StringBuilder();
			sb.append("<body>");
			sb.append("Πληροφορίες σχετικά με τον σταθμό:");
			sb.append("<br>");
			sb.append("Σταθμός:" + mItem.getDescription());
			sb.append("<br>");
			sb.append("Θερμοκρασία:" + mItem.getTemp());
			sb.append("<br>");
			sb.append("Υγρασία:" + mItem.getOutsidehumidity());
			sb.append("<br>");
			sb.append("Μποφόρ:" + mItem.getBeaufort());
			sb.append("<br>");
			sb.append("Ταχύτητα Ανέμου:" + mItem.getWindspeed());
			sb.append("<br>");
			sb.append("Ηλ. Διεύθυνση:" + mItem.getLink());
			sb.append("<br>");
			sb.append("Camera" + mItem.getCamera());
			sb.append("<br>");
			sb.append("</body>");
			String s_email = sb.toString();
			intent.putExtra(Intent.EXTRA_TEXT, Html.fromHtml(s_email));

			startActivity(Intent.createChooser(intent, "Send Email"));
			return true;

		}
		return (super.onOptionsItemSelected(menuItem));
	}

	

}
