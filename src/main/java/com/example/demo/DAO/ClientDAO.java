package com.example.demo.DAO;

import com.example.demo.Connection.ConnectionFactory;
import com.example.demo.Model.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO implements DAO<Client> {
    @Override
    public List<Client> getAll()
    {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;
        List<Client> list = new ArrayList<>();
        String query = "SELECT * FROM client";
        try
        {
            statement = dbConnection.prepareStatement(query);
            rs = statement.executeQuery();
            while (rs.next()) {
                list.add(constructClient(rs));
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

    public Client findByID(Long id)
    {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;
        String query = "SELECT * FROM client WHERE id = ?";
        try
        {
            statement = dbConnection.prepareStatement(query);
            statement.setLong(1, id);
            rs = statement.executeQuery();
            return constructClient(rs);
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
    }

    public Client findByEmail(String email)
    {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;
        String query = "SELECT * FROM client WHERE email = ?";
        try
        {
            statement = dbConnection.prepareStatement(query);
            statement.setString(1, email);
            rs = statement.executeQuery();
            return constructClient(rs);
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
    }

    public List<Client> findAllByAge(int age)
    {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;
        List<Client> list = new ArrayList<>();
        String query = "SELECT * FROM client WHERE age = ?";
        try
        {
            statement = dbConnection.prepareStatement(query);
            statement.setInt(1, age);
            rs = statement.executeQuery();
            while (rs.next()) {
                list.add(constructClient(rs));
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

    public List<Client> findAllByCountry(String country)
    {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;
        List<Client> list = new ArrayList<>();
        String query = "SELECT * FROM client WHERE country = ?";
        try
        {
            statement = dbConnection.prepareStatement(query);
            statement.setString(1, country);
            rs = statement.executeQuery();
            while (rs.next()) {
                list.add(constructClient(rs));
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

    private Client constructClient(ResultSet rs) throws SQLException
    {
        Client client = new Client();
        client.setId(rs.getLong("id"));
        client.setUserID(rs.getLong("userID"));
        client.setAge(rs.getInt("age"));
        client.setCountry(rs.getString("country"));
        return client;
    }

    @Override
    public void save(Client client)
    {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        String query1 = "INSERT INTO client (userID, age, country) VALUES (?, ?, ?)";
        String query2 = "INSERT INTO user (type, firstName, lastName, email, password) VALUES (?, ?, ?, ?, ?)";
        try
        {
            // Insert in the Client Table
            statement = dbConnection.prepareStatement(query1);
            statement.setLong(1, client.getUserID());
            statement.setInt(2, client.getAge());
            statement.setString(3, client.getCountry());
            statement.executeUpdate();
            // Insert in the User Table
            statement = dbConnection.prepareStatement(query2);
            statement.setString(1, client.getType());
            statement.setString(2, client.getFirstName());
            statement.setString(3, client.getLastName());
            statement.setString(4, client.getEmail());
            statement.setString(5, client.getPassword());
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
    public void update(Client client)
    {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        String query1 = "UPDATE client SET age = ?, country = ? WHERE id = " + client.getId();
        String query2 = "UPDATE user SET type = ?, firstName = ?, lastName = ?, email = ?, password = ?";
        query2 += " WHERE userID = " + client.getUserID();
        try
        {
            // Update in the Client Table
            statement = dbConnection.prepareStatement(query1);
            statement.setInt(1, client.getAge());
            statement.setString(2, client.getCountry());
            statement.executeUpdate();
            // Update in the User Table
            statement = dbConnection.prepareStatement(query2);
            statement.setString(1, client.getType());
            statement.setString(2, client.getFirstName());
            statement.setString(3, client.getLastName());
            statement.setString(4, client.getEmail());
            statement.setString(5, client.getPassword());
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
        String query1 = "DELETE FROM client WHERE id = ?";
        String query2 = "DELETE FROM user WHERE userID = ?";
        try
        {
            Client client = findByID(id);
            // Delete in the Client Table
            statement = dbConnection.prepareStatement(query1);
            statement.setLong(1, id);
            statement.execute();
            // Delete in the User Table
            statement = dbConnection.prepareStatement(query2);
            statement.setLong(1, client.getUserID());
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
