/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.ui;

import com.company.controller.EspecificarSeriacaoController;
import com.company.model.Classificacao;
import com.company.model.ProcessoSeriacao;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author joaor
 */
public class TabSeriacaoUI implements Initializable {

    private MainSceneUI mainSceneUI;
    private Stage novaSeriacaoStage;
    private EspecificarSeriacaoController sCtrl;

    @FXML
    private TableView<ProcessoSeriacao> tableViewAnunciosSeriac;
    @FXML
    private TableColumn<ProcessoSeriacao, String> tableViewAnunciosSeriacData;
    @FXML
    private TableColumn<ProcessoSeriacao, String> tableViewAnunciosSeriacNifColab;
    @FXML
    private TableColumn<ProcessoSeriacao, String> tableViewAnunciosSeriacidAnuncio;
    @FXML
    private TableView<Classificacao> tableViewClassific;
    @FXML
    private TableColumn<Classificacao, String> tableViewClassificPos;
    @FXML
    private TableColumn<Classificacao, String> tableViewClassificidCand;
    @FXML
    private TableColumn<Classificacao, String> tableViewClassifNifFreelancer;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadTabs();
        try {
            sCtrl = new EspecificarSeriacaoController();
            updateList();
        } catch (SQLException ex) {
            Logger.getLogger(TabTarefasUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void clickedAnuncio(MouseEvent event) {
        if(tableViewAnunciosSeriac.getSelectionModel().getSelectedItem()!=null)
        tableViewClassific.getItems().clear();
        loadClassific();
    }

    void associarParentUI(MainSceneUI mainSceneUI) {
        this.mainSceneUI = mainSceneUI;
        mainSceneUI.associarChildTabSeriacaoUI(this);
    }

    private void loadTabs() {
        tableViewAnunciosSeriacData.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ProcessoSeriacao, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ProcessoSeriacao, String> p) {
                return new SimpleStringProperty(p.getValue().getDataRealizacao().toString());
            }

        });
        tableViewAnunciosSeriacNifColab.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ProcessoSeriacao, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ProcessoSeriacao, String> p) {
                return new SimpleStringProperty(p.getValue().getColab().getEmail());
            }
        });
        tableViewAnunciosSeriacidAnuncio.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ProcessoSeriacao, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ProcessoSeriacao, String> p) {
                return new SimpleStringProperty(p.getValue().getAnuncio().getTarefa().getRef());
            }

        });
        tableViewClassificPos.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Classificacao, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Classificacao, String> p) {
                 return new SimpleStringProperty(p.getValue().getLugarClassificacao()+"º");
            }
        });
        tableViewClassificidCand.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Classificacao, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Classificacao, String> p) {
                SimpleStringProperty builtString = null;
                try {
                    builtString = new SimpleStringProperty("" + sCtrl.getCandId(p.getValue().getCandidatura()));
                } catch (SQLException ex) {
                    Logger.getLogger(TabSeriacaoUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                return builtString;
            }

        });
        tableViewClassifNifFreelancer.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Classificacao, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Classificacao, String> p) {
                return new SimpleStringProperty("Nome: " + p.getValue().getCandidatura().getFree().getNome() + "\n"
                        + "NIF: " + p.getValue().getCandidatura().getFree().getNif());
            }

        });

    }

    void updateList() throws SQLException {
        if (sCtrl.getListaSeriados() != null) {
            ObservableList<ProcessoSeriacao> serList = FXCollections.observableArrayList(sCtrl.getListaSeriados());
            tableViewAnunciosSeriac.setItems(serList);
        }
    }

    private void loadClassific() {
         try {
            List<Classificacao> tmp = new ArrayList<Classificacao>();
            tmp = sCtrl.getClassificacaoByProccSer(tableViewAnunciosSeriac.getSelectionModel().getSelectedItem());
            if (tmp != null && tmp.size() != 0) {
                ObservableList<Classificacao> classProfList = FXCollections.observableArrayList(tmp);
                tableViewClassific.setItems(classProfList);
            }
        } catch (Exception e) {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, App.TITULO_APLICACAO, "Erro no load de Graus Prof", e.getMessage());
        }
    }


}
