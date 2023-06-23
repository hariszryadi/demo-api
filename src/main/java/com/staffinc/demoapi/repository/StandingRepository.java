package com.staffinc.demoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.staffinc.demoapi.models.Standing;
import java.util.List;

public interface StandingRepository extends JpaRepository<Standing, Long> {
    List<Standing> findByTeamId(Integer teamId);
    
    @Query("select s, (s.home_goal - s.away_goal) as goal_diff FROM Standing s join s.team order by s.points desc, goal_diff desc, s.away_goal desc, s.number_of_match asc")
    List<Standing> getStandings();
}
