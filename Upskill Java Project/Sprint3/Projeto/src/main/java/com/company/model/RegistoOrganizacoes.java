/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.model;

import com.company.controller.UsersAPI;
import com.company.data.OrganizacaoDB;
import java.util.ArrayList;
import com.company.utils.Constantes;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Asus
 */
public class RegistoOrganizacoes {

    /**
     * Declaração de um objecto do tipo UsersAPI.
     */
    private UsersAPI uAPI;

    /**
     * Declaração de um objecto do tipo AlgoritmoGeradorPasswords que vai
     * assumir uma password aleatória.
     */
    private AlgoritmoGeradorPasswords agp;

    private OrganizacaoDB orgDB;

    /**
     * Constrói uma instância da lista de organizações. Inicializa a lista de
     * organizações vazia.
     */
    public RegistoOrganizacoes() {
        this.uAPI = new UsersAPI();
        this.agp = new AlgoritmoGeradorPasswords();
        this.orgDB = new OrganizacaoDB();
    }

    /**
     * Devolve o objecto users api.
     *
     * @return objecto uAPI do tipo UsersAPI
     */
    public UsersAPI getUsersAPI() {
        return this.uAPI;
    }

    /**
     * Devolve um novo objecto do tipo Organização construído com os parâmetros
     * de entrada.
     *
     * @param nome nome da organização
     * @param nif número de identificação fiscal da organização
     * @param site endereço web da organização
     * @param telefone contacto telefónico da organização
     * @param email e-mail da organização
     * @param endPostal endereço postal da organização
     * @param colab colaborador(por exemplo um gestor) que está a proceder ao
     * registo da organização
     * @return novo objecto do tipo Organização
     */
    public Organizacao novaOrganizacao(String nome, String nif, String site, String telefone, String email, EnderecoPostal endPostal, Colaborador colab) throws SQLException, Exception {
        //return new Organizacao(nome, nif, site, telefone, email, endPostal, colab);
        Organizacao o = null;
        try {
            o = new Organizacao(nome, nif, site, telefone, email, endPostal, colab);
            o.getRepositorioColaborador().registaColaborador(Constantes.ROLE_GESTOR_ORGANIZACAO, colab);
        } catch (Exception e) {
            throw new Exception(e);
        }
        return o;
    }

    /**
     * Valida e adiciona uma organização à lista de organizações.
     *
     * @param org organização a adicionar
     * @return true se for possível adicionar a organização, caso contrário
     * devolve false
     */
    public boolean registaOrganizacao(Organizacao org) throws SQLException {
        if (this.validaOrganizacao(org)) {
            orgDB.insertOrganizacao(org);
            return true;
        }
        return false;
    }

    /**
     * Verifica se a lista de organizações contém uma organização igual à que é
     * passada por parâmetro.
     *
     * @param org organização a validar
     * @return true se a organização for diferente de todas as organizações da
     * lista, caso contrário devolve false com uma mensagem de erro
     */
    public boolean validaOrganizacao(Organizacao org) throws SQLException {
        boolean flag = true;
        for (Organizacao organizacao : getAllOrgs()) {
            if (org.equals(organizacao)) {
                flag = false;
                throw new IllegalArgumentException("Já existe outra organização com os dados inseridos.");
            }
        }
        return flag;
    }

    /**
     * Adiciona um registo de um novo colaborador como utilizador à lista de
     * colaboradores.
     *
     * @param colab colaborador da organização
     * @return true se o registo for feito com sucesso, caso contrário devolve
     * false
     */
    public boolean registaColaboradorComoUtilizador(Colaborador colab) {
        return this.uAPI.registerUserWithRoles(colab.getNome(), colab.getEmail(), agp.geraPassword(), Constantes.ROLE_COLABORADOR_ORGANIZACAO);
    }

//    /**
//     * Adiciona um registo de um novo colaborador como utilizador à lista de colaboradores.
//     * 
//     * @param funcao função desempenhada pelo utilizador
//     * @param c organização à qual está associado o utilizador
//     * @param colab colaborador que se pretende registar
//     * @return true se o registo for feito com sucesso, caso contrário devolve
//     * false
//     */
//    private boolean registaGestorComoUtilizador(String funcao, Organizacao c, Colaborador colab) {
//        return c.getRepositorioColaborador().registaColaborador(funcao, colab);
//    }
    /**
     * Devolve uma Organização existente na lista de organizações com e-mail
     * igual ao passado por parâmetro.
     *
     * @param email e-mail da organização
     * @return organização, ou null caso o e-mail não exista na lista
     */
    public Organizacao getOrganizacaoByEmailUtilizador(String email) throws SQLException {
        return orgDB.getOrgByColabEmail(email);
    }

    public List<Organizacao> getAllOrgs() throws SQLException {
        return orgDB.getAllOrgs();
    }
}
