package com.olt.cthulhufalling.frums.game.controller;

import org.newdawn.slick.Input;

import com.olt.cthulhufalling.frums.game.character.Player;

public class MouseAndKeyBoardPlayerController extends PlayerController {

    public MouseAndKeyBoardPlayerController(Player player) {
        super(player);
    }

    public void handleInput(Input i, int delta) {
        //handle any input from the keyboard
        handleKeyboardInput(i,delta);
    }
    
    private void handleKeyboardInput(Input i, int delta){
        //we can both use the WASD or arrow keys to move around, obviously we can't move both left and right simultaneously
        if(i.isKeyDown(Input.KEY_A) || i.isKeyDown(Input.KEY_LEFT)){
            player.moveLeft(delta);
        }else if(i.isKeyDown(Input.KEY_D) || i.isKeyDown(Input.KEY_RIGHT)){
            player.moveRight(delta);
        }else{
            //we dont move if we don't press left or right, this will have the effect that our player decelerates
            player.setMoving(false);
        }
        
        if(i.isKeyDown(Input.KEY_SPACE)){
            player.jump();
        }
    }

}
