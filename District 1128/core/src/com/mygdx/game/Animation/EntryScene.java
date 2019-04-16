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

    public EntryScene(Meko givenMeko)
    {
        super();

        meko = givenMeko;

        doorOpen = new Texture(Gdx.files.internal("DoorOpen.png"));
        doorClosed = new Texture(Gdx.files.internal("DoorClosed.png"));

        future = 1;
        ms = 0;

        vickyCode = 0;

        vicky = new Vicky(meko.getX() + 250, 64);

        converse = new Conversation(meko);
        converse.addLine("Hurry up Vicky! The players are waiting!","Vicky");
        converse.addLine("Alright alright, I'm just getting dressed","Meko");
        converse.addLine("How do you think I look?","Meko");
        converse.addLine("Yea yea, very nice now move on","Vicky");
        converse.addLine("-TUTORIAL-","None");
        converse.addLine("Use the left arrow to move to the left","None");
        converse.addLine("Use the right arrow to move to the right","None");
        converse.addLine("Press the A button to attack","None");
        converse.addLine("Press the B button to interact with things","None");
        converse.addLine("You can interact with doors or totally not suspicious PORTALS!","None");



    }


    public void act()
    {
        Gdx.gl.glClearColor(0, 1, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        followCamera(meko);

        //This is keeping track of the time
        ms += Gdx.graphics.getDeltaTime();

        getBatch().begin();

        if (ms > future)
        {
            getBatch().draw(doorOpen, meko.getX(), 0, Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 4);

            if (vickyCode == 0)
            {
                vickyCode = 1;
            }

        }
        else
        {
            getBatch().draw(doorClosed, meko.getX(), 0, Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 4);
        }

        if (vickyCode == 1)
        {
            vicky.moveRight(Gdx.graphics.getDeltaTime());

            if (vicky.getX() >= 320)
            {
                vickyCode = -1;

                vicky.setActionCode(0);
            }
        }

        if (vickyCode != 0)
        {
            if (vicky.getActionCode() == -1)
            {
                vicky.drawLeftWalkAnimation(ms, getBatch());
            }
            else if (vicky.getActionCode() == 1)
            {
                vicky.drawRightWalkAnimation(ms, getBatch());
            }
            else
            {
                vicky.drawLeft(getBatch());
            }
        }

        //This means that Vicky is free to move now therefore we have to make her move to the right

        if(vicky.getActionCode()==0)
        {
            //Put Chen's conversation here just do .startConversation
            converse.startConversation(getBatch());

            System.out.println("The conversation begin");
            if (converse.isComplete())
            {
                setComplete(true);
            }
        }



        getBatch().end();
    }

}
