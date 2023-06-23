package com.staffinc.demoapi.models;

import java.io.Serializable;
import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="matches")
public class Match implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer homeTeam;

    private Integer awayTeam;

    private Date schedule;

    private Integer home_team_goal;

    private Integer away_team_goal;

    public Match() {
    }

    public Match(Integer home_team, Integer away_team, Date schedule, Integer home_team_goal,
            Integer away_team_goal) {
        this.homeTeam = home_team;
        this.awayTeam = away_team;
        this.schedule = schedule;
        this.home_team_goal = home_team_goal;
        this.away_team_goal = away_team_goal;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getHome_team() {
        return homeTeam;
    }

    public void setHome_team(Integer home_team) {
        this.homeTeam = home_team;
    }

    public Integer getAway_team() {
        return awayTeam;
    }

    public void setAway_team(Integer away_team) {
        this.awayTeam = away_team;
    }

    public Date getSchedule() {
        return schedule;
    }

    public void setSchedule(Date schedule) {
        this.schedule = schedule;
    }

    public Integer getHome_team_goal() {
        return home_team_goal;
    }

    public void setHome_team_goal(Integer home_team_goal) {
        this.home_team_goal = home_team_goal;
    }

    public Integer getAway_team_goal() {
        return away_team_goal;
    }

    public void setAway_team_goal(Integer away_team_goal) {
        this.away_team_goal = away_team_goal;
    }
}
