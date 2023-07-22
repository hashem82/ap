package sample;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Gameframe extends JPanel {
    /*Gameframe(){
        this.add(new Gameplane());
        this.setTitle("PAINT_IO");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }*/
    /*JTextField gamer1NameFld;
    JTextField gamer2NameFld;
    JSpinner areaHeightSpnr;
    JSpinner areaWidthSpnr;
    JSpinner gameSpeedSpnr;
    JSpinner botNumberSpnr;*/
    ActionListener actionListener;
    Gameframe (ActionListener actionListener){
        this.actionListener = actionListener;
        setBackground(Color.yellow);
        addComponents();
    }

    public Gameframe() {

    }

    public void addComponents() {
        add(new JLabel(" "));
        add(new JLabel(" "));

        JButton playBtn = new JButton("Play VS PC");
        JButton playMultiBtn = new JButton("Multiplayer(Offline)");
        JButton[] buttons = {playBtn, playMultiBtn};

        for(JButton button : buttons){
            button.setSize(100, 26);
            button.addActionListener(actionListener);
            button.setFont(new Font("Ink Free", Font.BOLD, 40));
            button.setBackground(Color.white);
            button.setForeground(Color.black);
            button.setFocusPainted(false);
            button.setBorderPainted(false);

            add(button);
        }
    }

}
