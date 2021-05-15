/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.ui;

import com.company.controller.RegistarAnuncioController;
import com.company.model.Colaborador;
import com.company.model.Tarefa;
import com.company.model.TipoRegimento;
import static com.company.ui.App.TITULO_APLICACAO;
import com.company.utils.Constantes;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class AdicionarAnuncioUI implements Initializable {

    private TabTarefasUI tabTarefasUI;

    @FXML
    private Button btnAdicionarAnuncio;
    @FXML
    private Button btnCancelar;
    
    @FXML
    private ComboBox<String> cmbTipoRegimento;
    @FXML
    private DatePicker dtInicioP;
    @FXML
    private DatePicker dtFimP;
    @FXML
    private DatePicker dtInicioCand;
    @FXML
    private DatePicker dtFimCand;
    @FXML
    private DatePicker dtInicioS;
    @FXML
    private DatePicker dtFimS;

    private RegistarAnuncioController anuncioCtrl;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void actionAdicionarAnuncio(ActionEvent event) throws SQLException {
        anuncioCtrl = new RegistarAnuncioController();
        
        Date d1 = Date.valueOf(dtInicioP.getValue());
        Date d2 = Date.valueOf(dtFimP.getValue());
        Date d3 = Date.valueOf(dtInicioCand.getValue());
        Date d4 = Date.valueOf(dtFimCand.getValue());
        Date d5 = Date.valueOf(dtInicioS.getValue());
        Date d6 = Date.valueOf(dtFimS.getValue());
        Tarefa tf = tabTarefasUI.getTarefaSelected();
        Colaborador colab = anuncioCtrl.getColabByEmail();
        TipoRegimento tReg = anuncioCtrl.getTipoRegimentoByDesignacao(cmbTipoRegimento.getValue());
        
        
        try {
            if (anuncioCtrl.novoAnuncio(
                    d1, //Date dtInicioPublicitacao
                    d2, // Date dtFimPublicitacao
                    d3, // Date dtInicioCandidatura
                    d4, //Date dtFimCandidatura
                    d5, //Date dtInicioSeriacao
                    d6, // Date dtFimSeriacao 
                    tf, //Tarefa tarefa
                    colab, //Colaborador colab
                     tReg//TipoRegimento regimento
            )) {
                showAACreationSuccess();
                tabTarefasUI.updateListAnuncios();
                encerrarAnuncioUI(event);
            }
        } catch (Exception ex) {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, Constantes.TAB_MANAGE_CATTAREFA_TITLE, "Erro ao adicionar Categoria de Tarefa.", ex.getMessage()).showAndWait();
        }
    }

    @FXML
    private void actionCancelar(ActionEvent event) {

        Alert alerta = AlertaUI.criarAlerta(Alert.AlertType.CONFIRMATION, TITULO_APLICACAO,
                "Irá perder os dados inseridos.", "Deseja mesmo encerrar o registo?");

        if (alerta.showAndWait().get() == ButtonType.CANCEL) {

            event.consume();
        } else {
            encerrarAnuncioUI(event);
        }
    }

    void updateTipoRegimentoCMB() throws SQLException {
        anuncioCtrl = new RegistarAnuncioController();
        ObservableList obList = FXCollections.observableList(anuncioCtrl.getAllTipoRegimentoDesignacao());
        this.cmbTipoRegimento.getItems().clear();
        this.cmbTipoRegimento.setItems(obList);
    }

    public void showAACreationSuccess() {
        Alert alerta = AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, App.TITULO_APLICACAO, "Sucesso", "Anuncio aicionado com sucesso");
        if (alerta.showAndWait().get() == ButtonType.OK) {
            alerta.close();
        }
    }

    public void showIncorrectInformation(Exception e) {
        Alert alert = AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, App.TITULO_APLICACAO,
                "Erro ao Adicionar Anúncio.", e.getMessage());
        if (alert.showAndWait().get() == ButtonType.OK) {
            alert.close();
        }
    }

    void associarParentUI(TabTarefasUI tabTarefasUI) {
        this.tabTarefasUI = tabTarefasUI;
    }

    private void encerrarAnuncioUI(ActionEvent event) {

        this.dtInicioP.getEditor().clear();
        this.dtFimP.getEditor().clear();
        this.dtInicioCand.getEditor().clear();
        this.dtFimCand.getEditor().clear();
        this.dtInicioS.getEditor().clear();
        this.dtFimS.getEditor().clear();
        this.cmbTipoRegimento.getSelectionModel().clearSelection();
        this.cmbTipoRegimento.setValue(null);

        ((Node) event.getSource()).getScene().getWindow().hide();
    }
}
