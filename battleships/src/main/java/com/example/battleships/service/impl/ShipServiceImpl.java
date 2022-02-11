package com.example.battleships.service.impl;

import com.example.battleships.model.entity.Category;
import com.example.battleships.model.entity.Ship;
import com.example.battleships.model.entity.User;
import com.example.battleships.model.service.ShipServiceModel;
import com.example.battleships.model.view.ShipViewModel;
import com.example.battleships.repository.ShipRepository;
import com.example.battleships.security.CurrentUser;
import com.example.battleships.service.CategoryService;
import com.example.battleships.service.ShipService;
import com.example.battleships.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShipServiceImpl implements ShipService {

    private final ShipRepository shipRepository;
    private final ModelMapper modelMapper;
    private final CategoryService categoryService;
    private final CurrentUser currentUser;
    private final UserService userService;

    public ShipServiceImpl(ShipRepository shipRepository, ModelMapper modelMapper, CategoryService categoryService, CurrentUser currentUser, UserService userService) {
        this.shipRepository = shipRepository;
        this.modelMapper = modelMapper;
        this.categoryService = categoryService;
        this.currentUser = currentUser;
        this.userService = userService;
    }

    @Override
    public boolean addShip(ShipServiceModel shipServiceModel) {
        Ship ship = modelMapper.map(shipServiceModel, Ship.class);
        Category category = categoryService.findBuCategoryEnumName(shipServiceModel.getCategory()).orElse(null);
        User user = userService.findById(currentUser.getId());

        if (user != null && category != null) {
            ship.setCategory(category);
            ship.setUser(user);
        }

        try {
            shipRepository.save(ship);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public List<ShipViewModel> findAllShips() {
        List<Ship> ships = shipRepository.findAll();
        return ships.stream().map(shipEntity -> modelMapper.map(shipEntity, ShipViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ShipViewModel> findByUser(Long id) {
        User user = userService.findById(id);

        List<Ship> ships = shipRepository.findAllByUser(user);

        return ships.stream().map(shipEntity -> modelMapper.map(shipEntity, ShipViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ShipViewModel> findByUserNot(Long id) {
        User user = userService.findById(id);

        List<Ship> ships = shipRepository.findAllByUserNot(user);

        return ships.stream().map(ship -> modelMapper.map(ship, ShipViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void fire(String ship1, String ship2) {
        Ship attacker = shipRepository.findByName(ship1);
        Ship defender = shipRepository.findByName(ship2);

        if (attacker != null && defender != null) {
            Integer power = attacker.getPower();
            Integer health = defender.getHealth();
            if (health - power <= 0) {
                shipRepository.delete(defender);
            } else {
                defender.setHealth(health - power);
                shipRepository.save(defender);
            }

        }


    }
}
