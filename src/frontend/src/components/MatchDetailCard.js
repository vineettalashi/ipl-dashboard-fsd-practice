import { React } from 'react';
import { Link } from 'react-router-dom';
import '../scss/MatchDetailCard.scss'

export const MatchDetailCard = ({teamName , match}) => {
  if(!match) return null;
  const otherteam = match.team1 === teamName ? match.team2 : match.team1;
  const otherTeamRoute = `/teams/${otherteam}`;
  const isWinner = teamName === match.winner
  return (
    <div className={isWinner ? "MatchDetailCard win-color" : "MatchDetailCard lost-color" }>
    <div>
        <h1>
        <span className="vs"> vs </span>
        <Link to={otherTeamRoute}>{otherteam} </Link>
        </h1>
        <h3 className="match-date">Match Date: {match.date}</h3>
        <h3 className="match-name">Played At: {match.venue}</h3>
        <h3 className="match-winner">{match.winner} won by {match.resultMargin} {match.result}</h3>
    </div>
    <div className="additional-match-info">
        <h3>Bat First By</h3>
        <p>{match.team1}</p>
        <h3>Chased By</h3>
        <p>{match.team2}</p>
        <h3>Player Of the Match</h3>
        <p>{match.playerOfMatch}</p>
        <h3>Umpires</h3>
        <p>{match.umpire1} , {match.umpire2}</p>
    </div>
  </div>
  );
}