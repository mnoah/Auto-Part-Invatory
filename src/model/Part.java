package model;

public  class Part {
    private int idPart;
    private String namePart;
    private double pricePart;
    private int stockPart;
    private int minPart;
    private int maxPart;

    public Part(int idPart, String namePart, double pricePart, int stockPart, int minPart, int maxPart) {
        this.idPart = idPart;
        this.namePart = namePart;
        this.pricePart = pricePart;
        this.stockPart = stockPart;
        this.minPart = minPart;
        this.maxPart = maxPart;
    }

    public Part() {

    }

    public int getIdPart() {
        return idPart;
    }

    public void setIdPart(int idPart) {
        this.idPart = idPart;
    }

    public String getNamePart() {
        return namePart;
    }

    public void setNamePart(String namePart) {
        this.namePart = namePart;
    }

    public double getPricePart() {
        return pricePart;
    }

    public void setPricePart(double pricePart) {
        this.pricePart = pricePart;
    }

    public int getStockPart() {
        return stockPart;
    }

    public void setStockPart(int stockPart) {
        this.stockPart = stockPart;
    }

    public int getMinPart() {
        return minPart;
    }

    public void setMinPart(int minPart) {
        this.minPart = minPart;
    }

    public int getMaxPart() {
        return maxPart;
    }

    public void setMaxPart(int maxPart) {
        this.maxPart = maxPart;
    }
}