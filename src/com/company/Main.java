package com.company;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String userResponse;
        int teamId;

        Scanner scanner = new Scanner(System.in);

        StatsTool createTeams = new StatsTool();
        System.out.println(createTeams.startApp());
        System.out.print(createTeams.askForOption());

        userResponse = scanner.next();
        while (!userResponse.toLowerCase().equals("q") && !userResponse.toLowerCase().equals("quit")) {
            try {
                teamId = Integer.parseInt(userResponse);
            } catch (NumberFormatException ex) {
                System.out.println("Must use a number to select a team.");
                userResponse = scanner.next();
                continue;
            }

            try {
                System.out.println(createTeams.printTeamStats(teamId));
            } catch (IllegalArgumentException ex) {
                System.out.println("Uh oh! Thats not a correct team id. Please choose from one of the available teams.");
                userResponse = scanner.next();
                continue;
            }

            scanner.next();
            System.out.print(createTeams.printMenu());
            System.out.print(createTeams.askForOption());

            userResponse = scanner.next();
            clearScreen();
        }

        System.out.println("Bye!");
    }

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
