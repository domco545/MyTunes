
package mytunes.dal;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.util.logging.Level;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;
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
    
 public void createPlaylist( String name)
    {
       try(Connection con = ds.getConnection())
       {
           String sql="insert into Playlist set (name) values (?)";
           PreparedStatement p=con.prepareStatement(sql);
           p.setString(1,name);
           p.executeUpdate();
           
       } catch (SQLServerException ex) {
        Logger.getLogger(PlaylistDBDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(PlaylistDBDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    }
    
    
}
