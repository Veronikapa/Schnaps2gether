package appsolutegamesgmbh.schnaps2gether;

import org.junit.Before;
import org.junit.Test;

import appsolutegamesgmbh.schnaps2gether.DataStructure.Spieler;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Spiel2;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Bummerl2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by n15r2 on 12.05.15.
 */
public class Spiel2abwechselnSpielerTest {
    public Bummerl2 bummerl = new Bummerl2();
    @Before
    public void setUp() {

    }

    @Test
    public void abwechselnSpielerNachRunden() {
        Spiel2 spiel = new Spiel2(bummerl.getAnzahlSpiele()); //erste Runde
        assertTrue("Spieler1 ist dran", spiel.getS1().isIstdran());
        spiel = new Spiel2((bummerl.getAnzahlSpiele()+1)); //zweite Runde
        assertTrue("Spieler2 ist dran", spiel.getS2().isIstdran());
    }
}
