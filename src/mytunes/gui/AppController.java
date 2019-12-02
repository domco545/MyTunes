/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mytunes.be.Playlist;
import mytunes.be.Song;
import mytunes.bll.BllManager;

/**
 *
 * @author domin
 */
public class AppController implements Initializable {
    BllManager bll = new BllManager();
    private ObservableList<Song> obsSongs = FXCollections.observableArrayList(bll.getAllSongs());
    private ObservableList<Playlist> obsPlaylists = FXCollections.observableArrayList(bll.getAllPlaylists());
    private ObservableList<Song> obsSOP = FXCollections.observableArrayList();
   
    Stage window;
    Scene scene1;
    @FXML
    private Button btnPlay;
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
    private TableView<Playlist> lstPlaylists;
    @FXML
    private ListView<Song> lstSOP;
    @FXML
    private TableView<Song> lstSongs;
    @FXML
    private TextField txtInput;
    @FXML
    private ImageView btnFind;
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
    @FXML
    private TableColumn<Song,String> ClTitle;
    @FXML
    private TableColumn<Song, String> ClArtist;
    @FXML
    private TableColumn<Song,Integer> ClCategory;
    @FXML
    private TableColumn<Song, Integer> ClTime;
    @FXML
    private TableColumn<Playlist, String> ClName;
    @FXML
    private TableColumn<Playlist,Integer> ClSongs;
    @FXML
    private TableColumn<Playlist,Integer> ClPTime;
    @FXML
    private Button filter;
    @FXML
    private Button btnPrevious;
    @FXML
    private Button btnNext;
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       
        lstSOP.setItems(obsSongs);
       
        ClTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        ClArtist.setCellValueFactory(new PropertyValueFactory<>("artist"));
        ClCategory.setCellValueFactory(new PropertyValueFactory<>("genre"));
        ClTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        lstSongs.setItems(obsSongs);  
        
        ClName.setCellValueFactory(new PropertyValueFactory<>("name"));
        ClSongs.setCellValueFactory(new PropertyValueFactory<>("songs"));
        ClPTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        lstPlaylists.setItems(obsPlaylists);
    }   

    @FXML
    private void newPlaylist(ActionEvent event) {
    }

    @FXML
    private void deleteSongOnPlaylist(ActionEvent event) {
    }

    @FXML
    private void deletePlaylist(ActionEvent event) {
    }

    @FXML
    private void editPlaylist(ActionEvent event) {
    }

    @FXML
    private void moveSongUp(ActionEvent event) {
    }

    @FXML
    private void newSong(ActionEvent event) {
        VBox layout1= new VBox();
       
        scene1=new Scene(layout1);
        window.setScene(scene1);
        window.show();
    }

    @FXML
    private void deleteSong(ActionEvent event) {
    }

    @FXML
    private void editSong(ActionEvent event) {
    }

    @FXML
    private void closeWindow(ActionEvent event) {
      
        
    }

    @FXML
    private void handleFilter(ActionEvent event) {
    }

    @FXML
    private void previousSong(ActionEvent event) {
    }

    @FXML
    private void playSong(ActionEvent event) {
    }

    @FXML
    private void nextSong(ActionEvent event) {
    }

    @FXML
    private void moveSongDown(ActionEvent event) {
    }

    @FXML
    private void moveSongToPlaylist(ActionEvent event) {
    }

    }
    
