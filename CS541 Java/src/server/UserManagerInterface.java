package server;

/**
 * Created by Paul on 01/05/2015.
 */

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

//Service Endpoint Interface
@WebService
@SOAPBinding(style = Style.RPC)
public interface  UserManagerInterface {

    @WebMethod
    public User login(String username, String password);

    @WebMethod
    public String addUser(int id, String name, String username, String password, String type);

    @WebMethod
    public String updateUser(int oldId, int id, String name, String username, String password, String type);

    @WebMethod
    public Object[] getUsers();

    @WebMethod
    public User getUser(int id);

   @WebMethod
    public String deleteUser(int id);
}
