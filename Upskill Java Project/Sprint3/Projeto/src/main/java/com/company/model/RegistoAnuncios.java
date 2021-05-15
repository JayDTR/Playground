/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.model;

import com.company.data.AnuncioDB;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author diana
 */
public class RegistoAnuncios {
    
    private AnuncioDB anuncioDB;
    /**
     * Declaração de um objecto do tipo arrayList de Anuncio.
     */
    private ArrayList<Anuncio> listaAnuncios;
    private ListaCandidaturas lCand;
    
     /**
     * Constrói uma instância da lista de anúncios. 
     * Inicializa a lista de anúncios vazia.
     */
    public RegistoAnuncios() {
        this.listaAnuncios = new ArrayList<>();
        this.anuncioDB = new AnuncioDB();
    }
    
    /**
     * Devolve uma cópia da lista de anúncios.
     *
     * @return cópia da lista de anúncios do tipo arrayList
     */
    public List<Anuncio> getAll() throws SQLException {
//        ArrayList<Anuncio> lAnuncio = new ArrayList<>();
//        lAnuncio.addAll(this.listaAnuncios);
        List<Anuncio> lAnuncio = anuncioDB.getAll();
        return lAnuncio;
    }
    
      /**
     * Devolve um novo objecto do tipo Anuncio construído com os parâmetros de
     * entrada.
     *
     * @param dInicioPublicitacao data de início da publicação do anúncio da tarefa
     * @param dFimPublicitacao data de fim da publicação do anúncio da tarefa
     * @param dInicioCandidatura data de início da candidatura ao anúncio da tarefa
     * @param dFimCandidatura data de fim da candidatura ao anúncio da tarefa
     * @param dInicioSeriacao data de início da ordenação dos candidatos ao anúncio da tarefa
     * @param dFimSeriacao data de fim da ordenação dos candidatos ao anúncio da tarefa
     * @param regimento tipo de regimento aplicado à ordenação dos candidatos
     * @return novo objecto do tipo Anuncio
     */
    public Anuncio novoAnuncio(Date dtInicioPublicitacao, Date dtFimPublicitacao, Date dtInicioCandidatura, Date dtFimCandidatura,
            Date dtInicioSeriacao, Date dtFimSeriacao, Tarefa tarefa, Colaborador colab, TipoRegimento regimento) {
        Anuncio anun = new Anuncio(dtInicioPublicitacao, dtFimPublicitacao, dtInicioCandidatura, dtFimCandidatura,
            dtInicioSeriacao, dtFimSeriacao, tarefa, colab, regimento);
        return anun;
    }
    
        
    public boolean registaAnuncio(Anuncio oAnuncio) throws SQLException {
        return anuncioDB.save(oAnuncio);
    }
    
    public boolean addAnuncio(Anuncio oAnuncio) throws SQLException{
        return listaAnuncios.add(oAnuncio);
    }
    
    
     public Anuncio getAnuncioByRefTarefa(String strRef) throws SQLException {
        Anuncio anuncio = anuncioDB.find(strRef);
        return anuncio;
    }
    
    public List<Anuncio> getAnunciosDisp(Date date) { // para UC9
        List<Anuncio> listAnunDisp = new ArrayList<>();
         for(Anuncio anun : listaAnuncios) {
             if(anun.getdInicioCandidatura().before(date) && anun.getdFimCandidatura().after(date)) {
                 listAnunDisp.add(anun);
             }
         }
         return listAnunDisp;
    }
    
    public List<Anuncio> getAllAnunciosByOrg(Organizacao org) throws SQLException {
        return anuncioDB.getAnunciosByOrg(org);
    }
 
}
