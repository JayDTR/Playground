/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence;

import java.sql.SQLException;

/**
 *
 * @author Asus
 */
public interface FabricaRepositorios { // acrescentar restantes repositorios
    public RepositorioAreaAtividade getRepositorioAreaAtividade()  throws SQLException;
    public RepositorioOrganizacao getRepositorioOrganizacao() throws SQLException;
    public RepositorioTarefa getRepositorioTarefa() throws SQLException;
}
