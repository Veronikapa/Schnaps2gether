package appsolutegamesgmbh.schnaps2gether;

import org.junit.Before;
import org.junit.Test;

import appsolutegamesgmbh.schnaps2gether.DataStructure.Bummerl3;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Spiel3;
import appsolutegamesgmbh.schnaps2gether.DataStructure.WrongGameException;

import static org.junit.Assert.assertTrue;

/**
 * Created by n15r2 on 12.05.15.
 */
public class Spiel3abwechselnSpielerTest {
    public Bummerl3 bummerl = new Bummerl3();
    @Before
    public void setUp() {

    }

    @Test
    public void S1_istdran() throws WrongGameException {
        Spiel3 spiel = new Spiel3(bummerl.getAnzahlSpiele()); //erste Runde
        assertTrue("Spieler1 ist dran", spiel.getS1().isIstdran());
        assertTrue("Spieler2 ist nicht dran", !spiel.getS2().isIstdran());
        assertTrue("Spieler3 ist nicht dran", !spiel.getS3().isIstdran());
    }

    @Test
    public void S2_istdran() throws WrongGameException {
        Spiel3 spiel = new Spiel3((bummerl.getAnzahlSpiele()+1)); //zweite Runde
        assertTrue("Spieler1 ist nicht dran", !spiel.getS1().isIstdran());
        assertTrue("Spieler2 ist dran", spiel.getS2().isIstdran());
        assertTrue("Spieler3 ist nicht dran", !spiel.getS3().isIstdran());
    }

    @Test
    public void S3_istdran() throws WrongGameException {;
        Spiel3 spiel = new Spiel3((bummerl.getAnzahlSpiele()+2)); //dritte Runde
        assertTrue("Spieler1 ist nicht dran", !spiel.getS1().isIstdran());
        assertTrue("Spieler2 ist nicht dran", !spiel.getS2().isIstdran());
        assertTrue("Spieler3 ist dran", spiel.getS3().isIstdran());;
    }


}
