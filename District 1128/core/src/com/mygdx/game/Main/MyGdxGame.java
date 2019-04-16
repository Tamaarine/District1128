package com.mygdx.game.Main;

import com.badlogic.gdx.Game;

public class MyGdxGame extends Game {

    @Override
    public void create() {
        this.setScreen(new EntryPoint(this));
    }

    public void render()
    {
        super.render();

    }

    public void dispose()
    {

    }
}
