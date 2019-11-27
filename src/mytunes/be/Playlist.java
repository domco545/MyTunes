
package mytunes.be;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Martin
 */
public class Playlist{
    private int id;
    private String name;
    private List<Song> songs = new ArrayList();

    public Playlist(int id, String name) {
      this.id = id;
      this.name = name;
	
    }
    
    public void addSongs(List<Song> songs){
        this.songs = songs;
    }
    
    public List<Song> getAllSongsOnPlaylist(){
        return songs;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }    
}
