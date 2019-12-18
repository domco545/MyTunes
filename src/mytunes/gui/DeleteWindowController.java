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
import mytunes.be.Playlist;
import mytunes.bll.BllFacade;
import mytunes.bll.BllManager;

/**
 * FXML Controller class
 *
 * @author narma
 */
public class DeleteWindowController implements Initializable {
private BllFacade bllfacade = new BllManager();
    @FXML
    private Button YesButton;
    @FXML
    private Button NoButton;
    
    Playlist playlist;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    
 public void acceptPlaylist(Playlist playlist){
        this.playlist = playlist;
        
    }
    @FXML
    private void delete(ActionEvent event) {
        bllfacade.deletePlaylist(playlist);
        bllfacade.reloadPlaylists();
        ((Node)(event.getSource())).getScene().getWindow().hide();
        
    }

    @FXML
    private void noDelete(ActionEvent event) {
        ((Node)(event.getSource())).getScene().getWindow().hide();
        
    }
    
}
