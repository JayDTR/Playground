/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.model;

import com.company.data.CandidaturaDB;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Asus
 */
public class ListaCandidaturas { // vou ter uma lista de anuncios (cuja data de inicio de candidatura seja inferior à do sistema)
    // à lista de anuncios vou retirar os anuncios cuja tarefa exija competencias tecnicas cujo grau de proficiencia esteja acima do do freelancer
    private ArrayList<Candidatura> lCand;
    
    private CandidaturaDB candDB;
    
    public ListaCandidaturas() {
        this.lCand = new ArrayList<>();
        this.candDB = new CandidaturaDB();
        //this.aaDB = new RepositorioAreaAtividadeDB();
    }
        
    public ArrayList<Candidatura> getLcand() {
        ArrayList<Candidatura> lc = new ArrayList<>();
        lc.addAll(this.lCand);
        return lc;
    }
    
    public Candidatura novaCandidatura(Anuncio anun, Date dtCandidatura, double valorPretendido, int nrDias, String txtApr, String txtMotiv, Freelancer free) throws SQLException {
        Candidatura cand = new Candidatura(anun, dtCandidatura, valorPretendido, nrDias, txtApr, txtMotiv, free);
        return cand;
        //return new Candidatura(anun, dtCandidatura, valorPretendido, nrDias, txtApr, txtMotiv, free);
    }
    
    public boolean registaCandidatura(Candidatura cand) throws SQLException {
        if (this.validaCandidatura(cand)) {
            candDB.save(cand);
            return true;
        }
        return false;
    }
    
    public Candidatura getCandidaturaById(int id) throws SQLException {
        return candDB.find(id);
    }
    
    public boolean validaCandidatura(Candidatura cand) {
        boolean bRet = true;
        for (Candidatura c : lCand) {
            if (c.getAnuncio().getTarefa().getRef().equalsIgnoreCase(cand.getAnuncio().getTarefa().getRef())) {
                bRet = false;
                throw new IllegalArgumentException("Já existe outra candidatura a este anuncio.");
            }
        }
        return bRet;
    }
    
    private boolean addCandidatura(Candidatura cand) {
        if(this.validaCandidatura(cand)) {
            return lCand.add(cand);
        }
        return false;
    }
    
//    public boolean freelancerIlegivel() {
//        boolean flag = true;
//        
//    }
    
    public List<Candidatura> getAll() throws SQLException {
        List<Candidatura> listaCandidaturas = this.candDB.getAll();
        return listaCandidaturas;
    }
    
    public int getCandId(Candidatura cand) throws SQLException{
        return candDB.getIdCand(cand);
    }
}
