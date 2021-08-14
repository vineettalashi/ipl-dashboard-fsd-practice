package io.fsdev.ipldashboard.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import io.fsdev.ipldashboard.Exceptions.NoTeamFoundException;
import io.fsdev.ipldashboard.Repository.MatchRepository;
import io.fsdev.ipldashboard.Repository.TeamRepository;
import io.fsdev.ipldashboard.data.Team;
import io.fsdev.ipldashboard.service.TeamService;

@RestController
@CrossOrigin
public class TeamController {

    Logger logger = LoggerFactory.getLogger(TeamController.class);

    private TeamService teamService;

    public TeamController() {
    }

    @Autowired
    public TeamController(TeamService teamService){
        this.teamService=teamService;
    }
    

    @GetMapping("/teams/{teamName}")
    public Team getTeam(@PathVariable String teamName){
        return teamService.getTeamDetails(teamName).orElseThrow(() -> new NoTeamFoundException(teamName));
        
    }
    
}
