package server;

/**
 * @author Yunong
 * @version 1.0
 * @time 03/05/2017
 */
public class User {

    private int ID;
    private String name, username,password,type;

    public User() {
        ID=-1;
    }

    public User(int ID, String name, String username, String password, String type) {
        this.ID = ID;
        this.name = name;
        this.username = username;
        this.password = password;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
