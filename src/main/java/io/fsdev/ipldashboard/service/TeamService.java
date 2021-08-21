package io.fsdev.ipldashboard.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.fsdev.ipldashboard.Repository.MatchRepository;
import io.fsdev.ipldashboard.Repository.TeamRepository;
import io.fsdev.ipldashboard.batch.Match;
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

    public Iterable<Team> getAllTeamsThatHavePlayedIPL(){
        return  this.teamRepository.findAll();
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
    
    public List<Match> getAllMatchesPlayedByTeamYearwise(String teamName,int year){
        LocalDate startDate = LocalDate.of(year, 1, 1);
        LocalDate endDate = LocalDate.of(year+1, 1, 1);
        List<Match> listOfMatches = this.matchRepository.getMatchesByTeamNameAndDateBetween(teamName,startDate,endDate);
        return listOfMatches;
        
    }
}
