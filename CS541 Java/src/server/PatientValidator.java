package server;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

/** 
 * basic check.
 * @author Yunong
 * @version 1.0
 * @time 03/05/2017
 */

public class PatientValidator {

    public static String validatePatientData(long PNC, int ICN, String address, String name, String dateOfBirth, int checkedIn) {

        if (!isValidPNC(PNC)) return "Invalid pnc";
        if (!isValidICN(ICN)) return "Invalid icn";
        if (!isValidDate(dateOfBirth)) return "Invalid date of birth";
        if (!isValidCheckedIn(checkedIn)) return "Invalid checkedIn";
        return "Valid";
    }

    public static boolean isValidPNC(long pnc) {
        //pnc must be 13 digits long
        if (pnc>=1000000000000L && pnc<=9999999999999L) return true;
        else return false;
    }

    private static boolean isValidICN(int ICN)
    {
        //icn must be 6 digits long
        if (ICN>100000 && ICN<999999) return true;
        else return  false;
    }

    private static boolean isValidDate(String dateOfBirth) {
        //DOB should be early than today
        GregorianCalendar date=dateParser(dateOfBirth);
        GregorianCalendar today=new GregorianCalendar();
        if(date.before(today)) return true;
        else return false;
    }

    private static boolean isValidCheckedIn(int checkedIn)
    {
        //should be a BOOL
        if (checkedIn==0 || checkedIn==1) return true;
        else return false;
    }

    public static GregorianCalendar dateParser(String date)
    {
        SimpleDateFormat parserSDF=new SimpleDateFormat("dd-MM-yyyy");
        GregorianCalendar parsedDate=new GregorianCalendar();
        parsedDate.setTime(parserSDF.parse(date, new ParsePosition(0)));
        return parsedDate;
    }
}
