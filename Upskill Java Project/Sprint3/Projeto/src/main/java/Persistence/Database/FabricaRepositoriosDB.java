/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence.Database;

import Persistence.FabricaRepositorios;
import Persistence.RepositorioAreaAtividade;
import Persistence.RepositorioOrganizacao;
import Persistence.RepositorioTarefa;
import java.sql.SQLException;

/**
 *
 * @author Asus
 */
public class FabricaRepositoriosDB implements FabricaRepositorios {

    @Override
    public RepositorioAreaAtividade getRepositorioAreaAtividade()  throws SQLException{
        return new RepositorioAreaAtividadeDB();
    }

    @Override
    public RepositorioOrganizacao getRepositorioOrganizacao()  throws SQLException {
        return new RepositorioOrganizacaoDB();
    }

    @Override
    public RepositorioTarefa getRepositorioTarefa()   throws SQLException{
        return new RepositorioTarefaDB();
    }
}
