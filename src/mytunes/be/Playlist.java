
package mytunes.be;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Martin
 */
//Playlist class contains getters and setters
public class Playlist{
    private int id;
    private String name;
    private int totalSongs;
    private int totalTime;
    private List<Song> songs = new ArrayList();

    public Playlist(int id, String name,int totalTime, int totalSongs) {
      this.id = id;
      this.name = name;
      this.totalTime = totalTime;
      this.totalSongs = totalSongs;
	
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
    
    public void setName(String name){
        this.name = name;
    }

    public int getTotalSongs() {
        return totalSongs;
    }

    public void setTotalSongs(int totalSongs) {
        this.totalSongs = totalSongs;
    }

    public int getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }

    @Override
    public String toString() {
        return name;
    }
    
}
