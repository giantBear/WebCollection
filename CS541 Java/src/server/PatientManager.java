package server;

import client.Notification;


import javax.jws.WebService;
import java.util.ArrayList;
import java.util.GregorianCalendar;

//Service Implementation for patient
/**
 * @author Yunong
 * @version 1.0
 * @time 03/05/2017
 */

@WebService(endpointInterface = "server.PatientManagerInterface")
public class PatientManager extends Subject implements PatientManagerInterface  {

    private ArrayList<Notification>observerList;
    private ConsultationManagerInterface consultationService;

    public PatientManager()
    {
        observerList=new ArrayList<Notification>();
    }

    @Override
    public Object[] getPatients() {
        ArrayList<Patient> patients =PatientMapper.getPatients();
        return patients.toArray();
    }

    @Override
    public Patient getPatient(long pnc) {
        Patient patient=new Patient();
        if (PatientValidator.isValidPNC(pnc)) {
            patient=PatientMapper.getPatient(pnc);
        }
        return patient;
    }

    public ArrayList<Notification> getObseverList()
    {
        return observerList;
    }

    @Override
    public String addPatient(long PNC, int ICN, String name,String address, String dateOfBirth) {
        String status=PatientValidator.validatePatientData(PNC,ICN,address,name,dateOfBirth,0);
        if(status.equals("Valid")) {
            GregorianCalendar date=PatientValidator.dateParser(dateOfBirth);
            Patient patient=new Patient(PNC,ICN,name,address, date,0);
            return PatientMapper.addPatient(patient);
        }
        else return status;
    }

    @Override
    public String updatePatient(long oldPNC, long PNC, int ICN, String name, String address, String dateOfBirth, int checkedIn) {
        String status=PatientValidator.validatePatientData(PNC,ICN,address,name,dateOfBirth, checkedIn);
        if(status.equals("Valid")) {
            GregorianCalendar date=PatientValidator.dateParser(dateOfBirth);
            Patient patient=new Patient(PNC,ICN,name,address, date,checkedIn);
            return PatientMapper.updatePatient(oldPNC, patient);
        }
        else return status;
    }


    public String register(Notification notification) {
       observerList.add(notification);
        return "registered";
    }


    public void unregister(Notification notification) {
        observerList.remove(notification);
    }

   @Override
    public String notifyObservers()
    {
        observerList.forEach(client.Observer::update);
        return  "Notifications notified";
    }
    public String notifyObservers(int doctorID, String patientName, long patientPNC)
    {
        String s=new String();
        for (Notification notification:observerList)
        {
            notification.update(doctorID, patientName, patientPNC);
            s+=" +1";
        }
        if (observerList.isEmpty()) return ("no observers");
        return "notified" +s;
    }

    @Override
    public String checkInPatient(long pnc, int doctorID) {
        if (PatientValidator.isValidPNC(pnc))
        {
            Patient patient=PatientMapper.getPatient(pnc);
            if(patient.getPNC()!=-1)
            {
                patient.setCheckedIn(1);
                PatientMapper.updatePatient(pnc, patient);
                notifyObservers(doctorID, patient.getName(), pnc);
                return "Success";
            }
            else return "No patient found with the specified PNC";
        }
        else return "Invalid PNC";
    }

    @Override
    public String checkOutPatient(long pnc) {
        if (PatientValidator.isValidPNC(pnc))
        {
            Patient patient=PatientMapper.getPatient(pnc);
            if(patient.getPNC()!=-1)
            {
                patient.setCheckedIn(0);
                PatientMapper.updatePatient(pnc, patient);
                return "Success";
            }
            else return "No patient found with the specified PNC";
        }
        else return "Invalid PNC";
    }
}
