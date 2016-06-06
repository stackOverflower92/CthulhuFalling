package com.olt.cthulhufalling;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

public class Sprite {
	private Image mImage;
	private Rectangle mRect;
	private Vector2f mPosition;
	
	public Image getImage() {
		return mImage;
	}
	public void setImage(Image mImage) {
		this.mImage = mImage;
	}
	public Rectangle getmRect() {
		return mRect;
	}
	public void setRect(Rectangle mRect) {
		this.mRect = mRect;
	}
	public Vector2f getPosition() {
		return mPosition;
	}
	public void setPosition(Vector2f mPosition) {
		this.mPosition = mPosition;
	}
}
