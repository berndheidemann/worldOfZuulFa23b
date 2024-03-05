package de.szut.zuul.model.items;

import de.szut.zuul.model.Player;

public abstract class EatableItem extends Item {

    public EatableItem(String name, String description, double weight) {
        super(name, description, weight);
    }

    public abstract void eat(Player player);
}
