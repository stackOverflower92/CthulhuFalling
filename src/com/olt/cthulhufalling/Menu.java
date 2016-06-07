package com.olt.cthulhufalling;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.util.ResourceLoader;

public class Menu {
	private MenuItem[] mItems;
	
	private Font mFont;
	private TrueTypeFont mTTF;
	
	private int mStartPosition;
	private int mGap = 80;
	private int mMenuHeight = 0;
	
	private Vector2f mMousePosition;
	
	public Menu(MenuItem[] items) {
		mItems = items;
	}
	
	private int getMenuItemX(MenuItem item, TrueTypeFont ttf) {
		return ((Constants.SCREEN_WIDTH / 2) - (ttf.getWidth(item.getDisplayName()) / 2));
	}
	
	private boolean isMouseHover(MenuItem item) {
		return item.getBox().contains(mMousePosition.getX(), mMousePosition.getY());
	}
	
	public void init() {
		try {
			// Init font
			mFont = Font.createFont(Font.TRUETYPE_FONT, ResourceLoader.getResourceAsStream("assets/fonts/plain_font.ttf"));
			mFont = mFont.deriveFont(55f);
			mTTF = new TrueTypeFont(mFont, true);
			
			// We will display the menu a little bit below the middle of the screen by default
			for (int i = 0; i < mItems.length; i++) {
				mMenuHeight += (mTTF.getHeight(mItems[i].getDisplayName()) + mGap);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void render(Graphics graphics) {
		// TODO: Add control for menu
		/*try {
			if (mMenuHeight > Constants.SCREEN_HEIGHT) {
				throw new MenuSizeException("Menu is too high for the screen");
			}
		} catch (MenuSizeException e) {
			e.printStackTrace();
		}*/
		
		for (int i = 0; i < mItems.length; i++) {
			mTTF.drawString(
					getMenuItemX(mItems[i], mTTF),
					(Constants.SCREEN_HEIGHT - mMenuHeight) + ((mTTF.getHeight(mItems[i].getDisplayName()) * (i + 1)) + mGap),
					mItems[i].getDisplayName()
			);
			
			// Set boxes
			mItems[i].setBox(new Rectangle(
					getMenuItemX(mItems[i], mTTF),
					(Constants.SCREEN_HEIGHT - mMenuHeight) + ((mTTF.getHeight(mItems[i].getDisplayName()) * (i + 1)) + mGap),
					mTTF.getWidth(mItems[i].getDisplayName()),
					mTTF.getHeight(mItems[i].getDisplayName())
			));
			
			if (mItems[i].isBoxShown()) {
				// Draw selection box
				graphics.setColor(Color.white);
				graphics.drawRect(mItems[i].getBox().getX(), mItems[i].getBox().getY(), mItems[i].getBox().getWidth(), mItems[i].getBox().getHeight());
			}
		}
	}
	
	public void update(GameContainer container, int delta) {
		// Update mouse position
		mMousePosition = new Vector2f(container.getInput().getMouseX(), container.getInput().getMouseY());
		
		for (int i = 0; i < mItems.length; i++) {
			if (isMouseHover(mItems[i])) {
				mItems[i].showBox(true);
			} else {
				mItems[i].showBox(false);
			}
		}
		
		// Detect click
		for (int i = 0; i < mItems.length; i++) {
			if (container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
				if (isMouseHover(mItems[i])) {
					mItems[i].executeFunction();
				}
			}
		}
	}
}
