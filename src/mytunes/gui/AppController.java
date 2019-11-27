/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import mytunes.be.Song;
import mytunes.bll.*;

/**
 *
 * @author domin
 */
public class AppController implements Initializable {

    @FXML
    private ImageView btnPlay;
    @FXML
    private Button btnNewPlst;
    @FXML
    private Button btnDeleteSOP;
    @FXML
    private Button btnDeletePlst;
    @FXML
    private Button btnEditPlst;
    @FXML
    private Button btnMoveUp;
    @FXML
    private Button btnNewSongs;
    @FXML
    private Button btnDeleteSongs;
    @FXML
    private Button btnEditSongs;
    @FXML
    private Button btnClose;
    @FXML
    private TableView<?> lstPlaylists;
    @FXML
    private ListView<?> lstSOP;
    @FXML
    private TableView<?> lstSongs;
    @FXML
    private TextField txtInput;
    @FXML
    private ImageView btnFind;
    @FXML
    private Button btnMoveBack;
    @FXML
    private ImageView btnPreviousSong;
    @FXML
    private ImageView btnNextSong;
    @FXML
    private ImageView btnMoveDown;
    @FXML
    private ImageView btnAddToPL;
    @FXML
    private Label lblIsPlaying;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}