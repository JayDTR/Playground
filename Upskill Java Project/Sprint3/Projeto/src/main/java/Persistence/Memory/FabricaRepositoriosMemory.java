/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence.Memory;

import Persistence.FabricaRepositorios;
import Persistence.RepositorioAreaAtividade;
import Persistence.RepositorioOrganizacao;
import Persistence.RepositorioTarefa;

/**
 *
 * @author Asus
 */
public class FabricaRepositoriosMemory implements FabricaRepositorios {

    @Override
    public RepositorioAreaAtividade getRepositorioAreaAtividade() {
        return new RepositorioAreaAtividadeMemory();
    }

    @Override
    public RepositorioOrganizacao getRepositorioOrganizacao() {
        return new RepositorioOrganizacaoMemory();
    }

    @Override
    public RepositorioTarefa getRepositorioTarefa() {
        return new RepositorioTarefaMemory();
    }

}
