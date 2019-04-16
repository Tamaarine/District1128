package com.mygdx.game.Controls;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Actor.GameObject;
import com.mygdx.game.Actor.Meko;

public class BButton extends GameObject
{
    //Relative variable
    private Meko meko;

    public BButton(Meko givenMeko)
    {
        super(0,0,64,64);
        meko=givenMeko;

        setWidth(64);
        setHeight(64);

        setTexture(new Texture(Gdx.files.internal("BButton.png")));

    }

    public void draw(SpriteBatch batch)
    {
        float mekoX=meko.getX();
        float mekoY=meko.getY();

        setPosition(mekoX+150,mekoY+16);

        batch.draw(getTexture(),getX(),getY());

    }

    public float map(float value,float low,float high,float toLow,float toHigh)
    {
        return toLow+(value-low)*(toHigh-toLow)/(high-low);
    }


}
