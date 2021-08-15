import { React, useEffect , useState } from 'react';
import { MatchDetailCard } from '../components/MatchDetailCard';
import { MatchSmallCard } from '../components/MatchSmallCard';
import { useParams } from 'react-router-dom';
import { PieChart } from 'react-minimal-pie-chart';
import '../scss/TeamPage.scss'

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
        
        <div className="team-name-section">
          <h1 className="team-name">{team.teamName}</h1>
          <br/>
          <h1>Latest Matches</h1>
        </div>

        <div className="wins-loss-section">
          Wins vs Losses

          <PieChart
          data={[
            { title: 'Losses', value: team.totalMatchesPlayed-team.totalWins, color: '#a34d5d' },
            { title: 'Wins', value: team.totalWins, color: '#4da375' }
            
          ]}
        />
        
        </div>



        <div className="match-detail-section">
           <MatchDetailCard teamName = {team.teamName} match = {team.matchesPlayed[0]} />
        </div>
        
        {team.matchesPlayed.slice(1).map(match => <MatchSmallCard teamName = {team.teamName} match={match}/>)}
                
        <div className="more-links-section">
          <a href="#">More &gt;</a>
        </div>
      
      </div>  
  );
}