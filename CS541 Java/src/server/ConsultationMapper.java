package server;

import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * @author Yunong
 * @version 1.0
 * @time 03/05/2017
 */
public class ConsultationMapper {

    public static ArrayList<Consultation> getConsultations() {
        ArrayList<Consultation> consultations = new ArrayList<Consultation>();
        try {
            Connection connection = DBConnection.getConnection();
            CallableStatement callableStatement = connection.prepareCall("{call getConsultations()}");
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                int id=resultSet.getInt(1);
                Date date=resultSet.getDate(2);
                GregorianCalendar gdate=(GregorianCalendar)GregorianCalendar.getInstance();
                gdate.setTime(date);
                Consultation consultation = new Consultation(id,gdate,resultSet.getLong(3), resultSet.getInt(4),resultSet.getString(5));
                consultations.add(consultation);
            }
            callableStatement.close();
            connection.close();
        } catch (SQLException e) {
            //e.printStackTrace();
        }
        return consultations;
    }

    public static String addConsultation(Consultation consultation)
    {
        try {
            Connection connection=DBConnection.getConnection();
            CallableStatement callableStatement=connection.prepareCall("{call addConsultation(?,?,?,?,?)}");
            callableStatement.setInt(1, consultation.getID());
            callableStatement.setDate(2, new Date(consultation.getDate().getTimeInMillis()));
            callableStatement.setLong(3, consultation.getPatientPNC());
            callableStatement.setInt(4, consultation.getDoctorID());
            callableStatement.setString(5, consultation.getObservations());
            callableStatement.executeQuery();
            callableStatement.close();
            connection.close();
        } catch (SQLException e) {
            return e.getMessage();
        }
        return "Success";
    }

    public static String updateConsultation(int oldID, Consultation consultation) {
        try {
            Connection connection=DBConnection.getConnection();
            CallableStatement callableStatement=connection.prepareCall("{call updateConsultation(?,?,?,?,?,?)}");
            callableStatement.setInt(1, oldID);
            callableStatement.setInt(2, consultation.getID());
            callableStatement.setDate(3, new Date(consultation.getDate().getTimeInMillis()));
            callableStatement.setLong(4, consultation.getPatientPNC());
            callableStatement.setInt(5, consultation.getDoctorID());
            callableStatement.setString(6, consultation.getObservations());
            callableStatement.executeQuery();
            callableStatement.close();
            connection.close();
        } catch (SQLException e) {
            return e.getMessage();
        }
        return "Success";
    }

    public static Consultation getConsultation(int ID) {
        Consultation consultation=new Consultation();
        try {
            Connection connection=DBConnection.getConnection();
            CallableStatement callableStatement=connection.prepareCall("{call getConsultation(?)}");
            callableStatement.setInt(1, ID);
            ResultSet resultSet=callableStatement.executeQuery();
            if (resultSet.next())
            { Date date=resultSet.getDate(2);
            GregorianCalendar gdate=new GregorianCalendar();
            gdate.setTime(date);
            consultation=new Consultation(resultSet.getInt(1),gdate, resultSet.getLong(3), resultSet.getInt(4),resultSet.getString(5));}
            callableStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return consultation;
    }

    public static String deleteConsultation(int ID) {
        try {
            Connection connection=DBConnection.getConnection();
            CallableStatement callableStatement=connection.prepareCall("{call deleteConsultation(?)}");
            callableStatement.setInt(1, ID);
            callableStatement.executeQuery();
            callableStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Success";
    }
}


