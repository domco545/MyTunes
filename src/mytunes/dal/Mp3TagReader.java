/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.dal;

import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author domin
 */
public class Mp3TagReader {
    Mp3File mp3file;
    public Mp3TagReader(String filepath) {
        try{
            mp3file = new Mp3File(filepath);
        } catch (IOException ex) {
            Logger.getLogger(Mp3TagReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedTagException ex) {
            Logger.getLogger(Mp3TagReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidDataException ex) {
            Logger.getLogger(Mp3TagReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int getLength(){
       int time = (int) mp3file.getLengthInMilliseconds();
       return time;
    }  
    
    public String getName(){
        return mp3file.getFilename();
    }
    
    public static void main(String[] args) {
        Mp3TagReader t = new Mp3TagReader("");
        System.out.println(t.getLength());
        System.out.println(t.getName());
        
    }
}
