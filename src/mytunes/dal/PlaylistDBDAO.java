
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
    public PlaylistDBDAO(){
  ds = new SQLServerDataSource();
        ds.setDatabaseName("MyTunesCSe19B3");
        ds.setUser("CSe19B_3");
        ds.setPassword("CSe19B_3");
        ds.setServerName("10.176.111.31");
        ds.setPortNumber(1433);
    }
    
 public void createPlaylist(String name)
    {
       try(Connection con = ds.getConnection())
       {
           String sql="INSERT INTO Playlist (name) values (?)";
           PreparedStatement p=con.prepareStatement(sql);
           p.setString(1,name);
           p.executeUpdate();
           
           
       } catch (SQLServerException ex) {
        Logger.getLogger(PlaylistDBDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(PlaylistDBDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    }
 
    public void updatePlaylist(Playlist playlist)
    {
        try(Connection con = ds.getConnection())
        {
            String sql="UPDATE Playlist SET name=? WHERE id=?";
            PreparedStatement p = con.prepareStatement(sql);
            p.setString(1,playlist.getName());
            p.setInt(2, playlist.getId());
            p.executeUpdate();
            
        } catch (SQLServerException ex) {
        Logger.getLogger(PlaylistDBDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(PlaylistDBDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    public void deletePlaylist(Playlist playlist)
    {
        try(Connection con = ds.getConnection())
        {
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
    
    
    public List<Playlist> loadPlaylists(){
        try(Connection con = ds.getConnection()){
            List<Playlist> pl = new ArrayList();
            String sql = "SELECT * FROM Playlist";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next()){                
                pl.add(new Playlist(rs.getInt("id"), rs.getString("name")));
            }
            
            
            
            return pl;
                    
        } catch (SQLServerException ex) {
        Logger.getLogger(PlaylistDBDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(PlaylistDBDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
        return null;
 }

    public List<Playlist> getAllPlaylists(){
        List<Playlist> pl = loadPlaylists();
        
        
        try(Connection con = ds.getConnection()){
            
            for (Playlist playlist : pl) {
                List<Song> songs = new ArrayList();
                int id = playlist.getId();
                
            String sql ="SELECT Songs_On_Playlist.song_id, Songs.title, Songs.artist, Songs.genre_id, Songs.[time], Songs.[path], Genre.id AS genre_table_id, Genre.name\n" +
                        "FROM Songs_On_Playlist\n" +
                        "LEFT JOIN Songs ON Songs_On_Playlist.song_id = Songs.id\n" +
                        "LEFT JOIN Genre ON Songs.genre_id = Genre.id\n" +
                        "Where Songs_On_Playlist.playlist_id=?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next()){
                songs.add(new Song(rs.getInt("song_id"), rs.getString("title"), rs.getString("artist"), new Genre(rs.getInt("genre_table_id"),rs.getString("name")), rs.getInt("time"), rs.getString("path")));
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
    
    public void addSongToPlaylist(int plId, int sId){
        try(Connection con = ds.getConnection())
       {
           String sql="INSERT INTO Songs_On_Playlist (playlist_id, song_id) values (?,?)";
           PreparedStatement p=con.prepareStatement(sql);
           p.setInt(1,plId);
           p.setInt(2, sId);
           p.executeUpdate();
       } catch (SQLServerException ex) {
        Logger.getLogger(PlaylistDBDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(PlaylistDBDAO.class.getName()).log(Level.SEVERE, null, ex);
    }    
    }
}
