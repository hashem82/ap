package sample;

import javafx.application.Platform;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Weapen2 {
    public Weapen2(){

    }

    public void Shooting(Page page, Me me){
        //DOWN
        if (me.getDx() == 0 && me.getDy() == 1){
            for (int i = me.getY()+1; i < page.GameHeigh; i++){
                for (Gamer gamer : page.gamers){
                    if (gamer.getY() == i && gamer.getX() == me.getX()) {
                        gamer.killed();
                        String s = gamer.getName();
                        System.out.println(s + " killed by weapen2");
                        for (int k = me.getY() + 4; k < gamer.getY(); k++){
                            me.setRectOwned(page.getRect(me.getX(), k));
                        }
                        Timer timer = new Timer();
                        TimerTask task = new TimerTask() {
                            @Override
                            public void run() {
                                Platform.runLater(() -> {
                                    for (int k = me.getY() + 4; k < gamer.getY(); k++){
                                        page.getRect(me.getX(), k).setOwner(null);
                                        me.removeRectOwned(page.getRect(me.getX(), k));
                                    }
                                });
                            }
                        };
                        timer.schedule(task , 100);
                        return;
                    }
                }
            }
        }
        //UP
        if (me.getDx() == 0 && me.getDy() == -1){
            for (int i = me.getY()-1; i > 0; i--){
                for (Gamer gamer : page.gamers){
                    if (gamer.getY() == i && gamer.getX() == me.getX()) {
                        gamer.killed();
                        String s = gamer.getName();
                        System.out.println(s + " killed by weapen2");
                        for (int k = me.getY() - 4; k > gamer.getY(); k--){
                            me.setRectOwned(page.getRect(me.getX(), k));
                        }
                        Timer timer = new Timer();
                        TimerTask task = new TimerTask() {
                            @Override
                            public void run() {
                                Platform.runLater(() -> {
                                    for (int k = me.getY() - 4; k > gamer.getY(); k--){
                                        page.getRect(me.getX(), k).setOwner(null);
                                        me.removeRectOwned(page.getRect(me.getX(), k));
                                    }
                                });
                            }
                        };
                        timer.schedule(task , 100);
                        return;
                    }
                }
            }
        }
        //RIGHT
        if (me.getDy() == 0 && me.getDx() == 1){
            for (int i = me.getX()+1; i < page.GameWidth; i++){
                for (Gamer gamer : page.gamers){
                    if (gamer.getX() == i && gamer.getY() == me.getY()) {
                        gamer.killed();
                        String s = gamer.getName();
                        System.out.println(s + " killed by weapen2");
                        for (int k = me.getX() + 4; k < gamer.getX(); k++){
                            me.setRectOwned(page.getRect(k, me.getY()));
                        }
                        Timer timer = new Timer();
                        TimerTask task = new TimerTask() {
                            @Override
                            public void run() {
                                Platform.runLater(() -> {
                                    for (int k = me.getX() + 4; k < gamer.getX(); k++){
                                        page.getRect(k, me.getY()).setOwner(null);
                                        me.removeRectOwned(page.getRect(k, me.getY()));
                                    }
                                });
                            }
                        };
                        timer.schedule(task , 300);
                        return;
                    }
                }
            }
        }
        //LEFT
        if (me.getDy() == 0 && me.getDx() == -1){
            for (int i = me.getX()-1; i > 0; i--){
                for (Gamer gamer : page.gamers){
                    if (gamer.getX() == i && gamer.getY() == me.getY()) {
                        gamer.killed();
                        String s = gamer.getName();
                        System.out.println(s + " killed by weapen2");
                        for (int k = me.getX() - 4; k > gamer.getX(); k--){
                            me.setRectOwned(page.getRect(k, me.getY()));
                        }
                        Timer timer = new Timer();
                        TimerTask task = new TimerTask() {
                            @Override
                            public void run() {
                                Platform.runLater(() -> {
                                    for (int k = me.getX() - 4; k > gamer.getX(); k--){
                                        page.getRect(k, me.getY()).setOwner(null);
                                        me.removeRectOwned(page.getRect(k, me.getY()));
                                    }
                                });
                            }
                        };
                        timer.schedule(task , 300);
                        return;
                    }
                }
            }
        }
    }
}
