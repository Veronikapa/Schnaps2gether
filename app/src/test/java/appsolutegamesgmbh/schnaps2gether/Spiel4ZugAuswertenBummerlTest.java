package appsolutegamesgmbh.schnaps2gether;

import org.junit.Before;
import org.junit.Test;

import appsolutegamesgmbh.schnaps2gether.DataStructure.Bummerl4;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Karte;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Spiel4;
import appsolutegamesgmbh.schnaps2gether.DataStructure.WrongGameException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by n15r2 on 27.05.15.
 */
public class Spiel4ZugAuswertenBummerlTest {

    Bummerl4 bummerl = new Bummerl4();

    Karte herzbube = new Karte("Herz", "Bube", 2);
    Karte herzdame = new Karte("Herz", "Dame", 3);
    Karte herzkoenig = new Karte("Herz", "König", 4);
    Karte herz10 = new Karte("Herz", "10er", 10);
    Karte herzass = new Karte("Herz", "Ass", 11);

    Karte karobube = new Karte("Karo", "Bube", 2);
    Karte karodame = new Karte("Karo", "Dame", 3);
    Karte karokoenig = new Karte("Karo", "König", 4);
    Karte karo10 = new Karte("Karo", "10er", 10);
    Karte karoass = new Karte("Karo", "Ass", 11);

    Karte pikbube = new Karte("Pik", "Bube", 2);
    Karte pikdame = new Karte("Pik", "Dame", 3);
    Karte pikkoenig = new Karte("Pik", "König", 4);
    Karte pik10 = new Karte("Pik", "10er", 10);
    Karte pikass = new Karte("Pik", "Ass", 11);

    Karte kreuzbube = new Karte("Kreuz", "Bube", 2);
    Karte kreuzdame = new Karte("Kreuz", "Dame", 3);
    Karte kreuzkoenig = new Karte("Kreuz", "König", 4);
    Karte kreuz10 = new Karte("Kreuz", "10er", 10);
    Karte kreuzass = new Karte("Kreuz", "Ass", 11);

    @Before
    public void setUp() {

    }

