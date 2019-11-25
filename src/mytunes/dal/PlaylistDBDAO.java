
package mytunes.dal;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
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
    
 
    
    
}
