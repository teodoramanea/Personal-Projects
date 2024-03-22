package Bussiness_Logic;

import Bussiness_Logic.Strategy;
import Model.Client;
import Model.Coada;

import java.util.List;

public class ShortestQueue implements Strategy {
    public void addTask(List<Coada> cozi, Client client) throws InterruptedException {
        Integer shortest_queue = Integer.MAX_VALUE;
        Coada coada = null;
        for (Coada value : cozi)
        {
            int queue = 0;
            for(Client client1 : value.getClienti())
            {
                queue++;
            }
            if(queue < shortest_queue)
            {
                shortest_queue = queue;
                coada = value;
            }
        }
        if (coada != null) {
            coada.addTask(client);
        }
    }
}
