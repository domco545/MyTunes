/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import mytunes.be.Playlist;
import mytunes.be.Song;
import mytunes.bll.BllFacade;
import mytunes.bll.BllManager;


/**
 *
 * @author domin
 */
public class AppController implements Initializable {
    BllManager bll = new BllManager();
    private BllFacade bllfacade = new BllManager();
    private ObservableList<Song> obsSongs = FXCollections.observableArrayList(bllfacade.getAllSongs());
    private ObservableList<Playlist> obsPlaylists = FXCollections.observableArrayList(bll.getAllPlaylists());
    private ObservableList<Song>  obsSOP ; 
   
    private int selectedPlaylistId;
    private int selectedSongId;
    private boolean inSearch;
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
    private Button btnMoveDown;
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
    @FXML
    private Button addToPlaylist;
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        init();
        
    }   
    // Handles the button for New Playlist
    // A new fxml file pops up after the click on the button
    // After that the Newplaylist.fxml closes it reloads the Playlists
    @FXML
    private void newPlaylist(ActionEvent event){
        Parent root;
        try{
            root = FXMLLoader.load(getClass().getResource("NewPlaylist.fxml"));
            Stage stage = new Stage();
           
            stage.setTitle("New/Edit Playlist");
            stage.setScene(new Scene(root, 350,250));
            stage.show();
            stage.setOnHiding(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    bllfacade.reloadPlaylists();
                    init();
                }
            });
        }catch(IOException e){e.printStackTrace();}
    }
    // On click event it deletes the selected song on a playlist, then it reloads them
    @FXML
    private void deleteSongOnPlaylist(ActionEvent event) {
        bllfacade.deleteSongOnPlaylist(
                lstPlaylists.getSelectionModel().getSelectedItem().getId(), 
                lstSOP.getSelectionModel().getSelectedItem().getId()
        );
        bllfacade.reloadPlaylists();
        init();
    }
    // Deletes a playlist and then it reloads them
    @FXML
    private void deletePlaylist(ActionEvent event) {
        
        bllfacade.deletePlaylist(lstPlaylists.getSelectionModel().getSelectedItem());
        bll.reloadPlaylists();
                    init();
    }
    // Handles the event for the Edit Playlist button
    // New window pops up
    // After the event on the EditPlaylist.fxml it reloads the Playlists
    @FXML
    private void editPlaylist(ActionEvent event) {
        Parent root;
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EditPlaylist.fxml"));
            root = loader.load();
            Stage stage = new Stage();
            
            EditPlaylistController epc = loader.getController();
            epc.acceptPlaylist(lstPlaylists.getSelectionModel().getSelectedItem());
           
            stage.setTitle("Edit Playlist");
            stage.setScene(new Scene(root, 350,250));
            stage.show();
            stage.setOnHiding(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    bllfacade.reloadPlaylists();
                    init();
                }
            });
        }catch(IOException e){e.printStackTrace();}
        
    }
    // Handles the NewSong button
    // New window pops up
    // After the windowevent it reloads everything
    @FXML
    private void newSong(ActionEvent event) {
         Parent root; 
        try{
            root = FXMLLoader.load(getClass().getResource("NewSong.fxml"));
            Stage stage = new Stage();
           
            stage.setTitle("New Song");
            stage.setScene(new Scene(root, 550,450));
            stage.show();
            stage.setOnHiding(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    bllfacade.reloadSongs();
                    init();
                }
            });
        } catch (IOException ex) {
            Logger.getLogger(AppController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // Deletes the selected Song from everywhere
    // Reloads the gui
    @FXML
    private void deleteSong(ActionEvent event) {
        bllfacade.deleteById(lstSongs.getSelectionModel().getSelectedItem());
        bllfacade.reloadPlaylists();
        bllfacade.reloadSongs();
        init();
    }
    // Handles the New Song button
    // New window pops up 
    // After the window is closed it reloads the gui
    @FXML
    private void editSong(ActionEvent event) {
         Parent root;
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EditSong.fxml"));
            root = loader.load();
            Stage stage = new Stage();
            
            EditSongController esc = loader.getController();
            esc.acceptSong(lstSongs.getSelectionModel().getSelectedItem());
           
            stage.setTitle("Edit Song");
            stage.setScene(new Scene(root, 550,450));
            stage.show();
            stage.setOnHiding(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    bllfacade.init();
                    
                    init();
                }
            });
        }catch(IOException e){e.printStackTrace();}
        
    }
    // Closes the Main gui
    @FXML
    private void closeWindow(ActionEvent event) {
        Platform.exit();
        System.exit(0);  
    }
    // Handles the Filter button
    // If there is text in the input and the button is clicked then it loads the data based on the querrySongs method
    @FXML
    private void handleFilter(ActionEvent event) {
        
        Image imageC = new Image(getClass().getResourceAsStream("/icons/button1.png"));
        Image imageF = new Image(getClass().getResourceAsStream("/icons/button4.png"));

        if(!inSearch){
        btnFind.setImage(imageC);
        //query here
        String querry = txtInput.getText();
        if(querry !=null){
        obsSongs = FXCollections.observableArrayList(bllfacade.querrySongs(querry));
        lstSongs.setItems(obsSongs);
        }
        inSearch = true;
        }else{
        btnFind.setImage(imageF); 
        txtInput.setText("");
        init();
        inSearch = false;
        }
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
    // Moves a song up on a current playlist if it is not the first one 
     @FXML
    private void moveSongUp(ActionEvent event) {
         
        Song songToMove = lstSOP.getSelectionModel().getSelectedItem();
        int index = lstSOP.getSelectionModel().getSelectedIndex();
        Song songAbove = lstSOP.getItems().get(index-1);
        
        if(index > 0){
        lstSOP.getItems().set(index-1, songToMove);
        lstSOP.getItems().set(index, songAbove);
        }
        
    }
    // Moves a song down if it is not the last one on the playlist
    @FXML
    private void moveSongDown(ActionEvent event) {
        
        int index = lstSOP.getSelectionModel().getSelectedIndex();
        Song songToMove = lstSOP.getSelectionModel().getSelectedItem();
        Song songBelow = lstSOP.getItems().get(index+1);
        
        int size = obsSOP.size();
        if(index < size){
        lstSOP.getItems().set(index+1, songToMove);
        lstSOP.getItems().set(index, songBelow);
       
        }
    }
    // It moves the selected song to the selected playlist and reloads them 
    @FXML
    private void moveSongToPlaylist(ActionEvent event) {
        bllfacade.addSongToPlaylist(selectedPlaylistId, selectedSongId);
        bllfacade.reloadPlaylists();
        init();
    }
    // Fills up the observable list with the songs on the playlists
    @FXML
    private void fiilSOPm(MouseEvent event) {
        obsSOP= FXCollections.observableArrayList(lstPlaylists.getSelectionModel().getSelectedItem().getAllSongsOnPlaylist());
        selectedPlaylistId = lstPlaylists.getSelectionModel().getSelectedItem().getId();
        lstSOP.setItems(obsSOP);
        
    }
    
    @FXML
    private void fiilSOPk(KeyEvent event) {
        obsSOP= FXCollections.observableArrayList(lstPlaylists.getSelectionModel().getSelectedItem().getAllSongsOnPlaylist());
        selectedPlaylistId = lstPlaylists.getSelectionModel().getSelectedItem().getId();
        lstSOP.setItems(obsSOP);
    }
    // Sets the collumns of the tableviews
    public void init(){
        obsSongs = FXCollections.observableArrayList(bllfacade.getAllSongs());
        obsPlaylists = FXCollections.observableArrayList(bllfacade.getAllPlaylists());
        
               
        ClTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        ClArtist.setCellValueFactory(new PropertyValueFactory<>("artist"));
        ClCategory.setCellValueFactory(new PropertyValueFactory<>("genre"));
        ClTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        lstSongs.setItems(obsSongs);  
        
        ClName.setCellValueFactory(new PropertyValueFactory<>("name"));
        ClSongs.setCellValueFactory(new PropertyValueFactory<>("totalSongs"));
        ClPTime.setCellValueFactory(new PropertyValueFactory<>("totalTime"));
        lstPlaylists.setItems(obsPlaylists);
    }

    @FXML
    private void selectSongK(KeyEvent event) {
        selectedSongId = lstSongs.getSelectionModel().getSelectedItem().getId();
    }

    @FXML
    private void selectSongM(MouseEvent event) {
        selectedSongId = lstSongs.getSelectionModel().getSelectedItem().getId();
    }
}
    
