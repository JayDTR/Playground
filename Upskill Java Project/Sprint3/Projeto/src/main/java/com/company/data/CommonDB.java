/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.data;

import com.company.model.Colaborador;
import com.company.model.Organizacao;

/**
 *
 * @author Asus
 */
public class CommonDB {
    public static String insertGestor(Organizacao org) {
        if (org == null) {
            return "";
        }

        return "INSERT INTO COLABORADOR (EMAILCOLABORADOR, NIFORGANIZACAO) VALUES('"
                + org.getGestor().getEmail() + "', "
                + Integer.parseInt(org.getNif()) + ")";
    }
    
    public static String insertEnderecoPostal(Organizacao org) {
        if (org == null) {
            return "";
        }

        return "INSERT INTO ENDERECOPOSTAL (MORADA, CODIGOPOSTAL, LOCALIDADE) VALUES('"
                + org.getEndPostal().getRua() + "', '"
                + org.getEndPostal().getCodPostal() + "', '"
                + org.getEndPostal().getLocalidade() + "')";
    }
    
    public static String insertGestorAsUtilizador(Organizacao org) {
        if (org == null) {
            return "";
        }
        return "INSERT INTO UTILIZADOR (EMAIL, NOME, TELEFONE) VALUES('"
                + org.getGestor().getEmail() + "', '"
                + org.getGestor().getNome() + "', "
                + Integer.parseInt(org.getGestor().getTelefone()) + ")";
    }
    
    public static String insertColabAsUtilizador(Colaborador colab) {
        if (colab == null) {
            return "";
        }
        return "INSERT INTO UTILIZADOR (EMAIL, NOME, TELEFONE) VALUES('"
                + colab.getEmail() + "', '"
                + colab.getNome() + "', "
                + Integer.parseInt(colab.getTelefone()) + ")";
    }

    public static String insertOrgAsUser(Organizacao org) {
        if (org == null) {
            return "";
        }
        return "INSERT INTO UTILIZADOR (EMAIL, NOME, TELEFONE) VALUES('"
                + org.getEmail() + "', '"
                + org.getNome() + "', "
                + Integer.parseInt(org.getTelefone()) + ")";
    }
}
