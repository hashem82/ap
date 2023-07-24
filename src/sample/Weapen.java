package sample;

public class Weapen {
    public Weapen() {

    }
    public void setOwned(Page page, Me me){
        if (page.counterweapon == 0) {
            //DOWN
            if (me.getDx() == 0 && me.getDy() == 1) {
                for (int i = me.getX() - 1; i <= me.getX() + 1; i++) {
                    for (int j = me.getY() + 5; j <= me.getY() + 7; j++) {
                        me.setRectOwned(page.getRect(i, j));
                    }
                }
            }
        }
        if (page.counterweapon == 0) {
            //UP
            if (me.getDx() == 0 && me.getDy() == -1) {
                for (int i = me.getX() - 1; i <= me.getX() + 1; i++) {
                    for (int j = me.getY() - 7; j <= me.getY() - 5; j++) {
                        me.setRectOwned(page.getRect(i, j));
                    }
                }
            }
        }
        if (page.counterweapon == 0) {
            //RIGHT
            if (me.getDx() == 1 && me.getDy() == 0) {
                for (int i = me.getX() + 5; i <= me.getX() + 7; i++) {
                    for (int j = me.getY() - 1; j <= me.getY() + 1; j++) {
                        me.setRectOwned(page.getRect(i, j));
                    }
                }
            }
        }
        if (page.counterweapon == 0){
            //LEFT
            if (me.getDx() == -1 && me.getDy() == 0){
                for (int i = me.getX()  - 7; i <= me.getX() - 5; i++) {
                    for (int j = me.getY() - 1; j <= me.getY() + 1; j++) {
                        me.setRectOwned(page.getRect(i, j));
                    }
                }
            }
        }
    }
}
