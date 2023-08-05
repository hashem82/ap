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
        int Y = me.getY();
        int X = me.getX();
        int BY;
        int BX;
        //DOWN
        if (me.getDx() == 0 && me.getDy() == 1){
            for (int i = Y +1; i < page.GameHeigh; i++){
                for (Gamer gamer : page.gamers){
                    if (gamer.getY() == i && gamer.getX() == X ) {
                        BY = i;
                        gamer.killed();
                        String s = gamer.getName();
                        System.out.println(s + " killed by weapen2");
                        for (int k = Y + 4; k < gamer.getY(); k++){
                            me.setRectOwned(page.getRect(X, k));
                        }
                        Timer timer = new Timer();
                        TimerTask task = new TimerTask() {
                            @Override
                            public void run() {
                                Platform.runLater(() -> {
                                    for (int k = Y + 4; k < BY; k++){
                                        page.getRect(X, k).setOwner(null);
                                        me.removeRectOwned(page.getRect(X, k));
                                    }
                                });
                            }
                        };
                        timer.schedule(task , page.timerspeed*27);
                        return;
                    }
                }
            }
            for (int j = me.getY() + 1; j < page.GameHeigh; j++){
                if (page.getRect(X, j).owner == me) {
                    me.removeRectOwned(page.getRect(X, j));
                    page.getRect(X, j).setOwner(null);
                }
                me.setRectsContested(page.getRect(X, j));
            }
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(() -> {
                        for (int k = me.getY() + 1; k < page.GameHeigh; k++){
                            page.getRect(X, k).setContestedOwner(null);
                            me.removeRectContested(page.getRect(X, k));
                        }
                    });
                }
            };
            timer.schedule(task , page.timerspeed*27);
        }
        //UP
        if (me.getDx() == 0 && me.getDy() == -1){
            for (int i = Y-1; i > 0; i--){
                for (Gamer gamer : page.gamers){
                    if (gamer.getY() == i && gamer.getX() == X) {
                        BY = i;
                        gamer.killed();
                        String s = gamer.getName();
                        System.out.println(s + " killed by weapen2");
                        for (int k = Y - 4; k > BY; k--){
                            me.setRectOwned(page.getRect(X, k));
                        }
                        Timer timer = new Timer();
                        TimerTask task = new TimerTask() {
                            @Override
                            public void run() {
                                Platform.runLater(() -> {
                                    for (int k = Y - 4; k > BY; k--){
                                        page.getRect(X, k).setOwner(null);
                                        me.removeRectOwned(page.getRect(X, k));
                                    }
                                });
                            }
                        };
                        timer.schedule(task , page.timerspeed*27);
                        return;
                    }
                }
            }
            for (int j = me.getY() - 1; j > 0; j--){
                if (page.getRect(X, j).owner == me) {
                    me.removeRectOwned(page.getRect(X, j));
                    page.getRect(X, j).setOwner(null);
                }
                me.setRectsContested(page.getRect(X, j));
            }
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(() -> {
                        for (int k = me.getY() - 1; k > 0; k--){
                            page.getRect(X, k).setContestedOwner(null);
                            me.removeRectContested(page.getRect(X, k));
                        }
                    });
                }
            };
            timer.schedule(task , page.timerspeed*27);
        }
        //RIGHT
        if (me.getDy() == 0 && me.getDx() == 1){
            for (int i = X+1; i < page.GameWidth; i++){
                for (Gamer gamer : page.gamers){
                    if (gamer.getX() == i && gamer.getY() == Y) {
                        BX = i;
                        gamer.killed();
                        String s = gamer.getName();
                        System.out.println(s + " killed by weapen2");
                        for (int k = X + 4; k < BX; k++){
                            me.setRectOwned(page.getRect(k, Y));
                        }
                        Timer timer = new Timer();
                        TimerTask task = new TimerTask() {
                            @Override
                            public void run() {
                                Platform.runLater(() -> {
                                    for (int k = X + 4; k < BX; k++){
                                        page.getRect(k, Y).setOwner(null);
                                        me.removeRectOwned(page.getRect(k, Y));
                                    }
                                });
                            }
                        };
                        timer.schedule(task , page.timerspeed*27);
                        return;
                    }
                }
            }
            for (int j = me.getX() + 1; j < page.GameWidth; j++){
                if (page.getRect(j, Y).owner == me) {
                    me.removeRectOwned(page.getRect(j, Y));
                    page.getRect(j, Y).setOwner(null);
                }
                me.setRectsContested(page.getRect(j, Y));
            }
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(() -> {
                        for (int k = me.getX() + 1; k < page.GameWidth; k++){
                            page.getRect(k, Y).setContestedOwner(null);
                            me.removeRectContested(page.getRect(k, Y));
                        }
                    });
                }
            };
            timer.schedule(task , page.timerspeed*27);
        }
        //LEFT
        if (me.getDy() == 0 && me.getDx() == -1){
            for (int i = X-1; i > 0; i--){
                for (Gamer gamer : page.gamers){
                    if (gamer.getX() == i && gamer.getY() == Y) {
                        BX = i;
                        gamer.killed();
                        String s = gamer.getName();
                        System.out.println(s + " killed by weapen2");
                        for (int k = X - 4; k > BX; k--){
                            me.setRectOwned(page.getRect(k, Y));
                        }
                        Timer timer = new Timer();
                        TimerTask task = new TimerTask() {
                            @Override
                            public void run() {
                                Platform.runLater(() -> {
                                    for (int k = X - 4; k > BX; k--){
                                        page.getRect(k, Y).setOwner(null);
                                        me.removeRectOwned(page.getRect(k, Y));
                                    }
                                });
                            }
                        };
                        timer.schedule(task , page.timerspeed*27);
                        return;
                    }
                }
            }
            for (int j = me.getX() - 1; j > 0; j--){
                if (page.getRect(j, Y).owner == me) {
                    me.removeRectOwned(page.getRect(j, Y));
                    page.getRect(j, Y).setOwner(null);
                }
                me.setRectsContested(page.getRect(j, Y));
            }
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(() -> {
                        for (int k = me.getX() - 1; k > 0; k--){
                            page.getRect(k, Y).setContestedOwner(null);
                            me.removeRectContested(page.getRect(k, Y));
                        }
                    });
                }
            };
            timer.schedule(task , page.timerspeed*27);
        }
    }
}
