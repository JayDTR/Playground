/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author diana
 */
public class CategoriaTarefa {

    /**
     * Valor por omissão do id da categoria de tarefa.
     */
    private static int id = 0;

    /**
     * Identificação única da categoria de tarefa.
     */
    private String idCategoria;

    /**
     * Descrição da categoria de tarefa.
     */
    private String descricao;

    /**
     * Declaração do objeto área de atividade.
     */
    private AreaAtividade areaAtividade;

    /**
     * Declaração da lista de competências técnicas como HashMap, onde as Keys
     * são do tipo CompetenciaTecnica e os respetivos valores, do tipo
     * CaracterCT.
     */
    private List<CaraterCT> lcompTec;

    /**
     * Declaração da plataforma e atribuição de uma instância de cópia da mesma.
     */
    private Plataforma plat = Plataforma.getInstance();

    /**
     * Constrói uma instância de Categoria de Tarefa recebendo a descrição, a
     * área de atividade e a lista de competências técnicas.
     *
     * Só é criada se todos os parâmetros forem válidos.
     *
     * Atribuição de um idCategoria único.
     *
     * @param descricao descrição da categoria de tarefa
     * @param oArea área de atividade na qual se enquadra a categoria
     * @param listCompTec lista de competências técnicas to tipo HashMap, da
     * área de atividade recebida
     */
    public CategoriaTarefa(String descricao, AreaAtividade oArea, List<CaraterCT> listCompTec) {
        if (descricao.isEmpty()) {
            throw new IllegalArgumentException("Desc dos argumentos pode ser nulo ou vazio.");
        } else if ((oArea == null)) {
            throw new IllegalArgumentException("Area de atividade pode ser nulo ou vazio.");
        } else if ((listCompTec == null)) {
            throw new IllegalArgumentException("Lista de Compentencias Técnicas não pode ser nula");
        }

        
        setDescricao(descricao);
        setAreaAtividade(oArea);
        lcompTec = new ArrayList<CaraterCT>();
        setLcompTec(listCompTec);

    }

    /**
     * Verifica se o id da Categoria é igual ao id de outra categoria, recebido
     * por parêmetro.
     *
     * @param idCategoria o outro idCategoria com o qual se compara o
     * idCategoria.
     * @return true se o id da categoria for igual ao id da categoria recebido
     * por parâmetro caso contrário devolve false.
     */
    public boolean hasId(String idCategoria) {
        return this.idCategoria.equalsIgnoreCase(idCategoria);
    }

    /**
     * Devolve o id da Categoria de Tarefa
     *
     * @return id da Categoria
     */
    public String getId() {
        return this.idCategoria;
    }

    /**
     * Modifica o id único, da categoria de tarefa, incrementando-o.
     *
     * @param idCategoria novo id da categoria de tarefa
     */
    public void setIdCategoria(String idCategoria) {
        // o erro esta aqui
        char c = 'C';

        if (idCategoria.charAt(0) == c) {
            this.idCategoria = idCategoria;
        } else {
            this.idCategoria = "CatTar-" + idCategoria;
        }

    }

    /**
     * Devolve a descrição da Categoria de Tarefa
     *
     * @return descricao da Categoria
     */
    public String getDescricao() {
        return this.descricao;
    }

    /**
     * Modifica a descrição da categoria de tarefa. Verifica se o novo tamanho
     * da descrição tem menos de 25 caracteres, caso contrário apresenta
     * mensagem de erro.
     *
     * @param descricao nova descrição da categoria de tarefa
     */
    public void setDescricao(String descricao) {
        if (descricao.length() < 25) {
            this.descricao = descricao;
        } else {
            throw new IllegalArgumentException("Descrição não deve ser maior do que 25 caracteres");
        }
    }

    /**
     * Devolve a Área de Atividade da Categoria de Tarefa
     *
     * @return area de atividade da categoria de tarefa
     */
    public AreaAtividade getAreaAtividade() {
        return this.areaAtividade;
    }

    /**
     * Modifica a área de atividade da categoria de tarefa. Verifica se não é
     * nula, caso contrário apresenta mensagem de erro.
     *
     * @param areaAtividade nova área de atividade da categoria de tarefa
     */
    public void setAreaAtividade(AreaAtividade areaAtividade) {
        if (areaAtividade != null) {
            this.areaAtividade = areaAtividade;
        } else {
            throw new IllegalArgumentException("Área de atividade não pode ser nula");
        }
    }

    /**
     * Devolve a lista de competências técnicas da categoria de tarefa.
     *
     * @return lista competências técnicas (CompetenciaTecnica,
     * Obrigatoriedade).
     */
    public List<CaraterCT> getLcompTec() {
        return this.lcompTec;
    }

    /**
     * Modifica a lista de competências técnicas da categoria para assumir os
     * valores da lista de entrada. A lista de competências técnicas apenas tem
     * de ter pelo menos uma competência técnica associada, caso contrário
     * apresenta uma mensagem de erro.
     *
     * @param lcompTec novo hashMap que a lista de competências técnicas, da
     * categoria, vai assumir (Key,Value)
     */
    public void setLcompTec(List<CaraterCT> lcomptec) {
        if (!(lcomptec == null)) {
            this.lcompTec.addAll(lcomptec);
        } else {
            throw new IllegalArgumentException("Lista de Competencias tecnicas esta a null");
        }
    }

    /**
     * Devolve a lista de competências técnicas relativas à área de atividade
     * recebida.
     *
     * @param at objecto do tipo AreaAtividade para procurar as respectivas
     * competências técnicas.
     * @return lista de competências técnicas relativas à área de atividade
     * recebida.
     */
    public ArrayList<CompetenciaTecnica> getListaCTPorArea(AreaAtividade at) throws SQLException {
        ArrayList<CompetenciaTecnica> lct; //declaração lct como arrayList de objectos CompetenciaTecnica
        lct = new ArrayList<>();

        for (CompetenciaTecnica ct : plat.getRct().getListaCompTec()) {
            if (ct.getAreaAtividade().equals(at)) {
                lct.add(ct);
            }

        }
        return lct;
    }

    /**
     * Compara a categoria com o objeto recebido.
     *
     * @param o objeto a comparar com a categoria.
     * @return true se o objeto recebido representar uma categoria equivalente à
     * categoria. Caso contrário, retorna false.
     */
    @Override
    public boolean equals(Object o) {
        // self check
        if (this == o) {
            return true;
        }
        // null check
        if (o == null) {
            return false;
        }
        // type check and cast
        if (getClass() != o.getClass()) {
            return false;
        }
        // field comparison
        CategoriaTarefa obj = (CategoriaTarefa) o;
        //return (Objects.equals(id, obj.id));
        return (this.getAreaAtividade().equals(obj.getAreaAtividade()) && this.getLcompTec().equals(obj.getLcompTec()) && this.getDescricao().equals(obj.getDescricao()));
    }

    /**
     * Devolve a descrição textual da categoria de tarefa no formato: ID --
     * Descrição -- Área Atividade: areaAtividade.toString().
     *
     * @return caraterísticas da categoria de tarefa
     */
    @Override
    public String toString() {
        return String.format("%s -- %s -- Área Atividade: %s", this.idCategoria, this.getDescricao(), this.getAreaAtividade().toString());
    }

}
