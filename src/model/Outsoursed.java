package model;

public class Outsoursed extends Part {
    private String compName;

    public Outsoursed(int id, String name, double price, int stock, int min, int max) {


        super(id,name, price, stock, min, max);
        this.compName = compName;
    }

    public String getCompName() {
        return compName;
    }

    public void setCompName(String  compName) {
        this.compName = compName;
    }
}
