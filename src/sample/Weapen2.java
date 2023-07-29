package sample;

import javax.swing.*;
import java.awt.*;

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
                    }
                }
            }
        }
    }
}
