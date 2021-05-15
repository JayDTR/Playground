/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence.Database;

import Persistence.RepositorioAreaAtividade;
import com.company.data.DataHandler;
import com.company.model.AreaAtividade;
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
public class RepositorioAreaAtividadeDB extends DataHandler implements RepositorioAreaAtividade {
    public AreaAtividade insertAA(AreaAtividade aa) throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            stmt = conn.createStatement();

            rs = stmt.executeQuery("INSERT INTO AREAATIVIDADE (ID, DESCBREVE, DESCDETALHADA) VALUES('"
                    + aa.getId() + "', '"
                    + aa.getDescBreve() + "', '"
                    + aa.getDescDetalhada() + "')");

            return aa;
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

    @Override
    public void save(AreaAtividade aa) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AreaAtividade find(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<AreaAtividade> getAll() throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM AREAATIVIDADE");
            
            AreaAtividade aa = null;
            
            List<AreaAtividade> tmp = new ArrayList<AreaAtividade>();
            while (rs.next()) {
                aa = new AreaAtividade(rs.getString("ID"), rs.getString("descBreve"), rs.getString("descdetalhada"));
                tmp.add(aa);
            }
            rs.close();
            return tmp;
        } catch (SQLException e) {
            throw new SQLException("Error at getAllPharmacys" + e.getMessage());
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
}
