package io.fsdev.ipldashboard.Repository;

import org.springframework.data.repository.CrudRepository;

import io.fsdev.ipldashboard.data.Team;

public interface TeamRepository extends CrudRepository<Team,Long>{

    public Team getAllDetailsByTeamName(String teamName);    
}
