package client;

import javax.swing.*;

/**
 * The class made to build the GUI of the application.
 * @author Dominic Duggan
 * @version 1.0
 * @time 02/05/2017
 */
public class Notification extends Observer{

    private int doctorID;

    public Notification()
    {};

    public Notification(int doctorID) {
        this.doctorID=doctorID;
    }

    /**
     * Shows all kinds of informative message
     * @param s the string containing the message to be showed
     */
    private void showMessage(String s) {
        JOptionPane.showMessageDialog(null, s);
    }

    @Override
    public String update()
    {
        return "Updated";
    }

    public void update(int doctorID, String patientName, long patientPNC) {
        if (this.doctorID==doctorID) showMessage("Patient "+patientName+" with PNC "+patientPNC+" has arrived");
        System.out.println("Notified");

    }
}


