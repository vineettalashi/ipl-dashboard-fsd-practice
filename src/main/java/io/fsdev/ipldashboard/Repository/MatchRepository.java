package io.fsdev.ipldashboard.Repository;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import io.fsdev.ipldashboard.batch.Match;

public interface MatchRepository extends CrudRepository<Match,Long> {
    
    public List<Match> getMatchesByTeam1OrTeam2OrderByDateDesc(String team1, String team2, Pageable pageable);

    default public List<Match> getLatestNMatchesPlayedByTeam(String teamName, int n){
        Pageable pageable = PageRequest.of(0, n);
        return this.getMatchesByTeam1OrTeam2OrderByDateDesc(teamName, teamName, pageable);

    }

}
