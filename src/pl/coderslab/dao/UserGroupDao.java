package pl.coderslab.dao;

import pl.coderslab.model.User;
import pl.coderslab.model.UserGroup;
import pl.coderslab.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserGroupDao {

    private static final String CREATE_QUERY = "INSERT INTO user_group(name) VALUES (?)";
    private static final String READ_QUERY = "SELECT * FROM user_group WHERE id = ?";

    public UserGroup create(UserGroup userGroup) {
        try (Connection conn = DBUtil.createConnection()) {
            PreparedStatement statement = conn.prepareStatement(CREATE_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, userGroup.getName());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                userGroup.setId(rs.getInt(1));
            }
            return userGroup;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public UserGroup read(int id) {
        try (Connection conn = DBUtil.createConnection()) {
            PreparedStatement statement = conn.prepareStatement(READ_QUERY);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                UserGroup userGroup = new UserGroup();
                userGroup.setId(rs.getInt("id"));
                userGroup.setName(rs.getString("name"));
                return userGroup;
            }
            return null;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
