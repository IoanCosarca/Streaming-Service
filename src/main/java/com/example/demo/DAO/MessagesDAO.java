package com.example.demo.DAO;

import com.example.demo.Connection.ConnectionFactory;
import com.example.demo.Model.Message;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MessagesDAO implements DAO<Message> {
    /**
     * Gets the connection, calls a query to get all the message in the database who belong to the specified user and returns them as list.
     * @param userID selection criteria
     * @return List[Message]
     */
    @Override
    public List<Message> findAllByUserID(Long userID)
    {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;
        List<Message> list = new ArrayList<>();
        String query = "SELECT * FROM messages WHERE userID = ?";
        try
        {
            statement = dbConnection.prepareStatement(query);
            statement.setLong(1, userID);
            rs = statement.executeQuery();
            while (rs.next()) {
                list.add(constructMessage(rs));
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
     * Given a result set entry from a query, constructs a Message object with the fields and returns it.
     * @param rs result set containing the fields from the table
     * @return Message
     * @throws SQLException the SQL exception will be handled where the method is called
     */
    private Message constructMessage(ResultSet rs) throws SQLException
    {
        Message message = new Message();
        message.setUserID(rs.getLong("userID"));
        message.setMessage(rs.getString("message"));
        return message;
    }

    /**
     * Gets the connection and calls a query to insert the received Message object into the database.
     * @param message the new object entry
     */
    @Override
    public void save(Message message)
    {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        String query = "INSERT INTO messages (userID, message) VALUES (?, ?)";
        try
        {
            statement = dbConnection.prepareStatement(query);
            statement.setLong(1, message.getUserID());
            statement.setString(2, message.getMessage());
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
    public List<Message> getAll() {
        return null;
    }

    @Override
    public void update(Message message) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Message findByID(Long id) {
        return null;
    }

    @Override
    public Message findByEmail(String email) {
        return null;
    }

    @Override
    public List<Message> findAllByAge(int age) {
        return null;
    }

    @Override
    public List<Message> findAllByCountry(String country) {
        return null;
    }

    @Override
    public List<Message> findByName(String name) {
        return null;
    }

    @Override
    public List<Message> findAllByChannel(String channel) {
        return null;
    }

    @Override
    public List<Message> findAllByGenre(String genre) {
        return null;
    }

    @Override
    public List<Message> findAllByStartHour(int startHour) {
        return null;
    }

}
