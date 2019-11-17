package pl.coderslab.dao;

import pl.coderslab.model.Exercise;
import pl.coderslab.util.DBUtil;

import javax.security.auth.login.CredentialException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExerciseDao {

    private static final String CREATE_QUERY = "INSERT INTO exercise (title, description) VALUES (? ,?)";
    private static final String READ_QUERY = "SELECT * FROM exercise WHERE id = ?";
    private static final String UPDATE_QUERY = "UPDATE exercise SET title = ?, description = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM exercise WHERE id = ?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM exercise";
    private static final String FIND_ALL_EXERCISE_WHERE_NO_SOLUTION = "SELECT e.id, e.title, e.description FROM exercise e LEFT JOIN solution sol ON e.id = sol.exercise_id AND sol.users_id = ? WHERE sol.id IS NULL";

    public Exercise create(Exercise exercise) {
        try (Connection conn = DBUtil.createConnection()) {
            PreparedStatement statement = conn.prepareStatement(CREATE_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, exercise.getTitle());
            statement.setString(2, exercise.getDescription());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                exercise.setId(rs.getInt(1));
            }
            rs.close();
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

    public List<Exercise> findAll() {
        try (Connection conn = DBUtil.createConnection();
             PreparedStatement statement = conn.prepareStatement(FIND_ALL_QUERY)) {
            List<Exercise> exerciseList = new ArrayList<>();
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Exercise exercise = new Exercise();
                exercise.setId(rs.getInt("id"));
                exercise.setTitle(rs.getString("title"));
                exercise.setDescription(rs.getString("description"));
                exerciseList.add(exercise);
            }
            rs.close();
            return exerciseList;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public List<Exercise> findAllExerciseWhereNoSolution(int userId) {
        try (Connection conn = DBUtil.createConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(FIND_ALL_EXERCISE_WHERE_NO_SOLUTION)) {
            List<Exercise> exerciseList = new ArrayList<>();
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Exercise exercise = new Exercise();
                exercise.setId(resultSet.getInt("id"));
                exercise.setTitle(resultSet.getString("title"));
                exercise.setDescription(resultSet.getString("description"));
                exerciseList.add(exercise);
            }
            resultSet.close();
            return exerciseList;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public Exercise[] addArray(Exercise exercise, Exercise[] exercises) {
        Exercise[] tmp = Arrays.copyOf(exercises, exercises.length + 1);
        tmp[exercises.length] = exercise;
        return tmp;
    }
}
