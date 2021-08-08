package io.fsdev.ipldashboard.data;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import io.fsdev.ipldashboard.batch.Match;

@Entity
public class Team {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private long id;
    private String teamName;
    private long totalMatchesPlayed;
    private long totalWins;
    @Transient
    private List<Match> matchesPlayed;

    public Team() {
    }

    public Team(String teamName, long totalMatchesPlayed) {
        this.teamName = teamName;
        this.totalMatchesPlayed = totalMatchesPlayed;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getTeamName() {
        return teamName;
    }
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
    public long getTotalMatchesPlayed() {
        return totalMatchesPlayed;
    }
    public void setTotalMatchesPlayed(long totalMatchesPlayed) {
        this.totalMatchesPlayed = totalMatchesPlayed;
    }
    public long getTotalWins() {
        return totalWins;
    }
    public void setTotalWins(long totalWins) {
        this.totalWins = totalWins;
    }

    public List<Match> getMatchesPlayed() {
        return matchesPlayed;
    }

    public void setMatchesPlayed(List<Match> matchesPlayed) {
        this.matchesPlayed = matchesPlayed;
    }

    @Override
    public String toString() {
        return "Team [matchesPlayed=" + matchesPlayed + ", teamName=" + teamName + ", totalMatchesPlayed="
                + totalMatchesPlayed + ", totalWins=" + totalWins + "]";
    }

    

}