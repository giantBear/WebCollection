package server;

/**interface
 * @author Yunong
 * @version 1.0
 * @time 01/05/2017
 */

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

//Service Endpoint Interface
@WebService
@SOAPBinding(style = Style.RPC)
public interface  ConsultationManagerInterface {

    @WebMethod
    public Object[] getConsultationsByPatientPNC(long PNC);

    @WebMethod
    public String addConsultation(int id, String date, long patientPNC, int doctorID, String text);

    @WebMethod
    public String update(int oldID, int id, String date, long patientPNC, int doctorID, String text);

    @WebMethod
    public Object[] getConsultations();

    @WebMethod
    public Consultation getConsultation(int ID);

    @WebMethod
    String deleteConsultation(int id);

    @WebMethod
    String updateDoctorConsultation(int id, String text);
}
