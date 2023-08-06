package sample;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Bot extends Gamer{
    int Dificult;
    int counterbotweapon = 0;
    private int[] numbers = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32};

    Bot(int height, int width, Color color) {
        super(height, width, color);
        this.name = "Bot"+numbers[new Random().nextInt(numbers.length)];
        try {
            File myObj = new File("src/dificult.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                Dificult = Integer.parseInt(data);
                if (Dificult < 1)
                    Dificult = 1;
            }
            myReader.close();
        } catch (FileNotFoundException er) {
            System.out.println("An error occurred.");
            er.printStackTrace();
        }
    }

    @Override
    void move(Page page) {
        if (Dificult > 1 && this.counterbotweapon == 0){
            //DOWN
            if (this.getDx() == 0 && this.getDy() == 1) {
                for (int i = this.getX() - 1; i <= this.getX() + 1; i++) {
                    for (int j = this.getY() + 5; j <= this.getY() + 7; j++) {
                        for (int k = 0; k < page.gamers.size(); k++){
                            if (page.gamers.get(k).getX() == i && page.gamers.get(k).getY() == j){
                                Weapen weapen = new Weapen();
                                weapen.setOwned(page, this);
                                //System.out.println("bot weapen ok");
                                this.counterbotweapon++;
                            }
                        }
                    }
                }
            }
            //UP
            if (this.getDx() == 0 && this.getDy() == -1) {
                for (int i = this.getX() - 1; i <= this.getX() + 1; i++) {
                    for (int j = this.getY() - 7; j <= this.getY() - 5; j++) {
                        for (int k = 0; k < page.gamers.size(); k++){
                            if (page.gamers.get(k).getX() == i && page.gamers.get(k).getY() == j ){
                                Weapen weapen = new Weapen();
                                weapen.setOwned(page, this);
                                //System.out.println("bot weapen ok");
                                this.counterbotweapon++;
                            }
                        }
                    }
                }
            }
            //RIGHT
            if (this.getDx() == 1 && this.getDy() == 0) {
                for (int i = this.getX() + 5; i <= this.getX() + 7; i++) {
                    for (int j = this.getY() - 1; j <= this.getY() + 1; j++) {
                        for (int k = 0; k < page.gamers.size(); k++){
                            if (page.gamers.get(k).getX() == i && page.gamers.get(k).getY() == j){
                                Weapen weapen = new Weapen();
                                weapen.setOwned(page, this);
                                //System.out.println("bot weapen ok");
                                this.counterbotweapon++;
                            }
                        }
                    }
                }
            }
            //LEFT
            if (this.getDx() == -1 && this.getDy() == 0) {
                for (int i = this.getX() - 7; i <= this.getX() - 5; i++) {
                    for (int j = this.getY() - 1; j <= this.getY() + 1; j++) {
                        for (int k = 0; k < page.gamers.size(); k++){
                            if (page.gamers.get(k).getX() == i && page.gamers.get(k).getY() == j){
                                Weapen weapen = new Weapen();
                                weapen.setOwned(page, this);
                                //System.out.println("bot weapen ok");
                                this.counterbotweapon++;
                            }
                        }
                    }
                }
            }
        }
        if (Dificult > 2){
            goon(page.mes.get(0).getX(), page.mes.get(0).getY());
            if (Dificult > 3){
                for (int i = this.getX() - 3; i <= this.getX() + 3; i++){
                    for (int j = this.getY() - 3; j <= this.getY() + 3; j++){
                        if (page.getRect(i, j).getContestedOwner() == page.mes.get(0) /*&& page.getRect(i, j).getContestedOwner() != this*/){
                            goon(i, j);
                            return;
                        }
                    }
                }
            }
            return;
        }

        if (this.getRectsContested().size() < 15){
            x += dx;
            y += dy;
            double rand = Math.random();
            if (rand < 0.05 && dx != -1) {
                dx = 1;
                dy = 0;
            } else if (rand < 0.1 && dx != 1) {
                dx = -1;
                dy = 0;
            } else if (rand < 0.15 && dy != -1) {
                dx = 0;
                dy = 1;
            } else if (rand < 0.2 && dy != 1) {
                dx = 0;
                dy = -1;
            }
            avoidOutofpage();
        }
        else {
            goon(this.lasthomeX, this.lasthomeY);
        }
    }

    private void goon(int meX, int meY){
        x += dx;
        y += dy;
        if (this.getX() < meX){
            dx = 1;
            dy = 0;
        }
        else if (this.getX() > meX){
            dx = -1;
            dy = 0;
        }
        else if (this.getY() < meY){
            dx = 0;
            dy = 1;
        }
        else if (this.getY() > meY){
            dx = 0;
            dy = -1;
        }
        else if (this.getX() < meX && this.getY() < meY){
            double rand = Math.random();
            if (rand < 0.1 ){
                dx = 1;
                dy = 0;
            }
            else if (rand < 0.2){
                dx = 0;
                dy = 1;
            }
        }
        else if (this.getX() < meX && this.getY() > meY){
            double rand = Math.random();
            if (rand < 0.1 ){
                dx = 1;
                dy = 0;
            }
            else if (rand < 0.2){
                dx = 0;
                dy = -1;
            }
        }
        else if (this.getX() > meX && this.getY() < meY){
            double rand = Math.random();
            if (rand < 0.1 ){
                dx = -1;
                dy = 0;
            }
            else if (rand < 0.2){
                dx = 0;
                dy = 1;
            }
        }
        else if (this.getX() > meX && this.getY() > meY){
            double rand = Math.random();
            if (rand < 0.1 ){
                dx = -1;
                dy = 0;
            }
            else if (rand < 0.2){
                dx = 0;
                dy = -1;
            }
        }
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
