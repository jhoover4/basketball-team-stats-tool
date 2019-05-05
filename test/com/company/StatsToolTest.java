package com.company;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StatsToolTest {
    private StatsTool statsTool;

    @Before
    public void setUp() throws Exception {
        this.statsTool = new StatsTool();
    }

    @Test
    public void startApp() {
        String prompt = "BASKETBALL STATS TEAM TOOL\n\n" +
                "---- MENU----\n\n" +
                "Teams with available stats:\n\n" +
                "1) Panthers\n" +
                "2) Bandits\n" +
                "3) Warriors\n\n" +
                "Press 'q' or 'quit' at any time to quit.\n";

        assertEquals(prompt, statsTool.startApp());
    }

    @Test
    public void printMenu() {
        String prompt = "Teams with available stats:\n" +
                "\n" +
                "1) Panthers\n" +
                "2) Bandits\n" +
                "3) Warriors\n";

        assertEquals(prompt, statsTool.printMenu());
    }

    @Test
    public void askForOption() {
        assertEquals("\nEnter an option > ", statsTool.askForOption());
    }

    @Test
    public void printTeamStats() {
        String prompt = "Team: Panthers Stats\n" +
                "-------------\n" +
                "Total Players: 6\n\n" +
                "Players on Team:\n" +
                "Sammy Adams, Chloe Alaska, Bill Bon, Matt Gill, Phillip Helm, Karl Saygan\n\n" +
                "Experienced player count: 3\n" +
                "Inexperienced player count: 3\n" +
                "Average Height: 43 inches\n\n" +
                "Guardians: Jeff Adams, Gary Adams, Jamie Alaska, David Alaska, Heather Bledsoe, Sara Bon, Jenny Bon, " +
                "Charles Gill, Sylvia Gill, Thomas Helm, Eva Jones\n\n" +
                "Press any key to continue...";

        assertEquals(prompt, statsTool.printTeamStats(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowErrorIfIncorrectId() {
        statsTool.printTeamStats(99);
    }
}