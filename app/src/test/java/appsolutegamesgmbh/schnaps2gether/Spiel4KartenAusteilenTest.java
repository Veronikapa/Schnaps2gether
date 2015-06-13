package appsolutegamesgmbh.schnaps2gether;

import org.junit.Before;
import org.junit.Test;

import appsolutegamesgmbh.schnaps2gether.DataStructure.Bummerl4;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Spiel4;
import appsolutegamesgmbh.schnaps2gether.DataStructure.WrongGameException;

import static org.junit.Assert.assertEquals;

/**
 * Created by n15r2 on 13.06.15.
 */
public class Spiel4KartenAusteilenTest {

    @Before
    public void setUp() {

    }

    @Test
    public void S1_besitzt5Handkarten() throws WrongGameException {
        Spiel4 spiel = new Spiel4(0);
        spiel.Trumpfansagen("Herz",0);
        assertEquals("Spieler1 5 Handkarten", 5, spiel.getS1().Hand.size());
    }

    @Test
    public void S2_besitzt5Handkarten() throws WrongGameException {
        Spiel4 spiel = new Spiel4(0);
        spiel.Trumpfansagen("Herz",0);
        assertEquals("Spieler2 5 Handkarten", 5, spiel.getS2().Hand.size());
    }

    @Test
    public void S3_besitzt5Handkarten() throws WrongGameException {
        Spiel4 spiel = new Spiel4(0);
        spiel.Trumpfansagen("Herz",0);
        assertEquals("Spieler3 5 Handkarten", 5, spiel.getS3().Hand.size());
    }

    @Test
    public void S4_besitzt5Handkarten() throws WrongGameException {
        Spiel4 spiel = new Spiel4(0);
        spiel.Trumpfansagen("Herz",0);
        assertEquals("Spieler4 5 Handkarten", 5, spiel.getS4().Hand.size());
    }
}
