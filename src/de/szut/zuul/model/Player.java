package de.szut.zuul.model;

import de.szut.zuul.exception.ItemNotFoundException;
import de.szut.zuul.exception.ItemTooHeavyException;
import de.szut.zuul.model.Item;
import de.szut.zuul.model.Room;

import java.util.LinkedList;
import java.util.Optional;
import java.util.StringJoiner;

public class Player {

    private Room currentRoom;
    private double loadCapacity;
    private LinkedList<Item> items;

    public Player() {
        this.loadCapacity=10.0;
        this.items=new LinkedList<>();
    }

    public void takeItem(Item item) throws ItemTooHeavyException {
        if(!isTakePossible(item)) {
            throw new ItemTooHeavyException("Item too heavy");
        } else {
            this.items.add(item);
        }
    }
    private Optional<Item> getItemByName(String name) {
        Item found=null;
        for(Item item: this.items) {
            if (item.getName().equals(name)) {
                found=item;
            }
        }
        return Optional.ofNullable(found);
    }
    public Item dropItem(String name) throws ItemNotFoundException {
        Item item=getItemByName(name).orElseThrow(()->new ItemNotFoundException("You don‘t own this item!" ));
        this.items.remove(item);
        return item;
    }
    private boolean isTakePossible(Item item) {
        return getCurrentWeight() + item.getWeight() <= this.loadCapacity;
    }

    private double getCurrentWeight() {
        double currentWeight=0;
        for(Item it: this.items) {
            currentWeight+=it.getWeight();
        }
        return currentWeight;
    }

    public void goTo(Room newRoom) {
        this.currentRoom=newRoom;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public String showStatus() {
        StringJoiner joiner=new StringJoiner("\n");
        joiner.add("Status of the player");
        joiner.add("loadCapacity: " + (this.loadCapacity-getCurrentWeight()) + " kg");
        if (!items.isEmpty()) {
            joiner.add("taken items:");
            for(Item item: this.items) {
                joiner.add("   " + item.toString());
            }
        }
        return joiner.toString();
    }
}
