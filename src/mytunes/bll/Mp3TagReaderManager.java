/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.bll;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import java.io.IOException;
import mytunes.dal.Mp3TagReaderInterface;
import mytunes.dal.Mp3TagReaderV1;
import mytunes.dal.Mp3TagReaderV2;


/**
 *
 * @author domin
 */
public class Mp3TagReaderManager {
    Mp3TagReaderInterface tagReader;
    
    public Mp3TagReaderManager(String path) throws IOException, UnsupportedTagException, InvalidDataException {
        Mp3File mp3 = new Mp3File(path); 
        tagReader = getTagReader(mp3, path);
    }
    
    private Mp3TagReaderInterface getTagReader(Mp3File mp3,String path){
        if(mp3.hasId3v1Tag()){
        return new Mp3TagReaderV1(path);
        }else{
        return new Mp3TagReaderV2(path);
        }
    }
       
    public String getName(){
        return tagReader.getName();
    }
    
    public int getLength(){
        return tagReader.getLength();
    }
    
    public String getArtist(){
        return tagReader.getArtist();
    }
    
    public String getGenre(){
        return tagReader.getGenre();
    }
}
