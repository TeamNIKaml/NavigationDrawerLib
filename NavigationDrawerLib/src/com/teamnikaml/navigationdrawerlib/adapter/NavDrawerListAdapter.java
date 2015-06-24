package com.teamnikaml.navigationdrawerlib.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.teamnikaml.navigationdrawerlib.activity.R;
import com.teamnikaml.navigationdrawerlib.model.Mapper;
import com.teamnikaml.navigationdrawerlib.model.NavDrawerItem;

public class NavDrawerListAdapter extends BaseAdapter {

	private Context context;
	private List<NavDrawerItem> navDrawerItems;
	private Mapper mapper = Mapper.getMapper();

	public NavDrawerListAdapter(Context context,
			List<NavDrawerItem> navDrawerItems) {
		this.context = context;
		this.navDrawerItems = navDrawerItems;
	}

	@Override
	public int getCount() {
		return navDrawerItems.size();
	}

	@Override
	public Object getItem(int position) {
		return navDrawerItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;

		if (convertView == null) {
			LayoutInflater mInflater = (LayoutInflater) context
					.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
			convertView = mInflater.inflate(R.layout.drawer_list_item, parent,
					false);
			convertView.setBackgroundColor(Color.parseColor(mapper
					.getBackGroundColor()));

			holder = new ViewHolder();
			holder.imgIcon = (ImageView) convertView.findViewById(R.id.icon);
			holder.txtTitle = (TextView) convertView.findViewById(R.id.title);
			holder.txtCount = (TextView) convertView.findViewById(R.id.counter);

			holder.txtTitle
					.setTextColor(Color.parseColor(mapper.getTextColor()));
			holder.txtCount.setTextColor(Color.parseColor(mapper
					.getTextColorSmall()));

			convertView.setTag(holder);

		} else
			holder = (ViewHolder) convertView.getTag();

		holder.imgIcon.setImageResource(navDrawerItems.get(position).getIcon());
		holder.txtTitle.setText(navDrawerItems.get(position).getTitle());

		if (navDrawerItems.get(position).getCounterVisibility())
			holder.txtCount.setText(navDrawerItems.get(position).getCount());
		else
			holder.txtCount.setVisibility(View.GONE);

		return convertView;
	}

	private static class ViewHolder {
		private TextView txtTitle, txtCount;
		private ImageView imgIcon;

	}

}
