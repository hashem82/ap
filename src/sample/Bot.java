package sample;

import java.awt.*;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Bot extends Gamer{
    private int[] numbers = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32};

    Bot(int height, int width, Color color) {
        super(height, width, color);
        this.name = "Bot"+numbers[new Random().nextInt(numbers.length)];
    }

    @Override
    void move() {
        x += dx;
        y += dy;
        double rand = Math.random();
        if (rand < 0.05 && dx != -1) {
            dx = 1;
            dy = 0;
        }else if (rand < 0.1 && dx != 1) {
            dx = -1;
            dy = 0;
        }else if (rand < 0.15 && dy != -1) {
            dx = 0;
            dy = 1;
        }else if (rand < 0.2 && dy != 1) {
            dx = 0;
            dy = -1;
        }
        avoidOutofpage();
    }

    private void avoidOutofpage() {
        if(x == 0 && y == Height - 1){
            if(dx == -1){
                dx = 0;
                dy = -1;
            }else {
                dx = 1;
                dy = 0;
            }
        }else if(x == Width -1 && y == 0){
            if(dx == 1){
                dx = 0;
                dy = 1;
            } else {
                dx = -1;
                dy = 0;
            }
        }else if(x == Width - 1 && y == Height -1){
            if(dx == 1){
                dx = 0;
                dy = -1;
            }else {
                dx = -1;
                dy = 0;
            }
        }else if(x == 0 && y == 0){
            if(dx == -1){
                dx = 0;
                dy = 1;
            }else {
                dx = 1;
                dy = 0;
            }
        }else if(x == 0 && dx == -1){
            dx = 0;
            dy = 1;
        }else if(x == Width -1 &&  dx == 1){
            dx = 0;
            dy = 1;
        }else if(y == 0 && dy == -1){
            dx = 1;
            dy = 0;
        }else if(y == Height -1 && dy == 1){
            dx = 1;
            dy = 0;
        }
    }

    @Override
    public void killed() {
        super.killed();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                setAlive(true);
            }
        },5000);
    }
}
