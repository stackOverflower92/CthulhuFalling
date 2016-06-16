package com.olt.cthulhufalling.frums.game.character;

import java.util.HashMap;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.olt.cthulhufalling.frums.game.enums.Facing;

public abstract class Character {
	 
    protected float x;
    protected float y;
    protected Image sprite;
    protected Facing facing;
    HashMap<Facing, Image> sprites;
    protected HashMap<Facing, Animation> movingAnimations;
    protected long lastTimeMoved;
    
    public Character(float x, float y) throws SlickException{
        this.x = x;
        this.y = y;
        //in case we forget to set the image, we don't want the game to crash, but it still has to be obvious that something was forgotten
        sprite = new Image("data/img/placeholder_sprite.png");
        
        // Default facing for character
        facing = Facing.RIGHT;
        
        setSprite(sprite);
    }
 
    public float getX(){
        return x;
    }
 
    public float getY(){
        return y;
    }
 
    public void render(){
    	//draw a moving animation if we have one and we moved within the last 150 miliseconds
        if(movingAnimations != null && lastTimeMoved+150 > System.currentTimeMillis()){
            movingAnimations.get(facing).draw(x-2,y-2);                
        }else{            
            sprites.get(facing).draw(x-2, y-2);          
        }
    }
    
    protected void setSprite(Image i){
        sprites = new HashMap<Facing,Image>();
        sprites.put(Facing.RIGHT, i);
        sprites.put(Facing.LEFT , i.getFlippedCopy(true, false));
    }
    
    protected void setMovingAnimation(Image[] images, int frameDuration){
        movingAnimations = new HashMap<Facing,Animation>();
 
        //we can just put the right facing in with the default images
        movingAnimations.put(Facing.RIGHT, new Animation(images,frameDuration));
 
        Animation facingLeftAnimation = new Animation();
        for(Image i : images){
            facingLeftAnimation.addFrame(i.getFlippedCopy(true, false), frameDuration);
        }
        movingAnimations.put(Facing.LEFT, facingLeftAnimation);
 
    }
}
