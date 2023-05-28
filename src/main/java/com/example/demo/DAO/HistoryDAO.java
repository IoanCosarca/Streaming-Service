package com.example.demo.DAO;

import com.example.demo.Connection.ConnectionFactory;
import com.example.demo.Model.History;
import org.springframework.stereotype.Repository;

import java.sql.*;
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
        String query = "SELECT * FROM history ORDER BY date DESC, time DESC";
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
        String query = "SELECT * FROM history WHERE userID = ? ORDER BY date DESC, time DESC";
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
     * Given a result set entry from a query, constructs a History object with the fields and returns it.
     * @param rs result set containing the fields from the table
     * @return History
     * @throws SQLException the SQL exception will be handled where the method is called
     */
    private History constructHistory(ResultSet rs) throws SQLException
    {
        History history = new History();
        history.setUserID(rs.getLong("userID"));
        history.setVideoID(rs.getLong("videoID"));
        history.setDate(String.valueOf(rs.getDate("date")));
        history.setTime(String.valueOf(rs.getTime("time")));
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
        String query = "INSERT INTO history (userID, videoID, date, time) VALUES (?, ?, ?, ?)";
        try
        {
            statement = dbConnection.prepareStatement(query);
            statement.setLong(1, history.getUserID());
            statement.setLong(2, history.getVideoID());
            statement.setDate(3, Date.valueOf(history.getDate()));
            statement.setTime(4, Time.valueOf(history.getTime()));
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
     * @param userID delete criteria
     */
    @Override
    public void delete(Long userID)
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
    public void update(History history) {}

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
    public List<History> findByName(String name) {
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
