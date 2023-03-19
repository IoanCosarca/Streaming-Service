package com.example.demo.DAO;

import com.example.demo.Connection.ConnectionFactory;
import com.example.demo.Model.History;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HistoryDAO implements DAO<History> {
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

    private History constructHistory(ResultSet rs) throws SQLException
    {
        History history = new History();
        history.setId(rs.getLong("id"));
        history.setUserID(rs.getLong("userID"));
        history.setVideoID(rs.getLong("videoID"));
        return history;
    }

    @Override
    public void save(History history)
    {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        String query = "INSERT INTO history (userID, videoID) VALUES (?, ?)";
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
}
