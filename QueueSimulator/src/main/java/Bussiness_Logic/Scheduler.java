package Bussiness_Logic;

import Model.Client;
import Model.Coada;

import java.util.ArrayList;
import java.util.List;

public class Scheduler{
    private List<Coada> cozi;
    private int maxNoClienti;
    private int maxClientiPerCoada;
    private Strategy strategy;
    public SelectionPolicy selectionPolicy;

    public Scheduler(int maxNoClienti, int maxClientiPerCoada)
    {
        this.maxNoClienti = maxNoClienti;
        this.maxClientiPerCoada = maxClientiPerCoada;
        this.cozi = new ArrayList<>();
    }

    public List<Coada> getCozi() {
        return cozi;
    }

    public void setCozi(List<Coada> cozi) {
        this.cozi = cozi;
    }

    public Integer getMaxNoClienti() {
        return maxNoClienti;
    }

    public void setMaxNoClienti(Integer maxNoClienti) {
        this.maxNoClienti = maxNoClienti;
    }

    public Integer getMaxClientiPerCoada() {
        return maxClientiPerCoada;
    }

    public void setMaxClientiPerCoada(Integer maxClientiPerCoada) {
        this.maxClientiPerCoada = maxClientiPerCoada;
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }
    public void changeStrategy(SelectionPolicy policy)
    {
        if(policy == SelectionPolicy.Shortest_Queue)
            strategy = new ShortestQueue();
         if(policy == SelectionPolicy.Shortest_Time)
             strategy = new ShortestTime();
    }
    public void dispatchClient(Client client) throws InterruptedException {
        strategy.addTask(cozi, client);

    }
    public void stopAllCozi() {
        for (Coada coada : cozi) {
            coada.setRunning(false);
        }
    }
}
