/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.controller;

import com.company.model.Anuncio;
import com.company.model.Candidatura;
import com.company.model.CaraterCT;
import com.company.model.Classificacao;
import com.company.model.CompetenciaTecnica;
import com.company.model.Freelancer;
import com.company.model.ListaCandidaturas;
import com.company.model.Organizacao;
import com.company.model.Plataforma;
import com.company.model.ReconhecimentoCT;
import com.company.model.RegistoAnuncios;
import com.company.utils.Constantes;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Asus
 */
public class EfetuarCandidaturaController {

    private Candidatura cand;
    private ListaCandidaturas lCand;
    private RegistoAnuncios ra;
    private final Plataforma plat;
    private List<Candidatura> listaCand;
    private Freelancer user;

    /**
     * Declaração de uma organização.
     */
    private Organizacao org;

    /**
     * Constrói uma instância de FetuarCandidaturaTarefaController. Valida o
     * acesso do utilizador 'Administrador' para efectuar o registo de
     * categorias de tarefa.
     */
    public EfetuarCandidaturaController() throws SQLException {
        if (!Plataforma.getInstance().getUsersAPI().getRole().equalsIgnoreCase(Constantes.ROLE_FREELANCER)) {
            throw new IllegalStateException("Utilizador não Autorizado");
        }

        listaCand = new ArrayList<>();
        this.plat = Plataforma.getInstance();
        this.lCand = new ListaCandidaturas();
        this.ra = new RegistoAnuncios();
        user = getFreelancerByEmail();
        listaCand = lCand.getAll();
    }

    public boolean novaCandidatura(Anuncio anun, double valorPretendido, int nrDias, String txtApr, String txtMotiv,
            Freelancer free) throws SQLException, Exception {
        try {
            Date dtCandidatura = Date.valueOf(LocalDate.now());
            Candidatura cand = this.plat.getLcand().novaCandidatura(anun, dtCandidatura, valorPretendido, nrDias, txtApr, txtMotiv, free);
            return this.plat.getLcand().registaCandidatura(cand);
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    public List<Anuncio> getAnunDisp(Date date) {
        return this.plat.getRa().getAnunciosDisp(date);
    }

    public Anuncio getAnuncioByRef(String ref) throws SQLException { // incompleto. falta verificaçao das datas e das competencias
        for (Organizacao orga : plat.getRo().getAllOrgs()) {
            for (Anuncio anun : orga.getRegistoAnuncios().getAll()) {
                if (anun.getTarefa().getRef().equalsIgnoreCase(ref)) {
                    return anun;
                }
            }
        }
        return null;
    }

    public List<String> getAllAnuncioByDesig() throws SQLException {
        List<String> lista3 = new ArrayList<>();
        String idDesig = "";
        for (Anuncio anun : ra.getAll()) {
            idDesig = "Ref: " + anun.getTarefa().getRef();
            lista3.add(idDesig);
        }
        return lista3;
    }

    public Freelancer getFreelancerByEmail() throws SQLException {
        String email = plat.getUsersAPI().getEmail();

        return this.plat.getRegFree().getFreelancerByEmail(email);
    }

    public List<Candidatura> getAllCandidaturas() throws SQLException {
        List<Candidatura> tmp = new ArrayList<>();
        for (Candidatura cand : listaCand) {
            if (cand.getFree().getNif().equalsIgnoreCase(getFreelancerByEmail().getNif())) {
                tmp.add(cand);
            }
        }
        return tmp;
    }

    public List<Anuncio> getAllAnunciosDisponiveis() throws SQLException {
        List<Anuncio> tmp = new ArrayList<>();
        LocalDate lNow = LocalDate.now();
        Date now = Date.valueOf(lNow);
        Organizacao orga = plat.getRo().getAllOrgs().get(0);

        for (Anuncio anun : orga.getRegistoAnuncios().getAll()) {
            if (anun.getdInicioCandidatura().before(now) && anun.getdFimCandidatura().after(now)) {
                if (!hasCandidatura(anun)) {
                    tmp.add(anun);
                }
            }
        }
        return tmp;
    }

    public boolean hasCandidatura(Anuncio anun) throws SQLException {
        for (Candidatura cand : listaCand) {
            if (cand.getFree().getEmail().equalsIgnoreCase(user.getEmail()) && cand.getAnuncio().getTarefa().getRef().equalsIgnoreCase(anun.getTarefa().getRef())) {
                return true;
            }
        }
        return false;
    }

    public boolean isFreelancerIlegivel(Anuncio anun, Freelancer free) throws SQLException {
        int min = 0, rec = 0;
        boolean flag = true;
        List<CaraterCT> ctAnun = new ArrayList<>();
        List<ReconhecimentoCT> ctFree = new ArrayList<>();

        for (CaraterCT cCT : anun.getTarefa().getCatTarefa().getLcompTec()) {
            if (cCT.getObrigatorio() == 1) {
                ctAnun.add(cCT);
            }
        }

        for (CaraterCT cCT : ctAnun) {
            CompetenciaTecnica ct = plat.getRct().getCompetenciaTecnicaById(cCT.getCodCompetenciaTecnica());
            min = ct.getListaGrausProficiencia().indexOf(ct.getGrausProficiencia().getGrauProficienciaFromCTByValor(ct.getId(), cCT.getGrauMinimoProficiencia()));
            for (ReconhecimentoCT rCT : free.getlRecFree()) {
                if (rCT.getGrauReconhecido().getCompT().getId().equalsIgnoreCase(cCT.getCodCompetenciaTecnica())) {
                    rec = ct.getListaGrausProficiencia().indexOf(ct.getGrausProficiencia().getGrauProficienciaFromCTByValor(rCT.getGrauReconhecido().getCompT().getId(), rCT.getGrauReconhecido().getValor()));
                    rec = ct.getListaGrausProficiencia().indexOf(ct.getGrausProficiencia().getGrauProficienciaFromCTByValor(rCT.getGrauReconhecido().getCompT().getId(), rCT.getGrauReconhecido().getValor()));
                    if (rec >= min) {
                        flag = true;
                    }
                } else {
                    flag = false;
                }
            }
            if (flag == false) {
                break;
            }
        }

        return flag;
    }
}
