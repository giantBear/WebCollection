package client;

import server.*;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * The web interence.
 * @author Dominic Duggan
 * @version 1.0
 * @time 02/05/2017
 */

public class Main {

    private static UserManagerInterface userService;
    private static PatientManagerInterface patientService;
    private static ConsultationManagerInterface consultationService;

    public static void main(String[] args) {

        setUpServices();
        GUI gui=new GUI(userService, patientService, consultationService);
    }

    private static void setUpServices()
    {
        try {
            //user
            URL userManagerUrl = new URL("http://localhost:8080/ClinicServices/UserManager?wsdl");
            QName userManagerQname = new QName("http://server/", "UserManagerService");
            Service service = Service.create(userManagerUrl, userManagerQname);
            userService = service.getPort(UserManagerInterface.class);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


        try {
            //patient
            URL patientManagerUrl = new URL("http://localhost:8080/ClinicServices/PatientManager?wsdl");
            QName patientManagerQname = new QName("http://server/", "PatientManagerService");
            Service service = Service.create(patientManagerUrl, patientManagerQname);
            patientService=service.getPort(PatientManagerInterface.class);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            //consultation
            URL consultationManagerUrl = new URL("http://localhost:8080/ClinicServices/ConsultationManager?wsdl");
            QName consultationManagerQname = new QName("http://server/", "ConsultationManagerService");
            Service service = Service.create(consultationManagerUrl, consultationManagerQname);
            consultationService=service.getPort(ConsultationManagerInterface.class);
         } catch (MalformedURLException e) {
         e.printStackTrace();
         }
    }

}





