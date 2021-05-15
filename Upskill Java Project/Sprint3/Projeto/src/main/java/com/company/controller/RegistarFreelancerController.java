/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.controller;

import com.company.model.CompetenciaTecnica;
import com.company.model.Freelancer;
import com.company.model.GrauProficiencia;
import com.company.model.HabilitacaoAcademica;
import com.company.model.Plataforma;
import com.company.model.ReconhecimentoCT;
import com.company.model.RegistoFreelancers;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author joaor
 */
public class RegistarFreelancerController {

    private Plataforma plat;
    private RegistoFreelancers rfr;
    private ArrayList<HabilitacaoAcademica> lHabAc;
    private ArrayList<ReconhecimentoCT> lRecFree;
    private Freelancer free;
    private CompetenciaTecnica ct;

    public RegistarFreelancerController() {
        this.plat = Plataforma.getInstance();
        this.rfr = plat.getRegFree();
        this.lHabAc = new ArrayList<>();
        this.lRecFree = new ArrayList<>();
    }

    public boolean novoFreelancer(String nome, String nif, String email, String telefone, String morada, String codPostal, String localidade) throws SQLException, Exception {
        free = rfr.novoFreelancer(nome, nif, telefone, email, morada, codPostal, localidade, lHabAc, lRecFree);
        return registaFreelancer(free);
    }

    public boolean registaFreelancer(Freelancer free) throws SQLException, Exception {
        return rfr.registaFreelancer(free);
    }

    public List<Freelancer> getListFreelancers() throws SQLException {
        return rfr.getAllFreelancers();
    }

    public boolean novaHabilitacaoAcademica(String nomeCurso, String faculdade, String grau, String media) throws Exception {
        boolean flag = false;
        HabilitacaoAcademica hAc = null;
        try {
             hAc = Freelancer.createHabAcademica(nomeCurso, faculdade, grau, Double.valueOf(media));
        } catch (Exception e) {
            throw new Exception(e);
        }
        return lHabAc.add(hAc);
    }

    public boolean novoReconhecimentoCT(Date dataReconhecimento, GrauProficiencia grau) {
        boolean flag = false;
        try {
            ReconhecimentoCT rCT = Freelancer.createRecCT(dataReconhecimento, grau);
            lRecFree.add(rCT);
            flag = true;
        } catch (Exception e) {
            throw e;
        }
        return flag;
    }
    
     /**
     * Devolve a lista de descrições e respectivos id's, de áreas de atividade.
     * 
     * @return lista de descrições e respectivos id's, de áreas de atividade, do tipo ArrayList
     */
    public ArrayList<String> getListaCompTecnicaIdDescBreve() throws SQLException {
        List<CompetenciaTecnica> list = new ArrayList<CompetenciaTecnica>(getCompetenciasTecnicas());
        String idDescBreve = "";
        ArrayList<String> listd = new ArrayList<>();
        for (CompetenciaTecnica ct : list) {
            idDescBreve = "ID: " + ct.getId() + " \n" + "DescBreve: " + ct.getDescBreve();
            listd.add(idDescBreve);
        }

        return listd;
    }
    
    
    
     /**
     * Devolve a lista de descrições e respectivos id's, de áreas de atividade.
     * 
     * @return lista de descrições e respectivos id's, de áreas de atividade, do tipo ArrayList
     */
    public ArrayList<String> getListaGrauProfValorDesig(CompetenciaTecnica ct) throws SQLException {
        List<GrauProficiencia> list = new ArrayList<GrauProficiencia>(getGrausProfSelectedCT(ct));
        String idDescBreve = "";
        ArrayList<String> listd = new ArrayList<>();
        for (GrauProficiencia gp : list) {
            idDescBreve = "Valor: " + gp.getValor() + " \n" + "Designacao: " + gp.getDesignacao();
            listd.add(idDescBreve);
        }

        return listd;
    }
    
    /**
     * Devolve uma lista de áreas de atividade existentes na plataforma.
     * 
     * @return lista de áreas de atividade existentes na plataforma
     */
    public List<CompetenciaTecnica> getCompetenciasTecnicas() throws SQLException {
        return this.plat.getRct().getListaCompTec();
    }
    
    /**
     * Devolve uma lista de áreas de atividade existentes na plataforma.
     * 
     * @return lista de CompetenciasTecnicas existentes na plataforma
     */
    public List<GrauProficiencia> getGrausProfSelectedCT(CompetenciaTecnica ct) throws SQLException {
        return ct.getListaGrausProficiencia();
    }

    public CompetenciaTecnica getCompTecnicaByID(String id) throws SQLException {
        
        for (CompetenciaTecnica cta : getCompetenciasTecnicas()) {
            if(cta.getId().equalsIgnoreCase(id))
            {
                ct = cta;
                return ct;
            }
        }
        throw new IllegalArgumentException("Nenhuma comptecnica com este id");
    }

    public GrauProficiencia getSelectedGrau(String valor) throws SQLException {
        for (GrauProficiencia gp : getGrausProfSelectedCT(ct)) {
            if(gp.getValor().equalsIgnoreCase(valor)){
                return gp;
            }
        }
        throw new IllegalArgumentException("Nenhuma grau proficiencia com este id");
    }


}
