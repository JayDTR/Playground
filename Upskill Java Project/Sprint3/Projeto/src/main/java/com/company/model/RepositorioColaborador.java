/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.model;

import com.company.data.ColaboradorDB;
import java.util.ArrayList;
import com.company.utils.Constantes;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Asus
 */
public class RepositorioColaborador {

    /**
     * Declaração de um objecto do tipo arrayList de Colaborador.
     */
    private ColaboradorDB colabDB;

    /**
     * Declaração da plataforma e atribuição de uma instância de cópia da mesma.
     */
    private static Plataforma plat = Plataforma.getInstance();

    /**
     * Constrói uma instância da lista de Colaboradores. Inicializa a lista de
     * Colaboradores vazia.
     */
    public RepositorioColaborador() {
        this.colabDB = new ColaboradorDB();
    }

    /**
     * Devolve uma cópia da lista de colaboradores.
     *
     * @return cópia da lista de colaboradores do tipo arrayList
     */
    public List<Colaborador> getColaboradoresByOrg(Organizacao org) throws SQLException {
        return colabDB.getColaboradoresByOrg(org);
    }

    public List<Colaborador> getColaboradores() throws SQLException {
        return colabDB.getAllColaboradores();
    }

    /**
     * Devolve um novo objecto do tipo colaborador construído com os parâmetros
     * de entrada.
     *
     * @param nome nome do colaborador
     * @param telefone telefone do colaborador
     * @param email e-mail do colaborador
     * @return novo objecto do tipo colaborador
     */
    public Colaborador novoColaborador(String nome, String telefone, String email) {
        return new Colaborador(nome, telefone, email);
    }

    /**
     * Valida e adiciona um colaborador à lista de colaboradores.
     *
     * @param funcao função do colaborador
     * @param c colaborador da organização
     * @return true se for possível adicionar o colaborador, caso contrário
     * devolve false
     */
    public boolean registaColaborador(String funcao, Colaborador c) throws SQLException {
        if (this.validaColaborador(c)) {
            try {
                if (funcao != Constantes.ROLE_GESTOR_ORGANIZACAO) {
                    colabDB.insertColab(c);
                }
                return plat.getUsersAPI().registerUserWithRoles(c.getNome(), c.getEmail(), plat.getAlgoritmoGeradorPwd().geraPassword(), funcao);

            } catch (Exception e) {
                throw e;
            }
        }
        return false;
    }

    /**
     * Verifica se a lista de colaboradores contém um colaborador com e-mail
     * e/ou username igual ao do colaborador passado por parâmetro.
     *
     * @param c colaborador a validar
     * @return true se o e-mail e/ou username forem diferentes dos do
     * colaborador, caso contrário devolve false com uma mensagem de erro
     */
    public boolean validaColaborador(Colaborador c) throws SQLException {
        boolean flag = true;

        for (Colaborador colab : colabDB.getAllColaboradores()) {
            if ((colab.getEmail().equalsIgnoreCase(c.getEmail()) || colab.getNome().equalsIgnoreCase(c.getNome())) || (colab.getEmail().equalsIgnoreCase(c.getEmail()) && colab.getNome().equalsIgnoreCase(c.getNome()))) {
                flag = false;
                throw new IllegalArgumentException("Já existe um colaborador com este Email e/ou Username");
            }
        }
        return flag;
    }

    /**
     * Devolve um colaborador existente na lista de colaboradores com e-mail
     * igual ao passado por parâmetro.
     *
     * @param email e-mail do colaborador da organização
     * @return colaborador, ou null caso o e-mail não exista na lista
     */
    public Colaborador getColaboradorByEmail(String email) throws SQLException {
        for (Colaborador colab : colabDB.getAllColaboradores()) {
            if (colab.hasEmail(email)) {
                return colab;
            }
        }
        return null;
    }

    /**
     * Devolve a função de um colaborador existente na lista de colaboradores
     * com e-mail igual ao passado por parâmetro.
     *
     * @param email e-mail do colaborador da organização
     * @return função do utilizador da organização
     */
    public String getColaboradorRole(String email) throws SQLException { // apagar?
        Colaborador c = getColaboradorByEmail(email);
        Organizacao org = plat.getRo().getOrganizacaoByEmailUtilizador(email);
        for (Colaborador colab : colabDB.getAllColaboradores()) {
            if (colab == org.getGestor()) {
                return Constantes.ROLE_GESTOR_ORGANIZACAO;
            }
        }
        return Constantes.ROLE_COLABORADOR_ORGANIZACAO;
    }
}
