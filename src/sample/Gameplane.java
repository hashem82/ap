package sample;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.Random;

public class Gameplane extends JPanel implements ActionListener {
    static final int SCREEN_WIDTH = 1400;
    static final int SCREEN_HEIGHT = 700;
    static final int UNIT_SIZE = 25;
    static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;
    static final int DELAY = 100;
    final int x[] = new int[GAME_UNITS];
    final int y[] = new int[GAME_UNITS];
    int xy[][] = new int[1000][1000];
    int bodyparts = 6;
    int adbody=0;
    int masahat;
    char direction = 'R';
    boolean running = false;
    int is_home;
    Timer timer;
    Random random;
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
            for (int i = 0; i < SCREEN_WIDTH / UNIT_SIZE; i++) {
                s.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
                s.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
            }
            s.setColor(new Color(45,180,0));
            s.fillRect(0*UNIT_SIZE, 1*UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
            s.fillRect(1*UNIT_SIZE, 1*UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
            s.fillRect(0*UNIT_SIZE, 2*UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
            s.fillRect(1*UNIT_SIZE, 2*UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
            File myObj = new File("src/is_home.csv");
            if (!myObj.exists()) {
                try {
                    myObj.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try (FileWriter fw = new FileWriter(myObj,false)){
                PrintWriter pw = new PrintWriter(fw);
                pw.print(0 + ",");
                pw.println(1);
                pw.print(0 + ",");
                pw.println(2);
                pw.print(1 + ",");
                pw.println(1);
                pw.print(1 + ",");
                pw.print(2);
                pw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
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
        /*for (int i=bodyparts;i>0;i--){
            if ((x[0] == x[i])&&(y[0] == y[i])){
                masahat = i;
                //running = false;
            }
        }*/
        int X[] = new int[1000];
        int Y[] = new int[1000];
        int i = 0;
        int j = 0;
        try{
            BufferedReader bf = new BufferedReader(new FileReader("src/is_home.csv"));
            String line;
            while ((line = bf.readLine()) != null){
                String[] values = line.split(",");
                rect Rect = (new rect(Integer.parseInt(values[0]), Integer.parseInt(values[1])));
                X[i] = Rect.getX();
                Y[i] = Rect.getY();
                i++;
                j++;
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        for (i=0; i<X.length; i++){
                if ((x[0] == X[i]*UNIT_SIZE) && (y[0] == Y[i]*UNIT_SIZE)) {
                    masahat = i;
                    running = false;
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
    public void Gameover(Graphics s){
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