    @Test
    public void ZugAuswerten_normal_S1Gewinner() throws WrongGameException {
        Spiel4 spiel = new Spiel4(0);
        spiel.Aufdrehen();
        spiel.Trumpfansagen("Herz", 0);
        spiel.getS1().Hand.removeAll(spiel.getS1().Hand);
        spiel.getS2().Hand.removeAll(spiel.getS2().Hand);
        spiel.getS3().Hand.removeAll(spiel.getS3().Hand);
        spiel.getS4().Hand.removeAll(spiel.getS4().Hand);

        //Karten Spieler1
        spiel.getS1().Hand.add(herzbube);
        spiel.getS1().Hand.add(herzdame);
        spiel.getS1().Hand.add(herzkoenig);
        spiel.getS1().Hand.add(herz10);
        spiel.getS1().Hand.add(herzass);

        //Karten Spieler2
        spiel.getS2().Hand.add(karobube);
        spiel.getS2().Hand.add(karodame);
        spiel.getS2().Hand.add(karokoenig);
        spiel.getS2().Hand.add(karo10);
        spiel.getS2().Hand.add(karoass);

        //Karten Spieler3
        spiel.getS3().Hand.add(pikbube);
        spiel.getS3().Hand.add(pikdame);
        spiel.getS3().Hand.add(pikkoenig);
        spiel.getS3().Hand.add(pik10);
        spiel.getS3().Hand.add(pikass);

        //Karten Spieler4
        spiel.getS4().Hand.add(kreuzbube);
        spiel.getS4().Hand.add(kreuzdame);
        spiel.getS4().Hand.add(kreuzkoenig);
        spiel.getS4().Hand.add(kreuz10);
        spiel.getS4().Hand.add(kreuzass);

        Karte ausgespieltS1 = spiel.getS1().Hand.get(0);
        Karte ausgespieltS2 = spiel.getS2().Hand.get(0);
        Karte ausgespieltS3 = spiel.getS3().Hand.get(0);
        Karte ausgespieltS4 = spiel.getS4().Hand.get(0);

        spiel.Auspielen(spiel.getS1().Hand.get(0), spiel.getS1());
        spiel.Auspielen(spiel.getS2().Hand.get(0), spiel.getS2());
        spiel.Auspielen(spiel.getS3().Hand.get(0), spiel.getS3());
        spiel.Auspielen(spiel.getS4().Hand.get(0), spiel.getS4());

        spiel.ZugAuswerten(ausgespieltS1, ausgespieltS2, ausgespieltS3, ausgespieltS4);
            assertEquals("Spieler1 erhält 8 Punkte", 8, spiel.getS1().getPunkte());
            assertEquals("Spieler2 erhält 0 Punkte", 0, spiel.getS2().getPunkte());
            assertEquals("Spieler3 erhält 0 Punkte", 0, spiel.getS3().getPunkte());
            assertEquals("Spieler4 erhält 0 Punkte", 0, spiel.getS4().getPunkte());
            assertTrue("Spieler1 ist dran", spiel.getS1().isIstdran());
            assertTrue(!spiel.istSpielzuEnde(bummerl));

        spiel.ZugAuswerten(spiel.getS1().Hand.get(0), spiel.getS2().Hand.get(0), spiel.getS3().Hand.get(0), spiel.getS4().Hand.get(0));
        spiel.getS1().Hand.remove(0);
        spiel.getS2().Hand.remove(0);
        spiel.getS3().Hand.remove(0);
        spiel.getS4().Hand.remove(0);
        assertEquals("Spieler1 erhält 20 Punkte", 20, spiel.getS1().getPunkte());
        assertEquals("Spieler2 erhält 0 Punkte", 0, spiel.getS2().getPunkte());
        assertEquals("Spieler3 erhält 0 Punkte", 0, spiel.getS3().getPunkte());
        assertEquals("Spieler4 erhält 0 Punkte", 0, spiel.getS4().getPunkte());
        assertTrue("Spieler1 ist dran", spiel.getS1().isIstdran());
        assertTrue(!spiel.istSpielzuEnde(bummerl));

        spiel.ZugAuswerten(spiel.getS1().Hand.get(0), spiel.getS2().Hand.get(0), spiel.getS3().Hand.get(0), spiel.getS4().Hand.get(0));
        spiel.getS1().Hand.remove(0);
        spiel.getS2().Hand.remove(0);
        spiel.getS3().Hand.remove(0);
        spiel.getS4().Hand.remove(0);
        assertEquals("Spieler1 erhält 36 Punkte", 36, spiel.getS1().getPunkte());
        assertEquals("Spieler2 erhält 0 Punkte", 0, spiel.getS2().getPunkte());
        assertEquals("Spieler3 erhält 0 Punkte", 0, spiel.getS3().getPunkte());
        assertEquals("Spieler4 erhält 0 Punkte", 0, spiel.getS4().getPunkte());
        assertTrue("Spieler1 ist dran", spiel.getS1().isIstdran());
        assertTrue(!spiel.istSpielzuEnde(bummerl));

        spiel.ZugAuswerten(spiel.getS1().Hand.get(0), spiel.getS2().Hand.get(0), spiel.getS3().Hand.get(0), spiel.getS4().Hand.get(0));
        spiel.getS1().Hand.remove(0);
        spiel.getS2().Hand.remove(0);
        spiel.getS3().Hand.remove(0);
        spiel.getS4().Hand.remove(0);
        assertEquals("Spieler1 erhält 76 Punkte", 76, spiel.getS1().getPunkte());
        assertEquals("Spieler2 erhält 0 Punkte", 0, spiel.getS2().getPunkte());
        assertEquals("Spieler3 erhält 0 Punkte", 0, spiel.getS3().getPunkte());
        assertEquals("Spieler4 erhält 0 Punkte", 0, spiel.getS4().getPunkte());
        assertTrue("Spieler1 ist dran", spiel.getS1().isIstdran());
        assertTrue(spiel.istSpielzuEnde(bummerl));
    }

