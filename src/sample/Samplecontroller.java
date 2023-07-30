package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Samplecontroller {
    @FXML
    TextField Ndificult;
    @FXML
    TextField Nbot;
    @FXML
    TextField Nspeed;
    @FXML
    TextField G1name;
    @FXML
    TextField G2name;


    public void newwindow(ActionEvent er){
        File myfile = new File("src/bot.txt");
        File myfile1 = new File("src/speed.txt");
        File myfile2 = new File("src/name1.txt");
        File myfile3 = new File("src/name2.txt");
        File myfile4 = new File("src/dificult.txt");
        if (!myfile.exists()) {
            try {
                myfile.createNewFile();
            } catch (IOException y) {
                y.printStackTrace();
            }
        }
        if (!myfile1.exists()) {
            try {
                myfile.createNewFile();
            } catch (IOException u) {
                u.printStackTrace();
            }
        }
        if (!myfile2.exists()) {
            try {
                myfile2.createNewFile();
            } catch (IOException o) {
                o.printStackTrace();
            }
        }
        if (!myfile3.exists()) {
            try {
                myfile3.createNewFile();
            } catch (IOException p) {
                p.printStackTrace();
            }
        }
        if (!myfile4.exists()) {
            try {
                myfile3.createNewFile();
            } catch (IOException b) {
                b.printStackTrace();
            }
        }
        try (FileWriter fw = new FileWriter("src/bot.txt", false)) {
            PrintWriter pw = new PrintWriter(fw);
            pw.println(Nbot.getText());
            pw.close();
        } catch (IOException k) {
            k.printStackTrace();
        }
        try (FileWriter fw = new FileWriter("src/speed.txt", false)) {
            PrintWriter pw = new PrintWriter(fw);
            pw.println(Nspeed.getText());
            pw.close();
        } catch (IOException m) {
            m.printStackTrace();
        }
        try (FileWriter fw = new FileWriter("src/name1.txt", false)) {
            PrintWriter pw = new PrintWriter(fw);
            pw.println(G1name.getText());
            pw.close();
        } catch (IOException n) {
            n.printStackTrace();
        }
        try (FileWriter fw = new FileWriter("src/name2.txt", false)) {
            PrintWriter pw = new PrintWriter(fw);
            pw.println(G2name.getText());
            pw.close();
        } catch (IOException v) {
            v.printStackTrace();
        }
        try (FileWriter fw = new FileWriter("src/dificult.txt", false)) {
            PrintWriter pw = new PrintWriter(fw);
            pw.println(Ndificult.getText());
            pw.close();
        } catch (IOException v) {
            v.printStackTrace();
        }


        System.setProperty("sun.java2d.opengl", "True");
        EventQueue.invokeLater(Game::new);
    }

}
