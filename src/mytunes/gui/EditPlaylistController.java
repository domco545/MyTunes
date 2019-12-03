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
import javafx.scene.control.TextField;
import mytunes.bll.BllFacade;
import mytunes.bll.BllManager;

/**
 * FXML Controller class
 *
 * @author narma
 */
public class EditPlaylistController implements Initializable {
private BllFacade bllfacade = new BllManager();
    @FXML
    private TextField txtNewPlaylist;
    @FXML
    private Button cancelEditPlaylist;
    @FXML
    private Button saveEditPlaylist;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void cancel(ActionEvent event) {
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    private void update(ActionEvent event) {
        if(txtNewPlaylist!=null)
        bllfacade.updatePlaylist(txtNewPlaylist.getText());       
        cancel(event);   
    }
    
}
