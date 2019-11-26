
package mytunes.be;

import java.util.*;
import java.io.*;
/**
 *
 * @author Martin
 */
public class Playlist{
    private int id;
    private String name;
    
    private int playlist_time;

    public Playlist(String name) {
      
	
	//Creates a Playlist object that contains an ArrayList for Songs.
	public Playlist()
	{
		playlist = new ArrayList<Song>();
	}
	
	//Adds a Song to a Playlist.
	public void addSong(Song song)
	{
		playlist.add(song);
	}
	
	//Returns the size of a Playlist.
	public int getPlaylistSize()
	{
		return playlist.size();
	}
    }
