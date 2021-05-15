/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.controller;

import com.company.model.Anuncio;
import com.company.model.Candidatura;
import com.company.model.Classificacao;
import com.company.model.Colaborador;
import com.company.model.Organizacao;
import com.company.model.Plataforma;
import com.company.model.ProcessoSeriacao;
import com.company.utils.Constantes;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author joaor
 */
public class EspecificarSeriacaoController {

    /**
     * Inicializa a plataforma com uma cópia com a mesma assinatura.
     */
    private Plataforma plat = Plataforma.getInstance();

    /**
     * Declaração de uma organização.
     */
    private Organizacao org;

    private List<ProcessoSeriacao> listaProcessosSer;
    private List<Candidatura> listaCand;
    private ArrayList<Classificacao> tmpListaClass;
    private int pos = 1;
    private Anuncio anuncio = null;

    /**
     * Constrói uma instância de EspecificarTarefaController. Valida o acesso do
     * utilizador ao registar das tarefas.
     */
    public EspecificarSeriacaoController() throws SQLException {
        if (!Plataforma.getInstance().getUsersAPI().getRole().equalsIgnoreCase(Constantes.ROLE_COLABORADOR_ORGANIZACAO)
                && !Plataforma.getInstance().getUsersAPI().getRole().equalsIgnoreCase(Constantes.ROLE_GESTOR_ORGANIZACAO)) {
            throw new IllegalStateException("Utilizador não Autorizado");
        }
        listaProcessosSer = new ArrayList<>();
        listaCand = new ArrayList<>();
        tmpListaClass = new ArrayList<>();
        pos = 1;
        this.org = plat.getRo().getOrganizacaoByEmailUtilizador(this.plat.getUsersAPI().getEmail());
        listaProcessosSer = org.getListaProcessosSeriacao().getProcessosSeriacaoByOrgNif(this.org.getNif());
    }

    public boolean novaProcessoSeriacao(List<Colaborador> participantes) throws Exception {
        try {
            LocalDate now = LocalDate.now();
            Date dNow = Date.valueOf(now);
            ProcessoSeriacao pS = new ProcessoSeriacao(dNow, org.getRepositorioColaborador().getColaboradorByEmail(this.plat.getUsersAPI().getEmail()), anuncio, participantes, tmpListaClass);

            return org.getListaProcessosSeriacao().registaProcessoSeriacao(pS, org.getNif());
        } catch (Exception ex) {
             throw new Exception(ex);
        }
    }

    public List<ProcessoSeriacao> getListaSeriados() throws SQLException {
        return org.getListaProcessosSeriacao().getProcessosSeriacaoByOrgNif(org.getNif());
    }

    public List<Candidatura> getListCandidaturas() {
        return listaCand;
    }

    public List<Classificacao> getListClassif() {
        return tmpListaClass;
    }

    public boolean hasProcSeriacao(Anuncio anun) {
        boolean flag = false;
        Date now = Date.valueOf(LocalDate.now());
        if (anun.getdInicioSeriacao().after(now) || anun.getdFimSeriacao().before(now) && anun.getColab().getEmail().equalsIgnoreCase(plat.getUsersAPI().getEmail())) {
            flag = true;
        } else {
            if (listaProcessosSer.size() == 0) {
                flag = false;
            }
            for (ProcessoSeriacao pS : listaProcessosSer) {
                if (pS.getAnuncio().getTarefa().getRef().equalsIgnoreCase(anun.getTarefa().getRef())) {
                    flag = true;
                }
            }
        }

        return flag;
    }

    public void setListaCand(Anuncio anuncio) throws SQLException {
        this.anuncio = anuncio;
        List<Candidatura> tmp = new ArrayList<>();
        for (Candidatura cand : plat.getLcand().getAll()) {
            Anuncio a = cand.getAnuncio();
            Anuncio b = anuncio;
            if (cand.getAnuncio().equals(anuncio)) {
                tmp.add(cand);
            }
        }
        listaCand.addAll(tmp);
    }

    /**
     * Adiciona a competência técnica com a respectiva obrigatoriedade e grau de
     * proficiencia à lista de competências técnicas.
     *
     * @param ct competência técnica a adicionar
     * @param obrigatoriedade obrigatoriedade da competência técnica
     * @param grauProficiencia grau de proficiencia associado à competência
     * técnica
     */
    public void addCandToList(Candidatura cand) {
        LocalDate now = LocalDate.now();
        Date dNow = Date.valueOf(now);
        Classificacao classif = new Classificacao(pos++, dNow, cand);
        this.tmpListaClass.add(classif);
        this.listaCand.remove(cand);
    }

    public int getPos() {
        return pos;
    }

    public void removeClassFromList(Classificacao classif) {
        this.listaCand.add(classif.getCandidatura());
        this.tmpListaClass.remove(classif);
        pos--;
    }

    public List<Colaborador> getColaboradoresByOrg() throws SQLException {
        return org.getRepositorioColaborador().getColaboradoresByOrg(org);
    }

    public int getCandId(Candidatura candidatura) throws SQLException {
        return plat.getLcand().getCandId(candidatura);
    }

    public List<Classificacao> getClassificacaoByProccSer(ProcessoSeriacao selectedItem) {
        return selectedItem.getClassificacoes();
    }

    public boolean novaProcessoSeriacaoAuto() throws Exception {
        try {
            LocalDate now = LocalDate.now();
            Date dNow = Date.valueOf(now);
            Collections.sort(listaCand);
            Candidatura a = listaCand.get(1);
            Candidatura b = listaCand.get(0);
            listaCand.remove(0);
            listaCand.remove(0);
            listaCand.add(0, a);
            listaCand.add(1, b);

            System.out.println(listaCand);
            for (Candidatura cand : listaCand) {
                Classificacao classif = new Classificacao(pos++, dNow, cand);
                tmpListaClass.add(classif);
            }
            ProcessoSeriacao pS
                    = new ProcessoSeriacao(dNow, org.getRepositorioColaborador().getColaboradorByEmail(this.plat.getUsersAPI().getEmail()),
                            anuncio, new ArrayList<Colaborador>(), tmpListaClass);

            return org.getListaProcessosSeriacao().registaProcessoSeriacao(pS, org.getNif());

        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

}
