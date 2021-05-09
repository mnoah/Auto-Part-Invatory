package controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddPart extends Controller implements Initializable {

    //Radio Button
    public RadioButton AddInHouse;
    public RadioButton AddOutsourced;
    public Label addPartMichID;

    //Text fields
    public TextField AddPartIDTxt;
    public TextField addPartName;
    public TextField addPartInv;
    public TextField AddPartPrice;
    public TextField addPartMax;
    public TextField AddPartMachId;
    public TextField addPartMin;




     int ID;
     String Name;
     double Price;
     int Inv;
     int min;
     int max;
     String Mechid;

     ObservableList<InHouse> parts = FXCollections.observableArrayList();
     ObservableList<Product> products = FXCollections.observableArrayList();








    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }

    public void AddPartSavebtn(ActionEvent actionEvent) throws IOException {

        ID = Integer.parseInt(AddPartIDTxt.getText());
        Name = addPartName.getText();
        Price = Double.parseDouble(AddPartPrice.getText());
        Inv = Integer.parseInt(addPartInv.getText());
        min = Integer.parseInt(addPartMin.getText());
        max = Integer.parseInt(addPartMax.getText());

        InHouse P = new InHouse(ID, Name, Price, Inv, min,max);
        Inventory.AddPart(P);

        Parent root = FXMLLoader.load(getClass().getResource("/view/Main_screen.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1050, 500);
        stage.setScene(scene);
        stage.show();






    }

    public void AddAPartCancel(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Main_screen.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1050, 500);
        stage.setScene(scene);
        stage.show();
    }

    public void InHouseOaAddPart(ActionEvent actionEvent) {

        addPartMichID.setText("Machine ID");
    }

    public void outAddPartOA(ActionEvent actionEvent) {
        addPartMichID.setText("Company Name");


    }
}
