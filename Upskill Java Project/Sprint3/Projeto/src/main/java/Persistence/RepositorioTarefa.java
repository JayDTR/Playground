/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence;

import com.company.model.Tarefa;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author diana
 */
public interface RepositorioTarefa {
    
    public void save(Tarefa tarefa) throws SQLException;
    
    public Tarefa find(String id) throws SQLException;
    
    public List<Tarefa> getAll() throws SQLException;
    
}
