package de.szut.zuul;

import java.util.HashMap;
import java.util.Optional;
import java.util.StringJoiner;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */
public class Room 
{
    public String description;

    /**
     * Im Falle vom marketplace
     *
     *  keys        |    values
     *  "north"          (Room tavern)
     *  "east"           (Room templePyramid)
     *  "west"           (Room sacrificialSite)
     */
    private HashMap<String, Room> exits;
    private HashMap<String, Item> items;


    public Room(String description) 
    {
        this.exits=new HashMap<>();
        this.items=new HashMap<>();
        this.description = description;
    }

    public Room putItem(Item newItem) {
        this.items.put(newItem.getName(), newItem);
        return this;
    }

    //              direction: "north"
    public Room getExit(String direction) {
        return exits.get(direction);
    }

    private String exitsToString() {
        StringJoiner joiner=new StringJoiner(" ");
        for (String key: exits.keySet()) {
            joiner.add(key);
        }
        return joiner.toString();
    }


    //                 "down"               (Room cave)
    public Room setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
        return this;
    }

    public String getLongDescription() {
        StringJoiner joiner=new StringJoiner("\n");
        joiner.add(this.description);
        joiner.add("Exits: " + this.exitsToString());
        joiner.add("Items in this room:");
        for(Item item: this.items.values()) {
            joiner.add("   " + item.toString());
        }
        return joiner.toString();
    }

    private Optional<Item> getItemByName(String name) {
        Item found=null;
        for(Item item: this.items.values()) {
            if (item.getName().equals(name)) {
                found=item;
            }
        }
        return Optional.ofNullable(found);
    }

    public Item removeItem(String name) {
        Item item=getItemByName(name).orElse(null);
        this.items.remove(item.getName());
        return item;
    }
}