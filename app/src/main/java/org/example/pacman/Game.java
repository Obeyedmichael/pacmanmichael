package org.example.pacman;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.TextView;


import java.util.ArrayList;


/**
 *
 * This class should contain all your game logic
 */

public class Game {
    //context is a reference to the activity
    private Context context;
    private int points = 0; //how points do we have

    //bitmap of the pacman
    private Bitmap pacBitmap;
    public Bitmap coinbitmap;
    //textview reference to points
    private TextView pointsView;
    private int pacx, pacy;
    //the list of goldcoins
    private ArrayList<GoldCoin> coins = new ArrayList<>();
    //a reference to the gameview
    private GameView gameView;
    //TODO create timer
    //TODO create levels or teleportation or maybe highscore
    //TODO create new class with enemies written like gold coin if you ever figure that out you fucking moron
    private int h,w; //height and width of screen
    //boolean to see if the game is running
    public boolean running = false;
    public int move;


    public Game(Context context, TextView view)
    {

        this.context = context;
        this.pointsView = view;
        pacBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.pacman);
        coinbitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.coin);


    }

    public void setGameView(GameView view)
    {
        this.gameView = view;
    }

    //TODO initialize goldcoins also here
    public void newGame()
    {
        coins.add(new GoldCoin(400,100,false));
        coins.add(new GoldCoin(900,400,false));
        coins.add(new GoldCoin(20,800,false));
        coins.add(new GoldCoin(600,60,false));
        coins.add(new GoldCoin(700,410,false));
        coins.add(new GoldCoin(900,1000,false));
        coins.add(new GoldCoin(500,800,false));
        coins.add(new GoldCoin(50,600,false));
        coins.add(new GoldCoin(820,800,false));
        coins.add(new GoldCoin(450,450,false));
        coins.add(new GoldCoin(250,780,false));


        pacx = 20;
        pacy = 20; //just some starting coordinates
        //reset the points
        points = 0;
        pointsView.setText(context.getResources().getString(R.string.points)+" "+points);
        gameView.invalidate(); //redraw screen
    }

    public void setSize(int h, int w)
    {
        this.h = h;
        this.w = w;
    }


    public void movePacmanLeft(int pixels)
    {
        //still within our boundaries?
        if (pacx+pixels+pacBitmap.getWidth()>0) {
            pacx = pacx - pixels;
            if (pacx < 1 ) {pacx = 1;}
            doCollisionCheck();
            gameView.invalidate();
        }
    }

    public void movePacmanRight(int pixels)
    {
        //still within our boundaries?
        if (pacx+pixels+pacBitmap.getWidth()<w) {
            pacx = pacx + pixels;
            doCollisionCheck();
            gameView.invalidate();
        }
    }

    public void movePacmanUp(int pixels)
    {
        //still within our boundaries?
        if (pacy+pixels+pacBitmap.getHeight()>0) {
            pacy = pacy - pixels;
            if (pacy < 1 ) {pacy = 1;}
            doCollisionCheck();
            gameView.invalidate();
        }
    }

    public void movePacmanDown(int pixels)
    {
        //still within our boundaries?
        if (pacy+pixels+pacBitmap.getHeight()<h) {
            pacy = pacy + pixels;
            doCollisionCheck();
            gameView.invalidate();
        }
    }

    //TODO check if the pacman touches a gold coin
    //and if yes, then update the neccesseary data
    //for the gold coins and the points
    //so you need to go through the arraylist of goldcoins and
    //check each of them for a collision with the pacman
    public void doCollisionCheck()
    {
        
    }

    public int getPacx()
    {
        return pacx;
    }

    public int getPacy()
    {
        return pacy;
    }

    public int getPoints()
    {
        return points;
    }

    public ArrayList<GoldCoin> getCoins()
    {
        return coins;
    }

    public Bitmap getPacBitmap()
    {
        return pacBitmap;
    }

    public Bitmap getCoinbitmap()
    {
        return coinbitmap;
    }


}
