package data;

import entities.data.User;
import utilities.ConnectionUtil;
import utilities.Global;
import utilities.LogLevel;
import utilities.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDAO{
    @Override
    public List<User> getUsers() {

        try (Connection conn = ConnectionUtil.getInstance().getConnection()) {
            String sql = "select * from foundation_project.users";
            PreparedStatement ps = conn.prepareStatement(sql);

            if (Global.debug) {
                Logger.Log(LogLevel.DEBUG,"sql: \n\t" + String.valueOf(ps) + "\n");
            }

            ResultSet rs = ps.executeQuery();
            List<User> userList = new ArrayList<>();

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("user_id"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                userList.add(user);
            }

            System.out.println(userList.get(0));

            return userList;

        } catch (SQLException e) {
            e.printStackTrace();
            String method = "UserDaoImpl.getUsers()";
            Logger.Log(LogLevel.ERROR, e.getMessage(), method);
            return null;
        }
    }

    @Override
    public User createUser(User user) {

        try (Connection conn = ConnectionUtil.getInstance().getConnection()) {
            String sql = "insert into users (first_name,last_name,username,\"password\",email)\n" +
                    "values (?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getFirstName());
            ps.setString(2,user.getLastName());
            ps.setString(3, user.getUsername());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getEmail());

            if (Global.debug) {
                Logger.Log(LogLevel.DEBUG,"sql: \n\t" + String.valueOf(ps) + "\n");
            }

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                user.setId(rs.getInt("user_id"));
                return user;
            } else {
                String message = "Failed to create user";
                String method = "UserDaoImpl.createUser(User " + user + ")";
                Logger.Log(LogLevel.WARNING, message, method);
                return null;
            }

        } catch (SQLException e) {
            //e.printStackTrace();
            String method = "UserDaoImpl.createUser(User " + user + ")";
            Logger.Log(LogLevel.ERROR, e.getMessage(), method);
            return null;
        }
    }

    @Override
    public User getUserById(int userId) {

        try (Connection conn =  ConnectionUtil.getInstance().getConnection()) {
            String sql = "select *\n" +
                    "from users \n" +
                    "where user_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,userId);

            if (Global.debug) {
                Logger.Log(LogLevel.DEBUG,"sql: \n\t" + String.valueOf(ps) + "\n");
            }

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("user_id"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                return user;
            } else {
                String message = "No user found";
                String method = "UserDaoImpl.getUserById(int " + userId + " )";
                Logger.Log(LogLevel.INFO, message, method);
                return null;
            }

        } catch (SQLException e) {
            //e.printStackTrace();
            String method = "UserDaoImpl.getUserById(int " + userId + " )";
            Logger.Log(LogLevel.ERROR, e.getMessage(), method);
            return null;
        }

    }

    @Override
    public User getUserByUsername(String username) {

        try (Connection conn = ConnectionUtil.getInstance().getConnection()) {
            String sql = "select *\n" +
                    "from users \n" +
                    "where username = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,username);

            if (Global.debug) {
                Logger.Log(LogLevel.DEBUG,"sql: \n\t" + String.valueOf(ps) + "\n");
            }

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("user_id"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                return user;
            } else {
                String message = "No user found";
                String method = "UserDaoImpl.getUserByUsername(String " + username + " )";
                Logger.Log(LogLevel.INFO, message, method);
                return null;
            }

        } catch (SQLException e) {
            //e.printStackTrace();
            String method = "UserDaoImpl.getUserByUsername(String " + username + " )";
            Logger.Log(LogLevel.ERROR, e.getMessage(), method);
            return null;
        }

    }

    @Override
    public boolean updateUser(User user) {

        try (Connection conn = ConnectionUtil.getInstance().getConnection()) {
            String sql = "update users \n" +
                    "set first_name = ?,\n" +
                    "last_name = ?,\n" +
                    "username = ?,\n" +
                    "\"password\" = ?,\n" +
                    "email = ?\n" +
                    "where user_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,user.getFirstName());
            ps.setString(2,user.getLastName());
            ps.setString(3, user.getUsername());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getEmail());
            ps.setInt(6,user.getId());

            if (Global.debug) {
                Logger.Log(LogLevel.DEBUG,"sql: \n\t" + String.valueOf(ps) + "\n");
            }

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {
            //e.printStackTrace();
            String method = "UserDaoImpl.updateUser(User " + user + " )";
            Logger.Log(LogLevel.ERROR,e.getMessage(),method);
            return false;
        }

    }

    @Override
    public Boolean deletedUser(int userId) {

        try (Connection conn = ConnectionUtil.getInstance().getConnection()) {
            String sql = "delete from users \n" +
                    "where user_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);

            if (Global.debug) {
                Logger.Log(LogLevel.DEBUG,"sql: \n\t" + String.valueOf(ps) + "\n");
            }

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {
            //e.printStackTrace();
            String method = "UserDaoImpl.deletedUser(int " + userId + " )";
            Logger.Log(LogLevel.ERROR,e.getMessage(),method);
            return null;
        }

    }
}
