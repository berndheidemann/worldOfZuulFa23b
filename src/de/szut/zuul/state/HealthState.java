package de.szut.zuul.state;


/**
 * Ist quasi ein Vertrag, jede Klasse die dieses Interface
 * "einbaut" (implementiert) muss die Methoden, die hier
 * auftauchen, ausimplementieren
 */
public interface HealthState {

    // leere (abstrakte) Methode, d.h. der Inhalt der Methode fehlt
    HealthState heal();

    HealthState hurtWeak();

    HealthState hurtStrong();
}
