/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import mytunes.be.Song;
import mytunes.bll.BllFacade;
import mytunes.bll.BllManager;

/**
 * FXML Controller class
 *
 * @author narma
 */
public class DeleteWindow2Controller implements Initializable {
private BllFacade bllfacade = new BllManager();
    @FXML
    private Button YesButton;
    @FXML
    private Button NoButton;
Song song;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void acceptSong(Song song){
        this.song = song;
        
    }
    @FXML
    private void deleteSong(ActionEvent event) {
        bllfacade.deleteById(song);
        bllfacade.reloadPlaylists();
        bllfacade.reloadSongs();
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    private void noDelete(ActionEvent event) {
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
    
}
