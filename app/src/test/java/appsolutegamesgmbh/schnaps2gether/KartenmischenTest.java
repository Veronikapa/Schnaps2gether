package appsolutegamesgmbh.schnaps2gether;

import org.junit.Before;
import org.junit.Test;

import appsolutegamesgmbh.schnaps2gether.DataStructure.Bummerl2;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Spiel2;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Karte;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

/**
 * Created by n15r2 on 12.05.15.
 */
public class KartenmischenTest {
    public Bummerl2 bummerl = new Bummerl2();
    Karte herzbube = new Karte("Herz", "Bube", 2);
    Karte herzdame = new Karte("Herz", "Dame", 3);
    Karte herzkoenig = new Karte("Herz", "König", 4);
    @Before
    public void setUp() {

    }

    @Test
    public void KartenWurdenGemischt() {
        Spiel2 spiel = new Spiel2(0);
        int karte1 = 0, karte2 = 0, karte3 = 0;
        boolean gemischt = true;

        if(herzbube.getFarbe() == spiel.getS1().Hand.get(0).getFarbe() && herzbube.getWertigkeit() == spiel.getS1().Hand.get(0).getWertigkeit()) {
            karte1 = 1;
        }
        if(herzdame.getFarbe() == spiel.getS1().Hand.get(1).getFarbe() && herzdame.getWertigkeit() == spiel.getS1().Hand.get(1).getWertigkeit()) {
            karte2 = 1;
        }
        if(herzkoenig.getFarbe() == spiel.getS1().Hand.get(2).getFarbe() && herzkoenig.getWertigkeit() == spiel.getS1().Hand.get(2).getWertigkeit()) {
            karte3 = 1;
        }
        if(karte1 == 1 && karte2 == 1 && karte3 == 1) {
            gemischt = false;
        }

        assertEquals("Karten sind gemischt", true, gemischt);
    }
}
