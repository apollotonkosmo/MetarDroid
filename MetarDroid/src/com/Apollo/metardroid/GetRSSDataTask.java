package com.Apollo.metardroid;

import java.util.List;



import android.os.AsyncTask;
import android.util.Log;


public class GetRSSDataTask extends AsyncTask<String, Void, List<WeatherItem>> {

	private Exception _exception;

	private IGetRSSDataTaskListener _listener;

	public interface IGetRSSDataTaskListener {

		public void onSuccesfullResponse(List<WeatherItem> lstWeatherItems);

		public void onException(Exception ex);
	}

	public GetRSSDataTask(IGetRSSDataTaskListener Listener) {

		this._listener = Listener;
	}

	@Override
	protected List<WeatherItem> doInBackground(String... urls) {
		try {
			// Create RSS reader
			RssReader rssReader = new RssReader(urls[0]);

			// Parse RSS, get items
			return rssReader.getStations();

		} catch (Exception ex) {
			_exception = ex;
			Log.e("weatherActivity", ex.getMessage());
		}

		return null;
	}

	@Override
	protected void onPostExecute(List<WeatherItem> result) {
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