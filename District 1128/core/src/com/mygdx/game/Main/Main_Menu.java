package com.mygdx.game.Main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;


public class Main_Menu implements Screen
{
    MyGdxGame MenuScreen;
    OrthographicCamera cam = new OrthographicCamera();
    Sprite ShopLoc;
    Sprite TitleLoc;
    Sprite selectBox;
    Sprite goRight;
    Sprite goLeft;
    Sprite scanLoc;
    Sprite earthLoc;
    SpriteBatch batch;
    Vector3 touchPos = new Vector3();
    Texture lvl1;
    Texture startGame;
    Texture barcodeScan;
    Texture shop;
    Texture MainIcon;
    Texture background;
    Texture SelIcon;
    Texture Right;
    Texture Left;
    Texture scan;
    Texture Earth;
    //Animation<TextureRegion> animation;
    float elapsed;
    public int crownCount = 0;
    public int compen = mapX(240);
    boolean isTouched = false;
    boolean scrollRight = false;
    boolean scrollLeft = false;
    public int initFinalW = 75;
    public int initFinalH = 80;
    public int scanFinalW = 100;
    public int scanFinalH = 120;
    public int earthFinalW = 75;
    public int earthFinalH = 80;
    //Texture Positions
    int screenWidth = Gdx.graphics.getWidth();
    int screenHeight = Gdx.graphics.getHeight();
    public int RelativeScreenSizeW = 1280;
    public int RelativeScreenSizeH = 720;
    public int MenuX = screenWidth/2-100;
    public int MenuY = screenHeight-200;

    public int spaceBet = 130;
    public int shopX = mapX(300);
    public int shopY = mapY(250);

    public int bcX = mapX(350);
    public int bcY = mapY(300);

    public int startX = mapX(screenWidth/2 - 80);
    public int startY = mapY(screenHeight/2 -125);

    public int lvl1X = mapX(100);
    public int lvl1Y = mapY(50);

    public int Min = 130;
    public int Max = 930;


    //RELATIVE LOCATIONS---------------------------------------------------------------------------------
    public int initX = mapX(spaceBet+ spaceBet);
    public int initY = mapY(150);
    public float initSizeW = mapX(75);
    public float initSizeH = mapY(80);

    public float initScanW = mapY(150);
    public float initScanH = mapY(170);
    public int scanX = mapX(screenWidth/2-75 + 10);
    public int scanY = mapY(140);

    public float initEarthW = 75;
    public float initEarthH = 80;
    public int earthX = mapX(screenWidth/2 + 4*earthFinalW);
    public int earthY = mapY(150);

    //public int scanX = mapX(spaceBet*2 + initSizeW);
    //----------------------------------------------------------------------------------------------------
    //ANY MOVEMENT WITH THE SCROLL NEEDS DIFFERENT SIZES WITH A COMPENSATION FACTOR

    public double deltaNew = mapX(6);
    public double deltaSize = .85;
    public float boxW = mapX(300);
    public float boxH = mapY(300);

    public float accel = 2;
    public float boxAcc = 1;

    public int titleX = mapX(200);
    public int titleY = mapY(250);


    public int goRightW = 130;
    public int goRightH = 190;

    public int goLeftW = 130;
    public int goLeftH = 190;
    public Main_Menu(MyGdxGame game)
    {
        MenuScreen = game;
        batch = new SpriteBatch();
        //lvl1 = new Texture("level_1.png");
        startGame = new Texture("Title.png");
        MainIcon = new Texture("MainMenu.png");
        shop = new Texture("shop.png");
        background = new Texture("backgroundMain.png");
        SelIcon = new Texture("Select.png");
        Right = new Texture("scrollRight.png");
        Left = new Texture("scrollLeft.png");
        scan = new Texture("scanIcon.png");
        Earth = new Texture("earth-icon.png");
        goRight = new Sprite(Right);
        goLeft = new Sprite(Left);
        ShopLoc = new Sprite(shop);
        ShopLoc.setPosition(initX,initY);
        ShopLoc.setSize(initSizeW,initSizeW);
        TitleLoc = new Sprite(startGame);
        TitleLoc.setSize(titleX,titleY);
        TitleLoc.setPosition(startX,startY);
        selectBox = new Sprite(SelIcon);
        selectBox.setSize(boxW,boxH);
        selectBox.setPosition(screenWidth/2-boxW/2,screenHeight/2-boxH/2);
        goRight.setSize(goRightW, goRightH);
        goRight.setPosition(mapX(screenWidth - compen + 75), mapY(selectBox.getY() + 120));
        goLeft.setSize(goLeftW,goLeftH);
        goLeft.setPosition(mapX(compen - 185), mapY(selectBox.getY() + 120));
        scanLoc = new Sprite(scan);
        scanLoc.setSize(initScanW,initScanH);
        scanLoc.setPosition(scanX,scanY);
        earthLoc = new Sprite(Earth);
        earthLoc.setSize(initEarthW, initEarthH);
        earthLoc.setPosition(earthX,earthY);
        //animation = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal("fallingRain.gif").read());
    }

