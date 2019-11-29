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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
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
    @FXML
    private TableColumn<Song,String> ClTitle;
    @FXML
    private TableColumn<Song, String> ClArtist;
    @FXML
    private TableColumn<Song,Integer> ClCategory;
    @FXML
    private TableColumn<Song, Integer> ClTime;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lstSOP.setItems(obsSongs);
       
        ClTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        ClArtist.setCellValueFactory(new PropertyValueFactory<>("artist"));
        ClCategory.setCellValueFactory(new PropertyValueFactory<>("genre"));
        ClTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        
        lstSongs.setItems(obsSongs);
        
        
    }
     public ObservableList<Song> generateTableViewRows() {
        
        ObservableList<Song> tableViewRows = FXCollections.observableArrayList();
            for (Song row : tableViewRows) {
                int id = row.getId();
                String title = row.getTitle();
                String artist= row.getArtist();
                int category = row.getGenre();
                int time = row.getTime();
                String path = row.getPath();
            tableViewRows.setAll(row);    
            }
            
            return tableViewRows;
        }
        
    }
    
