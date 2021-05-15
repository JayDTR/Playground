/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.model;

import java.sql.Date;

/**
 *
 * @author Asus
 */
public class Classificacao {

    private int lugarClassificacao;
    private Date dataHora;
    private Candidatura candidatura;

    public Classificacao(int lugarClassificacao, Date dataHora, Candidatura candidatura) {
        if (validadeData(lugarClassificacao, dataHora, candidatura)) {
            setLugarClassificacao(lugarClassificacao);
            setDataHora(dataHora);
            setCandidatura(candidatura);
        }
    }

    private boolean validadeData(int lugarClassificacao, Date dataHora, Candidatura candidatura) {
        if (lugarClassificacao == 0 || dataHora == null || candidatura == null) {
            throw new IllegalArgumentException("Nenhum dos Argumentos pode estar nulo ou vazio.");
        }
        return true;
    }

    /**
     * @return the lugarClassificacao
     */
    public int getLugarClassificacao() {
        return lugarClassificacao;
    }

    /**
     * @param lugarClassificacao the lugarClassificacao to set
     */
    public void setLugarClassificacao(int lugarClassificacao) {
        this.lugarClassificacao = lugarClassificacao;
    }

    /**
     * @return the dataHora
     */
    public Date getDataHora() {
        return dataHora;
    }

    /**
     * @param dataHora the dataHora to set
     */
    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    /**
     * @return the dataHora
     */
    public Candidatura getCandidatura() {
        return candidatura;
    }

    /**
     * @param dataHora the dataHora to set
     */
    public void setCandidatura(Candidatura candidatura) {
        this.candidatura = candidatura;
    }

    /**
     * Compara a categoria com o objeto recebido.
     *
     * @param o objeto a comparar com a categoria.
     * @return true se o objeto recebido representar uma classificacao
     * equivalente à categoria. Caso contrário, retorna false.
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
        Classificacao obj = (Classificacao) o;
        //return (Objects.equals(id, obj.id));
        return (this.getLugarClassificacao() == obj.getLugarClassificacao() 
                && this.getCandidatura().getAnuncio().getTarefa().getRef().equalsIgnoreCase(obj.getCandidatura().getAnuncio().getTarefa().getRef()));
    }

    @Override
    public String toString() {
        return String.format("Classificação: %d"
                + "\nValor: %.1f"
                + "\nFreelancer NIF: %s", this.lugarClassificacao, this.candidatura.getValorPretendido(), this.candidatura.getFree().getNif());
    }
}
