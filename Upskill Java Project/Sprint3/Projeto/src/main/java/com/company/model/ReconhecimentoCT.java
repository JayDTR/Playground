/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.model;

import java.sql.Date;
import java.util.Calendar;
import java.util.Objects;

/**
 *
 * @author Asus
 */
public class ReconhecimentoCT {

    private Date dataReconhecimento;
    private GrauProficiencia grauReconhecido;

    public ReconhecimentoCT(Date dataReconhecimento, GrauProficiencia grauReconhecido) {
        if (validaData(dataReconhecimento, grauReconhecido)) {
            setDataReconhecimento(dataReconhecimento);
            setGrauReconhecido(grauReconhecido);
        }
    }

    private boolean validaData(Date dataReconhecimento, GrauProficiencia grauReconhecido) {
        if (dataReconhecimento == null  || grauReconhecido == null) {
            throw new IllegalArgumentException("Nenhum dos argumentos pode ser nulo ou vazio.");
        }
        return true;
    }

    /**
     * @return the dataReconhecimento
     */
    public Date getDataReconhecimento() {
        return dataReconhecimento;
    }

    /**
     * @param dataReconhecimento the dataReconhecimento to set
     */
    public void setDataReconhecimento(Date dataReconhecimento) {
        Date now = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        if (dataReconhecimento.after(now)) {
            throw new IllegalArgumentException("Data de Reconhecimento não pode ser superior a data de hoje.");
        } else {
            this.dataReconhecimento = dataReconhecimento;
        }
    }

    /**
     * @return the grauReconhecido
     */
    public GrauProficiencia getGrauReconhecido() {
        return grauReconhecido;
    }

    /**
     * @param grauReconhecido the grauReconhecido to set
     */
    public void setGrauReconhecido(GrauProficiencia grauReconhecido) {
        this.grauReconhecido = grauReconhecido;
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
        ReconhecimentoCT obj = (ReconhecimentoCT) o;
        return (Objects.equals(getGrauReconhecido(), obj.getGrauReconhecido()));
    }

}
