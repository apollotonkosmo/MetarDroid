package com.Apollo.metardroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings.ZoomDensity;
import android.webkit.WebView;

public class WeatherDetailActivity extends Activity {
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

		setContentView(R.layout.activity_weather);
	

		WebView wv = (WebView) findViewById(com.Apollo.metardroid.R.id.wvWeather);
		wv.getSettings().setDefaultZoom(ZoomDensity.FAR);
		wv.setVerticalScrollBarEnabled(false);
		wv.setHorizontalScrollBarEnabled(false);
		wv.clearCache(true);
		StringBuilder sb = new StringBuilder();
		sb.append("<html>");
		sb.append("<body style='background:#EEEEEE;'>");

		sb.append("<br> <table border='0' cellpadding='0' cellspacing='0' width='100%' bgcolor='#ECF9FF'><tr>");
		sb.append(" <td align='left' width='30%'>");
		sb.append(" <a title='Μετάβαση στην σελίδα του σταθμού' href="
				+ mItem.getLink()
				+ "><img border='0' src='http://www.metar.org/icons/home.gif' width='55' height='55'></a>");
		sb.append("  </td>");

		sb.append("<td align='center' width='30%'>" + mItem.getPubDate());

		sb.append("</td>");
		sb.append(" <td align='center' width='30%'>");
		sb.append(" <a href='#' title=''><img border='0' src='http://www.metar.org/icons/CONDITIONS/"+getweatherType().toString() +"'></a>");
		sb.append(" </td></tr></table>");
		sb.append(" </td></tr></table><br>");

		sb.append("<table border='1' bordercolordark='#333333' bordercolorlight='#333333'  cellpadding='0' cellspacing='0' width='100%' style='border-collapse: collapse' bgcolor='#ECF9FF'>");
		sb.append("<tr height='30' bgcolor='#C8E0EC'>");
		sb.append("<td align='center' width='25%'> Χώρα</td><td align='center' width='25%'>Γεωγραφικό ΔΙαμέριμσα</td><td align='center' width='25%'>Περιγραφή</td><td align='center' width='25%'>Τίτλος</td></tr>");
		sb.append("<tr height='30'><td align='center' width='25%'>Ελλάδα</td><td align='center' width='25%'>"
				+ mItem.getDiamerisma() + "</td>");
		sb.append("<td align='center' width='25%'>" + mItem.getDescription()
				+ "</td><td align='center' width='25%'>" + mItem.getTitle()
				+ "</td></tr>");
		sb.append("</table>");

		sb.append("<br>");
		sb.append("<table border='1' bordercolordark='#333333' bordercolorlight='#333333'  cellpadding='0' cellspacing='0' width='100%' style='border-collapse: collapse' bgcolor='#ECF9FF'>");
		sb.append("<tr height='30' bgcolor='#C8E0EC'>");
		sb.append("<td align='center' width='33%'> Γεωγραφικό Πλάτος </td><td align='center' width='33%'> Γεωγραφικό Μήκος </td><td align='center' width='33%'>Υψόμετρο</td></tr>");
		sb.append("<tr height='30'><td align='center' width='33%'>"
				+ ((Float) Float.parseFloat(mItem.getGlat()))
				+ "</td><td align='center' width='33%'>"
				+ Float.parseFloat(mItem.getGlon()) + "</td>");
		sb.append("<td align='center' width='33%'>" + mItem.getElevation()
				+ "</td></tr>");
		sb.append("</table>");

		sb.append("<br>");
		sb.append("<table border='1' bordercolordark='#333333' bordercolorlight='#333333'  cellpadding='0' cellspacing='0' width='100%' style='border-collapse: collapse' bgcolor='#ECF9FF'>");
		sb.append("<tr height='30' bgcolor='#C8E0EC'>");
		sb.append("<td align='center' width='33%'> Θερμοκρασία </td><td align='center' width='33%'> Μεγ. Εξ. Θερμ  </td><td align='center' width='33%'> Ελ.Εξ. Θερμ. </td></tr>");
		sb.append("<tr height='30'><td align='center' width='25%'>"
				+ mItem.getTemp() + "</td><td align='center' width='25%'>"
				+ mItem.getHighoutsidetemp() + "</td>");
		sb.append("<td align='center' width='25%'>" + mItem.getLowoutsidetemp()
				+ "</td></tr>");
		sb.append("</table>");

