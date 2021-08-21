import { React, useEffect , useState } from 'react';
import '../scss/HomePage.scss'
import { TeamTile } from '../components/TeamTile';

export const HomePage = () => {

  const [teams, setTeams] = useState([]);
  const [status, setStatus] = useState(0);

    useEffect( 
      () => {
        const fetchAllTeams = async() => {
        const response = await fetch(`http://localhost:8081/teams/`)
        const data = await response.json();
        setStatus(response.status);
        setTeams(data);
        }
        fetchAllTeams();
      },[]
    );

    if(!teams){
      return <h1>Teams not found. Error code : {status}.
      Error Description : Not Found </h1>
    }

  return (
    <div className="HomePage">
        
        <div className="header-section">
          <h1 className="app-name">IPL Dashboard</h1>
        </div>

        <div className='team-grid'>
            {teams.map(team=><TeamTile key={team.id} teamName={team.teamName}/>)}
        </div>

       
      </div>  
  );
}