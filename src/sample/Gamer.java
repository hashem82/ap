package sample;

import java.awt.*;
import java.util.ArrayList;

public abstract class Gamer implements Comparable<Gamer>{

    ArrayList<Rect> rectsOwned = new ArrayList<>();
    ArrayList<Rect> rectsContested = new ArrayList<>();
    //ArrayList<Rect> rectwhite = new ArrayList<>();
    int Height;
    int Width;
    int x;
    int y;
    int dx;
    int dy;
    int lasthomeX;
    int lasthomeY;
    Color color;
    String name;
    Boolean Alive = true;
    Rect currentRect;

    Gamer(int height, int width, Color color){
        x =(int)(Math.random() * (width - 2) +1);
        y =(int)(Math.random() * (height - 2) +1);
        if (x < 800) {
            x += 800;
        } else if (x > (width - 800)) {
            x -= 800;
        }
        if (y < 800) {
            y += 800;
        } else if (y > (height) - 800) {
            y -= 800;
        }
        this.color = color;
        this.Height = height;
        this.Width = width;
        double rand = Math.random();
        if (rand < 0.25) {
            dx = 1;
            dy = 0;
        } else if (rand < 5) {
            dx = -1;
            dy = 0;
        } else if (rand < 0.75) {
            dx = 0;
            dy = 1;
        } else {
            dx = 0;
            dy = -1;
        }
    }

    abstract void move(Page page);

    public void killed(){
        Alive = false;
        ArrayList<Rect> ownedRectsCopy = (ArrayList<Rect>)rectsOwned.clone();
        ArrayList<Rect> contestedRectsCopy = (ArrayList<Rect>)rectsContested.clone();
        for(int i = 0; i < ownedRectsCopy.size(); i++){
            ownedRectsCopy.get(i).setOwner(null);
        }

        for(int i = 0; i < contestedRectsCopy.size(); i++){
            contestedRectsCopy.get(i).setContestedOwner(null);
        }
        rectsOwned.clear();
        rectsContested.clear();
        currentRect = null;
    }

    public void setRectOwned(Rect rect){
        rectsOwned.add(rect);
        lasthomeX = rect.getX();
        lasthomeY = rect.getY();
        rect.setOwner(this);
        rect.setContestedOwner(null);
    }

    /*public void makewhite(Rect rect){
        rectwhite.add(rect);
        rect.setOwner(null);
        rect.setContestedOwner(null);
    }*/


    public void removeRectOwned(Rect rect){
        rectsOwned.remove(rect);
    }

    public ArrayList<Rect> getRectsOwned() {
        return rectsOwned;
    }

    public double getPercentOwned(){
        return 100 * getRectsOwned().size() / (double)(Height*Width);
    }

    public void setRectsContested(Rect rect){
        rectsContested.add(rect);
        rect.setContestedOwner(this);
    }

    public ArrayList<Rect> getRectsContested() {
        return rectsContested;
    }

    public void contestToOwned(){
        for (Rect t : rectsContested) {
            setRectOwned(t);
        }
        rectsContested.clear();
    }

    void checkCollisionme(Rect rect, Gamer gamer){
        if(rect.getContestedOwner() != null && rect.getContestedOwner() != this) {
            rect.getContestedOwner().killed();
        }
    }

    public void setCurrentRect(Rect currentRect) {
        this.currentRect = currentRect;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Color getColor() {
        return color;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }

    public String getName() {
        return name;
    }

    public Boolean getAlive() {
        return Alive;
    }

    public void setAlive(Boolean alive) {
        Alive = alive;
    }

    @Override
    public int compareTo(Gamer o) {
        return Integer.compare(o.getRectsOwned().size(), rectsOwned.size());
    }
}
