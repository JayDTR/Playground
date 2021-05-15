/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.data;

import com.company.model.Anuncio;
import com.company.model.Plataforma;
import com.company.model.TipoRegimento;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author diana
 */
public class TipoRegimentoDB extends DataHandler {

    public boolean save(TipoRegimento tipoRegimento) throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            stmt = conn.createStatement();

            rs = stmt.executeQuery("INSERT INTO TIPOREGIMENTO (DESIGNACAO, DESCRICAO) VALUES('"
                    + tipoRegimento.getDesignacao() + "', '"
                    + tipoRegimento.getDescricaoRegras() + "')");

            return true;
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
                return false;
            }
        }
    }

    public TipoRegimento find(String designacao) throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            stmt = conn.createStatement();

            rs = stmt.executeQuery("SELECT * FROM TIPOREGIMENTO WHERE DESIGNACAO ='" + designacao + "'");

            TipoRegimento tReg = new TipoRegimento(rs.getString("DESIGNACAO"), rs.getString("DESCRICAO"));
            tReg.setId(rs.getInt("ID"));
            
            return tReg;

        } catch (SQLException e) {
            throw new SQLException("Erro em findTiposRegimento" + e.getMessage());
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

    public List<TipoRegimento> getAll() throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            stmt = conn.createStatement();

            rs = stmt.executeQuery("SELECT * FROM TIPOREGIMENTO");

            List<TipoRegimento> ltipoRegimento = new ArrayList<TipoRegimento>();

            while (rs.next()) {
                TipoRegimento tReg = new TipoRegimento(rs.getString("DESIGNACAO"), rs.getString("DESCRICAO"));
                tReg.setId(rs.getInt("ID"));
                ltipoRegimento.add(tReg);
            }

            return ltipoRegimento;

        } catch (SQLException e) {
            throw new SQLException("Erro em getAllTiposRegimento" + e.getMessage());
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
