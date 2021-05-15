/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.ui;

import com.company.controller.EspecificarColaboradorController;
import com.company.controller.EspecificarTarefaController;
import com.company.model.CategoriaTarefa;
import com.company.model.Colaborador;
import com.company.model.CompetenciaTecnica;
import com.company.model.Tarefa;
import com.company.ui.AdicionarTarefaUI;
import com.company.ui.AlertaUI;
import com.company.ui.App;
import com.company.ui.MainSceneUI;
import com.company.utils.Constantes;
import com.company.utils.TemaApp;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Map;
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
public class TabTarefasUI implements Initializable {

    private TemaApp tema = TemaApp.getInstance();

    private MainSceneUI mainSceneUI;
    private Stage novaTarefaStage;
    private Stage novoAnuncioStage;

    @FXML
    private TableView<Tarefa> tableViewTarefas;
    @FXML
    private TableColumn<Tarefa, String> tableViewTarefaDesignacao;
    @FXML
    private TableColumn<Tarefa, String> tableViewTarefaDescInformal;
    @FXML
    private TableColumn<Tarefa, String> tableViewDescricaoTecnica;
    @FXML
    private TableColumn<Tarefa, String> tableViewTarefaDuracao;
    @FXML
    private TableColumn<Tarefa, String> tableViewTarefasCusto;
    @FXML
    private TableColumn<Tarefa, String> tableViewTarefasCatTarefa;
    @FXML
    private Button btnPublicarTarefa;

    private EspecificarTarefaController cCtrl;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadTabs();
        btnPublicarTarefa.setVisible(false);

        try {
            cCtrl = new EspecificarTarefaController();
            updateList();
        } catch (SQLException ex) {
            Logger.getLogger(TabTarefasUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void actionAdicionarNovaTarefa(ActionEvent event) throws SQLException {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Constantes.REGISTO_TAREFA_FILE));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            novaTarefaStage = new Stage();
            novaTarefaStage.initModality(Modality.APPLICATION_MODAL);
            novaTarefaStage.setTitle(Constantes.REGISTO_TAREFA_TITLE);
            novaTarefaStage.setResizable(false);

            if (tema.isEnable()) {

                scene.getStylesheets().add(Constantes.CSS_DARK_THEME);
            } else {
                scene.getStylesheets().add(Constantes.CSS_LIGHT_THEME);
            }

            novaTarefaStage.setScene(scene);

            AdicionarTarefaUI adicionarTarefaUI = loader.getController();
            adicionarTarefaUI.associarParentUI(this, cCtrl);
            adicionarTarefaUI.updateCatTarCMB();

            novaTarefaStage.show();
        } catch (IOException ex) {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, App.TITULO_APLICACAO, "Erro.", ex.getMessage());
        }

    }

    void associarParentUI(MainSceneUI mainSceneUI) {
        this.mainSceneUI = mainSceneUI;
    }

    public void updateList() throws SQLException {

        if (cCtrl.getListaTarefas() != null) {
            ObservableList<Tarefa> tarList = FXCollections.observableArrayList(cCtrl.getListaTarefas());
            tableViewTarefas.setItems(tarList);
        }
    }

    private void loadTabs() {
        tableViewTarefaDesignacao.setCellValueFactory(new PropertyValueFactory<Tarefa, String>("ref"));
        tableViewTarefaDescInformal.setCellValueFactory(new PropertyValueFactory<Tarefa, String>("descrInformal"));
        tableViewDescricaoTecnica.setCellValueFactory(new PropertyValueFactory<Tarefa, String>("descrTecnica"));

        tableViewTarefaDuracao.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Tarefa, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Tarefa, String> p) {
                return new SimpleStringProperty(p.getValue().getDuracaoEst() + " Dias");
            }
        });
        tableViewTarefasCusto.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Tarefa, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Tarefa, String> p) {
                return new SimpleStringProperty(p.getValue().getCustoEst() + " POTs");
            }
        });
        tableViewTarefasCatTarefa.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Tarefa, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Tarefa, String> p) {
                return new SimpleStringProperty(p.getValue().getCatTarefa().getDescricao());
            }
        });
    }

    @FXML
    private void clkTarefaView(MouseEvent event) throws SQLException {
        if (!cCtrl.hasAnuncio(tableViewTarefas.getSelectionModel().getSelectedItem())) {
            btnPublicarTarefa.setVisible(true);
        } else {
            btnPublicarTarefa.setVisible(false);
        }
    }

    Tarefa getTarefaSelected() {
        return tableViewTarefas.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void actionPublicarTarefa(ActionEvent event) throws SQLException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Constantes.PUBLICAR_ANUN_FILE));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            novoAnuncioStage = new Stage();
            novoAnuncioStage.initModality(Modality.APPLICATION_MODAL);
            novoAnuncioStage.setTitle(Constantes.PUBLICAR_ANUN_TITLE);
            novoAnuncioStage.setResizable(false);
            novoAnuncioStage.setScene(scene);

            AdicionarAnuncioUI adicionarAnuncioUI = loader.getController();
            adicionarAnuncioUI.updateTipoRegimentoCMB();
            adicionarAnuncioUI.associarParentUI(this);

            novoAnuncioStage.show();
        } catch (IOException ex) {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, App.TITULO_APLICACAO, "Erro.", ex.getMessage());
        }
    }

    void updateListAnuncios() throws SQLException {
        mainSceneUI.getTabAnunciosUI().updateList();
        cCtrl = new EspecificarTarefaController();
    }

}
