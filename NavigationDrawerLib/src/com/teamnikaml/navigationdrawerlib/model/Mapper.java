/**
 * @author Nikhil V
 *
 * Jun 22, 2015
 */
package com.teamnikaml.navigationdrawerlib.model;

import java.util.List;

import android.app.Fragment;

/**
 * @author Nikhil V
 * 
 */
public class Mapper {

	private static Mapper mapper;
	
	private String backGroundColor = "#FFFFFF", textColor = "#000000",
			textColorSmall = "#FFFFFF";

	public String getTextColorSmall() {
		return textColorSmall;
	}

	public void setTextColorSmall(String textColorSmall) {
		this.textColorSmall = textColorSmall;
	}

	public String getBackGroundColor() {
		return backGroundColor;
	}

	public void setBackGroundColor(String backGroundColor) {
		this.backGroundColor = backGroundColor;
	}

	public String getTextColor() {
		return textColor;
	}

	public void setTextColor(String textColor) {
		this.textColor = textColor;
	}

	public static Mapper getMapper() {
		if (mapper == null)
			mapper = new Mapper();
		return mapper;

	}

	private List<NavDrawerItem> navigationDrawerItemList;

	private List<Fragment> fragmentList;

	public List<NavDrawerItem> getNavigationDrawerItemList() {
		return navigationDrawerItemList;
	}

	public void setNavigationDrawerItemList(
			List<NavDrawerItem> navigationDrawerItemList) {
		this.navigationDrawerItemList = navigationDrawerItemList;
	}

	public List<Fragment> getFragmentList() {
		return fragmentList;
	}

	public void setFragmentList(List<Fragment> fragmentList) {
		this.fragmentList = fragmentList;
	}

}
