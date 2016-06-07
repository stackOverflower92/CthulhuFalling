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
	private TrueTypeFont mTTFont;
	private int mTitleTop = 30;
	
	private Menu mMenu;
	
	// Game holding this state
	private StateBasedGame game;
	
	private void initMainMenu() {
		mMenu = new Menu(
				new MenuItem[] {
						new MenuItem("Play", new IMenuItemFunction() {
							@Override
							public void execute() {
								game.enterState(Level1State.id, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
							}
						}),
						new MenuItem("Options", new IMenuItemFunction() {
							@Override
							public void execute() {
								System.out.println("Options pressed");
							}
						}),
						new MenuItem("Exit", new IMenuItemFunction() {
							@Override
							public void execute() {
								System.out.println("Exit pressed");
							}
						})
				}
		);
		
		mMenu.init();
		
		try {
			mTitleFont = Font.createFont(Font.TRUETYPE_FONT, ResourceLoader.getResourceAsStream("assets/fonts/lolita_font.ttf"));
			mTitleFont = mTitleFont.deriveFont(52f);
			mTTFont = new TrueTypeFont(mTitleFont, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void drawMainMenu(Graphics graphics) {	
		mMenu.render(graphics);
	}
	
	private void drawGameTitle(Graphics graphics) {
		mTTFont.drawString(
				(Constants.SCREEN_WIDTH / 2) - (mTTFont.getWidth(Constants.GAME_NAME) / 2),
				mTitleTop,
				Constants.GAME_NAME,
				Color.red
		);
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
		mBackgroundImage.draw(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
		
		drawGameTitle(graphics);
		drawMainMenu(graphics);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		Input input = container.getInput();
		
		mMenu.update(container, delta);
		
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
