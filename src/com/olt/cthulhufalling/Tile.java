package com.olt.cthulhufalling;

import org.newdawn.slick.geom.Rectangle;

public class Tile {
	private TileType mType;
	private Rectangle mRect;
	
	public Tile(Rectangle rect, TileType tType) {
		this.mType = tType;
		this.mRect = rect;
	}
	
	public TileType getType() {
		return mType;
	}
	public void setType(TileType mType) {
		this.mType = mType;
	}
	public Rectangle getRect() {
		return mRect;
	}
	public void setRect(Rectangle mRect) {
		this.mRect = mRect;
	}
}
