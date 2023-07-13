package sample;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

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
