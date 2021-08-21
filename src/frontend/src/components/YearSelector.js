import { React } from 'react'
import { NavLink } from 'react-router-dom';

import '../scss/YearSelector.scss'

export const YearSelector = ({teamName}) => {

    let years = [];
    const startYear = process.env.REACT_APP_IPL_START_YEAR
    const lastYear = process.env.REACT_APP_IPL_END_YEAR

    require('dotenv').config();

    for(let i=startYear;i<=lastYear;i++){
        years.push(i);
    }

    return (
        <ol className='YearSelector'>
            {
            years.map(year=> (
            <li key={year}>
                <NavLink activeClassName='is-active' to={`/teams/${teamName}/matches/${year}`}>{year}</NavLink>
            </li>
            ))
         }
        </ol>
    );
        



    



}