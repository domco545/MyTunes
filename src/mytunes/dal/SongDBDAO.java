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
    public static void main(String[] args) {
        SongDBDAO s=new SongDBDAO();
        List<Song> songs =new ArrayList<Song>();
        songs=s.getAllSongs();
        for (Song song : songs) {
            System.out.println(song);
        }
        
        
    }
   
   public List<Song> getAllSongs()
   {
       try(Connection con=ds.getConnection()){
           String sql="select * from Songs";
         Statement s= con.createStatement();
         List<Song> songs = new ArrayList();
       
         ResultSet r = s.executeQuery(sql);
         while(r.next())
         {
             int id = r.getInt("id");
             String title=r.getString("title");
             String artist=r.getString("artist");
             int genre = r.getInt("genre_id");
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
  /* private int getNextAvailableId() {
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
    }*/
   public void createSong(String title,String artist,int genre,int time,String path)
   {
     
       try(Connection con=ds.getConnection()){
       String sql = "insert into Songs (title,artist,genre,time,path) values (?,?,?,?,?)";
        PreparedStatement p=con.prepareStatement(sql);
       
        p.setString(1, title);
        p.setString(2, artist);
        p.setInt(3, genre);
        p.setInt(4, time);
        p.setString(5, path);
        p.executeUpdate();
        
       
       } catch (SQLServerException ex) {
            Logger.getLogger(SongDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SongDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
       
   }
   
   public void editSong(Song song)
   {
       try(Connection con=ds.getConnection())
       {
           String sql="update Songs set title=? , artist=? , genre=? , time=? , path=? where id=?";
           PreparedStatement p=con.prepareStatement(sql);
           
           p.setString(1,song.getTitle());
           p.setString(2, song.getArtist());
           p.setInt(3, song.getGenre());
           p.setInt(4,song.getTime());
           p.setString(5,song.getPath());
           p.setInt(6,song.getId());
           
           p.executeUpdate();
       
       } catch (SQLServerException ex) {
            Logger.getLogger(SongDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SongDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
       
   }
   
   public void deleteById(Song song)
   {
        try(Connection con=ds.getConnection())
        {
            String sql = "delete from Songs where id = ?";
            PreparedStatement p = con.prepareStatement(sql);
            
            p.setInt(1,song.getId());
            
            p.executeUpdate();
                    
        } catch (SQLServerException ex) {
            Logger.getLogger(SongDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SongDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
   
   }
}
   
