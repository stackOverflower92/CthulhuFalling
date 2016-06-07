package com.olt.cthulhufalling;

import org.newdawn.slick.geom.Rectangle;

public class MenuItem {
	private String mDisplayName;
	private IMenuItemFunction mFunction;
	private Rectangle mSelectionRectangle;
	private boolean mShowBox = false;
	
	public MenuItem(String displayName, IMenuItemFunction function) {
		mDisplayName = displayName;
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
	
	public Rectangle getBox() {
		return mSelectionRectangle;
	}
	
	public void setBox(Rectangle rect) {
		this.mSelectionRectangle = rect;
	}
	
	public void showBox(boolean show) {
		mShowBox = show;
	}
	
	public boolean isBoxShown() {
		return mShowBox;
	}
}
