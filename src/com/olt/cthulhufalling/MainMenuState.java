package com.olt.cthulhufalling;


import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.util.ResourceLoader;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;

import org.newdawn.slick.UnicodeFont;

public class MainMenuState extends BasicGameState {
	// Exclusive id for state
	public static final int id = 1;
	
	private Image mBackgroundImage;
	
	private Font mTitleFont;
	private Font mMenuFont;
	private TrueTypeFont mTTFont;
	private TrueTypeFont mMenuTTFont;
	
	// Game holding this state
	private StateBasedGame game;
	
	private void initMainMenu() {
		try {
			mTitleFont = Font.createFont(Font.TRUETYPE_FONT, ResourceLoader.getResourceAsStream("assets/fonts/lolita_font.ttf"));
			mTitleFont = mTitleFont.deriveFont(55f);
			mTTFont = new TrueTypeFont(mTitleFont, true);
			
			mMenuFont = Font.createFont(Font.TRUETYPE_FONT, ResourceLoader.getResourceAsStream("assets/fonts/plain_font.ttf"));
			mMenuFont = mMenuFont.deriveFont(30f);
			mMenuTTFont = new TrueTypeFont(mMenuFont, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void drawMainMenu(Graphics graphics) {	
		mBackgroundImage.draw(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
		mTTFont.drawString((Constants.SCREEN_WIDTH / 2) - (mTTFont.getWidth("Cthulhu Falling") / 2), Constants.SCREEN_HEIGHT / 10, "Cthulhu Falling", Color.red);
		
		graphics.resetFont();
		graphics.setColor(Color.white);
		
		mMenuTTFont.drawString((Constants.SCREEN_WIDTH / 2) - (mMenuTTFont.getWidth("Play Game") / 2), (Constants.SCREEN_HEIGHT / 2) + 100, "Play Game");
		mMenuTTFont.drawString((Constants.SCREEN_WIDTH / 2) - (mMenuTTFont.getWidth("High Scores") / 2), (Constants.SCREEN_HEIGHT / 2) + 140, "High Scores");
		mMenuTTFont.drawString((Constants.SCREEN_WIDTH / 2) - (mMenuTTFont.getWidth("Quit") / 2), (Constants.SCREEN_HEIGHT / 2) + 180, "Quit");
	}
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		// Get game
		this.game = game;
		
		// Set Background Image
		mBackgroundImage = new Image("assets/images/wallpapers/wallpaper-mainmenu.jpg");
		
		initMainMenu();
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics graphics) throws SlickException {
		drawMainMenu(graphics);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		Input input = container.getInput();
		
		// Add input logic
		if (input.isKeyDown(Input.KEY_1)) {
			// Enter level 1 state
			game.enterState(Level1State.id, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
		}
	}

	@Override
	public int getID() {
		return id;
	}

}
