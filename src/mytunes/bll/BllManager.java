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
    

}
