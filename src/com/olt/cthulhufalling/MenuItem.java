package com.olt.cthulhufalling;

public class MenuItem {
	private String mDisplayName;
	private IMenuItemFunction mFunction;
	
	public MenuItem(IMenuItemFunction function) {
		mFunction = function; 
	}
	
	public void executeFunction() {
		mFunction.execute();
	}
	
	public void setDisplayName(String displayName) {
		mDisplayName = displayName;
	}
	
	public String getDisplayName() {
		return mDisplayName;
	}
}
