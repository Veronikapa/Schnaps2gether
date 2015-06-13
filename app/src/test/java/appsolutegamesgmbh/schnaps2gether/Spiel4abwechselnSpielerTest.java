package appsolutegamesgmbh.schnaps2gether;

import org.junit.Before;
import org.junit.Test;

import appsolutegamesgmbh.schnaps2gether.DataStructure.Bummerl4;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Spiel4;
import appsolutegamesgmbh.schnaps2gether.DataStructure.WrongGameException;

import static org.junit.Assert.assertTrue;

/**
 * Created by n15r2 on 12.06.15.
 */
public class Spiel4abwechselnSpielerTest {
    public Bummerl4 bummerl = new Bummerl4();
    @Before
    public void setUp() {

    }

    @Test
    public void S1_istdran() throws WrongGameException {
        Spiel4 spiel = new Spiel4(0); //erste Runde
        assertTrue("Spieler1 ist dran", spiel.getS1().isIstdran());
        assertTrue("Spieler2 ist nicht dran", !spiel.getS2().isIstdran());
        assertTrue("Spieler3 ist nicht dran", !spiel.getS3().isIstdran());
        assertTrue("Spieler4 ist nicht dran", !spiel.getS4().isIstdran());
    }

    @Test
    public void S2_istdran() throws WrongGameException {
        Spiel4 spiel = new Spiel4(1); //zweite Runde
        assertTrue("Spieler1 ist nicht dran", !spiel.getS1().isIstdran());
        assertTrue("Spieler2 ist dran", spiel.getS2().isIstdran());
        assertTrue("Spieler3 ist nicht dran", !spiel.getS3().isIstdran());
        assertTrue("Spieler4 ist nicht dran", !spiel.getS4().isIstdran());
    }

    @Test
    public void S3_istdran() throws WrongGameException {
        Spiel4 spiel = new Spiel4(2); //dritte Runde
        assertTrue("Spieler1 ist nicht dran", !spiel.getS1().isIstdran());
        assertTrue("Spieler2 ist nicht dran", !spiel.getS2().isIstdran());
        assertTrue("Spieler3 ist dran", spiel.getS3().isIstdran());
        assertTrue("Spieler4 ist nicht dran", !spiel.getS4().isIstdran());
    }

    @Test
    public void S4_istdran() throws WrongGameException {
        Spiel4 spiel = new Spiel4(3); //vierte Runde
        assertTrue("Spieler1 ist nicht dran", !spiel.getS1().isIstdran());
        assertTrue("Spieler2 ist nicht dran", !spiel.getS2().isIstdran());
        assertTrue("Spieler3 ist nicht dran", !spiel.getS3().isIstdran());
        assertTrue("Spieler4 ist dran", spiel.getS4().isIstdran());
    }
}
