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
import mytunes.be.Songs_On_playlist;
import mytunes.dal.SongDBDAO;
import mytunes.dal.Songs_On_PlaylistDBDAO;


/**
 *
 * @author domin
 */
public class BllManager {
    List<Genre> genreList = new ArrayList();
    List<Song> songList = new ArrayList();
    List<Playlist> playlistList = new ArrayList();
    List<Song> playlistSongs = new ArrayList();
    List<Songs_On_playlist> sop = new ArrayList<Songs_On_playlist>();

    public BllManager() {
        SongDBDAO p = new SongDBDAO();
        songList = p.getAllSongs();
        getAllSongsOnPlaylist(1);
        fillPlaylistList();
        
        
    }
    
    

    public void genre() {
        //ArrayList<Genre> genre = new ArrayList<Genre>();
        genreList.add(new Genre(1,"Pop"));
        genreList.add(new Genre(2,"Rock"));
        genreList.add(new Genre(3,"Techno"));
        genreList.add(new Genre(4,"Rap"));
    }
    public String getGenreName(int id)
    {
        for (Genre genre : genreList) {
            if(genre.getId() == id )
            {
                return genre.getName();
            }
        }
        
        return null;
    }
    
    public void getAllSongsOnPlaylist(int id){
        Songs_On_PlaylistDBDAO sopdb = new Songs_On_PlaylistDBDAO();
        sop = sopdb.getAllSongsInPlaylist(id);
    }
    
    public void fillPlaylistList(){
        for (Songs_On_playlist info : sop) {
            for (Song song : songList) {
                if(info.getSong_id() == song.getId()){
                    playlistSongs.add(song);
                }
            }
        }
    }
    
    public List<Song> getme(){
        return playlistSongs;
    }

}
