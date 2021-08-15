import { React , useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import {MatchDetailCard} from '../components/MatchDetailCard';
export const MatchPage = () => {

    const [matches,setMatches] = useState([]);
    const {teamName,year} = useParams();
    useEffect(
      () => {
        const fetchAllmatchesYearWise = async() => {
        const response = await fetch(`http://localhost:8081/teams/${teamName}/matches?year=${year}`);
        const matchesPlayedByTeam = await response.json();
        setMatches(matchesPlayedByTeam);
      }
      fetchAllmatchesYearWise();
    },[]
    );

    return (
    <div className="MatchPage">
        <h1>Match Page</h1>
        {
          matches.map(match=><MatchDetailCard teamName={teamName} match={match}/>)
      }
    </div>
  );
}