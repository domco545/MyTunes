/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import mytunes.be.Playlist;
import mytunes.bll.BllManager;

/**
 * FXML Controller class
 *
 * @author narma
 */
public class NewPlaylistController implements Initializable {
    BllManager bll = new BllManager();
private ObservableList<Playlist> obsPlaylists = FXCollections.observableArrayList(bll.getAllPlaylists());
    @FXML
    private TextField txtNewPlaylist;
    @FXML
    private Button cancelNewPlaylist;
    @FXML
    private Button saveNewPlaylist;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void cancel(ActionEvent event) {
        Platform.exit();
        System.exit(0);  
    }

    @FXML
    private void save(ActionEvent event) {
        
        
         Platform.exit();
         System.exit(0); 
    }
    
}
