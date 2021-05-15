/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence;

import com.company.model.Anuncio;
import com.company.model.Organizacao;
import java.util.List;

/**
 *
 * @author joaor
 */
public interface RepositorioAnuncio {
    
    public boolean save(Anuncio anuncio);
    
    public Anuncio find(String referencia);
    
    public List<Anuncio> getAll();
    
    public List<Anuncio> getAnunciosByOrg(Organizacao org);
}
