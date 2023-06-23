package com.staffinc.demoapi.models;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="standings")
public class Standing implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    private Integer points;

    private Integer win;

    private Integer lost;

    private Integer draw;

    private Integer number_of_match;

    private Integer home_goal;

    private Integer away_goal;

    public Standing() {
    }

    public Standing(Team team, Integer points, Integer win, Integer lost, Integer draw, Integer number_of_match,
            Integer home_goal, Integer away_goal) {
        this.team = team;
        this.points = points;
        this.win = win;
        this.lost = lost;
        this.draw = draw;
        this.number_of_match = number_of_match;
        this.home_goal = home_goal;
        this.away_goal = away_goal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getWin() {
        return win;
    }

    public void setWin(Integer win) {
        this.win = win;
    }

    public Integer getLost() {
        return lost;
    }

    public void setLost(Integer lost) {
        this.lost = lost;
    }

    public Integer getDraw() {
        return draw;
    }

    public void setDraw(Integer draw) {
        this.draw = draw;
    }

    public Integer getNumber_of_match() {
        return number_of_match;
    }

    public void setNumber_of_match(Integer number_of_match) {
        this.number_of_match = number_of_match;
    }

    public Integer getHome_goal() {
        return home_goal;
    }

    public void setHome_goal(Integer home_goal) {
        this.home_goal = home_goal;
    }

    public Integer getAway_goal() {
        return away_goal;
    }

    public void setAway_goal(Integer away_goal) {
        this.away_goal = away_goal;
    }

    @Override
    public String toString() {
        return "Standing [id=" + id + ", team=" + team + ", points=" + points + ", win=" + win + ", lost=" + lost
                + ", draw=" + draw + ", number_of_match=" + number_of_match + ", home_goal=" + home_goal
                + ", away_goal=" + away_goal + "]";
    }
    
    
}
