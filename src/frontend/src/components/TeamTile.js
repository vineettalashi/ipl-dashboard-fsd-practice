import {React} from 'react';
import '../scss/TeamTile.scss'
import { NavLink } from 'react-router-dom';
export const TeamTile = ({teamName}) => {

    return (
        <div className='TeamTile'>
            <h1>
             <NavLink to={`/teams/${teamName}`} >
                {teamName}
             </NavLink>
            </h1>
        </div>

    );

}