import { React } from 'react';
import { Link } from 'react-router-dom';

export const MatchDetailCard = ({teamName , match}) => {
  if(!match) return null;
  const otherteam = match.team1 === teamName ? match.team2 : match.team1;
  const otherTeamRoute = `/teams/${otherteam}`;
  return (
    <div className="MatchDetailCard">
        <h3>Latest Matches</h3>
        <h1>vs 
          <Link to={otherTeamRoute}>{otherteam} </Link>
          </h1>
        <h2>{match.date}</h2>
        <h3>{match.venue}</h3>
        <h3>{match.winner} won by {match.resultMargin} {match.result}</h3>
    </div>
  );
}