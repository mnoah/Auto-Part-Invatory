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
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
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
    public TextField OnPartSearchTextFld;
    public TextField partModName;
    String partSearchTxt;




    private static ObservableList<InHouse> parts = FXCollections.observableArrayList();
    public ObservableList<Product> products = FXCollections.observableArrayList();
    private InHouse ModPart = null;







//duplactat?


    public void DeletePartAction(ActionEvent actionEvent) {
        InHouse SP = (InHouse)PartTable.getSelectionModel().getSelectedItem();

        if(SP == null)
            return;

        Inventory.deletePart(SP);
    }



    public void ModifyProductsAction(ActionEvent actionEvent) {

    }

    public void DeleteProductsAction(ActionEvent actionEvent) {

    }

    public void ProductSearchAction(ActionEvent actionEvent) {
    }

    public void AddProductsAction(ActionEvent actionEvent) {
    }




    public void AddPartButtonAction(ActionEvent actionEvent) throws IOException {


        Parent root = FXMLLoader.load(getClass().getResource("/view/AddPart.fxml"));
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 475, 500);
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

    public  void addTableItem(){

       // parts.add( new InHouse(1, "Name", 1, 1, 1,1));

    }

    private static boolean firstRun= true;

    private void testData(){

        if(!firstRun){
            return;
        }

        firstRun = false;
        int ID;
        ID = AddPart.IdGen++;

        InHouse TestPart = new InHouse(ID, "Wheel", 3.0, 20, 1,20);
        Inventory.AddPart(TestPart);
        ID = AddPart.IdGen++;
        InHouse TestPart2 = new InHouse(ID, "Screw", 1.0, 20, 1,20);
        Inventory.AddPart(TestPart2);
        ID = AddPart.IdGen++;
        InHouse TestPart3 = new InHouse(ID, "Tire", 1.0, 20, 1,20);
        Inventory.AddPart(TestPart3);


    }

    private static Part PS;
    public static Part GetSP(){
        return PS;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

      /*  InHouse P = new InHouse(1, "Wheel", 11, 22, 1,1);
        Inventory.AddPart(P);*/
        PartTable.setItems(Inventory.getPart());





       //Test Data
        ProdTable.setItems(products);

        PartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        PartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        PricePartCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        InvPartCol.setCellValueFactory(new PropertyValueFactory<>("stock"));

        PartIDProdCol.setCellValueFactory(new PropertyValueFactory<>("idProd"));
        PartNameProdCol.setCellValueFactory(new PropertyValueFactory<>("nameProd"));
        PriceProdCol.setCellValueFactory(new PropertyValueFactory<>("priceProd"));
        InvProdCol.setCellValueFactory(new PropertyValueFactory<>("stockProd"));



        parts.add(new InHouse(1,"Name",3,4,5,6));

        products.add(new Product(1, "Car", 24000, 5, 1,1));
        testData();






        ObservableList<InHouse> partsList = Inventory.getPart();
        PartTable.setItems(partsList);



        PS = ModPart;

    }



    public void ModifyPartButtonAction(ActionEvent actionEvent) throws IOException {


        PS = (InHouse)PartTable.getSelectionModel().getSelectedItem();





        Parent root = FXMLLoader.load(getClass().getResource("/view/ModifyPart.fxml"));
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root,  450, 500);
        stage.setScene(scene);
        stage.show();



    }



    public void PartSearchAction(ActionEvent actionEvent) {


        partSearchTxt = OnPartSearchTextFld.getText();
        ObservableList<InHouse> partsList = SearchParts(partSearchTxt);
        try {
            if (partsList.size() == 0) {
                int ID = Integer.parseInt(partSearchTxt);
                InHouse PID = searchId(ID);
                if (PID != null) {
                    partsList.add(PID);
                }
            }
        }
        catch (NumberFormatException e){
            //ignore
        }


        PartTable.setItems(partsList);

    }

        private ObservableList<InHouse> SearchParts(String PartialName){
            ObservableList<InHouse> PartName = FXCollections.observableArrayList();
            ObservableList<InHouse> AllParts = Inventory.getPart();

            for(InHouse PS: AllParts){
                if(PS.getName().contains(PartialName)){
                    PartName.add(PS);
                }

            }


            return PartName;
    }

    private InHouse searchId(int IdNum){
        ObservableList<InHouse> AllParts = Inventory.getPart();

        for(int i = 0; i < AllParts.size(); i++){
            InHouse PID = AllParts.get(i);
            if(PID.getId() == IdNum){
                return PID;

            }

        }

        return null;
    }






}
