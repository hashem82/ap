package sample;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class Gameframe extends JPanel {
    ActionListener actionListener;
    Gameframe (ActionListener actionListener){
        this.actionListener = actionListener;
        setBackground(Color.yellow);
        addComponents();
    }

    public Gameframe() {

    }

    public void addComponents() {
        ImageIcon imageIcon;
        JLabel label1 = new JLabel();
        imageIcon = new ImageIcon("src/pic/page1.jpeg");
        label1.setIcon(imageIcon);

        add(label1);
        add(new JLabel(" "));
        add(new JLabel(" "));

        JButton playBtn = new JButton("Play VS PC");
        JButton playMultiBtn = new JButton("Multiplayer(Offline)");
        JButton[] buttons = {playBtn, playMultiBtn};

        label1.setVerticalAlignment(JLabel.CENTER);
        label1.setHorizontalAlignment(JLabel.CENTER);
        label1.setSize(400, 400);


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
