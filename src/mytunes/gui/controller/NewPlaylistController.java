/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.gui.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
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
    // Closes the window
    @FXML
    private void cancel(ActionEvent event) {
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
    // Saves the new Playlist
    @FXML
    private void save(ActionEvent event) {
        if(txtNewPlaylist!=null)
        bll.createPlaylist(txtNewPlaylist.getText());       
        cancel(event);   
    }
    
}
