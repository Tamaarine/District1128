package com.mygdx.game.Actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class LeftArrow extends GameObject {

    //Relative variable
    Meko meko;

    public LeftArrow(Meko givenMeko)
    {
        super(0,0,64,64);

        meko=givenMeko;

        setWidth(64);
        setHeight(64);

        float mekoX=meko.getX();
        float mekoY=meko.getY();

        setPosition(mekoX,mekoY);

        setTexture(new Texture(Gdx.files.internal("LeftArrow2.png")));
    }


    public void draw(SpriteBatch batch)
    {
        float mekoX=meko.getX();
        float mekoY=meko.getY();

         setPosition(mekoX-200,mekoY-50);


        batch.draw(getTexture(),getX(),getY());
    }

    public void drawBox(ShapeRenderer shaper)
    {
        shaper.rect(getX(),getY(),getWidth(),getHeight());
    }

    public void increment()
    {
        getHitBox().x++;
        getHitBox().y++;
    }
}
