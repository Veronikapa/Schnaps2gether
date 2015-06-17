package appsolutegamesgmbh.schnaps2gether;

import org.junit.Before;
import org.junit.Test;

import appsolutegamesgmbh.schnaps2gether.DataStructure.Rufspiel;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Spiel4;
import appsolutegamesgmbh.schnaps2gether.DataStructure.WrongGameException;

import static org.junit.Assert.assertTrue;

/**
 * Created by n15r2 on 12.06.15.
 */
public class Spiel4kannFleckenTest {

    @Before
    public void setUp() {

    }

    @Test
    public void Spielstart_S2_S4_kannflecken() throws WrongGameException {
        Spiel4 spiel = new Spiel4(2);
        spiel.SpielAnsagen(new Rufspiel("Land"), spiel.getS3());
        assertTrue("Spieler1 kann flecken", spiel.kannFlecken(spiel.getS1()));
        assertTrue("Spieler2 kann flecken", spiel.kannFlecken(spiel.getS2()));
        assertTrue("Spieler3 kann flecken", spiel.kannFlecken(spiel.getS3()));
        assertTrue("Spieler4 kann flecken", spiel.kannFlecken(spiel.getS4()));
    }

    @Test
    public void kannflecken() throws WrongGameException {
        Spiel4 spiel = new Spiel4(0);
        assertTrue("Spieler1 kann flecken", spiel.kannFlecken(spiel.getS1()));
        assertTrue("Spieler2 kann flecken", spiel.kannFlecken(spiel.getS2()));
        assertTrue("Spieler3 kann flecken", spiel.kannFlecken(spiel.getS3()));
        assertTrue("Spieler4 kann flecken", spiel.kannFlecken(spiel.getS4()));
        spiel.Flecken();
        assertTrue("Spieler1 kann flecken", spiel.kannFlecken(spiel.getS1()));
        assertTrue("Spieler2 kann nicht flecken", !spiel.kannFlecken(spiel.getS2()));
        assertTrue("Spieler3 kann flecken", spiel.kannFlecken(spiel.getS3()));
        assertTrue("Spieler4 kann nicht flecken", !spiel.kannFlecken(spiel.getS4()));
    }

    @Test
    public void kannflecken_gegenflecken() throws WrongGameException {
        Spiel4 spiel = new Spiel4(0);
        assertTrue("Spieler1 kann flecken", spiel.kannFlecken(spiel.getS1()));
        assertTrue("Spieler2 kann flecken", spiel.kannFlecken(spiel.getS2()));
        assertTrue("Spieler3 kann flecken", spiel.kannFlecken(spiel.getS3()));
        assertTrue("Spieler4 kann flecken", spiel.kannFlecken(spiel.getS4()));
        spiel.Flecken(); //Flecken
        assertTrue("Spieler1 kann flecken", spiel.kannFlecken(spiel.getS1()));
        assertTrue("Spieler2 kann nicht flecken", !spiel.kannFlecken(spiel.getS2()));
        assertTrue("Spieler3 kann flecken", spiel.kannFlecken(spiel.getS3()));
        assertTrue("Spieler4 kann nicht flecken", !spiel.kannFlecken(spiel.getS4()));
        spiel.Flecken(); // Gegenflecken
        assertTrue("Spieler1 kann nicht flecken", !spiel.kannFlecken(spiel.getS1()));
        assertTrue("Spieler2 kann nicht flecken", !spiel.kannFlecken(spiel.getS2()));
        assertTrue("Spieler3 kann nicht flecken", !spiel.kannFlecken(spiel.getS3()));
        assertTrue("Spieler4 kann nicht flecken", !spiel.kannFlecken(spiel.getS4()));
    }



}
