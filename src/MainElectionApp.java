import data.models.*;
import exceptions.invalidPassword;
import exceptions.tooYoungToVote;

import java.time.LocalDateTime;
import java.util.Scanner;

public class MainElectionApp {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String user = """
                Enter Role:
                1.Admin
                2.Voter
                """;
        while (true) {
            System.out.print(user);
            String role = input.nextLine();

            LocalDateTime startTime = LocalDateTime.now();
            LocalDateTime stopTime = LocalDateTime.now();
            Election election = null;
            BallotBox ballotBox = new BallotBox();
            switch (role) {
                case "1" -> {
                    Admin currentAdmin = null;

                    while (currentAdmin == null) {
                        System.out.println("Verify That It's You");
                        System.out.println("Enter Your Name, Age and Password:");

                        try {
                            System.out.print("Name: ");
                            String name = input.nextLine();

                            System.out.print("Age: ");

                            int age = Integer.parseInt(input.nextLine());

                            System.out.print("Password: ");
                            String pass = input.nextLine();


                            currentAdmin = new Admin(pass, name, age, true);

                            System.out.println("Successfully Verified!");

                        } catch (tooYoungToVote | invalidPassword e) {

                            System.out.println(e.getMessage());

                        }
                    }

                    String console = """
                            1.Create Election
                            2.Vote
                            3.Delete Ballot
                            4.Stop Election
                            5.Count Votes
                            6.Declare The Election Winner
                            """;
                    System.out.print(console);
                    System.out.println("Enter choice: ");
                    switch (input.nextLine()) {
                        case "1" -> {
                            System.out.print("Enter start time");
                            String time = """
                                    1.Right now
                                    2.In two days
                                    3.In three weeks
                                    4.In five hours
                                    """;
                            System.out.println(time);
                            String start = input.nextLine();

                            switch (start) {
                                case "1" -> startTime = LocalDateTime.now();

                                case "2" -> startTime = startTime.plusDays(2);

                                case "3" -> startTime = startTime.plusWeeks(3);

                                case "4" -> startTime = startTime.plusHours(5);

                                default -> System.out.println("Invalid Input");


                            }

                            System.out.print("Enter stop time");
                            System.out.println(time);

                            switch (input.nextLine()) {
                                case "1" -> stopTime = LocalDateTime.now();

                                case "2" -> stopTime = startTime.plusDays(2);

                                case "3" -> stopTime = startTime.plusWeeks(3);

                                case "4" -> stopTime = startTime.plusHours(5);

                                default -> System.out.println("Invalid Input");

                            }

//                            election = new Election(ballotBox, startTime, stopTime);
//                            System.out.println("Election has been Created On " + startTime + " To End On " + stopTime);
                        }
                        case "2" -> {
                            System.out.println("Cast Vote");
                            System.out.print("Choose Party");
                            String party = """
                                    1.APC
                                    2.PDP
                                    3.LP
                                    4.NNPP
                                    5.APGA
                                    """;
                            PoliticalParty politicalParty = PoliticalParty.APC;
                            System.out.println(party);
                            switch (input.nextLine()) {
                                case "1" -> politicalParty = PoliticalParty.APC;
                                case "2" -> politicalParty = PoliticalParty.PDP;
                                case "3" -> politicalParty = PoliticalParty.NNPP;
                                case "4" -> politicalParty = PoliticalParty.APGA;
                            }
                            currentAdmin.castVote(election, currentAdmin, politicalParty, ballotBox);
                        }
                        case "3" -> {
                            boolean correctBallot = false;
                            while (!correctBallot) {
                                System.out.println("Enter ballot number: ");
                                int ballotNumber = input.nextInt();
                                try {
                                    currentAdmin.deleteBallot(ballotBox, ballotNumber);
                                    correctBallot = true;
                                } catch (IllegalArgumentException e) {
                                    System.out.println(e.getMessage());
                                }
                            }
                        }

                        case "4" -> System.out.println(currentAdmin.stopElection(election, LocalDateTime.now()));

                        case "5" -> currentAdmin.countVotes(ballotBox);

                        case "6" -> currentAdmin.declareTheWinner(ballotBox);
                    }


                }

                case "2" -> {
                    System.out.println();
                    System.out.println("Welcome Voter Enter Your Information");
                    System.out.print("Enter Your Name: ");
                    String name = input.nextLine();
                    System.out.print("Enter Your Age: ");
                    int age = Integer.parseInt(input.nextLine());
                    Voter voter = null;
                    try {
                        voter = new Voter(name, age, false);
                    } catch (tooYoungToVote e) {
                        System.out.println(e.getMessage());
                    }

                    System.out.print("Enter Vote");
                    System.out.print("Choose Party");
                    String party = """
                            1.APC
                            2.PDP
                            3.LP
                            4.NNPP
                            5.APGA
                            """;
                    PoliticalParty politicalParty = PoliticalParty.APC;
                    System.out.println(party);
                    switch (input.nextLine()) {
                        case "1" -> politicalParty = PoliticalParty.APC;
                        case "2" -> politicalParty = PoliticalParty.PDP;
                        case "3" -> politicalParty = PoliticalParty.NNPP;
                        case "4" -> politicalParty = PoliticalParty.APGA;
                    }
                    voter.castVote(election, voter, politicalParty, ballotBox);

                }

            }
        }
    }
}