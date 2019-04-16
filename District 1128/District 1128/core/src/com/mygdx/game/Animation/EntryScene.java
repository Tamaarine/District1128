package com.mygdx.game.Animation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Actor.Conversation;
import com.mygdx.game.Actor.Meko;
import com.mygdx.game.Actor.Vicky;

public class EntryScene extends NewScene
{
    //Future variables
    float ms;
    float future;

    //Necessary textures
    private Texture doorOpen;
    private Texture doorClosed;

    //Vicky
    Vicky vicky;
    int vickyCode;

    //Meko
    Meko meko;

    //Conversations
    Conversation converse;

    public EntryScene()
    {
        super();

        doorOpen=new Texture(Gdx.files.internal("DoorOpen.png"));
        doorClosed=new Texture(Gdx.files.internal("DoorClosed.png"));

        future=1;
        ms=0;

        vickyCode=0;

        vicky=new Vicky(164,64);

        converse=new Conversation(meko);
        converse.addLine("Hurry up!","Vicky");
        converse.addLine("Hey I'm coming!","Meko");
        converse.addLine("I need to find a book in the library,","Meko");
        converse.addLine("Would you mind waiting me for a second?","Meko");
        converse.addLine("Of course not!","Vicky");
        converse.addLine("You've got a mission,"," ");
        converse.addLine("Find \"Silent Spring \" from the library."," ");

    }


    public void act() {
        Gdx.gl.glClearColor(0, 1, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //This is keeping track of the time
        ms += Gdx.graphics.getDeltaTime();

        getBatch().begin();

        if (ms > future) {
            getBatch().draw(doorOpen, 0, 0, Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 4);

            if(vickyCode==0)
            {
                vickyCode=1;
            }

        } else {
            getBatch().draw(doorClosed, 0, 0, Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 4);
        }

        if(vickyCode!=0)
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
                vicky.drawLeft(getBatch());
            }
        }

        //This means that Vicky is free to move now therefore we have to make her move to the right
        if (vickyCode==1) {
            vicky.moveRight(Gdx.graphics.getDeltaTime());

            if (vicky.getX() >= 260) {
                vickyCode=-1;

                vicky.setActionCode(0);
            }
        }
        else
        {
            //Put Chen's conversation here just do .startConversation
            converse.startConversation(getBatch());

            if(converse.isComplete())
            {
                setComplete(true);
            }
        }


        getBatch().end();
    }

}
