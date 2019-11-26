/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.be;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author domin
 */
public class Genre {

    public Genre(int id, String name) {
        this.id = id;
        this.name = name;
    }
    private int id;
    private String name;

  

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    
    

    public ArrayList<Genre> getAllGenre() {
        List<Genre> genre = new ArrayList<Genre>();
        genre.add(1,"pop");
        genre.add("rock");
        genre.add("techno");
        genre.add("rap");
        return (genre);
    }
}