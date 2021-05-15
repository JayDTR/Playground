/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.data;

import com.company.model.Anuncio;
import com.company.model.Colaborador;
import com.company.model.Organizacao;
import com.company.model.Plataforma;
import com.company.model.Tarefa;
import com.company.model.TipoRegimento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Asus
 */
public class AnuncioDB extends DataHandler {

    public boolean save(Anuncio anuncio) throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        PreparedStatement pS = null;

        try {

            conn = getConnection();
            stmt = conn.createStatement();

            String a = "INSERT INTO ANUNCIO (ID, DTINICIOPUBLICACAO, DTFIMPUBLICACAO,DTINICIOCANDIDATURA, DTFIMCANDIDATURA,"
                    + "DTINICIOSERIACAO,DTFIMSERIACAO, IDTIPOREGIMENTO, NIFORGANIZACAO) "
                    + "VALUES(?,?,?,?,?,?,?,?,?)";
            pS = conn.prepareStatement(a);

            String email = anuncio.getColab().getEmail();

            rs = stmt.executeQuery("SELECT * FROM COLABORADOR WHERE EMAILCOLABORADOR='" + anuncio.getColab().getEmail() + "'");
            int nifOrg = 0;
            while (rs.next()) {
                nifOrg = rs.getInt("nifOrganizacao");
            }

            rs = stmt.executeQuery("select * from tiporegimento where designacao = '" + anuncio.getRegimento().getDesignacao() + "'");
            int id = 0;
            while (rs.next()) {
                id = rs.getInt("ID");
            }

            pS.setString(1, anuncio.getTarefa().getRef());
            pS.setDate(2, anuncio.getdInicioPublicitacao());
            pS.setDate(3, anuncio.getdFimPublicitacao());
            pS.setDate(4, anuncio.getdInicioCandidatura());
            pS.setDate(5, anuncio.getdFimCandidatura());
            pS.setDate(6, anuncio.getdInicioSeriacao());
            pS.setDate(7, anuncio.getdFimSeriacao());
            pS.setInt(8, id);
            pS.setInt(9, nifOrg);

            pS.executeUpdate();
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
            if (pS != null) {
                pS.close();
            }
        }
    }

    public Anuncio find(String referencia) throws SQLException { // VERIFICAR
        Connection conn = null;
        Statement stmt = null;
        Statement stmt2 = null;
        ResultSet rs = null;
        ResultSet rs2 = null;
        try {
            conn = getConnection();
            stmt = conn.createStatement();
            stmt2 = conn.createStatement();

            Anuncio anun = null;
            rs = stmt.executeQuery("SELECT * FROM ANUNCIO WHERE ID = '" + referencia + "'");
            while (rs.next()) {
                TipoRegimento tReg = null;
                rs2 = stmt2.executeQuery("SELECT * FROM TIPOREGIMENTO WHERE ID = " + rs.getInt("IDTIPOREGIMENTO"));
                while (rs2.next()) {
                    tReg = new TipoRegimento(rs2.getString("DESIGNACAO"), rs2.getString("DESCRICAO"));
                    tReg.setId(rs2.getInt("ID"));
                }
                Date dtInicioPublicacao = rs.getDate("DTINICIOPUBLICACAO");
                Date dtFimPublicacao = rs.getDate("DTFIMPUBLICACAO");
                Date dtInicioCandidatura = rs.getDate("DTINICIOCANDIDATURA");
                Date dtFimCandidatura = rs.getDate("DTFIMCANDIDATURA");
                Date dtInicioSeriacao = rs.getDate("DTINICIOSERIACAO");
                Date dtFimSeriacao = rs.getDate("DTFIMSERIACAO");
                Tarefa tar = Plataforma.getInstance().getListaTarefas().getTarefaByRef(rs.getString("ID"));
                Colaborador colab = Plataforma.getInstance().getListaTarefas().getTarefaByRef(rs.getString("ID")).getColab();

                anun = new Anuncio(dtInicioPublicacao, dtFimPublicacao, dtInicioCandidatura, dtFimCandidatura,
                        dtInicioSeriacao, dtFimSeriacao, tar, colab, tReg);
            }

            return anun;
        } catch (SQLException e) {
            throw new SQLException("Error at getAnunciosByOrg" + e.getMessage());
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

    public List<Anuncio> getAll() throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        Statement stmt2 = null;
        ResultSet rs = null;
        ResultSet rs2 = null;

        try {
            conn = getConnection();
            stmt = conn.createStatement();
            stmt2 = conn.createStatement();

            rs = stmt.executeQuery("SELECT * FROM ANUNCIO");

            List<Anuncio> lanuncio = new ArrayList<Anuncio>();

            while (rs.next()) {
                rs2 = stmt2.executeQuery("SELECT * FROM TIPOREGIMENTO JOIN ANUNCIO ON ANUNCIO.IDTIPOREGIMENTO = TIPOREGIMENTO.ID "
                        + "WHERE ANUNCIO.IDTIPOREGIMENTO = " + rs.getInt("IDTIPOREGIMENTO"));

                TipoRegimento tReg = null;
                while (rs2.next()) {
                    tReg = new TipoRegimento(rs2.getString("DESIGNACAO"), rs2.getString("DESCRICAO"));
                    tReg.setId(rs2.getInt("ID"));
                }

                Date dtInicioPublicacao = rs.getDate("DTINICIOPUBLICACAO");
                Date dtFimPublicacao = rs.getDate("DTFIMPUBLICACAO");
                Date dtInicioCandidatura = rs.getDate("DTINICIOCANDIDATURA");
                Date dtFimCandidatura = rs.getDate("DTFIMCANDIDATURA");
                Date dtInicioSeriacao = rs.getDate("DTINICIOSERIACAO");
                Date dtFimSeriacao = rs.getDate("DTFIMSERIACAO");
                Tarefa tar = Plataforma.getInstance().getListaTarefas().getTarefaByRef(rs.getString("ID"));
                Colaborador colab = Plataforma.getInstance().getListaTarefas().getTarefaByRef(rs.getString("ID")).getColab();

                Anuncio anun = new Anuncio(dtInicioPublicacao, dtFimPublicacao, dtInicioCandidatura, dtFimCandidatura,
                        dtInicioSeriacao, dtFimSeriacao, tar, colab, tReg);

                lanuncio.add(anun);
            }
            return lanuncio;
        } catch (SQLException e) {
            throw new SQLException("Error at getAnunciosByOrg" + e.getMessage());
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

    public List<Anuncio> getAnunciosByOrg(Organizacao org) throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        Statement stmt2 = null;
        ResultSet rs = null;
        ResultSet rs2 = null;
        try {
            conn = getConnection();
            stmt = conn.createStatement();
            stmt2 = conn.createStatement();

            rs = stmt.executeQuery("select * from anuncio where niforganizacao = " + Integer.valueOf(org.getNif()));
            List<Anuncio> tmp = new ArrayList<Anuncio>();

            while (rs.next()) {
                rs2 = stmt2.executeQuery("SELECT * FROM TIPOREGIMENTO JOIN ANUNCIO ON ANUNCIO.IDTIPOREGIMENTO = TIPOREGIMENTO.ID "
                        + "WHERE ANUNCIO.IDTIPOREGIMENTO = " + rs.getInt("IDTIPOREGIMENTO"));

                TipoRegimento tReg = null;
                while (rs2.next()) {
                    tReg = new TipoRegimento(rs2.getString("DESIGNACAO"), rs2.getString("DESCRICAO"));
                    tReg.setId(rs2.getInt("ID"));
                }

                Date dtInicioPublicacao = rs.getDate("DTINICIOPUBLICACAO");
                Date dtFimPublicacao = rs.getDate("DTFIMPUBLICACAO");
                Date dtInicioCandidatura = rs.getDate("DTINICIOCANDIDATURA");
                Date dtFimCandidatura = rs.getDate("DTFIMCANDIDATURA");
                Date dtInicioSeriacao = rs.getDate("DTINICIOSERIACAO");
                Date dtFimSeriacao = rs.getDate("DTFIMSERIACAO");
                Tarefa tar = Plataforma.getInstance().getListaTarefas().getTarefaByRef(rs.getString("ID"));
                Colaborador colab = Plataforma.getInstance().getListaTarefas().getTarefaByRef(rs.getString("ID")).getColab();

                Anuncio anun = new Anuncio(dtInicioPublicacao, dtFimPublicacao, dtInicioCandidatura, dtFimCandidatura,
                        dtInicioSeriacao, dtFimSeriacao, tar, colab, tReg);

                tmp.add(anun);
            }
            rs.close();
            return tmp;
        } catch (SQLException e) {
            throw new SQLException("Error at getAnunciosByOrg" + e.getMessage());
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
}