    @Test
    public void ZugAuswerten_normal_S2Gewinner() throws WrongGameException {
        Spiel4 spiel = new Spiel4(0);
        spiel.Aufdrehen();
        spiel.Trumpfansagen("Karo", 0);
        spiel.getS1().Hand.removeAll(spiel.getS1().Hand);
        spiel.getS2().Hand.removeAll(spiel.getS2().Hand);
        spiel.getS3().Hand.removeAll(spiel.getS3().Hand);
        spiel.getS4().Hand.removeAll(spiel.getS4().Hand);

        //Karten Spieler1
        spiel.getS1().Hand.add(herzbube);
        spiel.getS1().Hand.add(herzdame);
        spiel.getS1().Hand.add(herzkoenig);
        spiel.getS1().Hand.add(herz10);
        spiel.getS1().Hand.add(herzass);

        //Karten Spieler2
        spiel.getS2().Hand.add(karobube);
        spiel.getS2().Hand.add(karodame);
        spiel.getS2().Hand.add(karokoenig);
        spiel.getS2().Hand.add(karo10);
        spiel.getS2().Hand.add(karoass);

        //Karten Spieler3
        spiel.getS3().Hand.add(pikbube);
        spiel.getS3().Hand.add(pikdame);
        spiel.getS3().Hand.add(pikkoenig);
        spiel.getS3().Hand.add(pik10);
        spiel.getS3().Hand.add(pikass);

        //Karten Spieler4
        spiel.getS4().Hand.add(kreuzbube);
        spiel.getS4().Hand.add(kreuzdame);
        spiel.getS4().Hand.add(kreuzkoenig);
        spiel.getS4().Hand.add(kreuz10);
        spiel.getS4().Hand.add(kreuzass);

        Karte ausgespieltS1 = spiel.getS1().Hand.get(0);
        Karte ausgespieltS2 = spiel.getS2().Hand.get(0);
        Karte ausgespieltS3 = spiel.getS3().Hand.get(0);
        Karte ausgespieltS4 = spiel.getS4().Hand.get(0);

        spiel.Auspielen(spiel.getS1().Hand.get(0), spiel.getS1());
        spiel.Auspielen(spiel.getS2().Hand.get(0), spiel.getS2());
        spiel.Auspielen(spiel.getS3().Hand.get(0), spiel.getS3());
        spiel.Auspielen(spiel.getS4().Hand.get(0), spiel.getS4());

        spiel.ZugAuswerten(ausgespieltS1, ausgespieltS2, ausgespieltS3, ausgespieltS4);
        assertEquals("Spieler1 erhält 0 Punkte", 0, spiel.getS1().getPunkte());
        assertEquals("Spieler2 erhält 8 Punkte", 8, spiel.getS2().getPunkte());
        assertEquals("Spieler3 erhält 0 Punkte", 0, spiel.getS3().getPunkte());
        assertEquals("Spieler4 erhält 0 Punkte", 0, spiel.getS4().getPunkte());
        assertTrue("Spieler2 ist dran", spiel.getS2().isIstdran());
        assertTrue(!spiel.istSpielzuEnde(bummerl));

        spiel.ZugAuswerten(spiel.getS1().Hand.get(0), spiel.getS2().Hand.get(0), spiel.getS3().Hand.get(0), spiel.getS4().Hand.get(0));
        spiel.getS1().Hand.remove(0);
        spiel.getS2().Hand.remove(0);
        spiel.getS3().Hand.remove(0);
        spiel.getS4().Hand.remove(0);
        assertEquals("Spieler1 erhält 0 Punkte", 0, spiel.getS1().getPunkte());
        assertEquals("Spieler2 erhält 20 Punkte", 20, spiel.getS2().getPunkte());
        assertEquals("Spieler3 erhält 0 Punkte", 0, spiel.getS3().getPunkte());
        assertEquals("Spieler4 erhält 0 Punkte", 0, spiel.getS4().getPunkte());
        assertTrue("Spieler2 ist dran", spiel.getS2().isIstdran());
        assertTrue(!spiel.istSpielzuEnde(bummerl));

        spiel.ZugAuswerten(spiel.getS1().Hand.get(0), spiel.getS2().Hand.get(0), spiel.getS3().Hand.get(0), spiel.getS4().Hand.get(0));
        spiel.getS1().Hand.remove(0);
        spiel.getS2().Hand.remove(0);
        spiel.getS3().Hand.remove(0);
        spiel.getS4().Hand.remove(0);
        assertEquals("Spieler1 erhält 0 Punkte", 0, spiel.getS1().getPunkte());
        assertEquals("Spieler2 erhält 36 Punkte", 36, spiel.getS2().getPunkte());
        assertEquals("Spieler3 erhält 0 Punkte", 0, spiel.getS3().getPunkte());
        assertEquals("Spieler4 erhält 0 Punkte", 0, spiel.getS4().getPunkte());
        assertTrue("Spieler2 ist dran", spiel.getS2().isIstdran());
        assertTrue(!spiel.istSpielzuEnde(bummerl));

        spiel.ZugAuswerten(spiel.getS1().Hand.get(0), spiel.getS2().Hand.get(0), spiel.getS3().Hand.get(0), spiel.getS4().Hand.get(0));
        spiel.getS1().Hand.remove(0);
        spiel.getS2().Hand.remove(0);
        spiel.getS3().Hand.remove(0);
        spiel.getS4().Hand.remove(0);
        assertEquals("Spieler1 erhält 0 Punkte", 0, spiel.getS1().getPunkte());
        assertEquals("Spieler2 erhält 76 Punkte", 76, spiel.getS2().getPunkte());
        assertEquals("Spieler3 erhält 0 Punkte", 0, spiel.getS3().getPunkte());
        assertEquals("Spieler4 erhält 0 Punkte", 0, spiel.getS4().getPunkte());
        assertTrue("Spieler2 ist dran", spiel.getS2().isIstdran());
        assertTrue(spiel.istSpielzuEnde(bummerl));
    }

