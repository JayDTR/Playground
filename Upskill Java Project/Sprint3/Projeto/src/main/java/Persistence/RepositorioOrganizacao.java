/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence;

import com.company.model.Organizacao;
import java.util.List;

/**
 *
 * @author Asus
 */
public interface RepositorioOrganizacao {
    public void save(Organizacao rg);
    
    public Organizacao find(String id);
    
    public List<Organizacao> getAll();
}
