/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.model;

import java.util.Objects;

/**
 *
 * @author joaor
 */
public class GrauProficiencia {

    /**
     * Valor unico da competência técnica.
     */
    private String valor;
    
    /**
     * Designação deste grau de proficiencia.
     */
    private String designacao;
    
    
     /**
     * Declaração do objeto Competencia Tecnica a que este Grau pertence.
     */
    private CompetenciaTecnica compT;
    
    
    /**
     * Constrói uma instância de Competência Técnica recebendo o identificador,
     * a descrição breve, a descrição detalhada e a área de atividade. 
     * Só é criada se todos os parâmetros forem válidos.
     *
     * @param id identificação da competência técnica
     * @param descBreve descrição breve da competência técnica
     * @param descDetalhada descrição detalhada da competência técnica
     * @param areaAtividade área de atividade em que se vai enquadrar a Competência Técnica
     */
    public GrauProficiencia(String valor, String designacao, CompetenciaTecnica compT) {
        if ((valor == null) || (designacao == null)
                || (compT == null) || (valor.isEmpty()) || (designacao.isEmpty())) {
            throw new IllegalArgumentException("Nenhum dos argumentos pode ser nulo ou vazio.");
        }

        setValor(valor);
        setDesignacao(designacao);
        setCompT(compT);
    }
    
    

    private void setValor(String valor) {
       this.valor = valor;
        if (valor.length() < 10) {
            this.valor = valor;
        } else {
            throw new IllegalArgumentException("valor não deve ser maior do que 10 caracteres");
        }
    }

    private void setCompT(CompetenciaTecnica compT) {
       this.compT = compT;
    }

    private void setDesignacao(String designacao) {
        this.designacao = designacao;
        if (designacao.length() < 100) {
            this.designacao = designacao;
        } else {
            throw new IllegalArgumentException("Designacao não deve ser maior do que 100 caracteres");
        }
    }
    
    /**
     * Devolve a descrição textual do Grau Proficiencia no formato: Valor -
     * Designaçã - CompetenciaTecnica:
     * compT.toString().
     *
     * @return caraterísticas do Grau Proficiencia
     */
    @Override
    public String toString() {
        return String.format("%s - %s - %s  - CompetenciaTecnica: %s", this.getValor(), this.getDesignacao(), this.getCompT().toString());
    }

    /**
     * @return the valor
     */
    public String getValor() {
        return valor;
    }

    /**
     * @return the designacao
     */
    public String getDesignacao() {
        return designacao;
    }

    /**
     * @return the compT
     */
    public CompetenciaTecnica getCompT() {
        return compT;
    }
    
        /**
     * Compara a competência técnica com o objeto recebido.
     *
     * @param o objeto a comparar com a competência técnica.
     * @return true se o objeto recebido representar uma competência técnica
     * equivalente à competência técnica. Caso contrário, retorna false.
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
        GrauProficiencia obj = (GrauProficiencia) o;
        return (Objects.equals(getValor(), obj.getValor()) && Objects.equals(getCompT().getId(), obj.getCompT().getId()));
    }

    

}
