/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.dal;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mytunes.be.Genre;

/**
 *
 * @author domin
 */
public class GenreDBDAO {

   private SQLServerDataSource ds;
    public GenreDBDAO(){
        ds = new SQLServerDataSource();
        ds.setDatabaseName("MyTunesCSe19B3");
        ds.setUser("CSe19B_3");
        ds.setPassword("CSe19B_3");
        ds.setServerName("10.176.111.31");
        ds.setPortNumber(1433);
    }
    
    public List<Genre> loadGenres()
    {
         try(Connection con = ds.getConnection()){
             List<Genre> genres= new ArrayList();
             String sql = "SELECT * FROM Genre";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
             while (rs.next()) {                 
                 genres.add(new Genre(rs.getInt("id"),rs.getString("name")));
             }
             return genres;
             
             
         } catch (SQLServerException ex) {
           Logger.getLogger(GenreDBDAO.class.getName()).log(Level.SEVERE, null, ex);
       } catch (SQLException ex) {
           Logger.getLogger(GenreDBDAO.class.getName()).log(Level.SEVERE, null, ex);
       }
        return null;
    }
    
}
