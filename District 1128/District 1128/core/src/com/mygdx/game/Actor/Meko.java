package com.mygdx.game.Actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Meko {
    public Rectangle full;
    public Rectangle position;
    public Texture texture;
    public final float velocity=100f;
    public Animation<TextureRegion> leftWalk;
    public Animation<TextureRegion> rightWalk;
    public Animation<TextureRegion> leftRun;
    public Animation<TextureRegion> rightRun;
    public int actionCode;

    public Meko(float x,float y)
    {
        full=new Rectangle(x-41/2,y,41,112);
        position=new Rectangle(x,y,0,0);
        texture=new Texture(Gdx.files.internal("Meko/Meko.png"));

        actionCode=0;

        //Making animations here
        TextureAtlas leftWalkAtlas=new TextureAtlas(Gdx.files.internal("Meko/MekoLeft.atlas"));
        leftWalk=new Animation<TextureRegion>(1/7f,leftWalkAtlas.findRegions("M"), Animation.PlayMode.LOOP);

        TextureAtlas rightWalkAtlas=new TextureAtlas(Gdx.files.internal("Meko/MekoRight.atlas"));
        rightWalk=new Animation<TextureRegion>(1/7f,rightWalkAtlas.findRegions("M"), Animation.PlayMode.LOOP);

        updateFull();

    }

    public int hit(Rectangle anotherR)
    {
        if(full.overlaps(anotherR))
        {
            return 1;
        }

        return -1;
    }

    public void draw(SpriteBatch batch)
    {
        batch.draw(texture,full.x,full.y);

        setActionCode(0);
    }

    public void setPosition(float x,float y)
    {
        position.x=x;
        position.y=y;
    }

    public Rectangle getHitBox()
    {
        return full;
    }

    public void moveLeft(float delta)
    {
        position.x-=velocity*delta;

        updateFull();
        setActionCode(-1);
    }

    public void moveRight(float delta)
    {
        position.x+=velocity*delta;

        updateFull();

        setActionCode(1);
    }

    public void drawLeftWalkAnimation(float stateTime,SpriteBatch batch)
    {
        batch.draw(leftWalk.getKeyFrame(stateTime),full.x,full.y);
    }

    public void drawRightWalkAnimation(float stateTime,SpriteBatch batch)
    {
        batch.draw(rightWalk.getKeyFrame(stateTime),full.x,full.y);
    }

    public int getActionCode()
    {
        return actionCode;
    }

    public void setActionCode(int code)
    {
        actionCode=code;
    }

    public float getX()
    {
        return position.x;
    }

    public float getY()
    {
        return position.y;
    }

    public Rectangle getPosition()
    {
        return position;
    }

    public void updateFull()
    {
        full.x=position.x-41;
        full.y=position.y-8;
    }
}
