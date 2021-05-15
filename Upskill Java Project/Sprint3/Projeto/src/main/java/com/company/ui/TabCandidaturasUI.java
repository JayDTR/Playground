/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.ui;

import com.company.controller.EfetuarCandidaturaController;
import com.company.model.Anuncio;
import com.company.model.Candidatura;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;


/**
 * FXML Controller class
 *
 * @author Asus
 */
public class TabCandidaturasUI implements Initializable {

    private MainSceneUI mainSceneUI;
    private Stage novaCandidaturaStage;
    
    @FXML
    private TableView<Candidatura> tableViewCandidatura;
    @FXML
    private TableColumn<Candidatura, String> tableViewIdAnuncio;
    @FXML
    private TableColumn<Candidatura, String> tableViewDataCand;
    @FXML
    private TableColumn<Candidatura, String> tableViewValorPretendido;
    @FXML
    private TableColumn<Candidatura, String> tableViewNrDias;
    
    private EfetuarCandidaturaController candCtrl;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadColumns();

        try {
            updateList();
        } catch (SQLException ex) {
            Logger.getLogger(TabAnunciosUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    
    void associarParentUI(MainSceneUI mainSceneUI) {
        this.mainSceneUI = mainSceneUI;
        mainSceneUI.associarChildTabCandidaturaUI(this);
    }
    
    private void loadColumns() {
         tableViewIdAnuncio.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Candidatura, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Candidatura, String> p) {
                return new SimpleStringProperty(p.getValue().getAnuncio().getTarefa().getRef());
            }

        });
        tableViewDataCand.setCellValueFactory(new PropertyValueFactory<Candidatura, String>("dtCandidatura"));
        tableViewValorPretendido.setCellValueFactory(new PropertyValueFactory<Candidatura, String>("valorPretendido"));
        tableViewNrDias.setCellValueFactory(new PropertyValueFactory<Candidatura, String>("nrDias"));
    }
    
    public void updateList() throws SQLException {
        candCtrl = new EfetuarCandidaturaController();
        if (candCtrl.getAllCandidaturas() != null) {
            ObservableList<Candidatura> list = FXCollections.observableArrayList(candCtrl.getAllCandidaturas());
            tableViewCandidatura.setItems(list);
        }
    }
}
