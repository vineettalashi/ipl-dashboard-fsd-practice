package io.fsdev.ipldashboard.batch.config;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import io.fsdev.ipldashboard.data.Team;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

    private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

    private final JdbcTemplate jdbcTemplate;

    private EntityManager em;

    @Autowired
    public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate, EntityManager em) {
        this.jdbcTemplate = jdbcTemplate;
        this.em = em;
    }

    @Override
    @Transactional
    public void afterJob(JobExecution jobExecution) {
        Map<String,Team> teamData = new HashMap<>();
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("!!! JOB FINISHED! Time to verify the results");
            jdbcTemplate
                    .query("SELECT team1, team2 , winner FROM MATCH",
                    (rs, row) -> "team1 "+ rs.getString(1) + " team2 "+ rs.getString(2) + " winner " + rs.getString(3))
                    .forEach(str -> { System.out.println(str);} );

                    em.createQuery("Select m.team1 , count(*) from Match m group by m.team1",Object[].class)
                    .getResultList()
                    .stream()
                    .map(objArr -> new Team((String)objArr[0], (long)objArr[1]))
                    .forEach(team -> teamData.put(team.getTeamName(), team));
                    
                    em.createQuery("Select m.team2 , count(*) from Match m group by m.team2",Object[].class)
                    .getResultList()
                    .stream()
                    .forEach(objArr -> 
                        {
                            Team team = teamData.get((String)objArr[0]);
                            team.setTotalMatchesPlayed(team.getTotalMatchesPlayed() + (long)objArr[1]);
                        }
                    );

                    em.createQuery("Select m.winner , count(*) from Match m group by m.winner",Object[].class)
                    .getResultList()
                    .stream()
                    .forEach(objArr -> 
                        { 
                            if(!objArr[0].equals("NA")){
                                Team team = teamData.get((String)objArr[0]);
                                team.setTotalWins((long)objArr[1]);
                            }
                        }
                    );

                    teamData.values().stream().forEach(team -> em.persist(team));
                    teamData.values().stream().forEach(team -> System.out.println(team));
        }
    }
}
