package com.staffinc.demoapi.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.staffinc.demoapi.models.Match;

public interface MatchRepository extends JpaRepository<Match, Long> {
    List<Match> findByHomeTeamAndSchedule(Integer homeTeam, Date schedule);
    List<Match> findByAwayTeamAndSchedule(Integer awayTeam, Date schedule);
}
