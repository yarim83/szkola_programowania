package pl.coderslab.dao;

import pl.coderslab.model.Exercise;
import pl.coderslab.util.DBUtil;

import javax.security.auth.login.CredentialException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class ExerciseDao {

    private static final String CREATE_QUERY = "INSERT INTO exercise (title, description) VALUES (? ,?)";
    private static final String READ_QUERY = "SELECT * FROM exercise WHERE id = ?";
    private static final String UPDATE_QUERY = "UPDATE exercise SET title = ?, description = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM exercise WHERE id = ?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM exercise";

    public Exercise create(Exercise exercise) {
        try (Connection conn = DBUtil.createConnection()) {
            PreparedStatement statement = conn.prepareStatement(CREATE_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, exercise.getTitle());
            statement.setString(2, exercise.getDescription());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                exercise.setId(rs.getInt("id"));
            }
            return exercise;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }

    }

    public Exercise read(int id) {
        try (Connection conn = DBUtil.createConnection()) {
            PreparedStatement statement = conn.prepareStatement(READ_QUERY);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Exercise exercise = new Exercise();
                exercise.setId(rs.getInt("id"));
                exercise.setTitle(rs.getString("title"));
                exercise.setDescription(rs.getString("description"));
                return exercise;
            }
            return null;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }

    }

    public void update(Exercise exercise) {
        try (Connection conn = DBUtil.createConnection()) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_QUERY);
            statement.setString(1, exercise.getTitle());
            statement.setString(2, exercise.getDescription());
            statement.setInt(3, exercise.getId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void delete(int id) {
        try (Connection conn = DBUtil.createConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_QUERY);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Exercise[] findAll() {
        try (Connection conn = DBUtil.createConnection()) {
            Exercise[] exercises = new Exercise[0];
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_QUERY);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Exercise exercise = new Exercise();
                exercise.setId(rs.getInt("id"));
                exercise.setTitle(rs.getString("title"));
                exercise.setDescription(rs.getString("description"));
                exercises = addArray(exercise, exercises);
            }
            return exercises;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }

    }

    public Exercise[] addArray(Exercise exercise, Exercise[] exercises){
        Exercise[] tmp = Arrays.copyOf(exercises, exercises.length+1);
        tmp[exercises.length] = exercise;
        return tmp;
    }
}
