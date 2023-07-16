package sample;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class Gameplane extends JPanel implements ActionListener {
    static final int SCREEN_WIDTH = 1400;
    static final int SCREEN_HEIGHT = 700;
    static final int UNIT_SIZE = 25;
    static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;
    static final int DELAY = 200;
    final int x[] = new int[GAME_UNITS];
    final int y[] = new int[GAME_UNITS];
    int xy[][] = new int[1000][1000];
    ArrayList homesx = new ArrayList(1000);
    ArrayList homesy = new ArrayList(1000);
    ArrayList ishomesx = new ArrayList(1000);
    ArrayList ishomesy = new ArrayList(1000);
    int bodyparts = 6;
    int adbody=0;
    int masahat;
    char direction = 'R';
    boolean running = false;
    boolean home = false;
    boolean homes = false;
    int is_home;
    Timer timer;
    Random random;
    int minx = 0;
    int maxx = 0;
    int miny = 0;
    int maxy = 0;
    Gameplane(){
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        this.setBackground(Color.white);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        Startgame();

    }
    public void Startgame(){
        running = true;
        timer = new Timer(DELAY,this);
        timer.start();
    }
    public void paintComponent(Graphics s){
        super.paintComponent(s);
        Draw(s);
    }
    public void Draw(Graphics s){
        if (running) {
            ishomesx.clear();
            ishomesy.clear();
            if (home){
                Makehome(s);
                home = false;
                homes = true;
            }
            if (homes){
                for (int j=0; j<homesx.size(); j++){
                    s.setColor(new Color(45,180,0));
                    s.fillRect((Integer) homesx.get(j), (Integer) homesy.get(j), UNIT_SIZE, UNIT_SIZE);
                    ishomesx.add(homesx.get(j));
                    ishomesy.add(homesy.get(j));
                }
            }
            for (int i = 0; i < SCREEN_WIDTH / UNIT_SIZE; i++) {
                s.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
                s.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
            }
            s.setColor(new Color(45,180,0));
            s.fillRect(0*UNIT_SIZE, 1*UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
            s.fillRect(1*UNIT_SIZE, 1*UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
            s.fillRect(0*UNIT_SIZE, 2*UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
            s.fillRect(1*UNIT_SIZE, 2*UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
            ishomesx.add(0*UNIT_SIZE);
            ishomesy.add(1*UNIT_SIZE);
            ishomesx.add(1*UNIT_SIZE);
            ishomesy.add(1*UNIT_SIZE);
            ishomesx.add(0*UNIT_SIZE);
            ishomesy.add(2*UNIT_SIZE);
            ishomesx.add(1*UNIT_SIZE);
            ishomesy.add(2*UNIT_SIZE);

            for (int i = 0; i < bodyparts; i++) {
                if (i == 0) {
                    s.setColor(Color.green);
                    s.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                } else {
                    s.setColor(new Color(45, 205, 0));
                    s.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                }
            }
            //s.setColor(Color.red);
            //s.setFont(new Font("Ink Free",Font.BOLD,40));
            //FontMetrics metrics = getFontMetrics(s.getFont());
            //s.drawString("Score: "+appleeten, (SCREEN_WIDTH - metrics.stringWidth("Score: "+appleeten))/2, s.getFont().getSize());
        }
        else {
            Gameover(s);
        }

    }
    public void Move(){
        for (int i=bodyparts; i>0; i--){
            x[i] = x[i-1];
            y[i] = y[i-1];
        }
        switch (direction){
            case 'U':
                y[0] = y[0] - UNIT_SIZE;
                break;
            case 'D':
                y[0] = y[0] + UNIT_SIZE;
                break;
            case 'R':
                x[0] = x[0] + UNIT_SIZE;
                break;
            case 'L':
                x[0] = x[0] - UNIT_SIZE;
                break;
        }
    }
    public void addbody(){
        bodyparts++;
        adbody++;

    }

    public void Checkcollisions(){
        for (int a=0; a<ishomesx.size(); a++){
            if ((x[0] == (Integer) ishomesx.get(a)) && (y[0] == (Integer) ishomesy.get(a))){
                masahat = a;
                //running = false;
                home = true;
            }
        }
        if (x[0]<0 || x[0]>SCREEN_WIDTH)
            running = false;
        if (y[0]<0 || y[0]>SCREEN_HEIGHT)
            running = false;
        if (!running){
            timer.stop();
        }
    }
    public void Makehome(Graphics s){
        for (int i=0;i<=adbody;i++) {
            int a[] = new int[1000];
            if (x[i] < minx)
                minx = x[i];
            if(x[i] > maxx)
                maxx = x[i];
            if (y[i] < miny)
                miny = x[i];
            if(y[i] > maxy)
                maxy = x[i];

            s.setColor(new Color(45,180,0));
            s.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
            homesx.add(x[i]);
            homesy.add(y[i]);
        }
        System.out.println(maxx);
        System.out.println(minx);
        System.out.println(maxy);
        System.out.println(miny);
        for (int a = 0; a<=adbody; a++){
            if (x[a] <= maxx && y[a] <= maxy){
                s.setColor(new Color(45, 180, 0));
                s.fillRect(minx, maxy, UNIT_SIZE, UNIT_SIZE);
                homesx.add(minx);
                homesy.add(maxy);
            }
        }
        for (int a = 0; a<1000; a++) {
            if (y[a]<maxy && y[a] > miny ){
                for (int b=0; b<=maxx/UNIT_SIZE; b++) {
                    if (x[b] <= maxx && x[b] >= minx ) {
                        s.setColor(new Color(45, 180,0));
                        s.fillRect(b*UNIT_SIZE, y[a], UNIT_SIZE, UNIT_SIZE);
                        homesx.add(b*UNIT_SIZE);
                        homesy.add(y[a]);
                    }
                }
            }
        }
    }
    public void Gameover(Graphics s){
        //for (int k=bodyparts;k>0;k--) {
            //for (int a = 0; a < k; a++) {
                //if (x[a] * UNIT_SIZE < x[k] * UNIT_SIZE) {
                    //s.setColor(new Color(45,180,0));
                    //s.fillRect(bodyparts*UNIT_SIZE, UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
                    //running = false;
                //}
            //}
        //}
        s.setColor(Color.red);
        s.setFont(new Font("Ink Free",Font.BOLD,40));
        FontMetrics metrics1 = getFontMetrics(s.getFont());
        s.drawString("Score: "+adbody, (SCREEN_WIDTH - metrics1.stringWidth("Score: "+adbody))/2, s.getFont().getSize());

        s.setColor(Color.red);
        s.setFont(new Font("Ink Free",Font.BOLD,40));
        FontMetrics metrics3 = getFontMetrics(s.getFont());
        s.drawString("s : "+masahat, (SCREEN_WIDTH - metrics3.stringWidth("s : "+masahat))/2, SCREEN_HEIGHT/4);

        s.setColor(Color.red);
        s.setFont(new Font("Ink Free",Font.BOLD,75));
        FontMetrics metrics2 = getFontMetrics(s.getFont());
        s.drawString("Game Over", (SCREEN_WIDTH - metrics2.stringWidth("Game Over"))/2, SCREEN_HEIGHT/2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running){
            Move();
            addbody();
            Checkcollisions();
        }
        repaint();
    }
    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e){
            switch (e.getKeyCode()){
                case KeyEvent.VK_LEFT:
                    if (direction != 'R'){
                        direction = 'L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (direction != 'L'){
                        direction = 'R';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (direction != 'D'){
                        direction = 'U';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (direction != 'U'){
                        direction = 'D';
                    }
                    break;
            }
        }
    }
}
