package controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Part;
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

    public class ModifyPart extends Controller implements Initializable {

        //Radio Button
        public RadioButton AddInHouse;
        public RadioButton AddOutsourced;
        public Label addPartMichID;

        //Text fields
        public TextField ModPartID;
        public TextField partModName;
        public TextField modPartInv;
        public TextField modPartPrice;
        public TextField modPartMax;
        public TextField ModPartMechID;
        public TextField modPartMin;



        int ID;
        String Name;
        double Price;
        int Inv;
        int min;
        int max;
        String Mechid;

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







        }


        public void OnModPartSaveBtn(ActionEvent actionEvent) throws IOException {
        Name = partModName.getText();
        Price = Double.parseDouble(modPartPrice.getText());
        Inv = Integer.parseInt(modPartInv.getText());
        min = Integer.parseInt(modPartMin.getText());
        max = Integer.parseInt(modPartMax.getText());


        InHouse partMod = new InHouse(ID, Name, Price, Inv, min,max);
        partMod.setId(ID);
        partMod.setName(Name);
        partMod.setPrice(Price);
        partMod.setStock(Inv);
        partMod.setMin(min);
        partMod.setMax(max);
        Inventory.ModPart(, partMod);


        Parent root = FXMLLoader.load(getClass().getResource("/view/Main_screen.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1050, 500);
        stage.setScene(scene);
        stage.show();
        }

        public void ModifyPartCancel(ActionEvent actionEvent) throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("/view/Main_screen.fxml"));
            Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 1050, 500);
            stage.setScene(scene);
            stage.show();

        }
    }
