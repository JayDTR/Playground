/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.data;

import com.company.model.Colaborador;
import com.company.model.CompetenciaTecnica;
import com.company.model.Organizacao;
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
public class ColaboradorDB extends DataHandler{
    
      public List<Colaborador> getColaboradoresByOrg(Organizacao org) throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT utilizador.email,utilizador.nome,utilizador.telefone,colaborador.niforganizacao FROM Colaborador"
                    + " join utilizador on Colaborador.emailColaborador = utilizador.email where colaborador.niforganizacao = "+Integer.parseInt(org.getNif()));

            Colaborador colab = null;

            List<Colaborador> tmp = new ArrayList<Colaborador>();
            while (rs.next()) {
                colab = new Colaborador(rs.getString("Nome"), String.valueOf(rs.getInt("telefone")), rs.getString("email"));
                tmp.add(colab);
            }
            rs.close();
            return tmp;
        } catch (SQLException e) {
            throw new SQLException("Error at getColaboradorByOrg" + e.getMessage());
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

    public List<Colaborador> getAllColaboradores() throws SQLException {
          Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT utilizador.email,utilizador.nome,utilizador.telefone,colaborador.niforganizacao FROM Colaborador"
                    + " join utilizador on Colaborador.emailColaborador = utilizador.email");

            Colaborador colab = null;

            List<Colaborador> tmp = new ArrayList<Colaborador>();
            while (rs.next()) {
                colab = new Colaborador(rs.getString("Nome"), String.valueOf(rs.getInt("telefone")), rs.getString("email"));
                tmp.add(colab);
            }
            rs.close();
            return tmp;
        } catch (SQLException e) {
            throw new SQLException("Error at getAllColaboradores" + e.getMessage());
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

    public Colaborador insertColab(Colaborador colab) throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            stmt = conn.createStatement();
            
            rs = stmt.executeQuery(CommonDB.insertColabAsUtilizador(colab));

            rs = stmt.executeQuery("INSERT INTO Colaborador (emailcolaborador, niforganizacao) VALUES('"
                    + colab.getEmail() + "', "
                    + Plataforma.getInstance().getRo().getOrganizacaoByEmailUtilizador(Plataforma.getInstance().getUsersAPI().getEmail()).getNif() + ")");

            return colab;
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

    
   
    
}
