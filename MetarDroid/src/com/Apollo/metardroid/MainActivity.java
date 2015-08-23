package com.Apollo.metardroid;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


import android.app.Activity;
import android.app.Dialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebSettings.ZoomDensity;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.Apollo.metardroid.GetRSSArticleDataTask.IGetRSSArticleDataTaskListener;
import com.Apollo.metardroid.GetRSSDataTask.IGetRSSDataTaskListener;
import com.squareup.picasso.Picasso;

public class MainActivity extends Activity implements
		ListView.OnItemClickListener {
	Context con;

	private String result = "result";
	private boolean cameras = false;
	private Boolean news = true;
	private DrawerLayout mDrawerLayout;
	private ProgressBar mProgressBar;
	private android.app.ActionBar mActionBar;
	private String mActionBarTitle;
	private ActionBarDrawerToggle mDrawerToggle;
	private TextView mtxtEmpty;
	private String[] lstMenus;
	private ListView lstvMenus;
	private menuAdapter _adapterMenus;
	public ArrayList<WeatherItem> Finalresult;
	public ArrayList<WeatherItem> lstweatherItems = null;
	public String[] lstFinalResult;
	private ListView lstvArticles;
	private GridView grdCameras;
	private articleAdapter _adapterArticles;
	private GridAdapter _adapterCameras;
	private ArticleAdapter _madapter;
	private ArrayList<RssItem> mLstItems;
	private GridView mGrdArticles;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mActionBar = getActionBar();
		mActionBarTitle = "Νέα";
		mActionBar.setTitle(mActionBarTitle);
		mActionBar.setHomeButtonEnabled(true);
		mActionBar.setDisplayHomeAsUpEnabled(true);
		setContentView(R.layout.activity_main);
		this.con = getBaseContext();

		// drawerLayout
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mProgressBar = (ProgressBar) findViewById(R.id.pb);
		mProgressBar.setVisibility(View.VISIBLE);

		// Inflate
		lstvMenus = (ListView) findViewById(R.id.left_drawer);
		lstvMenus.setOnItemClickListener(this);

		lstvArticles = (ListView) findViewById(R.id.list_articles);
		grdCameras = (GridView) findViewById(R.id.grd_cameras);
		lstvArticles.setOnItemClickListener(this);
		grdCameras.setOnItemClickListener(this);
		mGrdArticles=(GridView)findViewById(R.id.grd_articles);
		mGrdArticles.setOnItemClickListener(this);
		lstMenus = getResources().getStringArray(R.array.lstMenu);
		// _adapterMenus = new ArrayAdapter<String>(con,
		// android.R.layout.simple_list_item_1, lstMenus);
		
		_adapterMenus = new menuAdapter();
		lstvMenus.setAdapter(_adapterMenus);
		initializedrawer();
		// Set the list's click listener

		GetRSSDataTask weathertask = new GetRSSDataTask(
				new IGetRSSDataTaskListener() {

					@Override
					public void onSuccesfullResponse(
							List<WeatherItem> lstWeatherItems) {

						Finalresult = (ArrayList<WeatherItem>) lstWeatherItems;
						// mProgressBar = (ProgressBar) findViewById(R.id.pb);

						mProgressBar.setVisibility(View.GONE);
						lstweatherItems = Finalresult;
						
						//LoadAdapter(lstweatherItems);

					}

					@Override
					public void onException(Exception ex) {
						// TODO Auto-generated method stub

					}
				});
		GetRSSArticleDataTask newsTask = new GetRSSArticleDataTask(
				new IGetRSSArticleDataTaskListener() {

					@Override
					public void onSuccesfullResponse(List<RssItem> lstRSSItems) {
						// TODO Auto-generated method stub
						mLstItems = (ArrayList<RssItem>) lstRSSItems;
						//mProgressBar.setVisibility(View.GONE);
						//_madapter = new ArticleAdapter(mLstItems, con);
						//mGrdArticles.setAdapter(_madapter);
						
						
					
						
						grdCameras.setVisibility(View.GONE);
						lstvArticles.setVisibility(View.GONE);
						mGrdArticles.setVisibility(View.VISIBLE);
						_madapter= new ArticleAdapter(mLstItems, con);
						mGrdArticles.setAdapter(_madapter);
					}

					@Override
					public void onException(Exception ex) {
						// TODO Auto-generated method stub

					}
				});
		
		try {
			weathertask.execute("http://metar.org/upload/teilar.xml");
			newsTask.execute("http://www.metar.gr/index.php?format=feed&type=rss");
		} catch (Exception ex) {
			Toast.makeText(con, "Please check Internet connectivity",
					Toast.LENGTH_LONG).show();
		}
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mDrawerToggle.syncState();
		mtxtEmpty = (TextView) findViewById(R.id.empty);
	}

	private void initializedrawer() {

		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, R.string.drawer_open,
				R.string.drawer_close) {
			@Override
			public void onDrawerClosed(View drawerView) {
				getActionBar().setTitle(mActionBarTitle);
				invalidateOptionsMenu();
			}

			@Override
			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(R.string.drawer_open);
				invalidateOptionsMenu();
			}
		};

		mDrawerLayout.setDrawerListener(mDrawerToggle);
	}

	@Override
	public void onItemClick(AdapterView<?> adapter, View view, int position,
			long id) {
		if (adapter.getAdapter() == _adapterMenus) {
			OnListViewMenusItemClick(position);
		} else if (adapter.getAdapter() == _adapterArticles) {
			OnListViewArticlesItemClick(position);
		} else if (adapter.getAdapter() == _adapterCameras) {
			OnGridViewCamerasItemClick(position);
		}else if (adapter.getAdapter()==_madapter){
			onGridViewArticleClick(position);
		}
	}

	private void OnListViewMenusItemClick(int position) {
		// closes the left drawer
		// closeDrawer();
		mDrawerLayout.closeDrawers();
		// selects option
		switch (position) {
		case 0:
			// ο καιρος στην ελλαδα
			cameras = false;
			news= false;
			mtxtEmpty.setVisibility(View.GONE);
			lstweatherItems = Finalresult;
			// stFinalResult = PrepareLstFinalResult(lstweatherItems);
			if (lstweatherItems != null) {
				LoadAdapter(lstweatherItems);
				// lstvArticles.setOnItemClickListener(new WeatherListListener(
				// Finalresult, this));

				mActionBarTitle = "Ο καιρός LIVE!";
			}
			break;
		case 1:
			// καμερες
			mtxtEmpty.setVisibility(View.GONE);
			lstweatherItems = new ArrayList<WeatherItem>();
			if (Finalresult != null) {
				for (WeatherItem weatheritem : Finalresult) {
					String camera = weatheritem.getCamera().toString().trim();
					;
					if (camera != null && !camera.equals("-")) {

						lstweatherItems.add(weatheritem);

					}

				}
			}
			if (lstweatherItems != null) {
				cameras = true;
				news= false;
				LoadAdapter(lstweatherItems);

			} else {
				Toast.makeText(this, "this menu item is empty",
						Toast.LENGTH_LONG).show();
			}

			mActionBarTitle = "Κάμερες";
			break;
		case 2:
			// ο καιρος στα βουνα
			// Filter out

			mActionBarTitle = "Ο καιρός στα Βουνά!";
			cameras = false;
			news= false;
			lstweatherItems = new ArrayList<WeatherItem>();
			if (Finalresult != null) {
				for (WeatherItem weatherItem : Finalresult) {
					int elevation = Integer.parseInt(weatherItem.getElevation()
							.substring(0,
									weatherItem.getElevation().length() - 2));
					if (elevation > 800) {

						lstweatherItems.add(weatherItem);

					}

				}
			}
			// lstFinalResult = PrepareLstFinalResult(lstweatherItems);
			if (lstweatherItems != null) {
				LoadAdapter(lstweatherItems);

			} else if (lstweatherItems.size() == 0) {
				mtxtEmpty.setVisibility(View.VISIBLE);
				LoadAdapter(lstweatherItems);
			}

			break;
		case 3:
			// για τον οδηγο
			cameras = false;
			news= false;
			mActionBarTitle = "Για τον Οδηγό";
			lstweatherItems = new ArrayList<WeatherItem>();
			if (Finalresult != null) {
				for (WeatherItem weatherItem : Finalresult) {
					int humidity = Integer
							.parseInt(weatherItem.getOutsidehumidity()
									.substring(
											0,
											weatherItem.getOutsidehumidity()
													.length() - 2));
					int rain = Integer.parseInt(weatherItem.getDailyrain()
							.substring(0,
									weatherItem.getDailyrain().length() - 5));

					if (humidity > 97 || rain > 20) {

						lstweatherItems.add(weatherItem);

					}

				}
			}
			if (lstweatherItems != null) {
				LoadAdapter(lstweatherItems);

			} else if (lstweatherItems.size() == 0) {

				mtxtEmpty.setVisibility(View.VISIBLE);
				LoadAdapter(lstweatherItems);
			}
			break;
		// case 4:
		// // ευαισθητες κοιν ομαδες
		// cameras = false;
		// lstweatherItems = new ArrayList<WeatherItem>();
		// for (WeatherItem weatherItem : Finalresult) {
		// int heat = Integer.parseInt(weatherItem.getHeatindex()
		// .substring(0, weatherItem.getHeatindex().length() - 5));
		// int chill = Integer.parseInt(weatherItem.getWindchill()
		// .substring(0, weatherItem.getWindchill().length() - 5));
		//
		// if (heat > 37 || chill < -5) {
		//
		// lstweatherItems.add(weatherItem);
		//
		// }
		//
		// }
		// if (lstweatherItems == null) {
		//
		// Toast.makeText(this, "this menu item is empty",
		// Toast.LENGTH_LONG).show();
		// } else {
		// LoadAdapter(lstweatherItems);
		// }
		//
		// break;
		// case 5:
		//
		// // κινδυνος πυρκαγιας
		// cameras = false;
		// lstweatherItems = new ArrayList<WeatherItem>();
		// for (WeatherItem weatherItem : Finalresult) {
		// int fire = Integer.parseInt(weatherItem.getFire());
		//
		// if (fire >= 1 && fire <= 5) {
		//
		// lstweatherItems.add(weatherItem);
		//
		// }
		//
		// }
		// if (lstweatherItems == null) {
		//
		// Toast.makeText(this, "this menu item is empty",
		// Toast.LENGTH_LONG).show();
		// } else {
		// LoadAdapter(lstweatherItems);
		// }
		//
		// break;
		// case 6:
		// // αγροτες
		// cameras = false;
		// Toast.makeText(this, lstMenus[6], Toast.LENGTH_LONG).show();
		// break;
		case 4:
			// νεα
				cameras=false;
				news=true;
				mActionBarTitle="Νέα";
				mActionBar.setTitle(mActionBarTitle);
				grdCameras.setVisibility(View.GONE);
				lstvArticles.setVisibility(View.GONE);
				mGrdArticles.setVisibility(View.VISIBLE);
				_madapter= new ArticleAdapter(mLstItems, con);
				mGrdArticles.setAdapter(_madapter);
//			Intent news = new Intent(this, ArticleActivity.class);
//
//			startActivity(news);
			break;
		case 5:
			final Dialog dialog = new Dialog(MainActivity.this);
			// disables dialog title.
			dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
			dialog.setContentView(com.Apollo.metardroid.R.layout.activity_web);
			dialog.setTitle("About");
			WebView wv = (WebView) dialog
					.findViewById(com.Apollo.metardroid.R.id.wvArticle);
			wv.getSettings().setDefaultZoom(ZoomDensity.FAR);
			wv.setVerticalScrollBarEnabled(false);
			wv.setHorizontalScrollBarEnabled(false);
			

			StringBuilder sb = new StringBuilder();
			sb.append("<html>");
			sb.append("<body>");

			sb.append("<h2 style=\"text-align: center;\">");
			sb.append("<a href=\"http://www.metar.gr\"><img alt=\"Metar.gr\" src=\"http://www.metar.gr/templates/metar/images/metar.gif\" style=\"width: 140px; height: 70px;\" /></a></h2>");
			sb.append("<p style=\"text-align: center;\">");
			sb.append("	<strong>Metar.gr for Android </strong><strong>2014</strong></p>");
			sb.append("<p style=\"text-align: center;\">");
			sb.append("	<strong>Version 0.15</strong></p>");
			sb.append("<p style=\"text-align: center;\">");
			sb.append("<a href=\"http://www.teilar.gr\"><img alt=\"teilar.gr\" src=\"http://www.cs.teilar.gr/CS/images/cslogo72.png\" style=\"width: 215px; height: 70px;\" /></a></p>");
			sb.append("<p style=\"text-align: center;\">");
			sb.append("Copyright 2014 ApolloTonKosmo Co.</p>");
			sb.append("<p style=\"text-align: center;\">");
			sb.append("	.</p>");

			sb.append("</body>");
			sb.append("</html>");
			String html = sb.toString();
			html = html.replaceAll("width=\"[0-9]+\"", "width=\"" + "500"
					+ "\"");

			wv.loadDataWithBaseURL(null, html, "text/html", "utf-8", null);
			dialog.show();
			break;

		default:

			break;
		}
	}

	private void OnListViewArticlesItemClick(int position) {

		if (cameras == true) {
			Intent camera = new Intent(con, CameraAcivity.class);
			camera.putExtra(result,
					(WeatherItem) _adapterArticles.getItem(position));
			startActivity(camera);
		} else {
			Intent weather = new Intent(con, WeatherDetailActivity.class);
			weather.putExtra(result,
					(WeatherItem) _adapterArticles.getItem(position));
			startActivity(weather);
		}

	}

	private void OnGridViewCamerasItemClick(int position) {

		Intent camera = new Intent(con, CameraAcivity.class);
		camera.putExtra(result, (WeatherItem) _adapterCameras.getItem(position));

		startActivity(camera);

	}
	public void onGridViewArticleClick(int arg2) {
	
		Intent news = new Intent(con, ArticleDetailActivity.class);
		news.putExtra("result", (RssItem) _madapter.getItem(arg2));
		startActivity(news);
	}
	private void LoadAdapter(ArrayList<WeatherItem> DataSource) {
		if (cameras == true) {
			grdCameras.setVisibility(View.VISIBLE);
			lstvArticles.setVisibility(View.GONE);
			mGrdArticles.setVisibility(View.GONE);
			_adapterCameras = new GridAdapter();
			grdCameras.setAdapter(_adapterCameras);
		}
		 
		else {
			grdCameras.setVisibility(View.GONE);
			mGrdArticles.setVisibility(View.GONE);
			lstvArticles.setVisibility(View.VISIBLE);
			_adapterArticles = new articleAdapter();
			lstvArticles.setAdapter(_adapterArticles);
			_adapterArticles.notifyDataSetChanged();
		}
	}

	// Actionbar option Menu
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)||news==true) {
			return false;
		}
		else  {
			getMenuInflater().inflate(R.menu.main, menu);

			SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
			SearchView searchView = (SearchView) menu.findItem(
					R.id.action_search).getActionView();

			searchView.setSearchableInfo(searchManager
					.getSearchableInfo(getComponentName()));
			searchView.setIconifiedByDefault(false);

			SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
				@Override
				public boolean onQueryTextChange(String newText) {
					// this is your adapter that will be filtered
					if (cameras == true) {
						_adapterCameras.getFilter(newText);
					} else {
						_adapterArticles.getFilter(newText);
					}
					return true;
				}

				@Override
				public boolean onQueryTextSubmit(String query) {
					// this is your adapter that will be filtered

					_adapterArticles.getFilter(query);
					return true;
				}
			};
			searchView.setOnQueryTextListener(queryTextListener);

			return super.onCreateOptionsMenu(menu);
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem menuItem) {
		switch (menuItem.getItemId()) {
		case android.R.id.home:
			// passes handling to mDrawerToggle
			if (mDrawerToggle.onOptionsItemSelected(menuItem)) {
				return true;
			}
			return true;
		case (R.id.action_refresh):
			onCreate(null);
			// todo
			return true;
		case (R.id.action_search):
			// todo

			return true;

		}
		return (super.onOptionsItemSelected(menuItem));
	}

	
	// Menu adapter
	public class menuAdapter extends BaseAdapter {

		public menuAdapter() {

		}

		@Override
		public int getCount() {

			return lstMenus.length;
		}

		@Override
		public Object getItem(int arg0) {

			return lstMenus[arg0];
		}

		@Override
		public long getItemId(int arg0) {

			return arg0;
		}

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			arg1 = LayoutInflater.from(con).inflate(R.layout.menu_cell, null);
			TextView txtMenu = (TextView) arg1.findViewById(R.id.txtMenu);
			txtMenu.setText(this.getItem(arg0).toString());
			return arg1;
		}

	}

	public class GridAdapter extends BaseAdapter {
		private ArrayList<WeatherItem> arraylist;

		public GridAdapter() {
			this.arraylist = new ArrayList<WeatherItem>();
			this.arraylist.addAll(lstweatherItems);

		}

		@Override
		public int getCount() {

			return arraylist.size();
		}

		@Override
		public Object getItem(int arg0) {

			return arraylist.get(arg0);
		}

		@Override
		public long getItemId(int arg0) {

			return arg0;
		}

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {

			WeatherItem wi = (WeatherItem) this.getItem(arg0);
			arg1 = LayoutInflater.from(con).inflate(R.layout.camera_cell, null);
			TextView txtCamera = (TextView) arg1.findViewById(R.id.txtCamera);
			txtCamera.setText(wi.getTitle().toString());
			ImageView imgCamera = (ImageView) arg1.findViewById(R.id.imgCamera);
			if (wi.getCamera() != null) {

				Picasso.with(con).load(wi.getCamera().toString())
						.into(imgCamera);
				imgCamera.setScaleType(ScaleType.CENTER_CROP);
			} else {

			}

			return arg1;
		}

		public void getFilter(String charText) {

			charText = charText.toLowerCase(Locale.getDefault());
			arraylist.clear();

			if (charText.length() == 0) {
				arraylist.addAll(lstweatherItems);
			} else {
				for (WeatherItem wp : lstweatherItems) {
					if (wp.getTitle().toLowerCase(Locale.getDefault())
							.contains(charText)
							|| (wp.getDescription().toLowerCase(
									Locale.getDefault()).contains(charText))
							|| (wp.getDiamerisma().toLowerCase(
									Locale.getDefault()).contains(charText))) {
						arraylist.add(wp);
					}
				}
			}
			notifyDataSetChanged();

		}
	}

	public class articleAdapter extends BaseAdapter {

		private ArrayList<WeatherItem> arraylist;

		public articleAdapter() {
			this.arraylist = new ArrayList<WeatherItem>();
			this.arraylist.addAll(lstweatherItems);

		}

		@Override
		public int getCount() {

			return arraylist.size();
		}

		@Override
		public Object getItem(int arg0) {

			return arraylist.get(arg0);
		}

		@Override
		public long getItemId(int arg0) {

			return arg0;
		}

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {

			WeatherItem wi = (WeatherItem) this.getItem(arg0);
			arg1 = LayoutInflater.from(con)
					.inflate(R.layout.station_cell, null);

			TextView txtCamera = (TextView) arg1.findViewById(R.id.txtCamera);
			txtCamera.setText(wi.getTitle().toString());

			TextView txtTemp = (TextView) arg1.findViewById(R.id.txtTemp);
			txtTemp.setText(wi.getTemp());	

			ImageView imgCamera = (ImageView) arg1.findViewById(R.id.imgCamera);
			if (wi.getCamera() != null && !(wi.getCamera().equals("-"))) {

				Picasso.with(con).load(wi.getCamera().toString())
						.into(imgCamera);
			} else {
				// imgCamera.setBackgroundResource(R.drawable.metar_big);
				imgCamera.setScaleType(ScaleType.CENTER_INSIDE);
			}

			return arg1;

		}

		public void getFilter(String charText) {

			charText = charText.toLowerCase(Locale.getDefault());
			arraylist.clear();

			if (charText.length() == 0) {
				arraylist.addAll(lstweatherItems);
			} else {
				for (WeatherItem wp : lstweatherItems) {
					if (wp.getTitle().toLowerCase(Locale.getDefault())
							.contains(charText)
							|| (wp.getDescription().toLowerCase(
									Locale.getDefault()).contains(charText))
							|| (wp.getDiamerisma().toLowerCase(
									Locale.getDefault()).contains(charText))) {
						arraylist.add(wp);
					}
				}
			}
			notifyDataSetChanged();

		}
	}
}
