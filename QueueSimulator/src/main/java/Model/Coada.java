package Model;

import Bussiness_Logic.Simulation_Manager;
//import jdk.internal.org.jline.utils.ShutdownHooks;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Coada implements Runnable{
    private static int contor = 1;
    private Integer ID;
    private BlockingQueue<Client> clienti;
    private AtomicInteger waitingPeriod;
    private Boolean isRunning;

    public Coada()
    {
        this.ID = contor;
        contor++;
        clienti = new LinkedBlockingQueue<>();
        waitingPeriod = new AtomicInteger(0);
        isRunning = true;
    }

    public AtomicInteger getWaitingPeriod() {
        return waitingPeriod;
    }

    public BlockingQueue<Client> getClienti() {
        return clienti;
    }

    public Boolean getRunning() {
        return isRunning;
    }

    public void setRunning(Boolean running) {
        isRunning = running;
    }

    public void setClienti(BlockingQueue<Client> clienti) {
        this.clienti = clienti;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public void setWaitingPeriod(AtomicInteger waitingPeriod) {
        this.waitingPeriod = waitingPeriod;
    }
    public void  addTask(Client client) throws InterruptedException {
            clienti.put(client);
           // waitingPeriod.addAndGet(client.getServiceTime());
            waitingPeriod.getAndAdd(client.getServiceTime());
    }
    public synchronized void run() {
        while(isRunning) {
            Client client = null;
                client = clienti.peek();
            AtomicInteger serviceTime = null;
            if (client != null) {
                serviceTime = new AtomicInteger(client.getServiceTime());
            }
            if (serviceTime != null && serviceTime.get() == 1)
            {
                clienti.poll();
                waitingPeriod.decrementAndGet();
            }
            if (serviceTime != null && client.getArrivalTime() != Simulation_Manager.currentTime) {
                serviceTime.decrementAndGet();
            }
            if (client != null) {
                client.setServiceTime(serviceTime.get());
            }

            try {
                if (client != null) {
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
}


