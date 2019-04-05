package org.example.pacman;

public class Enemy {

    private int ghostx, ghosty;
    private boolean isSet = false;
    private boolean moving = true;

    public Enemy(int ghostx, int ghosty) {
        this.ghostx = ghostx;
        this.ghosty = ghosty;

    }



    public int getGhostx() {
        return ghostx;
    }

    public int getGhosty() {
        return ghosty;
    }

    public void setGhostx(int ghostx) {
        this.ghostx = ghostx;
    }

    public void setGhosty(int ghosty) {
        this.ghosty = ghosty;
    }

    public boolean isSet() {
        return isSet;
    }

    public void setSet(boolean set) {
        isSet = set;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }
}
