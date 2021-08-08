package io.fsdev.ipldashboard.batch;

import java.time.LocalDate;

import org.springframework.batch.item.ItemProcessor;

import io.fsdev.ipldashboard.data.MatchInput;

public class MatchDataProcessor implements ItemProcessor<MatchInput, Match> {

    @Override
    public Match process(MatchInput matchInput) {
        Match match = new Match();
        match.setId(Long.parseLong(matchInput.getId()));
        match.setCity(matchInput.getCity());
        match.setDate(LocalDate.parse(matchInput.getDate()));
        match.setPlayerOfMatch(matchInput.getPlayer_of_match());
        match.setVenue(matchInput.getVenue());

        //assuming team1 = who batted first ; team2 = who batted second
        String teamWhoBattedFirst="";
        String teamWhoBattedSecond="";
        /* if(matchInput.getToss_winner().equals(matchInput.getTeam1()) && matchInput.getToss_decision().equals("bat")){
            battingFirst = matchInput.getTeam1();
            battingSecond = matchInput.getTeam2();
        }
        else if(matchInput.getToss_winner().equals(matchInput.getTeam2()) && matchInput.getToss_decision().equals("field")){
            battingFirst = matchInput.getTeam2();
            battingSecond = matchInput.getTeam1();
        } */

        if(matchInput.getToss_decision().equals("bat")){
            teamWhoBattedFirst = matchInput.getToss_winner();
            teamWhoBattedSecond = matchInput.getToss_winner().equals(matchInput.getTeam1()) ? matchInput.getTeam2() : matchInput.getTeam1();
        }
        else{
            teamWhoBattedFirst = matchInput.getToss_winner().equals(matchInput.getTeam1()) ? matchInput.getTeam2() : matchInput.getTeam1();
            teamWhoBattedSecond = matchInput.getToss_winner();
        }

       
        match.setTeam1(teamWhoBattedFirst);
        match.setTeam2(teamWhoBattedSecond);
        match.setTossWinner(matchInput.getToss_winner());
        match.setTossDecision(matchInput.getToss_decision());
        match.setWinner(matchInput.getWinner());
        match.setResult(matchInput.getResult());
        match.setResultMargin(matchInput.getResult_margin());
        match.setUmpire1(matchInput.getUmpire1());
        match.setUmpire2(matchInput.getUmpire2());
        
        return match;
    }
}