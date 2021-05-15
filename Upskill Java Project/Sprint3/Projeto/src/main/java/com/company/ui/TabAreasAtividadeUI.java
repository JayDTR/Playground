/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.ui;

import com.company.controller.RegistarAreaAtividadeController;
import com.company.model.AreaAtividade;
import com.company.utils.Constantes;
import com.company.utils.TemaApp;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
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
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author joaor
 */
public class TabAreasAtividadeUI implements Initializable {
    
    private TemaApp tema = TemaApp.getInstance();


    private MainSceneUI mainSceneUI;
    private Stage novaAreaTarefaStage;

    @FXML
    private TableView<AreaAtividade> tableViewTarefas;
    @FXML
    private TableColumn<AreaAtividade, String> tableViewAreaAtividadeCodUnico;
    @FXML
    private TableColumn<AreaAtividade, String> tableViewAreaAtividadeDescBreve;
    @FXML
    private TableColumn<AreaAtividade, String> tableViewAreaAtividadeDescDetalhada;
    @FXML
    private Button btnAdicionarNovaTarefa;

    private RegistarAreaAtividadeController aaCtrl;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        aaCtrl = new RegistarAreaAtividadeController();
        loadTabs();

        try {
            updateList();
        } catch (SQLException ex) {
            Logger.getLogger(TabAreasAtividadeUI.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void actionAdicionarNovaTarefa(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Constantes.REGISTO_AREAATIVIDADE_FILE));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            novaAreaTarefaStage = new Stage();
            novaAreaTarefaStage.initModality(Modality.APPLICATION_MODAL);
            novaAreaTarefaStage.setTitle(Constantes.REGISTO_AREAATIVIDADE_TITLE);
            novaAreaTarefaStage.setResizable(false);
            
            if(tema.isEnable()) {
                scene.getStylesheets().add(Constantes.CSS_DARK_THEME);
            } else {
                scene.getStylesheets().add(Constantes.CSS_LIGHT_THEME);
            }
            
            novaAreaTarefaStage.setScene(scene);

            AdicionarAreaAtividadeUI adicionarAreaAtividadeUI = loader.getController();
            adicionarAreaAtividadeUI.associarParentUI(this);

            novaAreaTarefaStage.show();
        } catch (IOException ex) {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, App.TITULO_APLICACAO, "Erro.", ex.getMessage());
        }
    }

    void associarParentUI(MainSceneUI mainSceneUI) {
        this.mainSceneUI = mainSceneUI;
    }

    public void updateList() throws SQLException {

        List<AreaAtividade> tmp = new ArrayList<>();
        tmp = aaCtrl.getListAreasAtividade();

        if (tmp != null) {
            ObservableList<AreaAtividade> AAList = FXCollections.observableArrayList(tmp);
            tableViewTarefas.setItems(AAList);
        }
    }

    private void loadTabs() {
        tableViewAreaAtividadeCodUnico.setCellValueFactory(new PropertyValueFactory<AreaAtividade, String>("id"));
        tableViewAreaAtividadeDescBreve.setCellValueFactory(new PropertyValueFactory<AreaAtividade, String>("descBreve"));
        tableViewAreaAtividadeDescDetalhada.setCellValueFactory(new PropertyValueFactory<AreaAtividade, String>("descDetalhada"));
    }

}
