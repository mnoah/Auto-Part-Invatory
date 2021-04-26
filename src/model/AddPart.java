package model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.AddPart;
import model.Part;
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import controller.Controller;

public class AddPart  implements Initializable {

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


    //Part Object
    TableColumn PartIDProdCol;
     TableColumn PartNameProdCol;
     TableColumn InvProdCol;
     TableColumn PriceProdCol;
     TableColumn PartIDCol;
     TableColumn PartNameCol;
     TableColumn InvPartCol;
     TableColumn PricePartCol;
     TableView PartTable;
     TableView ProdTable;

     int ID;
     String Name;
     double Price;
     int Inv;
     int min;
     int max;
     String Mechid;




    ObservableList<Part> parts = FXCollections.observableArrayList();
     ObservableList<Product> products = FXCollections.observableArrayList();



    @Override
    public void initialize(URL location, ResourceBundle resources) {
/*
        PartTable.setItems(parts);
        ProdTable.setItems(products);
*/




    }

    public void AddPartSavebtn(ActionEvent actionEvent) throws IOException {

        ID = Integer.parseInt(AddPartIDTxt.getText());
        Name = addPartName.getText();
        Price = Double.parseDouble(AddPartPrice.getText());
        Inv = Integer.parseInt(addPartInv.getText());
        min = Integer.parseInt(addPartMin.getText());
        max = Integer.parseInt(addPartMax.getText());

        if (ID = Null){


        }

        parts.add(new Part(ID, Name, Price, Inv, min,max));

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
