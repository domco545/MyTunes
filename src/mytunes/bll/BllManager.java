/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.bll;

import java.util.ArrayList;
import java.util.List;
import mytunes.be.Genre;
import mytunes.be.Playlist;
import mytunes.be.Song;
import mytunes.dal.GenreDBDAO;
import mytunes.dal.PlaylistDBDAO;
import mytunes.dal.SongDBDAO;


/**
 *
 * @author domin
 */
public class BllManager {
    List<Genre> genreList = new ArrayList();
    List<Song> songList = new ArrayList();
    List<Playlist> playlistList = new ArrayList();
    
    PlaylistDBDAO pldb = new PlaylistDBDAO();
    SongDBDAO sdb = new SongDBDAO();
    GenreDBDAO gdb = new GenreDBDAO();

    public BllManager() {
        init();
    }
    
    public void init(){
        genreList = gdb.loadGenres();
        songList = sdb.getAllSongs();
        playlistList = pldb.getAllPlaylists();
    }
    
    public void reloadGenre(){
        genreList = gdb.loadGenres();
    }
    
    public void reloadPlaylists(){
        playlistList = pldb.getAllPlaylists();
    }
    
    public List<Song> getAllSongs(){
        return songList;
    }
    
    public List<Playlist> getAllPlaylists(){
        return playlistList;
    }
}
