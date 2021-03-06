package com.teamnikaml.navigationdrawerlib.activity;

import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.teamnikaml.navigationdrawerlib.adapter.NavDrawerListAdapter;
import com.teamnikaml.navigationdrawerlib.model.Mapper;
import com.teamnikaml.navigationdrawerlib.model.NavDrawerItem;

@SuppressWarnings("deprecation")
public class NavigationDrawerActivity extends Activity {
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
	private List<NavDrawerItem> navigationDrawerItemList;
	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	private NavDrawerListAdapter adapter;
	private List<Fragment> fragmentList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_navigation_drawer);

		init();

		if (savedInstanceState == null) {
			// on first time display view for first nav item
			displayView(0);
		}
	}

	/**
	 * @author Nikhil V Jun 23, 2015
	 */
	private void init() {
		// TODO Auto-generated method stub

		mTitle = mDrawerTitle = getTitle();
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.list_slidermenu);
		
		mDrawerList.setBackgroundColor(Color.parseColor(Mapper.getMapper().getBackGroundColor()));

		setDrawerAdaptor();

		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		setListners();

	}

	/**
	 * @author Nikhil V Jun 23, 2015
	 */
	private void setListners() {
		// TODO Auto-generated method stub

		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, true,
				R.drawable.ic_drawer, // nav menu toggle icon
				R.string.app_name, // nav drawer open - description for
									// accessibility
				R.string.app_name // nav drawer close - description for
									// accessibility
		)

		{
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(mTitle);

				// calling onPrepareOptionsMenu() to show action bar icons
				invalidateOptionsMenu();
			}

			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(mDrawerTitle);
				// calling onPrepareOptionsMenu() to hide action bar icons
				invalidateOptionsMenu();
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);

	}

	/**
	 * @author Nikhil V Jun 23, 2015
	 */
	private void setDrawerAdaptor() {
		// TODO Auto-generated method stub

		Mapper mapper = Mapper.getMapper();

		navigationDrawerItemList = mapper.getNavigationDrawerItemList();
		fragmentList = mapper.getFragmentList();

		mDrawerList.setOnItemClickListener(new SlideMenuClickListener());

		// setting the nav drawer list adapter
		adapter = new NavDrawerListAdapter(getApplicationContext(),
				navigationDrawerItemList);
		mDrawerList.setAdapter(adapter);

	}

	/**
	 * Slide menu item click listener
	 * */
	private class SlideMenuClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// display view for selected nav drawer item
			displayView(position);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// toggle nav drawer on selecting action bar app icon/title
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}

		// Handle action bar actions click
		switch (item.getItemId()) {

		
		// case R.id.action_settings: return true;
		 

		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// if nav drawer is opened, hide the action items
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
		menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}

	/**
	 * @author Nikhil V Jun 22, 2015
	 * @param position
	 */
	public void displayView(int position) {
		// TODO Auto-generated method stub
		Fragment fragment = fragmentList.get(position);

		if (fragment != null) {
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.frame_container, fragment).commit();

			// update selected item and title, then close the drawer
			mDrawerList.setItemChecked(position, true);
			mDrawerList.setSelection(position);
			setTitle(navigationDrawerItemList.get(position).getTitle());
			mDrawerLayout.closeDrawer(mDrawerList);
		} else {
			// error in creating fragment
			Log.e("MainActivity", "Error in creating fragment");
		}
	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getActionBar().setTitle(mTitle);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

}
