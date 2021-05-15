/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.model;

import com.company.data.GrauProficienciaDB;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joaor
 */
public class RegistoGrausProficiencia {

    /**
     * Declaração de um objecto do tipo arrayList de Tarefa.
     */
    private GrauProficienciaDB gpDB;

    /**
     * Constrói uma instância da lista de tarefas, recebendo por parâmetro uma
     * lista de tarefas pré preenchida.
     *
     * @param listTarefa lista de tarefas pré preenchida
     */

    /**
     * Constrói uma instância da lista de tarefas,. Inicializa a lista de
     * tarefas vazia.
     */
    public RegistoGrausProficiencia() {
        this.gpDB = new GrauProficienciaDB();
    }


    /**
     * Devolve um novo objecto do tipo Tarefa construído com os parâmetros de
     * entrada.
     *
     * @param valor do Grau Proficiencia
     * @param designacao designação do Grau Proficiencia
     * @param cT objeto Competencia Tecnica ao qual o Grau Proficiencia ira
     * estar associado
     * @return novo objecto do tipo Grau Proficiencia
     */
    public GrauProficiencia novoGrauProficiencia(String valor, String designacao, CompetenciaTecnica cT) throws SQLException {
        return new GrauProficiencia(valor, designacao, cT);
    }

    /**
     * Valida e adiciona um Grau Proficiencia à lista de graus proficiencia.
     *
     * @param oTarefa Tarefa a adicionar
     * @return true se for possível adicionar a Tarefa, caso contrário devolve
     * false
     */
    public boolean registaGrauProficiencia(GrauProficiencia oGrauProf) throws SQLException {
        if (this.validaGrauProficiencia(oGrauProf)) {
            return addGrauProficiencia(oGrauProf);
        }
        return false;
    }

    /**
     * Adiciona uma Tarefa à lista de tarefas.
     *
     * @param oTarefa Tarefa a adicionar
     * @return true se for adicionada a Tarefa, caso contrário devolve false
     */
    public boolean addGrauProficiencia(GrauProficiencia oGrauProf) throws SQLException {
        if (this.validaGrauProficiencia(oGrauProf)) {
            gpDB.insertGP(oGrauProf);
            return true;
        }
        return false;
    }

    /**
     * Verifica se a lista de tarefas contém um Grau Proficiencia com valor e
     * Competencia Tecnica igual à referência do objecto Grau Proficiencia
     * passada por parâmetro.
     *
     * @param oGrauProf Grau Proficiencia a validar
     * @return true se o valor e Competencia Tecnica do Grau Proficiencia for
     * diferente de todos os conjuntos de valor e Competencia Tecnica a da lista
     * de Graus Proficiencia, caso contrário devolve false com uma mensagem de
     * erro
     */
    public boolean validaGrauProficiencia(GrauProficiencia oGrauProf) throws SQLException {
        boolean valida = true;

        for (GrauProficiencia gp : gpDB.getAllGrausProf()) {
            if (gp.getValor().equalsIgnoreCase(oGrauProf.getValor()) && gp.getCompT().getId().equalsIgnoreCase(oGrauProf.getCompT().getId())) {
                valida = false;
                throw new IllegalArgumentException("Já existe outro Grau Proficiencia com este valor para esta Competenica Tecnica.");
            }
        }
        return valida;
    }
    
  

    public List<GrauProficiencia> getListaByCompTec(String id) throws SQLException {

        List<GrauProficiencia> tmp = gpDB.getGrausProfByCompTec(id);
        
        return tmp;
    }
    
    public GrauProficiencia getGrauProficienciaFromCTByValor(String id, String valor) throws SQLException{
        GrauProficiencia tmp = null;
        for (GrauProficiencia gp : getListaByCompTec(id)) {
            if(valor.equalsIgnoreCase(gp.getValor())){
                tmp = gp;
            }
        }
        if(tmp == null) {
            throw new IllegalArgumentException("Não ha nenhum grau com este valor: "+valor+" nesta competencia tecnica: "+id);
        }else{
            return tmp;
        }
    }
}
