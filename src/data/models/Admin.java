package data.models;

import exceptions.invalidPassword;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Admin extends User {
    private String password = "12345";
    private List<Election> elections;
    private String[] details = new String[2];


    public Admin(String password, String username, int age, boolean isAdmin) {
        super(username, age, isAdmin);
        verifyPassword(password);
        this.elections = new ArrayList<>();
        details[0] = password;
        details[1] = username;

    }




    public boolean login(String username, String password){
        return Objects.equals(password, details[0]) && Objects.equals(username, details[1]);
    }



    public Election createElection(String name, BallotBox ballotBox, LocalDateTime startTime, LocalDateTime endTime) {
        return new Election(name, ballotBox, startTime, endTime);
    }

    public String stopElection(Election election, LocalDateTime endTime) {
        election.setActive(false);
        return "Election has been cancelled";
    }

    public String deleteBallot(BallotBox ballotBox, int ballotNumber) {
        validateBallotNumber(ballotBox, ballotNumber);
        ballotBox.getBallots().removeIf(ballot -> ballotNumber == ballot.getBallotNumber());
        return "Ballot has been removed";


    }

    private void verifyPassword(String password) {
        if(!Objects.equals(password, this.password)) {
            throw new invalidPassword("Passwords don't match");
        }
    }

    public int[] countVotes(BallotBox ballotBox) {
        int[] partyVotes = new int[5];
        List<Ballot> apcBallot = new ArrayList<>();
        List<Ballot> pdpBallot = new ArrayList<>();
        List<Ballot> lpBallot = new ArrayList<>();
        List<Ballot> nnppBallot = new ArrayList<>();
        List<Ballot> apgaBallot = new ArrayList<>();
        for (Ballot ballot : ballotBox.getBallots()) {
            if (ballot.getPoliticalParty() == PoliticalParty.APC) {
                    apcBallot.add(ballot);
            }
            if (ballot.getPoliticalParty() == PoliticalParty.PDP) {
                pdpBallot.add(ballot);
            }
            if (ballot.getPoliticalParty() == PoliticalParty.LP) {
                lpBallot.add(ballot);
            }
            if (ballot.getPoliticalParty() == PoliticalParty.NNPP) {
                nnppBallot.add(ballot);
            }
            if (ballot.getPoliticalParty() == PoliticalParty.APGA) {
                apgaBallot.add(ballot);
            }
        }
        partyVotes[0] = apcBallot.size();
        partyVotes[1] = pdpBallot.size();
        partyVotes[2] = lpBallot.size();
        partyVotes[3] = nnppBallot.size();
        partyVotes[4] = apgaBallot.size();
        return partyVotes;
        }


        public int declareTheWinner(BallotBox ballotBox){
            int [] partyVotes= countVotes(ballotBox);
                    int highest = partyVotes[0];
                for(int i = 1; i < partyVotes.length; i++){
                    if(partyVotes[i] > highest){
                        highest = partyVotes[i];
                    }
                }

               return highest;
        }


        private void validateBallotNumber(BallotBox ballotBox, int BallotNumber){
                if(BallotNumber < ballotBox.getBallots().size() ||BallotNumber > ballotBox.getBallots().size()){
                    throw new IllegalArgumentException("Ballot number does not exists");
                }
        }

    public List<Election> getElections() {
        return elections;
    }

    public void setElections(List<Election> elections) {
        this.elections = elections;
    }


}
