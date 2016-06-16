package com.olt.cthulhufalling.frums.game.character;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.olt.cthulhufalling.frums.game.enums.Facing;

public class Player extends Character {
	 
    public Player(float x, float y) throws SlickException{
        super(x,y);
        setSprite(new Image("data/img/characters/player/player.png"));
        
        // TODO: Add different frames, now there's just one for testing
        setMovingAnimation(
        		new Image[]{
	        		new Image("data/img/characters/player/player.png"),
	        		new Image("data/img/characters/player/player.png"),
	                new Image("data/img/characters/player/player.png"),
	                new Image("data/img/characters/player/player.png")
	            }
                ,125
        );
    }
    
    public void moveLeft(int delta){
        x = x - (0.15f*delta);
        facing = Facing.LEFT;
        lastTimeMoved = System.currentTimeMillis();
    }
 
    public void moveRight(int delta){
        x = x + (0.15f*delta);
        facing = Facing.RIGHT;
        lastTimeMoved = System.currentTimeMillis();
    }
}