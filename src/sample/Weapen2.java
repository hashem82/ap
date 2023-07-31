package sample;

import javafx.application.Platform;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Weapen2 {
    public Weapen2(){

    }
    boolean setnull = false;

    public void Shooting(Page page, Me me){
        //DOWN
        if (me.getDx() == 0 && me.getDy() == 1){
            for (int i = me.getY()+1; i < page.GameHeigh; i++){
                for (Gamer gamer : page.gamers){
                    if (gamer.getY() == i && gamer.getX() == me.getX()) {
                        gamer.killed();
                        String s = gamer.getName();
                        System.out.println(s + " killed by weapen2");
                        /*for (int k = me.getY() + 4; k < gamer.getY(); k++){
                            me.setRectOwned(page.getRect(me.getX(), k));
                        }
                                setnull = true;
                        if (setnull){
                            for (int k = me.getY() + 4; k < gamer.getY(); k++){
                                page.getRect(me.getX(), k).setOwner(null);
                                me.removeRectOwned(page.getRect(me.getX(), k));
                            }
                            setnull = false;
                        }*/
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
                        /*for (int k = me.getY() - 4; k > gamer.getY(); k--){
                            me.setRectOwned(page.getRect(me.getX(), k));
                        }
                                setnull = true;
                        if (setnull){
                            for (int k = me.getY() - 4; k > gamer.getY(); k--){
                                page.getRect(me.getX(), k).setOwner(null);
                                me.removeRectOwned(page.getRect(me.getX(), k));
                            }
                            setnull = false;
                        }*/
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
                        /*for (int k = me.getX() + 4; k < gamer.getX(); k++){
                            me.setRectOwned(page.getRect(k, me.getY()));
                        }
                                setnull = true;
                        if (setnull){
                            for (int k = me.getX() + 4; k < gamer.getX(); k++){
                                page.getRect(k, me.getY()).setOwner(null);
                                me.removeRectOwned(page.getRect(k, me.getY()));
                            }
                            setnull = false;
                        }*/
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
                        /*for (int k = me.getX() - 4; k > gamer.getX(); k--){
                            me.setRectOwned(page.getRect(k, me.getY()));
                        }
                                setnull = true;
                        if (setnull){
                            for (int k = me.getX() - 4; k > gamer.getX(); k--){
                                page.getRect(k, me.getY()).setOwner(null);
                                me.removeRectOwned(page.getRect(k, me.getY()));
                            }
                            setnull = false;
                        }*/
                    }
                }
            }
        }
    }
}
