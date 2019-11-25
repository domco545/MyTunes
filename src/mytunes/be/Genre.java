/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.be;

import java.util.ArrayList;

/**
 *
 * @author domin
 */
public class Genre {
    private int id;
    private String name;

    public Genre(String name, int id) {
        this.name = name;
        this.id = id;
    }  

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    
    ArrayList<String> genre = new ArrayList<String>();

    public ArrayList<String> genre() {
        genre.add("pop");
        genre.add("rock");
        genre.add("techno");
        genre.add("rap");
        return (genre);
    }
}