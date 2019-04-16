package com.mygdx.game.Controls;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.Actor.GameObject;
import com.mygdx.game.Actor.Meko;

public class RightArrow extends GameObject {

    //Relative variables
    private Meko meko;

    private boolean isTouched;

    public RightArrow(Meko givenMeko)
    {
        super(0,0,64,64);

        setWidth(64);
        setHeight(64);

        isTouched=false;

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

        listenButton();

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


    public void listenButton()
    {
        float touchX=Gdx.input.getX();
        float touchY=Gdx.graphics.getHeight()-Gdx.input.getY();

        float mappedX=map(touchX,0,1766,getX()-222,getX()+222);
        float mappedY=map(touchY,0,1080,0,270);

        if(getHitBox().contains(mappedX,mappedY))
        {
            isTouched=true;
        }
        else
        {
            isTouched=false;
        }
    }

    public boolean isTouched()
    {
        return isTouched;
    }

    public float map(float value,float low,float high,float toLow,float toHigh)
    {
        return toLow+(value-low)*(toHigh-toLow)/(high-low);
    }

}
