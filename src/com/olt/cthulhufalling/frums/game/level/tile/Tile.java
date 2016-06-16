package com.olt.cthulhufalling.frums.game.level.tile;

import com.olt.cthulhufalling.frums.game.physics.AABoundingRect;
import com.olt.cthulhufalling.frums.game.physics.BoundingShape;

public class Tile {
    protected int x;
    protected int y;
    protected BoundingShape boundingShape;
    
    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
        
        boundingShape = new AABoundingRect(x * 32, y * 32, 32, 32);
    }
 
    public BoundingShape getBoundingShape() {
    	return boundingShape;
    }
    
    public int getX(){
        return x;
    }
 
    public int getY(){
        return y;
    }
 
}
