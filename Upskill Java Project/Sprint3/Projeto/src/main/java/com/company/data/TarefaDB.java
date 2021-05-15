/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.data;

import com.company.model.CategoriaTarefa;
import com.company.model.Colaborador;
import com.company.model.Organizacao;
import com.company.model.Plataforma;
import com.company.model.Tarefa;
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
public class TarefaDB extends DataHandler {

    public void save(Tarefa tarefa) throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        Statement stmt1 = null;
        ResultSet rs1 = null;

        try {
            conn = getConnection();
            stmt = conn.createStatement();
            stmt1 = conn.createStatement();

            String a = tarefa.getColab().getEmail();
            rs1 = stmt1.executeQuery("SELECT * FROM COLABORADOR WHERE EMAILCOLABORADOR='" + tarefa.getColab().getEmail() + "'");
            int nifOrg = 0;
            while (rs1.next()) {
                nifOrg = rs1.getInt("nifOrganizacao");
            }
            //insere uma tarefa na tabela

            rs = stmt.executeQuery("INSERT INTO TAREFA (REFERENCIA, DESIGNACAO, DESCINFORMAL,DESCTECNICA, DURACAOEST, CUSTOEST, EMAILCOLABORADOR, NIFORGANIZACAO, IDCATTAR) VALUES('"
                    + tarefa.getRef() + "','"
                    + tarefa.getDesignacao() + "','"
                    + tarefa.getDescrInformal() + "','"
                    + tarefa.getDescrTecnica() + "',"
                    + tarefa.getDuracaoEst() + ","
                    + tarefa.getCustoEst() + ",'"
                    + tarefa.getColab().getEmail() + "',"
                    + nifOrg + ","
                    + String.valueOf(tarefa.getCatTarefa().getId().split("-")[1]) + ")");

        } catch (SQLException e) {
            throw new SQLException("Error at getAllOrgs" + e.getMessage());
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (rs != null) {
                rs.close();
            }
            if (stmt1 != null) {
                stmt1.close();
            }
            if (rs1 != null) {
                rs1.close();
            }
            try {
                //
            } catch (Exception e) {
            }
        }
    }

    public Tarefa find(String referencia) throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM TAREFA WHERE REFERENCIA = '" + referencia + "'");
            Tarefa tarefaEncontrada = null;
            while (rs.next()) {
                CategoriaTarefa ct = Plataforma.getInstance().getListaCategoriaTarefa().getCategoriaTarefaById("CatTar-" + rs.getString("IDCATTAR"));
                tarefaEncontrada = new Tarefa(rs.getString("REFERENCIA"), rs.getString("DESIGNACAO"),
                        rs.getString("DESCINFORMAL"), rs.getString("DESCTECNICA"),
                        rs.getInt("DURACAOEST"), rs.getDouble("CUSTOEST"),
                        // espera repositorio
                        Plataforma.getInstance().getListaCategoriaTarefa().getCategoriaTarefaById("CatTar-" + rs.getString("IDCATTAR")),
                        Plataforma.getInstance().getRc().getColaboradorByEmail(rs.getString("EMAILCOLABORADOR"))
                );
            }

            rs.close();
            return tarefaEncontrada;
        } catch (SQLException e) {
            throw new SQLException("Erro em getTarefaByRef" + e.getMessage());
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

    public List<Tarefa> getAll() throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM TAREFA");

            Tarefa tarefa = null;
            List<Tarefa> listaTarefas = new ArrayList<>();
            while (rs.next()) {
                tarefa = new Tarefa(rs.getString("REFERENCIA"), rs.getString("DESIGNACAO"),
                        rs.getString("DESCINFORMAL"), rs.getString("DESCTECNICA"),
                        rs.getInt("DURACAOEST"), rs.getDouble("CUSTOEST"),
                        // espera repositorio
                        Plataforma.getInstance().getListaCategoriaTarefa().getCategoriaTarefaById("CatTar-" + rs.getString("IDCATTAR")),
                        Plataforma.getInstance().getRc().getColaboradorByEmail(rs.getString("EMAILCOLABORADOR")));
                listaTarefas.add(tarefa);
            }

            return listaTarefas;
        } catch (SQLException e) {
            throw new SQLException("Error at getTarefasByOrg" + e.getMessage());
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

    public List<Tarefa> getTarefasByOrg(Organizacao org) throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from tarefa where niforganizacao = " + Integer.valueOf(org.getNif()));
            Tarefa tar = null;
            List<Tarefa> tmp = new ArrayList<Tarefa>();
            while (rs.next()) {
                tar = new Tarefa(rs.getString("referencia"), rs.getString("designacao"),
                        rs.getString("descInformal"), rs.getString("descTecnica"),
                        rs.getInt("duracaoEst"), rs.getDouble("custoest"),
                        Plataforma.getInstance().getListaCategoriaTarefa().getCategoriaTarefaById("CatTar-" + rs.getString("IdCatTar")),
                        Plataforma.getInstance().getRc().getColaboradorByEmail(rs.getString("EMAILCOLABORADOR")));
                        //org.getRepositorioColaborador().getColaboradorByEmail(rs.getString("emailColaborador")));
                tmp.add(tar);
            }
            rs.close();
            return tmp;
        } catch (SQLException e) {
            throw new SQLException("Error at getTarefasByOrg" + e.getMessage());
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
