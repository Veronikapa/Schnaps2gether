package appsolutegamesgmbh.schnaps2gether;

import org.junit.Before;
import org.junit.Test;

import appsolutegamesgmbh.schnaps2gether.DataStructure.Bummerl3;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Rufspiel;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Spiel3;
import appsolutegamesgmbh.schnaps2gether.DataStructure.WrongGameException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by n15r2 on 12.05.15.
 */
public class Spiel3KartenAusteilenTest {
    public Bummerl3 bummerl = new Bummerl3();
    @Before
    public void setUp() {

    }

    @Test
    public void S1_besitzt5Handkarten() throws WrongGameException {
        Spiel3 spiel = new Spiel3(0);
        assertEquals("Spieler1 3 Handkarten", 3, spiel.getS1().Hand.size());
        spiel.Trumpfansagen("Herz", 0);
        assertEquals("Spieler1 6 Handkarten", 6, spiel.getS1().Hand.size());
    }

    @Test
    public void S2_besitzt5Handkarten() throws WrongGameException {
        Spiel3 spiel = new Spiel3(0);
        assertEquals("Spieler2 3 Handkarten", 3, spiel.getS2().Hand.size());
        spiel.Trumpfansagen("Herz", 0);
        assertEquals("Spieler2 6 Handkarten", 6, spiel.getS2().Hand.size());
    }

    @Test
    public void S3_besitzt5Handkarten() throws WrongGameException {
        Spiel3 spiel = new Spiel3(0);
        assertEquals("Spieler3 3 Handkarten", 3, spiel.getS3().Hand.size());
        spiel.Trumpfansagen("Herz", 0);
        assertEquals("Spieler3 6 Handkarten", 6, spiel.getS3().Hand.size());
    }
}
