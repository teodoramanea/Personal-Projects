package Bussiness_Logic;

import Model.Client;
import Model.Coada;

import java.util.List;

public interface Strategy {
    public void addTask(List<Coada> cozi, Client client) throws InterruptedException;
}
