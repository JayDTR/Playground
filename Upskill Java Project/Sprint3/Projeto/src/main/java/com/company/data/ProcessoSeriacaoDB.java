/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.data;

import com.company.model.Anuncio;
import com.company.model.Candidatura;
import com.company.model.CategoriaTarefa;
import com.company.model.Classificacao;
import com.company.model.Colaborador;
import com.company.model.HabilitacaoAcademica;
import com.company.model.Plataforma;
import com.company.model.ProcessoSeriacao;
import com.company.model.ReconhecimentoCT;
import com.company.model.Tarefa;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joaor
 */
public class ProcessoSeriacaoDB extends DataHandler {

    public ArrayList<ProcessoSeriacao> getAllProcessosSeriacao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean add(ProcessoSeriacao pSer) throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        PreparedStatement pS = null;
        try {
            conn = getConnection();
            stmt = conn.createStatement();

            pS = conn.prepareStatement("Insert into PROCESSOSERIACAO (DATAREALIZACAO, EMAILCOLABORADOR, IDANUNCIO) VALUES(?,?,?)");

            pS.setDate(1, pSer.getDataRealizacao());
            pS.setString(2, pSer.getColab().getEmail());
            pS.setString(3, pSer.getAnuncio().getTarefa().getRef());

            pS.executeUpdate();

            String sql = "Insert into Classificacao(idProcessoSeriacao,dataHora,lugarClassificacao,idCandidatura) values (?,?,?,?)";

            for (Classificacao classif : pSer.getClassificacoes()) {
                pS = conn.prepareStatement(sql);

                pS.setString(1, classif.getCandidatura().getAnuncio().getTarefa().getRef());
                pS.setDate(2, classif.getDataHora());
                pS.setInt(3, classif.getLugarClassificacao());
                pS.setInt(4, Plataforma.getInstance().getLcand().getCandId(classif.getCandidatura()));

                pS.executeUpdate();
            }

            sql = "Insert into ListaColaboradorProcessoSeriacao(idProcessoSeriacao,emailColaborador) values (?,?)";

            for (Colaborador colab : pSer.getParticipantes()) {
                pS = conn.prepareStatement(sql);
                pS.setString(1, pSer.getAnuncio().getTarefa().getRef());
                pS.setString(2, colab.getEmail());
                pS.executeUpdate();
            }

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
        }
    }

    public List<Classificacao> getListClassificacoesByProcSerId(String id) throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        ResultSet rs2 = null;
        try {
            conn = getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from classificacao where idProcessoSeriacao = '" + id + "'");

            Classificacao classif = null;
            ArrayList<Classificacao> tmp = new ArrayList<Classificacao>();
            while (rs.next()) {
                classif = new Classificacao(rs.getInt("lugarClassificacao"), rs.getDate("datahora"), Plataforma.getInstance().getLcand().getCandidaturaById(rs.getInt("id")));
                tmp.add(classif);
            }
            rs.close();
            return tmp;
        } catch (SQLException e) {
            throw new SQLException("Error at getProcessosSeriacaoByOrgNif" + e.getMessage());
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
    }

    public List<ProcessoSeriacao> getProcessosSeriacaoByOrgNif(String nif) throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        Statement stmt2 = null;
        ResultSet rs = null;
        ResultSet rs2 = null;
        try {
            conn = getConnection();
            stmt = conn.createStatement();
            stmt2 = conn.createStatement();

            ProcessoSeriacao pS = null;
            Classificacao classif = null;
            Colaborador colab = null;
            Colaborador partic = null;
            Anuncio anuncio = null;
            Date data = null;

            ArrayList<Classificacao> classificacoes = null;
            ArrayList<Colaborador> participantes = null;
            ArrayList<ProcessoSeriacao> tmp = new ArrayList<ProcessoSeriacao>();

            rs = stmt.executeQuery("SELECT * FROM PROCESSOSERIACAO join anuncio on processoseriacao.idanuncio = anuncio.id where anuncio.niforganizacao = " + nif);

            while (rs.next()) {
                classificacoes = new ArrayList<>();
                participantes = new ArrayList<>();
                String anuncioId = rs.getString("idAnuncio");
                anuncio = Plataforma.getInstance().getRa().getAnuncioByRefTarefa(anuncioId);
                colab = Plataforma.getInstance().getRc().getColaboradorByEmail(rs.getString("emailColaborador"));
                data = rs.getDate("DATAREALIZACAO");

                rs2 = stmt2.executeQuery("select * from listacolaboradorprocessoseriacao where idprocessoseriacao = '" + anuncioId + "'");
                while (rs2.next()) {
                    partic = Plataforma.getInstance().getRc().getColaboradorByEmail(rs2.getString("emailColaborador"));
                    participantes.add(partic);
                }

                rs2 = stmt2.executeQuery("select * from classificacao where idProcessoSeriacao='" + anuncioId + "'");
                while (rs2.next()) {
                    Candidatura cand = Plataforma.getInstance().getLcand().getCandidaturaById(rs2.getInt("IDCANDIDATURA"));
                    classif = new Classificacao(rs2.getInt("lugarClassificacao"), rs2.getDate("datahora"), cand);
                    classificacoes.add(classif);
                }

                pS = new ProcessoSeriacao(data, colab, anuncio, participantes, classificacoes);
                tmp.add(pS);
                
            }

            return tmp;
        } catch (SQLException e) {
            throw new SQLException("Error at getProcessosSeriacaoByOrgNif" + e.getMessage());
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (rs != null) {
                rs.close();
            }
            if (rs2 != null) {
                rs2.close();
            }
        }
    }
}
