package com.remigiusz.mastermindgame;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MasterMindGameTest {

    private MasterMindGame masterMindGame;
    @Before
    public void setUp() throws Exception {
        masterMindGame = new MasterMindGame();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void TestAllTheSameGetScore2() {
        masterMindGame.setComputerCode("1234");

        assertEquals("1111", masterMindGame.getScore2("1234"));
        assertEquals("110", masterMindGame.getScore2("1223"));
        assertEquals("0000", masterMindGame.getScore2("4321"));
        assertEquals("111", masterMindGame.getScore2("1235"));
        assertEquals("1", masterMindGame.getScore2("4444"));
    }
    @Test
    public void TestAllTheSameInCodeGetScore2() {
        masterMindGame.setComputerCode("1111");

        assertEquals("1111", masterMindGame.getScore2("1111"));
        assertEquals("111", masterMindGame.getScore2("1121"));
        assertEquals("11", masterMindGame.getScore2("2211"));
        assertEquals("111", masterMindGame.getScore2("3111"));
        assertEquals("1", masterMindGame.getScore2("1234"));
    }
    @Test
    public void StarndardGetScore2() {
        masterMindGame.setComputerCode("1221");

        assertEquals("1111", masterMindGame.getScore2("1221"));
        assertEquals("11", masterMindGame.getScore2("1331"));
        assertEquals("11", masterMindGame.getScore2("3223"));
        assertEquals("0000", masterMindGame.getScore2("2112"));
        assertEquals("111", masterMindGame.getScore2("1321"));
        assertEquals("1", masterMindGame.getScore2("3331"));
    }
}