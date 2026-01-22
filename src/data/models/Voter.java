package data.models;

public class Voter extends User {
    private int voterId;

    public Voter(String username, int age, boolean isAdmin) {
        super(username, age, isAdmin);
        voterId = voterId + 1;
    }

    public int getVoterId() {
        return voterId;
    }


}
