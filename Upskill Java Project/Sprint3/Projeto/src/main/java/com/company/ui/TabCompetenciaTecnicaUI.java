/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.ui;

import com.company.controller.DefinirCategoriaTarefaController;
import com.company.controller.RegistarAreaAtividadeController;
import com.company.controller.EspecificarCompetenciaTecnicaController;
import com.company.model.AreaAtividade;
import com.company.model.CompetenciaTecnica;
import com.company.model.GrauProficiencia;
import com.company.model.Plataforma;
import com.company.model.Tarefa;
import com.company.utils.Constantes;
import com.company.utils.TemaApp;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
public class TabCompetenciaTecnicaUI implements Initializable {

    private TemaApp tema = TemaApp.getInstance();

    private MainSceneUI mainSceneUI;

    private Stage novaCTStage;

    @FXML
    private TableView<CompetenciaTecnica> tableViewCompTecnica;
    @FXML
    private TableColumn<CompetenciaTecnica, String> tableViewCodUnico;
    @FXML
    private TableColumn<CompetenciaTecnica, String> tableViewAreaAtividade;
    @FXML
    private TableColumn<CompetenciaTecnica, String> tableViewDescBreve;
    @FXML
    private TableColumn<CompetenciaTecnica, String> tableViewDescDetalhada;
    @FXML
    private TableView<GrauProficiencia> listViewCompTecListaGrauProf;
    @FXML
    private TableColumn<GrauProficiencia, String> listViewCompTecnicaGrauValor;
    @FXML
    private TableColumn<GrauProficiencia, String> listViewCompTecnicaGrauDesigna;

    private EspecificarCompetenciaTecnicaController ctCtrl;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadTabs();

        try {
            updateList();
        } catch (SQLException ex) {
            Logger.getLogger(TabAreasAtividadeUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void actionAdicionarNovaCT(ActionEvent event) throws SQLException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Constantes.REGISTO_COMPTECNICA_FILE));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            novaCTStage = new Stage();
            novaCTStage.initModality(Modality.APPLICATION_MODAL);
            novaCTStage.setTitle(Constantes.REGISTO_COMPTECNICA_TITLE);
            novaCTStage.setResizable(false);

            if (tema.isEnable()) {
                scene.getStylesheets().add(Constantes.CSS_DARK_THEME);
            } else {
                scene.getStylesheets().add(Constantes.CSS_LIGHT_THEME);
            }

            novaCTStage.setScene(scene);

            AdicionarCompetenciaTecnicaUI adicionarCompetenciaTecnicaUI = loader.getController();
            adicionarCompetenciaTecnicaUI.associarParentUI(this);
            adicionarCompetenciaTecnicaUI.updateAA();

            novaCTStage.show();
        } catch (IOException ex) {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, App.TITULO_APLICACAO, "Erro.", ex.getMessage());
        }
    }

    void associarParentUI(MainSceneUI mainSceneUI) {
        this.mainSceneUI = mainSceneUI;
    }

    public void refreshTable() throws SQLException {
        tableViewCompTecnica.getItems().setAll(parseCompTecnicaList());
    }

    private List<CompetenciaTecnica> parseCompTecnicaList() throws SQLException {
        return Plataforma.getInstance().getRct().getListaCompTec();
    }

    public void updateList() throws SQLException {
        EspecificarCompetenciaTecnicaController cCtrl = new EspecificarCompetenciaTecnicaController();
        List<CompetenciaTecnica> tmp = new ArrayList<>();
        tmp = cCtrl.getListCompTec();

        if (tmp != null) {
            ObservableList<CompetenciaTecnica> ctList = FXCollections.observableArrayList(tmp);
            tableViewCompTecnica.setItems(ctList);
        }

    }

    @FXML
    private void mouseTabComp(MouseEvent event) {
        listViewCompTecListaGrauProf.getItems().clear();
        loadGrauProf();
    }

    private void loadGrauProf() {
        try {
            List<GrauProficiencia> tmp = new ArrayList<GrauProficiencia>();
            ctCtrl = new EspecificarCompetenciaTecnicaController();
            tmp = ctCtrl.getCompTecGrausProf(tableViewCompTecnica.getSelectionModel().getSelectedItem());
            if (tmp != null && tmp.size() != 0) {
                ObservableList<GrauProficiencia> GrausProfList = FXCollections.observableArrayList(tmp);
                listViewCompTecListaGrauProf.setItems(GrausProfList);
            }
        } catch (Exception e) {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, App.TITULO_APLICACAO, "Erro no load de Graus Prof", e.getMessage());
        }
    }

    private void loadTabs() {
        tableViewCodUnico.setCellValueFactory(new PropertyValueFactory<CompetenciaTecnica, String>("id"));
        tableViewAreaAtividade.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CompetenciaTecnica, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<CompetenciaTecnica, String> p) {
                return new SimpleStringProperty("ID: " + p.getValue().getAreaAtividade().getId()
                        + "\n" + "Descrição: " + p.getValue().getAreaAtividade().getDescBreve());
            }

        });
        tableViewDescBreve.setCellValueFactory(new PropertyValueFactory<CompetenciaTecnica, String>("descBreve"));
        tableViewDescDetalhada.setCellValueFactory(new PropertyValueFactory<CompetenciaTecnica, String>("descDetalhada"));

        listViewCompTecnicaGrauValor.setCellValueFactory(new PropertyValueFactory<GrauProficiencia, String>("valor"));
        listViewCompTecnicaGrauDesigna.setCellValueFactory(new PropertyValueFactory<GrauProficiencia, String>("designacao"));

    }
}