    @Test
    public void ZugAuswerten_normal_S3Gewinner() throws WrongGameException {
        Spiel4 spiel = new Spiel4(0);
        spiel.Aufdrehen();
        spiel.Trumpfansagen("Pik", 0);
        spiel.getS1().Hand.removeAll(spiel.getS1().Hand);
        spiel.getS2().Hand.removeAll(spiel.getS2().Hand);
        spiel.getS3().Hand.removeAll(spiel.getS3().Hand);
        spiel.getS4().Hand.removeAll(spiel.getS4().Hand);

        //Karten Spieler1
        spiel.getS1().Hand.add(herzbube);
        spiel.getS1().Hand.add(herzdame);
        spiel.getS1().Hand.add(herzkoenig);
        spiel.getS1().Hand.add(herz10);
        spiel.getS1().Hand.add(herzass);

        //Karten Spieler2
        spiel.getS2().Hand.add(karobube);
        spiel.getS2().Hand.add(karodame);
        spiel.getS2().Hand.add(karokoenig);
        spiel.getS2().Hand.add(karo10);
        spiel.getS2().Hand.add(karoass);

        //Karten Spieler3
        spiel.getS3().Hand.add(pikbube);
        spiel.getS3().Hand.add(pikdame);
        spiel.getS3().Hand.add(pikkoenig);
        spiel.getS3().Hand.add(pik10);
        spiel.getS3().Hand.add(pikass);

        //Karten Spieler4
        spiel.getS4().Hand.add(kreuzbube);
        spiel.getS4().Hand.add(kreuzdame);
        spiel.getS4().Hand.add(kreuzkoenig);
        spiel.getS4().Hand.add(kreuz10);
        spiel.getS4().Hand.add(kreuzass);

        Karte ausgespieltS1 = spiel.getS1().Hand.get(0);
        Karte ausgespieltS2 = spiel.getS2().Hand.get(0);
        Karte ausgespieltS3 = spiel.getS3().Hand.get(0);
        Karte ausgespieltS4 = spiel.getS4().Hand.get(0);

        spiel.Auspielen(spiel.getS1().Hand.get(0), spiel.getS1());
        spiel.Auspielen(spiel.getS2().Hand.get(0), spiel.getS2());
        spiel.Auspielen(spiel.getS3().Hand.get(0), spiel.getS3());
        spiel.Auspielen(spiel.getS4().Hand.get(0), spiel.getS4());

        spiel.ZugAuswerten(ausgespieltS1, ausgespieltS2, ausgespieltS3, ausgespieltS4);
        assertEquals("Spieler1 erhält 0 Punkte", 0, spiel.getS1().getPunkte());
        assertEquals("Spieler2 erhält 0 Punkte", 0, spiel.getS2().getPunkte());
        assertEquals("Spieler3 erhält 8 Punkte", 8, spiel.getS3().getPunkte());
        assertEquals("Spieler4 erhält 0 Punkte", 0, spiel.getS4().getPunkte());
        assertTrue("Spieler3 ist dran", spiel.getS3().isIstdran());
        assertTrue(!spiel.istSpielzuEnde(bummerl));

        spiel.ZugAuswerten(spiel.getS1().Hand.get(0), spiel.getS2().Hand.get(0), spiel.getS3().Hand.get(0), spiel.getS4().Hand.get(0));
        spiel.getS1().Hand.remove(0);
        spiel.getS2().Hand.remove(0);
        spiel.getS3().Hand.remove(0);
        spiel.getS4().Hand.remove(0);
        assertEquals("Spieler1 erhält 0 Punkte", 0, spiel.getS1().getPunkte());
        assertEquals("Spieler2 erhält 0 Punkte", 0, spiel.getS2().getPunkte());
        assertEquals("Spieler3 erhält 20 Punkte", 20, spiel.getS3().getPunkte());
        assertEquals("Spieler4 erhält 0 Punkte", 0, spiel.getS4().getPunkte());
        assertTrue("Spieler3 ist dran", spiel.getS3().isIstdran());
        assertTrue(!spiel.istSpielzuEnde(bummerl));

        spiel.ZugAuswerten(spiel.getS1().Hand.get(0), spiel.getS2().Hand.get(0), spiel.getS3().Hand.get(0), spiel.getS4().Hand.get(0));
        spiel.getS1().Hand.remove(0);
        spiel.getS2().Hand.remove(0);
        spiel.getS3().Hand.remove(0);
        spiel.getS4().Hand.remove(0);
        assertEquals("Spieler1 erhält 0 Punkte", 0, spiel.getS1().getPunkte());
        assertEquals("Spieler2 erhält 0 Punkte", 0, spiel.getS2().getPunkte());
        assertEquals("Spieler3 erhält 36 Punkte", 36, spiel.getS3().getPunkte());
        assertEquals("Spieler4 erhält 0 Punkte", 0, spiel.getS4().getPunkte());
        assertTrue("Spieler3 ist dran", spiel.getS3().isIstdran());
        assertTrue(!spiel.istSpielzuEnde(bummerl));

        spiel.ZugAuswerten(spiel.getS1().Hand.get(0), spiel.getS2().Hand.get(0), spiel.getS3().Hand.get(0), spiel.getS4().Hand.get(0));
        spiel.getS1().Hand.remove(0);
        spiel.getS2().Hand.remove(0);
        spiel.getS3().Hand.remove(0);
        spiel.getS4().Hand.remove(0);
        assertEquals("Spieler1 erhält 0 Punkte", 0, spiel.getS1().getPunkte());
        assertEquals("Spieler2 erhält 0 Punkte", 0, spiel.getS2().getPunkte());
        assertEquals("Spieler3 erhält 76 Punkte", 76, spiel.getS3().getPunkte());
        assertEquals("Spieler4 erhält 0 Punkte", 0, spiel.getS4().getPunkte());
        assertTrue("Spieler3 ist dran", spiel.getS3().isIstdran());
        assertTrue(spiel.istSpielzuEnde(bummerl));
    }

