package com.olt.cthulhufalling.frums.game.character;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.olt.cthulhufalling.frums.game.enums.Facing;
import com.olt.cthulhufalling.frums.game.physics.AABoundingRect;

public class Player extends Character {

    public Player(float x, float y) throws SlickException{
        super(x,y);
        setSprite(new Image("data/img/characters/player/player.png"));
        
        setMovingAnimation(new Image[]{new Image("data/img/characters/player/player.png"),new Image("data/img/characters/player/player.png"),
                                       new Image("data/img/characters/player/player.png"),new Image("data/img/characters/player/player.png")}
                                       ,125);
        boundingShape = new AABoundingRect(x+3, y, 26, 32);
        
        accelerationSpeed = 0.001f;
        maximumSpeed = 0.15f;
        maximumFallSpeed = 0.3f;
        decelerationSpeed = 0.001f;
    }
    
    public void updateBoundingShape(){
        boundingShape.updatePosition(x+3,y);
    }

}