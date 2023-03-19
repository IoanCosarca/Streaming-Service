package com.example.demo.DAO;

import com.example.demo.Connection.ConnectionFactory;
import com.example.demo.Model.Video;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class VideoDAO implements DAO<Video> {
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

    public Video findByName(String name)
    {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;
        String query = "SELECT * FROM video WHERE name = ?";
        try
        {
            statement = dbConnection.prepareStatement(query);
            statement.setString(1, name);
            rs = statement.executeQuery();
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

    public List<Video> findAllByChannel(String channel)
    {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;
        List <Video> list = new ArrayList<>();
        String query = "SELECT * FROM video WHERE channel = ?";
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

    private Video constructVideo(ResultSet rs) throws SQLException
    {
        Video video = new Video();
        video.setId(rs.getLong("id"));
        video.setName(rs.getString("name"));
        video.setChannel(rs.getString("channel"));
        video.setGenre(rs.getString("genre"));
        video.setAgeRestriction(rs.getBoolean("ageRestriction"));
        video.setLink(rs.getString("link"));
        video.setStartHour(rs.getInt("startHour"));
        video.setEndHour(rs.getInt("endHour"));
        video.setStatus(rs.getString("status"));
        return video;
    }

    @Override
    public void save(Video video)
    {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        String query = "INSERT INTO video (name, channel, genre, ageRestriction, link, startHour, endHour, status) ";
        query += "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try
        {
            statement = dbConnection.prepareStatement(query);
            statement.setString(1, video.getName());
            statement.setString(2, video.getChannel());
            statement.setString(3, video.getGenre());
            statement.setBoolean(4, video.isAgeRestriction());
            statement.setString(5, video.getLink());
            statement.setInt(6, video.getStartHour());
            statement.setInt(7, video.getEndHour());
            statement.setString(8, video.getStatus());
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
            statement.setString(3, video.getGenre());
            statement.setBoolean(4, video.isAgeRestriction());
            statement.setString(5, video.getLink());
            statement.setInt(6, video.getStartHour());
            statement.setInt(7, video.getEndHour());
            statement.setString(8, video.getStatus());
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
}
