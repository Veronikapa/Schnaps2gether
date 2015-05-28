package appsolutegamesgmbh.schnaps2gether;

import org.junit.Before;
import org.junit.Test;

import appsolutegamesgmbh.schnaps2gether.DataStructure.Bummerl2;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Spiel2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by n15r2 on 12.05.15.
 */
public class Spiel2KartenAusteilenTest {
    public Bummerl2 bummerl = new Bummerl2();
    @Before
    public void setUp() {

    }

    @Test
    public void Spieler1besitzt5Handkarten() {
        Spiel2 spiel = new Spiel2(0);
        assertEquals("Spieler1 5 Handkarten", 5, spiel.getS1().Hand.size());
    }

    @Test
    public void Spieler2besitzt5Handkarten() {
        Spiel2 spiel = new Spiel2(0);
        assertEquals("Spieler2 5 Handkarten", 5, spiel.getS2().Hand.size());
    }

    @Test
    public void TrumpfkarteAufdecken() {
        Spiel2 spiel = new Spiel2(0);
        assertNotNull("Trumpf ist aufgedeckt", spiel.getAufgedeckterTrumpf());
    }
}
