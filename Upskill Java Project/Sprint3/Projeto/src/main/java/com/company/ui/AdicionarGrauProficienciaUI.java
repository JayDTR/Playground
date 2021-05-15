/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.ui;

import com.company.controller.EspecificarCompetenciaTecnicaController;
import com.company.model.AreaAtividade;
import static com.company.ui.App.TITULO_APLICACAO;
import com.company.utils.Constantes;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author joaor
 */
public class AdicionarGrauProficienciaUI implements Initializable {

    private AdicionarCompetenciaTecnicaUI adicionarCompetenciaTecnicaUI;

    @FXML
    private TextField txtValor;
    @FXML
    private TextArea txtDesignacao;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnAdicionarNovoGrauProficiencia;

    private EspecificarCompetenciaTecnicaController ctCtrl;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }


    @FXML
    private void actionCancel(ActionEvent event) throws SQLException {
        adicionarCompetenciaTecnicaUI.showAACreationSuccess();
        adicionarCompetenciaTecnicaUI.tabCompetenciaTecnicaUI.updateList();
        encerrarNovoGrauProficienciaUI(event);
    }

    void associarParentUI(AdicionarCompetenciaTecnicaUI adicionarCompetenciaTecnicaUI) {
        this.adicionarCompetenciaTecnicaUI = adicionarCompetenciaTecnicaUI;
    }

    @FXML
    private void actionAdicionarNovoGrauProficiencia(ActionEvent event) {
        try {
            if (ctCtrl.novoGrauProficiencia(txtValor.getText().trim(), txtDesignacao.getText().trim())) {
                showAACreationSuccess();
            }

        } catch (Exception ex) {
            adicionarCompetenciaTecnicaUI.showIncorrectInformation(ex);
        }
    }
    
    
     public void showAACreationSuccess() {
        Alert alerta = AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, App.TITULO_APLICACAO, "Sucesso", "Grau Proficiencia adicionado com Sucesso");
        if (alerta.showAndWait().get() == ButtonType.OK) {
            cleanNovoGrauProficienciaUI();
            alerta.close();
        }
    }
    
    void cleanNovoGrauProficienciaUI() {
        this.txtValor.clear();
        this.txtDesignacao.clear();
    }
    
    void encerrarNovoGrauProficienciaUI(ActionEvent event) {
        this.txtValor.clear();
        this.txtDesignacao.clear();

        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    void associarParentCtrl(EspecificarCompetenciaTecnicaController ctCtrl) {
        this.ctCtrl = ctCtrl;
    }

    @FXML
    private void actionLimitTxtValor(KeyEvent event) {
         if (!(event.getCode() == KeyCode.BACK_SPACE || event.getCode() == KeyCode.DELETE || event.getCode() == KeyCode.ESCAPE)) {
            if (txtValor.getText().length() > 10) {
                Alert alerta = AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, TITULO_APLICACAO,
                        "Maximo Caracteres", "Maximo Carateres de valor é 10 caracteres");

                if (alerta.showAndWait().get() == ButtonType.OK) {
                    event.consume();
                }
            }
        }
    }

    @FXML
    private void actionLimitTxtDesignacao(KeyEvent event) {
        if (!(event.getCode() == KeyCode.BACK_SPACE || event.getCode() == KeyCode.DELETE || event.getCode() == KeyCode.ESCAPE)) {
            if (txtDesignacao.getText().length() > 100) {
                Alert alerta = AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, TITULO_APLICACAO,
                        "Maximo Caracteres", "Maximo Carateres de Designacao é 100 caracteres");

                if (alerta.showAndWait().get() == ButtonType.OK) {
                    event.consume();
                }
            }
        }
    }

}

