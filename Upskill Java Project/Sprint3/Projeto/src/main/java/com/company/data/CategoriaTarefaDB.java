/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.data;

import com.company.model.AreaAtividade;
import com.company.model.CaraterCT;
import com.company.model.CategoriaTarefa;
import com.company.model.Plataforma;
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
public class CategoriaTarefaDB extends DataHandler {

    public CategoriaTarefa insertCategoriaTarefa(CategoriaTarefa ct) throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            stmt = conn.createStatement();

            //insere uma categoria na tabela
            rs = stmt.executeQuery("INSERT INTO CATEGORIATAREFA (DESCRICAO, IDAREAATIVIDADE) VALUES('"
                    + ct.getDescricao() + "', '"
                    + ct.getAreaAtividade().getId() + "')");

            //vai buscar o id da ultima categoria criada
            rs = stmt.executeQuery("SELECT MAX(ID) ID FROM CATEGORIATAREFA");
            int maxId = 1;
            if (rs.next()) {
                maxId = rs.getInt("ID");
            }

            //preenche a tabela CaracterCT com competencias técnicas da categoria de tarefa criada
            for (CaraterCT caracter : ct.getLcompTec()) {
                
                rs = stmt.executeQuery("INSERT INTO CARATERCT(IDCATEGORIATAREFA, CODCOMPETENCIATECNICA, OBRIGATORIO, GRAUMINIMOPROFICIENCIA) VALUES("
                        + maxId + ", '"
                        + caracter.getCodCompetenciaTecnica() + "', "
                        + caracter.getObrigatorio() + ", '"
                        + caracter.getGrauMinimoProficiencia().split(" ")[0] + "')");
            }
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
        }
    }

    public List<CategoriaTarefa> getAllCategoriasTarefa() throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        Statement stmt1 = null;
        ResultSet rs = null;
        ResultSet rs1 = null;
        try {
            conn = getConnection();
            stmt = conn.createStatement();
            stmt1 = conn.createStatement();

            rs = stmt.executeQuery("SELECT * FROM CATEGORIATAREFA");
            
            CategoriaTarefa categoria = null;
            CaraterCT cCT = null;

            List<CategoriaTarefa> tmp = new ArrayList<CategoriaTarefa>();
            while (rs.next()) {
                String descricao = rs.getString("DESCRICAO");
                AreaAtividade areaAtividade = Plataforma.getInstance().getLat().getAreaAtividadeById(rs.getString("IDAREAATIVIDADE"));
                ArrayList<CaraterCT> lcompt = new ArrayList<>();
                
                
                rs1 = stmt1.executeQuery("SELECT * FROM CARATERCT WHERE IDCATEGORIATAREFA = " + rs.getInt("id"));
                while (rs1.next()) {
                    cCT = new CaraterCT(rs1.getString("idcategoriatarefa"),rs1.getString("codcompetenciatecnica"),rs1.getInt("obrigatorio"), rs1.getString("grauminimoproficiencia"));
                    lcompt.add(cCT);
                }
                
                categoria = new CategoriaTarefa(descricao, areaAtividade, lcompt);
                categoria.setIdCategoria(rs.getInt("id") + "");
                // String descricao, AreaAtividade oArea, HashMap<CompetenciaTecnica, CaracterCT> listCompTec
                tmp.add(categoria);
            }
            //não fechar porque o método close faz return antes to tmp.
            return tmp;
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
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
        }
    }
    
    public List<CaraterCT> getListCaraterCTByCatTarId(String id) throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM CARATERCT WHERE IDCATEGORIATAREFA = "+Integer.valueOf(id));

            CaraterCT cCT = null;

            List<CaraterCT> tmp = new ArrayList<CaraterCT>();
            while (rs.next()) {
                cCT = new CaraterCT(rs.getString("idCategoriaTarefa"), rs.getString("codcompetenciatecnica"),rs.getInt("obrigatorio"),rs.getString("grauminimoproficiencia"));
                tmp.add(cCT);
            }
            rs.close();
            return tmp;
        } catch (SQLException e) {
            throw new SQLException("Error at getListCaraterCTByCatTarId" + e.getMessage());
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
