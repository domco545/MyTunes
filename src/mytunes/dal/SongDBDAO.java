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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mytunes.be.Genre;
import mytunes.be.Song;

/**
 *
 * @author domin
 */
public class SongDBDAO {
    private SQLServerDataSource ds;
   
   public SongDBDAO(){     
        ds = new SQLServerDataSource();
        ds.setDatabaseName("MyTunesCSe19B3");
        ds.setUser("CSe19B_3");
        ds.setPassword("CSe19B_3");
        ds.setServerName("10.176.111.31");
        ds.setPortNumber(1433);
}
   // Seletcs everything from Song table
   public List<Song> getAllSongs()
   {
       try(Connection con=ds.getConnection()){
           String sql="SELECT Songs.id, Songs.title, Songs.artist, Songs.genre_id, Songs.[time], Songs.[path], Genre.id AS genre_table_id, Genre.name\n" +
                      "FROM Songs\n" +
                      "LEFT JOIN Genre ON Songs.genre_id = Genre.id";
                        
         Statement s= con.createStatement();
         List<Song> songs = new ArrayList();
       
         ResultSet r = s.executeQuery(sql);
         while(r.next())
         {
             int id = r.getInt("id");
             String title=r.getString("title");
             String artist=r.getString("artist");
             Genre genre = new Genre(r.getInt("genre_table_id"),r.getString("name"));
             int time=r.getInt("time");
             String path=r.getString("path");
             
           Song song = new Song(id,title,artist,genre,time,path);
           songs.add(song);
         }
        return songs;
       }
       catch (SQLServerException ex) {
            Logger.getLogger(SongDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SongDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
   }
// Inserts a new Song
   public void createSong(String title,String artist,Genre genre,int time,String path)
   {
     
       try(Connection con=ds.getConnection()){
       String sql = "insert into Songs (title,artist,genre_id,time,path) values (?,?,?,?,?)";
        PreparedStatement p=con.prepareStatement(sql);
       
        p.setString(1, title);
        p.setString(2, artist);
        p.setInt(3, genre.getId());
        p.setInt(4, time);
        p.setString(5, path);
        p.executeUpdate();
        
       
       } catch (SQLServerException ex) {
            Logger.getLogger(SongDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SongDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
       
   }
   // Updates a Song
   public void editSong(Song song)
   {
       try(Connection con=ds.getConnection())
       {
           String sql="update Songs set title=? , artist=? , genre_id=? , time=? , path=? where id=?";
           PreparedStatement p=con.prepareStatement(sql);
           
           p.setString(1,song.getTitle());
           p.setString(2, song.getArtist());
           p.setInt(3, song.getGenreId());
           p.setInt(4,song.getTimeInInt());
           p.setString(5,song.getPath());
           p.setInt(6,song.getId());
           
           p.executeUpdate();
       
       } catch (SQLServerException ex) {
            Logger.getLogger(SongDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SongDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
       
   }
   // Deletes a Song by id
   public void deleteById(Song song)
   {
        try(Connection con=ds.getConnection())
        {
            String sql = " DELETE FROM Songs_On_Playlist WHERE song_id=?;DELETE FROM Songs WHERE id = ?";
            PreparedStatement p = con.prepareStatement(sql);
            
            p.setInt(1,song.getId());
            p.setInt(2, song.getId());
            p.executeUpdate();
                    
        } catch (SQLServerException ex) {
            Logger.getLogger(SongDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SongDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
   
   }
   // Method for the filter
   // It searches a Song by the title and the artist
   public List<Song> querrySongs(String querry){
        try(Connection con=ds.getConnection()){
        List<Song> songs = new ArrayList();
        querry = querry.replace("!", "!!")
                       .replace("%", "!%")
                       .replace("_", "!_")
                       .replace("[", "![");
        String sql="SELECT Songs.*, Genre.name FROM Songs \n" +
                   "LEFT JOIN Genre ON Songs.genre_id = Genre.id\n" +
                   "WHERE title LIKE ? OR artist LIKE ?";
                        
        PreparedStatement p= con.prepareStatement(sql);
        p.setString(1, "%"+querry+"%");
        p.setString(2, "%"+querry+"%");
        ResultSet r = p.executeQuery();
        
        while(r.next())
        {
            int id = r.getInt("id");
            String title=r.getString("title");
            String artist=r.getString("artist");
            Genre genre = new Genre(r.getInt("genre_id"),r.getString("name"));
            int time=r.getInt("time");
            String path=r.getString("path");
             
           Song song = new Song(id,title,artist,genre,time,path);
           songs.add(song);
        }
        return songs;
       }
          catch (SQLServerException ex) {
            Logger.getLogger(SongDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SongDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
   }
}
   
