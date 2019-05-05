package com.company;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

public class TeamTest {
    private Team testTeam;
    private List<Player> testPlayers;

    @Before
    public void setUp() throws Exception {
        HashMap playerOneData = new HashMap<>();
        playerOneData.put("name", "Karl Saygan");
        playerOneData.put("guardians", "Heather Bledsoe");
        playerOneData.put("experience", "YES");
        playerOneData.put("height", "42 inches");

        HashMap playerTwoData = new HashMap<>();
        playerTwoData.put("name", "Robb Stark");
        playerTwoData.put("guardians", "Katlin Stark");
        playerTwoData.put("experience", "NO");
        playerTwoData.put("height", "56 inches");

        this.testPlayers = new ArrayList<>();
        this.testPlayers.add(new Player(playerOneData));
        this.testPlayers.add(new Player(playerTwoData));

        testTeam = new Team("test", this.testPlayers);
    }

    @Test
    public void playerCount() throws Exception {
        assertEquals(2, this.testTeam.playerCount());
    }

    @Test
    public void getPlayerNames() {
        List<String> names = new ArrayList<>(2);
        names.add("Karl Saygan");
        names.add("Robb Stark");

        assertEquals(names, this.testTeam.getPlayerNames());
    }

    @Test
    public void getExperiencedPlayerCount() {
        assertEquals(1, this.testTeam.getExperiencedPlayerCount());
    }

    @Test
    public void getInexperiencedPlayerCount() {
        assertEquals(1, this.testTeam.getInexperiencedPlayerCount());
    }

    @Test
    public void getTeamGuardians() {
        List<String> guardianNames = new ArrayList<>(2);
        guardianNames.add("Heather Bledsoe");
        guardianNames.add("Katlin Stark");

        assertEquals(guardianNames, this.testTeam.getTeamGuardians());
    }

    @Test
    public void getAverageHeight() {
        assertEquals(49, this.testTeam.getAverageHeight());
    }
}