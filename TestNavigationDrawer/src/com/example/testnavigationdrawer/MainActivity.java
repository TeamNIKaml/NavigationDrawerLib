package com.example.testnavigationdrawer;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.testnavigationdrawer.fragment.CommunityFragment;
import com.example.testnavigationdrawer.fragment.FindPeopleFragment;
import com.example.testnavigationdrawer.fragment.HomeFragment;
import com.example.testnavigationdrawer.fragment.PagesFragment;
import com.example.testnavigationdrawer.fragment.PhotosFragment;
import com.example.testnavigationdrawer.fragment.WhatsHotFragment;
import com.teamnikaml.navigationdrawerlib.activity.NavigationDrawerActivity;
import com.teamnikaml.navigationdrawerlib.activity.NavigationSearchActivity;
import com.teamnikaml.navigationdrawerlib.model.Mapper;
import com.teamnikaml.navigationdrawerlib.model.NavDrawerItem;



public class MainActivity extends Activity {

	private List<Fragment> fragmentList;

	private List<NavDrawerItem> navigationDrawerItemList;
	
	Mapper mapper = Mapper.getMapper();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setData();
		
		Intent intent = new Intent(getApplicationContext(),NavigationSearchActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
		finish();
		
	

	}

	private void setData() {
		// TODO Auto-generated method stub

		String[] title = { "Home", "Find People", "Photos", "Communities",
				"Pages", "What\'s Hot" };
		int[] icon = { R.drawable.ic_home, R.drawable.ic_people,
				R.drawable.ic_photos, R.drawable.ic_communities,
				R.drawable.ic_pages, R.drawable.ic_whats_hot };

		navigationDrawerItemList = new ArrayList<>();
		NavDrawerItem drawerItem;

		for (int i = 0; i < 6; i++) {

			drawerItem = new NavDrawerItem(title[i], icon[i]);
			if (i == 3) {
				drawerItem.setCount("22");
				drawerItem.setCounterVisibility(true);
			}

			if (i == 5) {
				drawerItem.setCount("50");
				drawerItem.setCounterVisibility(true);
			}
			navigationDrawerItemList.add(drawerItem);
		}

		fragmentList = new ArrayList<Fragment>();

		fragmentList.add(new HomeFragment());
		fragmentList.add(new FindPeopleFragment());
		fragmentList.add(new PhotosFragment());
		fragmentList.add(new CommunityFragment());
		fragmentList.add(new PagesFragment());
		fragmentList.add(new WhatsHotFragment());
		
		//mapper.setDrawer(R.drawable.ic_drawer);
		mapper.setFragmentList(fragmentList);
		mapper.setNavigationDrawerItemList(navigationDrawerItemList);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
