/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.data;

import com.company.model.Anuncio;
import com.company.model.Candidatura;
import com.company.model.Freelancer;
import com.company.model.Plataforma;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Asus
 */
public class CandidaturaDB extends DataHandler {

    public List<Candidatura> getAll() throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        Statement stmt2 = null;
        ResultSet rs = null;
        ResultSet rs2 = null;

        try {
            conn = getConnection();
            stmt = conn.createStatement();
            stmt2 = conn.createStatement();

            rs = stmt.executeQuery("SELECT * FROM CANDIDATURA");

            List<Candidatura> lcand = new ArrayList<>();

            while (rs.next()) {
                Anuncio anun = Plataforma.getInstance().getRa().getAnuncioByRefTarefa(rs.getString("IDANUNCIO"));
                Date dtCandidatura = rs.getDate("DATACANDIDATURA");
                Double valorPretendido = rs.getDouble("VALORPRETENDIDO");
                int nrDias = rs.getInt("NRDIAS");
                String txtApr = rs.getString("TXTAPResentacao");
                String txtMotiv = rs.getString("TXTMOTIVACAO");

                Freelancer free = Plataforma.getInstance().getRegFree().getFreelancerByNif(rs.getInt("NifFreelancer"));

                Candidatura cand = new Candidatura(anun, dtCandidatura, valorPretendido, nrDias, txtApr, txtMotiv, free);

                lcand.add(cand);
            }
            return lcand;
        } catch (SQLException e) {
            throw new SQLException("Error at getAllCands" + e.getMessage());
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (rs != null) {
                rs.close();
            }
            if (stmt2 != null) {
                stmt2.close();
            }
            if (rs2 != null) {
                rs2.close();
            }
        }

    }

    public boolean save(Candidatura cand) throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        PreparedStatement pS = null;
        CallableStatement cs = null;
        try {
            conn = getConnection();
            cs = conn.prepareCall("{CALL addCandidatura(?,?,?,?,?,?,?)}");

            conn.setAutoCommit(false);

            cs.setDate(1, cand.getDtCandidatura());
            cs.setDouble(2, cand.getValorPretendido());
            cs.setInt(3, cand.getNrDias());
            cs.setString(4, cand.getTxtApr());
            cs.setString(5, cand.getTxtMotiv());
            cs.setInt(6, Integer.valueOf(cand.getFree().getNif()));
            cs.setString(7, cand.getAnuncio().getTarefa().getRef());

            cs.executeQuery();

            conn.commit();
            cs.close();
            
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
            conn.setAutoCommit(true);
        }
    }

    public Candidatura find(int id) throws SQLException { // VERIFICAR
        Connection conn = null;
        Statement stmt = null;
        Statement stmt2 = null;
        ResultSet rs = null;
        ResultSet rs2 = null;
        try {
            conn = getConnection();
            stmt = conn.createStatement();
            stmt2 = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM CANDIDATURA WHERE ID = " + id);
            Candidatura cand = null;
            while (rs.next()) {
                Anuncio anun = Plataforma.getInstance().getRa().getAnuncioByRefTarefa(rs.getString("IDANUNCIO"));
                Date dtCandidatura = rs.getDate("DATACANDIDATURA");
                Double valorPretendido = rs.getDouble("VALORPRETENDIDO");
                int nrDias = rs.getInt("NRDIAS");
                String txtApr = rs.getString("TXTAPResentacao");
                String txtMotiv = rs.getString("TXTMOTIVACAO");
                Freelancer free = Plataforma.getInstance().getRegFree().getFreelancerByNif(rs.getInt("NifFreelancer"));

                cand = new Candidatura(anun, dtCandidatura, valorPretendido, nrDias, txtApr, txtMotiv, free);

            }

            return cand;
        } catch (SQLException e) {
            throw new SQLException("Error at findCandByRef" + e.getMessage());
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (rs != null) {
                rs.close();
            }
            if (stmt2 != null) {
                stmt2.close();
            }
            if (rs2 != null) {
                rs2.close();
            }
            try {
                //
            } catch (Exception e) {
            }
        }
    }

    public int getIdCand(Candidatura cand) throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT ID FROM CANDIDATURA WHERE NIFFREELANCER= " + cand.getFree().getNif() + " AND IDANUNCIO ='" + cand.getAnuncio().getTarefa().getRef() + "'");
            int id = 0;
            while (rs.next()) {
                id = rs.getInt("ID");
            }

            return id;
        } catch (SQLException e) {
            throw new SQLException("Error at getIdCand" + e.getMessage());
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
