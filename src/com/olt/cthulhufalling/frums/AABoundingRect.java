package com.olt.cthulhufalling.frums;

import java.util.ArrayList;

public class AABoundingRect extends BoundingShape {
	 
    public float x;
    public float y;
    public float width;
    public float height;
 
    public AABoundingRect(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
 
    public void updatePosition(float newX, float newY) {
        this.x = newX;
        this.y = newY;
    }
 
    public void movePosition(float x, float y) {
        this.x += x;
        this.y += y;
    }

	@Override
	public boolean checkCollision(AABoundingRect rect) {
		return !(rect.x > this.x+width || rect.x+rect.width < this.x || rect.y > this.y+height || rect.y+rect.height < this.y);
	}

	@Override
	public ArrayList<Tile> getTilesOccupying(Tile[][] tiles) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Tile> getGroundTiles(Tile[][] tiles) {
		// TODO Auto-generated method stub
		return null;
	}
   
}
