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
import mytunes.dal.PlaylistDBDAO;


/**
 *
 * @author domin
 */
public class BllManager {
    List<Genre> genreList = new ArrayList();
    List<Song> songList = new ArrayList();
    List<Playlist> playlistList = new ArrayList();
    PlaylistDBDAO pldb = new PlaylistDBDAO();

    public BllManager() {
        
        playlistList = pldb.getAllPlaylists();
        
    }
}
