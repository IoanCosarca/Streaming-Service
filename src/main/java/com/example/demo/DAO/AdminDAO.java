package com.example.demo.DAO;

import com.example.demo.Connection.ConnectionFactory;
import com.example.demo.Model.Admin;
import com.example.demo.Model.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDAO implements DAO<Admin> {
    @Override
    public List<Admin> getAll()
    {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;
        List<Admin> list = new ArrayList<>();
        String query = "SELECT * FROM admin";
        try
        {
            statement = dbConnection.prepareStatement(query);
            rs = statement.executeQuery();
            while (rs.next()) {
                list.add(constructAdmin(rs));
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

    public Admin findByEmail(String email)
    {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;
        String query = "SELECT * FROM admin WHERE email = ?";
        try
        {
            statement = dbConnection.prepareStatement(query);
            statement.setString(1, email);
            rs = statement.executeQuery();
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

    private Admin constructAdmin(ResultSet rs) throws SQLException
    {
        Admin admin = new Admin();
        admin.setId(rs.getLong("id"));
        admin.setUserID(rs.getLong("userID"));
        return admin;
    }

    @Override
    public void save(Admin admin)
    {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        String query1 = "INSERT INTO admin (userID) VALUES (?)";
        String query2 = "INSERT INTO user (type, firstName, lastName, email, password) VALUES (?, ?, ?, ?, ?)";
        try
        {
            // Insert in the Admin Table
            statement = dbConnection.prepareStatement(query1);
            statement.setLong(1, admin.getUserID());
            statement.executeUpdate();
            // Insert in the User Table
            statement = dbConnection.prepareStatement(query2);
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
}
