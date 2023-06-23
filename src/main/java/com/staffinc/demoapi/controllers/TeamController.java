package com.staffinc.demoapi.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.staffinc.demoapi.models.Standing;
import com.staffinc.demoapi.models.Team;
import com.staffinc.demoapi.repository.StandingRepository;
import com.staffinc.demoapi.repository.TeamRepository;

@RestController
@RequestMapping("/api")
public class TeamController {
    
    @Autowired
    TeamRepository teamRepository;

    @Autowired
    StandingRepository standingRepository;

    @GetMapping("/teams")
    public ResponseEntity<List<Team>> getAllTeams() {
        try {
            List<Team> team = teamRepository.findAll();

            if (team.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            
            return new ResponseEntity<>(team, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/teams/{id}")
    public ResponseEntity<Team> getTeamById(@PathVariable("id") Long id) {
        Optional<Team> teamData = teamRepository.findById(id);

        if (teamData.isPresent()) {
            return new ResponseEntity<>(teamData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/teams")
    public ResponseEntity<Team> createTeam(@RequestBody Team team) {
        try {
            Team _team = teamRepository.save(new Team(team.getName(), team.getCity()));
            // Long team_id = _team.getId();
            
            // save standing
            standingRepository.save(new Standing(_team, 0, 0, 0, 0, 0, 0, 0));

            return new ResponseEntity<>(_team, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/teams/{id}")
    public ResponseEntity<Team> updateTeam(@PathVariable Long id, @RequestBody Team team) {
        Optional<Team> teamData = teamRepository.findById(id);

        if (teamData.isPresent()) {
            Team _team = teamData.get();
            _team.setName(team.getName());
            _team.setCity(team.getCity());

            return new ResponseEntity<>(teamRepository.save(_team), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/teams/{id}")
    public ResponseEntity<HttpStatus> deleteTeam(@PathVariable("id") Long id) {
        try {
            teamRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
