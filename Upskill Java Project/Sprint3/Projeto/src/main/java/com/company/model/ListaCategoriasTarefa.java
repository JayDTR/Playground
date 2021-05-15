/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.model;

import com.company.data.CategoriaTarefaDB;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author diana
 */
public class ListaCategoriasTarefa {

    /**
     * Declaração de um objecto do tipo arrayList de CategoriaTarefa.
     */
    private CategoriaTarefaDB categoriaTarefaDB;

    /**
     * Constrói uma instância da lista de categorias de tarefa. Inicializa a
     * lista de categorias de tarefa vazia.
     */
    public ListaCategoriasTarefa() {
        this.categoriaTarefaDB = new CategoriaTarefaDB();
    }

    /**
     * Devolve uma cópia da lista de categorias de tarefa.
     *
     * @return cópia da lista de categorias de tarefa do tipo arrayList
     */
    public List<CategoriaTarefa> getAllCategoriasTarefa() throws SQLException {
        return this.categoriaTarefaDB.getAllCategoriasTarefa();
    }

    /**
     * Devolve um novo objecto do tipo categoria de tarefa construído com os
     * parâmetros de entrada.
     *
     * @param descricao descrição da categoria de tarefa
     * @param oArea objecto área de atividade
     * @param lcompTecCT lista de competências técnicas do tipo HashMap
     * (CompetenciaTecnica, Obrigatoriedade)
     * @return novo objecto do tipo categoria de tarefa
     */
    public CategoriaTarefa novaCategoriaTarefa(String descricao, AreaAtividade oArea, List<CaraterCT> lcompTecCT) throws SQLException {
        CategoriaTarefa categoria = new CategoriaTarefa(descricao, oArea, lcompTecCT);
        return categoria;
    }

    /**
     * Valida e adiciona uma categoria de tarefa à lista de categorias de
     * tarefa.
     *
     * @param oCatTarefa categoria de tarefa a adicionar
     * @return true se for possível adicionar a categoria de tarefa, caso
     * contrário devolve false
     */
    public boolean registaCategoriaTarefa(CategoriaTarefa oCatTarefa) throws SQLException {
        if (this.validaCategoriaTarefa(oCatTarefa)) {
             categoriaTarefaDB.insertCategoriaTarefa(oCatTarefa);
             return true;
        }
        return false;
    }


    /**
     * Verifica se a lista de categorias de tarefa contém uma categoria de
     * tarefa com id igual ao da categoria de tarefa passada por parâmetro.
     *
     * @param oCatTarefa categoria de tarefa a validar
     * @return true se o id da categoria de tarefa for diferente de todos os
     * id's da lista da categorias de tarefa, caso contrário devolve false com
     * uma mensagem de erro
     */
    public boolean validaCategoriaTarefa(CategoriaTarefa oCatTarefa) throws SQLException {
        boolean valida = true;

        for (CategoriaTarefa c : getAllCategoriasTarefa()) {
            String id1 = c.getId();
            String id2 = oCatTarefa.getId();
            if (c.getId().equalsIgnoreCase(oCatTarefa.getId())) {
                valida = false;
                throw new IllegalArgumentException("Já existe outra categoria de tarefa com este ID.");
            }
        }
        return valida;
    }

    /**
     * Devolve uma categoria de tarefa existente na lista de categorias de
     * tarefa com id igual ao passado por parâmetro.
     *
     * @param strId identificação da categoria de tarefa
     * @return categoria de tarefa, ou null caso o id não exista na lista
     */
    public CategoriaTarefa getCategoriaTarefaById(String strId) throws SQLException {

        for (CategoriaTarefa c :  getAllCategoriasTarefa()) {
            if (c.getId().equalsIgnoreCase(strId)) {
                return c;
            }
        }

        return null;
    }

    /**
     * Devolve uma categoria de tarefa existente na lista de categorias de
     * tarefa com descricao igual à passada por parâmetro.
     *
     * @param strDescricao descrição da categoria de tarefa
     * @return categoria de tarefa, ou null caso a descrição não exista na lista
     */
    public CategoriaTarefa getCategoriaTarefaByDescricao(String strDescricao) throws SQLException {

        for (CategoriaTarefa c :  getAllCategoriasTarefa()) {
            if (c.getDescricao().equalsIgnoreCase(strDescricao)) {
                return c;
            }
        }

        return null;
    }
    
    public List<CaraterCT> getListCaraterCTByCatTarId(String id) throws SQLException {
        return categoriaTarefaDB.getListCaraterCTByCatTarId(id);
    }
}
