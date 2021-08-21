package io.fsdev.ipldashboard.Repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import io.fsdev.ipldashboard.data.Team;

public interface TeamRepository extends CrudRepository<Team,Long>{

    public Optional<Team> getAllDetailsByTeamName(String teamName);
}
