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
public class BllManager implements BllFacade {
    List<Genre> genreList = new ArrayList();
    List<Song> songList = new ArrayList();
    List<Playlist> playlistList = new ArrayList();
    
    PlaylistDBDAO pldb = new PlaylistDBDAO();
    SongDBDAO sdb = new SongDBDAO();
    GenreDBDAO gdb = new GenreDBDAO();

    public BllManager() {
        init();
    }
    
    @Override
    public void init(){
        genreList = gdb.loadGenres();
        songList = sdb.getAllSongs();
        playlistList = pldb.getAllPlaylists();
    }
    
    @Override
    public void reloadGenre(){
        genreList = gdb.loadGenres();
    }
    
    @Override
    public void reloadPlaylists(){
        playlistList = pldb.getAllPlaylists();
    }
    
    @Override
    public List<Playlist> getAllPlaylists(){
        return playlistList;
    }
    
    @Override
    public void createPlaylist(String name)
    {
        pldb.createPlaylist(name);
    }

    @Override
    public List<Song> getAllSongs() {
        return songList;
    }

    @Override
    public void createSong(String title, String artist, Genre genre, int time, String path) {
        sdb.createSong(title, artist, genre, time, path);
    }

    @Override
    public void editSong(Song song) {
      sdb.editSong(song);
    }

    @Override
    public void deleteById(Song song) {
     sdb.deleteById(song);
    }

    @Override
    public List<Playlist> loadPlaylists() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Genre> loadGenres() {
        return genreList;
    }

    @Override
    public void updatePlaylist(Playlist playlist) {
        pldb.updatePlaylist(playlist);
    }

    @Override
    public void deletePlaylist(Playlist playlist) {
        pldb.deletePlaylist(playlist);
    }

    @Override
    public void addSongToPlaylist(int plId, int sId) {
        pldb.addSongToPlaylist(plId, sId);
    }

    public void deleteSongOnPlaylist(int plId, int sId){
        pldb.deleteSongOnPlaylist(plId, sId);
    }
    @Override
    public void reloadSongs() {
        songList = sdb.getAllSongs();
    }

    @Override
    public List<Song> querrySongs(String querry) {
        return sdb.querrySongs(querry);
    }
    
}
 
