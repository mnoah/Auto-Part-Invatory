package model;

public class InHouse extends Part {
    private int machineID;

    public InHouse(){

    }
    public InHouse(int idPart, String namePart, double pricePart, int stockPart, int minPart, int maxPart) {
       /* setIdPart(getIdPart());
        setNamePart(getNamePart());
        setPricePart(getPricePart());
        setStockPart(getStockPart());
        setMinPart(getMinPart());
        setMaxPart(getMaxPart());*/

        super(idPart,namePart, pricePart, stockPart, minPart, maxPart);
        this.machineID = machineID;
    }

    public int getMachineID() {
        return machineID;
    }

    public void setMachineID(int machineID) {
        this.machineID = machineID;
    }


}
