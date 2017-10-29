package server;

/** update delete add. contains database
 * @author Yunong
 * @version 1.0
 * @time 03/05/2017
 */

import java.util.ArrayList;
import java.util.GregorianCalendar;
import javax.jws.WebService;

//Service Implementation Bean

@WebService(endpointInterface = "server.ConsultationManagerInterface")
public class ConsultationManager implements ConsultationManagerInterface {

    @Override
    public Object[] getConsultationsByPatientPNC(long PNC)
    {
        ArrayList<Consultation> consultations=ConsultationMapper.getConsultations();
        ArrayList<Consultation> patientConsultations=new ArrayList<Consultation>();
        for (Consultation consultation:consultations)
            if (consultation.getPatientPNC()==PNC) patientConsultations.add(consultation);
        return patientConsultations.toArray();
    }

    @Override
    public String addConsultation(int id, String date, long patientPNC, int doctorID, String text) {
        String status=ConsultationValidator.validateConsultationDate(id, date, patientPNC, doctorID,  text);
        GregorianCalendar gdate=ConsultationValidator.dateParser(date);
        if (status.equals("Valid"))
        {
            Consultation consultation=new Consultation(id,gdate,patientPNC,doctorID,text);
            return ConsultationMapper.addConsultation(consultation);
        }
        else return status;
    }

    @Override
    public String update(int oldID, int id, String date, long patientPNC, int doctorID, String text) {
        String status=ConsultationValidator.validateConsultationDate(id, date, patientPNC, doctorID,  text);
        GregorianCalendar gdate=ConsultationValidator.dateParser(date);
        if (status.equals("Valid"))
        {
            Consultation consultation=new Consultation(id,gdate,patientPNC,doctorID,text);
            return ConsultationMapper.updateConsultation(oldID, consultation);
        }
        else return status;
    }

    @Override
    public Object[] getConsultations() {
        return ConsultationMapper.getConsultations().toArray();
    }

    @Override
    public Consultation getConsultation(int ID) {
       return ConsultationMapper.getConsultation(ID);
    }

    @Override
    public String deleteConsultation(int id) {
        return ConsultationMapper.deleteConsultation(id);
    }

    @Override
    public String updateDoctorConsultation(int id, String text) {

        Consultation consultation=ConsultationMapper.getConsultation(id);
        if(consultation.getID()!=-1)
        {
            consultation.setObservations(text);
            ConsultationMapper.updateConsultation(id, consultation);
            return "Success";
        }
        else return "No consultation found with the specified ID";
    }
}
