package org.example.pacman;

/**
 * This class should contain information about a single GoldCoin.
 * such as x and y coordinates (int) and whether or not the goldcoin
 * has been taken (boolean)
 */
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


    public int getGoldx() {
        return goldx;
    }


}