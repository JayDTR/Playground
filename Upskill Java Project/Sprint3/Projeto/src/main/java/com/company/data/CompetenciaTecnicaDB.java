/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.data;

import com.company.model.CompetenciaTecnica;
import com.company.model.Plataforma;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joaor
 */
public class CompetenciaTecnicaDB extends DataHandler {

    public CompetenciaTecnica insertCT(CompetenciaTecnica ct) throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            stmt = conn.createStatement();

            rs = stmt.executeQuery("INSERT INTO COMPETENCIATECNICA (CODUNICO, DESCBREVE, DESCDETALHADA,IDAREAATIVIDADE) VALUES('"
                    + ct.getId() + "', '"
                    + ct.getDescBreve() + "', '"
                    + ct.getDescDetalhada() + "','"
                    + ct.getAreaAtividade().getId() + "')");

            return ct;
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

    public List<CompetenciaTecnica> getAllCompetenciasTecnicas() throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM COMPETENCIATECNICA");

            CompetenciaTecnica ct = null;

            List<CompetenciaTecnica> tmp = new ArrayList<CompetenciaTecnica>();
            while (rs.next()) {
                ct = new CompetenciaTecnica(rs.getString("codUnico"), rs.getString("descBreve"), rs.getString("descDetalhada"), Plataforma.getInstance().getLat().getAreaAtividadeById(rs.getString("idAreaAtividade")));
                tmp.add(ct);
            }
            rs.close();
            return tmp;
        } catch (SQLException e) {
            throw new SQLException("Error at getAllCompetenciasTecnicas" + e.getMessage());
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
