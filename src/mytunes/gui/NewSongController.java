/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.gui;

import static java.lang.Integer.parseInt;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import mytunes.be.Genre;
import mytunes.be.Playlist;
import mytunes.be.Song;
import mytunes.bll.BllFacade;
import mytunes.bll.BllManager;

/**
 * FXML Controller class
 *
 * @author narma
 */
public class NewSongController implements Initializable {
    BllFacade bllfacade = new BllManager(); 
    private ObservableList<Song> obsSongs = FXCollections.observableArrayList(bllfacade.getAllSongs());
      private ObservableList<Genre> obsGenre = FXCollections.observableArrayList(bllfacade.loadGenres());
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
    private ChoiceBox<Genre> txtGenreInput;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
       txtGenreInput.getItems().addAll(obsGenre);
    }    

    @FXML
    private void handleCancelNewSong(ActionEvent event) {
                ((Node)(event.getSource())).getScene().getWindow().hide();

    }

    @FXML
    private void handleSaveNewSong(ActionEvent event) {
        if(txtNewSong!=null && txtNewArtist!=null && txtNewSongTime!=null && txtNewSongFile!=null)
        bllfacade.createSong(txtNewSong.getText(),txtNewArtist.getText(),txtGenreInput.getSelectionModel().getSelectedItem(),parseInt(txtNewSongTime.getText()),txtNewSongFile.getText());
        handleCancelNewSong(event);
    }

    @FXML
    private void handleChoosePath(ActionEvent event) {
    }

    @FXML
    private void show(MouseEvent event) {
         
    }

    
}