		sb.append("<br>");
		sb.append("<table border='1' bordercolordark='#333333' bordercolorlight='#333333'  cellpadding='0' cellspacing='0' width='100%' style='border-collapse: collapse' bgcolor='#ECF9FF'>");
		sb.append("<tr height='30' bgcolor='#C8E0EC'>");
		sb.append("<td align='center' width='33%'> Μποφόρτ </td><td align='center' width='33%'> Ταχ. Ανέμου  </td><td align='center' width='33%'> Διεύ. Ανέμου </td></tr>");
		sb.append("<tr height='30'><td align='center' width='25%'>"
				+ mItem.getBeaufort() + "</td><td align='center' width='33%'>"
				+ mItem.getWindspeed() + "</td>");
		sb.append("<td align='center' width='33%'>" + mItem.getWindDirection()
				+ "/" + mItem.getWindDirection_TIP() + "</td>" + "</tr>");
		sb.append("</table>");

		sb.append("<br>");
		sb.append("<table border='1' bordercolordark='#333333' bordercolorlight='#333333'  cellpadding='0' cellspacing='0' width='100%' style='border-collapse: collapse' bgcolor='#ECF9FF'>");
		sb.append("<tr height='30' bgcolor='#C8E0EC'>");
		sb.append("<td align='center' width='33%'> Βαρ. Χαμηλό </td><td align='center' width='33%'> Υγρασία  </td><td align='center' width='33%'> Βροχόπτωση </td></tr>");
		sb.append("<tr height='30'><td align='center' width='33%'>"
				+ mItem.getOutsidedewpt()
				+ "</td><td align='center' width='33%'>"
				+ mItem.getOutsidehumidity() + "</td>");
		sb.append("<td align='center' width='33%'>" + mItem.getDailyrain()
				+ "</td></tr>");
		sb.append("</table>");

		sb.append("<br><center> <img src=" + "\""
				+ mItem.getCamera().toString() + "\"" + "width='100%'" + "/>"
				+ "<br></center>");

		sb.append("</body>");
		sb.append("</html>");
		mHtml = sb.toString();

		wv.getSettings().setDefaultTextEncodingName("UTF-8");
		wv.loadDataWithBaseURL(null, mHtml, "text/html", "utf-8", null);

		// TextView txtTitle = (TextView)
		// findViewById(com.Apollo.metardroid.R.id.txtTitle);
		// txtTitle.setText(mListItems.get(pos).getTitle() + " - "
		// + mListItems.get(pos).getDescription() + " - "
		// + mListItems.get(pos).getDiamerisma());
	}
private String getweatherType(){
	String strWeather="";
	Float temp = Float.parseFloat(mItem.getTemp().substring(0, mItem.getElevation().length() - 2));
	if (temp<=15){
		strWeather ="cold.gif";
	}else if (temp>15){
		strWeather ="hot.gif";
	}
	
	return strWeather;
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
	    	//optional
//	    case (R.id.action_weather_fav):
//	    	//todo
//	    	return true;
	    case (R.id.action_weather_share):
	    	//todo
	    	
	    	Intent intent = new Intent(Intent.ACTION_SEND);
	    intent.setType("text/html");
	   
	    intent.putExtra(Intent.EXTRA_SUBJECT,"Metar.gr - " +mItem.getTitle() + " - " + mItem.getDescription() + " - " + mItem.getDiamerisma());
	    StringBuilder sb = new StringBuilder();
	    sb.append("<body>");
	    sb.append("Πληροφορίες σχετικά με τον σταθμό:");
	    sb.append("<br>");
	    sb.append("Σταθμός:" +mItem.getDescription());
	    sb.append("<br>");
	    sb.append("Θερμοκρασία:" +mItem.getTemp());
	    sb.append("<br>");
	    sb.append("Υγρασία:" +mItem.getOutsidehumidity());
	    sb.append("<br>");
	    sb.append("Μποφόρ:" +mItem.getBeaufort());
	    sb.append("<br>");
	    sb.append("Ταχύτητα Ανέμου:" +mItem.getWindspeed());
	    sb.append("<br>");
	    sb.append("Ηλ. Διεύθυνση:" +mItem.getLink());
	    sb.append("<br>");
	    sb.append("Camera" + mItem.getCamera());
		sb.append("<br>");
	    sb.append("</body>");
	    String s_email = sb.toString();
	    intent.putExtra(Intent.EXTRA_TEXT,Html.fromHtml(s_email) );

	    startActivity(Intent.createChooser(intent, "Send Email"));
	    	return true;
	  
	    }
	  return (super.onOptionsItemSelected(menuItem));
	}

}