    @Override
    public void show()
    {

    }

    @Override
    public void render(float delta)
    {
        if (Gdx.input.isTouched())
        {

            if (isHovering(TitleLoc))
            {
                MenuScreen.setScreen(new GameScreen(MenuScreen));
            }
            if(isHovering(goRight))
            {
                scrollRight = true;
                ShopLoc.translate(initX,initY);
                scanLoc.translate(scanX,scanY);
                earthLoc.translate(earthX,earthY);
            }
            else
            {
                scrollRight = false;
            }
            if(isHovering(goLeft))
            {
                scrollLeft = true;
                ShopLoc.translate(initX,initY);
                scanLoc.translate(scanX,scanY);
                earthLoc.translate(earthX,earthY);
            }
            else
            {
                scrollLeft = false;
            }
            if(isHovering(earthLoc))
            {
                //put code for earth Icon here
            }
            else{}
            if(isHovering(scanLoc))
            {
                //CorwnCount changes here

            }
            else
            {

            }
            if(overShop())
            {
                MenuScreen.setScreen(new ShopClass(MenuScreen));
                //game.setScreen(new GameScreen(game));
            }
        }
        else
        {
            scrollRight = false;
            scrollLeft = false;
        }


        if(scrollRight)
        {
            initX += deltaNew;
            scanX += deltaNew;
            earthX += deltaNew;
            if (initX + ShopLoc.getWidth()/2>= screenWidth / 2)
            {
                initSizeW -= deltaSize;
                initSizeH -= deltaSize;

            } else {
                initSizeW += deltaSize;
                initSizeH += deltaSize;

            }
            if(scanX + scanLoc.getWidth()/2 >= screenWidth/2)
            {
                initScanH -= deltaSize;
                initScanW -= deltaSize;
            }
            else
            {
                initScanH += deltaSize;
                initScanW += deltaSize;
            }
            if(earthX + earthLoc.getWidth()/2 >= screenWidth/2)
            {
                initEarthH -= deltaSize;
                initEarthW -= deltaSize;
            }
            else
            {
                initEarthH += deltaSize;
                initEarthW += deltaSize;
            }

        }
        if(scrollLeft)
        {
                initX -= deltaNew;
                scanX -= deltaNew;
                earthX -= deltaNew;
                ShopLoc.translate(initX,initY);
                scanLoc.translate(scanX,scanY);
                earthLoc.translate(earthX,earthY);
                if(earthX + earthLoc.getWidth()/2 <= screenWidth/2)
                {
                    initEarthH -= deltaSize;
                    initEarthW -= deltaSize;
                }
                else
                {
                    initEarthH += deltaSize;
                    initEarthW += deltaSize;
                }
            if (initX + ShopLoc.getWidth()/2 <= screenWidth / 2)
            {
                initSizeW -= deltaSize;
                initSizeH -= deltaSize;

            } else {
                initSizeW += deltaSize;
                initSizeH += deltaSize;

            }
            if(scanX + scanLoc.getWidth()/2 <= screenWidth/2)
            {
                initScanH -= deltaSize;
                initScanW -= deltaSize;
            }
            else
            {
                initScanH += deltaSize;
                initScanW += deltaSize;
            }

        }
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        //batch.draw(animation.getKeyFrame(elapsed), 20.0f, 20.0f);
        batch.draw(background,0,0,screenWidth,screenHeight);
        batch.draw(MainIcon,MenuX,MenuY,mapX(275),mapY(150));
        //if(isTouched == false)
        //{
        ShopLoc.translate(initX,initY);
        scanLoc.translate(scanX,scanY);
        earthLoc.translate(earthX,earthY);
        if(Gdx.input.isTouched()) {
            if(isHovering(ShopLoc) || overShop())
            {
                MenuScreen.setScreen(new ShopClass(MenuScreen));
            }
        }
/**
            batch.draw(ShopLoc, initX, initY, initSizeW, initSizeH);
            batch.draw(scanLoc,scanX,scanY,initScanW,initScanH);
            batch.draw(earthLoc,earthX,earthY,initEarthW,initEarthH);
 **/
        //}
       batch.draw(startGame,startX,startY, mapX(200), mapY(250));

       if(initX  >= goRight.getX())
       {
           initX = (int)goLeft.getX() + goLeftW;//(int)ShopLoc.getWidth()/2 + compen;
           initSizeW = initFinalW;
           initSizeH = initFinalH;

       }

       if(initX + initSizeW<= goLeft.getX() + goLeftW)
       {
           initX = (int)goRight.getX();
           initSizeW = initFinalW;
           initSizeH = initFinalH;
       }

       if(scanX >= goRight.getX())
       {
           scanX = (int)goLeft.getX() + goLeftW;
           initScanW = scanFinalW;
           initScanH = scanFinalH;
       }

       if(scanX + initScanW<= goLeft.getX() + goLeftW)
       {
           scanX = (int)goRight.getX();
           initScanW = scanFinalW;
           initScanH = scanFinalH;

       }

       if(earthX >= goRight.getX())
       {
           earthX = (int)goLeft.getX() + goLeftW;
           initEarthW = earthFinalW;
           initEarthH = earthFinalH;
       }

       if(earthX + initEarthW<= goLeft.getX() + goLeftW)
       {
           earthX = (int)goRight.getX();
           initEarthW = earthFinalW;
           initEarthH = earthFinalH;
       }

        batch.draw(selectBox,mapX(screenWidth/2-boxW/2 + 14),screenHeight/2-boxH + mapY(10),boxW,boxH);
        batch.draw(goRight,mapX(screenWidth - compen + 75), mapY(selectBox.getY() - 75), mapX(goRightW), mapY(goRightH - 25));
        batch.draw(goLeft,mapX(compen - 200), mapY(selectBox.getY() - 75), mapX(goLeftW), mapY(goLeftH - 25));
        batch.draw(scanLoc,scanX,scanY,initScanW,initScanH);
        batch.draw(earthLoc,earthX,earthY,initEarthW,initEarthH);
        batch.draw(ShopLoc, initX, initY, initSizeW, initSizeH);
        if(Gdx.input.isTouched()) {
            if(isHovering(selectBox))
            {
                MenuScreen.setScreen(new Statscreen(MenuScreen));
            }
        }
        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();

    }
    public boolean isHovering(Sprite sprite)
    {
        if (Gdx.input.isTouched()) {

            if (Gdx.input.getX() > sprite.getX() && Gdx.input.getX() < sprite.getX() + sprite.getWidth())
            {
                if (Gdx.input.getY() > sprite.getY() && Gdx.input.getY() < sprite.getY() + sprite.getHeight())
                {

                    return true;
                }
            }
        }
        return false;
    }

    public int mapX(double pixels)
    {
        double ratio = Gdx.graphics.getWidth()/(RelativeScreenSizeW+ 1);
        return (int)pixels;
        //(int)(ratio*pixels);
    }
    public int mapY(double pixels)
    {
        double ratio = Gdx.graphics.getHeight()/(RelativeScreenSizeH+1);
        return (int)pixels;//(int)(ratio*pixels);
    }
    public void ScrollRight(Sprite sprite)
    {

    }
    public double compenSize(int Pos, int min, int max)
    {
        //THIS HAS BEEN TESTED BY WALID KHAN
        //*max-min)/2 should be the maximum size
        int center = (int)((max-min)/2) + min;
        //distance away from center
        int dist = Math.abs(Pos-center);
        double ratio = dist/center;
        return 1- Math.abs(ratio) + .5;
    }
    public boolean overShop()
    {
        if(Gdx.input.isTouched())
        {
            int x = Gdx.input.getX();
            int y = Gdx.input.getY();
            if((Gdx.input.getX() > initX && Gdx.input.getX() < initX + initSizeW))
        {
            if (Gdx.input.getY() > initY && Gdx.input.getY() < initY + initSizeH)
            {

                return true;
            }

        }
        }
        return false;

}

}
