/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence;

import com.company.model.CompetenciaTecnica;
import java.util.List;

/**
 *
 * @author joaor
 */
public interface RepositorioCompetenciaTecnica {
    public void save(CompetenciaTecnica ct);
    
    public List<CompetenciaTecnica> getAll();
    
    public CompetenciaTecnica find(String id);
}
