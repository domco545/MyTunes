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
    private Genre genre;
    private int time;
    private String path;

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getGenre() {
        return genre.getName();
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
    
    public int getGenreId(){
        return genre.getId();
    }
    public void setGenreName(String name)
    {
        genre.setName(name);
    }

    public Song(int id,String title, String artist, Genre genre, int time, String path) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.time = time;
        this.path = path;
    }

    @Override
    public String toString() {
        return title + " " +  artist + " " + time;
    }
    
   
    
   
}
