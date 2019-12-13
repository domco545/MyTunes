/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.dal;

import com.mpatric.mp3agic.ID3v1;
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
public class Mp3TagReaderV1 implements Mp3TagReaderInterface{
   private Mp3File mp3file;
   
    public Mp3TagReaderV1(String filepath) {
        try{
            mp3file = new Mp3File(filepath);
        } catch (IOException ex) {
            Logger.getLogger(Mp3TagReaderV1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedTagException ex) {
            Logger.getLogger(Mp3TagReaderV1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidDataException ex) {
            Logger.getLogger(Mp3TagReaderV1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public int getLength(){
       int time = (int) mp3file.getLengthInMilliseconds();
       return time;
    }  
    
    @Override
    public String getName(){
        return mp3file.getFilename();
    }

    @Override
    public String getArtist() {
        if(mp3file.getId3v1Tag().getArtist() == null){
            return "";
        }else{
            return mp3file.getId3v1Tag().getArtist();
        }
    }

    @Override
    public String getGenre() {
        return mp3file.getId3v1Tag().getGenreDescription();
    }
}
