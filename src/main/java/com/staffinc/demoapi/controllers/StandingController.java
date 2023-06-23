package com.staffinc.demoapi.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.staffinc.demoapi.models.Standing;
import com.staffinc.demoapi.repository.StandingRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api")
public class StandingController {
    
    @Autowired
    StandingRepository standingRepository;

    private Integer rank = new Integer(0);

    @GetMapping("/standings")
    public ResponseEntity<ArrayNode> getStanding() {
        try {
            List<Standing> standing = standingRepository.getStandings();
            ArrayNode arrayNode = JsonNodeFactory.instance.arrayNode();
            rank = new Integer( rank.intValue() + 1);
            standing.forEach(i -> {
                ObjectNode node = JsonNodeFactory.instance.objectNode();
                node.put("id", i.getTeam().getId());
                node.put("name", i.getTeam().getName());
                node.put("city", i.getTeam().getCity());
                node.put("rank", rank);
                node.put("points", i.getPoints());
                node.put("win", i.getWin());
                node.put("lost", i.getLost());
                node.put("draw", i.getDraw());
                node.put("number_of_match", i.getNumber_of_match());
                node.put("home_goal", i.getHome_goal());
                node.put("away_goal", i.getAway_goal());
                arrayNode.add(node);
                rank++;
            });

            return new ResponseEntity<>(arrayNode, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
