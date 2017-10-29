package server;

/**
 * Interence for consultation.
 * @author Yunong
 * @version 1.0
 * @time 03/05/2017
 */

import java.util.GregorianCalendar;
public class Consultation {

    private int ID;
    private GregorianCalendar date;
    private long patientPNC;
    private  int doctorID;
    private String observations;

    public Consultation() {
        this.ID=-1;
    }

    public Consultation(int ID, GregorianCalendar date,long patientPNC, int doctorID, String observations) {
        this.ID = ID;
        this.date = date;
        this.patientPNC = patientPNC;
        this.doctorID = doctorID;
        this.observations = observations;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public GregorianCalendar getDate() {
        return date;
    }

    public void setDate(GregorianCalendar date) {
        this.date = date;
    }

    public long getPatientPNC() {
        return patientPNC;
    }

    public void setPatientPNC(long patientPNC) {
        this.patientPNC = patientPNC;
    }

    public int getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(int doctorID) {
        this.doctorID = doctorID;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }
}
