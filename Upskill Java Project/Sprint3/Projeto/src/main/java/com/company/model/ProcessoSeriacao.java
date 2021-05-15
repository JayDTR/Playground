/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.sql.Date; 
import java.util.List;

/**
 *
 * @author joaor
 */
public class ProcessoSeriacao {
    
    private Date dataRealizacao;
    private Colaborador colab;
    private Anuncio anuncio;
    private List<Colaborador> participantes;
    private ArrayList<Classificacao> classificacoes;
    
    public ProcessoSeriacao(Date dataRealizacao,Colaborador colab, Anuncio anuncio, List<Colaborador> participantes,ArrayList<Classificacao> classificacoes){
        if(validaData(dataRealizacao,colab,anuncio,participantes,classificacoes)){
            setDataRealizacao(dataRealizacao);
            setColab(colab);
            setAnuncio(anuncio);
            this.classificacoes = new ArrayList<>();
            this.participantes = new ArrayList<>();
            setParticipantes(participantes);
            setClassificacoes(classificacoes);
        }
    }

    private boolean validaData(Date dataReconhecimento, Colaborador colab, Anuncio anuncio, List<Colaborador> participantes, ArrayList<Classificacao> classificacoes) {
        if(dataReconhecimento == null || colab == null || anuncio==null || participantes==null || classificacoes==null){
            throw new IllegalArgumentException("Nenhum dos Argumentos pode estar nulo ou vazio.");
        }
        return true;
    }

    /**
     * @param dataReconhecimento the dataReconhecimento to set
     */
    public void setDataRealizacao(Date dataRealizacao) {
        this.dataRealizacao = dataRealizacao;
    }

    /**
     * @param colab the colab to set
     */
    public void setColab(Colaborador colab) {
        this.colab = colab;
    }

    /**
     * @param anuncio the anuncio to set
     */
    public void setAnuncio(Anuncio anuncio) {
        this.anuncio = anuncio;
    }

    /**
     * @param participantes the participantes to set
     */
    public void setParticipantes(List<Colaborador> participantes) {
        this.participantes.addAll(participantes);
    }

    /**
     * @param classificacoes the classificacoes to set
     */
    public void setClassificacoes(ArrayList<Classificacao> classificacoes) {
        this.classificacoes.addAll(classificacoes);
    }
    
    
    /**
     * @return the dataRealizacao
     */
    public Date getDataRealizacao() {
        return dataRealizacao;
    }

    /**
     * @return the colab
     */
    public Colaborador getColab() {
        return colab;
    }

    /**
     * @return the anuncio
     */
    public Anuncio getAnuncio() {
        return anuncio;
    }

    /**
     * @return the participantes
     */
    public List<Colaborador> getParticipantes() {
        return participantes;
    }

    /**
     * @return the classificacoes
     */
    public ArrayList<Classificacao> getClassificacoes() {
        return classificacoes;
    }
            
     /**
     * Compara a categoria com o objeto recebido.
     *
     * @param o objeto a comparar com a categoria.
     * @return true se o objeto recebido representar uma classificacao equivalente à
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
        ProcessoSeriacao obj = (ProcessoSeriacao) o;
        //return (Objects.equals(id, obj.id));
        return (this.getAnuncio().getTarefa().getRef().equals(obj.getAnuncio().getTarefa().getRef()) && this.getColab().equals(obj.getColab()));
    }

    
    
}
