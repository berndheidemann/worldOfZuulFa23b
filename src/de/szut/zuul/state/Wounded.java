package de.szut.zuul.state;

import de.szut.zuul.model.Player;

public class Wounded implements HealthState{

    private static Wounded instance;

    public static Wounded getInstance() {
        if (instance==null) {
            // das hier ist die einzige Möglichkeit, ein Objekt vom Typ Healthy zu erzeugen
            instance=new Wounded();
        }
        return instance;
    }

    private Wounded() {
    }

    @Override
    public HealthState heal() {
        return Healthy.getInstance();
    }

    @Override
    public HealthState hurtWeak() {
        return Stunned.getInstance();
    }

    @Override
    public HealthState hurtStrong() {
        return Stunned.getInstance();
    }

    @Override
    public String toString() {
        return "Wounded";
    }
}
