package com.mygdx.game.Animation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Actor.Vicky;

public class EntryScene extends NewScene
{
    //Future variables
    float ms;
    float future;

    boolean setVickyFree;

    //Necessary textures
    private Texture doorOpen;
    private Texture doorClosed;

    //Vicky
    Vicky vicky;

    public EntryScene()
    {
        super();

        doorOpen=new Texture(Gdx.files.internal("DoorOpen.png"));
        doorClosed=new Texture(Gdx.files.internal("DoorClosed.png"));

        future=9;
        ms=0;

        setVickyFree=false;

        vicky=new Vicky(0,0);
    }


    public void act() {
        Gdx.gl.glClearColor(0, 1, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //This is keeping track of the time
        ms += Gdx.graphics.getDeltaTime();

        //This means that Vicky is free to move now therefore we have to make her move to the right
        if (setVickyFree) {
            vicky.moveRight(Gdx.graphics.getDeltaTime());

            if (vicky.getHitBox().x >= 500) {
                setVickyFree = false;

                setComplete(true);
            }
        }

        getBatch().begin();

        if (ms > future) {
            getBatch().draw(doorOpen, 0, 0, Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 4);

            setVickyFree = true;
        } else {
            getBatch().draw(doorClosed, 0, 0, Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 4);
        }

        if(setVickyFree)
        {
            if (vicky.getActionCode() == -1)
            {
                vicky.drawLeftWalkAnimation(ms,getBatch());
            }
            else if(vicky.getActionCode()== 1)
            {
                vicky.drawRightWalkAnimation(ms,getBatch());
            }
            else
            {
                vicky.draw(getBatch());
            }
        }
        getBatch().end();
    }

}
