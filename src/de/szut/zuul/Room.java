package de.szut.zuul;

import java.util.HashMap;
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


    public Room(String description) 
    {
        this.exits=new HashMap<>();
        this.description = description;
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
    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }

    public void printRoomInformation() {
        System.out.println("You are " + getDescription());
        System.out.print("Exits: " + exitsToString());

        System.out.println();
    }
}