package sample;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.*;
public class Page extends JPanel {
    final int GameHeigh = 2200;
    final int GameWidth = 2200;
    final int UNIT_SIZE = 20;
    Rect[][] gameArea;
    int botNumber;
    int timernumber = 0;
    int timerspeed;
    ArrayList<Gamer> gamers = new ArrayList<>();
    ArrayList<Me> mes = new ArrayList<>();
    ArrayList<Draw> draws = new ArrayList<>();
    ArrayList<Gamer> deadBots = new ArrayList<>();
    HashMap<Rect, Gamer> rectGamerMap = new HashMap<>();
    HashMap<Gamer, Draw> gamer_draw = new HashMap<>();
    ActionListener actionListener;
    boolean paused = true;
    boolean Gameover = false;
    ArrayList<Color> colorList = new ArrayList<>(Arrays.asList(Color.magenta, Color.green, Color.red, Color.blue, Color.orange, Color.yellow, Color.pink, Color.black));
    Page(){}
    public Page( ActionListener actionListener, String p1name, int gameSpeed, int botNumber) {
        this.actionListener = actionListener;
        this.botNumber = botNumber;
        int[] speeds = {13, 12, 11, 10, 9, 8, 7, 6, 5, 4};
        timerspeed = speeds[gameSpeed - 1];

        gamers.add(new Me(GameHeigh, GameWidth, new Color((int)(Math.random() * 0x1000000)), p1name));
        mes.add((Me) gamers.get(0));

        inPage();

        draws.add(new Draw(UNIT_SIZE, this, mes.get(0), gamers));
        gamer_draw.put(mes.get(0), draws.get(0));
    }

    public Page(ActionListener actionListener, String p1name, String p2name, int gameSpeed, int botNumber) {
        this.actionListener = actionListener;
        this.botNumber = botNumber;
        int[] speeds = {13, 12, 11, 10, 9, 8, 7, 6, 5, 4};
        timerspeed = speeds[gameSpeed - 1];

        gamers.add(new Me(GameHeigh, GameWidth, new Color((int)(Math.random() * 0x1000000)), p1name));
        gamers.add(new Me(GameHeigh, GameWidth, gamers.get(0).getColor(), p2name));
        mes.add((Me) gamers.get(0));
        mes.add((Me) gamers.get(1));

        inPage();

        draws.add(new Draw(UNIT_SIZE, this, mes.get(0), gamers));
        gamer_draw.put(mes.get(0), draws.get(0));
        draws.add(new Draw(UNIT_SIZE, this, mes.get(1), gamers));
        gamer_draw.put(mes.get(1), draws.get(0));
    }

