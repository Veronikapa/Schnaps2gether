package appsolutegamesgmbh.schnaps2gether;

import org.junit.Before;
import org.junit.Test;

import appsolutegamesgmbh.schnaps2gether.DataStructure.Bummerl2;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Karte;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Spiel2;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Spieler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import android.util.Log;

/**
 * Created by n15r2 on 12.05.15.
 */
public class Spiel2ZugAuswertenTest {
    Karte herzbube = new Karte("Herz","Bube",2);
    Karte herzdame = new Karte("Herz","Dame",3);
    Karte herzass = new Karte("Herz","Ass",11);
    Karte karobube = new Karte("Karo","Bube",2);
    @Before
    public void setUp() {

    }

    @Test
    public void zugWirdRichtigAusgewertet_SpielerIstDran_Punkte() {
        Spiel2 spiel = new Spiel2(0);
        spiel.getS1().Hand.removeAll(spiel.getS1().Hand);
        spiel.getS2().Hand.removeAll(spiel.getS2().Hand);

        assertEquals("Handkarten sind leer", 0, spiel.getS1().Hand.size());
        assertEquals("Handkarten sind leer", 0, spiel.getS2().Hand.size());
        spiel.getS1().Hand.add(herzass);
        spiel.getS1().Hand.add(herzdame);
        spiel.getS2().Hand.add(herzbube);
        spiel.getS2().Hand.add(karobube);

        spiel.ZugAuswerten(spiel.getS1().Hand.get(0), spiel.getS2().Hand.get(1));

        if (spiel.getTrumpf() == spiel.getS2().Hand.get(1).getFarbe()) {
            assertTrue("Spieler2 gewinnt und ist dran", spiel.getS2().isIstdran());
            assertEquals("Spieler2 hat 13 Punkte", 13, spiel.getS2().getPunkte());
            spiel.Ansagen20er("Herz", spiel.getS2());
        }
        if (spiel.getTrumpf() != spiel.getS2().Hand.get(1).getFarbe()) {
            assertTrue("Spieler1 gewinnt und ist dran", spiel.getS1().isIstdran());
            assertEquals("Spieler1 hat 13 Punkte", 13, spiel.getS1().getPunkte());
            spiel.Ansagen20er("Herz", spiel.getS1());
        }
    }

    @Test
    public void zugWirdRichtigAusgewertet_SpielerIstDran_Punkte20er_Ansagen_Punkte40er() { //fertig stellen
        Spiel2 spiel = new Spiel2(0);
        spiel.getS1().Hand.removeAll(spiel.getS1().Hand);
        spiel.getS2().Hand.removeAll(spiel.getS2().Hand);

        assertEquals("Handkarten sind leer", 0, spiel.getS1().Hand.size());
        assertEquals("Handkarten sind leer", 0, spiel.getS2().Hand.size());
        spiel.getS1().Hand.add(herzass);
        spiel.getS1().Hand.add(herzass);
        spiel.getS2().Hand.add(karobube);
        spiel.getS2().Hand.add(karobube);
        spiel.Ansagen20er("Herz", spiel.getS1());

        assertTrue("Spieler1 hat angesagt", spiel.getS1().isAngesagt20er());
        assertTrue("Spieler2 hat nicht angesagt", !spiel.getS2().isAngesagt20er());

        spiel.ZugAuswerten(spiel.getS1().Hand.get(0), spiel.getS2().Hand.get(0));

        if (spiel.getTrumpf() == spiel.getS2().Hand.get(0).getFarbe()) {
            assertTrue("Spieler2 gewinnt und ist dran", spiel.getS2().isIstdran());
            assertEquals("Spieler2 hat 13 Punkte", 13, spiel.getS2().getPunkte());
        }
        if (spiel.getTrumpf() != spiel.getS2().Hand.get(0).getFarbe() && spiel.getTrumpf() != spiel.getS1().Hand.get(0).getFarbe()) {
            assertTrue("Spieler1 gewinnt und ist dran", spiel.getS1().isIstdran());
            assertEquals("Spieler1 hat 33 Punkte", 33, spiel.getS1().getPunkte());
        }

        if (spiel.getTrumpf() != spiel.getS2().Hand.get(0).getFarbe() && spiel.getTrumpf() == spiel.getS1().Hand.get(0).getFarbe()) {
            assertTrue("Spieler1 gewinnt und ist dran", spiel.getS1().isIstdran());
            assertEquals("Spieler1 hat 33 Punkte", 53, spiel.getS1().getPunkte());
        }
    }


}
