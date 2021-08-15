import { React } from 'react';
import { Link } from 'react-router-dom';
import '../scss/MatchSmallCard.scss'

export const MatchSmallCard = ({teamName, match}) => {
  if(!match) return null;
  const otherteam = match.team1 === teamName ? match.team2 : match.team1;
  const otherTeamRoute = `/teams/${otherteam}`;
  const isWinner = teamName === match.winner
  return (
    <div className={isWinner ? "MatchSmallCard win-color" : "MatchSmallCard lost-color" }>
        <span className="vs"> vs </span>
        <h1>
          <Link to={otherTeamRoute}>{otherteam} </Link>
        </h1>
        <p className="match-winner">{match.winner} won by {match.resultMargin} {match.result}</p>
    </div>
  );
}