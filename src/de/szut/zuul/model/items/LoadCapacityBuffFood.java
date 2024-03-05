package de.szut.zuul.model.items;

import de.szut.zuul.model.Player;

public class LoadCapacityBuffFood extends EatableItem{


    public LoadCapacityBuffFood(String name, String description, double weight) {
        super(name, description, weight);
    }

    @Override
    public void eat(Player player) {
        player.increaseLoadCapacity();
    }
}
