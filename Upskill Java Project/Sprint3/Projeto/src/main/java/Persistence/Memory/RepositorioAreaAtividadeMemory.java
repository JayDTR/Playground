/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence.Memory;

//import Persistence.Database.RepositorioAreaAtividadeDB;
import Persistence.RepositorioAreaAtividade;
import com.company.model.AreaAtividade;
import java.util.List;

/**
 *
 * @author Asus
 */
public class RepositorioAreaAtividadeMemory implements RepositorioAreaAtividade { //singleton

    private List<AreaAtividade> laa;

    @Override
    public void save(AreaAtividade aa) {
        //RepositorioAreaAtividadeDB.insertAA(aa);
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AreaAtividade find(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
