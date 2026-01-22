package data.models;

import java.time.LocalDateTime;

public class Election {
    private String name;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private BallotBox ballotBox;
    private boolean isActive;

    public Election(String name, BallotBox ballotBox,  LocalDateTime startTime, LocalDateTime endTime) {
        this.ballotBox = ballotBox;
        this.startTime = startTime;
        this.endTime = endTime;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
