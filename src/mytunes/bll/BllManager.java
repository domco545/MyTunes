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

/**
 *
 * @author domin
 */
public class BllManager {
    List<Genre> genreList = new ArrayList();
    List<Song> songList = new ArrayList();
    List<Playlist> playlistList = new ArrayList();

    public ArrayList<String> genre() {
        ArrayList<String> genre = new ArrayList<String>();
        genre.add("pop");
        genre.add("rock");
        genre.add("techno");
        genre.add("rap");
        return (genre);
    }
    
    

}
