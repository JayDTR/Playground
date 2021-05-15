/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Asus
 */
public class FreelancerTest {
    
    public FreelancerTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of createHabAcademica method, of class Freelancer.
     */
    @Test
    public void testCreateHabAcademica() {
        System.out.println("createHabAcademica");
        String grau = "Licenciatura";
        String nomeCurso = "EI";
        String fac = "ISEP";
        Double media = 15.3;
        HabilitacaoAcademica expResult = new HabilitacaoAcademica("Licenciatura", "EI", "ISEP", 15.3);
        HabilitacaoAcademica result = Freelancer.createHabAcademica(grau, nomeCurso, fac, media);
        assertEquals(expResult, result);
    }

    /**
     * Test of createRecCT method, of class Freelancer.
     */
    @Test
    public void testCreateRecCT() {
        System.out.println("createRecCT");
        Date dataReconhecimento = Date.valueOf("2020-12-12");
        AreaAtividade a = new AreaAtividade("1", "Frontend", "Javascript");
        CompetenciaTecnica c1 = new CompetenciaTecnica("1", "Javascript", "Desenvolvimento aplicacoes em javascript usando react.js", a);
        GrauProficiencia grau = new GrauProficiencia("1", "Fraco", c1);
        ReconhecimentoCT expResult = new ReconhecimentoCT(dataReconhecimento, grau);
        ReconhecimentoCT result = Freelancer.createRecCT(dataReconhecimento, grau);
        assertEquals(expResult, result);
    }

    /**
     * Test of createEndPostal method, of class Freelancer.
     */
    @Test
    public void testCreateEndPostal() {
        System.out.println("createEndPostal");
        String morada = "rua";
        String codPostal = "1234-123";
        String localidade = "Matosinhos";
        EnderecoPostal expResult = new EnderecoPostal("rua", "1234-123", "Matosinhos");
        EnderecoPostal result = Freelancer.createEndPostal(morada, codPostal, localidade);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Freelancer.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");        
        AreaAtividade a = new AreaAtividade("1", "Frontend", "Javascript");
        CompetenciaTecnica c1 = new CompetenciaTecnica("1", "Javascript", "Desenvolvimento aplicacoes em javascript usando react.js", a);
        EnderecoPostal end = new EnderecoPostal("rua", "1234-123", "Matosinhos");
        ArrayList<HabilitacaoAcademica> lHabAc = new ArrayList<>();
        lHabAc.add(new HabilitacaoAcademica("Licenciatura", "EI", "Isep", 15));
        ArrayList<ReconhecimentoCT> lRecFree = new ArrayList<>();
        Date dataRec = Date.valueOf("2012-05-02");
        lRecFree.add(new ReconhecimentoCT(dataRec, new GrauProficiencia("1", "fraco", c1)));
        Freelancer instance = new Freelancer("Jose", "11111111", "jose@jose.pt", "12334322", end, lHabAc, lRecFree);
        Object o = new Freelancer("Jose", "11111111", "jose@jose.pt", "12334322", end, lHabAc, lRecFree);
        boolean expResult = true;
        boolean result = instance.equals(o);
        assertEquals(expResult, result);
    }
    
}
