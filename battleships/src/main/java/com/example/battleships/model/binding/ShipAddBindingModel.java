package com.example.battleships.model.binding;

import com.example.battleships.model.enums.CategoryNameEnum;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;

public class ShipAddBindingModel {

    private String name;
    private Integer power;
    private Integer health;
    private LocalDate created;
    private CategoryNameEnum category;

    public ShipAddBindingModel() {
    }

    @NotBlank
    @Size(min = 2, max = 10)
    public String getName() {
        return name;
    }

    public ShipAddBindingModel setName(String name) {
        this.name = name;
        return this;
    }

    @NotNull
    @Positive
    public Integer getPower() {
        return power;
    }

    public ShipAddBindingModel setPower(Integer power) {
        this.power = power;
        return this;
    }
    @NotNull
    @Positive
    public Integer getHealth() {
        return health;
    }

    public ShipAddBindingModel setHealth(Integer health) {
        this.health = health;
        return this;
    }
    @PastOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public LocalDate getCreated() {
        return created;
    }

    public ShipAddBindingModel setCreated(LocalDate created) {
        this.created = created;
        return this;
    }

    @NotNull(message = "Select category you donkey!")
    public CategoryNameEnum getCategory() {
        return category;
    }

    public ShipAddBindingModel setCategory(CategoryNameEnum category) {
        this.category = category;
        return this;
    }
}
