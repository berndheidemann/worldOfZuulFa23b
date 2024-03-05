package de.szut.zuul.model.items;

import de.szut.zuul.model.Player;
import de.szut.zuul.model.items.EatableItem;

public class HealingHerb extends EatableItem {

    public HealingHerb(String name, String description, double weight) {
        super(name, description, weight);
    }

    @Override
    public void eat(Player player) {
        player.heal();
    }
}
