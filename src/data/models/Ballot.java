package data.models;

public class Ballot {
    private int ballotNumber;
    private User voter;
    private PoliticalParty politicalParty;

    public Ballot(User voter, PoliticalParty politicalParty) {
        this.ballotNumber = ballotNumber + 1;
        this.voter = voter;
        this.politicalParty = politicalParty;
    }

    public int getBallotNumber() {
        return ballotNumber;
    }

    public User getVoter() {
        return voter;
    }

    public PoliticalParty getPoliticalParty() {
        return politicalParty;
    }
}
