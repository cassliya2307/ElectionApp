package data.models;
import exceptions.electionIsNotActiveException;
import exceptions.invalidPassword;
import exceptions.tooYoungToVote;
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
        election = admin.createElection("name", ballotBox, startTime, endTime);
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
        Election election = admin.createElection("name", ballotBox, startTime, endTime);
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

    @Test
    public void testThatYouCantVoteIfAnElectionIsNotActive(){
        election.setActive(false);
        Voter voter = new Voter("Emmanuel" , 18, "08054418793", false);

        assertThrows(electionIsNotActiveException.class, () -> {voter.castVote(election, admin, PoliticalParty.PDP, ballotBox);});
    }

  @Test
    public void testThatYouCannotVoteIfYouAreNotOfAge(){
      assertThrows(tooYoungToVote.class, () -> {Voter voter = new Voter("Emmanuel" , 8, "08054418793", false);});
  }

  @Test
    public void testThatAdminInputsCorrectPassword(){
        assertThrows(invalidPassword.class, () ->{Admin admin = new Admin("45" , "Haliya", 20, true);});
  }

  @Test
    public void testThatAdminCannotRemoveABallotThatDoesNotExist(){
        assertThrows(IllegalArgumentException.class, () -> {admin.deleteBallot(ballotBox, 2);});
  }



}