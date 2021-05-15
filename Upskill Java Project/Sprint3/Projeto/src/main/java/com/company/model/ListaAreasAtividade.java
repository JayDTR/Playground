/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.model;

//import Persistence.Database.RepositorioAreaAtividadeDB;
import com.company.data.AreaAtividadeDB;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Asus
 */
public class ListaAreasAtividade {

    /**
     * Declaração de um objecto do tipo arrayList de AreaAtividade.
     */
    private ArrayList<AreaAtividade> lat;
    //private RepositorioAreaAtividadeDB aaDB;
    private AreaAtividadeDB aaDB;

    /**
     * Constrói uma instância da lista de áreas de atividade, 
     * recebendo por parâmetro uma lista de áreas de atividade pré preenchida.
     * @param lat lista de áreas de atividade pré preenchida
     */
    public ListaAreasAtividade(ArrayList<AreaAtividade> lat) {
        this.lat = new ArrayList<>(lat);
    }
    
    /**
     * Constrói uma instância da lista de áreas de atividade.
     * Inicializa a lista de áreas de atividade vazia.
     */
    public ListaAreasAtividade() {
        this.lat = new ArrayList<>();
        this.aaDB = new AreaAtividadeDB();
        //this.aaDB = new RepositorioAreaAtividadeDB();
    }

    /**
     * Devolve uma cópia da lista de áreas de atividade.
     *
     * @return cópia da lista de áreas de atividade do tipo arrayList
     */
    public ArrayList<AreaAtividade> getLat() {
        ArrayList<AreaAtividade> lc = new ArrayList<>();
        lc.addAll(this.lat);
        return lc;
    }

    /**
     * Devolve um novo objecto do tipo área de atividade construído com os parâmetros de entrada.
     * 
     * @param strCodigo identificação da area de atividade
     * @param strDescricaoBreve descrição breve da area de atividade
     * @param strDescricaoDetalhada descrição detalhada da area de atividade
     * @return novo objecto do tipo área de atividade
     */
    public AreaAtividade novaAreaAtividade(String strCodigo, String strDescricaoBreve, String strDescricaoDetalhada) throws SQLException {
        AreaAtividade aa = new AreaAtividade(strCodigo, strDescricaoBreve, strDescricaoDetalhada);
        return aaDB.insertAA(aa);
        //return new AreaAtividade(strCodigo, strDescricaoBreve, strDescricaoDetalhada);
    }

    /**
     * Valida e adiciona uma área de atividade à lista de áreas de atividade.
     * 
     * @param oArea área de atividade a adicionar
     * @return true se for possível adicionar a área de atividade, caso contrário devolve false
     */
    public boolean registaAreaAtividade(AreaAtividade oArea) {
        if (this.validaAreaAtividade(oArea)) {
            return addAreaAtividade(oArea);
        }
        return false;
    }

    /**
     * Adiciona uma área de atividade à lista de áreas de atividade.
     * 
     * @param oArea área de atividade a adicionar
     * @return true se for adicionada a área de atividade, caso contrário devolve false
     */
    private boolean addAreaAtividade(AreaAtividade oArea) {
        return lat.add(oArea);
    }

    /**
     * Verifica se a lista de áreas de atividade contém uma área de atividade com id igual ao da 
     * área de atividade passada por parâmetro.
     * 
     * @param oArea área de atividade a validar
     * @return true se o id da área de atividade for diferente de todos os id's da lista da área de atividade,
     * caso contrário devolve false com uma mensagem de erro
     */
    public boolean validaAreaAtividade(AreaAtividade oArea) {

        boolean bRet = true;
        for (AreaAtividade a : lat) {
            if (a.getId().equalsIgnoreCase(oArea.getId())) {
                bRet = false;
                throw new IllegalArgumentException("Já existe outra área de atividade com este ID.");
            }
        }
        return bRet;

    }

    /**
     * Devolve uma área de atividade existente na lista de áreas de atividade com id igual ao passado por parâmetro.
     * 
     * @param strId identificação da area de atividade
     * @return área de atividade, ou null caso o id não exista na lista
     */
    public AreaAtividade getAreaAtividadeById(String strId) throws SQLException {
        for (AreaAtividade area : aaDB.getAll()) {
            if (area.hasId(strId)) {
                return area;
            }
        }
        return null;
    }
    
    public List<AreaAtividade> getAllAreasAtividade() throws SQLException {
        List<AreaAtividade> tmp = aaDB.getAll();
        
        for(AreaAtividade area : tmp) {
            this.lat.add(area);
        }
        return aaDB.getAll();
    }
}
