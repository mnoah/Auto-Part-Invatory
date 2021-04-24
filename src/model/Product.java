package model;

public  class Product {
    private int idProd;
    private String nameProd;
    private double priceProd;
    private int stockProd;
    private int minProd;
    private int maxProd;

    public Product(int idProd, String nameProd, double priceProd, int stockProd, int minProd, int maxProd) {
        this.idProd = idProd;
        this.nameProd = nameProd;
        this.priceProd = priceProd;
        this.stockProd = stockProd;
        this.minProd = minProd;
        this.maxProd = maxProd;
    }


    public int getIdProd() {
        return idProd;
    }

    public void setIdProd(int idProd) {
        this.idProd = idProd;
    }

    public String getNameProd() {
        return nameProd;
    }

    public void setNameProd(String nameProd) {
        this.nameProd = nameProd;
    }

    public double getPriceProd() {
        return priceProd;
    }

    public void setPriceProd(double priceProd) {
        this.priceProd = priceProd;
    }

    public int getStockProd() {
        return stockProd;
    }

    public void setStockProd(int stockProd) {
        this.stockProd = stockProd;
    }

    public int getMinProd() {
        return minProd;
    }

    public void setMinProd(int minProd) {
        this.minProd = minProd;
    }

    public int getMaxProd() {
        return maxProd;
    }

    public void setMaxProd(int maxProd) {
        this.maxProd = maxProd;
    }
}