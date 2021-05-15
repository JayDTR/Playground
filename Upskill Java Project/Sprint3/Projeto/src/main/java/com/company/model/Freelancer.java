/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.model;

import java.util.ArrayList;
import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author Asus
 */
public class Freelancer {

    private static final String PATTERN_NUM = "[0-9]+";
    private static final String PATTERN_NOME = "[a-zA-Z ]+";
    private static final String PATTERN_EMAIL = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$";
    private String nome;
    private String nif;
    private String email;
    private String telefone;
    private EnderecoPostal endPostal;
    private ArrayList<HabilitacaoAcademica> lHabAc;
    private ArrayList<ReconhecimentoCT> lRecFree;

    public Freelancer(String nome, String nif, String email, String telefone, EnderecoPostal codPostal, ArrayList<HabilitacaoAcademica> lHabAc, ArrayList<ReconhecimentoCT> lRecFree) {
        if (validaData(nome, nif, email, telefone, codPostal, lHabAc, lRecFree)) {
            this.lHabAc = new ArrayList<>();
            this.lRecFree = new ArrayList<>();
            setNome(nome);
            setNif(nif);
            setEmail(email);
            setTelefone(telefone);
            setEndPostal(codPostal);
            setlHabAc(lHabAc);
            setlRecFree(lRecFree);
        }
    }

    public static HabilitacaoAcademica createHabAcademica(String grau, String nomeCurso, String fac, Double media) {
        return new HabilitacaoAcademica(grau, nomeCurso, fac, media);
    }

    public static ReconhecimentoCT createRecCT(Date dataReconhecimento, GrauProficiencia grau) {
        return new ReconhecimentoCT(dataReconhecimento, grau);
    }

    public static EnderecoPostal createEndPostal(String morada, String codPostal, String localidade) {
        return new EnderecoPostal(morada, codPostal, localidade);
    }

    private boolean validaData(String nome, String nif, String email, String telefone, EnderecoPostal endPostal, ArrayList<HabilitacaoAcademica> lHabAc, ArrayList<ReconhecimentoCT> lRecFree) {
        if (nome.isEmpty() || nif.isEmpty() || email.isEmpty() || telefone.isEmpty()
                || endPostal == null || lHabAc == null || lRecFree == null) {
            throw new IllegalArgumentException("Nenhum dos dados podem ser vazios ou null");
        }
        return true;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        if (nome.matches(PATTERN_NOME)) {
            this.nome = nome;
        } else {
            throw new IllegalArgumentException("Nome não deve conter números.");
        }
    }

    /**
     * @return the nif
     */
    public String getNif() {
        return nif;
    }

    /**
     * @param nif the nif to set
     */
    public void setNif(String nif) {
        if (nif.matches(PATTERN_NUM)) {
            this.nif = nif;
        } else {
            throw new IllegalArgumentException("NIF deve ser do tipo numérico.");
        }
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        if (email.matches(PATTERN_EMAIL)) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Email Invalido");
        }
    }

    /**
     * @return the telefone
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * @param telefone the telefone to set
     */
    public void setTelefone(String telefone) {
        if (telefone.matches(PATTERN_NUM)) {
            this.telefone = telefone;
        } else {
            throw new IllegalArgumentException("Telefone deve ser do tipo numérico.");
        }
    }

    /**
     * @return the endPostal
     */
    public EnderecoPostal getEndPostal() {
        return endPostal;
    }

    /**
     * @param endPostal the endPostal to set
     */
    public void setEndPostal(EnderecoPostal endPostal) {
        this.endPostal = endPostal;
    }

    /**
     * @return the lHabAc
     */
    public ArrayList<HabilitacaoAcademica> getlHabAc() {
        return lHabAc;
    }

    /**
     * @param lHabAc the lHabAc to set
     */
    public void setlHabAc(ArrayList<HabilitacaoAcademica> lHabAc) {
        this.lHabAc = lHabAc;
    }

    /**
     * @param lRecFree the lRecFree to set
     */
    public void setlRecFree(ArrayList<ReconhecimentoCT> lRecFree) {
        this.lRecFree.addAll(lRecFree);
    }

    /**
     * @return the lRecFree
     */
    public ArrayList<ReconhecimentoCT> getlRecFree() {
        ArrayList<ReconhecimentoCT> listaCopy = new ArrayList(lRecFree);
        return listaCopy;
    }
    
    public boolean hasEmail(String email) {
        return this.email.equalsIgnoreCase(email);
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
        Freelancer obj = (Freelancer) o; // em baixo deve bastar o email e o nif, verificar depois
        return (Objects.equals(email, obj.email) && Objects.equals(nome, obj.nome) && Objects.equals(nif, obj.nif));
    }

    @Override
    public String toString() {
        return String.format("Nome: %s\nNIF: %s\nEmail: %s\nTelefone: %s\n", this.nome, this.nif, this.email, this.telefone);
    }

}
