package sample;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;

public class Game extends JFrame implements ActionListener {
    Page page;
    Gameframe gameframe;
    JPanel cards;
    String name2;
    String name1;
    int bot;
    int speed;
    Game(){
        firstUI();
    }

    private void firstUI() {
        setSize(1400, 700);
        setResizable(true);
        setVisible(true);
        setTitle("Paint_IO");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        gameframe = new Gameframe(this);
        cards = new JPanel(new CardLayout());
        cards.add(gameframe, "menu");

        add(cards);
    }

    private enum STATE{
        GAME,
        MENU
    }

    private void setState(STATE s){
        CardLayout cardLayout = (CardLayout) cards.getLayout();
        if(s == STATE.GAME){
            cardLayout.show(cards, "board");
            page.setPaused(false);
        }else if(s == STATE.MENU){
            cardLayout.show(cards, "menu");
            page.setPaused(true);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            File myObj = new File("src/name2.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                name2 = data;
            }
            myReader.close();
        } catch (FileNotFoundException er) {
            System.out.println("An error occurred.");
            er.printStackTrace();
        }
        try {
            File myObj = new File("src/name1.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                name1 = data;
            }
            myReader.close();
        } catch (FileNotFoundException er) {
            System.out.println("An error occurred.");
            er.printStackTrace();
        }
        try {
            File myObj = new File("src/bot.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                bot = Integer.parseInt(data);
            }
            myReader.close();
        } catch (FileNotFoundException er) {
            System.out.println("An error occurred.");
            er.printStackTrace();
        }
        try {
            File myObj = new File("src/speed.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                speed = Integer.parseInt(data);
            }
            myReader.close();
        } catch (FileNotFoundException er) {
            System.out.println("An error occurred.");
            er.printStackTrace();
        }

        switch (e.getActionCommand()) {
            case "Play VS PC":
                page = new Page(this, name1, speed, bot);
                cards.add(page, "board");
                setState(STATE.GAME);
                break;
            case "Multiplayer(Offline)":
                page = new Page(this, name1, name2, speed, bot);
                cards.add(page, "board");
                setState(STATE.GAME);
                break;
            case "End Game":
                setState(STATE.MENU);
                break;
        }
    }

}
