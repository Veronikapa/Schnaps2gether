package appsolutegamesgmbh.schnaps2gether;

import org.junit.Before;
import org.junit.Test;

import appsolutegamesgmbh.schnaps2gether.DataStructure.Bummerl2;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Spiel2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by n15r2 on 12.05.15.
 */
public class Spiel2AusspielenTest {
    public Bummerl2 bummerl = new Bummerl2();
    @Before
    public void setUp() {

    }

    @Test
    public void Spieler1spieltKarteAus() {
        Spiel2 spiel = new Spiel2(0);
        spiel.Auspielen(spiel.getS1().Hand.get(0), spiel.getS1());
        assertEquals("Spieler1 hat Karte ausgespielt", 4, spiel.getS1().Hand.size());
    }

    @Test
    public void Spieler2spieltKarteAus() {
        Spiel2 spiel = new Spiel2(0);
        spiel.Auspielen(spiel.getS2().Hand.get(0), spiel.getS2());
        assertEquals("Spieler2 hat Karte ausgespielt", 4, spiel.getS2().Hand.size());
    }
}
