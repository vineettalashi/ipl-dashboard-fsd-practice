import { React, useEffect , useState } from 'react';
import { MatchDetailCard } from '../components/MatchDetail';
import { MatchSmallCard } from '../components/MatchSmallCard';
import { useParams } from 'react-router-dom';

export const TeamPage = () => {

  const [team, setTeam] = useState({matchesPlayed: []});
  const {teamName} = useParams();
  const [status, setStatus] = useState(0);
  const [statusText, setStatusText] = useState();

    useEffect( 
      () => {
        const fetchMatches = async() => {
        const response = await fetch(`http://localhost:8081/teams/${teamName}`)
        const data = await response.json();
        setStatus(response.status);
        setStatusText(response.statusText)
        setTeam(data);
        }
        fetchMatches();
      },[teamName]
    );

    if(!team || !team.teamName){
      return <h1>Team '{teamName}' not found. Error code : {status}.
      Error Description : {statusText}. </h1>
    }

  return (
    <div className="TeamPage">
        <h1>{team.teamName}</h1>
        <MatchDetailCard teamName = {team.teamName} match = {team.matchesPlayed[0]} />
        {team.matchesPlayed.slice(1).map(match =><MatchSmallCard teamName = {team.teamName} match={match}/>)}
    </div>
  );
}