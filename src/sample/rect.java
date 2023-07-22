package sample;

import java.awt.*;

public class Rect {
    int x;
    int y;
    Gamer owner;
    Color color = Color.white;
    Gamer contestedOwner;

    public Rect(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Color lightColors() {
        float blendedRed = ((owner.getColor().getRed() / 255f) * (contestedOwner.getColor().getRed() / 255f));
        float blendedGreen = ((owner.getColor().getGreen() / 255f) * (contestedOwner.getColor().getGreen() / 255f));
        float blendedBlue = ((owner.getColor().getBlue() / 255f) * (contestedOwner.getColor().getBlue() / 255f));

        return(new Color(((blendedRed + 1 ) / 2),((blendedGreen + 1) / 2),((blendedBlue +1 )/ 2)));
    }

    public Color getColor(){
        if(owner != null && contestedOwner == null) {
            return owner.getColor().darker();
        }

        else if (owner == null && contestedOwner != null) {
            return(new Color(contestedOwner.getColor().getRed(), contestedOwner.getColor().getGreen(),
                    contestedOwner.getColor().getBlue(), 100));
        }

        else if (owner != null && contestedOwner != owner){
            return lightColors();
        }else{
            return color;
        }
    }

    Gamer getContestedOwner() {
        return contestedOwner;
    }

    public void setContestedOwner(Gamer contestedOwner) {
        this.contestedOwner = contestedOwner;
    }

    Gamer getOwner() {
        return owner;
    }

    void setOwner(Gamer owner) {
        if(this.owner != null){
            this.owner.removeRectOwned(this);
        }
        this.owner = owner;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
