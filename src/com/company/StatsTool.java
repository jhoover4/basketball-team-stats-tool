package com.company;

import java.util.*;
import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.*;

class StatsTool {
    private List<Team> teams;
    private List<String> teamNames;
    private List<Player> players;
    private JSONObject jsonData;

    StatsTool() {
        try {
            this.readJson();
            this.createPlayers();
            this.createTeams();
        } catch (Exception e) {
            System.out.println("File not found:" + e);
        }
    }

    private void readJson() throws Exception {
        Object obj = new JSONParser().parse(new FileReader("team_fixture.json"));
        this.jsonData = (JSONObject) obj;
    }

    @SuppressWarnings("unchecked cast")
    private void createPlayers() {
        ArrayList<HashMap> rawPlayerData = (ArrayList<HashMap>) this.jsonData.get("players");

        this.players = new ArrayList<>(rawPlayerData.size());
        for (HashMap playerData : rawPlayerData) {
            Player player = new Player(playerData);
            this.players.add(player);
        }
    }

    private void staggerPlayersByExperience() {
        List<Player> expPlayers = new ArrayList<>();
        List<Player> inExpPlayers = new ArrayList<>();
        List<Player> staggeredPlayers = new ArrayList<>();

        for (Player player : this.players) {
            if (player.hasExperience()) {
                expPlayers.add(player);
            } else {
                inExpPlayers.add(player);
            }
        }

        Iterator<Player> expPlayersIt = expPlayers.iterator();
        Iterator<Player> inExpPlayersIt = inExpPlayers.iterator();

        while (expPlayersIt.hasNext() || inExpPlayersIt.hasNext()) {
            if (expPlayersIt.hasNext()) staggeredPlayers.add(expPlayersIt.next());
            if (inExpPlayersIt.hasNext()) staggeredPlayers.add(inExpPlayersIt.next());
        }

        this.players = staggeredPlayers;
    }

    private void createTeams() {
        this.setTeamNames();
        this.teams = new ArrayList<>();

        this.staggerPlayersByExperience();
        int increment = Math.round(this.players.size() / this.teamNames.size());
        int startSubList = 0;
        int endSubList = increment;
        for (int i = 0; i < this.teamNames.size(); i++) {
            String teamName = this.teamNames.get(i);

            Team team = new Team(teamName, this.players.subList(startSubList, endSubList));
            this.teams.add(team);

            startSubList = endSubList;
            endSubList = increment * (i + 2);
        }
    }

    @SuppressWarnings("unchecked cast")
    private void setTeamNames() {
        this.teamNames = (ArrayList<String>) this.jsonData.get("teams");
    }

    String startApp() {
        String prompt;

        prompt = "BASKETBALL STATS TEAM TOOL\n\n";
        prompt += "---- MENU----\n\n";
        prompt += printMenu() + "\n";
        prompt += "Press 'q' or 'quit' at any time to quit.\n";

        return prompt;
    }

    private String printList(List<String> listData) {
        String prompt;

        int i = 1;
        StringBuilder promptBuilder = new StringBuilder("\n");
        for (String item : listData) {
            promptBuilder.append(i).append(") ").append(item).append("\n");
            i++;
        }
        prompt = promptBuilder.toString();

        return prompt;
    }

    String printMenu() {
        String prompt = "Teams with available stats:\n";
        prompt += this.printList(this.teamNames);

        return prompt;
    }

    String askForOption() {
        return "\nEnter an option > ";
    }

    String printTeamStats(int teamId) throws IllegalArgumentException {

        if (teamId > this.teams.size()) {
            throw new IllegalArgumentException("Invalid id.");
        }

        String prompt;
        Team team = this.teams.get(teamId - 1);

        prompt = "Team: " + team.getName() + " Stats\n";
        prompt += "-------------\n";
        prompt += "Total Players: " + team.playerCount() + "\n\n";

        prompt += "Players on Team:\n";
        prompt += String.join(", ", team.getPlayerNames()) + "\n\n";

        prompt += "Experienced player count: ";
        prompt += team.getExperiencedPlayerCount() + "\n";
        prompt += "Inexperienced player count: ";
        prompt += team.getInexperiencedPlayerCount() + "\n";
        prompt += "Average Height: ";
        prompt += team.getAverageHeight() + " inches\n\n";
        prompt += "Guardians: ";
        prompt += String.join(", ", team.getTeamGuardians()) + "\n\n";

        prompt += "Press any key to continue...";

        return prompt;
    }
}
