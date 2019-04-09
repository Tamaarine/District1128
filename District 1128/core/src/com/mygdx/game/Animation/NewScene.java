package com.mygdx.game.Animation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class NewScene
{
    private boolean isComplete;
    private SpriteBatch batch;
    private OrthographicCamera camera;

    public NewScene()
    {
        isComplete=true;
        batch=new SpriteBatch();
        camera=new OrthographicCamera();
        camera.setToOrtho(false,Gdx.graphics.getWidth()/4,Gdx.graphics.getHeight()/4);
        batch.setProjectionMatrix(camera.combined);
    }


    public abstract void act();

    //Accesor method
    public SpriteBatch getBatch()
    {
        return batch;
    }

    public boolean isComplete()
    {
        return isComplete;
    }

    public void setComplete(boolean b)
    {
        isComplete=b;
    }
}
