package Bussiness_Logic;

import Model.Client;
import Model.Coada;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ShortestTime implements Strategy {
    public void addTask(List<Coada> cozi, Client client) throws InterruptedException {
        AtomicInteger shortest_time = new AtomicInteger(Integer.MAX_VALUE);
        Coada coada = null;
        for (Coada value : cozi) {
           if(value.getWaitingPeriod().get() <  shortest_time.get()) {
               {
                   shortest_time = value.getWaitingPeriod();
                   coada = value;
               }
           }

        }
        if (coada != null) {
            coada.addTask(client);
        }
    }
}
