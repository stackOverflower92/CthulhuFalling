package com.olt.cthulhufalling.frums.game.physics;

import java.util.ArrayList;

import com.olt.cthulhufalling.frums.game.level.tile.Tile;

public abstract class BoundingShape {
    
    public boolean checkCollision(BoundingShape bv){
        if(bv instanceof AABoundingRect)
            return checkCollision((AABoundingRect) bv);
        return false;
    }
    
    public abstract boolean checkCollision(AABoundingRect box);
    
    public abstract void updatePosition(float newX, float newY);
    
    public abstract void movePosition(float x, float y);
    
    public abstract ArrayList<Tile> getTilesOccupying(Tile[][] tiles);
    
    public abstract ArrayList<Tile> getGroundTiles(Tile[][] tiles);

}
