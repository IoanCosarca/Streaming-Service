package com.example.demo.DAO;

import com.example.demo.Connection.ConnectionFactory;
import com.example.demo.Model.Video;
import com.example.demo.Model.VideoGenre;
import com.example.demo.Model.VideoStatus;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Defines all the methods and queries for accessing the Video table in the database.
 */
@Repository
public class VideoDAO implements DAO<Video> {
    /**
     * Gets the connection, calls a query to get all the entries in the database and returns them as list.
     * @return List[Video]
     */
    @Override
    public List<Video> getAll()
    {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;
        List <Video> list = new ArrayList<>();
        String query = "SELECT * FROM video";
        try
        {
            statement = dbConnection.prepareStatement(query);
            rs = statement.executeQuery();
            while (rs.next()) {
                list.add(constructVideo(rs));
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

    @Override
    public Video findByID(Long id)
    {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;
        String query = "SELECT * FROM video WHERE id = ?";
        try
        {
            statement = dbConnection.prepareStatement(query);
            statement.setLong(1, id);
            rs = statement.executeQuery();
            rs.next();
            return constructVideo(rs);
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
     * Gets the connection, calls a query to get the Video from the database with the given name and returns it.
     * @param name selection criteria
     * @return Video
     */
    public List<Video> findByName(String name)
    {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;
        List <Video> list = new ArrayList<>();
        String query = "SELECT * FROM video WHERE name LIKE CONCAT('%', ?, '%')";
        try
        {
            statement = dbConnection.prepareStatement(query);
            statement.setString(1, name);
            rs = statement.executeQuery();
            while (rs.next()) {
                list.add(constructVideo(rs));
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
     * Gets the connection, calls a query to get all the Videos in the database from a specified channel and returns them as list.
     * @param channel selection criteria
     * @return List[Video]
     */
    public List<Video> findAllByChannel(String channel)
    {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;
        List <Video> list = new ArrayList<>();
        String query = "SELECT * FROM video WHERE channel LIKE CONCAT('%', ?, '%')";
        try
        {
            statement = dbConnection.prepareStatement(query);
            statement.setString(1, channel);
            rs = statement.executeQuery();
            while (rs.next()) {
                list.add(constructVideo(rs));
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
     * Gets the connection, calls a query to get all the Videos in the database of a specified genre and returns them as list.
     * @param genre selection criteria
     * @return List[Video]
     */
    public List<Video> findAllByGenre(String genre)
    {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;
        List <Video> list = new ArrayList<>();
        String query = "SELECT * FROM video WHERE genre = ?";
        try
        {
            statement = dbConnection.prepareStatement(query);
            statement.setString(1, genre);
            rs = statement.executeQuery();
            while (rs.next()) {
                list.add(constructVideo(rs));
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
     * Gets the connection, calls a query to get all the Videos in the database that start at a specified hour and returns them as list.
     * @param startHour selection criteria
     * @return List[Video]
     */
    public List<Video> findAllByStartHour(int startHour)
    {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;
        List <Video> list = new ArrayList<>();
        String query = "SELECT * FROM video WHERE startHour = ?";
        try
        {
            statement = dbConnection.prepareStatement(query);
            statement.setInt(1, startHour);
            rs = statement.executeQuery();
            while (rs.next()) {
                list.add(constructVideo(rs));
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
     * Given a result set entry from a query, constructs a Video object with the fields and returns it.
     * @param rs result set containing the fields from the table
     * @return Video
     * @throws SQLException the SQL exception will be handled where the method is called
     */
    private Video constructVideo(ResultSet rs) throws SQLException
    {
        Video video = new Video();
        video.setId(rs.getLong("id"));
        video.setName(rs.getString("name"));
        video.setChannel(rs.getString("channel"));
        video.setGenre(VideoGenre.valueOf(rs.getString("genre")));
        video.setAgeRestriction(rs.getBoolean("ageRestriction"));
        video.setLink(rs.getString("link"));
        video.setStartHour(rs.getInt("startHour"));
        video.setEndHour(rs.getInt("endHour"));
        video.setStatus(VideoStatus.valueOf(rs.getString("status")));
        return video;
    }

    /**
     * Gets the connection and calls a query to insert the received Video object into the database.
     * @param video the new Video table entry
     */
    @Override
    public void save(Video video)
    {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;
        String query1 = "SELECT MAX(id) FROM video";
        String query2 = "INSERT INTO video (id, name, channel, genre, ageRestriction, link, startHour, endHour, status) ";
        query2 += "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try
        {
            statement = dbConnection.prepareStatement(query1);
            rs = statement.executeQuery();
            rs.next();
            video.setId(rs.getLong("MAX(id)") + 1);
            // Insert video
            statement = dbConnection.prepareStatement(query2);
            statement.setLong(1, video.getId());
            statement.setString(2, video.getName());
            statement.setString(3, video.getChannel());
            statement.setString(4, video.getGenre().toString());
            statement.setBoolean(5, video.isAgeRestriction());
            statement.setString(6, video.getLink());
            statement.setInt(7, video.getStartHour());
            statement.setInt(8, video.getEndHour());
            statement.setString(9, video.initializeStatus().toString());
            statement.executeUpdate();
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
     * Gets the connection and calls a query to update the database with the Video object.
     * @param video object containing the new information for the Video with the same id
     */
    @Override
    public void update(Video video)
    {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        String query = "UPDATE video SET ";
        query += "name = ?, channel = ?, genre = ?, ageRestriction = ?, link = ?, startHour = ?, endHour = ?, status = ?";
        query += " WHERE id = " + video.getId();
        try
        {
            statement = dbConnection.prepareStatement(query);
            statement.setString(1, video.getName());
            statement.setString(2, video.getChannel());
            statement.setString(3, video.getGenre().toString());
            statement.setBoolean(4, video.isAgeRestriction());
            statement.setString(5, video.getLink());
            statement.setInt(6, video.getStartHour());
            statement.setInt(7, video.getEndHour());
            statement.setString(8, video.getStatus().toString());
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
        String query = "DELETE FROM video WHERE id = ?";
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

    @Override
    public Video findByEmail(String email) {
        return null;
    }

    @Override
    public List<Video> findAllByAge(int age) {
        return null;
    }

    @Override
    public List<Video> findAllByCountry(String country) {
        return null;
    }

    @Override
    public List<Video> findAllByUserID(Long userID) {
        return null;
    }

}
