/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.model;

import com.company.data.AnuncioDB;
import com.company.data.TipoRegimentoDB;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author diana
 */
public class RegistoTipoRegimento {

    private TipoRegimentoDB tipoRegimentoDB;
    /**
     * Declaração de um objecto do tipo arrayList de TipoRegimento.
     */
    private ArrayList<TipoRegimento> listaTiposRegimento;

    /**
     * Constrói uma instância da lista de tipos de regimento. Inicializa a lista
     * de tipos de regimento vazia.
     */
    public RegistoTipoRegimento() {
        this.listaTiposRegimento = new ArrayList<>();
        this.tipoRegimentoDB = new TipoRegimentoDB();
    }

    /**
     * Devolve uma cópia da lista de tipos de regimento.
     *
     * @return cópia da lista de tipos de regimento do tipo arrayList
     */
    public List<TipoRegimento> getAll() throws SQLException {
        List<TipoRegimento> ltipoReg = tipoRegimentoDB.getAll();
        return ltipoReg;
    }

    /**
     * Devolve um novo objecto do tipo TipoRegimento construído com os
     * parâmetros de entrada.
     *
     * @param designacao
     * @param descricaoRegras
     * @return
     */
    public TipoRegimento novoTipoRegimento(String designacao, String descricaoRegras) {
        TipoRegimento tipoReg = new TipoRegimento(designacao, descricaoRegras);
        return tipoReg;
    }

    public boolean registaTipoRegimento(TipoRegimento oTipoRegimento) throws SQLException {
        return tipoRegimentoDB.save(oTipoRegimento);
    }

    public boolean addTipoRegimento(TipoRegimento oTipoRegimento) throws SQLException {
        return listaTiposRegimento.add(oTipoRegimento);
    }

    public TipoRegimento getTipoRegimentoByDesignacao(String strRef) throws SQLException {
        TipoRegimento tipoReg = tipoRegimentoDB.find(strRef);
        return tipoReg;
    }

}
