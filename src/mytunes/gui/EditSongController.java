/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.gui;

import static java.lang.Integer.parseInt;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import mytunes.be.Song;
import mytunes.bll.BllFacade;
import mytunes.bll.BllManager;

/**
 * FXML Controller class
 *
 * @author narma
 */
public class EditSongController implements Initializable {
private BllFacade bllfacade = new BllManager();
Song song;
    @FXML
    private TextField txtNewSong;
    @FXML
    private TextField txtNewArtist;
    @FXML
    private TextField txtNewSongFile;
    @FXML
    private Button btnCancelNewSong;
    @FXML
    private Button btnSaveNewSong;
    @FXML
    private Button btnChoose;
    @FXML
    private TextField txtNewSongTime;
    @FXML
    private TextField txtGenreInput;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void acceptSong(Song song){
        this.song = song;
        txtNewSong.setText(song.getTitle());
        txtNewArtist.setText(song.getArtist());
        txtGenreInput.setText(song.getGenre());
       txtNewSongTime.setText(String.valueOf(song.getTime()));
        txtNewSongFile.setText(song.getPath());
        
        
    }
    @FXML
    private void handleCancelNewSong(ActionEvent event) {
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    private void handleSaveNewSong(ActionEvent event) {
        song.setTitle(txtNewSong.getText());
        song.setArtist(txtNewArtist.getText());
        song.setGenreName(txtGenreInput.getText());
        song.setTime(parseInt(txtNewSongTime.getText()));
        song.setPath(txtNewSongFile.getText());
            bllfacade.editSong(song);  
        handleCancelNewSong(event);
    }

    @FXML
    private void handleChoosePath(ActionEvent event) {
    }

    
    
}
