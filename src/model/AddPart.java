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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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






        parts.add(new Part(1, "Wheel", 11, 22, 1,1));





    }

    public void AddAPartCancel(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Main_screen.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1050, 500);
        stage.setScene(scene);
        stage.show();
    }
}
