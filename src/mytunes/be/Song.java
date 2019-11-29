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

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public int getGenre() {
        return genre;
    }

    public int getTime() {
        return time;
    }

    public String getPath() {
        return path;
    }

    public int getId() {
        return id;
    }

    public Song(int id,String title, String artist, int genre, int time, String path) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.time = time;
        this.path = path;
    }

    @Override
    public String toString() {
        return title + " " +  artist + " " + genre + " " + time;
    }
    
   
    
   
}
