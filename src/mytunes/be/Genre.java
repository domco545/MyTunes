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

    @Override
    public String toString() {
        return id +","+ name;
    }
    
}