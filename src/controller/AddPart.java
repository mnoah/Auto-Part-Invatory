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
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Outsoursed;
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddPart extends Controller implements Initializable {

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






     public static String Name;
    public static double Price;
    public static int Inv;
    public static int min;
    public static int max;
    public static int Mechid;
    public static String compName;
     int autoIdGn;
     private boolean outsourcedRadio;



     ObservableList<InHouse> parts = FXCollections.observableArrayList();
     ObservableList<Product> products = FXCollections.observableArrayList();

     public static int IdGen = 1;









    @Override
    public void initialize(URL location, ResourceBundle resources) {





    }

    public void AddPartSavebtn(ActionEvent actionEvent) throws IOException {

       try {

           autoIdGn = IdGen++;
           Name = addPartName.getText();
           Price = Double.parseDouble(AddPartPrice.getText());
           Inv = Integer.parseInt(addPartInv.getText());
           min = Integer.parseInt(addPartMin.getText());
           max = Integer.parseInt(addPartMax.getText());

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

             if(outsourcedRadio == false){
                 InHouse inAdd = new InHouse(autoIdGn, Name, Price, Inv, min,max);
                 Mechid = Integer.parseInt(AddPartMachId.getText());
                 inAdd.setMachineID(Mechid);

                 Alert AddPartALT = new Alert(Alert.AlertType.CONFIRMATION);
                 AddPartALT.setTitle("Add Part?");
                 AddPartALT.setHeaderText("Are you sure you want to add part?");
                 Optional<ButtonType> results = AddPartALT.showAndWait();
                 if(results.get() == ButtonType.OK){Inventory.AddPart(inAdd);}

             }else {
                 Outsoursed outAdd = new Outsoursed(autoIdGn, Name, Price, Inv, min,max);
                 compName = AddPartMachId.getText();
                 outAdd.setCompName(compName);
                 Alert AddPartALT = new Alert(Alert.AlertType.CONFIRMATION);
                 AddPartALT.setTitle("Add Part?");
                 AddPartALT.setHeaderText("Are you sure you want to add part?");
                 Optional<ButtonType> results = AddPartALT.showAndWait();
                 if(results.get() == ButtonType.OK){ Inventory.AddPart(outAdd);}}








           Parent root = FXMLLoader.load(getClass().getResource("/view/Main_screen.fxml"));
           Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
           Scene scene = new Scene(root, 1050, 500);
           stage.setScene(scene);
           stage.show();

       }catch (NumberFormatException e){
           Alert inputError = new Alert(Alert.AlertType.WARNING);
           inputError.setTitle("Input Error");
           inputError.setHeaderText("Incorrect input");
           inputError.show();
       }

    }

    public void AddAPartCancel(ActionEvent actionEvent) throws IOException {
        Alert Cancel = new Alert(Alert.AlertType.CONFIRMATION);
        Cancel.setTitle("Cancel?");
        Cancel.setHeaderText("Are you sure you want to cancel");
        Optional<ButtonType> results = Cancel.showAndWait();
        if(results.get() == ButtonType.OK){
            Parent root = FXMLLoader.load(getClass().getResource("/view/Main_screen.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 1050, 500);
            stage.setScene(scene);
            stage.show();

        }






    }


    public void InHouseOaAddPart(ActionEvent actionEvent) {
        outsourcedRadio = false;
        addPartMichID.setText("Machine ID");
    }

    public void outAddPartOA(ActionEvent actionEvent) {
        outsourcedRadio = true;
        addPartMichID.setText("Company Name");


    }


    //MODIFY PART




}
