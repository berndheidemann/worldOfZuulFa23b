package de.szut.zuul;

import java.util.StringJoiner;

/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class CommandWords
{
    // a constant array that holds all valid command words
    private static final String[] validCommands = {
        "go", "quit", "help", "look"
    };

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        // nothing to do at the moment...
    }

    /**
     * Check whether a given String is a valid command word. 
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        for(int i = 0; i < validCommands.length; i++) {
            if(validCommands[i].equals(aString))
                return true;
        }
        // if we get here, the string was not found in the commands
        return false;
    }

    public String showAll() {
        // Hilfsobjekt um Texte zu verknüpfen. Dabei kann man das Trennzeichen angeben
        StringJoiner joiner=new StringJoiner(" ");
        // validCommands ist ein Array mit Strings

        // Foreach-Schleife
        // for ( einzelnes Element: Container)
        for (String s: validCommands) {
            // für jedes Element des Containers wird diese Zeile einmal aufgerufen
            // das einzelne Element ist dann s
            joiner.add(s);
        }
        return joiner.toString();
    }
}
