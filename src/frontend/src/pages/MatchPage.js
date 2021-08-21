import { React , useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import {MatchDetailCard} from '../components/MatchDetailCard';
import {YearSelector} from '../components/YearSelector';
import '../scss/MatchPage.scss'
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
    },[teamName,year]
    );

    return (
    <div className="MatchPage">
        <div className='year-selector'  >
            <h3>Select Year</h3>
            <YearSelector teamName={teamName}/>
        </div> 
        <div>
          <h1 className='match-page-heading'>{teamName} matches in year {year}</h1>
        {
          matches.map(match=><MatchDetailCard key={match.id} teamName={teamName} match={match}/>)
        }
      </div>
    </div>
  );
}