    @Test
    public void ZugAuswerten_normal_S4Gewinner() throws WrongGameException {
        Spiel4 spiel = new Spiel4(0);
        spiel.Trumpfansagen("Kreuz", 0);
        spiel.getS1().Hand.removeAll(spiel.getS1().Hand);
        spiel.getS2().Hand.removeAll(spiel.getS2().Hand);
        spiel.getS3().Hand.removeAll(spiel.getS3().Hand);
        spiel.getS4().Hand.removeAll(spiel.getS4().Hand);

        //Karten Spieler1
        spiel.getS1().Hand.add(herzbube);
        spiel.getS1().Hand.add(herzdame);
        spiel.getS1().Hand.add(herzkoenig);
        spiel.getS1().Hand.add(herz10);
        spiel.getS1().Hand.add(herzass);

        //Karten Spieler2
        spiel.getS2().Hand.add(karobube);
        spiel.getS2().Hand.add(karodame);
        spiel.getS2().Hand.add(karokoenig);
        spiel.getS2().Hand.add(karo10);
        spiel.getS2().Hand.add(karoass);

        //Karten Spieler3
        spiel.getS3().Hand.add(pikbube);
        spiel.getS3().Hand.add(pikdame);
        spiel.getS3().Hand.add(pikkoenig);
        spiel.getS3().Hand.add(pik10);
        spiel.getS3().Hand.add(pikass);

        //Karten Spieler4
        spiel.getS4().Hand.add(kreuzbube);
        spiel.getS4().Hand.add(kreuzdame);
        spiel.getS4().Hand.add(kreuzkoenig);
        spiel.getS4().Hand.add(kreuz10);
        spiel.getS4().Hand.add(kreuzass);

        Karte ausgespieltS1 = spiel.getS1().Hand.get(0);
        Karte ausgespieltS2 = spiel.getS2().Hand.get(0);
        Karte ausgespieltS3 = spiel.getS3().Hand.get(0);
        Karte ausgespieltS4 = spiel.getS4().Hand.get(0);

        spiel.Auspielen(spiel.getS1().Hand.get(0), spiel.getS1());
        spiel.Auspielen(spiel.getS2().Hand.get(0), spiel.getS2());
        spiel.Auspielen(spiel.getS3().Hand.get(0), spiel.getS3());
        spiel.Auspielen(spiel.getS4().Hand.get(0), spiel.getS4());

        spiel.ZugAuswerten(ausgespieltS1, ausgespieltS2, ausgespieltS3, ausgespieltS4);
        assertEquals("Spieler1 erhält 0 Punkte", 0, spiel.getS1().getPunkte());
        assertEquals("Spieler2 erhält 0 Punkte", 0, spiel.getS2().getPunkte());
        assertEquals("Spieler3 erhält 0 Punkte", 0, spiel.getS3().getPunkte());
        assertEquals("Spieler4 erhält 8 Punkte", 8, spiel.getS4().getPunkte());
        assertTrue("Spieler4 ist dran", spiel.getS4().isIstdran());
        assertTrue(!spiel.istSpielzuEnde(bummerl));

        spiel.ZugAuswerten(spiel.getS1().Hand.get(0), spiel.getS2().Hand.get(0), spiel.getS3().Hand.get(0), spiel.getS4().Hand.get(0));
        assertEquals("Spieler1", 3, spiel.getS1().Hand.get(0).getPunkte());
        assertEquals("Spieler2", 3, spiel.getS2().Hand.get(0).getPunkte());
        assertEquals("Spieler3", 3, spiel.getS3().Hand.get(0).getPunkte());
        assertEquals("Spieler4", 3, spiel.getS4().Hand.get(0).getPunkte());
        spiel.getS1().Hand.remove(0);
        spiel.getS2().Hand.remove(0);
        spiel.getS3().Hand.remove(0);
        spiel.getS4().Hand.remove(0);
        assertEquals("Spieler1 erhält 0 Punkte", 0, spiel.getS1().getPunkte());
        assertEquals("Spieler2 erhält 0 Punkte", 0, spiel.getS2().getPunkte());
        assertEquals("Spieler3 erhält 0 Punkte", 0, spiel.getS3().getPunkte());
        assertEquals("Spieler4 erhält 20 Punkte", 20, spiel.getS4().getPunkte());
        assertTrue("Spieler4 ist dran", spiel.getS4().isIstdran());
        assertTrue(!spiel.istSpielzuEnde(bummerl));

        spiel.ZugAuswerten(spiel.getS1().Hand.get(0), spiel.getS2().Hand.get(0), spiel.getS3().Hand.get(0), spiel.getS4().Hand.get(0));
        spiel.getS1().Hand.remove(0);
        spiel.getS2().Hand.remove(0);
        spiel.getS3().Hand.remove(0);
        spiel.getS4().Hand.remove(0);
        assertEquals("Spieler1 erhält 0 Punkte", 0, spiel.getS1().getPunkte());
        assertEquals("Spieler2 erhält 0 Punkte", 0, spiel.getS2().getPunkte());
        assertEquals("Spieler3 erhält 0 Punkte", 0, spiel.getS3().getPunkte());
        assertEquals("Spieler4 erhält 36 Punkte", 36, spiel.getS4().getPunkte());
        assertTrue("Spieler4 ist dran", spiel.getS4().isIstdran());
        assertTrue(!spiel.istSpielzuEnde(bummerl));

        spiel.ZugAuswerten(spiel.getS1().Hand.get(0), spiel.getS2().Hand.get(0), spiel.getS3().Hand.get(0), spiel.getS4().Hand.get(0));
        spiel.getS1().Hand.remove(0);
        spiel.getS2().Hand.remove(0);
        spiel.getS3().Hand.remove(0);
        spiel.getS4().Hand.remove(0);
        assertEquals("Spieler1 erhält 0 Punkte", 0, spiel.getS1().getPunkte());
        assertEquals("Spieler2 erhält 0 Punkte", 0, spiel.getS2().getPunkte());
        assertEquals("Spieler3 erhält 0 Punkte", 0, spiel.getS3().getPunkte());
        assertEquals("Spieler4 erhält 76 Punkte", 76, spiel.getS4().getPunkte());
        assertTrue("Spieler4 ist dran", spiel.getS4().isIstdran());
        assertTrue(spiel.istSpielzuEnde(bummerl));
    }
}