package com.example.demo.DAO;

import com.example.demo.Connection.ConnectionFactory;
import com.example.demo.Model.History;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Defines all the methods and queries for accessing the History table in the database.
 */
@Repository
public class HistoryDAO implements DAO<History> {
    /**
     * Gets the connection, calls a query to get all the entries in the database and returns them as list.
     * @return List[History]
     */
    @Override
    public List<History> getAll()
    {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;
        List<History> list = new ArrayList<>();
        String query = "SELECT * FROM history";
        try
        {
            statement = dbConnection.prepareStatement(query);
            rs = statement.executeQuery();
            while (rs.next()) {
                list.add(constructHistory(rs));
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally
        {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(dbConnection);
        }
        return list;
    }

    /**
     * Gets the connection, calls a query to get all the entries in the database where the userID is the specified one and returns them as list.
     * @param userID selection criteria
     * @return List[History]
     */
    public List<History> findAllByUserID(Long userID)
    {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;
        List<History> list = new ArrayList<>();
        String query = "SELECT * FROM history WHERE userID = ?";
        try
        {
            statement = dbConnection.prepareStatement(query);
            statement.setLong(1, userID);
            rs = statement.executeQuery();
            while (rs.next()) {
                list.add(constructHistory(rs));
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally
        {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(dbConnection);
        }
        return list;
    }

    /**
     * Gets the connection, calls a query to get all the entries in the database where the videoID is the specified one and returns them as list.
     * @param videoID selection criteria
     * @return List[History]
     */
    public List<History> findAllByVideoID(Long videoID)
    {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;
        List<History> list = new ArrayList<>();
        String query = "SELECT * FROM history WHERE videoID = ?";
        try
        {
            statement = dbConnection.prepareStatement(query);
            statement.setLong(1, videoID);
            rs = statement.executeQuery();
            while (rs.next()) {
                list.add(constructHistory(rs));
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally
        {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(dbConnection);
        }
        return list;
    }

    /**
     * Give a result set entry from a query, constructs a History object with the fields and returns it.
     * @param rs result set containing the fields from the table
     * @return History
     * @throws SQLException the SQL exception will be handled where the method is called
     */
    private History constructHistory(ResultSet rs) throws SQLException
    {
        History history = new History();
        history.setId(rs.getLong("id"));
        history.setUserID(rs.getLong("userID"));
        history.setVideoID(rs.getLong("videoID"));
        return history;
    }

    /**
     * Gets the connection and calls a query to insert the received History object into the database.
     * @param history the new object entry
     */
    @Override
    public void save(History history)
    {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        String query = "INSERT INTO history (id, userID, videoID) VALUES (?, ?, ?)";
        try
        {
            statement = dbConnection.prepareStatement(query);
            statement.setLong(1, history.getId());
            statement.setLong(2, history.getUserID());
            statement.setLong(3, history.getVideoID());
            statement.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally
        {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(dbConnection);
        }
    }

    /**
     * Gets the connection and calls a query to update the database with the History object.
     * @param history object containing the new information for the History entry with the same id
     */
    @Override
    public void update(History history)
    {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        String query = "UPDATE history SET ";
        query += "userID = ?, videoID = ? WHERE id = " + history.getId();
        try
        {
            statement = dbConnection.prepareStatement(query);
            statement.setLong(1, history.getUserID());
            statement.setLong(2, history.getVideoID());
            statement.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally
        {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(dbConnection);
        }
    }

    /**
     * Gets the connection and calls a query to delete the entry with the given id from the database.
     * @param id delete criteria
     */
    @Override
    public void delete(Long id)
    {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        String query = "DELETE FROM history WHERE id = ?";
        try
        {
            statement = dbConnection.prepareStatement(query);
            statement.setLong(1, id);
            statement.execute();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally
        {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(dbConnection);
        }
    }

    /**
     * Gets the connection and calls a query to delete the watch history of the user with the specified id from the database.
     * @param userID delete criteria
     */
    public void deleteUserHistory(Long userID)
    {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        String query = "DELETE FROM history WHERE userID = ?";
        try
        {
            statement = dbConnection.prepareStatement(query);
            statement.setLong(1, userID);
            statement.execute();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally
        {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(dbConnection);
        }
    }

    @Override
    public History findByID(Long id) {
        return null;
    }

    @Override
    public History findByEmail(String email) {
        return null;
    }

    @Override
    public List<History> findAllByAge(int age) {
        return null;
    }

    @Override
    public List<History> findAllByCountry(String country) {
        return null;
    }

    @Override
    public History findByName(String name) {
        return null;
    }

    @Override
    public List<History> findAllByChannel(String channel) {
        return null;
    }

    @Override
    public List<History> findAllByGenre(String genre) {
        return null;
    }

    @Override
    public List<History> findAllByStartHour(int startHour) {
        return null;
    }
}
