package sample;

import java.awt.*;
import java.util.ArrayList;

public abstract class Gamer implements Comparable<Gamer>{
    int x;
    int y;
    int dx;
    int dy;
    Color color;
    ArrayList<Rect> rectsOwned = new ArrayList<>();
    ArrayList<Rect> rectsContested = new ArrayList<>();
    int Height;
    int Width;
    String name;
    Boolean isAlive = true;
    Rect currentRect;

    Gamer(int height, int width, Color color){
        x =(int)(Math.random() * (width - 2) +1);
        y =(int)(Math.random() * (height - 2) +1);

        if(x < 5){
            x += 5;
        }else if(x > (width -5)){
            x-= 5;
        }
        if(y < 5){
            y+= 5;
        }else if(y > (height) - 5){
            y -= 5;
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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Color getColor() {
        return color;
    }

    abstract void move();

    public void killed(){
        isAlive = false;
        ArrayList<Rect> ownedTilesCopy = (ArrayList<Rect>)rectsOwned.clone();
        ArrayList<Rect> contestedTilesCopy = (ArrayList<Rect>)rectsContested.clone();
        for(int i = 0; i < ownedTilesCopy.size(); i++){
            ownedTilesCopy.get(i).setOwner(null);
        }

        for(int i = 0; i < contestedTilesCopy.size(); i++){
            contestedTilesCopy.get(i).setContestedOwner(null);
        }
        rectsOwned.clear();
        rectsContested.clear();
        currentRect = null;
    }

    public void setTileOwned(Rect rect){
        rectsOwned.add(rect);
        rect.setOwner(this);
        rect.setContestedOwner(null);
    }

    public void removeTileOwned(Rect rect){
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
            setTileOwned(t);
        }
        rectsContested.clear();
    }

    void checkCollision(Rect rect){
        if(rect.getContestedOwner() != null) {
            rect.getContestedOwner().killed();
        }
    }

    public void setCurrentRect(Rect currentRect) {
        this.currentRect = currentRect;
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
        return isAlive;
    }

    public void setAlive(Boolean alive) {
        isAlive = alive;
    }

    @Override
    public int compareTo(Gamer o) {
        return Integer.compare(o.getRectsOwned().size(), rectsOwned.size());
    }
}
