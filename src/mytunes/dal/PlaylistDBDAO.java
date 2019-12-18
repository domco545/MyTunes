package mytunes.dal;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.util.logging.Level;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import mytunes.be.Genre;
import mytunes.be.Playlist;
import mytunes.be.Song;

/**
 *
 * @author Martin
 */
public class PlaylistDBDAO {

    private SQLServerDataSource ds;

    public PlaylistDBDAO() {
        ds = new SQLServerDataSource();
        ds.setDatabaseName("MyTunesCSe19B3");
        ds.setUser("CSe19B_3");
        ds.setPassword("CSe19B_3");
        ds.setServerName("10.176.111.31");
        ds.setPortNumber(1433);
    }
// Method for creating a new Playlist
    public void createPlaylist(String name) {
        try ( Connection con = ds.getConnection()) {
            String sql = "INSERT INTO Playlist (name) values (?)";
            PreparedStatement p = con.prepareStatement(sql);
            p.setString(1, name);
            p.executeUpdate();

        } catch (SQLServerException ex) {
            Logger.getLogger(PlaylistDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PlaylistDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
// Method for update a Playlist
    public void updatePlaylist(Playlist playlist) {
        try ( Connection con = ds.getConnection()) {
            String sql = "UPDATE Playlist SET name=? WHERE id=?";
            PreparedStatement p = con.prepareStatement(sql);
            p.setString(1, playlist.getName());
            p.setInt(2, playlist.getId());
            p.executeUpdate();

        } catch (SQLServerException ex) {
            Logger.getLogger(PlaylistDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PlaylistDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
// Deletes a Playlist
    public void deletePlaylist(Playlist playlist) {
        try ( Connection con = ds.getConnection()) {
            String sql = "DELETE FROM Playlist WHERE id=?";
            PreparedStatement p = con.prepareStatement(sql);
            p.setInt(1, playlist.getId());
            p.executeUpdate();

        } catch (SQLServerException ex) {
        Logger.getLogger(PlaylistDBDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(PlaylistDBDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
// Loads the Playlists 
    // Shows the totaltime and the number of song on a Playlists 
    // Also orders them by the time and the number of songs
    public List<Playlist> loadPlaylists() {
        try ( Connection con = ds.getConnection()) {
            List<Playlist> pl = new ArrayList();
            String sql = "SELECT Playlist.*,SUM(time) AS totaltime,COUNT(playlist_id) AS totalsongs FROM Playlist\n" +
                         "LEFT JOIN Songs_On_Playlist ON Playlist.id  = Songs_On_Playlist.playlist_id\n" +
                         "LEFT JOIN Songs ON Songs_On_Playlist.song_id = Songs.id\n" +
                         "GROUP BY Playlist.id,Playlist.name";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next()){                
                pl.add(new Playlist(rs.getInt("id"), rs.getString("name"), rs.getInt("totaltime"), rs.getInt("totalsongs")));
            }

            return pl;

        } catch (SQLServerException ex) {
            Logger.getLogger(PlaylistDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PlaylistDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Playlist> getAllPlaylists() {
        List<Playlist> pl = loadPlaylists();

        try ( Connection con = ds.getConnection()) {

            for (Playlist playlist : pl) {
                List<Song> songs = new ArrayList();
                int id = playlist.getId();

                String sql = "SELECT Songs_On_Playlist.song_id, Songs_On_Playlist.position, Songs.title, Songs.artist, Songs.genre_id, Songs.[time], Songs.[path], Genre.id AS genre_table_id, Genre.name\n"
                        + "FROM Songs_On_Playlist\n"
                        + "LEFT JOIN Songs ON Songs_On_Playlist.song_id = Songs.id\n"
                        + "LEFT JOIN Genre ON Songs.genre_id = Genre.id\n"
                        + "Where Songs_On_Playlist.playlist_id=?\n"
                        + "ORDER BY Songs_On_Playlist.position";
                PreparedStatement pstmt = con.prepareStatement(sql);
                pstmt.setInt(1, id);
                ResultSet rs = pstmt.executeQuery();

                while (rs.next()) {
                    Song s = new Song(rs.getInt("song_id"), rs.getString("title"), rs.getString("artist"), new Genre(rs.getInt("genre_table_id"), rs.getString("name")), rs.getInt("time"), rs.getString("path"));
                    s.setPosition(rs.getInt("position"));
                    songs.add(s);
                }
                playlist.addSongs(songs);

            }
            return pl;
        } catch (SQLServerException ex) {
            Logger.getLogger(PlaylistDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PlaylistDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
// Inserts a new Playlist
// using subquerry to get next position for song
    public void addSongToPlaylist(int plId, int sId) {
        try ( Connection con = ds.getConnection()) {
            
            String sql = "insert into Songs_On_Playlist(position, playlist_id, song_id)\n" +
                         "select \n" +
                         "MAX(position+1) AS position,?,?\n" +
                         "from Songs_On_Playlist where playlist_id=?;";
            PreparedStatement p = con.prepareStatement(sql);
            p.setInt(1, plId);
            p.setInt(2, sId);
            p.setInt(3, plId);
            p.executeUpdate();
        } catch (SQLServerException ex) {
            Logger.getLogger(PlaylistDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PlaylistDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
// Deletes a song from a Playlist by id
    public void deleteSongOnPlaylist(int plId, int sId, int position) {
        try (Connection con = ds.getConnection()) {
            String sql = "DELETE FROM Songs_On_Playlist WHERE playlist_id =? AND song_id=? AND position = ?";
            PreparedStatement p = con.prepareStatement(sql);
            p.setInt(1, plId);
            p.setInt(2, sId);
            p.setInt(3, position);
            p.executeUpdate();
        } catch (SQLServerException ex) {
            Logger.getLogger(PlaylistDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PlaylistDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void songUp(int plId, int songId, int position){
        //1 is smallest position song can have
        if(position == 1){
            return;
        }
            try (Connection con = ds.getConnection()) {
//            String sql = "UPDATE Songs_On_Playlist\n" +
//                         "SET position = CASE WHEN position = ? THEN ? \n" +
//                         "WHEN position = ? THEN ?\n" +
//                         "Where playlist_id = ?";
            //probably shitty solution but it works
            String sql = "UPDATE Songs_On_Playlist\n" +
                         "SET old_position = position\n" +
                         "WHERE position = ? AND playlist_id=?;\n" +
                         "UPDATE Songs_On_Playlist\n" +
                         "SET position = ?\n" +
                         "WHERE old_position = ? AND playlist_id=?;\n" +
                         "UPDATE Songs_On_Playlist\n" +
                         "SET position = ?\n" +
                         "WHERE old_position !=? AND position = ? AND playlist_id=?;\n" +
                         "UPDATE Songs_On_Playlist\n" +
                         "SET old_position = 0;";
            
            PreparedStatement p = con.prepareStatement(sql);
            p.setInt(1, position);
            p.setInt(2, plId);
            p.setInt(3, position-1);
            p.setInt(4, position);
            p.setInt(5, plId);
            p.setInt(6, position+1);
            p.setInt(7, position);
            p.setInt(8, position-1);
            p.setInt(9, plId);
            p.executeUpdate();
        } catch (SQLServerException ex) {
            Logger.getLogger(PlaylistDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PlaylistDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void songDown(int plId, int songId, int position){
        try (Connection con = ds.getConnection()) {
            String sql1 = "SELECT MAX(position) WHERE playlist_id = ?";
            PreparedStatement p1 = con.prepareStatement(sql1);
            p1.setInt(1, plId);
            ResultSet rs = p1.executeQuery();
            int maxpos = 0;
            
            while(rs.next()){
                maxpos = rs.getInt("position");
            }
            if(position+1 > maxpos){
                return;
            }
            
//            String sql = "UPDATE Songs_On_Playlist\n" +
//                         "SET position = CASE WHEN position = ? THEN ? \n" +
//                         "WHEN position = ? THEN ?\n" +
//                         "Where playlist_id = ?";
            //probably shitty solution but it works
            String sql = "UPDATE Songs_On_Playlist\n" +
                         "SET old_position = position\n" +
                         "WHERE position = ? AND playlist_id=?;\n" +
                         "UPDATE Songs_On_Playlist\n" +
                         "SET position = ?\n" +
                         "WHERE old_position = ? AND playlist_id=?;\n" +
                         "UPDATE Songs_On_Playlist\n" +
                         "SET position = ?\n" +
                         "WHERE old_position !=? AND position = ? AND playlist_id=?;\n" +
                         "UPDATE Songs_On_Playlist\n" +
                         "SET old_position = 0;";
            
            PreparedStatement p = con.prepareStatement(sql);
            p.setInt(1, position);
            p.setInt(2, plId);
            p.setInt(3, position+1);
            p.setInt(4, position);
            p.setInt(5, plId);
            p.setInt(6, position-1);
            p.setInt(7, position);
            p.setInt(8, position+1);
            p.setInt(9, plId);
            p.executeUpdate();
        } catch (SQLServerException ex) {
            Logger.getLogger(PlaylistDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PlaylistDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
