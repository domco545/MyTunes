/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.gui.controller;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.stage.FileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import mytunes.be.Genre;
import mytunes.be.Playlist;
import mytunes.be.Song;
import mytunes.bll.BllFacade;
import mytunes.bll.BllManager;
import mytunes.bll.Mp3TagReaderManager;
import mytunes.bll.WavReaderManager;

/**
 * FXML Controller class
 *
 * @author narma
 */
public class NewSongController implements Initializable {
    BllFacade bllfacade = new BllManager(); 
    //private ObservableList<Song> obsSongs = FXCollections.observableArrayList(bllfacade.getAllSongs());
      private ObservableList<Genre> obsGenre = FXCollections.observableArrayList(bllfacade.loadGenres());
      private boolean readyToSave;
      
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
    @FXML
    private Label lblError;

    /**
     * Initializes the controller class.
     */
    // Fills up the choicebox
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
       txtGenreInput.getItems().addAll(obsGenre);
       txtNewSongFile.setDisable(true);
       txtNewSongTime.setDisable(true);
    }    
    // Closes the window
    @FXML
    private void handleCancelNewSong(ActionEvent event) {
                ((Node)(event.getSource())).getScene().getWindow().hide();

    }
    // Saves the new song
    @FXML
    private void handleSaveNewSong(ActionEvent event) {
        if(txtNewSong!=null && txtNewArtist!=null && txtNewSongTime!=null && txtNewSongFile!=null && txtGenreInput.getSelectionModel().getSelectedItem() !=null &&readyToSave==true){
        bllfacade.createSong(txtNewSong.getText(),txtNewArtist.getText(),txtGenreInput.getSelectionModel().getSelectedItem(),parseInt(txtNewSongTime.getText()),txtNewSongFile.getText());
        handleCancelNewSong(event);
        }else{
            lblError.setText("every field need to be filled");
        }
    }

    @FXML
    private void handleChoosePath(ActionEvent event) {
        FileChooser fc = new FileChooser();
        File file = fc.showOpenDialog(null);
        
        if(file.getAbsolutePath().endsWith(".mp3") || file.getAbsolutePath().endsWith(".wav")){
            Path path = Paths.get(file.getAbsolutePath());
            Path pathbase = Paths.get("").toAbsolutePath();
            Path relative = pathbase.relativize(path);
            
            if(relative.toString().contains("src\\songs\\")){
                txtNewSongFile.setText(relative.toString());
                try {
                    if(file.getAbsolutePath().endsWith(".mp3")){populateMp3(relative.toString());}
                    else{populateWav(relative.toString());}
                } catch (IOException ex) {
                    Logger.getLogger(NewSongController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedTagException ex) {
                    Logger.getLogger(NewSongController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InvalidDataException ex) {
                    Logger.getLogger(NewSongController.class.getName()).log(Level.SEVERE, null, ex);
                }
                readyToSave = true;
            }else{
                //didnt have time to do proper error displaying :D
                lblError.setText("Songs have to be in src/songs/ directory");
                readyToSave = false;
            }
        }else{
            //didnt have time to do proper error displaying :D
            lblError.setText("File format not supported");
            readyToSave = false;
        }
        
    }

    @FXML
    private void show(MouseEvent event) {
         
    }
    
    private void populateMp3(String path) throws IOException, UnsupportedTagException, InvalidDataException{
        Mp3TagReaderManager mp3m = new Mp3TagReaderManager(path);
        txtNewSongTime.setText(Integer.toString(mp3m.getLength()));
        //txtNewArtist.setText(mp3m.getArtist());
        txtNewSong.setText(mp3m.getName());
    }
    
    private void populateWav(String path){
        WavReaderManager wavm = new WavReaderManager(path);
        txtNewSongTime.setText(Integer.toString(wavm.getLength()));
    
    }

    
}
