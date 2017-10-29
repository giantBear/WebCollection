package server;

/**
 * @author Yunong
 * @version 1.0
 * @time 04/05/2017
 */
public class UserValidator {

    public UserValidator(){}

    public static String validateUserData(int id, String name, String username, String password, String type)
    {
        if (UserMapper.login(username,password).getID()!=-1) return "User already exists";
        if(!isValidID(id)) return "ID must be greater than 0";
        return "Valid";
    }

    public static boolean isValidID(int id)
    {
        if (id>0) return true;
        else return false;
    }
}
