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
import model.*;

import java.io.IOException;
import java.io.PipedReader;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

    public class ModifyPart extends Controller implements Initializable {

        //Radio Button
        public RadioButton AddInHouse;
        public RadioButton AddOutsourced;
        public Label modPartMichID;

        //Text fields
        public TextField ModPartID;
        public TextField partModName;
        public TextField modPartInv;
        public TextField modPartPrice;
        public TextField modPartMax;
        public TextField ModPartMechID;
        public TextField modPartMin;
        private boolean outsourcedRadio;
        private RadioButton modInhouseRadio;
        private RadioButton modOutsourcedRadio;



        int ID;
        String Name;
        double Price;
        int Inv;
        int min;
        int max;
        int Mechid;
        String compName;

        private Part Parts;





        @Override
        public void initialize(URL location, ResourceBundle resources) {
        Parts = Controller.GetSP();
        ModPartID.setText(String.valueOf(Parts.getId()));
        partModName.setText(String.valueOf(Parts.getName()));
        modPartPrice.setText(String.valueOf(Parts.getPrice()));
        modPartInv.setText(String.valueOf(Parts.getStock()));
        modPartMin.setText(String.valueOf(Parts.getMin()));
        modPartMax.setText(String.valueOf(Parts.getMax()));

        if(Parts instanceof InHouse){
            modPartMichID.setText("Machine ID");
            ModPartMechID.setText(Integer.toString(((InHouse) Parts).getMachineID()));
            modInhouseRadio.setSelected(true);
        }else {
            modPartMichID.setText("Company Name");
            ModPartMechID.setText(((Outsoursed) Parts).getCompName());
            modOutsourcedRadio.setSelected(true);
        }







        }


        public void OnModPartSaveBtn(ActionEvent actionEvent) throws IOException {

         try {
             Name = partModName.getText();
             Price = Double.parseDouble(modPartPrice.getText());
             Inv = Integer.parseInt(modPartInv.getText());
             min = Integer.parseInt(modPartMin.getText());
             max = Integer.parseInt(modPartMax.getText());

             if (max < min) {
                 Alert alert = new Alert(Alert.AlertType.ERROR);
                 alert.setHeaderText("Stock should not be great than max");
                 alert.show();
                 return;

             } else if (Inv < min) {
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

             if (outsourcedRadio == false) {


                 InHouse partMod = new InHouse(ID, Name, Price, Inv, min, max);
                 partMod.setId(ID);
                 partMod.setName(Name);
                 partMod.setPrice(Price);
                 partMod.setStock(Inv);
                 partMod.setMin(min);
                 partMod.setMax(max);
                 Inventory.ModPart(modIndex, partMod);
                 Mechid = Integer.parseInt(ModPartMechID.getText());
                 partMod.setMachineID(Mechid);

                 Alert AddPartALT = new Alert(Alert.AlertType.CONFIRMATION);
                 AddPartALT.setTitle("Add Part?");
                 AddPartALT.setHeaderText("Are you sure you want to add part?");
                 Optional<ButtonType> results = AddPartALT.showAndWait();
                 if (results.get() == ButtonType.OK) {
                     Inventory.AddPart(partMod);
                 }

             } else {
                 Outsoursed outAdd = new Outsoursed(ID, Name, Price, Inv, min, max);
                 compName = ModPartMechID.getText();
                 outAdd.setCompName(compName);
                 Alert AddPartALT = new Alert(Alert.AlertType.CONFIRMATION);
                 AddPartALT.setTitle("Add Part?");
                 AddPartALT.setHeaderText("Are you sure you want to add part?");
                 Optional<ButtonType> results = AddPartALT.showAndWait();
                 if (results.get() == ButtonType.OK) {
                     Inventory.AddPart(outAdd);
                 }
             }


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

        public void ModifyPartCancel(ActionEvent actionEvent) throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("/view/Main_screen.fxml"));
            Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 1050, 500);
            stage.setScene(scene);
            stage.show();

        }


        public void onModInhouse(ActionEvent actionEvent) {
            outsourcedRadio = false;
            modPartMichID.setText("Machine ID");
        }

        public void onModOutsourced(ActionEvent actionEvent) {
            outsourcedRadio = true;
            modPartMichID.setText("Company Name");
        }
    }
