package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import controller.Controller;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Part;
import model.Product;

public class AddProduct implements Initializable{


    public  TableColumn PartIDCol;
    public  TableColumn PartNameCol;
    public  TableColumn InvPartCol;
    public  TableColumn PricePartCol;
    public  TableView PartTable;

    public  TableColumn AstProdPartId;
    public  TableColumn AstProdPartName;
    public  TableColumn AstProdPartInv;
    public  TableColumn AstProdPartPrice;
    public  TableView PartTable2;


    public TextField AddProdId;
    public TextField AddProdName;
    public TextField AddProdInv;
    public TextField AddProdPrice;
    public TextField AddProdMax;
    public TextField AddProdMin;


    public  String Name;
    public  double Price;
    public  int Inv;
    public  int min;
    public  int max;
    int autoIdGn;
    public static int IdGen = 1;

    public ObservableList<Part> Parts2 = FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        PartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        PartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        PricePartCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        InvPartCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        ObservableList<Part> partsList = Inventory.getPart();
        PartTable.setItems(partsList);

        AstProdPartId.setCellValueFactory(new PropertyValueFactory<>("id"));
        AstProdPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        AstProdPartInv.setCellValueFactory(new PropertyValueFactory<>("price"));
        AstProdPartPrice.setCellValueFactory(new PropertyValueFactory<>("stock"));



    }
    public void OnAddProdSavebtn(ActionEvent actionEvent) throws IOException {
        try {
            autoIdGn = IdGen++;
            Name = AddProdName.getText();
            Price = Double.parseDouble(AddProdPrice.getText());
            Inv = Integer.parseInt(AddProdInv.getText());
            min = Integer.parseInt(AddProdMin.getText());
            max = Integer.parseInt(AddProdMax.getText());


            Product Add = new Product(autoIdGn, Name, Price, Inv, min, max);
            Add.setProductParts(Parts2);
            Inventory.AddProduct(Add);

            Parent root = FXMLLoader.load(getClass().getResource("/view/Main_screen.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 1050, 500);
            stage.setScene(scene);
            stage.show();
        }
        catch (NumberFormatException e){

        }

    }

    public void OnAddProdbtn(ActionEvent actionEvent) {

        Part selected = (Part) PartTable.getSelectionModel().getSelectedItem();


        Parts2.add(selected);

        PartTable2.setItems(Parts2);





    }

    public void RemoveAssPart(ActionEvent actionEvent) {
        Part selected = (Part) PartTable2.getSelectionModel().getSelectedItem();
        Parts2.remove(selected);

    }




    public void OnAddProdCancelbtn(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Main_screen.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1050, 500);
        stage.setScene(scene);
        stage.show();
    }

    public void ProdPartTableMadify(){


    }

    public void AddProdPart(){


    }
}
