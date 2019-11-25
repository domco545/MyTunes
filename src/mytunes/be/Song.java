/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.be;

/**
 *
 * @author domin
 */
public class Song {
    private int id;
    private String title;
    private String artist;
    private int genre;
    private int time;
    private String path;

    public Song(int id, String title, String artist, int genre, int time, String path) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.time = time;
        this.path = path;
    }
    
    
   
}
