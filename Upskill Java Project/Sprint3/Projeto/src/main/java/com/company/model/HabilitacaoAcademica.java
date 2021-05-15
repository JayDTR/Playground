/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.model;

import java.util.Objects;

/**
 *
 * @author Asus
 */
public class HabilitacaoAcademica {

    private static final String PATTERN_NOME = "[a-zA-Z ]+";
    private String grau;
    private String nomeCurso;
    private String faculdade;
    private double media;

    public HabilitacaoAcademica(String grau, String nomeCurso, String faculdade, double media) {
        if (validadeData(grau, nomeCurso, faculdade, media)) {
            setGrau(grau);
            setNomeCurso(nomeCurso);
            setFaculdade(faculdade);
            setMedia(media);
        }
    }

    private boolean validadeData(String grau, String nomeCurso, String faculdade, double media) {
        if (grau.isEmpty() || nomeCurso.isEmpty() || faculdade.isEmpty()) {
            throw new IllegalArgumentException("Nenhum dos Argumentos podem estar vazios");
        }
        return true;
    }

    /**
     * @return the grau
     */
    public String getGrau() {
        return grau;
    }

    /**
     * @param grau the grau to set
     */
    public void setGrau(String grau) {
        this.grau = grau;
    }

    /**
     * @return the nomeCurso
     */
    public String getNomeCurso() {
        return nomeCurso;
    }

    /**
     * @param nomeCurso the nomeCurso to set
     */
    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    /**
     * @return the faculdade
     */
    public String getFaculdade() {
        return faculdade;
    }

    /**
     * @param faculdade the faculdade to set
     */
    public void setFaculdade(String faculdade) {
        if (faculdade.matches(PATTERN_NOME)) {
            this.faculdade = faculdade;
        } else {
            throw new IllegalArgumentException("Faculdade não deve conter números.");
        }
    }

    /**
     * @return the media
     */
    public double getMedia() {
        return media;
    }

    /**
     * @param media the media to set
     */
    public void setMedia(double media) {
        if (media < 0) {
            throw new IllegalArgumentException("Media não pode ser negativa");
        } else if (media > 20) {
            throw new IllegalArgumentException("Media não pode ser superior a 20");
        } else {
            this.media = media;
        }
    }

    /**
     * Compara a Habilitação Academica com o objeto recebido.
     *
     * @param o objeto a comparar com a competência técnica.
     * @return true se o objeto recebido representar uma Habilitação Academica
     * equivalente à Habilitação Academica. Caso contrário, retorna false.
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
        HabilitacaoAcademica obj = (HabilitacaoAcademica) o;
        return (Objects.equals(getGrau(), obj.getGrau())
                && Objects.equals(getNomeCurso(), obj.getNomeCurso())
                && Objects.equals(getFaculdade(), obj.getFaculdade())
                && Objects.equals(getMedia(), obj.getMedia()));
    }

    public String toString() {
        return String.format("Grau académico: %s\nNome do curso: %s\nFaculdade: %s\nMedia: %d\n", this.grau, this.nomeCurso, this.faculdade, this.media);
    }
}