    private void inPage(){
        this.gameArea = new Rect[GameHeigh][GameWidth];
        for(int i = 0; i < gameArea.length; i++){
            for(int j = 0; j < gameArea[i].length; j++){
                gameArea[i][j] = new Rect(j,i);
            }
        }
        Whatkeydo();

        setBackground(Color.BLACK);
        for(int i = 0; i < botNumber; i++){
            if(i > 7){
                gamers.add(new Bot(gameArea.length,gameArea[0].length,
                        new Color((int)(Math.random() * 0x1000000))));
            }else {
                gamers.add(new Bot(gameArea.length, gameArea[0].length, colorList.get(i)));
            }
        }
        for(int i = 0; i < gamers.size(); i++) {
            if (!checkingfasele(gamers.get(i))) {
                gamers.remove(gamers.get(i));
                i--;
                if (botNumber > 7) {
                    gamers.add(new Bot(gameArea.length, gameArea[0].length,
                            new Color((int) (Math.random() * 0x1000000))));
                } else {
                    gamers.add(new Bot(gameArea.length, gameArea[0].length, colorList.get(i)));
                }
                continue;
            } else {
                startGame(gamers.get(i));
            }
        }
        final int INITIAL_DELAY = 0;
        final int PERIOD_INTERVAL = 1000/60;
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new ScheduleTask(),
                INITIAL_DELAY, PERIOD_INTERVAL);
    }

    private void Whatkeydo() {
        InputMap im = getInputMap(WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = getActionMap();
        if(mes.size() == 1){
            im.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "moveUP");
            am.put("moveUP", new AbstractAction() {
                public void actionPerformed(ActionEvent evt) { mes.get(0).setHappenedKey(KeyEvent.VK_UP);
                }
            });
            im.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "moveDOWN");
            am.put("moveDOWN", new AbstractAction() {
                public void actionPerformed(ActionEvent evt) {mes.get(0).setHappenedKey(KeyEvent.VK_DOWN);
                }
            });
            im.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "moveLEFT");
            am.put("moveLEFT", new AbstractAction() {
                public void actionPerformed(ActionEvent evt) { mes.get(0).setHappenedKey(KeyEvent.VK_LEFT);
                }
            });
            im.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "moveRIGHT");
            am.put("moveRIGHT", new AbstractAction() {
                public void actionPerformed(ActionEvent evt) { mes.get(0).setHappenedKey(KeyEvent.VK_RIGHT);
                }
            });
            im.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "ENTER");
            am.put("ENTER", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mes.get(0).setHappenedKey(KeyEvent.VK_ENTER);
                }
            });

        }else if(mes.size() == 2) {
            im.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "moveP1UP");
            am.put("moveP1UP", new AbstractAction() {
                public void actionPerformed(ActionEvent evt) {
                    mes.get(1).setHappenedKey(KeyEvent.VK_UP);
                }
            });
            im.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "moveP1DOWN");
            am.put("moveP1DOWN", new AbstractAction() {
                public void actionPerformed(ActionEvent evt) {
                    mes.get(1).setHappenedKey(KeyEvent.VK_DOWN);
                }
            });
            im.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "moveP1LEFT");
            am.put("moveP1LEFT", new AbstractAction() {
                public void actionPerformed(ActionEvent evt) {
                    mes.get(1).setHappenedKey(KeyEvent.VK_LEFT);
                }
            });
            im.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "moveP1RIGHT");
            am.put("moveP1RIGHT", new AbstractAction() {
                public void actionPerformed(ActionEvent evt) {
                    mes.get(1).setHappenedKey(KeyEvent.VK_RIGHT);
                }
            });
            im.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0), "moveP2UP");
            am.put("moveP2UP", new AbstractAction() {
                public void actionPerformed(ActionEvent evt) {
                    mes.get(0).setHappenedKey(KeyEvent.VK_W);
                }
            });
            im.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0), "moveP2DOWN");
            am.put("moveP2DOWN", new AbstractAction() {
                public void actionPerformed(ActionEvent evt) {
                    mes.get(0).setHappenedKey(KeyEvent.VK_S);
                }
            });
            im.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0), "moveP2LEFT");
            am.put("moveP2LEFT", new AbstractAction() {
                public void actionPerformed(ActionEvent evt) {
                    mes.get(0).setHappenedKey(KeyEvent.VK_A);
                }
            });
            im.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0), "moveP2RIGHT");
            am.put("moveP2RIGHT", new AbstractAction() {
                public void actionPerformed(ActionEvent evt) {
                    mes.get(0).setHappenedKey(KeyEvent.VK_D);
                }
            });
        }
    }

    private void startGame(Gamer gamer) {
        int x = gamer.getX();
        int y = gamer.getY();
        if(!checkingfasele(gamer)){
            Gamer gamerCopy = new Bot(gameArea.length,gameArea[0].length, gamer.getColor());
            startGame(gamerCopy);
        }
        for(int i = x-1; i <= x+1; i++){
            for(int j = y-1; j <= y+1; j++){
                gamer.setRectOwned(getRect(i,j));
            }
        }
    }
    private boolean checkingfasele(Gamer gamer) {
        int x = gamer.getX();
        int y = gamer.getY();
        for(int i = x-3; i <= x+3; i++) {
            for (int j = y - 3; j <= y + 3; j++) {
                if (getRect(i, j).getOwner() != null || getRect(i, j).getContestedOwner() != null ) {
                    return false;
                }
            }
        }
        return true;
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(int i = 0; i < draws.size(); i++){
            g.setClip(getWidth()/draws.size() * i,0,getWidth()/draws.size(),getHeight());
            g.translate(getWidth()/draws.size() * i,0);
            draws.get(i).draw(g);
            g.translate(-getWidth()/draws.size() * i,0);
        }
        try {
            drawditails(g);
        } catch(IndexOutOfBoundsException ignored){
        }
        Toolkit.getDefaultToolkit().sync();
    }

    private void drawditails(Graphics g) {
        g.setFont(new Font("Ink Free", Font.BOLD, 18));
        FontMetrics fontMetrics = g.getFontMetrics();
        int fontHeight = fontMetrics.getHeight();

        Gamer gamer;
        String string;
        Color color;

        double highestPercentOwned = gamers.get(0).getPercentOwned();
        Collections.sort(gamers);

        int navardWith;
        int navarHithe = fontHeight + 8;
        for(int i = 0; i < Integer.min(5, gamers.size()); i++){
            gamer = gamers.get(i);
            string = String.format(gamer.getName());
            color = gamer.getColor();

            navardWith = (int)((gamer.getPercentOwned() / highestPercentOwned)*(getWidth()/4));
            g.setColor(gamer.getColor());
            g.fillRect(getWidth() - navardWith,  navarHithe*i, navardWith,navarHithe);
            if(0.299*color.getRed() + 0.587*color.getGreen() + 0.114*color.getBlue() < 127){
                g.setColor(Color.WHITE);
            }else{
                g.setColor(Color.BLACK);
            }
            g.drawString(string, 2+getWidth() -  navardWith,  navarHithe*i + fontHeight);
        }
        if (Gameover){
            end(g);
        }
    }

    private void ruleofGame(){
        Gamer gamer;
        rectGamerMap.clear();
        for (int i = 0; i < gamers.size(); i++) {
            gamer = gamers.get(i);
            gamer.move();

            if(gamer.getX() < 0 || gamer.getX() >= GameWidth || gamer.getY() < 0 || gamer.getY() >= GameHeigh){
                gamer.killed();
            }else{
                Rect rect = getRect(gamer.getX(), gamer.getY());
                gamer.checkCollision(rect);
                gamer.setCurrentRect(rect);
                ShakhbeShakh(gamer, rect);

                if (rect.getOwner() != gamer && gamer.getAlive()) {
                    gamer.setRectsContested(rect);
                } else if (gamer.getRectsContested().size() > 0) {
                    gamer.contestToOwned();
                    paintnewOwned(gamer);
                }
            }
            if(gamer instanceof Bot && !gamer.getAlive()){
                deadBots.add(gamer);
            }
        }
        alivebots();

        boolean allKilled = true;
        for(Me me : mes){
            me.updateKey();

            gamer_draw.get(me).setDraw(me.getAlive());
            allKilled = allKilled && !me.getAlive();
        }
        if(allKilled){
            Gameover = true;
        }
        gamers.removeIf(p -> !p.getAlive());
    }

    private void end(Graphics g) {
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free",Font.BOLD,75));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Game Over", (getWidth() - metrics2.stringWidth("Game Over"))/2, getHeight()/2);
    }

    private void alivebots() {
        for(int i = 0; i < deadBots.size(); i++){
            if(deadBots.get(i).getAlive()){
                Gamer gamer = new Bot(gameArea.length,gameArea[0].length,
                        new Color((int)(Math.random() * 0x1000000)));
                startGame(gamer);
                gamers.add(gamer);
                deadBots.remove(deadBots.get(i));
            }
        }
    }

    private void ShakhbeShakh(Gamer gamer, Rect rect) {
        if(rectGamerMap.containsKey(rect)) {
            for(Map.Entry<Rect, Gamer> entry : rectGamerMap.entrySet()) {
                if (entry.getKey() == rect) {
                    if (entry.getValue().getRectsContested().size() > gamer.getRectsContested().size()) {
                        entry.getValue().killed();
                    } else if (entry.getValue().getRectsContested().size() < gamer.getRectsContested().size()) {
                        gamer.killed();
                    } else if (entry.getValue().getRectsContested().size() == gamer.getRectsContested().size()) {
                        if (entry.getValue().getRectsOwned().size() > gamer.getRectsOwned().size()) {
                            entry.getValue().killed();
                        } else {
                            gamer.killed();
                        }
                    }
                }
            }
        }else {
            rectGamerMap.put(rect, gamer);
        }
        gamers.removeIf(p -> !p.getAlive());
    }
    private void paintnewOwned(Gamer gamer) {
        int maxX = 0;
        int minX = gameArea[0].length;
        int maxY = 0;
        int minY = gameArea.length;
        for (Rect rect : gamer.getRectsOwned()) {
            if(rect.getX() > maxX) maxX = rect.getX();
            if(rect.getX() < minX) minX = rect.getX();
            if(rect.getY() > maxY) maxY = rect.getY();
            if(rect.getY() < minY) minY = rect.getY();
        }

        ArrayList<Rect> outside = new ArrayList<>();
        ArrayList<Rect> inside  = new ArrayList<>();
        ArrayList<Rect> shoulddpaint = new ArrayList<>();
        HashSet<Rect> Check = new HashSet<>();

        int y;
        int x;
        for(Rect rect : gamer.getRectsOwned()){
            y = rect.getY();
            x = rect.getX();
            if(y -1 >= 0) Check.add(gameArea[y-1][x]);
            if(y + 1 < gameArea.length) Check.add(gameArea[y+1][x]);
            if(x - 1 >= 0) Check.add(gameArea[y][x-1]);
            if(x + 1 < gameArea[y].length) Check.add(gameArea[y][x+1]);
        }
        for(Rect rect : Check){
            if(!inside.contains(rect)){
                Stack<Rect> stack = new Stack<>();
                boolean cont = true;
                Rect v;
                shoulddpaint.clear();
                stack.push(rect);
                while((!stack.empty()) && cont){
                    v = stack.pop();
                    if(!shoulddpaint.contains(v) && (v.getOwner() != gamer)){
                        y = v.getY();
                        x = v.getX();
                        if(outside.contains(v) //If already declared as outside
                                || x < minX || x > maxX || y < minY || y > maxY //If outside of boundary
                                || x == gameArea[0].length -1 || x == 0 || y == 0 || y == gameArea.length -1){ // If it is a edge tile
                            cont = false;
                        }else{
                            shoulddpaint.add(v);
                            if(y -1 >= 0) stack.push(gameArea[y-1][x]);
                            if(y + 1 < gameArea.length) stack.push(gameArea[y+1][x]);
                            if(x - 1 >= 0) stack.push(gameArea[y][x-1]);
                            if(x + 1 < gameArea[y].length) stack.push(gameArea[y][x+1]);
                        }
                    }
                }
                if(cont){
                    inside.addAll(shoulddpaint);
                }else{
                    outside.addAll(shoulddpaint);
                }
            }
        }
        for(Rect rect : inside){
            gamer.setRectOwned(rect);
        }
    }

    public void setPaused(Boolean paused){
        this.paused = paused;
    }

    public int getGameHeigh() {
        return GameHeigh;
    }

    public int getGameWidth() {
        return GameWidth;
    }

    public int getTimernumber() {
        return timernumber;
    }

    public int getTimerspeed() {
        return timerspeed;
    }

    Rect getRect(int x, int y){
        return gameArea[y][x];
    }
    private void Timerupdate(){
        timernumber++;
        timernumber %= timerspeed;
    }
    private class ScheduleTask extends TimerTask {
        @Override
        public void run() {
            if(!paused) {
                Timerupdate();
                if (timernumber == 0) {
                    ruleofGame();
                }
                repaint();
            }
        }
    }
}
