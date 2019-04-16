package com.mygdx.game.Actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class TinMonster
{
    //Animations
    private Animation<TextureRegion> moving;
    private TextureAtlas movingAtlas;
    private Rectangle position;
    private final float VELOCITY=50;

    public TinMonster(float x,float y)
    {
        movingAtlas=new TextureAtlas(Gdx.files.internal("TinCan/CanMoving.atlas"));
        moving=new Animation<TextureRegion>(1/3f,movingAtlas.findRegions("S"), Animation.PlayMode.LOOP);
        position=new Rectangle(x,y,64,64);

    }

    public void move()
    {
        position.x-=VELOCITY*Gdx.graphics.getDeltaTime();
    }

    public void draw(SpriteBatch batch,float stateTime)
    {
        move();

        batch.draw(moving.getKeyFrame(stateTime),getX(),getY());
    }

    public float getX()
    {
        return position.x;
    }

    public float getY()
    {
        return position.y;
    }


}
