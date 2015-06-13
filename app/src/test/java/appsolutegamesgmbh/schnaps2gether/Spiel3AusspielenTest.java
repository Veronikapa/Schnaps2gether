package appsolutegamesgmbh.schnaps2gether;

import org.junit.Before;
import org.junit.Test;

import appsolutegamesgmbh.schnaps2gether.DataStructure.Bummerl3;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Spiel3;
import appsolutegamesgmbh.schnaps2gether.DataStructure.WrongGameException;

import static org.junit.Assert.assertEquals;

/**
 * Created by n15r2 on 12.05.15.
 */
public class Spiel3AusspielenTest {
    public Bummerl3 bummerl = new Bummerl3();
    @Before
    public void setUp() {

    }

    @Test
    public void S1_spieltKarteAus() throws WrongGameException {
        Spiel3 spiel = new Spiel3(0);
        spiel.Auspielen(spiel.getS1().Hand.get(0), spiel.getS1());
        assertEquals("Spieler1 hat Karte ausgespielt", 2, spiel.getS1().Hand.size());
    }

    @Test
    public void S2_spieltKarteAus() throws WrongGameException {
        Spiel3 spiel = new Spiel3(0);
        spiel.Auspielen(spiel.getS2().Hand.get(0), spiel.getS2());
        assertEquals("Spieler2 hat Karte ausgespielt", 2, spiel.getS2().Hand.size());
    }

    @Test
    public void S3_spieltKarteAus() throws WrongGameException {
        Spiel3 spiel = new Spiel3(0);
        spiel.Auspielen(spiel.getS3().Hand.get(0), spiel.getS3());
        assertEquals("Spieler2 hat Karte ausgespielt", 2, spiel.getS3().Hand.size());
    }
}
