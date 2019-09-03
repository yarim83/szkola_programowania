package pl.coderslab.dao;

import com.sun.source.tree.WhileLoopTree;
import pl.coderslab.model.Solution;
import pl.coderslab.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;

public class SolutionDao {

    private static final String CREATE_QUERY = "INSERT INTO solution (created, updated, description, " +
            "exercise_id, users_id) VALUES (? ,?, ?, ?, ?, ?)";
    private static final String READ_QUERY = "SELECT * FROM solution WHERE id = ?";
    private static final String UPDATE_QUERY = "UPDATE solution SET created = ?, updated = ?, description = ?, " +
            "exercise_id = ?, users_id = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM solution WHERE id = ?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM solution";

    public Solution create(Solution solution) {
        try (Connection conn = DBUtil.createConnection()) {
            PreparedStatement statement = conn.prepareStatement(CREATE_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setTimestamp(1, solution.getCreated());
            statement.setTimestamp(2, solution.getUpdated());
            statement.setString(3, solution.getDescription());
            statement.setInt(4, solution.getExercise_id());
            statement.setInt(5, solution.getUsers_id());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                solution.setId(rs.getInt("id"));
            }
            return solution;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public Solution read(int id) {
        try (Connection conn = DBUtil.createConnection()) {
            PreparedStatement statement = conn.prepareStatement(READ_QUERY);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Solution solution = new Solution();
                solution.setId(rs.getInt("id"));
                solution.setCreated(rs.getTimestamp("created"));
                solution.setUpdated(rs.getTimestamp("updated"));
                solution.setExercise_id(rs.getInt("exercise_id"));
                solution.setUsers_id(rs.getInt("users_id"));
                return solution;
            }
            return null;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void update(Solution solution) {
        try (Connection conn = DBUtil.createConnection()) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_QUERY);
            statement.setTimestamp(1, solution.getCreated());
            statement.setTimestamp(2, solution.getUpdated());
            statement.setString(3, solution.getDescription());
            statement.setInt(4, solution.getExercise_id());
            statement.setInt(5, solution.getUsers_id());
            statement.setInt(6, solution.getId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void delete(int id) {
        try (Connection conn = DBUtil.createConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_QUERY);
            statement.setInt(1, id);
            statement.executeQuery();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Solution[] findAll() {
        try (Connection conn = DBUtil.createConnection()) {
            Solution[] solutions = new Solution[0];
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_QUERY);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Solution solution = new Solution();
                solution.setId(rs.getInt("id"));
                solution.setCreated(rs.getTimestamp("created"));
                solution.setUpdated(rs.getTimestamp("updated"));
                solution.setDescription(rs.getString("description"));
                solution.setExercise_id(rs.getInt("exercise_id"));
                solution.setUsers_id(rs.getInt("users_id"));
                solutions = addArray(solution, solutions);
            }
            return solutions;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public Solution[] addArray(Solution solution, Solution[] solutions) {
        Solution[] tmp = Arrays.copyOf(solutions, solutions.length + 1);
        tmp[solutions.length] = solution;
        return tmp;
    }
}
