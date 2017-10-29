package server;

/**
 * @author Yunong
 * @version 1.0
 * @time 01/05/2017
 */

import javax.jws.WebService;

//Service Implementation Bean

@WebService(endpointInterface = "server.UserManagerInterface")
public class UserManager implements UserManagerInterface {

    @Override
    public User login(String username, String password) {
       return UserMapper.login(username,password);
    }

    @Override
    public String addUser(int id, String name, String username, String password, String type) {
        String status=UserValidator.validateUserData(id,name,username,password,type);
        if (status.equals("Valid")) {
            User user=new User(id,name,username,password,type);
            return UserMapper.addUser(user);
        }
        else return status;
    }

    @Override
    public String updateUser(int oldId, int id, String name, String username, String password, String type) {

        if (UserValidator.isValidID(oldId))
        {
            String status=UserValidator.validateUserData(id,name,username,password,type);
            if (status.equals("User already exists") || status.equals("Valid")) {
                 User user=new User(id,name,username,password,type);
                 return UserMapper.updateUser(oldId,user);
            }
            else return status;
        }
        else return "Invalid ID";
    }

    @Override
    public Object[] getUsers() {
        return UserMapper.getUsers().toArray();
    }

    @Override
    public User getUser(int id) {
        return UserMapper.getUser(id);
    }

   @Override
    public String deleteUser(int id) {
        return UserMapper.deleteUser(id);
    }
}