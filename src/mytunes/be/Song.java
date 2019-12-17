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
//Song class, contains getters and setters and the method for converting the time 
public class Song {
    private int id;
    private String title;
    private String artist;
    private Genre genre;
    private int time;
    private String path;
    private int position;

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

    public void setPosition(int position) {
        this.position = position;
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

    public String getTime() {
        return convertTimeToString();
    }
    
    public int getTimeInInt(){
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

    public int getPosition() {
        return position;
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
    
    private String convertTimeToString(){
        int seconds = (int) (time / 1000) % 60 ;
        int minutes = (int) ((time / (1000*60)) % 60);
        int hours   = (int) ((time / (1000*60*60)) % 24);
        return hours+":"+minutes+":"+seconds;
    
    }

    @Override
    public String toString() {
        return title + " " +  artist + " " + convertTimeToString();
    }
    
   
    
   
}
