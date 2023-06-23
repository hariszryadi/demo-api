package com.staffinc.demoapi.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.staffinc.demoapi.models.Match;
import com.staffinc.demoapi.models.Standing;
import com.staffinc.demoapi.repository.MatchRepository;
import com.staffinc.demoapi.repository.StandingRepository;
import com.staffinc.demoapi.repository.TeamRepository;

@RestController
@RequestMapping("/api")
public class MatchController {
    
    @Autowired
    MatchRepository matchRepository;
    
    @Autowired
    TeamRepository teamRepository;

    @Autowired
    StandingRepository standingRepository;

    @GetMapping("/matches")
    public ResponseEntity<List<Match>> getAllMatch() {
        try {
            List<Match> match = matchRepository.findAll();
            
            if (match.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(match, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/matches/{id}")
    public ResponseEntity<Match> getMatchById(@PathVariable("id") Long id) {
        Optional<Match> matchData = matchRepository.findById(id);

        if (matchData.isPresent()) {
            return new ResponseEntity<>(matchData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/matches")
    public ResponseEntity<Match> createMatch(@RequestBody Match match) {
        try {
            List<Standing> homeTeamData = standingRepository.findByTeamId(match.getHome_team());
            List<Standing> awayTeamData = standingRepository.findByTeamId(match.getAway_team());

            List<Match> matchHomeData = matchRepository.findByHomeTeamAndSchedule(match.getHome_team(), match.getSchedule());
            List<Match> matchAwayData = matchRepository.findByAwayTeamAndSchedule(match.getAway_team(), match.getSchedule());

            if (homeTeamData.isEmpty() || awayTeamData.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            if (!matchHomeData.isEmpty() || !matchAwayData.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            Match _match = matchRepository.save(new Match(match.getHome_team(), match.getAway_team(), match.getSchedule(), match.getAway_team_goal(), match.getHome_team_goal()));
            
            Integer home_goal = _match.getHome_team_goal();
            Integer away_goal = _match.getAway_team_goal();
            Integer win = 0;
            Integer lost = 0;
            Integer draw = 0;
            Integer points = 0;

            Standing standingHome = homeTeamData.get(0);
            Standing standingAway = awayTeamData.get(0);

            if (home_goal > away_goal) {
                win = 1;
                lost = 1;
                draw = 0;
                points = 3;

                standingHome.setHome_goal(standingHome.getHome_goal() + home_goal);
                standingHome.setWin(standingHome.getWin() + win);
                standingHome.setNumber_of_match(standingHome.getNumber_of_match() + 1);
                standingHome.setPoints(standingHome.getPoints() + points);
                standingRepository.save(standingHome);

                standingAway.setAway_goal(standingAway.getAway_goal() + away_goal);
                standingAway.setLost(standingAway.getLost() + lost);
                standingAway.setNumber_of_match(standingAway.getNumber_of_match() + 1);
                standingRepository.save(standingAway);

            } else if (home_goal < away_goal) {
                win = 1;
                lost = 1;
                draw = 0;
                points = 3;

                standingHome.setHome_goal(standingHome.getHome_goal() + home_goal);
                standingHome.setLost(standingHome.getLost() + lost);
                standingHome.setNumber_of_match(standingHome.getNumber_of_match() + 1);
                standingRepository.save(standingHome);

                standingAway.setAway_goal(standingAway.getAway_goal() + away_goal);
                standingAway.setWin(standingAway.getWin() + win);
                standingAway.setNumber_of_match(standingAway.getNumber_of_match() + 1);
                standingAway.setPoints(standingAway.getPoints() + points);
                standingRepository.save(standingAway);

            } else if (home_goal == away_goal) {
                win = 0;
                lost = 0;
                draw = 1;
                points = 1;

                standingHome.setHome_goal(standingHome.getHome_goal() + home_goal);
                standingHome.setDraw(standingHome.getDraw() + draw);
                standingHome.setNumber_of_match(standingHome.getNumber_of_match() + 1);
                standingHome.setPoints(standingHome.getPoints() + points);
                standingRepository.save(standingHome);

                standingAway.setAway_goal(standingAway.getAway_goal() + away_goal);
                standingAway.setDraw(standingAway.getDraw() + draw);
                standingAway.setNumber_of_match(standingAway.getNumber_of_match() + 1);
                standingAway.setPoints(standingAway.getPoints() + points);
                standingRepository.save(standingAway);
            }

            return new ResponseEntity<>(_match, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
