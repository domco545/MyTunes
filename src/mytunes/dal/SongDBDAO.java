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
   public List<Song> getAllSongs()
   {
       try(Connection con=ds.getConnection()){
           String sql="select * from Songs ";
         Statement s= con.createStatement();
         List<Song> songs = new ArrayList();
       
         ResultSet r = s.executeQuery(sql);
         while(r.next())
         {
             int id = r.getInt("id");
             String title=r.getString("title");
             String artist=r.getString("artist");
             int genre = r.getInt("genre");
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
   private int getNextAvailableId() {
         try(Connection con=ds.getConnection()){
         String sql="select top (1) id from Songs order by id desc";
         Statement s= con.createStatement();
        ResultSet r = s.executeQuery(sql);
        if(r.next())
         { return r.getInt("id")+1; }
         } catch (SQLServerException ex) {
            Logger.getLogger(SongDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SongDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    return -1;
    }
   public Song createSong(String title,String artist,int genre,int time,String path)
   {
       int id = getNextAvailableId();
       try(Connection con=ds.getConnection()){
       String sql = "insert into Songs (id,title,artist,genre,time,path) values (?,?,?,?,?,?)";
        PreparedStatement p=con.prepareStatement(sql);
        p.setInt(1,id);
        p.setString(2, title);
        p.setString(3, artist);
        p.setInt(4, genre);
        p.setInt(5, time);
        p.setString(6, path);
        p.executeUpdate();
        return new Song(id,title,artist,genre,time,path);
       
       } catch (SQLServerException ex) {
            Logger.getLogger(SongDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SongDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
       return null;
   }
   
   
}
   
