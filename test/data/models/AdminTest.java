package data.models;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class AdminTest {

    Admin admin;
    BallotBox ballotBox;
    Election election;
    @BeforeEach
    void setUp() {
        admin = new Admin("12345" , "Haliya", 20, true);
        ballotBox = new BallotBox();
        LocalDateTime startTime = LocalDateTime.now();
        LocalDateTime endTime = startTime.plusHours(24);
        election = admin.createElection(ballotBox, startTime, endTime);
        election.setActive(true);
    }


    @Test
    public void testThatAdminCanVote(){
        String result = admin.castVote(election, admin, PoliticalParty.APC, ballotBox);
        assertEquals("Vote Added Successfully", result);
    }

    @Test
    public void testThatAdminCanStopElection(){
        LocalDateTime startTime = LocalDateTime.now();
        LocalDateTime endTime = startTime.plusHours(24);
        Election election = admin.createElection(ballotBox, startTime, endTime);
        LocalDateTime stopTime = LocalDateTime.now();
        String result = admin.stopElection(election, stopTime);
        assertEquals("Election has been cancelled", result);
    }

    @Test
    public void testThatAdminCanDeleteBallot(){
        admin.castVote(election, admin, PoliticalParty.APC, ballotBox);
        admin.deleteBallot(ballotBox, 1);
        assertEquals(0, ballotBox.getBallots().size());
    }






}