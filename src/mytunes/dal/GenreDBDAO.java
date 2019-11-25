/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.dal;
import java.util.ArrayList;

/**
 *
 * @author domin
 */
public class GenreDBDAO {

    private int id;    
    ArrayList<String> genre = new ArrayList<String>();

    public ArrayList<String> genre() {
        genre.add("pop");
        genre.add("rock");
        genre.add("techno");
        genre.add("rap");
        return (genre);
    }
}
