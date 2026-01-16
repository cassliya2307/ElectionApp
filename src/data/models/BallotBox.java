package data.models;

import java.util.ArrayList;
import java.util.List;

public class BallotBox {
    private List<Ballot> ballots;

    public BallotBox(){
        this.ballots = new ArrayList<>();
    }

    public List<Ballot> getBallots() {
        return ballots;
    }
}
