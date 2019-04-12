package com.mygdx.game.Outdated;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class Conversation
{
    ArrayList<Dialogue> conversation=new ArrayList<Dialogue>();
    private int conversationCounter;

    public Conversation()
    {
        conversationCounter=0;
        conversation=new ArrayList<Dialogue>();
    }

    public void addDialogue(String a,String b,String c,String name)
    {
        conversation.add(new Dialogue(a,b,c,name));
    }

    public void nextLine(SpriteBatch batch)
    {
        if(conversationCounter<conversation.size())
        {
            conversation.get(conversationCounter).drawDialogue(batch);

            if(conversation.get(conversationCounter).isComplete())
            {
                conversationCounter++;
            }
        }
    }

    public boolean isComplete()
    {
        if(conversationCounter==conversation.size())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

}
