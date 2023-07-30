package sample;

public class Weapen {
    public Weapen() {

    }
    public void setOwned(Page page, Gamer gamer){
        if (page.counterweapon == 0) {
            //DOWN
            if (gamer.getDx() == 0 && gamer.getDy() == 1) {
                for (int i = gamer.getX() - 1; i <= gamer.getX() + 1; i++) {
                    for (int j = gamer.getY() + 5; j <= gamer.getY() + 7; j++) {
                        gamer.setRectOwned(page.getRect(i, j));
                        for (int k = 0; k < page.gamers.size(); k++){
                            if (page.gamers.get(k).getX() == i && page.gamers.get(k).getY() == j) {
                                page.gamers.get(k).killed();
                                System.out.println(page.gamers.get(k).getName() + " by weapen1");
                            }
                        }
                    }
                }
            }
        }
        if (page.counterweapon == 0) {
            //UP
            if (gamer.getDx() == 0 && gamer.getDy() == -1) {
                for (int i = gamer.getX() - 1; i <= gamer.getX() + 1; i++) {
                    for (int j = gamer.getY() - 7; j <= gamer.getY() - 5; j++) {
                        gamer.setRectOwned(page.getRect(i, j));
                        for (int k = 0; k < page.gamers.size(); k++){
                            if (page.gamers.get(k).getX() == i && page.gamers.get(k).getY() == j) {
                                page.gamers.get(k).killed();
                                System.out.println(page.gamers.get(k).getName() + " by weapen1");
                            }
                        }
                    }
                }
            }
        }
        if (page.counterweapon == 0) {
            //RIGHT
            if (gamer.getDx() == 1 && gamer.getDy() == 0) {
                for (int i = gamer.getX() + 5; i <= gamer.getX() + 7; i++) {
                    for (int j = gamer.getY() - 1; j <= gamer.getY() + 1; j++) {
                        gamer.setRectOwned(page.getRect(i, j));
                        for (int k = 0; k < page.gamers.size(); k++){
                            if (page.gamers.get(k).getX() == i && page.gamers.get(k).getY() == j) {
                                page.gamers.get(k).killed();
                                System.out.println(page.gamers.get(k).getName() + " by weapen1");
                            }
                        }
                    }
                }
            }
        }
        if (page.counterweapon == 0){
            //LEFT
            if (gamer.getDx() == -1 && gamer.getDy() == 0){
                for (int i = gamer.getX()  - 7; i <= gamer.getX() - 5; i++) {
                    for (int j = gamer.getY() - 1; j <= gamer.getY() + 1; j++) {
                        gamer.setRectOwned(page.getRect(i, j));
                        for (int k = 0; k < page.gamers.size(); k++){
                            if (page.gamers.get(k).getX() == i && page.gamers.get(k).getY() == j) {
                                page.gamers.get(k).killed();
                                System.out.println(page.gamers.get(k).getName() + " by weapen1");
                            }
                        }
                    }
                }
            }
        }
    }
}
