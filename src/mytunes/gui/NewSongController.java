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
import javafx.scene.control.Button;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author narma
 */
public class NewSongController implements Initializable {

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
    private Button btnMoreGenre;
    @FXML
    private SplitMenuButton menuGenres;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleCancelNewSong(ActionEvent event) {
    }

    @FXML
    private void handleSaveNewSong(ActionEvent event) {
    }

    @FXML
    private void handleChoosePath(ActionEvent event) {
    }

    @FXML
    private void handleMoreGenre(ActionEvent event) {
    }
    
}
