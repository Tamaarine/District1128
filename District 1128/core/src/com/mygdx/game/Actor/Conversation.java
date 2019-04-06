package com.mygdx.game.Actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

import java.util.ArrayList;

public class Conversation implements Disposable {

    //Instance variables
    private ArrayList<Line> lines;
    private int lineCounter;

    //For future
    private float future;
    private float ms;

    //Meko's position
    Meko meko;

    public Conversation(Meko givenMeko)
    {
        lines=new ArrayList<Line>();
        lineCounter=0;
        meko=givenMeko;


        //These variables are used to handle the timing of pressing the conversation
        ms=0;
        future=0;
    }

    public void startConversation(SpriteBatch batch)
    {
        float x=meko.getHitBox().x;


        ms=ms+Gdx.graphics.getDeltaTime();

        if(lineCounter<lines.size())
        {
            lines.get(lineCounter).drawLine(batch,x-Gdx.graphics.getWidth()/8);
        }

        checkToGoNextLine();

    }

    public void addLine(String newLine,String newName)
    {
        lines.add(new Line(newLine,newName));
    }

    private void checkToGoNextLine()
    {
        //Future for a cooldown so that the user won't zoom through the conversation
        if(Gdx.input.isTouched()&&ms>future)
        {
            future=ms+1f;

            //This means that the screen is pressed to tell the line to go to the next line
            if(lineCounter<lines.size())
            {
                lineCounter++;
            }
        }
    }

    //Accessor method to tell whether the conversation is finished or not
    public boolean isComplete()
    {
        if(lineCounter==lines.size())
        {
            return true;
        }

        return false;
    }

    //The dispose method that is required from implementing Disposable
    public void dispose()
    {
        for(Line e:lines)
        {
            e.dispose();
        }
    }
}
