package server;

import client.Observer;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

//Service Endpoint Interface
/**
 * @author Yunong
 * @version 1.0
 * @time 03/05/2017
 */

@WebService
@SOAPBinding(style = Style.RPC)
public interface PatientManagerInterface {

   @WebMethod
    public String addPatient(long PNC, int ICN, String name, String address, String dateOfBirth);

    @WebMethod
    public String register(Observer observer);

    @WebMethod
    public void unregister(Observer observer);

    @WebMethod
    public String updatePatient(long  oldPNC, long PNC, int ICN, String name, String address, String dateOfBirth, int checkedIn);

    @WebMethod
    public Object[] getPatients();

    @WebMethod
    public Patient getPatient(long pnc);

    @WebMethod
    public String checkInPatient(long pnc, int doctorID);

    @WebMethod
    public String checkOutPatient(long pnc);
}

