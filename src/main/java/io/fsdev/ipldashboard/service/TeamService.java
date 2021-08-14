package io.fsdev.ipldashboard.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import io.fsdev.ipldashboard.Repository.MatchRepository;
import io.fsdev.ipldashboard.Repository.TeamRepository;
import io.fsdev.ipldashboard.data.Team;

@Service
public class TeamService {
    Logger logger = LoggerFactory.getLogger(TeamService.class);

    private TeamRepository teamRepository;
    private MatchRepository matchRepository;
    
    @Autowired
    public TeamService(TeamRepository teamRepository, MatchRepository matchRepository) {
        this.teamRepository = teamRepository;
        this.matchRepository = matchRepository;
    }


    public Optional<Team> getTeamDetails(String teamName){
        Optional<Team> teamOptional = this.teamRepository.getAllDetailsByTeamName(teamName);
        if(!teamOptional.isPresent()){
            logger.error("Team {} not part of IPL",teamName);
            return Optional.empty();
        }

        Team team = teamOptional.get();
        team.setMatchesPlayed(this.matchRepository.getLatestNMatchesPlayedByTeam(teamName, 4));
        return Optional.of(team);
    }
    
}
