package com.example.battleships.service;

import com.example.battleships.model.service.ShipServiceModel;
import com.example.battleships.model.view.ShipViewModel;

import java.util.List;

public interface ShipService {
    boolean addShip(ShipServiceModel shipServiceModel);

    List<ShipViewModel> findAllShips();

    List<ShipViewModel> findByUser(Long id);

    List<ShipViewModel> findByUserNot(Long id);

    void fire(String ship1, String ship2);
}

