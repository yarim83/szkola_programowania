package pl.coderslab.dao;

import pl.coderslab.model.User;
import pl.coderslab.util.DBUtil;

import java.net.UnknownServiceException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserDao {

    private static final String CREATE_QUERY = "INSERT INTO users(username, email, password, user_group_id) VALUES (?,?,?,?)";
    private static final String READ_BY_ID_QUERY = "SELECT * FROM users WHERE id = ?";
    private static final String UPDATE_QUERY = "UPDATE users SET username = ?, email = ?, password = ?, user_group_id = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM users WHERE id = ?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM users";
    private static final String FIND_ALL_BY_GROUP_ID = "SELECT * FROM users WHERE user_group_id = ?";

    /**
     * This method is DAO method to create User and write into DB.
     *
     * @param user This is the User object to create.
     * @return User This returns user object from database.
     */
    public User create(User user) {
        try (Connection conn = DBUtil.createConnection()) {
            // PreparedStatement.RETURN_GENERATED_KEYS - flaga ktora mowi ze jak sie uda wywowal query to zwóci ID utworzone
            PreparedStatement statement = conn.prepareStatement(CREATE_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setInt(4, user.getGoupId());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                user.setId(rs.getInt(1));
            }
            return user;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * This method is DAO method to read User from DB.
     *
     * @param id This is the user id to read.
     * @return User This returns user object from database.
     */
    public User read(int id) {
        try (Connection conn = DBUtil.createConnection()) {
            PreparedStatement statement = conn.prepareStatement(READ_BY_ID_QUERY);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUserName(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setGoupId(rs.getInt("user_group_id"));
                return user;
            }
            return null;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    /**
     * This method is DAO method to update User and write into DB.
     *
     * @param user This is the User object to update.
     * @return Nothing.
     */
    public void update(User user) {
        try (Connection conn = DBUtil.createConnection()) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_QUERY);
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setInt(4, user.getGoupId());
            statement.setInt(5, user.getId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
    /**
     * This method is DAO method to delete User from DB.
     *
     * @param id This is the User id to delete.
     * @return Nothing.
     */
    public void delete(int id) {
        try (Connection conn = DBUtil.createConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_QUERY);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    /**
     * This method is DAO method to find all User obcects in DB.
     *
     * @return List<User> list of User obcects.
     */
    public List<User> findAll() {
        try (Connection conn = DBUtil.createConnection()) {
            List<User> users = new ArrayList<>();
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_QUERY);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUserName(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setGoupId(rs.getInt("user_group_id"));
                users.add(user);
            }
            return users;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    /**
     * This method is DAO method to find all User by Exercise Id in DB.
     *
     * @return List<User> list of User objects.
     */
    public List<User> findAllByExerciseId(int id) {
        try (Connection conn = DBUtil.createConnection()) {
            List<User> userList = new ArrayList<>();
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_BY_GROUP_ID);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUserName(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setGoupId(rs.getInt("user_group_id"));
                userList.add(user);
            }
            return userList;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
