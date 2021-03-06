/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.controller;

import Persistence.FabricaRepositorios;
import Persistence.RepositorioAreaAtividade;
import com.company.data.AreaAtividadeDB;
import com.company.model.AreaAtividade;
import com.company.utils.Constantes;
import com.company.model.Plataforma;
import com.company.model.ListaAreasAtividade;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável pela ligação do UI com os métodos necessários, para registar novas Áreas de atividade.
 * 
 * @author Asus
 */
public class RegistarAreaAtividadeController {
    private Plataforma plat;
    private ListaAreasAtividade lat;
    private AreaAtividade areaAtividade;
    private AreaAtividadeDB aaDB;
    //private FabricaRepositorios fr = new FabricaRepositoriosDB();
    //private FabricaRepositoriosMemory fr = new FabricaRepositoriosMemory();
    //private RepositorioAreaAtividade repo = fr.getRepositorioAreaAtividade();
    //private RepositorioAreaAtividadeMemory repo = frMemory.getRepositorioAreaAtividadeMemory;
    
    /**
     * Constrói uma instância de EspecificarAreaAtividadeController.
     * Valida o acesso do utilizador 'Administrador' para efectuar o registo de áreas de atividade.
     */
    public RegistarAreaAtividadeController() {
        if(!Plataforma.getInstance().getUsersAPI().getRole().equalsIgnoreCase(Constantes.ROLE_ADMINISTRADOR))
            throw new IllegalStateException("Utilizador não Autorizado");
        this.plat = Plataforma.getInstance();
        this.lat = plat.getLat();
    }
    
    /**
     * Valida e adiciona um novo objecto do tipo Area de Atividade construído com os parâmetros
     * de entrada.
     * 
     * @param id identificação da area de atividade
     * @param descBreve descrição breve da area de atividade
     * @param descDetalhada descrição detalhada da area de atividade
     * @return true se for possível adicionar a área de atividade, caso contrário
     * devolve false com uma mensagem de erro
     */
    public boolean registarAreaAtividade(String id, String descBreve, String descDetalhada) throws Exception {
        try {
            //AreaAtividade aa = new AreaAtividade(id, descBreve, descDetalhada);
            //repo.save(aa); // apagar o que esta em baixo
            this.areaAtividade = this.plat.getLat().novaAreaAtividade(id, descBreve,descDetalhada);
            return this.plat.getLat().validaAreaAtividade(this.areaAtividade);
        }
        catch(RuntimeException ex) {
            this.areaAtividade = null;
            throw new Exception (ex);
        }
    }
   
    /**
     * Devolve uma cópia da lista de áreas de atividade existentes na plataforma.
     * 
     * @return cópia da lista de áreas de atividade existentes na plataforma
     */
    public List<AreaAtividade> getListAreasAtividade() throws SQLException {
        List<AreaAtividade> listAA = lat.getAllAreasAtividade();
        return listAA;
    }
    
     /**
     * Valida e adiciona um novo objecto do tipo Área de Atividade à lista de áreas de atividade da plataforma.
     * 
     * @return true se for possível adicionar um novo objecto do tipo Áreas de Atividade,
     * caso contrário devolve false
     */
    public boolean registaAreaAtividade() {
        return this.plat.getLat().registaAreaAtividade(this.areaAtividade);
    }

    /**
     * Devolve a descrição textual da área de atividade.
     * 
     * @return descrição textual da área de atividade
     */
    public String getAreaAtividadeString() {
        return this.areaAtividade.toString();
    }
    
//    public List<AreaAtividade> getAllAreasAtividade() throws SQLException {
//        return this.plat.getLat().getAllAreasAtividade();
//    }
}
