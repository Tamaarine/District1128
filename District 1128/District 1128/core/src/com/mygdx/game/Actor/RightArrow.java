package com.mygdx.game.Actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class RightArrow extends GameObject {

    //Relative variables
    Meko meko;

    public RightArrow(Meko givenMeko)
    {
        super(0,0,64,64);

        setWidth(64);
        setHeight(64);

        meko=givenMeko;

        float mekoX=meko.getHitBox().x;
        float mekoY=meko.getHitBox().y;

        setPosition(mekoX-100,mekoY-50);
        setTexture(new Texture(Gdx.files.internal("RightArrow2.png")));

    }


    public void draw(SpriteBatch batch)
    {
        float mekoX=meko.getX();
        float mekoY=meko.getY();

        setPosition(mekoX-130,mekoY-50);

        batch.draw(getTexture(),getX(),getY());

    }

    public void drawBox(ShapeRenderer shaper)
    {
        shaper.rect(getX(),getY(),getWidth(),getHeight());
    }

    public void increment()
    {
        if(getX()>0)
        {
            getHitBox().x--;
        }

        System.out.println(getX());
    }

}
