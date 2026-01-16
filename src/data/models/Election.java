package data.models;

import java.time.LocalDateTime;

public class Election {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private BallotBox ballotBox;
    private boolean isActive;

    public Election(BallotBox ballotBox,  LocalDateTime startTime, LocalDateTime endTime) {
        this.ballotBox = ballotBox;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public BallotBox getBallotBox() {
        return ballotBox;
    }

    public void setBallotBox(BallotBox ballotBox) {
        this.ballotBox = ballotBox;
    }


    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
