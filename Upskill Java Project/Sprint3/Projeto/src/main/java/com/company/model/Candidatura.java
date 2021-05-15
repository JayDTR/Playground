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
public class Candidatura implements Comparable<Candidatura>{
    private Anuncio anun;
    private Date dtCandidatura;
    private double valorPretendido;
    private int nrDias;
    private String txtApr;
    private String txtMotiv;
    private Freelancer free;
    
    public Candidatura(Anuncio anun, Date dtCandidatura, double valorPretendido, int nrDias, String txtApr, String txtMotiv, Freelancer free) {
        if(validateData(anun, dtCandidatura, valorPretendido, nrDias, free)) {
            this.anun = anun;
            this.dtCandidatura = dtCandidatura;
            this.valorPretendido = valorPretendido;
            this.nrDias = nrDias;
            this.txtApr = txtApr;
            this.txtMotiv = txtMotiv;
            this.free = free;
        }
    }
    
    private boolean validateData(Anuncio anun, Date dtCandidatura, double valorPretendido, int nrDias, Freelancer free) {
        if ((anun == null) || (dtCandidatura == null) || (valorPretendido == 0) || (nrDias == 0) || (free == null)) {
            throw new IllegalArgumentException("Nenhum dos argumentos pode ser nulo ou vazio.");
        } else {
            return true;
        }
    }

    /**
     * @return the anuncioId
     */
    public Anuncio getAnuncio() {
        return anun;
    }

    /**
     * @param anuncioId the anuncioId to set
     */
    public void setAnuncio(Anuncio anuncio) {
        this.anun = anuncio;
    }

    /**
     * @return the dtCandidatura
     */
    public Date getDtCandidatura() {
        return dtCandidatura;
    }

    /**
     * @param dtCandidatura the dtCandidatura to set
     */
    public void setDtCandidatura(Date dtCandidatura) {
        this.dtCandidatura = dtCandidatura;
    }

    /**
     * @return the valorPretendido
     */
    public double getValorPretendido() {
        return valorPretendido;
    }

    /**
     * @param valorPretendido the valorPretendido to set
     */
    public void setValorPretendido(double valorPretendido) {
        this.valorPretendido = valorPretendido;
    }

    /**
     * @return the nrDias
     */
    public int getNrDias() {
        return nrDias;
    }

    /**
     * @param nrDias the nrDias to set
     */
    public void setNrDias(int nrDias) {
        this.nrDias = nrDias;
    }

    /**
     * @return the txtApr
     */
    public String getTxtApr() {
        return txtApr;
    }

    /**
     * @param txtApr the txtApr to set
     */
    public void setTxtApr(String txtApr) {
        this.txtApr = txtApr;
    }

    /**
     * @return the txtMotiv
     */
    public String getTxtMotiv() {
        return txtMotiv;
    }

    /**
     * @param txtMotiv the txtMotiv to set
     */
    public void setTxtMotiv(String txtMotiv) {
        this.txtMotiv = txtMotiv;
    }

    /**
     * @return the free
     */
    public Freelancer getFree() {
        return free;
    }

    /**
     * @param free the free to set
     */
    public void setFree(Freelancer free) {
        this.free = free;
    }
    
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
        Candidatura obj = (Candidatura) o;
        //return (Objects.equals(id, obj.id));
        return this.anun.equals(obj.getAnuncio()) && this.free.equals(obj.getFree()) && this.nrDias == obj.nrDias && this.valorPretendido == obj.valorPretendido;
    }
    
    @Override
    public String toString() {
        return String.format("Valor Pretendido: %.1f POTs"
                + "\nTempo de execucao: %d Dias"
                + "\nFreelancer"
                + "\nNome: %s"
                + "\nNIF: %s"
                + "\nEmail: %s"
                + "\n", valorPretendido, nrDias, free.getNome(),free.getNif(),free.getEmail());
    }

    @Override
    public int compareTo(Candidatura o) {
        Double a = getValorPretendido();
        Double b = o.getValorPretendido();
        int c = a.compareTo(b);
        return c;
    }
}