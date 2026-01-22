import data.models.*;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Scanner;

public class Main2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Admin admin = null;
        LocalDateTime [] time = new LocalDateTime[2];
        LocalDateTime startTime = LocalDateTime.now();
        LocalDateTime stopTime = LocalDateTime.now();
        time[0] = startTime;
        time[1] = stopTime;
        Election [] election = new Election[1];
        Voter voter = null;
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
                            2.Create Election
                            3.View Votes
                            """;
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
                            admin = new Admin(password, username, age, true);
                            System.out.println("Registered successfully!");


                        }

                        case "2" -> {
                            if(admin == null) System.out.println("You are not registered");

                            else {
                                System.out.println("Create An Election!");
                                System.out.print("Enter election name: ");
                                String name = scanner.nextLine();
                                System.out.println("Enter start time");
                                String timer = """
                                        1.Right now
                                        2.In two days
                                        3.In three weeks
                                        4.In five hours
                                        """;
                                System.out.println(timer);
                                String start = scanner.nextLine();
                                switch (start) {
                                    case "1" -> time[0] = LocalDateTime.now();

                                    case "2" -> time[0] = time[0].plusDays(2);

                                    case "3" -> time[0] = time[0].plusWeeks(3);

                                    case "4" -> time[0] = time[0].plusHours(5);

                                    default -> System.out.println("Invalid Input");


                                }

                                System.out.println("Enter stop time");
                                System.out.println(timer);

                                switch (scanner.nextLine()) {
                                    case "1" -> time[1] = LocalDateTime.now();

                                    case "2" -> time[1] = startTime.plusDays(2);

                                    case "3" -> time[1] = startTime.plusWeeks(3);

                                    case "4" -> time[1] = startTime.plusHours(5);

                                    default -> System.out.println("Invalid Input");

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

                        case "3"->{
                            System.out.println("Enter Election Name: ");
                            String name = scanner.nextLine();
                            if(election[0] != null && Objects.equals(name, election[0].getName())){
                                for(Ballot ballot: election[0].getBallotBox().getBallots()){
                                    System.out.println("Ballot number: " + ballot.getBallotNumber());
                                    System.out.println("Political Party: " + ballot.getPoliticalParty());


                                }

                            }

                            else System.out.println("Election does not exist");

                        }


                    }
                }

                case "2" ->{
                    String choose = """
                            1.Register
                            2.Vote
                            """;
                    System.out.print(choose);
                    System.out.print("Enter choice: ");
                    String input = scanner.nextLine();

                    switch (input){
                        case "1" ->{
                            System.out.print("Enter name: ");
                            String username = scanner.nextLine();
                            System.out.print("Enter age: ");
                            int age = scanner.nextInt();
                            voter = new Voter(username, age, false);
                            System.out.println("Registered successfully!");
                        }

                        case "2"->{
                            if(voter == null) System.out.println("You are not registered yet");

                            else{
                                if(election[0] == null || !election[0].isActive()){
                                    System.out.println("No ongoing election");
                                }
                                else{
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
                                    String choices = scanner.nextLine();;
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
                    }
                }
            }
        }
    }
}
