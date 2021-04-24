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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.AddPart;
import model.Part;
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    public TableColumn PartIDProdCol;
    public TableColumn PartNameProdCol;
    public TableColumn InvProdCol;
    public TableColumn PriceProdCol;
    public TableColumn PartIDCol;
    public TableColumn PartNameCol;
    public TableColumn InvPartCol;
    public TableColumn PricePartCol;
    public TableView PartTable;
    public TableView ProdTable;

    public ObservableList<Part> parts = FXCollections.observableArrayList();
    public ObservableList<Product> products = FXCollections.observableArrayList();




    public void PartSearchAction(ActionEvent actionEvent) {
    }

    public void ModifyPartButtonAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/AddPart.fxml"));
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root,  450, 500);
        stage.setScene(scene);
        stage.show();
    }

    public void DeletePartAction(ActionEvent actionEvent) {
        Part SP = (Part)PartTable.getSelectionModel().getSelectedItem();

        if(SP == null)
            return;

        parts.remove(SP);
    }



    public void ModifyProductsAction(ActionEvent actionEvent) {
    }

    public void DeleteProductsAction(ActionEvent actionEvent) {
    }

    public void ProductSearchAction(ActionEvent actionEvent) {
    }

    public void AddProductsAction(ActionEvent actionEvent) {
    }

    public void ModifyPartCancel(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Main_screen.fxml"));
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1050, 500);
        stage.setScene(scene);
        stage.show();

    }


    public void AddPartButtonAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/AddPart.fxml"));
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 450, 500);
        stage.setScene(scene);
        stage.show();

    }


    public void RadioAddPartInHouse(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/AddPart.fxml"));
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 450, 500);
        stage.setScene(scene);
        stage.show();
    }

    public void RadioAddpartOutsourced(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/AddPartInHouse.fxml"));
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 450, 500);
        stage.setScene(scene);
        stage.show();

    }

    public void RaidoModifyOutsourced(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/ModifyPartOutsourced.fxml"));
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 450, 500);
        stage.setScene(scene);
        stage.show();
    }

    public void ModifyPartINHouse(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/ModifyPartInHouse.fxml"));
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 450, 500);
        stage.setScene(scene);
        stage.show();
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {


        PartTable.setItems(parts);
        ProdTable.setItems(products);

        PartIDCol.setCellValueFactory(new PropertyValueFactory<>("idPart"));
        PartNameCol.setCellValueFactory(new PropertyValueFactory<>("namePart"));
        PricePartCol.setCellValueFactory(new PropertyValueFactory<>("pricePart"));
        InvPartCol.setCellValueFactory(new PropertyValueFactory<>("stockPart"));

        PartIDProdCol.setCellValueFactory(new PropertyValueFactory<>("idProd"));
        PartNameProdCol.setCellValueFactory(new PropertyValueFactory<>("nameProd"));
        PriceProdCol.setCellValueFactory(new PropertyValueFactory<>("priceProd"));
        InvProdCol.setCellValueFactory(new PropertyValueFactory<>("stockProd"));

        parts.add(new Part(1, "Wheel", 11, 22, 1,1));
        parts.add(new Part(2, "Hub", 24, 4, 1,1));

        products.add(new Product(1, "Car", 24000, 5, 1,1));




    }
}
