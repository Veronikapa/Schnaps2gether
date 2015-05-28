package appsolutegamesgmbh.schnaps2gether;

import org.junit.Before;
import org.junit.Test;

import appsolutegamesgmbh.schnaps2gether.DataStructure.Karte;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Spiel2;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Bummerl2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by n15r2 on 12.05.15.
 */
public class Spiel2BummerlTest {
    Karte herzbube = new Karte("Herz","Bube",2);
    Karte herzdame = new Karte("Herz","Dame",3);
    Karte herzass = new Karte("Herz","Ass",11);
    Karte karobube = new Karte("Karo","Bube",2);
    @Before
    public void setUp() {

    }

    @Test
    public void bummerlWirdRichtigGezaehlt() {
        Bummerl2 bummerl = new Bummerl2();
        Spiel2 spiel = new Spiel2(0);

        assertTrue("Runde ist nicht zu Ende", !spiel.istSpielzuEnde(bummerl));

        spiel.getS1().setPunkte(67);
        spiel.getS2().setPunkte(34);
        assertTrue("Runde ist zu Ende", spiel.istSpielzuEnde(bummerl));
        assertEquals("S1 hat 1 Punkte", 1, bummerl.getPunkteS1());
        assertEquals("S2 hat 0 Punkte", 0, bummerl.getPunkteS2());
        spiel.getS1().setPunkte(67);
        spiel.getS2().setPunkte(2);
        assertTrue("Runde ist zu Ende", spiel.istSpielzuEnde(bummerl));
        assertEquals("S1 hat 3 Punkte", 3, bummerl.getPunkteS1());
        assertEquals("S2 hat 0 Punkte", 0, bummerl.getPunkteS2());
        spiel.getS1().setPunkte(67);
        spiel.getS2().setPunkte(0);
        assertTrue("Runde ist zu Ende", spiel.istSpielzuEnde(bummerl));
        assertEquals("S1 hat 6 Punkte", 6, bummerl.getPunkteS1());
        assertEquals("S2 hat 0 Punkte", 0, bummerl.getPunkteS2());
        spiel.getS1().setPunkte(0);
        spiel.getS2().setPunkte(67);
        assertTrue("Runde ist zu Ende", spiel.istSpielzuEnde(bummerl));
        assertEquals("S1 hat 6 Punkte", 6, bummerl.getPunkteS1());
        assertEquals("S2 hat 3 Punkte", 3, bummerl.getPunkteS2());
        spiel.getS1().setPunkte(2);
        spiel.getS2().setPunkte(67);
        assertTrue("Runde ist zu Ende", spiel.istSpielzuEnde(bummerl));
        assertEquals("S1 hat 6 Punkte", 6, bummerl.getPunkteS1());
        assertEquals("S2 hat 5 Punkte", 5, bummerl.getPunkteS2());
        spiel.getS1().setPunkte(50);
        spiel.getS2().setPunkte(67);
        assertTrue("Runde ist zu Ende", spiel.istSpielzuEnde(bummerl));
        assertEquals("S1 hat 6 Punkte", 6, bummerl.getPunkteS1());
        assertEquals("S2 hat 6 Punkte", 6, bummerl.getPunkteS2());
        assertTrue("Spiel ist nicht zu Ende", !bummerl.istBummerlzuEnde());
        spiel.getS1().setPunkte(50);
        spiel.getS2().setPunkte(67);
        assertTrue("Runde ist zu Ende", spiel.istSpielzuEnde(bummerl));
        assertEquals("S1 hat 6 Punkte", 6, bummerl.getPunkteS1());
        assertEquals("S2 hat 7 Punkte", 7, bummerl.getPunkteS2());
        assertTrue("Spiel ist zu Ende", bummerl.istBummerlzuEnde());


    }


}
