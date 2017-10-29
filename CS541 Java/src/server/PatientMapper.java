package server;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.CallableStatement;
import java.sql.Date;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.util.GregorianCalendar;

/**
 * @author Yunong
 * @version 1.0
 * @time 03/05/2017
 */

public class PatientMapper {

    public static String addPatient(Patient patient)
    {
        try {
            Connection connection=DBConnection.getConnection();
            CallableStatement callableStatement=connection.prepareCall("{call addPatient(?,?,?,?,?)}");
            callableStatement.setLong(1, patient.getPNC());
            callableStatement.setInt(2, patient.getICN());
            callableStatement.setString(3, patient.getName());
            callableStatement.setString(4, patient.getAddress());
            callableStatement.setDate(5, new Date(patient.getDateOfBirth().getTimeInMillis()));
            callableStatement.executeQuery();
            callableStatement.close();
            connection.close();
        } catch (SQLException e) {
            return e.getMessage();
        }
        return "Success";
    }

    public static String updatePatient(long oldPNC, Patient patient) {
        try {
            Connection connection=DBConnection.getConnection();
            CallableStatement callableStatement=connection.prepareCall("{call updatePatient(?,?,?,?,?,?,?)}");
            callableStatement.setLong(1, oldPNC);
            callableStatement.setLong(2, patient.getPNC());
            callableStatement.setInt(3, patient.getICN());
            callableStatement.setString(4, patient.getName());
            callableStatement.setString(5, patient.getAddress());
            callableStatement.setDate(6, new Date(patient.getDateOfBirth().getTimeInMillis()));
            callableStatement.setInt(7, patient.getCheckedIn());
            callableStatement.executeQuery();
            callableStatement.close();
            connection.close();
        } catch (SQLException e) {
            return e.getMessage();
        }
        return "Success";
    }

    public static ArrayList<Patient> getPatients() {
        ArrayList<Patient> patients=new ArrayList<Patient>();
        try {
            Connection connection=DBConnection.getConnection();
            CallableStatement callableStatement=connection.prepareCall("{call getPatients()}");
            ResultSet resultSet=callableStatement.executeQuery();
            while (resultSet.next())
            {
                Date date=resultSet.getDate(5);
                GregorianCalendar gdate=new GregorianCalendar();
                gdate.setTime(date);
                Patient patient=new Patient(resultSet.getLong(1),resultSet.getInt(2),resultSet.getString(3),resultSet.getString(4),gdate,resultSet.getInt(6));
                patients.add(patient);
            }
            callableStatement.close();
            connection.close();
        } catch (SQLException e) {
        }
        return patients;

    }

    public static Patient getPatient(long PNC) {
        Patient patient=new Patient();
        try {
             Connection connection=DBConnection.getConnection();
            CallableStatement callableStatement=connection.prepareCall("{call getPatient(?)}");
            callableStatement.setLong(1,PNC);
            ResultSet resultSet=callableStatement.executeQuery();
            resultSet.next();
            Date date=resultSet.getDate(5);
            GregorianCalendar gdate=new GregorianCalendar();
            gdate.setTime(date);
            patient=new Patient(resultSet.getLong(1),resultSet.getInt(2),resultSet.getString(3),resultSet.getString(4),gdate,resultSet.getInt(6));
            callableStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patient;
    }
}
