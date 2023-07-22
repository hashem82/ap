package sample;

public class Weapen {
    int x;
    int y;
    Gamer gamer;
    Page page;
    boolean startwepeon = true;
    public Weapen(int x, int y, Gamer gamer, Page page) {
        this.x = x;
        this.y = y;
        this.gamer = gamer;
        this.page = page;
    }
    public void setOwned(){
        for(int i = x-1; i <= x+1; i++){
            for(int j = y+5; j <= y+10; j++){
                gamer.setRectOwned(page.getRect(i,j));
            }
        }
        startwepeon = false;
    }
}
