/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.data;

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
public class OrganizacaoDB extends DataHandler {

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
            rs = stmt.executeQuery(insertOrgQuery(org, id + 1));
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

        return "INSERT INTO ORGANIZACAO (NIF, EMAIL, IDENDERECOPOSTAL,EMAILGESTOR, WEBSITE) "
                + "VALUES ("+org.getNif()+",'"+org.getEmail()+"',"+idEndPostal+",'"+org.getGestor().getEmail()+"','"+org.getEmail()+"')";
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
                colab = new Colaborador(rs.getString("NOME"), rs.getString(String.valueOf("TELEFONE")), rs.getString("EMAIL"));
                tmpColab.add(colab);
            }

            while (rs2.next()) {
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

    public Organizacao getOrgByColabEmail(String email) throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            stmt = conn.createStatement();
            String sqlQuery = "Select * from organizacao join enderecopostal on organizacao.idenderecopostal = enderecopostal.id join utilizador on organizacao.email = utilizador.email join utilizador on organizacao.emailgestor = utilizador.email where nif = (Select niforganizacao from colaborador where emailcolaborador = '" + email + "')";
            rs = stmt.executeQuery(sqlQuery);
            Organizacao org = null;
            while (rs.next()) {
                String nome = rs.getString("NOME");
                String nif = String.valueOf(rs.getString("NIF"));
                String site = rs.getString("WEBSITE");
                String telefone = String.valueOf(rs.getInt("TELEFONE"));
                String emailorg = rs.getString("EMAIL");
                String morada = rs.getString("MORADA");
                String codPostal = rs.getString("CODIGOPOSTAL");
                String localidade = rs.getString("LOCALIDADE");
                String nomeGestor = rs.getString(14);
                String telefoneGestor = String.valueOf(rs.getInt(15));
                String emailGestor = rs.getString(13);
                EnderecoPostal end = new EnderecoPostal(morada, codPostal, localidade);
                Colaborador c1 = new Colaborador(nomeGestor, telefoneGestor, emailGestor);
                org = new Organizacao(nome, nif, site, telefone, emailorg, end, c1);
            }

            return org;
        } catch (SQLException e) {
            throw new SQLException("Error at getAllOrgs" + e.getMessage());
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
