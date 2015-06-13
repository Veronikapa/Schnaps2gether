package appsolutegamesgmbh.schnaps2gether;

import org.junit.Before;
import org.junit.Test;

import appsolutegamesgmbh.schnaps2gether.DataStructure.Spieler;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Spiel2;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Bummerl2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by n15r2 on 12.06.15.
 */
public class Spiel2abwechselnSpielerTest {

    @Before
    public void setUp() {

    }

    @Test
    public void S1_istdran() {
        Spiel2 spiel = new Spiel2(0); //erste Runde
        assertTrue("Spieler1 ist dran", spiel.getS1().isIstdran());
        assertTrue("Spieler2 ist nicht dran", !spiel.getS2().isIstdran());
    }

    @Test
    public void S2_istdran() {
        Spiel2 spiel = new Spiel2(1); //zweite Runde
        assertTrue("Spieler1 ist nicht dran", !spiel.getS1().isIstdran());
        assertTrue("Spieler2 ist dran", spiel.getS2().isIstdran());
    }
}
