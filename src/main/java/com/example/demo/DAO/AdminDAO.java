package com.example.demo.DAO;

import com.example.demo.Connection.ConnectionFactory;
import com.example.demo.Model.Admin;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Defines all the methods and queries for accessing the Admin table in the database.
 */
@Repository
public class AdminDAO implements DAO<Admin> {
    /**
     * Gets the connection, calls a query to get all the entries in the database and returns them as list.
     * @return List[Admin]
     */
    @Override
    public List<Admin> getAll()
    {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        ResultSet rsA = null;
        ResultSet rsU = null;
        List<Admin> list = new ArrayList<>();
        String query1 = "SELECT * FROM admin";
        String query2 = "SELECT * FROM user WHERE userID = ?";
        try
        {
            statement = dbConnection.prepareStatement(query1);
            rsA = statement.executeQuery();
            while (rsA.next())
            {
                statement = dbConnection.prepareStatement(query2);
                statement.setLong(1, rsA.getLong("userID"));
                rsU = statement.executeQuery();
                rsU.next();
                list.add(constructAdmin(rsA));
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally
        {
            ConnectionFactory.close(rsA);
            ConnectionFactory.close(rsU);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(dbConnection);
        }
        return list;
    }

    /**
     * Gets the connection, calls a query to get the object from the database with the given id and returns it.
     * @param id - selection criteria
     * @return Admin
     */
    public Admin findByID(Long id)
    {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;
        String query = "SELECT * FROM admin WHERE id = ?";
        try
        {
            statement = dbConnection.prepareStatement(query);
            statement.setLong(1, id);
            rs = statement.executeQuery();
            rs.next();
            return constructAdmin(rs);
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

    /**
     * Gets the connection, calls a query to get the object from the database with the given email and returns it.
     * @param email - selection criteria
     * @return Admin
     */
    public Admin findByEmail(String email)
    {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;
        String query = "SELECT * FROM user WHERE email = ?";
        try
        {
            statement = dbConnection.prepareStatement(query);
            statement.setString(1, email);
            rs = statement.executeQuery();
            rs.next();
            return constructAdmin(rs);
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

    /**
     * Give a result set entry from a query, constructs an Admin object with the fields and returns it.
     * @param rs - result set containing the fields from the table
     * @return Admin
     * @throws SQLException - the SQL exception will be handled where the method is called
     */
    private Admin constructAdmin(ResultSet rs) throws SQLException
    {
        Admin admin = new Admin();
        admin.setId(rs.getLong("id"));
        admin.setUserID(rs.getLong("userID"));
        return admin;
    }

    /**
     * Gets the connection and calls a query to insert the received Admin object into the database.
     * @param admin - the new Admin table entry
     */
    @Override
    public void save(Admin admin)
    {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        String query1 = "INSERT INTO admin (id, userID) VALUES (?, ?)";
        String query2 = "INSERT INTO user (userID, type, firstName, lastName, email, password) VALUES (?, ?, ?, ?, ?, ?)";
        try
        {
            // Insert in the Admin Table
            statement = dbConnection.prepareStatement(query1);
            statement.setLong(1, admin.getId());
            statement.setLong(2, admin.getUserID());
            statement.executeUpdate();
            // Insert in the User Table
            statement = dbConnection.prepareStatement(query2);
            statement.setLong(1, admin.getUserID());
            statement.setString(2, admin.getType());
            statement.setString(3, admin.getFirstName());
            statement.setString(4, admin.getLastName());
            statement.setString(5, admin.getEmail());
            statement.setString(6, admin.getPassword());
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
     * Gets the connection and calls a query to update the database with the Admin object.
     * @param admin - object containing the new information for the Admin with the same id
     */
    @Override
    public void update(Admin admin)
    {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        String query = "UPDATE user SET type = ?, firstName = ?, lastName = ?, email = ?, password = ?";
        query += " WHERE userID = " + admin.getUserID();
        try
        {
            statement = dbConnection.prepareStatement(query);
            statement.setString(1, admin.getType());
            statement.setString(2, admin.getFirstName());
            statement.setString(3, admin.getLastName());
            statement.setString(4, admin.getEmail());
            statement.setString(5, admin.getPassword());
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
     * @param id - delete criteria
     */
    @Override
    public void delete(Long id)
    {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        String query1 = "DELETE FROM admin WHERE id = ?";
        String query2 = "DELETE FROM user WHERE userID = ?";
        try
        {
            Admin admin = findByID(id);
            // Delete in the Admin Table
            statement = dbConnection.prepareStatement(query1);
            statement.setLong(1, id);
            statement.execute();
            // Delete in the User Table
            statement = dbConnection.prepareStatement(query2);
            statement.setLong(1, admin.getUserID());
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
    public List<Admin> findAllByAge(int age) {
        return null;
    }

    @Override
    public List<Admin> findAllByCountry(String country) {
        return null;
    }

    @Override
    public Admin findByName(String name) {
        return null;
    }

    @Override
    public List<Admin> findAllByChannel(String channel) {
        return null;
    }

    @Override
    public List<Admin> findAllByGenre(String genre) {
        return null;
    }

    @Override
    public List<Admin> findAllByStartHour(int startHour) {
        return null;
    }

    @Override
    public List<Admin> findAllByUserID(Long userID) {
        return null;
    }

    @Override
    public List<Admin> findAllByVideoID(Long videoID) {
        return null;
    }

    @Override
    public void deleteUserHistory(Long userID) {}
}
