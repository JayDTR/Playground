/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.model;

import Persistence.FabricaRepositorios;
import com.company.controller.UsersAPI;
import java.sql.SQLException;

/**
 *
 * @author Asus
 */
public class Plataforma {

    private static Plataforma plataforma;
    private UsersAPI uAPI;
    private IAlgoritmoGeradorPasswords agp;
    private FabricaRepositorios repo;

    private ListaAreasAtividade lat;
    private RegistoCompetenciasTecnicas rct;
    private ListaCategoriasTarefa m_lstCategoriaTarefa;
    private ListaTarefas m_lstTarefas;
    private RegistoOrganizacoes ro;
    private ListaCandidaturas lCand;
    private RegistoAnuncios ra;
    public RepositorioColaborador rc;
    private RegistoTipoRegimento ltipoRegimento;
    private RegistoFreelancers regFree;

    public Plataforma(){
        //Geração de passwords
        agp = new AlgoritmoGeradorPasswords();
        //UsersAPI
        uAPI = new UsersAPI();

        //Promoção do padrão HC/LC
        this.ro = new RegistoOrganizacoes();
        this.lat = new ListaAreasAtividade();
        this.rc = new RepositorioColaborador();
        this.rct = new RegistoCompetenciasTecnicas();
        this.m_lstCategoriaTarefa = new ListaCategoriasTarefa();
        this.m_lstTarefas = new ListaTarefas();
        this.lCand = new ListaCandidaturas();
        this.ra = new RegistoAnuncios();
        this.ltipoRegimento = new RegistoTipoRegimento();
        this.regFree = new RegistoFreelancers();
        
//        if(this.ltipoRegimento.registaTipoRegimento(new TipoRegimento(1,"designacao1", "regrasDescricao1"))){
//             this.ltipoRegimento.addTipoRegimento(new TipoRegimento(1,"designacao1", "regrasDescricao1"));
//        }
//        if(this.ltipoRegimento.registaTipoRegimento(new TipoRegimento(2,"designacao2", "regrasDescricao2"))){
//             this.ltipoRegimento.addTipoRegimento(new TipoRegimento(2,"designacao2", "regrasDescricao2"));
//        }
//        if(this.ltipoRegimento.registaTipoRegimento(new TipoRegimento(3,"designacao3", "regrasDescricao3"))){
//             this.ltipoRegimento.addTipoRegimento(new TipoRegimento(3,"designacao3", "regrasDescricao3"));
//        }
        
    }

    /**
     * Devolve a instância da plataforma para disponibilizar acesso aos seus
     * métodos.
     *
     * @return plataforma
     */
    public static Plataforma getInstance() {
        if (Plataforma.plataforma == null) {
            Plataforma.plataforma = new Plataforma();
        }
        return plataforma;
    }

    /**
     * @return repositório
     */
    public FabricaRepositorios getRepo() {
        return repo;
    }

    /**
     * Devolve uma lista de organizações.
     *
     * @return lista de organizações
     */
    public RegistoOrganizacoes getRo() {
        return ro;
    }

    /**
     * Devolve uma lista de organizações.
     *
     * @return lista de organizações
     */
    public RegistoFreelancers getRegFree() {
        return regFree;
    }
    /**
     * Devolve o objecto que vai gerar a password.
     *
     * @return objecto que vai gerar a password
     */
    public IAlgoritmoGeradorPasswords getAlgoritmoGeradorPwd() {
        return agp;
    }

    /**
     * Devolve o objecto users api.
     *
     * @return objecto uAPI do tipo UsersAPI
     */
    public UsersAPI getUsersAPI() {
        return uAPI;
    }

    /**
     * Reinicia a aplicação que trata dos utilizadores
     */
    public void restartUsersAPI() {
        this.uAPI = new UsersAPI();
    }

    /**
     * Devolve a lista de competências técnicas.
     *
     * @return lista de competências técnicas
     */
    public RegistoCompetenciasTecnicas getRct() {
        return rct;
    }

    /**
     * Devolve a lista de categorias de tarefa.
     *
     * @return lista de categorias de tarefa
     */
    public ListaCategoriasTarefa getListaCategoriaTarefa() {
        return m_lstCategoriaTarefa;
    }

    /**
     * Devolva a lista de tarefas.
     *
     * @return lista de tarefas
     */
    public ListaTarefas getListaTarefas() {
        return m_lstTarefas;
    }

    /**
     * Devolve a lista de áreas de atividade.
     *
     * @return lista de áreas de atividade
     */
    public ListaAreasAtividade getLat() {
        return lat;
    }

    /**
     * DEvolve a lista de colaboradores.
     *
     * @return lista de colaboradores
     */
    public RepositorioColaborador getRc() {
        return rc;
    }

    public ListaCandidaturas getLcand() {
        return lCand;
    }

    public RegistoAnuncios getRa() {
        return ra;
    }

    /**
     * @return the ltipoRegimento
     */
    public RegistoTipoRegimento getRegistoTiposRegimento() {
        return ltipoRegimento;
    }

}
