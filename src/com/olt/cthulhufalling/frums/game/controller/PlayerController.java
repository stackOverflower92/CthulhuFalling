package com.olt.cthulhufalling.frums.game.controller;

import org.newdawn.slick.Input;

import com.olt.cthulhufalling.frums.game.character.Player;
 
public abstract class PlayerController {
 
    protected Player player;
 
    public PlayerController(Player player){
        this.player = player;
    }
 
    public abstract void handleInput(Input i, int delta);
 
}
