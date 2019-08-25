package pl.coderslab.dao;

import com.mysql.cj.x.protobuf.MysqlxPrepare;
import pl.coderslab.model.User;
import pl.coderslab.util.DBUtil;

import java.net.UnknownServiceException;
import java.sql.*;
import java.util.Arrays;

public class UserDao {

    private static final String CREATE_QUERY = "INSERT INTO user(username, email, password, group_id) VALUES (?,?,?,?)";
    private static final String READ_BY_ID_QUERY = "SELECT * FROM users WHERE id = ?";
    private static final String UPDATE_QUERY = "UPDATE user SET name = ?, email = ?, password = ?, group_id = ? WHERE id = ?";
    private static final String DELETE_QUERY =  "DELEFE FROM user WHERE id = ?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM users";

    public User create(User user){
        try(Connection conn = DBUtil.createConnection()){
            // PreparedStatement.RETURN_GENERATED_KEYS - flaga ktora mowi ze jak sie uda wywowal query to zwóci ID utworzone
            PreparedStatement statement = conn.prepareStatement(CREATE_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getUserName());
            statement.setString(2,user.getEmail());
            statement.setString(3,user.getPassword());
            statement.setInt(4,user.getGoupId());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if(rs.next()){
                user.setId(rs.getInt(1));
            }
            return user;
        } catch (SQLException ex){
            ex.printStackTrace();
            return null;
        }
    }

    public User read(int id){
        try(Connection conn = DBUtil.createConnection()){
            PreparedStatement statement = conn.prepareStatement(READ_BY_ID_QUERY);
            statement.setInt(1,id);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUserName(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setGoupId(rs.getInt("group_id"));
                return user;
            }
            return null;
        } catch (SQLException ex){
            ex.printStackTrace();
            return null;
        }
    }

    public void update(User user){
        try(Connection conn = DBUtil.createConnection()){
            PreparedStatement statement = conn.prepareStatement(UPDATE_QUERY);
            statement.setString(1, user.getUserName());
            statement.setString(2,user.getEmail());
            statement.setString(3,user.getPassword());
            statement.setInt(4,user.getGoupId());
            statement.setInt(5, user.getId());
            statement.executeUpdate();
        } catch (SQLException ex){
            ex.printStackTrace();
        }

    }

    public void delete(int id){
        try(Connection conn = DBUtil.createConnection()){
            PreparedStatement statement = conn.prepareStatement(DELETE_QUERY);
            statement.setInt(1,id);
            statement.executeUpdate();
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public User[] findAll() {
        try (Connection conn = DBUtil.createConnection()) {
            User[] users = new User[0];
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_QUERY);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUserName(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setGoupId(rs.getInt("group_id"));
                users = addToArray(user, users);
            }
            return users;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private User[] addToArray (User user, User[] users){
        User[] tmp = Arrays.copyOf(users, users.length + 1);
        tmp[users.length] = user;
        return tmp;
    }


}
