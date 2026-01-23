import data.models.*;
import exceptions.invalidPassword;
import exceptions.tooYoungToVote;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Main2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Admin admin = null;
        Election[] election = new Election[1];
        Voter voter = null;
        ArrayList<Voter> voters = new ArrayList<>();
        while (true) {
            String console = """
                    Welcome To Haliya's E-Voting App
                    1.Admin
                    2.Voter
                    """;
            System.out.println(console);
            System.out.print("Enter choice: ");
            String choice = scanner.nextLine();


            switch (choice) {
                case "1" -> {


                    String choice2 = """
                            1.Register
                            2.Login
                            3.Create Election
                            4.View Votes
                            0.Exit
                            """;
                    boolean secondLayer = true;
                    while (secondLayer) {
                        System.out.print(choice2);
                        String option = scanner.nextLine();

                        switch (option) {


                            case "1" -> {
                                System.out.print("Enter username: ");
                                String username = scanner.nextLine();
                                System.out.print("Enter password: ");
                                String password = scanner.nextLine();
                                System.out.print("Enter age: ");
                                int age = scanner.nextInt();

                                try {
                                    admin = new Admin(password, username, age, true);
                                } catch (tooYoungToVote | invalidPassword ex) {
                                    System.out.println(ex.getMessage());
                                }
                                System.out.println("Registered successfully!");


                            }

                            case "2" -> {

                                if (admin != null) {
                                    System.out.println("Log-in");
                                    System.out.println("Enter username: ");
                                    String name = scanner.nextLine();
                                    System.out.println("Enter password: ");
                                    String password = scanner.nextLine();
                                    boolean answer = admin.login(name, password);
                                    if (answer) System.out.println("Welcome back " + name);
                                    else System.out.println("Wrong details");
                                } else System.out.println("You are not registered");
                            }

                            case "3" -> {
                                if (admin == null) System.out.println("You are not registered");

                                else {
                                    System.out.println("Create An Election!");
                                    System.out.print("Enter election name: ");
                                    String name = scanner.nextLine();
                                    System.out.print("Enter start time (yyyy-MM-dd HH:mm): ");
                                    String input = scanner.nextLine();
                                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

                                    LocalDateTime startTime = null;
                                    try {

                                       startTime = LocalDateTime.parse(input, formatter);


                                        System.out.println("Success! Time recorded: " + startTime);


                                    } catch (DateTimeParseException e) {

                                        System.out.println("Please follow the pattern: yyyy-MM-dd HH:mm");
                                    }

                                    System.out.print("Enter stop time (yyyy-MM-dd HH:mm): ");
                                    String input2 = scanner.nextLine();

                                    LocalDateTime stopTime = null;
                                    try {

                                        stopTime = LocalDateTime.parse(input2, formatter);
                                        System.out.println("Success! Time recorded: " + stopTime);
                                        

                                    } catch (DateTimeParseException e) {
                                        System.out.println("Please follow the pattern: yyyy-MM-dd HH:mm");
                                    }

                                    BallotBox ballotBox = new BallotBox();
                                    election[0] = admin.createElection(name, ballotBox, startTime, stopTime);
                                    admin.getElections().add(election[0]);
                                    System.out.println("Enter election status");
                                    System.out.println("Active?(true/false): ");
                                    boolean isActive = scanner.nextBoolean();
                                    election[0].setActive(isActive);
                                    System.out.println("Election has been Created On " + startTime + " To End On " + stopTime);
                                }
                            }

                            case "4" -> {
                                System.out.println("Enter Election Name: ");
                                String name = scanner.nextLine();
                                if (election[0] != null && Objects.equals(name, election[0].getName())) {
                                    for (Ballot ballot : election[0].getBallotBox().getBallots()) {
                                        System.out.println("Ballot number: " + ballot.getBallotNumber());
                                        System.out.println("Political Party: " + ballot.getPoliticalParty());


                                    }

                                } else System.out.println("Election does not exist");

                            }

                            case "0" -> secondLayer = false;


                        }
                    }
                }

                case "2" -> {
                    boolean thirdLayer = true;
                    while(thirdLayer){
                    String choose = """
                            1.Register
                            2.Vote
                            3.Login
                            0.Exit
                            """;
                    System.out.print(choose);
                    System.out.print("Enter choice: ");
                    String input = scanner.nextLine();

                    switch (input) {
                        case "1" -> {
                            System.out.print("Enter name: ");
                            String username = scanner.nextLine();
                            System.out.print("Enter age: ");
                            int age = scanner.nextInt();
                            System.out.print("Enter your phone number: ");
                            String phoneNumber = scanner.next();
                            try {
                                voter = new Voter(username, age, phoneNumber, false);
                            } catch (tooYoungToVote |IllegalArgumentException e) {
                                System.out.println(e.getMessage());
                            }

                            System.out.println("Registered successfully!");
                            voters.add(voter);
                        }

                        case "2" -> {
                            if (voter == null) System.out.println("You are not registered yet");

                            else {
                                if (election[0] == null || !election[0].isActive()) {
                                    System.out.println("No ongoing election");
                                } else {
                                    System.out.println("Place a vote");

                                    String party = """
                                            1.APC
                                            2.PDP
                                            3.LP
                                            4.NNPP
                                            5.APGA
                                            """;
                                    PoliticalParty politicalParty = null;
                                    System.out.println(party);
                                    System.out.print("Choose Your Party: ");
                                    String choices = scanner.nextLine();
                                    ;
                                    switch (choices) {
                                        case "1" -> politicalParty = PoliticalParty.APC;
                                        case "2" -> politicalParty = PoliticalParty.PDP;
                                        case "3" -> politicalParty = PoliticalParty.NNPP;
                                        case "4" -> politicalParty = PoliticalParty.APGA;
                                    }
                                    Ballot ballot = new Ballot(voter, politicalParty);
                                    election[0].getBallotBox().getBallots().add(ballot);
                                    System.out.println("Vote Has Been Added");
                                }
                            }
                        }

                        case "3" -> {
                            System.out.println("Login to your account");
                            System.out.println("Enter phone number: ");
                            String phoneNumber = scanner.nextLine();

                            for (Voter votee : voters) {
                                if (!Objects.equals(votee.getPhoneNumber(), phoneNumber))
                                    System.out.println("Incorrect phone number");
                                else System.out.println("Welcome back " + votee.getUsername());
                            }
                        }

                        case "0" -> thirdLayer = false;
                    }
                }
                }
            }
        }
    }
}