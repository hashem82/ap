package sample;

import javax.swing.*;

public class Gameframe extends JFrame {
    Gameframe(){
        this.add(new Gameplane());
        this.setTitle("PAINT_IO");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
