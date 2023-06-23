package com.staffinc.demoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.staffinc.demoapi.models.Team;


public interface TeamRepository extends JpaRepository<Team, Long> {
}
