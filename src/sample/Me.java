package sample;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Me extends Gamer{
    int happenedKey;
    Me(int height, int width, Color color, String name) {
        super(height, width, color);
        this.name = name;
    }
    public void setHappenedKey(int happenedKey) {
        this.happenedKey = happenedKey;
    }
    @Override
    void move() {
        x += dx;
        y += dy;
    }

    void updateKey() {
        //Left
        if((happenedKey == KeyEvent.VK_LEFT || happenedKey == KeyEvent.VK_A) && dx != 1) {
            dx = -1;
            dy = 0;
        }
        //Right
        if((happenedKey == KeyEvent.VK_RIGHT || happenedKey == KeyEvent.VK_D) && dx != -1) {
            dx = 1;
            dy = 0;
        }
        //Up
        if((happenedKey == KeyEvent.VK_UP || happenedKey == KeyEvent.VK_W) && dy != 1) {
            dx = 0;
            dy = -1;
        }
        //Down
        if((happenedKey == KeyEvent.VK_DOWN || happenedKey == KeyEvent.VK_S) && dy != -1) {
            dx = 0;
            dy = 1;
        }
        //Shoot
        if (happenedKey == KeyEvent.VK_ENTER){

        }
        //Shoot2
        if (happenedKey == KeyEvent.VK_SPACE){

        }
    }
}
