package data.models;

import exceptions.invalidPassword;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Admin extends User {
    private String password = "12345";

    public Admin(String password, String username, int age, boolean isAdmin) {
        super(username, age, isAdmin);
        verifyPassword(password);
    }

    public Election createElection(BallotBox ballotBox, LocalDateTime startTime, LocalDateTime endTime) {
        return new Election(ballotBox, startTime, endTime);
    }

    public String stopElection(Election election, LocalDateTime endTime) {
        election.setActive(false);
        return "Election has been cancelled";
    }

    public String deleteBallot(BallotBox ballotBox, int ballotNumber) {
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
}
