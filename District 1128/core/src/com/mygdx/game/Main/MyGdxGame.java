package com.mygdx.game.Main;

import com.badlogic.gdx.Game;
//hey this is walid, lets see if this works lol
public class MyGdxGame extends Game {

    @Override
    public void create()
    {
        this.setScreen(new Main_Menu(this));

    }

    public void render()
    {
        super.render();

    }

    public void dispose()
    {

    }
}
