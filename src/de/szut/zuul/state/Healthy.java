package de.szut.zuul.state;

import de.szut.zuul.model.Player;

public class Healthy implements HealthState{

    private Player player;
    private static Healthy instance;

    public static Healthy getInstance() {
        if (instance==null) {
            // das hier ist die einzige Möglichkeit, ein Objekt vom Typ Healthy zu erzeugen
            instance=new Healthy();
        }
        return instance;
    }


    // privater Konstruktor: niemand kann dieses Objekt erzeugen
    private Healthy() {
    }


    @Override
    public HealthState heal() {
        return this;
    }

    @Override
    public HealthState hurtWeak() {
        return Wounded.getInstance();
    }

    @Override
    public HealthState hurtStrong() {
        return Stunned.getInstance();
    }

    @Override
    public String toString() {
        return "Healthy";
    }
}
