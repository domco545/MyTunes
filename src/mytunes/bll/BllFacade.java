/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.bll;

import java.util.List;
import mytunes.be.Genre;
import mytunes.be.Playlist;
import mytunes.be.Song;

/**
 *
 * @author narma
 */
public interface BllFacade {
    
    public List<Song> getAllSongs();
    public void createSong(String title,String artist,Genre genre,int time,String path);
    public void editSong(Song song);
    public void deleteById(Song song);
    public void createPlaylist( String name);
    public List<Playlist> loadPlaylists();
    public List<Playlist> getAllPlaylists();
    public List<Genre> loadGenres();
    public void updatePlaylist(Playlist playlist);
    public void deletePlaylist(Playlist playlist);
    public void addSongToPlaylist(int plId, int sId);
    public void deleteSongOnPlaylist(int plId, int sId, int position);
    public void reloadPlaylists();
    public void reloadSongs();
    public void reloadGenre();
    public void init();
    public List<Song> querrySongs(String querry);
    public void songUp(int plId, int songId, int position);
    public void songDown(int plId, int songId, int position);
}
