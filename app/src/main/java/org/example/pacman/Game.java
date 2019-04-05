package org.example.pacman;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Random;


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
    //bitmap of coins
    private Bitmap coinbitmap;
    //bitmap of enemies
    private  Bitmap ghostBitmap;
    //textview reference to points
    private TextView pointsView;
    private int pacx, pacy;
    //the list of goldcoins
    private Boolean initCoins = false;
    private ArrayList<GoldCoin> coins = new ArrayList<>();
    private final int numberofcoinslvl1 = 11;
    private final int numberofcoinslvl2 = 6;
    //the list of enemies
    private Boolean initEnemies = false;
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private final int numberofenemieslvl1 = 2;
    private final int numberofenemieslvl2 = 4;
    private int ghostDirection;
    public int ghostspeed = 10;
    //a reference to the gameview
    private GameView gameView;
    private int h,w; //height and width of screen
    //boolean to see if the game is running
    public boolean running = false;
    public int move;
    public int lvl;


    public Game(Context context, TextView view)
    {

        this.context = context;
        this.pointsView = view;
        pacBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.pacman);
        coinbitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.coin);
        ghostBitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.ghost);


    }

    public void setGameView(GameView view)
    {
        this.gameView = view;
    }

    public void initCoins(){

            switch (lvl) {
                case 1:  for(int i = 0; i < numberofcoinslvl1; i++)
                {
                    coins.clear();
                    coins.add(new GoldCoin(400, 100, false));
                    coins.add(new GoldCoin(900, 400, false));
                    coins.add(new GoldCoin(20, 800, false));
                    coins.add(new GoldCoin(600, 60, false));
                    coins.add(new GoldCoin(700, 410, false));
                    coins.add(new GoldCoin(900, 1000, false));
                    coins.add(new GoldCoin(500, 800, false));
                    coins.add(new GoldCoin(50, 600, false));
                    coins.add(new GoldCoin(820, 800, false));
                    coins.add(new GoldCoin(450, 450, false));
                    coins.add(new GoldCoin(250, 780, false));
                }
                break;
                case 2:  for(int i = 0; i < numberofcoinslvl2; i++)
                {
                    coins.clear();
                    coins.add(new GoldCoin(900, 1000, false));
                    coins.add(new GoldCoin(500, 800, false));
                    coins.add(new GoldCoin(50, 600, false));
                    coins.add(new GoldCoin(820, 800, false));
                    coins.add(new GoldCoin(450, 450, false));
                    coins.add(new GoldCoin(250, 780, false));
                }
                break;
            }
    }

    public void initEnemies() {
        switch (lvl) {
            case 1:
                enemies.clear();
                for (int i = 0; i < numberofenemieslvl1; i++) {
                    enemies.add(new Enemy(900, 200));
                    enemies.add(new Enemy(900, 900));
                }
                break;
            case 2:
                enemies.clear();
                for (int i = 0; i < numberofenemieslvl2; i++) {
                    enemies.add(new Enemy(700, 300));
                    enemies.add(new Enemy(600, 700));
                    enemies.add(new Enemy(900, 200));
                    enemies.add(new Enemy(900, 900));
                }
                break;
        }
    }

    public void newGame()
    {
        lvl = 1;

        initCoins = false;
        for(GoldCoin g : coins){
            g.setTaken(true);

        }
        initEnemies = false;
        for(Enemy e : enemies){
            e.setSet(true);

        }

        pacx = 20;
        pacy = 20; //just some starting coordinates
        //reset the points
        points = 0;
        pointsView.setText(context.getResources().getString(R.string.points)+" "+points);
        gameView.invalidate(); //redraw screen
    }

    public void nextlevel()
    {
        lvl = 2;
        initCoins = false;
        for(GoldCoin g : coins){
            g.setTaken(true);

        }
        initEnemies = false;
        for(Enemy e : enemies){
            e.setSet(true);

        }

        pacx = 20;
        pacy = 20;

        pointsView.setText(context.getResources().getString(R.string.points)+" "+points);
        gameView.invalidate();
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
            dodeathcheck();
            gameView.invalidate();
        }
    }

    public void movePacmanRight(int pixels)
    {
        //still within our boundaries?
        if (pacx+pixels+pacBitmap.getWidth()<w) {
            pacx = pacx + pixels;
            doCollisionCheck();
            dodeathcheck();
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
            dodeathcheck();
            gameView.invalidate();
        }
    }

    public void movePacmanDown(int pixels)
    {
        //still within our boundaries?
        if (pacy+pixels+pacBitmap.getHeight()<h) {
            pacy = pacy + pixels;
            doCollisionCheck();
            dodeathcheck();
            gameView.invalidate();
        }
    }

    //check if the pacman touches a gold coin
    //and if yes, then update the neccesseary data
    //for the gold coins and the points
    //so you need to go through the arraylist of goldcoins and
    //check each of them for a collision with the pacman
    public void doCollisionCheck()
    {
        for (GoldCoin g: coins) {
            int x1 = pacx - g.getGoldx();
            int y1 = pacy - g.getGoldy();
            double sum = Math.sqrt((x1*x1)+(y1*y1));
            int r1 = pacBitmap.getHeight() + pacBitmap.getWidth() - 200;
            int r2 = coinbitmap.getHeight() + coinbitmap.getWidth() - 200;
            if (sum <= r1 + r2 && !g.isTaken()) {
                g.setTaken(true);
                points++;
                pointsView.setText(context.getResources().getString(R.string.points)+" "+points);
            }
            if (points == 11)
            {
                CharSequence text = "Next Level\n congratulations";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                nextlevel();

            }
            if (points == 17)
            {
                CharSequence text = "You Won\n congratulations";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                newGame();
                running = false;
            }
        }

    }

    public void dodeathcheck()
    {
        for (Enemy e: enemies) {
            int dx1 = pacx - e.getGhostx();
            int dy1 = pacy - e.getGhosty();
            double dsum = Math.sqrt((dx1*dx1)+(dy1*dy1));
            int dr1 = pacBitmap.getHeight() + pacBitmap.getWidth() - 250;
            int dr2 = ghostBitmap.getHeight() + ghostBitmap.getWidth() - 250;
            if (dsum <= dr1 + dr2) {
                CharSequence text = "Game over\n" +  "You got " + getPoints() + " points\n Press Continue to try again";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                newGame();
                running = false;

            }
        }
    }

    public void moveEnemies() {
        for (Enemy enemy : enemies) {

            Random randDirection = new Random(System.currentTimeMillis() - 500);
            ghostDirection = randDirection.nextInt(4);

            switch (getGhostDirection()) {
                case 0: // UP
                    if (enemy.getGhosty() >= ghostspeed + getGhostBitmap().getHeight() - 130) {
                        enemy.setGhosty(enemy.getGhosty() - ghostspeed);
                        gameView.invalidate();
                    }

                    break;

                case 1: // RIGHT
                    if (enemy.getGhostx() + ghostspeed + getGhostBitmap().getWidth() <= gameView.getWidth()) {
                        enemy.setGhostx(enemy.getGhostx() + ghostspeed);
                        gameView.invalidate();
                    }
                    break;

                case 2: // DOWN
                    if (enemy.getGhosty() + ghostspeed + getGhostBitmap().getHeight() <= gameView.getHeight()) {
                        enemy.setGhosty(enemy.getGhosty() + ghostspeed);
                        gameView.invalidate();

                    }
                    break;

                case 3: // LEFT

                    if (enemy.getGhostx() >= ghostspeed + getGhostBitmap().getWidth() - 140) {
                        enemy.setGhostx(enemy.getGhostx() - ghostspeed);
                        gameView.invalidate();
                    }
                    break;
            }
        }
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

    public boolean InitCoins(){return initCoins;}
    public boolean SetCoins(boolean value) {
        return initCoins = value;
    }

    public ArrayList<Enemy> getEnemies() {return enemies; }

    public boolean InitEnemies(){return initEnemies;}
    public boolean SetEnemies(boolean value){
        return initEnemies = value;
    }

    public Bitmap getPacBitmap()
    {
        return pacBitmap;
    }

    public Bitmap getCoinbitmap()
    {
        return coinbitmap;
    }

    public Bitmap getGhostBitmap() {return  ghostBitmap;}

    public void setGhostDirection(int ghostDirection) {
        this.ghostDirection = ghostDirection;
    }
    public int getGhostDirection() {
        return ghostDirection;
    }


}
