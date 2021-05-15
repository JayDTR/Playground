/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.controller;

import com.company.model.Anuncio;
import com.company.model.Colaborador;
import com.company.model.Organizacao;
import com.company.model.Plataforma;
import com.company.model.Tarefa;
import com.company.model.TipoRegimento;
import com.company.utils.Constantes;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author Asus
 */
public class RegistarAnuncioController {

    /**
     * Inicializa a plataforma com uma cópia com a mesma assinatura.
     */
    private Plataforma plat = Plataforma.getInstance();

    /**
     * Declaração de uma organização.
     */
    private Organizacao org;

    public RegistarAnuncioController() throws SQLException {
        if (!Plataforma.getInstance().getUsersAPI().getRole().equalsIgnoreCase(Constantes.ROLE_COLABORADOR_ORGANIZACAO)
                && !Plataforma.getInstance().getUsersAPI().getRole().equalsIgnoreCase(Constantes.ROLE_GESTOR_ORGANIZACAO)) {
            throw new IllegalStateException("Utilizador não Autorizado");
        }
        this.org = plat.getRo().getOrganizacaoByEmailUtilizador(this.plat.getUsersAPI().getEmail());
    }

    public boolean novoAnuncio(Date dtInicioPublicitacao, Date dtFimPublicitacao, Date dtInicioCandidatura, Date dtFimCandidatura,
            Date dtInicioSeriacao, Date dtFimSeriacao, Tarefa tarefa, Colaborador colab, TipoRegimento regimento) throws Exception {
        try {
            Anuncio a = this.org.getRegistoAnuncios().novoAnuncio(dtInicioPublicitacao, dtFimPublicitacao, dtInicioCandidatura, dtFimCandidatura, dtInicioSeriacao, dtFimSeriacao, tarefa, colab, regimento);
            return this.org.getRegistoAnuncios().registaAnuncio(a);
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

//    public List<Anuncio> getAllAnuncios() throws SQLException {
//        List<Anuncio> lista1 = new ArrayList<Anuncio>(this.org.getRegistoAnuncios().getAll());
//        return lista1;
//    }
    
    public List<Anuncio> getAllAnuncios() throws SQLException {
        List<Anuncio> lista1 = new ArrayList<Anuncio>(this.org.getRegistoAnuncios().getAllAnunciosByOrg(org));
        return lista1;
    }

    public List<String> getAllTarefaDesig() throws SQLException {
        List<Tarefa> lista2 = new ArrayList<>(this.org.getRegistoListaTarefas().getListaTarefasByOrg(org)); //alterado aqui para popular a combobox com tarefas só desta org
        List<String> lista3 = new ArrayList<>();
         String idDesig = "";
        for (Tarefa tarefa : lista2) {
            idDesig = "Ref: " + tarefa.getRef() + " \n" + "Designacao: " + tarefa.getDesignacao();
            lista3.add(idDesig);
        }
        return lista3;
    }

    public List<String> getAllTipoRegimentoDesignacao() throws SQLException {
        List<TipoRegimento> list = new ArrayList<>(this.plat.getRegistoTiposRegimento().getAll());
        List<String> list1 = new ArrayList<>();
        for (TipoRegimento tipoRegimento : list) {
            list1.add(tipoRegimento.getDesignacao());
        }
        return list1;
    }

    public String getTarefaIdByDesignacao(String designacao) throws SQLException {
        List<Tarefa> list = new ArrayList<>(this.org.getRegistoListaTarefas().getAllTarefas());
        for (Tarefa tarefa : list) {
            if (tarefa.getDesignacao().equalsIgnoreCase(designacao)) {
                return tarefa.getRef();

            }
        }
        return null;
    }

    public Tarefa getTarefaByRef(String ref) throws SQLException {
        List<Tarefa> list = new ArrayList<>(org.getRegistoListaTarefas().getAllTarefas());
        for (Tarefa tarefa : list) {
            if (tarefa.getRef().equalsIgnoreCase(ref)) {
                return tarefa;
            }
        }
        return null;
    }

    public TipoRegimento getTipoRegimentoByDesignacao(String designacao) throws SQLException {
        List<TipoRegimento> list = new ArrayList<>(this.plat.getRegistoTiposRegimento().getAll());
        for (TipoRegimento tipoRegimento : list) {
            if (tipoRegimento.getDesignacao().equalsIgnoreCase(designacao)) {
                return tipoRegimento;
            }
        }
        return null;
    }

    public Colaborador getColabByEmail() throws SQLException {
        String email = plat.getUsersAPI().getEmail();

        return org.getRepositorioColaborador().getColaboradorByEmail(email);
    }
}
