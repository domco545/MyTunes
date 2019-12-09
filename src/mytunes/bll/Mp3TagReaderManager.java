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
import mytunes.dal.Mp3TagReaderV1;
import mytunes.dal.Mp3TagReaderV2;


/**
 *
 * @author domin
 */
public class Mp3TagReaderManager {
    Object mptag;
    String path;
    
    public Mp3TagReaderManager(String path) throws IOException, UnsupportedTagException, InvalidDataException {
        Mp3File mp3 = new Mp3File(path);
        if(mp3.hasId3v1Tag()){
            mptag = new Mp3TagReaderV1(path);
        }else if(mp3.hasId3v2Tag()){
            mptag = new Mp3TagReaderV2(path);
        }else{
            throw new UnsupportedTagException();
        }   
    }
}
