package de.szut.zuul.state;

import de.szut.zuul.model.Player;

public class Stunned implements HealthState{

    private static Stunned instance;

    public static Stunned getInstance() {
        if (instance==null) {
            // das hier ist die einzige Möglichkeit, ein Objekt vom Typ Healthy zu erzeugen
            instance=new Stunned();
        }
        return instance;
    }
    private Stunned() {
    }
    @Override
    public HealthState heal() {
        return Wounded.getInstance();
    }

    @Override
    public HealthState hurtWeak() {
        return this;
    }

    @Override
    public String toString() {
        return "Stunned";
    }

    @Override
    public HealthState hurtStrong() {
        return this;
    }
}
