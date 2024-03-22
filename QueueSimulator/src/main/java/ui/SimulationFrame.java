package ui;

import Bussiness_Logic.Simulation_Manager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimulationFrame extends JDialog {

    private  JPanel SimulationFrame;
    private JTextField time;
    private JTextField minproc;
    private JTextField maxproc;
    private JTextField minarriv;
    private JTextField maxarriv;
    private JTextField nrserver;
    private JTextField nrclients;
    private JButton startSimulationButton;
    private JButton validateInputDataButton;
    private JScrollPane resultPane;
    private JTextArea resultArea;
    private JLabel servicetime;
    private JLabel waitingtime;
    private JLabel peakhour;

    public JTextArea getResultArea() {
        return resultArea;
    }

    public JLabel getServicetime() {
        return servicetime;
    }

    public JLabel getWaitingtime() {
        return waitingtime;
    }

    public JLabel getPeakhour() {
        return peakhour;
    }

    public SimulationFrame(JFrame parent)
    {
        super(parent);
        setTitle("SimulationForm");
        setContentPane(SimulationFrame);
        setSize(new Dimension(700, 500));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setModal(true);
        setLocationRelativeTo(parent);

        startSimulationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int timeLimit = Integer.parseInt(time.getText());
                int nrCozi = Integer.parseInt(nrserver.getText());
                int nrclienti = Integer.parseInt(nrclients.getText());
                int minProcessingTime = Integer.parseInt(minproc.getText());
                int maxProcessingTime = Integer.parseInt(maxproc.getText());
                int minArrivalTime = Integer.parseInt(minarriv.getText());
                int maxArrivalTime = Integer.parseInt(maxarriv.getText());
               Simulation_Manager simulationManager = new Simulation_Manager(nrCozi, nrclienti, timeLimit, minProcessingTime, maxProcessingTime, minArrivalTime, maxArrivalTime, ui.SimulationFrame.this);

                //System.out.println(simulationManager.timeLimit + " " + simulationManager.nrOfCozi + " " + simulationManager.nrOfClients);
                Thread thread = new Thread(simulationManager);
                thread.start();
            }
        });

        validateInputDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(!time.getText().matches("[0-9]+") || !nrserver.getText().matches("[0-9]+") || !nrclients.getText().matches("[0-9]+")
                        || !minproc.getText().matches("[0-9]+") || !maxproc.getText().matches("[0-9]+") || !minarriv.getText().matches("[0-9]+")
                        || !maxarriv.getText().matches("[0-9]+"))
                {
                    JOptionPane.showMessageDialog(null, "Invalid input data");
                }
                else JOptionPane.showMessageDialog(null, "Start the simulation you twat!");
            }
        });
        setVisible(true);
    }

    public static void main(String[] args) {
        ui.SimulationFrame simulationFrame = new SimulationFrame(null);
    }

}
