package appsolutegamesgmbh.schnaps2gether;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import appsolutegamesgmbh.schnaps2gether.DataStructure.Karte;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Spiel3;
import appsolutegamesgmbh.schnaps2gether.DataStructure.WrongGameException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by n15r2 on 12.05.15.
 */
public class Spiel3kannFleckenTest {

    @Before
    public void setUp() {

    }

    @Test
    public void Spielstart_S2_S3_kannflecken() throws WrongGameException {
        Spiel3 spiel = new Spiel3(0);
        assertTrue("Spieler1 kann nicht flecken", !spiel.kannFlecken(spiel.getS1()));
        assertTrue("Spieler2 kann flecken", spiel.kannFlecken(spiel.getS2()));
        assertTrue("Spieler3 kann flecken", spiel.kannFlecken(spiel.getS3()));
    }

    @Test
    public void kannflecken() throws WrongGameException {
        Spiel3 spiel = new Spiel3(0);
        assertTrue("Spieler1 kann nicht flecken", !spiel.kannFlecken(spiel.getS1()));
        assertTrue("Spieler2 kann flecken", spiel.kannFlecken(spiel.getS2()));
        assertTrue("Spieler3 kann flecken", spiel.kannFlecken(spiel.getS3()));
        spiel.Flecken();
        assertTrue("Spieler1 kann flecken", spiel.kannFlecken(spiel.getS1()));
        assertTrue("Spieler2 kann nicht flecken", !spiel.kannFlecken(spiel.getS2()));
        assertTrue("Spieler3 kann nicht flecken", !spiel.kannFlecken(spiel.getS3()));
    }

    @Test
    public void kannflecken_gegenflecken() throws WrongGameException {
        Spiel3 spiel = new Spiel3(0);
        assertTrue("Spieler1 kann nicht flecken", !spiel.kannFlecken(spiel.getS1()));
        assertTrue("Spieler2 kann flecken", spiel.kannFlecken(spiel.getS2()));
        assertTrue("Spieler3 kann flecken", spiel.kannFlecken(spiel.getS3()));
        spiel.Flecken(); //Flecken
        assertTrue("Spieler1 kann flecken", spiel.kannFlecken(spiel.getS1()));
        assertTrue("Spieler2 kann nicht flecken", !spiel.kannFlecken(spiel.getS2()));
        assertTrue("Spieler3 kann nicht flecken", !spiel.kannFlecken(spiel.getS3()));
        spiel.Flecken(); // Gegenflecken
        assertTrue("Spieler1 kann nicht flecken", !spiel.kannFlecken(spiel.getS1()));
        assertTrue("Spieler2 kann nicht flecken", !spiel.kannFlecken(spiel.getS2()));
        assertTrue("Spieler3 kann nicht flecken", !spiel.kannFlecken(spiel.getS3()));
    }



}
