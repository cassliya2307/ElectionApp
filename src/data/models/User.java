package data.models;


import exceptions.electionIsNotActiveException;
import exceptions.tooYoungToVote;

public class User {
    private String username;
    private int age;
    private boolean isAdmin = false;

    public User(String username, int age, boolean isAdmin) {
        this.username = username;
        validateAge(age);
        this.age = age;
        this.isAdmin = isAdmin;
    }


    public String castVote(Election election, User user, PoliticalParty politicalParty, BallotBox ballotBox) {
        validateVote(election);
        Ballot ballot = new Ballot(user, politicalParty);
        ballotBox.getBallots().add(ballot);
        return "Vote Added Successfully";
    }

    private void validateAge(int age) {
        if(age < 18){
            throw new tooYoungToVote("Age must be 18 and above");
        }
    }

    private void validateVote(Election election) {
        if(!election.isActive()){
            throw new electionIsNotActiveException("Election is not active");
        }
    }
}
