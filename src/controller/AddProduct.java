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

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
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
    public TextField addProdSrchTxt;


    public  String Name;
    public  double Price;
    public  int Inv;
    public  int min;
    public  int max;
    int autoIdGn;
    public static int IdGen = 1;
    public String partSearch;

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

            if(max < min ){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Stock should not be great than max");
                alert.show();
                return;

            }
            else if (Inv < min){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Stock cannot be less than min");
                alert.show();
                return;

            } else if (Inv > max) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Stock cannot be greater than max");
                alert.show();
                return;
            }


            Product Add = new Product(autoIdGn, Name, Price, Inv, min, max);
            Add.setProductParts(Parts2);

            Alert AddPartALT = new Alert(Alert.AlertType.CONFIRMATION);
            AddPartALT.setTitle("Add Part?");
            AddPartALT.setHeaderText("Are you sure you want to add part?");
            Optional<ButtonType> results = AddPartALT.showAndWait();
            if(results.get() == ButtonType.OK){ Inventory.AddProduct(Add);}

            Parent root = FXMLLoader.load(getClass().getResource("/view/Main_screen.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 1050, 500);
            stage.setScene(scene);
            stage.show();
        }
        catch (NumberFormatException e){
            Alert inputError = new Alert(Alert.AlertType.WARNING);
            inputError.setTitle("Input Error");
            inputError.setHeaderText("Incorrect input");
            inputError.show();

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


    public void addProdSrchbtn(ActionEvent actionEvent) {
        partSearch = addProdSrchTxt.getText();
        ObservableList<Part> partsList = SearchParts(partSearch);
        try {
            if (partsList.size() == 0) {
                int ID = Integer.parseInt(partSearch);
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
}
