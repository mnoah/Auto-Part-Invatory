package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {

    //Part
    private static ObservableList<Part> parts = FXCollections.observableArrayList();
    public static void AddPart(Part part) {

        parts.add(part);
    }


    public static ObservableList<Part> getPart() {

        return parts;
    }



    public static void deletePart(Part part) {

        parts.remove(part);


    }


    public static void ModPart(int index, Part part) {
        parts.set(index, part);

    }





    //Product
    private static ObservableList<Product> products = FXCollections.observableArrayList();

    public static void AddProduct(Product product){

        products.add(product);
    }

    public static ObservableList<Product> getProduct() {

        return products;
    }
    public static void deleteProduct(Product product) {

        products.remove(product);

    }
    public static void ModProduct(int index, Product product) {
        products.set(index, product);

    }

}
