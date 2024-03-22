package Model;

public class Client {
    private static int contor = 1;
    private Integer ID;
    private Integer arrivalTime;
    private Integer serviceTime;

    public Client(Integer arrivalTime, Integer serviceTime) {
        this.ID = contor;
        contor++;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Integer arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Integer getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(Integer serviceTime) {
        this.serviceTime = serviceTime;
    }
}
