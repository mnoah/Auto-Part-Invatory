package controller;

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
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;



public class ModifyProduct implements Initializable {

    public TextField modProdId;
    public TextField modProdName;
    public TextField modProdInv;
    public TextField modProdPrice;
    public TextField modProdMax;
    public TextField modProdMin;

    public TableColumn PartIDCol;
    public  TableColumn PartNameCol;
    public  TableColumn InvPartCol;
    public  TableColumn PricePartCol;
    public TableView PartTable;

    public  TableColumn AstProdPartId;
    public  TableColumn AstProdPartName;
    public  TableColumn AstProdPartInv;
    public  TableColumn AstProdPartPrice;
    public  TableView PartTable2;




    int ID;
    String Name;
    double Price;
    int Inv;
    int min;
    int max;

    public Product product;

    public void OnModProdbtn(ActionEvent actionEvent) {
    }

    public void RemoveModAssPart(ActionEvent actionEvent) {
    }

    public void OnModProdSavebtn(ActionEvent actionEvent) throws IOException {

        Name = modProdName.getText();
        Price = Double.parseDouble(modProdPrice.getText());
        Inv = Integer.parseInt(modProdInv.getText());
        min = Integer.parseInt(modProdMin.getText());
        max = Integer.parseInt(modProdMax.getText());

        Product prodMod = new Product(ID, Name, Price, Inv, min, max);
        prodMod.setIdProd(ID);
        prodMod.setNameProd(Name);
        prodMod.setPriceProd(Price);
        prodMod.setStockProd(Inv);
        prodMod.setMinProd(min);
        prodMod.setMaxProd(max);
        Inventory.ModProduct(modProdIndex, prodMod);
        Inventory.AddProduct(prodMod);


        Parent root = FXMLLoader.load(getClass().getResource("/view/Main_screen.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1050, 500);
        stage.setScene(scene);
        stage.show();

    }

    public void OnModProdCancelbtn(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Main_screen.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1050, 500);
        stage.setScene(scene);
        stage.show();
    }

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

        product = Controller.GetProdSelc();
        modProdId.setText(String.valueOf(product.getIdProd()));
        modProdName.setText(String.valueOf(product.getNameProd()));
        modProdPrice.setText(String.valueOf(product.getPriceProd()));
        modProdInv.setText(String.valueOf(product.getStockProd()));
        modProdMax.setText(String.valueOf(product.getMinProd()));
        modProdMin.setText(String.valueOf(product.getMaxProd()));




    }
}

