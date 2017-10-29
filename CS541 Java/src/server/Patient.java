package server;

/**
 * @author Yunong
 * @version 1.0
 * @time 03/05/2017
 */

import java.util.GregorianCalendar;

public class Patient {

    private long PNC;
    private int ICN;
    private String name;
    private String address;
    private GregorianCalendar dateOfBirth;
    private int checkedIn;

    public Patient()
    {
        setPNC(-1);
    }

    public Patient(long PNC, int ICN, String name, String address, GregorianCalendar dateOfBirth, int checkedIn) {
        this.PNC = PNC;
        this.ICN = ICN;
        this.name = name;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.checkedIn=checkedIn;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public GregorianCalendar getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(GregorianCalendar dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getICN() {
        return ICN;
    }

    public void setICN(int ICN) {
        this.ICN = ICN;
    }

    public long getPNC() {
        return PNC;
    }

    public void setPNC(long PNC) {
        this.PNC = PNC;
    }

    public int getCheckedIn() {
        return checkedIn;
    }

    public void setCheckedIn(int checkedIn) {
        this.checkedIn = checkedIn;
    }

}
