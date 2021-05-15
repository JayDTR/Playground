/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.data;

import com.company.model.AreaAtividade;
import java.sql.CallableStatement;
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
public class AreaAtividadeDB extends DataHandler {

    public AreaAtividade insertAA(AreaAtividade aa) throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        CallableStatement cs = null;
        try {
            conn = getConnection();
            cs = conn.prepareCall("{CALL createAreaAtividade(?, ?, ?)}");

            conn.setAutoCommit(false);

            cs.setString(1, aa.getId());
            cs.setString(2, aa.getDescBreve());
            cs.setString(3, aa.getDescDetalhada());

            cs.executeQuery();

            conn.commit();
            cs.close();

            return aa;
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

    public List<AreaAtividade> getAll() throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM AREAATIVIDADE");

            AreaAtividade aa = null;

            List<AreaAtividade> tmp = new ArrayList<AreaAtividade>();
            while (rs.next()) {
                aa = new AreaAtividade(rs.getString("ID"), rs.getString("descBreve"), rs.getString("descdetalhada"));
                tmp.add(aa);
            }
            rs.close();
            return tmp;
        } catch (SQLException e) {
            throw new SQLException("Error at getAllPharmacys" + e.getMessage());
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
