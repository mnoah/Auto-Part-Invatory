package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Part;
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
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
    public TextField OnPartSearchTextFld;
    public TextField partModName;
    String partSearchTxt;




    private static ObservableList<Part> parts = FXCollections.observableArrayList();
    public ObservableList<Product> products = FXCollections.observableArrayList();





    public InHouse ModPart;
    public static int modIndex;
    public static int partModIndex() {
        return modIndex;
    }

    public Product modProd;
    public static int modProdIndex;
    public static int prodModIndex() {
        return modProdIndex;
    }












    public void DeletePartAction(ActionEvent actionEvent) {
        InHouse SP = (InHouse)PartTable.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Delete Part");
        alert.setHeaderText("No Part Selected");
        alert.setContentText("Please select a part");

        Alert DeleteConfirm  = new Alert(Alert.AlertType.CONFIRMATION);
        DeleteConfirm.setTitle("Delete Part?");
        DeleteConfirm.setContentText("Are you sure you want to delete?");


        if(SP == null) {
            alert.show();
            return;
        }



        Optional<ButtonType> results = DeleteConfirm.showAndWait();
        if(results.get() == ButtonType.OK)
            Inventory.deletePart(SP);
    }





    public void DeleteProductsAction(ActionEvent actionEvent) {

    }



    public void ProductSearchAction(ActionEvent actionEvent) {
    }



    public void AddProductsAction(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/view/AddProduct.fxml"));
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 950, 615);
        stage.setScene(scene);
        stage.show();
    }




    public void AddPartButtonAction(ActionEvent actionEvent) throws IOException {


        Parent root = FXMLLoader.load(getClass().getResource("/view/AddPart.fxml"));
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 475, 500);
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

    private static boolean firstRun= true;

    public void testData(){

        if(!firstRun){
            return;
        }

        firstRun = false;
        int ID;
        ID = AddPart.IdGen++;
        int ID2 = AddProduct.IdGen++;

        Part TestPart = new InHouse(ID, "Wheel", 3.0, 20, 1,20);

        Inventory.AddPart(TestPart);
        ID = AddPart.IdGen++;
        InHouse TestPart2 = new InHouse(ID, "Screw", 1.0, 20, 1,20);
        Inventory.AddPart(TestPart2);
        ID = AddPart.IdGen++;
        InHouse TestPart3 = new InHouse(ID, "Tire", 1.0, 20, 1,20);
        Inventory.AddPart(TestPart3);

        Product TestProd = new Product(ID2, "Car", 223.2,33,1,60);
        Inventory.AddProduct(TestProd);


    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

      /*  InHouse P = new InHouse(1, "Wheel", 11, 22, 1,1);
        Inventory.AddPart(P);*/
        PartTable.setItems(Inventory.getPart());





       //Test Data
        ProdTable.setItems(products);
        //Part Table
        PartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        PartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        PricePartCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        InvPartCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        //Product Table
        PartIDProdCol.setCellValueFactory(new PropertyValueFactory<>("idProd"));
        PartNameProdCol.setCellValueFactory(new PropertyValueFactory<>("nameProd"));
        PriceProdCol.setCellValueFactory(new PropertyValueFactory<>("priceProd"));
        InvProdCol.setCellValueFactory(new PropertyValueFactory<>("stockProd"));




        ObservableList<Part> partsList = Inventory.getPart();
        PartTable.setItems(partsList);

        ObservableList<Product> prodList = Inventory.getProduct();
        ProdTable.setItems(prodList);


        PS = ModPart;

        prodSelec = modProd;

        updatePart();
        updateProd();

        testData();


    }

    public void ModifyProductsAction(ActionEvent actionEvent) throws IOException {
        prodSelec = (Product) ProdTable.getSelectionModel().getSelectedItem();
        modProdIndex = Inventory.getProduct().indexOf(prodSelec);

        Parent root = FXMLLoader.load(getClass().getResource("/view/ModifyProduct.fxml"));
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 950, 615);
        stage.setScene(scene);
        stage.show();

    }



    public void ModifyPartButtonAction(ActionEvent actionEvent) throws IOException {


    PS = (InHouse) PartTable.getSelectionModel().getSelectedItem();
    modIndex = Inventory.getPart().indexOf(PS);

    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Modify Part");
    alert.setHeaderText("No Part Selected");
    alert.setContentText("Please select a part");

    if (PS == null) {
        alert.show();
        return;
    }

    Parent root = FXMLLoader.load(getClass().getResource("/view/ModifyPart.fxml"));
    Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
    Scene scene = new Scene(root, 450, 500);
    stage.setScene(scene);
    stage.show();




    }

    public void updatePart(){
        PartTable.setItems(Inventory.getPart());

    }

    public void updateProd(){
        ProdTable.setItems(Inventory.getProduct());
    }



    private static Part PS;
    public static Part GetSP(){
        return PS;
    }

    private static Product prodSelec;
    public static Product GetProdSelc(){
        return prodSelec;
    }

    public void PartSearchAction(ActionEvent actionEvent) {
        partSearchTxt = OnPartSearchTextFld.getText();
        ObservableList<Part> partsList = SearchParts(partSearchTxt);
        try {
            if (partsList.size() == 0) {
                int ID = Integer.parseInt(partSearchTxt);
                Part PID = searchId(ID);
                if (PID != null) {
                    partsList.add(PID);
                }
            }
        }
        catch (NumberFormatException e){
            //ignore
        }


       if(partsList.size() != 0){
           PartTable.setItems(partsList);
       }else { Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Part Not Found");
           alert.setHeaderText("The Part you searched was not found. Please try again");
           alert.showAndWait();}

    }

        private ObservableList<Part> SearchParts(String PartialName){
            ObservableList<Part> PartName = FXCollections.observableArrayList();
            ObservableList<Part> AllParts = Inventory.getPart();

            for(Part PS: AllParts){
                if(PS.getName().contains(PartialName)){
                    PartName.add(PS);
                }

            }


            return PartName;
    }

    private Part searchId(int IdNum){
        ObservableList<Part> AllParts = Inventory.getPart();

        for(int i = 0; i < AllParts.size(); i++){
            Part PID = AllParts.get(i);
            if(PID.getId() == IdNum){
                return PID;

            }

        }

        return null;
    }


    public void OnExitMain(ActionEvent actionEvent) {
        System.exit(0);
    }
}
