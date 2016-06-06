package com.olt.cthulhufalling;


import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class MainMenuState extends BasicGameState {
	// Exclusive id for state
	public static final int id = 1;
	
	// Game holding this state
	private StateBasedGame game;
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		// Get game
		this.game = game;
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics graphics) throws SlickException {
		graphics.setColor(Color.white);
		graphics.drawString("Cthulhu Falling", 50, 10);
	 
		graphics.drawString("1. Play Game", 50, 100);
		graphics.drawString("2. High Scores", 50, 120);
		graphics.drawString("3. Quit", 50, 140);
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
