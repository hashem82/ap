package sample;


import java.awt.*;
import java.util.List;

public class Draw {
    int width;
    int height;
    final int UNIT_SIZE;
    List<Gamer> gamers;
    Gamer me;
    Page page;
    boolean draw = true;

    public Draw(int UNIT_SIZE, Page page, Gamer me, List<Gamer> gamers) {
        this.UNIT_SIZE = UNIT_SIZE;
        this.gamers = gamers;
        this.me = me;
        this.page = page;
        this.draw = draw;
    }
    void draw(Graphics g){
        int X;
        int Y;
        if(draw){
            height = g.getClipBounds().height;
            width = g.getClipBounds().width;

            for (int y = 0; y < page.getGameHeigh(); y++) {
                for (int x = 0; x < page.getGameWidth(); x++) {
                    X = (x - me.getX()) * UNIT_SIZE + ((width - UNIT_SIZE) / 2) + (int) ((-me.getDx()) * UNIT_SIZE * ((page.getTimernumber() + 1) / (double) page.getTimerspeed()));
                    Y = (y - me.getY()) * UNIT_SIZE + ((height - UNIT_SIZE) / 2) + (int) ((-me.getDy()) * UNIT_SIZE * ((page.getTimernumber() + 1) / (double) page.getTimerspeed()));
                    if (!(X + UNIT_SIZE < 0 || X > width || Y + UNIT_SIZE < 0 || Y > height)) {
                        g.setColor(Color.white);
                        g.fillRect(X, Y, UNIT_SIZE, UNIT_SIZE);
                        g.setColor(Color.darkGray);
                        g.drawLine(X, 0, X, height);
                        g.drawLine(0, Y, width, Y);
                        g.setColor(page.getRect(x,y).getColor());
                        g.fillRect(X, Y, UNIT_SIZE, UNIT_SIZE);
                    }
                }
            }
            g.setFont(new Font("System", Font.PLAIN, 12));
            FontMetrics fontMetrics = g.getFontMetrics();

            for (Gamer gamer1 : gamers) {
                X = (gamer1.getX() - me.getX()) * UNIT_SIZE + ((width - UNIT_SIZE) / 2);
                Y = (gamer1.getY() - me.getY()) * UNIT_SIZE + ((height - UNIT_SIZE) / 2);
                if (gamer1 != me) {
                    X += ((gamer1.getDx() - me.getDx()) * UNIT_SIZE * ((page.getTimernumber() + 1) / (double) page.getTimerspeed()));
                    Y += ((gamer1.getDy() - me.getDy()) * UNIT_SIZE * ((page.getTimernumber() + 1) / (double) page.getTimerspeed()));
                }
                g.setColor(Color.BLACK);
                g.drawString(gamer1.getName(), X + (UNIT_SIZE - fontMetrics.stringWidth(gamer1.getName()))/2, Y + UNIT_SIZE + 16);
                if (!(X + UNIT_SIZE < 0 || X > width || Y + UNIT_SIZE < 0 || Y > height)) {
                    g.setColor(gamer1.getColor());
                    g.fillRect(X, Y, UNIT_SIZE, UNIT_SIZE);
                }
            }
        }
    }
    public void setDraw(boolean draw) {
        this.draw = draw;
    }
}
