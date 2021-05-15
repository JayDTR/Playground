/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence.Database;

import Persistence.RepositorioOrganizacao;
import com.company.data.CommonDB;
import com.company.data.DataHandler;
import com.company.model.Colaborador;
import com.company.model.EnderecoPostal;
import com.company.model.Organizacao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Asus
 */
public class RepositorioOrganizacaoDB extends DataHandler implements RepositorioOrganizacao {
    
    public Organizacao insertOrganizacao(Organizacao org) throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            stmt = conn.createStatement();

            //id endereco postal
            rs = stmt.executeQuery("select max(id) id from enderecopostal");
            int id = 1;
            if (rs.next()) {
                id = rs.getInt("id");
            }

            //result 
            //alterar para plsql transaction
            rs = stmt.executeQuery(CommonDB.insertGestorAsUtilizador(org));       
            rs = stmt.executeQuery(CommonDB.insertEnderecoPostal(org));  
            rs = stmt.executeQuery(CommonDB.insertOrgAsUser(org));
            rs = stmt.executeQuery(insertOrgQuery(org, id+1));               
            rs = stmt.executeQuery(CommonDB.insertGestor(org));

            return org;
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (rs != null) {
                rs.close();
            }
            try {
                //
            } catch (Exception e) {
            }
        }
    }
    
    public static String insertOrgQuery(Organizacao org, int idEndPostal) {
        if (org == null) {
            return "";
        }

        return "INSERT INTO ORGANIZACAO (NIF, EMAIL, IDENDERECOPOSTAL, EMAILGESTOR, WEBSITE) VALUES("
                + Integer.parseInt(org.getNif()) + ", '"
                + org.getEmail() + "', "
                + idEndPostal + ", '"
                + org.getGestor().getEmail() + "', '"
                + org.getSite() + "')";
    }
    
    public List<Organizacao> getAllOrgs() throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        Statement stmt2 = null;
        ResultSet rs = null; //query para gestor como utilizador
        ResultSet rs2 = null; //query para organizacao como utilizador
        try {
            conn = getConnection();
            stmt = conn.createStatement();
            stmt2 = conn.createStatement();
            rs = stmt.executeQuery("select * from utilizador join organizacao on utilizador.email = organizacao.emailGestor");
            rs2 = stmt2.executeQuery("select * from utilizador join organizacao on utilizador.email = organizacao.email join enderecopostal on organizacao.idenderecopostal = enderecopostal.id");
            Organizacao org = null;
            Colaborador colab = null;
            EnderecoPostal end = null;
            
            /* usar a seguinte query para obter os gestores na tabela utilizador
            select utilizador.nome, utilizador.email, utilizador.telefone from organizacao join utilizador on organizacao.emailgestor = utilizador.email where organizacao.emailgestor = utilizador.email;
            */
            
            List<Colaborador> tmpColab = new ArrayList<>();
            List<Organizacao> tmp = new ArrayList<Organizacao>();
            int i = 0;
            while (rs.next()) {                
                colab = new Colaborador(rs.getString("NOME"),rs.getString(String.valueOf("TELEFONE")), rs.getString("EMAIL"));
                tmpColab.add(colab);
            }
            
            while(rs2.next()) {
                end = new EnderecoPostal(rs2.getString("MORADA"), rs2.getString("CODIGOPOSTAL"), rs2.getString("LOCALIDADE"));
                String nome = rs2.getString("NOME");
                String nif = rs2.getString(String.valueOf("NIF"));
                String site = rs2.getString("WEBSITE");
                String telefone = rs2.getString(String.valueOf("TELEFONE"));
                String email = rs2.getString("EMAIL");
                Colaborador c1 = tmpColab.get(i);
                org = new Organizacao(nome, nif, site, telefone, email, end, c1);
                tmp.add(org);
                i++;
            }
            
            rs.close();
            rs2.close();
            return tmp;
        } catch (SQLException e) {
            throw new SQLException("Error at getAllOrgs" + e.getMessage());
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (rs != null) {
                rs.close();
            }
            try {
                //
            } catch (Exception e) {
            }
        }
    }


    
    @Override
    public void save(Organizacao rg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Organizacao find(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Organizacao> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
