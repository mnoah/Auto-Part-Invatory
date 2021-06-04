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

/**The controller for the main screen
 * RUNTIME ERROR: Had trouble passing and pulling data to and from other classes.
 * In almost all cases the issue was not matching the FXML(View) data with the data I was trying to pass to the controller which was giving me Nullvalue errors */
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
    public TextField prodSearchText;
    String partSearchTxt;




    private static ObservableList<Part> parts = FXCollections.observableArrayList();
    public ObservableList<Product> products = FXCollections.observableArrayList();





    public Part ModPart;
    public static int modIndex;

    /**returns the part array index for modifying parts*/
    public static int partModIndex() {
        return modIndex;
    }

    public Product modProd;
    public static int modProdIndex;
    /**returns the product array index for modifying products*/
    public static int prodModIndex() {
        return modProdIndex;
    }

    private static Part PS;

    /**returns the selected part for modifying parts*/
    public static Part GetSP(){
        return PS;
    }

    private static Product prodSelec;
    /**returns the selected product for modifying products*/
    public static Product GetProdSelc(){
        return prodSelec;
    }











    /**Button for deleting part from part table*/
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
    /**Button for deleting product from product table*/
    public void DeleteProductsAction(ActionEvent actionEvent) {
        Product SProd = (Product)ProdTable.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Delete Product");
        alert.setHeaderText("No Product Selected");
        alert.setContentText("Please select a Product");

        if(!SProd.getProductParts().isEmpty()){
            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert2.setTitle("Delete Product");
            alert2.setHeaderText("Associated Part found");
            alert2.setContentText("Associated Part found, please remove all parts from product before deleting");
            alert2.show();

        }else {

            Alert DeleteConfirm = new Alert(Alert.AlertType.CONFIRMATION);
            DeleteConfirm.setTitle("Delete Product?");
            DeleteConfirm.setContentText("Are you sure you want to delete?");



            if (SProd == null) {
                alert.show();
                return;
            }


            Optional<ButtonType> results = DeleteConfirm.showAndWait();
            if (results.get() == ButtonType.OK)
                Inventory.deleteProduct(SProd);
        }


    }













    /**Button to open add Product screen*/
    public void AddProductsAction(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/view/AddProduct.fxml"));
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 950, 615);
        stage.setScene(scene);
        stage.show();
    }



    /**Button to open the Add part screen*/
    public void AddPartButtonAction(ActionEvent actionEvent) throws IOException {


        Parent root = FXMLLoader.load(getClass().getResource("/view/AddPart.fxml"));
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 475, 500);
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
        ID2 = AddPart.IdGen++;


    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        PartTable.setItems(Inventory.getPart());





       //Test Data
        ProdTable.setItems(products);
        //Part Table
        PartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        PartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        PricePartCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        InvPartCol.setCellValueFactory(new PropertyValueFactory<>("stock"));

        ObservableList<Part> partsList = Inventory.getPart();
        PartTable.setItems(partsList);
        PS = ModPart;
        updatePart();


        //Product Table
        PartIDProdCol.setCellValueFactory(new PropertyValueFactory<>("idProd"));
        PartNameProdCol.setCellValueFactory(new PropertyValueFactory<>("nameProd"));
        PriceProdCol.setCellValueFactory(new PropertyValueFactory<>("priceProd"));
        InvProdCol.setCellValueFactory(new PropertyValueFactory<>("stockProd"));

        ObservableList<Product> prodList = Inventory.getProduct();
        ProdTable.setItems(prodList);

        prodSelec = modProd;
        updateProd();
        testData();


    }
    /**Button to open the modify product screen*/
    public void ModifyProductsAction(ActionEvent actionEvent) throws IOException {
        prodSelec = (Product) ProdTable.getSelectionModel().getSelectedItem();
        modProdIndex = Inventory.getProduct().indexOf(prodSelec);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Modify Product");
        alert.setHeaderText("No Product Selected");
        alert.setContentText("Please select a product");

        if (prodSelec == null) {
            alert.show();
            return;
        }

        Parent root = FXMLLoader.load(getClass().getResource("/view/ModifyProduct.fxml"));
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 950, 615);
        stage.setScene(scene);
        stage.show();

    }


    /**Button to open the modify part screen*/
    public void ModifyPartButtonAction(ActionEvent actionEvent) throws IOException {
    PS = (Part) PartTable.getSelectionModel().getSelectedItem();
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
    /**Updates the part table with modified part*/
    public void updatePart(){
        PartTable.setItems(Inventory.getPart());

    }
    /**Updates the product table with modified product*/
    public void updateProd(){

        ProdTable.setItems(Inventory.getProduct());
    }

public String prodSearch;

    /**On Action for product search*/
    public void ProductSearchAction(ActionEvent actionEvent) {
        Alert alertProd = new Alert(Alert.AlertType.INFORMATION);
        alertProd.setTitle("Search Product");
        alertProd.setHeaderText("No Product found");
        alertProd.setContentText("Please try again");

        prodSearch = prodSearchText.getText();
        ObservableList<Product> prodlist = SearchProd(prodSearch);
        try{
            if(prodlist.size() == 0){
                int ID = Integer.parseInt(prodSearch);
                Product PID = searchProdId(ID);
                if (PID != null) {
                    prodlist.add(PID);
                }
            }
        }catch (NumberFormatException e){
            alertProd.show();
        }

        ProdTable.setItems(prodlist);

    }
    /**On Action for part search*/
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

        }


       if(partsList.size() != 0){
           PartTable.setItems(partsList);
       }else { Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Part Not Found");
           alert.setHeaderText("The Part you searched was not found. Please try again");
           alert.showAndWait();}

    }
    /**checks search input for matching strings or part of string*/
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
    /**Checks input for matching ID int*/
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


    /**checks search input for matching strings or part of string*/
    private ObservableList<Product> SearchProd(String PartialName){
        ObservableList<Product> ProdName = FXCollections.observableArrayList();
        ObservableList<Product> AllProd = Inventory.getProduct();

        for(Product PS: AllProd){
            if(PS.getNameProd().contains(PartialName)){
                ProdName.add(PS);
            }

        }


        return ProdName;
    }
    /**Checks input for matching product ID int*/
    private Product searchProdId(int IdNum){
        ObservableList<Product> AllProd = Inventory.getProduct();

        for(int i = 0; i < AllProd.size(); i++){
            Product PID = AllProd.get(i);
            if(PID.getIdProd() == IdNum){
                return PID;

            }

        }

        return null;
    }

    /**On action for exit button, closes program*/
    public void OnExitMain(ActionEvent actionEvent) {
        System.exit(0);
    }
}
