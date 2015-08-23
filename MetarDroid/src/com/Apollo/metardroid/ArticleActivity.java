package com.Apollo.metardroid;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ProgressBar;

import com.Apollo.metardroid.GetRSSArticleDataTask.IGetRSSArticleDataTaskListener;

public class ArticleActivity extends Activity {
	Context con;
	private ArticleAdapter _madapter;
	private ArrayList<RssItem> mLstItems;
	private GridView mGrdArticles;
	private android.app.ActionBar mActionBar;
	private ProgressBar mProgressBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_article);
		this.con = getBaseContext();
		// ActionBar
		mActionBar = getActionBar();
		mActionBar.setTitle("ÍÝá");
		mActionBar.setHomeButtonEnabled(true);
		mActionBar.setDisplayHomeAsUpEnabled(true);

		// progressBar
		mProgressBar = (ProgressBar) findViewById(R.id.pb_Article);

		// Grid
		mGrdArticles = (GridView) findViewById(R.id.grd_articles);
		mGrdArticles.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent news = new Intent(con, ArticleDetailActivity.class);
				news.putExtra("result", (RssItem) _madapter.getItem(arg2));
				startActivity(news);
			}
		});

		GetRSSArticleDataTask task = new GetRSSArticleDataTask(
				new IGetRSSArticleDataTaskListener() {

					@Override
					public void onSuccesfullResponse(List<RssItem> lstRSSItems) {
						// TODO Auto-generated method stub
						mLstItems = (ArrayList<RssItem>) lstRSSItems;
						mProgressBar.setVisibility(View.GONE);
						_madapter = new ArticleAdapter(mLstItems, con);
						mGrdArticles.setAdapter(_madapter);
					}

					@Override
					public void onException(Exception ex) {
						// TODO Auto-generated method stub

					}
				});
		task.execute("http://www.metar.gr/index.php?format=feed&type=rss");

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.article_main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	public boolean onOptionsItemSelected(MenuItem menuItem) {
		// TODO Auto-generated method stub
		switch (menuItem.getItemId()) {
		case android.R.id.home:
			this.finish();
			return true;
		case R.id.action_refresh:
			onCreate(null);
			return true;

		}
		return (super.onOptionsItemSelected(menuItem));
	}
}
