package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {
    private static ObservableList<InHouse> parts = FXCollections.observableArrayList();

    public static void AddPart(InHouse part) {

        parts.add(part);
    }


    public static ObservableList<InHouse> getPart() {

        return parts;
    }

    public static void deletePart(InHouse part) {

        parts.remove(part);
    }

    public static void ModPart(InHouse part) {
        // parts.(part);
    }

}
