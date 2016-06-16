package com.olt.cthulhufalling.frums.game.state;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.olt.cthulhufalling.frums.game.Game;
import com.olt.cthulhufalling.frums.game.character.Player;
import com.olt.cthulhufalling.frums.game.controller.MouseAndKeyBoardPlayerController;
import com.olt.cthulhufalling.frums.game.controller.PlayerController;
import com.olt.cthulhufalling.frums.game.level.Level;
import com.olt.cthulhufalling.frums.game.level.tile.Tile;
import com.olt.cthulhufalling.frums.game.physics.Physics;

public class LevelState extends BasicGameState {

    private Level  level;
    private String startinglevel;
    private Player player;
    private PlayerController playerController;
    private Physics physics;
    
    public LevelState(String startingLevel){
        this.startinglevel = startingLevel;
    }
    
    public void init(GameContainer container, StateBasedGame sbg) throws SlickException {
        
        //once we initialize our level, we want to load the right level
        level = new Level(startinglevel);
        
        //at the start of the game we don't have a player yet
        player = new Player(128,415);
        level.addCharacter(player);
        
        //and we create a controller, for now we use the MouseAndKeyBoardPlayerController
        playerController = new MouseAndKeyBoardPlayerController(player);
        
        physics = new Physics();
    }

    public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException {
        //every update we have to handle the input from the player
        playerController.handleInput(container.getInput(), delta);
        physics.handlePhysics(level, delta);
    }
    
    public void render(GameContainer container, StateBasedGame sbg, Graphics g) throws SlickException {
        g.scale(Game.SCALE, Game.SCALE);
        //render the level
        level.render();
        
        
        for(Tile t : player.getBoundingShape().getGroundTiles(level.getTiles())){
            g.setColor(new Color(255,130,90,200));
            g.drawRect(t.getX()*32, t.getY()*32, 32, 32);
        }
        
        
    }
    
    //this method is overriden from basicgamestate and will trigger once you press any key on your keyboard
    public void keyPressed(int key, char code){
        //if the key is escape, close our application
        if(key == Input.KEY_ESCAPE){
            System.exit(0);
        }
    }

    public int getID() {
        //this is the id for changing states
        return 0;
    }

}