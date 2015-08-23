package com.Apollo.metardroid;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ArticleAdapter extends BaseAdapter {
	private Context mCon;
	private ArrayList<RssItem> mLstItems;

	public ArticleAdapter(ArrayList<RssItem> lstItems, Context con) {
		this.mCon = con;
		this.mLstItems = lstItems;
	}

	@Override
	public int getCount() {

		return mLstItems.size();
	}

	@Override
	public Object getItem(int arg0) {

		return mLstItems.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {

		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// get rss item
		RssItem item = (RssItem) getItem(arg0);
		arg1 = LayoutInflater.from(mCon).inflate(R.layout.article_cell, null);
		// initialize
		TextView txtNews = (TextView) arg1.findViewById(R.id.txtNews);
		txtNews.setText(item.getTitle());
		
		ImageView imgNews = (ImageView) arg1.findViewById(R.id.imgNews);
		String content = item.getDescription();
		content.substring(9, content.length() - 9);

		try {
			Document doc = Jsoup.parse(content);
			String url = doc.select("img").attr("src");
			Picasso.with(mCon).load(url).into(imgNews);
			imgNews.setScaleType(ScaleType.CENTER_CROP);
		} catch (Exception ex) {
			ex.getMessage().toString();
		}

		Jsoup.parse(content);

		return arg1;
	}

}
