package de.szut.zuul.gamecontrol;

import de.szut.zuul.exception.ItemNotFoundException;
import de.szut.zuul.exception.ItemTooHeavyException;
import de.szut.zuul.model.*;
import de.szut.zuul.model.items.EatableItem;
import de.szut.zuul.model.items.HealingHerb;
import de.szut.zuul.model.items.Item;
import de.szut.zuul.model.items.LoadCapacityBuffFood;

/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class Game 
{
    private Parser parser;
    private Player player;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room marketsquare, templePyramid, tavern, sacrificialSite, hut, jungle, secretPassage, cave, beach, sorcererChamber, basement;
      
        // create the rooms
        marketsquare = new Room("on the market square");
        templePyramid = new Room("in a temple pyramid");
        tavern = new Room("in the tavern at the market square");
        sacrificialSite = new Room("at a sacrificial site");
        hut = new Room("in a hut");
        jungle = new Room("in the jungle");
        secretPassage = new Room("in a secret passage");
        cave = new Room("in a cave");
        beach = new Room("on the beach");
        sorcererChamber=new Room("in a magical Chamber");
        basement=new Room("in a dark basement");

        Item bow, treasure, arrows, plant, cocoa, knife, spear, food, jewelry;
        EatableItem muffin, herb;
        bow = new Item("bow", "a bow made out of wood", 0.5);
        treasure = new Item("treasure", "a small treasure with coins", 7.5);
        arrows = new Item("arrows", "a quiver with some kind of arrows", 1.0);
        plant = new Item("plant", "a healing plant", 0.5);
        cocoa = new Item("cocoa", "a small tree of cocoa", 5.0);
        knife = new Item("knife", "a very sharp and big knife", 1.0);
        spear = new Item("spear", "a spear with its slingshot", 5.0);
        food = new Item("food", "a plate with hearty meat and corn porridge", 0.5);
        jewelry =  new Item("jewelry", "a very pretty headdress", 1.0);
        muffin = new LoadCapacityBuffFood("muffin", "a magic muffin", 1.0);
        herb = new HealingHerb("herb", "a magic herb", 0.5);

        marketsquare
                .setExit("north", tavern)
                .setExit("east", templePyramid)
                .setExit("west", sacrificialSite)
                .putItem(bow)
                .putItem(muffin);

        templePyramid
                .setExit("north", hut)
                .setExit("west", marketsquare)
                .setExit("up", sorcererChamber)
                .setExit("down", basement);

        tavern
                .setExit("east", hut)
                .setExit("south", marketsquare)
                .putItem(food);

        sacrificialSite
                .setExit("east", marketsquare)
                .setExit("down", cave)
                .putItem(knife);

        hut
                .setExit("east", jungle)
                .setExit("south", templePyramid)
                .setExit("west", tavern)
                .putItem(spear);

        jungle
                .setExit("west", hut)
                .putItem(plant)
                .putItem(cocoa)
                .putItem(herb);

        secretPassage
                .setExit("east", basement)
                .setExit("west", cave);

        cave
                .setExit("east", secretPassage)
                .setExit("south", beach)
                .setExit("up", sacrificialSite)
                .putItem(treasure);

        beach
                .setExit("north", cave);

        basement
                .setExit("west", secretPassage)
                .setExit("up", templePyramid)
                .putItem(jewelry);

        sorcererChamber
                .setExit("down", templePyramid)
                .putItem(arrows);

        this.player=new Player();
        this.player.goTo(marketsquare); // start game on marketsquare
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        printRoomInformation();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        } else if(commandWord.equals("look")) {
            look();
         } else  if(commandWord.equals("take")) {
            takeItem(command);
            showStatus();

        } else if(commandWord.equals("drop")) {
            dropItem(command);
           showStatus();
        } else if(commandWord.equals("eat")) {
            eat(command);
            showStatus();
    }


        return wantToQuit;
    }

    private void showStatus() {
        System.out.println(this.player.showStatus());
        printRoomInformation();
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("through the jungle. At once there is a glade. On it there a buildings...");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println("   " + parser.showCommands());
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = null;
        nextRoom=this.player.getCurrentRoom().getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            this.player.goTo(nextRoom);
            printRoomInformation();
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }

    private void printRoomInformation() {
        System.out.println(this.player.getCurrentRoom().getLongDescription());
    }

    private void look() {
        printRoomInformation();
    }

    private void takeItem(Command command) {
        // take bow
        if (!command.hasSecondWord()) {
            System.out.println("Was willst du nehmen?");
            return;
        }
        Item itemToTake=null;
        try {
            // item aus dem Raum genommen
            itemToTake = this.player.getCurrentRoom().removeItem(command.getSecondWord());
            // Item dem Spieler zugewiesen
            this.player.takeItem(itemToTake);  // wenn hier ein Fehler auftritt, ist das Item aus dem Raum weg
        } catch(ItemNotFoundException itemNotFoundException) {
            System.out.println(itemNotFoundException.getMessage());
        } catch(ItemTooHeavyException itemTooHeavyException) {
            System.out.println(itemTooHeavyException.getMessage());
            this.player.getCurrentRoom().putItem(itemToTake);
        }
    }

    private void dropItem(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Was willst du ablegen?");
            return;
        }
        try {
            Item item = this.player.dropItem(command.getSecondWord());
            this.player.getCurrentRoom().putItem(item);
        } catch(ItemNotFoundException itemNotFoundException) {
            System.out.println(itemNotFoundException.getMessage());
        }
    }

    private void eat(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Was willst du essen?");
            return;
        }
        try {
            Item item = this.player.dropItem(command.getSecondWord());
            if(item instanceof EatableItem) {
                EatableItem eatableItem=(EatableItem)item;
                eatableItem.eat(this.player);
                System.out.println("lecker...");
            } else {
                System.out.println("Das kannst du nicht essen...");
                this.player.takeItem(item);
            }

        } catch(ItemNotFoundException | ItemTooHeavyException itemNotFoundException) {
            System.out.println(itemNotFoundException.getMessage());
        }

    }
}
