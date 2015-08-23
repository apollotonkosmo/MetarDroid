package com.Apollo.metardroid;

import java.util.List;



import android.os.AsyncTask;
import android.util.Log;


public class GetRSSArticleDataTask extends AsyncTask<String, Void, List<RssItem>> {

	private Exception _exception;

	private IGetRSSArticleDataTaskListener _listener;

	public interface IGetRSSArticleDataTaskListener {

		public void onSuccesfullResponse(List<RssItem> lstRSSItems);

		public void onException(Exception ex);
	}

	public GetRSSArticleDataTask(IGetRSSArticleDataTaskListener Listener) {

		this._listener = Listener;
	}

	@Override
	protected List<RssItem> doInBackground(String... urls) {
		try {
			// Create RSS reader
			RssReader rssReader = new RssReader(urls[0]);

			// Parse RSS, get items
			return rssReader.getItems();

		} catch (Exception ex) {
			_exception = ex;
			Log.e("parsing", ex.getMessage());
		}

		return null;
	}

	@Override
	protected void onPostExecute(List<RssItem> result) {
		super.onPostExecute(result);
		if (this._exception != null) {
			if (this._listener != null) {
				this._listener.onException(this._exception);
				return;
			}
		}
			if (this._listener != null) {

				this._listener.onSuccesfullResponse(result);

			}

		
	}
}