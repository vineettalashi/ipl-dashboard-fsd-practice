package io.fsdev.ipldashboard.Repository;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import io.fsdev.ipldashboard.batch.Match;
import io.fsdev.ipldashboard.data.Team;

public interface MatchRepository extends CrudRepository<Match,Long> {
    
    public List<Match> getMatchesByTeam1OrTeam2OrderByDateDesc(String team1, String team2, Pageable pageable);

    /* public List<Match> getMatchesByTeam1AndDateBetweenOrTeam2AndDateBetweenOrderByDateDesc(
        String team1,LocalDate startDate1,LocalDate endDate1,
        String team2,LocalDate startDate2,LocalDate endDate2
    ); */

    @Query("Select m from Match m where (m.team1=:teamName or m.team2=:teamName) and m.date between :startDate and :endDate order by date desc")
    public List<Match> getMatchesByTeamNameAndDateBetween(
        @Param("teamName") String teamName,
        @Param("startDate") LocalDate startDate,
        @Param("endDate") LocalDate endDate
    );

    default public List<Match> getLatestNMatchesPlayedByTeam(String teamName, int n){
        Pageable pageable = PageRequest.of(0, n);
        return this.getMatchesByTeam1OrTeam2OrderByDateDesc(teamName, teamName, pageable);

    }

    @Query(nativeQuery = true,value = "Select (m.team1) from Match m UNION Select (m.team2) from Match m")
    public Set<String> findDistinctTeam1();

}
