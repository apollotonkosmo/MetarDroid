package com.Apollo.metardroid;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
/**
 * Class implements a list listener
 * 
 * @author ApolloTonkosmo
 * 
 */
public class ListListener implements OnItemClickListener {

	// List item's reference
	List<RssItem> listItems;
	
	// Calling activity reference
	Activity activity;

	public ListListener(List<RssItem> aListItems, Activity anActivity) {
		listItems = aListItems;
		activity = anActivity;
	}

	/**
	 * Start a browser with url from the rss item. Update - Starts a webview
	 * instead.
	 */
	public void onItemClick(AdapterView<?> parent, View view, final int pos,
			long id) {
		
		
		
		
		Intent ArticleDetail = new Intent(activity, ArticleDetailActivity.class);
		activity.startActivity(ArticleDetail);
		
		

		// starts a dialog box that hold a webview that load the article's link
			
//		final Dialog dialog = new Dialog(this.activity);
//		// disables dialog title.
//		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//		dialog.setContentView(com.Apollo.metardroid.R.layout.activity_web);
//		WebView wv = (WebView) dialog
//				.findViewById(com.Apollo.metardroid.R.id.wvArticle);
//		wv.getSettings().setDefaultZoom(ZoomDensity.FAR);
//		wv.setVerticalScrollBarEnabled(false);
//		wv.setHorizontalScrollBarEnabled(false);
//		int intWidth = wv.getWidth();
//
//		StringBuilder sb = new StringBuilder();
//		sb.append("<html>");
//		sb.append("<body>");
//		sb.append(listItems.get(pos).getDescription());
//		sb.append("</body>");
//		sb.append("</html>");
//		String html = sb.toString();
//		html= html.replaceAll("width=\"[0-9]+\"",
//				"width=\"" +  "500" + "\"");
//		
//		
//	
//		
//		
//		
//		
//		wv.loadDataWithBaseURL(null,html, "text/html", "utf-8",null);
//		
//		
//		TextView txtdesc = (TextView) dialog
//				.findViewById(com.Apollo.metardroid.R.id.txtTitle);
//		txtdesc.setText(listItems.get(pos).getTitle());
//		// Button share listener
//		Button btnShare = (Button) dialog
//				.findViewById(com.Apollo.metardroid.R.id.btnShare);
//
//		btnShare.setOnClickListener(new OnClickListener() {
//
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//
//				Intent i = new Intent(Intent.ACTION_SEND);
//
//				i.putExtra(android.content.Intent.EXTRA_TEXT, listItems
//						.get(pos).getLink());
//				i.putExtra(Intent.EXTRA_SUBJECT, listItems.get(pos).getTitle());
//				i.setType("text/html");
//				activity.startActivity(Intent.createChooser(i, "Send email..."));
//			}
//		});
//		// Button close listener
//		Button btnclose = (Button) dialog
//				.findViewById(com.Apollo.metardroid.R.id.btnclose);
//
//		btnclose.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				dialog.cancel();
//			}
//		});
//
//		dialog.show();
	}

}
