/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.be;

import mytunes.dal.Songs_On_PlaylistDBDAO;

/**
 *
 * @author domin
 */
public class Songs_On_playlist {
    private int id;
    private int playlist_id;
    private int song_id;

    public Songs_On_playlist(int playlist_id, int song_id) {
        this.playlist_id = playlist_id;
        this.song_id = song_id;
    }
    
    public void setID(int id){
        this.id = id;
    }
    
//    public void assignID(){
//        Songs_On_PlaylistDBDAO sop = new Songs_On_PlaylistDBDAO();
//        this.id = sop.getNextID();
//    }

    @Override
    public String toString() {
        return "Songs_On_playlist{" + "playlist_id=" + playlist_id + ", song_id=" + song_id + '}';
    }
    
    
}
