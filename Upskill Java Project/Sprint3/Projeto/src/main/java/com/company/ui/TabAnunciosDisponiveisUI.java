/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.ui;

import com.company.controller.EfetuarCandidaturaController;
import com.company.model.Anuncio;
import com.company.model.Candidatura;
import com.company.utils.Constantes;
import com.company.utils.TemaApp;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author joaor
 */
public class TabAnunciosDisponiveisUI implements Initializable {

    private TemaApp tema = TemaApp.getInstance();

    private MainSceneUI mainSceneUI;
    private EfetuarCandidaturaController candCtrl;
    private Stage novaCandidaturaStage;

    @FXML
    private TableView<Anuncio> tableViewAnuncio;
    @FXML
    private TableColumn<Anuncio, String> tableViewAnuncioId;
    @FXML
    private TableColumn<Anuncio, String> tableViewDesignacao;
    @FXML
    private TableColumn<Anuncio, String> tableViewDescInformal;
    @FXML
    private TableColumn<Anuncio, String> tableViewDescTecnica;
    @FXML
    private TableColumn<Anuncio, String> tableViewTipoRegimento;
    @FXML
    private Button btnCandidatarAnuncio;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadColumns();
        btnCandidatarAnuncio.setVisible(false);
        try {
            candCtrl = new EfetuarCandidaturaController();
            updateList();
        } catch (SQLException ex) {
            Logger.getLogger(TabAnunciosUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void deletePrev() {
        tableViewAnuncio.getItems().remove(tableViewAnuncio.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void actionCandidatarAnuncio(ActionEvent event) throws SQLException {
        if (!candCtrl.isFreelancerIlegivel(tableViewAnuncio.getSelectionModel().getSelectedItem(), candCtrl.getFreelancerByEmail())) {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, App.TITULO_APLICACAO, "Erro.", "Voce não está elegivel para este Anuncio").showAndWait();
        } else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(Constantes.EFETUAR_CAND_FILE));
                Parent root = loader.load();

                Scene scene = new Scene(root);

                novaCandidaturaStage = new Stage();
                novaCandidaturaStage.initModality(Modality.APPLICATION_MODAL);
                novaCandidaturaStage.setTitle(Constantes.EFETUAR_CAND_TITLE);
                novaCandidaturaStage.setResizable(false);

                if (tema.isEnable()) {
                    scene.getStylesheets().add(Constantes.CSS_MAIN_APP);
                } else {
                    scene.getStylesheets().clear();
                }

                novaCandidaturaStage.setScene(scene);

                AdicionarCandidaturaUI addCandUI = loader.getController();
                addCandUI.associarParentUI(this);

                novaCandidaturaStage.show();
            } catch (IOException ex) {
                AlertaUI.criarAlerta(Alert.AlertType.ERROR, App.TITULO_APLICACAO, "Erro.", ex.getMessage()).showAndWait();
            }
        }
    }

        void updateList() throws SQLException {
            if (candCtrl.getAllAnunciosDisponiveis() != null) {
                ObservableList<Anuncio> list = FXCollections.observableArrayList(candCtrl.getAllAnunciosDisponiveis());
                tableViewAnuncio.setItems(list);
            }
        }

        void updateListCandidaturas() throws SQLException {
            mainSceneUI.getTabCandidaturasUI().updateList();
        }

        Anuncio tableViewAnuncio() throws SQLException {
            return tableViewAnuncio.getSelectionModel().getSelectedItem();
        }

        void associarParentUI
        (MainSceneUI mainSceneUI
        
            ) {
        this.mainSceneUI = mainSceneUI;
        }

    

    private void loadColumns() {
        tableViewAnuncioId.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Anuncio, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Anuncio, String> p) {
                return new SimpleStringProperty(p.getValue().getTarefa().getRef());
            }

        });
        tableViewDesignacao.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Anuncio, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Anuncio, String> p) {
                return new SimpleStringProperty(p.getValue().getTarefa().getDesignacao());
            }

        });
        tableViewDescInformal.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Anuncio, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Anuncio, String> p) {
                return new SimpleStringProperty(p.getValue().getTarefa().getDescrInformal());
            }

        });
        tableViewDescTecnica.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Anuncio, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Anuncio, String> p) {
                return new SimpleStringProperty(p.getValue().getTarefa().getDescrTecnica());
            }
        });
        tableViewTipoRegimento.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Anuncio, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Anuncio, String> p) {
                return new SimpleStringProperty(p.getValue().getRegimento().getDesignacao());
            }
        });

    }

    @FXML
    private void clkAnuncio(MouseEvent event) throws SQLException {
        if (tableViewAnuncio.getSelectionModel().getSelectedItem() != null) {
            if (candCtrl.hasCandidatura(tableViewAnuncio.getSelectionModel().getSelectedItem())) {
                btnCandidatarAnuncio.setVisible(false);
            } else {
                btnCandidatarAnuncio.setVisible(true);
            }
        }
    }

}
