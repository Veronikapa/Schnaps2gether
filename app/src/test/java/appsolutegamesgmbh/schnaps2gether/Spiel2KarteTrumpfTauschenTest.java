package appsolutegamesgmbh.schnaps2gether;

import org.junit.Before;
import org.junit.Test;

import appsolutegamesgmbh.schnaps2gether.DataStructure.Karte;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Spiel2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by n15r2 on 15.05.15.
 */
public class Spiel2KarteTrumpfTauschenTest {
    int count = 0;
    Karte herzdame = new Karte("Herz","Dame",3);
    Karte herzk = new Karte("Herz","König",4);
    Karte herzass = new Karte("Herz","Ass",11);

    //TrumpfKarten
    Karte herzt = new Karte("Herz", "Bube", 2);
    Karte kreuzt = new Karte("Kreuz", "Bube", 2);
    Karte pikt = new Karte("Pik", "Bube", 2);
    Karte karot = new Karte("Karo", "Bube", 2);

    @Before
    public void setUp() {

    }

    @Test
    public void TrumpfDarfGetauschtWerden() {
        Spiel2 spiel = new Spiel2(0);
        spiel.getS1().Hand.removeAll(spiel.getS1().Hand);
        spiel.getS2().Hand.removeAll(spiel.getS2().Hand);

        assertEquals("Handkarten sind leer", 0, spiel.getS1().Hand.size());
        assertEquals("Handkarten sind leer", 0, spiel.getS2().Hand.size());
        //alle Buben Spieler1
        spiel.getS1().Hand.add(herzt);
        spiel.getS1().Hand.add(kreuzt);
        spiel.getS1().Hand.add(pikt);
        spiel.getS1().Hand.add(karot);
        //keine Buben Spieler2
        spiel.getS2().Hand.add(herzk);
        spiel.getS2().Hand.add(herzass);

        if (spiel.getTrumpf() == "Herz") {
            spiel.TrumpfkarteAustauschen(spiel.getS1().Hand.get(0), spiel.getS1());
            assertEquals("neue Karte wurde aufgenommen", 4, spiel.getS1().Hand.size());
            for (Karte k : spiel.getS1().Hand) {
                if(k.getPunkte() != 2) {
                    count++;
                    assertEquals("Trumpf getauscht", 1, count);
                }
            }
        }
        if (spiel.getTrumpf() == "Kreuz") {
            spiel.TrumpfkarteAustauschen(spiel.getS1().Hand.get(1), spiel.getS1());
            assertEquals("neue Karte wurde aufgenommen", 4, spiel.getS1().Hand.size());
            for (Karte k : spiel.getS1().Hand) {
                if(k.getPunkte() != 2) {
                    count++;
                    assertEquals("Trumpf getauscht", 1, count);
                }
            }
        }
        if (spiel.getTrumpf() == "Pik") {
            spiel.TrumpfkarteAustauschen(spiel.getS1().Hand.get(2), spiel.getS1());
            assertEquals("neue Karte wurde aufgenommen", 4, spiel.getS1().Hand.size());
            for (Karte k : spiel.getS1().Hand) {
                if(k.getPunkte() != 2) {
                    count++;
                    assertEquals("Trumpf getauscht", 1, count);
                }
            }
        }
        if (spiel.getTrumpf() == "Karo") {
            spiel.TrumpfkarteAustauschen(spiel.getS1().Hand.get(3), spiel.getS1());
            assertEquals("neue Karte wurde aufgenommen", 4, spiel.getS1().Hand.size());
            for (Karte k : spiel.getS1().Hand) {
                if(k.getPunkte() != 2) {
                    count++;
                    assertEquals("Trumpf getauscht", 1, count);
                }
            }
        }



    }

}
