/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.model;

import com.company.data.FreelancerDB;
import java.sql.SQLException;
import java.util.ArrayList;
import com.company.utils.Constantes;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Asus
 */
public class RegistoFreelancers { // falta a adição de competências técnicas + graus de proficiencia. ver como se quer implementar (via reconhecimentoCT ?)

    private FreelancerDB fDB;

    public RegistoFreelancers() {
        this.fDB = new FreelancerDB();
    }

    public List<Freelancer> getAllFreelancers() throws SQLException {
        return fDB.getAllFreelancers();
    }

    public Freelancer novoFreelancer(String nome, String nif, String telefone, String email, String morada, String codPostal, String localidade, ArrayList<HabilitacaoAcademica> lHabAc, ArrayList<ReconhecimentoCT> lRecFree) /*throws SQLException*/ {
        EnderecoPostal endPostal = Freelancer.createEndPostal(morada, codPostal, localidade);
        return new Freelancer(nome, nif, email, telefone, endPostal, lHabAc, lRecFree);
    }

    public boolean registaFreelancer(Freelancer free) throws SQLException, Exception {
        if (this.validaFreelancer(free)) {
            try {
                fDB.add(free);
                Plataforma.getInstance().getUsersAPI().registerUserWithRoles(free.getNome(), free.getEmail(), Plataforma.getInstance().getAlgoritmoGeradorPwd().geraPassword(), Constantes.ROLE_FREELANCER);
            }catch (Exception e){
                throw new Exception (e);
            }
            return true;
        }
        return false;
    }

    public boolean validaFreelancer(Freelancer free) throws SQLException {
        boolean bRet = true;
        for (Freelancer f : getAllFreelancers()) {
            if (f.getNif().equalsIgnoreCase(free.getNif())) {
                bRet = false;
                throw new IllegalArgumentException("Já existe outro freelancer com este nif.");
            }
        }
        return bRet;
    }
    
        /**
     * Devolve um colaborador existente na lista de colaboradores com e-mail
     * igual ao passado por parâmetro.
     *
     * @param email e-mail do colaborador da organização
     * @return colaborador, ou null caso o e-mail não exista na lista
     */
    public Freelancer getFreelancerByEmail(String email) throws SQLException {
        for (Freelancer free : fDB.getAllFreelancers()) {
            if (free.hasEmail(email)) {
                return free;
            }
        }
        return null;
    }
    
    public Freelancer getFreelancerByNif(int NIF) throws SQLException {
        for (Freelancer free : fDB.getAllFreelancers()) {
            if (Integer.valueOf(free.getNif()) == NIF) {
                return free;
            }
        }
        return null;
    }
}
