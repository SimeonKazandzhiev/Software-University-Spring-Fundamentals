package com.example.battleships.repository;

import com.example.battleships.model.entity.Ship;
import com.example.battleships.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShipRepository extends JpaRepository<Ship, Long> {
    Ship findByName(String ship1);

    @Query("select  s from Ship s order by s.health , s.power, s.id")
    List<Ship> findAll();
    List<Ship> findAllByUser(User user);

    List<Ship> findAllByUserNot(User user);


}
