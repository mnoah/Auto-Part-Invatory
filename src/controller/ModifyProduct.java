package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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


/** This is the controller for the modify product screen
 * FUTURE ENHANCEMENT: It would be nice to see */
public class ModifyProduct extends Controller implements Initializable {

    public TextField modProdId;
    public TextField modProdName;
    public TextField modProdInv;
    public TextField modProdPrice;
    public TextField modProdMax;
    public TextField modProdMin;
    public TextField modProdSrchTxt;

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
    String modpartSearch;

    public Product product;
    public ObservableList<Part> Parts2 = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        product = Controller.GetProdSelc();

        modProdId.setText(String.valueOf(product.getIdProd()));
        modProdName.setText(String.valueOf(product.getNameProd()));
        modProdPrice.setText(String.valueOf(product.getPriceProd()));
        modProdInv.setText(String.valueOf(product.getStockProd()));
        modProdMax.setText(String.valueOf(product.getMinProd()));
        modProdMin.setText(String.valueOf(product.getMaxProd()));
        Parts2 = product.getProductParts();




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

        PartTable2.setItems(Parts2);






    }

    /**Adds part from part table to associated part table*/
    public void OnModProdbtn(ActionEvent actionEvent) {

        Part selected = (Part) PartTable.getSelectionModel().getSelectedItem();


        Parts2.add(selected);

        PartTable2.setItems(Parts2);
    }
    /**Removes part from associated part table*/
    public void RemoveModAssPart(ActionEvent actionEvent) {
        Part selected = (Part) PartTable2.getSelectionModel().getSelectedItem();
        Parts2.remove(selected);
    }
    /**Saves edits made to selected product.
     * first sets text field with selected product data.
     * then sets product values with any edits made, if no edit is made to a filed it will stay they same.*/
    public void OnModProdSavebtn(ActionEvent actionEvent) throws IOException {
        ID = Integer.parseInt(modProdId.getText());
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
        prodMod.setProductParts(Parts2);

        Inventory.ModProduct(modProdIndex, prodMod);

        //Inventory.AddProduct(prodMod);


        Parent root = FXMLLoader.load(getClass().getResource("/view/Main_screen.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1050, 500);
        stage.setScene(scene);
        stage.show();

    }
    /**returns user to main screen*/
    public void OnModProdCancelbtn(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Main_screen.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1050, 500);
        stage.setScene(scene);
        stage.show();
    }

    /**On Action for part search*/
    public void modProdSrch(ActionEvent actionEvent) throws IOException {
        modpartSearch = modProdSrchTxt.getText();
        ObservableList<Part> partsList = SearchParts(modpartSearch);
        try {
            if (partsList.size() == 0) {
                int ID = Integer.parseInt(modpartSearch);
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

}

