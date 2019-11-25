/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.dal;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mytunes.be.Songs_On_playlist;


/**
 *
 * @author domin
 */
public class Songs_On_PlaylistDBDAO {
    SQLServerDataSource ds;
    
    public Songs_On_PlaylistDBDAO() {
        ds = new SQLServerDataSource();
        ds.setDatabaseName("MyTunesCSe19B3");
        ds.setUser("CSe19B_3");
        ds.setPassword("CSe19B_3");
        ds.setPortNumber(1433);
        ds.setServerName("10.176.111.31");
    }
    
    
    public List<Songs_On_playlist> getAllSongsInPlaylist(int playlistID){
        List<Songs_On_playlist> songs = new ArrayList<Songs_On_playlist>();
        try(Connection conn = ds.getConnection()){
            String sql = "SELECT * FROM Songs_On_Playlist WHERE playlist_id=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, playlistID);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                Songs_On_playlist p = new Songs_On_playlist(rs.getInt("playlist_id"),rs.getInt("song_id"));
                p.setID(rs.getInt("id"));
                songs.add(p);
            }
        } catch (SQLServerException ex) {
            Logger.getLogger(Songs_On_PlaylistDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Songs_On_PlaylistDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return songs;
    }

    public int getNextID() {
            try(Connection con = ds.getConnection()){
            String sql = "SELECT TOP (1) id FROM Songs_On_Playlist ORDER BY id DESC";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            if (rs.next()) {
                return rs.getInt("id")+1;
            }

    }   catch (SQLServerException ex) {
            Logger.getLogger(Songs_On_PlaylistDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Songs_On_PlaylistDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
          return -1;
    }
}
