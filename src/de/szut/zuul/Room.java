package de.szut.zuul;

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
    private Room northExit;
    private Room southExit;
    private Room eastExit;
    private Room westExit;
    private Room upExit;
    private Room downExit;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
    }


    public Room getExit(String direction) {
        //  IF Anweisungen um den passenden Raum zu finden
        if (direction.equals("north")) {
            return northExit;
        }
        // TODO....
        return null;
    }

    private String exitsToString() {
        // TODO
        StringJoiner joiner=new StringJoiner(" ");
        joiner.add("north");
        joiner.add("west");
        joiner.add("up");
        joiner.add("down");
        joiner.add("Heidemann");

        return joiner.toString();
    }

    public void setExits(Room north, Room east, Room south, Room west, Room up, Room down)
    {
        if(north != null) {
            northExit = north;
        }
        if(east != null) {
            eastExit = east;
        }
        if(south != null) {
            southExit = south;
        }
        if(west != null) {
            westExit = west;
        }
        if(up!=null) {
            this.upExit=up;
        }
        if(down!=null) {
            this.downExit=down;
        }
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