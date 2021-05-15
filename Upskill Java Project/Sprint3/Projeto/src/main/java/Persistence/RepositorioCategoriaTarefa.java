/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence;

import com.company.model.CaraterCT;
import com.company.model.CategoriaTarefa;
import java.util.List;

/**
 *
 * @author joaor
 */
public interface RepositorioCategoriaTarefa {
    
    public void Save(CategoriaTarefa CatTar);
    
    public List<CategoriaTarefa> getAll();
    
    public List<CaraterCT> getCaraterCTByCatTarID(String id);
}
