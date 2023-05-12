package com.example.demo.DAO;

import com.example.demo.Connection.ConnectionFactory;
import com.example.demo.Model.Client;
import com.example.demo.Model.UserType;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Defines all the methods and queries for accessing the Client table in the database.
 */
@Repository
public class ClientDAO implements DAO<Client> {
    /**
     * Gets the connection, calls a query to get all the entries in the database and returns them as list.
     * @return List[Client]
     */
    @Override
    public List<Client> getAll()
    {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        ResultSet rsC = null;
        ResultSet rsU = null;
        List<Client> list = new ArrayList<>();
        String query1 = "SELECT * FROM client";
        String query2 = "SELECT * FROM user WHERE userID = ?";
        try
        {
            statement = dbConnection.prepareStatement(query1);
            rsC = statement.executeQuery();
            while (rsC.next())
            {
                statement = dbConnection.prepareStatement(query2);
                statement.setLong(1, rsC.getLong("userID"));
                rsU = statement.executeQuery();
                rsU.next();
                list.add(constructClient(rsC, rsU));
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally
        {
            ConnectionFactory.close(rsC);
            ConnectionFactory.close(rsU);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(dbConnection);
        }
        return list;
    }

    /**
     * Gets the connection, calls a query to get the object from the database with the given id and returns it.
     * @param id selection criteria
     * @return Client
     */
    public Client findByID(Long id)
    {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        ResultSet rsC = null;
        ResultSet rsU = null;
        String query1 = "SELECT * FROM client WHERE id = ?";
        String query2 = "SELECT * FROM user WHERE userID = ?";
        try
        {
            statement = dbConnection.prepareStatement(query1);
            statement.setLong(1, id);
            rsC = statement.executeQuery();
            rsC.next();
            statement = dbConnection.prepareStatement(query2);
            statement.setLong(1, rsC.getLong("userID"));
            rsU = statement.executeQuery();
            rsU.next();
            return constructClient(rsC, rsU);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally
        {
            ConnectionFactory.close(rsC);
            ConnectionFactory.close(rsU);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(dbConnection);
        }
    }

    /**
     * Gets the connection, calls a query to get the object from the database with the given email and returns it.
     * @param email selection criteria
     * @return Client
     */
    public Client findByEmail(String email)
    {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        ResultSet rsC = null;
        ResultSet rsU = null;
        String query1 = "SELECT * FROM user WHERE email = ?";
        String query2 = "SELECT * FROM client WHERE userID = ?";
        try
        {
            statement = dbConnection.prepareStatement(query1);
            statement.setString(1, email);
            rsU = statement.executeQuery();
            rsU.next();
            statement = dbConnection.prepareStatement(query2);
            statement.setLong(1, rsU.getLong("userID"));
            rsC = statement.executeQuery();
            rsC.next();
            return constructClient(rsC, rsU);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally
        {
            ConnectionFactory.close(rsC);
            ConnectionFactory.close(rsU);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(dbConnection);
        }
    }

    /**
     * Gets the connection, calls a query to get all the Clients in the database who have a specified age and returns them as list.
     * @param age selection criteria
     * @return List[Client]
     */
    public List<Client> findAllByAge(int age)
    {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        ResultSet rsC = null;
        ResultSet rsU = null;
        List<Client> list = new ArrayList<>();
        String query1 = "SELECT * FROM client WHERE age = ?";
        String query2 = "SELECT * FROM user WHERE userID = ?";
        try
        {
            statement = dbConnection.prepareStatement(query1);
            statement.setInt(1, age);
            rsC = statement.executeQuery();
            while (rsC.next())
            {
                statement = dbConnection.prepareStatement(query2);
                statement.setLong(1, rsC.getLong("userID"));
                rsU = statement.executeQuery();
                rsU.next();
                list.add(constructClient(rsC, rsU));
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally
        {
            ConnectionFactory.close(rsC);
            ConnectionFactory.close(rsU);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(dbConnection);
        }
        return list;
    }

    /**
     * Gets the connection, calls a query to get all the Clients in the database from a specified country and returns them as list.
     * @param country selection criteria
     * @return List[Client]
     */
    public List<Client> findAllByCountry(String country)
    {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        ResultSet rsC = null;
        ResultSet rsU = null;
        List<Client> list = new ArrayList<>();
        String query1 = "SELECT * FROM client WHERE country = ?";
        String query2 = "SELECT * FROM user WHERE userID = ?";
        try
        {
            statement = dbConnection.prepareStatement(query1);
            statement.setString(1, country);
            rsC = statement.executeQuery();
            while (rsC.next())
            {
                statement = dbConnection.prepareStatement(query2);
                statement.setLong(1, rsC.getLong("userID"));
                rsU = statement.executeQuery();
                rsU.next();
                list.add(constructClient(rsC, rsU));
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally
        {
            ConnectionFactory.close(rsC);
            ConnectionFactory.close(rsU);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(dbConnection);
        }
        return list;
    }

    /**
     * Give a result set entry from a query, constructs a Client object with the fields and returns it.
     * @param rsC result set containing the fields from the Client table
     * @param rsU result set containing the fields from the User table
     * @return Client
     * @throws SQLException the SQL exception will be handled where the method is called
     */
    private Client constructClient(ResultSet rsC, ResultSet rsU) throws SQLException
    {
        Client client = new Client();
        client.setUserID(rsC.getLong("userID"));
        client.setType(UserType.valueOf(rsU.getString("type")));
        client.setFirstName(rsU.getString("firstName"));
        client.setLastName(rsU.getString("lastName"));
        client.setEmail(rsU.getString("email"));
        client.setPassword(rsU.getString("password"));
        client.setAge(rsC.getInt("age"));
        client.setCountry(rsC.getString("country"));
        return client;
    }

    /**
     * Gets the connection and calls a query to insert the received Client object into the database.
     * @param client the new Client table entry
     */
    @Override
    public void save(Client client)
    {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        String query1 = "INSERT INTO user (userID, type, firstName, lastName, email, password) VALUES (?, ?, ?, ?, ?, ?)";
        String query2 = "INSERT INTO client (userID, age, country) VALUES (?, ?, ?)";
        try
        {
            // Insert in the User Table
            statement = dbConnection.prepareStatement(query1);
            statement.setLong(1, client.getUserID());
            statement.setString(2, client.getType().toString());
            statement.setString(3, client.getFirstName());
            statement.setString(4, client.getLastName());
            statement.setString(5, client.getEmail());
            statement.setString(6, client.getPassword());
            statement.executeUpdate();
            // Insert in the Client Table
            statement = dbConnection.prepareStatement(query2);
            statement.setLong(1, client.getUserID());
            statement.setInt(2, client.getAge());
            statement.setString(3, client.getCountry());
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
     * Gets the connection and calls a query to update the database with the Client object.
     * @param client object containing the new information for the Client with the same id
     */
    @Override
    public void update(Client client)
    {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        String query1 = "UPDATE client SET age = ?, country = ? WHERE userID = " + client.getUserID();
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
            statement.setString(1, client.getType().toString());
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

    /**
     * Gets the connection and calls a query to delete the entry with the given id from the database.
     * @param id delete criteria
     */
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

    @Override
    public Client findByName(String name) {
        return null;
    }

    @Override
    public List<Client> findAllByChannel(String channel) {
        return null;
    }

    @Override
    public List<Client> findAllByGenre(String genre) {
        return null;
    }

    @Override
    public List<Client> findAllByStartHour(int startHour) {
        return null;
    }

    @Override
    public List<Client> findAllByUserID(Long userID) {
        return null;
    }

    @Override
    public List<Client> findAllByVideoID(Long videoID) {
        return null;
    }

    @Override
    public void deleteUserHistory(Long userID) {}
}
