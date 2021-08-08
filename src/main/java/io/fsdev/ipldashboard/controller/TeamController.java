package io.fsdev.ipldashboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import io.fsdev.ipldashboard.Repository.MatchRepository;
import io.fsdev.ipldashboard.Repository.TeamRepository;
import io.fsdev.ipldashboard.data.Team;

@RestController
public class TeamController {

    private TeamRepository teamRepository;
    private MatchRepository matchRepository;
    

    public TeamController() {
    }

    @Autowired
    public TeamController(TeamRepository teamRepository, MatchRepository matchRepository) {
        this.teamRepository = teamRepository;
        this.matchRepository = matchRepository;
    }

    @GetMapping("/teams/{teamName}")
    public Team getTeam(@PathVariable String teamName){
        Team team = this.teamRepository.getAllDetailsByTeamName(teamName);
        team.setMatchesPlayed(this.matchRepository.getLatestNMatchesPlayedByTeam(teamName, 4));
        return team;
    }
    
}
