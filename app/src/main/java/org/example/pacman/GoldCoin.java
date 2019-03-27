/*package org.example.pacman;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * This class should contain information about a single GoldCoin.
 * such as x and y coordinates (int) and whether or not the goldcoin
 * has been taken (boolean)
*//*

public class GoldCoin {

//TODO its not so delete this at some point
    protected boolean taken;

    public Context context;
    public Bitmap coinBitmap;

    private int cx = math.random;
    private int cy;

    public GoldCoin() {
    }

    //draws the gold coin
    private void init() {

        coinBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.coin);
    }

}
*/


package org.example.pacman;

import java.util.Random;

/**
 * This class should contain information about a single GoldCoin.
 * such as x and y coordinates (int) and whether or not the goldcoin
 * has been taken (boolean)
 */
//TODO is this right??
public class GoldCoin {


    private boolean taken = false;

    private int goldy;
    private int goldx;

    public GoldCoin(int goldx, int goldy, boolean taken){

        this.goldx = goldx;
        this.goldy = goldy;
        this.taken = taken;
    }

    public boolean isTaken() {
        return taken;
    }

    public void setTaken(boolean taken) {
        this.taken = taken;
    }

    public int getGoldy() {
        return goldy;
    }

    public void setGoldy(int goldy) {
        this.goldy = goldy;
    }

    public int getGoldx() {
        return goldx;
    }

    public void setGoldx(int goldx) {
        this.goldx = goldx;
    }
}