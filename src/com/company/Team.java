package com.company;

import java.util.*;

class Team {
    private List<Player> players;
    private String name;
    private int experiencedPlayerCount;
    private int inexperiencedPlayerCount;
    private int averageHeight;
    private List<String> guardians;

    Team(String name, List<Player> players) {
        this.name = name;
        this.players = players;

        this.createStats();
        this.createTeamGuardians();
    }

    String getName() {
        return this.name;
    }

    int playerCount() {
        return players.size();
    }

    List<String> getPlayerNames() {
        List<String> names = new ArrayList<>(this.players.size());

        for (Player player : this.players) {
            names.add(player.getName());
        }
        names.sort(new LastNameComparator());

        return names;
    }

    private void createStats() {
        int totalPlayers = this.players.size();
        int experienceCount = 0;
        int totalHeight = 0;

        for (Player player : this.players) {
            if (player.hasExperience()) {
                experienceCount++;
            }

            totalHeight += player.getHeight();
        }

        this.averageHeight = totalHeight / totalPlayers;
        this.experiencedPlayerCount = experienceCount;
        this.inexperiencedPlayerCount = totalPlayers - experienceCount;
    }

    int getExperiencedPlayerCount() {
        return experiencedPlayerCount;
    }

    int getInexperiencedPlayerCount() {
        return inexperiencedPlayerCount;
    }

    private void createTeamGuardians() {
        Set<String> guardiansSet = new HashSet<>();

        for (Player player : this.players) guardiansSet.addAll(player.getGuardians());

        this.guardians = new ArrayList<>(guardiansSet);
        this.guardians.sort(new LastNameComparator());
    }

    List<String> getTeamGuardians() {
        return this.guardians;
    }

    int getAverageHeight() {
        return averageHeight;
    }
}