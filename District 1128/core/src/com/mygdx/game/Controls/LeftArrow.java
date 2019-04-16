package com.mygdx.game.Controls;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.Actor.GameObject;
import com.mygdx.game.Actor.Meko;

public class LeftArrow extends GameObject {

    //Relative variable
    private Meko meko;

    private boolean isTouched;

    public LeftArrow(Meko givenMeko)
    {
        super(0,0,64,64);

        meko=givenMeko;

        setWidth(64);
        setHeight(64);

        isTouched=false;

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
        //setPosition(mekoX-,mekoY-50);

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


    public boolean listenButton()
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

        return isTouched;
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
