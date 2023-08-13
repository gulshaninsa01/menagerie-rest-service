package com.menagerie.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.menagerie.entity.Event;

@Repository
public interface EventDao extends JpaRepository<Event, Integer>{

}
