/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.controller;

import com.company.utils.Constantes;
import com.company.model.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável pela ligação do UI com os métodos necessários, para
 * registar novas Tarefas.
 *
 * @author diana
 */
public class EspecificarTarefaController {

    /**
     * Inicializa a plataforma com uma cópia com a mesma assinatura.
     */
    private Plataforma plat = Plataforma.getInstance();

    /**
     * Declaração de uma organização.
     */
    private Organizacao org;

    private ArrayList<Anuncio> anunciosOrg;

    /**
     * Constrói uma instância de EspecificarTarefaController. Valida o acesso do
     * utilizador ao registar das tarefas.
     */
    public EspecificarTarefaController() throws SQLException {
        if (!Plataforma.getInstance().getUsersAPI().getRole().equalsIgnoreCase(Constantes.ROLE_COLABORADOR_ORGANIZACAO)
                && !Plataforma.getInstance().getUsersAPI().getRole().equalsIgnoreCase(Constantes.ROLE_GESTOR_ORGANIZACAO)) {
            throw new IllegalStateException("Utilizador não Autorizado");
        }
        anunciosOrg = new ArrayList<>();
        this.org = plat.getRo().getOrganizacaoByEmailUtilizador(this.plat.getUsersAPI().getEmail());
        anunciosOrg.addAll(org.getRegistoAnuncios().getAllAnunciosByOrg(org));
    }

    /**
     * Valida e adiciona um novo objecto do tipo Tarefa construído com os
     * parâmetros de entrada.
     *
     * @param ref referência da Tarefa
     * @param designacao designação da Tarefa
     * @param descrInformal descrição informal da Tarefa
     * @param descrTecnica descrição técnica da Tarefa
     * @param duracaoEst duração estimada da Tarefa
     * @param custoEst custo estimado da Tarefa
     * @param catTarefa categoria de tarefa em que se vai enquadrar a Tarefa
     * @param colab colaborador que faz o registo da Tarefa
     * @return true se for possível adicionar a tarefa, caso contrário devolve
     * false com uma mensagem de erro
     */
    public boolean novaTarefa(String ref, String designacao, String descrInformal,
            String descrTecnica, Integer duracaoEst, Double custoEst, CategoriaTarefa catTarefa, Colaborador colab) throws Exception {
        try {
            Tarefa t = this.org.getRegistoListaTarefas().novaTarefa(ref, designacao, descrInformal, descrTecnica, duracaoEst, custoEst, catTarefa, colab);
            return this.org.getRegistoListaTarefas().registaTarefa(t);
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    /**
     * Devolve a lista de descrições e respectivos id's, de categorias de
     * tarefa.
     *
     * @return lista de descrições e respectivos id's, de categorias de tarefa,
     * do tipo ArrayList
     */
    public ArrayList<String> getListaCatTarefaIdDesc() throws SQLException {
        List<CategoriaTarefa> list = new ArrayList<CategoriaTarefa>(getAllCatTarefa());
        String idDescBreve = "";
        ArrayList<String> listd = new ArrayList<>();
        for (CategoriaTarefa ct : list) {
            //idDescBreve = "ID: " + ct.getId() + " \n" + "DescBreve: " + ct.getDescricao();
            //                    10                  :         descricao
            idDescBreve = ct.getId().split("-")[1] + " : " + ct.getDescricao();
            listd.add(idDescBreve);
        }

        return listd;
    }

    /**
     * Devolve o objecto colaborador correspondente ao e-mail de login.
     *
     * @return objecto colaborador correspondente ao e-mail de login
     */
    public Colaborador getColaborador() throws SQLException {
        String email = this.plat.getUsersAPI().getEmail();
        return this.org.getRepositorioColaborador().getColaboradorByEmail(email);
    }

    /**
     * Devolve o objecto categoria de tarefa correspondente ao id passado por
     * parâmetro.
     *
     * @param s id da categoria de tarefa
     * @return objecto categoria de tarefa
     */
    public CategoriaTarefa getCategoriaTarefa(String s) throws SQLException {
        CategoriaTarefa a = this.plat.getListaCategoriaTarefa().getCategoriaTarefaById(s);
        return this.plat.getListaCategoriaTarefa().getCategoriaTarefaById(s);
    }

    /**
     * Devolve uma lista de tarefas existentes na organização.
     *
     * @return lista de tarefas existentes na organização
     */
    public List<Tarefa> getListaTarefas() throws SQLException {
        List<Tarefa> j = this.org.getRegistoListaTarefas().getListaTarefasByOrg(org);
        return j;
        //return null;
    }

    private List<CategoriaTarefa> getAllCatTarefa() throws SQLException {
        List<CategoriaTarefa> i = Plataforma.getInstance().getListaCategoriaTarefa().getAllCategoriasTarefa();

        return Plataforma.getInstance().getListaCategoriaTarefa().getAllCategoriasTarefa();
    }

    public boolean hasAnuncio(Tarefa tarefa) throws SQLException {
        if (anunciosOrg != null && anunciosOrg.size() != 0) {
            for (Anuncio anun : anunciosOrg) {
                if ((anun.getTarefa().getRef().equalsIgnoreCase(tarefa.getRef()) && (tarefa.getColab().getEmail().equalsIgnoreCase(getColaborador().getEmail())))) {
                    return true;
                }
            }

        }
        return false;
    }
}
