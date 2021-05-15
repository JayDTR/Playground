/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.data;

import com.company.model.Colaborador;
import com.company.model.CompetenciaTecnica;
import com.company.model.EnderecoPostal;
import com.company.model.Freelancer;
import com.company.model.GrauProficiencia;
import com.company.model.HabilitacaoAcademica;
import com.company.model.Plataforma;
import com.company.model.ReconhecimentoCT;
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
public class FreelancerDB extends DataHandler {

    public List<Freelancer> getAllFreelancers() throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        Statement stmt2 = null;
        ResultSet rs = null;
        ResultSet rs2 = null;
        ResultSet rs3 = null;
        try {
            conn = getConnection();
            stmt = conn.createStatement();
            stmt2 = conn.createStatement();
            rs = stmt.executeQuery("select * from freelancer join utilizador on freelancer.email = utilizador.email join enderecopostal on freelancer.idenderecopostal = enderecopostal.id");

            Freelancer free = null;

            List<Freelancer> tmp = new ArrayList<Freelancer>();
            ArrayList<HabilitacaoAcademica> tmp1 = null;
            ArrayList<ReconhecimentoCT> tmp2 = null;
            while (rs.next()) {
                tmp1 = new ArrayList<>();
                tmp2 = new ArrayList<>();
                int nif = rs.getInt("Nif");
                String morada = rs.getString(8);
                String codPostal = rs.getString(9);
                String localidade = rs.getString(10);
                String nome = rs.getString("Nome");
                String email = rs.getString(2);
                String telefone = String.valueOf(rs.getInt("telefone"));

                rs2 = stmt2.executeQuery("select * from habilitacaoacademica where niffreelancer =" + nif);

                while (rs2.next()) {
                    tmp1.add(new HabilitacaoAcademica(rs2.getString("grau"), rs2.getString("designacaocurso"), rs2.getString("nomeinstituicao"), rs2.getDouble("mediacurso")));
                }

                rs3 = stmt2.executeQuery("select * from reconhecimentoCT where niffreelancer =" + nif);

                while (rs3.next()) {
                    CompetenciaTecnica ct = Plataforma.getInstance().getRct().getCompetenciaTecnicaById(rs3.getString("codcompetenciatecnica"));
                    GrauProficiencia gp = ct.getGrausProficiencia().getGrauProficienciaFromCTByValor(ct.getId(), rs3.getString("valorcompetenciatecnicareconhecido"));
                    tmp2.add(new ReconhecimentoCT(rs3.getDate("datareconhecimento"), gp));
                }
                EnderecoPostal ep = new EnderecoPostal(morada, codPostal, localidade);
                free = new Freelancer(nome, String.valueOf(nif), email, telefone, ep, tmp1, tmp2);
                tmp.add(free);
            }
            rs.close();
            return tmp;
        } catch (SQLException e) {
            throw new SQLException("Error at getAllFreelancers" + e.getMessage());
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

    public void add(Freelancer free) throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        PreparedStatement pS = null;
        try {
            conn = getConnection();
            stmt = conn.createStatement();

            //id endereco postal
            rs = stmt.executeQuery("select max(id) id from enderecopostal");
            int id = 1;
            if (rs.next()) {
                id = rs.getInt("id");
            }

            pS = conn.prepareStatement("INSERT INTO ENDERECOPOSTAL (MORADA, CODIGOPOSTAL, LOCALIDADE) VALUES(?,?,?)");

            pS.setString(1, free.getEndPostal().getRua());
            pS.setString(2, free.getEndPostal().getCodPostal());
            pS.setString(3, free.getEndPostal().getLocalidade());

            pS.executeUpdate();

            pS = conn.prepareStatement("INSERT INTO UTILIZADOR (EMAIL, NOME, TELEFONE) VALUES(?,?,?)");
            pS.setString(1, free.getEmail());
            pS.setString(2, free.getNome());
            pS.setInt(3, Integer.valueOf(free.getTelefone().trim()));
            pS.executeUpdate();

            pS = conn.prepareStatement("INSERT INTO FREELANCER (NIF, EMAIL, IDENDERECOPOSTAL) VALUES(?,?,?)");
            pS.setString(1, free.getNif());
            pS.setString(2, free.getEmail());
            pS.setInt(3, (id + 1));
            pS.executeUpdate();
            

            String sql = "INSERT INTO ReconhecimentoCT (dataReconhecimento, NIFFreelancer , valorcompetenciatecnicareconhecido,codcompetenciatecnica) "
                    + "VALUES(?,?,?,?)";
            pS = conn.prepareStatement(sql);
            for (ReconhecimentoCT rCT : free.getlRecFree()) {
                pS.setDate(1, rCT.getDataReconhecimento());
                pS.setInt(2, Integer.valueOf(free.getNif().trim()));
                pS.setString(3, rCT.getGrauReconhecido().getValor());
                pS.setString(4, rCT.getGrauReconhecido().getCompT().getId());
                pS.executeUpdate();
            }

            sql = "INSERT INTO HabilitacaoAcademica (Grau,Designacaocurso,NomeInstituicao,MediaCurso,NifFreelancer)values(?,?,?,?,?)";
            pS = conn.prepareStatement(sql);
            for (HabilitacaoAcademica hAcad : free.getlHabAc()) {
                pS.setString(1, hAcad.getGrau());
                pS.setString(2, hAcad.getNomeCurso());
                pS.setString(3, hAcad.getFaculdade());
                pS.setDouble(4, hAcad.getMedia());
                pS.setInt(5, Integer.valueOf(free.getNif()));
                pS.executeUpdate();
            }

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
