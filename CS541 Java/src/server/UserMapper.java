package server;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Yunong
 * @version 1.0
 * @time 03/05/2017
 */
public class UserMapper {

    public static User login(String username, String password) {

        User user=new User();
       try {
            Connection connection=DBConnection.getConnection();
            CallableStatement callableStatement;
            callableStatement=connection.prepareCall("{call login(?,?)}");
            callableStatement.setString(1,username);
            callableStatement.setString(2, password);
            ResultSet resultSet=callableStatement.executeQuery();
            if(resultSet.next())
            user=new User(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5));
            callableStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public static String  addUser(User user) {

        try {
            Connection connection=DBConnection.getConnection();
            CallableStatement callableStatement = connection.prepareCall("{call addUser(?,?,?,?,?)}");
            callableStatement.setInt(1,user.getID());
            callableStatement.setString(2, user.getName());
            callableStatement.setString(3, user.getUsername());
            callableStatement.setString(4, user.getPassword());
            callableStatement.setString(5, user.getType());
            callableStatement.executeQuery();
            callableStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return "Success";
    }

    public static String  updateUser(int oldID,User user) {
        try {
            Connection connection=DBConnection.getConnection();
            CallableStatement callableStatement = connection.prepareCall("{call updateUser(?,?,?,?,?,?)}");
            callableStatement.setInt(1,oldID);
            callableStatement.setInt(2,user.getID());
            callableStatement.setString(3, user.getName());
            callableStatement.setString(4, user.getUsername());
            callableStatement.setString(5, user.getPassword());
            callableStatement.setString(6, user.getType());
            callableStatement.executeQuery();
            callableStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return "Success";
    }

    public static ArrayList<User> getUsers() {
        ArrayList<User> userList=new ArrayList<User>();
        try {
            Connection connection=DBConnection.getConnection();
            CallableStatement callableStatement = connection.prepareCall("{call getUsers}");
            ResultSet resultSet=callableStatement.executeQuery();
            while (resultSet.next())
            {
                User user = new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5));
                userList.add(user);
            }
            callableStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public static User getUser(int id) {
        User user=new User();
        try {
            Connection connection=DBConnection.getConnection();
            CallableStatement callableStatement = connection.prepareCall("{call getUser(?)}");
            callableStatement.setInt(1,id);
            ResultSet resultSet=callableStatement.executeQuery();
             if(resultSet.next()) {
                 user = new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5));
             }
            callableStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public static String deleteUser(int id) {
        try {
            Connection connection = DBConnection.getConnection();
            CallableStatement callableStatement = connection.prepareCall("{call deleteUser(?)}");
            callableStatement.setInt(1, id);
            callableStatement.executeQuery();
            callableStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return "Success";
    }

}
