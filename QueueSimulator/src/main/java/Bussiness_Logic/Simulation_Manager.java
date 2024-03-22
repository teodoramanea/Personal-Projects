package Bussiness_Logic;

import Model.Client;
import Model.Coada;
import ui.SimulationFrame;

import javax.swing.*;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Simulation_Manager implements Runnable{
    ///pe astea o sa le citesti defapt din interfata
    public int timeLimit;
    public int maxProcessingTime;
    public int minProcessingTime;
    public int maxArrivalTime;
    public int minArrivalTime;
    public int nrOfCozi;
    public int nrOfClients;
    private float avgServiceTime;
    private float avgWaitingTime;
    private float peakHour = 0;
    private Scheduler scheduler;
    private List<Client> generatedClienti;

    private SimulationFrame simulationFrame;
    public SelectionPolicy selectionPolicy = SelectionPolicy.Shortest_Time;
    public Scheduler getScheduler() {
        return scheduler;
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public List<Client> getGeneratedClienti() {
        return generatedClienti;
    }

    public void setGeneratedClienti(List<Client> generatedClienti) {
        this.generatedClienti = generatedClienti;
    }

    public Simulation_Manager(Integer nrOfCozi, Integer nrOfClients, Integer timeLimit, Integer minProcessingTime, Integer maxProcessingTime, Integer minArrivalTime, Integer maxArrivalTime, SimulationFrame simulationFrame)
    {
        this.nrOfClients = nrOfClients;
        this.nrOfCozi = nrOfCozi;
        this.timeLimit = timeLimit;
        this.minProcessingTime = minProcessingTime;
        this.maxProcessingTime = maxProcessingTime;
        this.minArrivalTime = minArrivalTime;
        this.maxArrivalTime = maxArrivalTime;
        this.simulationFrame = simulationFrame;
        generatedClienti = new ArrayList<>();
        scheduler = new Scheduler(nrOfCozi, nrOfClients);
        scheduler.changeStrategy(selectionPolicy);

        for(int i=0;i<nrOfCozi;i++)
        {
            Coada coada = new Coada();
            scheduler.getCozi().add(coada);
            Thread thread = new Thread(coada);
            thread.start();
        }
        for(Coada coada : scheduler.getCozi())
            System.out.println(coada.getID() + " " + coada.getWaitingPeriod());

         generateNRandomClienti(nrOfClients);
    }
    ///random client generator
    private void generateNRandomClienti(int N)
    {
        generatedClienti = new ArrayList<Client>();
        for(int i=0;i<N;i++)
        {
            Random random = new Random();
            int processingTime = random.nextInt(maxProcessingTime - minProcessingTime + 1) + minProcessingTime;
            int arrivalTime = random.nextInt(maxArrivalTime - minArrivalTime + 1) + minArrivalTime;
            avgServiceTime += processingTime;
            Client client = new Client(arrivalTime, processingTime);
            generatedClienti.add(client);
        }
        ///sortare dupa arrivalTime
        generatedClienti.sort(new Comparator<Client>() {
            @Override
            public int compare(Client o1, Client o2) {
                return o1.getArrivalTime().compareTo(o2.getArrivalTime());
            }
        });
    }

    public static int currentTime = 0;
    int maxclientipercozi = 0;
    @Override
    public synchronized void run() {

        try (FileWriter writer = new FileWriter("log2.txt")){
            PrintWriter out = new PrintWriter(writer, true);


        while(currentTime <= timeLimit) {
            String firstString = new String();
            String secondString = new String();
            System.out.println("Time" + currentTime);
            System.out.println("Waiting Clients:");
            firstString = firstString + "Time" + currentTime;
            secondString = secondString + "Waiting Clients:";
            simulationFrame.getResultArea().append(firstString + "\n");
            simulationFrame.getResultArea().append(secondString + "\n");
            out.println(firstString + "\n");
            out.println(secondString + "\n");
                for (int i = 0; i < generatedClienti.size(); i++) {
                    String clientString = new String();
                    Client client = generatedClienti.get(i);
                    if (client.getArrivalTime() == currentTime) {
                        try {
                            scheduler.dispatchClient(client);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        generatedClienti.remove(i);
                        i--;
                    }
                    else {
                        System.out.println("(" + client.getID() + "," + client.getArrivalTime() + "," + client.getServiceTime() + ")");
                        clientString = clientString + "(" + client.getID() + "," + client.getArrivalTime() + "," + client.getServiceTime() + ")";
                        simulationFrame.getResultArea().append(clientString + "\n");
                        out.println(clientString + "\n");
                    }
                }
                int clientipercozi = 0;
                for (int i = 0; i < nrOfCozi; i++) {
                    String coadaString = new String();
                    String coadaempty = new String();
                    Coada coada = scheduler.getCozi().get(i);

                    if (coada.getClienti().size() == 0) {
                        System.out.println("Queue" + " " + coada.getID() + " " + "empty");
                        coadaempty = coadaempty + "Queue" + " " + coada.getID() + " " + "empty";
                        simulationFrame.getResultArea().append(coadaempty + "\n");
                        out.println(coadaempty + "\n");
                    } else {
                        System.out.print("Queue " + coada.getID() + ": ");
                        String stringQueue = new String();
                        stringQueue = stringQueue + "Queue " + coada.getID() + ": ";
                        simulationFrame.getResultArea().append(stringQueue);
                        out.println(stringQueue);
                        for (Client client : coada.getClienti()) {
                            System.out.print("(" + client.getID() + "," + client.getArrivalTime() + "," + client.getServiceTime() + ")");
                            coadaString = coadaString + "(" + client.getID() + "," + client.getArrivalTime() + "," + client.getServiceTime() + ")";
                            simulationFrame.getResultArea().append(coadaString);
                            out.println(coadaString);
                            clientipercozi += 1;
                        }
                        System.out.println();
                        simulationFrame.getResultArea().append("\n");
                        out.println("\n");
                    }
                    if(clientipercozi > maxclientipercozi)
                    {
                        maxclientipercozi = clientipercozi;
                        peakHour = currentTime;
                    }
                }

                currentTime++;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
        }
        avgServiceTime = avgServiceTime/nrOfClients;
        avgWaitingTime = avgServiceTime/nrOfCozi;
        simulationFrame.getServicetime().setText(String.valueOf(avgServiceTime));
        simulationFrame.getWaitingtime().setText(String.valueOf(avgWaitingTime));
        simulationFrame.getPeakhour().setText(String.valueOf(peakHour));
        System.out.println(avgServiceTime);
        System.out.println(avgWaitingTime);
        System.out.println(peakHour);
        out.println("average service time:" + avgServiceTime);
        out.println("average waiting time:" + avgWaitingTime);
        out.println("peak hour:" + peakHour);
        } catch (Exception e) {
            e.printStackTrace();
        }
        scheduler.stopAllCozi();
    }

}

