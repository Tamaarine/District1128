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

        setSpriteTexture(new Texture(Gdx.files.internal("RightArrow2.png")));
        meko=givenMeko;

        float mekoX=meko.getHitBox().x;
        float mekoY=meko.getHitBox().y;

        setPosition(mekoX-100,mekoY-50);
    }


    public void draw(SpriteBatch batch)
    {
        float mekoX=meko.getHitBox().x;
        float mekoY=meko.getHitBox().y;

        setPosition(mekoX-100,mekoY-50);

        batch.draw(getSprite().getTexture(),getX(),getY());
    }

    public void drawBox(ShapeRenderer shaper)
    {
        shaper.rect(getX(),getY(),getWidth(),getHeight());
    }


}
