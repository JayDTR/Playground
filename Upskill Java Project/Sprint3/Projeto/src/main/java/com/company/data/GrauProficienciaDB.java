/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.data;

import com.company.model.GrauProficiencia;
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
public class GrauProficienciaDB extends DataHandler {

    public GrauProficiencia insertGP(GrauProficiencia gp) throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            stmt = conn.createStatement();

            rs = stmt.executeQuery("INSERT INTO GRAUPROFICIENCIA (VALOR, DESIGNACAO, CODCOMPETENCIATECNICA) VALUES('"
                    + gp.getValor() + "', '"
                    + gp.getDesignacao() + "', '"
                    + gp.getCompT().getId() + "')");

            return gp;
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

    public List<GrauProficiencia> getAllGrausProf() throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM GRAUPROFICIENCIA");

            GrauProficiencia gp = null;

            List<GrauProficiencia> tmp = new ArrayList<GrauProficiencia>();
            while (rs.next()) {
                gp = new GrauProficiencia(rs.getString("valor"), rs.getString("designacao"), Plataforma.getInstance().getRct().getCompetenciaTecnicaById(rs.getString("codCompetenciaTecnica")));
                tmp.add(gp);
            }
            rs.close();
            return tmp;
        } catch (SQLException e) {
            throw new SQLException("Error at getAllGrausProf" + e.getMessage());
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
    }

    public List<GrauProficiencia> getGrausProfByCompTec(String id) throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM GRAUPROFICIENCIA WHERE CODCOMPETENCIATECNICA = '"+id+"'");

            GrauProficiencia gp = null;

            List<GrauProficiencia> tmp = new ArrayList<GrauProficiencia>();
            while (rs.next()) {
                gp = new GrauProficiencia(rs.getString("valor"), rs.getString("designacao"), Plataforma.getInstance().getRct().getCompetenciaTecnicaById(rs.getString("codCompetenciaTecnica")));
                tmp.add(gp);
            }
            rs.close();
            return tmp;
        } catch (SQLException e) {
            throw new SQLException("Error at getGrausProfByCompTec" + e.getMessage());
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
    }
}
