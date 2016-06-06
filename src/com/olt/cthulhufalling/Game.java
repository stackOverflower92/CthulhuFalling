package com.olt.cthulhufalling;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Game extends StateBasedGame {

	public Game(String name) {
		super(name);
	}
	
	@Override
	public void initStatesList(GameContainer arg0) throws SlickException {
		// Add all states to the game
		addState(new MainMenuState());		
		addState(new Level1State());
	}
	
	public static void main(String[] args) {
		try {
			AppGameContainer container = new AppGameContainer(new Game("Cthulhu Falling"));
			
			container.setDisplayMode(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, false);
			container.setShowFPS(false);
			container.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
