package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    TableView table;
    @FXML
    TableColumn c1;
    @FXML
    TableColumn c2;
    @FXML
    TableColumn c3;
    @FXML
    TextField name;
    @FXML
    TextField age;
    @FXML
    TextField id;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        c1.setCellValueFactory(new PropertyValueFactory<model, String>("c1"));
        c2.setCellValueFactory(new PropertyValueFactory<model, String>("c2"));
        c3.setCellValueFactory(new PropertyValueFactory<model, String>("c3"));
        try{
            BufferedReader bf = new BufferedReader(new FileReader("src/data.csv"));
            String line;
            while ((line = bf.readLine()) != null){
                String[] values = line.split(",");
                table.getItems().add(new model(values[0], values[1], values[2]));

            }
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public void sub(ActionEvent event) {
        model model = new model(name.getText(), age.getText(), id.getText());
        ObservableList<model> models = table.getItems();
        models.add(model);
        table.setItems(models);
    }
    public void save(ActionEvent event)throws IOException{
        File myfile = new File("src/data.csv");
        if(!myfile.exists()) {
            try {
                myfile.createNewFile();
            }catch(IOException e) {
                e.printStackTrace();
            }}
        try (FileWriter fw = new FileWriter("src/data.csv",false)){
            PrintWriter pw = new PrintWriter(fw);
            for (int i = 0 ; i < table.getItems().size() ; i++) {
                model m = (model) table.getItems().get(i);
                pw.print(m.getC1()+ ",");
                pw.print(m.getC2() + ",");
                pw.println(m.getC3());


            }
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0 ; i<table.getItems().size() ; i++) {
            model m = (model) table.getItems().get(i);
            System.out.println(m.getC1() + "  " + m.getC2() + " " + m.getC3());
            //System.out.println(table.getItems().size());
        }
       /* for (int i = 0 ; i < table.getItems().size() ; i++) {
            model m = (model) table.getItems().get(i);
            pw.print(m.getC1()+ ",");
            pw.print(m.getC2() + ",");
            pw.println(m.getC3());


        }*/
    }
    public void change(ActionEvent event) {
        ObservableList<model> currentmodel = table.getItems();
        int currentid = Integer.parseInt(id.getText());
        for (model Model : currentmodel) {
            if (Integer.parseInt(Model.getC3()) == currentid) {
                Model.setC1(name.getText());
                Model.setC2(age.getText());

                table.setItems(currentmodel);
                table.refresh();
                break;
            }
        }
    }

    public void dele(ActionEvent event) {
        int selectid = table.getSelectionModel().getFocusedIndex();
        table.getItems().remove(selectid);
       //TableRow row = table.getRowFactory().get(0);
        //model m = table.getItems().get(0);
        //ObservableList<model> models = table.getColumns().get(0);
       // model m=new model(models.get(0).toString(), models.get(1).toString(), models.get(2).toString());
        //String x =table.getItems().get(0).toString();
        //TableColumn c= table.getVisibleLeafColumn(0);
        //System.out.println(c.toString());
        //for (int i=0 ; i<=t)



    }

    public void rowclicked(MouseEvent event) {
        model clickedmodel = (model) table.getSelectionModel().getSelectedItem();
        name.setText(String.valueOf(clickedmodel.getC1()));
        age.setText(String.valueOf(clickedmodel.getC2()));
        id.setText(String.valueOf(clickedmodel.getC3()));
    }
}