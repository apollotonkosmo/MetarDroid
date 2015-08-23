package com.Apollo.metardroid;

import java.util.List;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Rss URL  activity.
 
 *
 */
public class RssChannelActivity extends Activity  {
	
	// A reference to this activity
    private RssChannelActivity local;
	private String rssUrl;
	private android.app.ActionBar mActionBar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mActionBar= getActionBar();
		mActionBar.setTitle("ÍÝá");
		mActionBar.setHomeButtonEnabled(true);
		mActionBar.setDisplayHomeAsUpEnabled(true);
		setContentView(R.layout.activity_rss_channel);
		
		// Get the RSS URL that was set in the RssTabActivity
		 rssUrl = (String)getIntent().getExtras().get("rss-url");
		//rssUrl= "http://metar.gr/index.php?format=feed&type=rss";
		//rssUrl= "http://www.digitallife.gr/category/news/mobiles/feed";
		// Set reference to this activity
        local = this;         
        GetRSSDataTask task = new GetRSSDataTask();         
        // Start process RSS task
        task.execute(rssUrl);		
	}
	

	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}


	/**
	 * This class downloads and parses RSS Channel feed.
	 
	 */
	private class GetRSSDataTask extends AsyncTask<String, Void, List<RssItem> > {
        @Override
        protected List<RssItem> doInBackground(String... urls) {
            try {
                // Create RSS reader
                RssReader rssReader = new RssReader(urls[0]);
             
                // Parse RSS, get items
                return rssReader.getItems();
             
            } catch (Exception e) {
                Log.e("RssChannelActivity", e.getMessage());
            }
             
            return null;
        }
         
        @Override
        protected void onPostExecute(List<RssItem> result) {
             
            // Get a ListView from the RSS Channel view
            ListView itcItems = (ListView) findViewById(R.id.rssChannelListView);
                         
            // Create a list adapter
            ArrayAdapter<RssItem> adapter = new ArrayAdapter<RssItem>(local,android.R.layout.simple_list_item_1, result);
            // Set list adapter for the ListView
            itcItems.setAdapter(adapter);
                         
            // Set list view item click listener
            itcItems.setOnItemClickListener(new ListListener(result, local));
          
        }
        
        
        
    }
	@Override
	  public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    case R.id.action_refresh:
	      Toast.makeText(this, "Refreshing...", Toast.LENGTH_SHORT)
	          .show();
	      GetRSSDataTask task = new GetRSSDataTask();
	         
	        // Start process RSS task
	        task.execute(rssUrl);
			   
	      break;    

	  
	    }

	    return true;
	  } 
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.web, menu);
	return	super.onCreateOptionsMenu(menu);

		
	}
}
