package appsolutegamesgmbh.schnaps2gether;

import org.junit.Before;
import org.junit.Test;

import appsolutegamesgmbh.schnaps2gether.DataStructure.Bummerl2;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Karte;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Spiel2;
import appsolutegamesgmbh.schnaps2gether.DataStructure.WrongGameException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by n15r2 on 14.06.15.
 */
public class Spiel2ZugAuswertenTest {

    Bummerl2 bummerl = new Bummerl2();

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
    public void ZugAuswerten_Punktepruefen_zugedreht_S1Gewinner() throws WrongGameException {
        Spiel2 spiel = new Spiel2(0);

        while (spiel.getTrumpf() != "Herz") {
            spiel = new Spiel2(0);
        }

        spiel.getS1().Hand.removeAll(spiel.getS1().Hand);
        spiel.getS2().Hand.removeAll(spiel.getS2().Hand);

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

        Karte ausgespieltS1 = spiel.getS1().Hand.get(0);
        Karte ausgespieltS2 = spiel.getS2().Hand.get(0);

        spiel.Auspielen(spiel.getS1().Hand.get(0), spiel.getS1());
        spiel.Auspielen(spiel.getS2().Hand.get(0), spiel.getS2());

        spiel.Zudrehen(spiel.getS2());

        //Zug1
        spiel.ZugAuswerten(ausgespieltS1, ausgespieltS2, 0);
            assertEquals("Spieler1 erhält 4 Punkte", 4, spiel.getS1().getPunkte());
            assertEquals("Spieler2 erhält 0 Punkte", 0, spiel.getS2().getPunkte());
            assertTrue("Spieler1 ist dran", spiel.getS1().isIstdran());
            assertTrue(!spiel.istSpielzuEnde(bummerl));

        //Zug2
        spiel.ZugAuswerten(herzdame, karodame, 0);
        spiel.getS1().Hand.remove(herzdame);
        spiel.getS2().Hand.remove(karodame);
        assertEquals("Spieler1 erhält 10 Punkte", 10, spiel.getS1().getPunkte());
        assertEquals("Spieler2 erhält 0 Punkte", 0, spiel.getS2().getPunkte());
        assertTrue("Spieler1 ist dran", spiel.getS1().isIstdran());
        assertTrue(!spiel.istSpielzuEnde(bummerl));

        //Zug3
        spiel.ZugAuswerten(herzkoenig, karokoenig, 0);
        spiel.getS1().Hand.remove(herzkoenig);
        spiel.getS2().Hand.remove(karokoenig);
        assertEquals("Spieler1 erhält 18 Punkte", 18, spiel.getS1().getPunkte());
        assertEquals("Spieler2 erhält 0 Punkte", 0, spiel.getS2().getPunkte());
        assertTrue("Spieler1 ist dran", spiel.getS1().isIstdran());
        assertTrue(!spiel.istSpielzuEnde(bummerl));


        //Zug4
        spiel.ZugAuswerten(herz10, karo10,0);
        spiel.getS1().Hand.remove(herz10);
        spiel.getS2().Hand.remove(karo10);
        assertEquals("Spieler1 erhält 38 Punkte", 38, spiel.getS1().getPunkte());
        assertEquals("Spieler2 erhält 0 Punkte", 0, spiel.getS2().getPunkte());
        assertTrue("Spieler1 ist dran", spiel.getS1().isIstdran());
        assertTrue(!spiel.istSpielzuEnde(bummerl));


        //Zug5
        spiel.ZugAuswerten(herzass, karoass,0);
        spiel.getS1().Hand.remove(herzass);
        spiel.getS2().Hand.remove(karoass);
        assertEquals("Spieler1 erhält 60 Punkte", 60, spiel.getS1().getPunkte());
        assertEquals("Spieler2 erhält 0 Punkte", 0, spiel.getS2().getPunkte());
        assertTrue("Spieler1 ist dran", spiel.getS1().isIstdran());
        assertTrue(spiel.istSpielzuEnde(bummerl));
    }

    @Test
    public void ZugAuswerten_Punktepruefen_zugedreht_S2Gewinner() throws WrongGameException {
        Spiel2 spiel = new Spiel2(0);

        while (spiel.getTrumpf() != "Karo") {
            spiel = new Spiel2(0);
        }

        spiel.getS1().Hand.removeAll(spiel.getS1().Hand);
        spiel.getS2().Hand.removeAll(spiel.getS2().Hand);

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

        Karte ausgespieltS1 = spiel.getS1().Hand.get(0);
        Karte ausgespieltS2 = spiel.getS2().Hand.get(0);

        spiel.Auspielen(spiel.getS1().Hand.get(0), spiel.getS1());
        spiel.Auspielen(spiel.getS2().Hand.get(0), spiel.getS2());

        spiel.Zudrehen(spiel.getS2());

        //Zug1
        spiel.ZugAuswerten(ausgespieltS1, ausgespieltS2, 0);
        assertEquals("Spieler1 erhält 0 Punkte", 0, spiel.getS1().getPunkte());
        assertEquals("Spieler2 erhält 4 Punkte", 4, spiel.getS2().getPunkte());
        assertTrue("Spieler2 ist dran", spiel.getS2().isIstdran());
        assertTrue(!spiel.istSpielzuEnde(bummerl));

        //Zug2
        spiel.ZugAuswerten(herzdame, karodame, 0);
        spiel.getS1().Hand.remove(herzdame);
        spiel.getS2().Hand.remove(karodame);
        assertEquals("Spieler1 erhält 0 Punkte", 0, spiel.getS1().getPunkte());
        assertEquals("Spieler2 erhält 10 Punkte", 10, spiel.getS2().getPunkte());
        assertTrue("Spieler2 ist dran", spiel.getS2().isIstdran());
        assertTrue(!spiel.istSpielzuEnde(bummerl));

        //Zug3
        spiel.ZugAuswerten(herzkoenig, karokoenig, 0);
        spiel.getS1().Hand.remove(herzkoenig);
        spiel.getS2().Hand.remove(karokoenig);
        assertEquals("Spieler1 erhält 0 Punkte", 0, spiel.getS1().getPunkte());
        assertEquals("Spieler2 erhält 18 Punkte", 18, spiel.getS2().getPunkte());
        assertTrue("Spieler2 ist dran", spiel.getS2().isIstdran());
        assertTrue(!spiel.istSpielzuEnde(bummerl));


        //Zug4
        spiel.ZugAuswerten(herz10, karo10, 0);
        spiel.getS1().Hand.remove(herz10);
        spiel.getS2().Hand.remove(karo10);
        assertEquals("Spieler1 erhält 0 Punkte", 0, spiel.getS1().getPunkte());
        assertEquals("Spieler2 erhält 38 Punkte", 38, spiel.getS2().getPunkte());
        assertTrue("Spieler2 ist dran", spiel.getS2().isIstdran());
        assertTrue(!spiel.istSpielzuEnde(bummerl));


        //Zug5
        spiel.ZugAuswerten(herzass, karoass, 0);
        spiel.getS1().Hand.remove(herzass);
        spiel.getS2().Hand.remove(karoass);
        assertEquals("Spieler1 erhält 0 Punkte", 0, spiel.getS1().getPunkte());
        assertEquals("Spieler2 erhält 60 Punkte", 60, spiel.getS2().getPunkte());
        assertTrue("Spieler2 ist dran", spiel.getS2().isIstdran());
        assertTrue(spiel.istSpielzuEnde(bummerl));
    }

    @Test
    public void ZugAuswerten_Punktepruefen_zugedreht_S1Verliert() throws WrongGameException {
        Spiel2 spiel = new Spiel2(0);

        while (spiel.getTrumpf() != "Karo") {
            spiel = new Spiel2(0);
        }

        spiel.getS1().Hand.removeAll(spiel.getS1().Hand);
        spiel.getS2().Hand.removeAll(spiel.getS2().Hand);

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

        Karte ausgespieltS1 = spiel.getS1().Hand.get(0);
        Karte ausgespieltS2 = spiel.getS2().Hand.get(0);

        spiel.Auspielen(spiel.getS1().Hand.get(0), spiel.getS1());
        spiel.Auspielen(spiel.getS2().Hand.get(0), spiel.getS2());

        spiel.Zudrehen(spiel.getS2());

        //Zug1
        spiel.ZugAuswerten(ausgespieltS1, ausgespieltS2, 0);
        assertEquals("Spieler1 erhält 0 Punkte", 0, spiel.getS1().getPunkte());
        assertEquals("Spieler2 erhält 4 Punkte", 4, spiel.getS2().getPunkte());
        assertTrue("Spieler2 ist dran", spiel.getS2().isIstdran());
        assertTrue(!spiel.istSpielzuEnde(bummerl));

        //Zug2
        spiel.ZugAuswerten(herzdame, karodame, 0);
        spiel.getS1().Hand.remove(herzdame);
        spiel.getS2().Hand.remove(karodame);
        assertEquals("Spieler1 erhält 0 Punkte", 0, spiel.getS1().getPunkte());
        assertEquals("Spieler2 erhält 10 Punkte", 10, spiel.getS2().getPunkte());
        assertTrue("Spieler2 ist dran", spiel.getS2().isIstdran());
        assertTrue(!spiel.istSpielzuEnde(bummerl));

        //Zug3
        spiel.ZugAuswerten(herzkoenig, karokoenig, 0);
        spiel.getS1().Hand.remove(herzkoenig);
        spiel.getS2().Hand.remove(karokoenig);
        assertEquals("Spieler1 erhält 0 Punkte", 0, spiel.getS1().getPunkte());
        assertEquals("Spieler2 erhält 18 Punkte", 18, spiel.getS2().getPunkte());
        assertTrue("Spieler2 ist dran", spiel.getS2().isIstdran());
        assertTrue(!spiel.istSpielzuEnde(bummerl));


        //Zug4
        spiel.ZugAuswerten(herz10, karo10,0);
        spiel.getS1().Hand.remove(herz10);
        spiel.getS2().Hand.remove(karo10);
        assertEquals("Spieler1 erhält 0 Punkte", 0, spiel.getS1().getPunkte());
        assertEquals("Spieler2 erhält 38 Punkte", 38, spiel.getS2().getPunkte());
        assertTrue("Spieler2 ist dran", spiel.getS2().isIstdran());
        assertTrue(!spiel.istSpielzuEnde(bummerl));


        //Zug5
        spiel.ZugAuswerten(herzass, karoass,0);
        spiel.getS1().Hand.remove(herzass);
        spiel.getS2().Hand.remove(karoass);
        assertEquals("Spieler1 erhält 0 Punkte", 0, spiel.getS1().getPunkte());
        assertEquals("Spieler2 erhält 60 Punkte", 60, spiel.getS2().getPunkte());
        assertTrue("Spieler2 ist dran", spiel.getS2().isIstdran());
        assertTrue(spiel.istSpielzuEnde(bummerl));
    }

    @Test
    public void ZugAuswerten_Punktepruefen_zugedreht_S2Verliert() throws WrongGameException {
        Spiel2 spiel = new Spiel2(0);

        while (spiel.getTrumpf() != "Herz") {
            spiel = new Spiel2(0);
        }

        spiel.getS1().Hand.removeAll(spiel.getS1().Hand);
        spiel.getS2().Hand.removeAll(spiel.getS2().Hand);

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

        Karte ausgespieltS1 = spiel.getS1().Hand.get(0);
        Karte ausgespieltS2 = spiel.getS2().Hand.get(0);

        spiel.Auspielen(spiel.getS1().Hand.get(0), spiel.getS1());
        spiel.Auspielen(spiel.getS2().Hand.get(0), spiel.getS2());

        spiel.Zudrehen(spiel.getS2());

        //Zug1
        spiel.ZugAuswerten(ausgespieltS1, ausgespieltS2, 0);
        assertEquals("Spieler1 erhält 4 Punkte", 4, spiel.getS1().getPunkte());
        assertEquals("Spieler2 erhält 0 Punkte", 0, spiel.getS2().getPunkte());
        assertTrue("Spieler1 ist dran", spiel.getS1().isIstdran());
        assertTrue(!spiel.istSpielzuEnde(bummerl));

        //Zug2
        spiel.ZugAuswerten(herzdame, karodame, 0);
        spiel.getS1().Hand.remove(herzdame);
        spiel.getS2().Hand.remove(karodame);
        assertEquals("Spieler1 erhält 10 Punkte", 10, spiel.getS1().getPunkte());
        assertEquals("Spieler2 erhält 0 Punkte", 0, spiel.getS2().getPunkte());
        assertTrue("Spieler1 ist dran", spiel.getS1().isIstdran());
        assertTrue(!spiel.istSpielzuEnde(bummerl));

        //Zug3
        spiel.ZugAuswerten(herzkoenig, karokoenig, 0);
        spiel.getS1().Hand.remove(herzkoenig);
        spiel.getS2().Hand.remove(karokoenig);
        assertEquals("Spieler1 erhält 18 Punkte", 18, spiel.getS1().getPunkte());
        assertEquals("Spieler2 erhält 0 Punkte", 0, spiel.getS2().getPunkte());
        assertTrue("Spieler1 ist dran", spiel.getS1().isIstdran());
        assertTrue(!spiel.istSpielzuEnde(bummerl));


        //Zug4
        spiel.ZugAuswerten(herz10, karo10, 0);
        spiel.getS1().Hand.remove(herz10);
        spiel.getS2().Hand.remove(karo10);
        assertEquals("Spieler1 erhält 38 Punkte", 38, spiel.getS1().getPunkte());
        assertEquals("Spieler2 erhält 0 Punkte", 0, spiel.getS2().getPunkte());
        assertTrue("Spieler1 ist dran", spiel.getS1().isIstdran());
        assertTrue(!spiel.istSpielzuEnde(bummerl));


        //Zug5
        spiel.ZugAuswerten(herzass, karoass, 0);
        spiel.getS1().Hand.remove(herzass);
        spiel.getS2().Hand.remove(karoass);
        assertEquals("Spieler1 erhält 60 Punkte", 60, spiel.getS1().getPunkte());
        assertEquals("Spieler2 erhält 0 Punkte", 0, spiel.getS2().getPunkte());
        assertTrue("Spieler1 ist dran", spiel.getS1().isIstdran());
        assertTrue(spiel.istSpielzuEnde(bummerl));
    }

    @Test
    public void ZugAuswerten_S1Gewinnt_67Pkt() throws WrongGameException {
        Spiel2 spiel = new Spiel2(0);

        spiel.getS1().setPunkte(67);
        spiel.getS2().setPunkte(0);

        //Auswertung
        assertEquals("Spieler1 erhält 67 Punkte", 67, spiel.getS1().getPunkte());
        assertEquals("Spieler2 erhält 0 Punkte", 0, spiel.getS2().getPunkte());
        assertTrue(spiel.istSpielzuEnde(bummerl));
    }

    @Test
    public void ZugAuswerten_S2Gewinnt_67Pkt() throws WrongGameException {
        Spiel2 spiel = new Spiel2(0);

        spiel.getS1().setPunkte(0);
        spiel.getS2().setPunkte(67);

        //Auswertung
        assertEquals("Spieler1 erhält 0 Punkte", 0, spiel.getS1().getPunkte());
        assertEquals("Spieler2 erhält 67 Punkte", 67, spiel.getS2().getPunkte());
        assertTrue(spiel.istSpielzuEnde(bummerl));
    }

    @Test
    public void ZugAuswerten_Punktepruefen_40erangesagt_S1Gewinner() throws WrongGameException {
        Spiel2 spiel = new Spiel2(0);

        while (spiel.getTrumpf() != "Herz") {
            spiel = new Spiel2(0);
        }

        spiel.getS1().Hand.removeAll(spiel.getS1().Hand);
        spiel.getS2().Hand.removeAll(spiel.getS2().Hand);

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

        spiel.Ansagen20er("Herz", spiel.getS1());

        Karte ausgespieltS1 = spiel.getS1().Hand.get(0);
        Karte ausgespieltS2 = spiel.getS2().Hand.get(0);

        spiel.Auspielen(spiel.getS1().Hand.get(0), spiel.getS1());
        spiel.Auspielen(spiel.getS2().Hand.get(0), spiel.getS2());

        //Zug1
        spiel.ZugAuswerten(ausgespieltS1, ausgespieltS2, 0);
        assertEquals("Spieler1 erhält 44 Punkte", 44, spiel.getS1().getPunkte());
        assertEquals("Spieler2 erhält 0 Punkte", 0, spiel.getS2().getPunkte());
        assertTrue("Spieler1 ist dran", spiel.getS1().isIstdran());
        assertTrue(!spiel.istSpielzuEnde(bummerl));

        //Zug2
        spiel.ZugAuswerten(herzdame, karodame, 0);
        spiel.getS1().Hand.remove(herzdame);
        spiel.getS2().Hand.remove(karodame);
        assertEquals("Spieler1 erhält 50 Punkte", 50, spiel.getS1().getPunkte());
        assertEquals("Spieler2 erhält 0 Punkte", 0, spiel.getS2().getPunkte());
        assertTrue("Spieler1 ist dran", spiel.getS1().isIstdran());
        assertTrue(!spiel.istSpielzuEnde(bummerl));

        //Zug3
        spiel.ZugAuswerten(herzkoenig, karokoenig, 0);
        spiel.getS1().Hand.remove(herzkoenig);
        spiel.getS2().Hand.remove(karokoenig);
        assertEquals("Spieler1 erhält 58 Punkte", 58, spiel.getS1().getPunkte());
        assertEquals("Spieler2 erhält 0 Punkte", 0, spiel.getS2().getPunkte());
        assertTrue("Spieler1 ist dran", spiel.getS1().isIstdran());
        assertTrue(!spiel.istSpielzuEnde(bummerl));


        //Zug4
        spiel.ZugAuswerten(herz10, karo10,0);
        spiel.getS1().Hand.remove(herz10);
        spiel.getS2().Hand.remove(karo10);
        assertEquals("Spieler1 erhält 78 Punkte", 78, spiel.getS1().getPunkte());
        assertEquals("Spieler2 erhält 0 Punkte", 0, spiel.getS2().getPunkte());
        assertTrue("Spieler1 ist dran", spiel.getS1().isIstdran());
        assertTrue(spiel.istSpielzuEnde(bummerl));
    }

    @Test
    public void ZugAuswerten_Punktepruefen_40erangesagt_S2Gewinner() throws WrongGameException {
        Spiel2 spiel = new Spiel2(0);

        while (spiel.getTrumpf() != "Karo") {
            spiel = new Spiel2(0);
        }

        spiel.getS1().Hand.removeAll(spiel.getS1().Hand);
        spiel.getS2().Hand.removeAll(spiel.getS2().Hand);

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

        spiel.Ansagen20er("Karo", spiel.getS2());

        Karte ausgespieltS1 = spiel.getS1().Hand.get(0);
        Karte ausgespieltS2 = spiel.getS2().Hand.get(0);

        spiel.Auspielen(spiel.getS1().Hand.get(0), spiel.getS1());
        spiel.Auspielen(spiel.getS2().Hand.get(0), spiel.getS2());

        //Zug1
        spiel.ZugAuswerten(ausgespieltS1, ausgespieltS2, 0);
        assertEquals("Spieler1 erhält 0 Punkte", 0, spiel.getS1().getPunkte());
        assertEquals("Spieler2 erhält 44 Punkte", 44, spiel.getS2().getPunkte());
        assertTrue("Spieler2 ist dran", spiel.getS2().isIstdran());
        assertTrue(!spiel.istSpielzuEnde(bummerl));

        //Zug2
        spiel.ZugAuswerten(herzdame, karodame, 0);
        spiel.getS1().Hand.remove(herzdame);
        spiel.getS2().Hand.remove(karodame);
        assertEquals("Spieler1 erhält 0 Punkte", 0, spiel.getS1().getPunkte());
        assertEquals("Spieler2 erhält 50 Punkte", 50, spiel.getS2().getPunkte());
        assertTrue("Spieler2 ist dran", spiel.getS2().isIstdran());
        assertTrue(!spiel.istSpielzuEnde(bummerl));

        //Zug3
        spiel.ZugAuswerten(herzkoenig, karokoenig, 0);
        spiel.getS1().Hand.remove(herzkoenig);
        spiel.getS2().Hand.remove(karokoenig);
        assertEquals("Spieler1 erhält 0 Punkte", 0, spiel.getS1().getPunkte());
        assertEquals("Spieler2 erhält 58 Punkte", 58, spiel.getS2().getPunkte());
        assertTrue("Spieler2 ist dran", spiel.getS2().isIstdran());
        assertTrue(!spiel.istSpielzuEnde(bummerl));


        //Zug4
        spiel.ZugAuswerten(herz10, karo10, 0);
        spiel.getS1().Hand.remove(herz10);
        spiel.getS2().Hand.remove(karo10);
        assertEquals("Spieler1 erhält 0 Punkte", 0, spiel.getS1().getPunkte());
        assertEquals("Spieler2 erhält 78 Punkte", 78, spiel.getS2().getPunkte());
        assertTrue("Spieler2 ist dran", spiel.getS2().isIstdran());
        assertTrue(spiel.istSpielzuEnde(bummerl));
    }

    @Test
    public void ZugAuswerten_Punktepruefen_20erangesagt_S1Gewinner() throws WrongGameException {
        Spiel2 spiel = new Spiel2(0);

        while (spiel.getTrumpf() != "Pik") {
            spiel = new Spiel2(0);
        }

        spiel.getS1().Hand.removeAll(spiel.getS1().Hand);
        spiel.getS2().Hand.removeAll(spiel.getS2().Hand);

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

        spiel.Ansagen20er("Herz", spiel.getS1());

        Karte ausgespieltS1 = spiel.getS1().Hand.get(0);
        Karte ausgespieltS2 = spiel.getS2().Hand.get(0);

        spiel.Auspielen(spiel.getS1().Hand.get(0), spiel.getS1());
        spiel.Auspielen(spiel.getS2().Hand.get(0), spiel.getS2());

        //Zug1
        spiel.ZugAuswerten(ausgespieltS1, ausgespieltS2, 0);
        assertEquals("Spieler1 erhält 24 Punkte", 24, spiel.getS1().getPunkte());
        assertEquals("Spieler2 erhält 0 Punkte", 0, spiel.getS2().getPunkte());
        assertTrue("Spieler1 ist dran", spiel.getS1().isIstdran());
        assertTrue(!spiel.istSpielzuEnde(bummerl));

        //Zug2
        spiel.ZugAuswerten(herzdame, karodame, 0);
        spiel.getS1().Hand.remove(herzdame);
        spiel.getS2().Hand.remove(karodame);
        assertEquals("Spieler1 erhält 30 Punkte", 30, spiel.getS1().getPunkte());
        assertEquals("Spieler2 erhält 0 Punkte", 0, spiel.getS2().getPunkte());
        assertTrue("Spieler1 ist dran", spiel.getS1().isIstdran());
        assertTrue(!spiel.istSpielzuEnde(bummerl));

        //Zug3
        spiel.ZugAuswerten(herzkoenig, karokoenig, 0);
        spiel.getS1().Hand.remove(herzkoenig);
        spiel.getS2().Hand.remove(karokoenig);
        assertEquals("Spieler1 erhält 38 Punkte", 38, spiel.getS1().getPunkte());
        assertEquals("Spieler2 erhält 0 Punkte", 0, spiel.getS2().getPunkte());
        assertTrue("Spieler1 ist dran", spiel.getS1().isIstdran());
        assertTrue(!spiel.istSpielzuEnde(bummerl));


        //Zug4
        spiel.ZugAuswerten(herz10, karo10,0);
        spiel.getS1().Hand.remove(herz10);
        spiel.getS2().Hand.remove(karo10);
        assertEquals("Spieler1 erhält 58 Punkte", 58, spiel.getS1().getPunkte());
        assertEquals("Spieler2 erhält 0 Punkte", 0, spiel.getS2().getPunkte());
        assertTrue("Spieler1 ist dran", spiel.getS1().isIstdran());
        assertTrue(!spiel.istSpielzuEnde(bummerl));

        //Zug5
        spiel.ZugAuswerten(herzass, karoass,0);
        spiel.getS1().Hand.remove(herzass);
        spiel.getS2().Hand.remove(karoass);
        assertEquals("Spieler1 erhält 80 Punkte", 80, spiel.getS1().getPunkte());
        assertEquals("Spieler2 erhält 0 Punkte", 0, spiel.getS2().getPunkte());
        assertTrue("Spieler1 ist dran", spiel.getS1().isIstdran());
        assertTrue(spiel.istSpielzuEnde(bummerl));
    }

    @Test
    public void ZugAuswerten_Punktepruefen_20erangesagt_S2Gewinner() throws WrongGameException {
        Spiel2 spiel = new Spiel2(1);

        while (spiel.getTrumpf() != "Pik") {
            spiel = new Spiel2(1);
        }

        spiel.getS1().Hand.removeAll(spiel.getS1().Hand);
        spiel.getS2().Hand.removeAll(spiel.getS2().Hand);

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

        spiel.Ansagen20er("Karo", spiel.getS2());

        Karte ausgespieltS1 = spiel.getS1().Hand.get(0);
        Karte ausgespieltS2 = spiel.getS2().Hand.get(0);

        spiel.Auspielen(spiel.getS1().Hand.get(0), spiel.getS1());
        spiel.Auspielen(spiel.getS2().Hand.get(0), spiel.getS2());

        spiel.getS2().setIstdran(true);
        assertTrue("S2 beginnt", spiel.getS2().isIstdran());

        //Zug1
        spiel.ZugAuswerten(ausgespieltS1, ausgespieltS2, 1);
        assertEquals("Spieler1 erhält 0 Punkte", 0, spiel.getS1().getPunkte());
        assertEquals("Spieler2 erhält 24 Punkte", 24, spiel.getS2().getPunkte());
        assertTrue("Spieler2 ist dran", spiel.getS2().isIstdran());
        assertTrue(!spiel.istSpielzuEnde(bummerl));

        //Zug2
        spiel.ZugAuswerten(herzdame, karodame, 1);
        spiel.getS1().Hand.remove(herzdame);
        spiel.getS2().Hand.remove(karodame);
        assertEquals("Spieler1 erhält 0 Punkte", 0, spiel.getS1().getPunkte());
        assertEquals("Spieler2 erhält 30 Punkte", 30, spiel.getS2().getPunkte());
        assertTrue("Spieler2 ist dran", spiel.getS2().isIstdran());
        assertTrue(!spiel.istSpielzuEnde(bummerl));

        //Zug3
        spiel.ZugAuswerten(herzkoenig, karokoenig, 1);
        spiel.getS1().Hand.remove(herzkoenig);
        spiel.getS2().Hand.remove(karokoenig);
        assertEquals("Spieler1 erhält 0 Punkte", 0, spiel.getS1().getPunkte());
        assertEquals("Spieler2 erhält 38 Punkte", 38, spiel.getS2().getPunkte());
        assertTrue("Spieler2 ist dran", spiel.getS2().isIstdran());
        assertTrue(!spiel.istSpielzuEnde(bummerl));


        //Zug4
        spiel.ZugAuswerten(herz10, karo10, 1);
        spiel.getS1().Hand.remove(herz10);
        spiel.getS2().Hand.remove(karo10);
        assertEquals("Spieler1 erhält 0 Punkte", 0, spiel.getS1().getPunkte());
        assertEquals("Spieler2 erhält 58 Punkte", 58, spiel.getS2().getPunkte());
        assertTrue("Spieler2 ist dran", spiel.getS2().isIstdran());
        assertTrue(!spiel.istSpielzuEnde(bummerl));

        //Zug5
        spiel.ZugAuswerten(herzass, karoass,1);
        spiel.getS1().Hand.remove(herzass);
        spiel.getS2().Hand.remove(karoass);
        assertEquals("Spieler1 erhält 0 Punkte", 0, spiel.getS1().getPunkte());
        assertEquals("Spieler2 erhält 80 Punkte", 80, spiel.getS2().getPunkte());
        assertTrue("Spieler2 ist dran", spiel.getS2().isIstdran());
        assertTrue(spiel.istSpielzuEnde(bummerl));
    }

    @Test
    public void ZugAuswerten_KarteNachStichAufheben() throws WrongGameException {
        Spiel2 spiel = new Spiel2(0);

        while (spiel.getTrumpf() != "Herz") {
            spiel = new Spiel2(0);
        }

        spiel.getS1().Hand.removeAll(spiel.getS1().Hand);
        spiel.getS2().Hand.removeAll(spiel.getS2().Hand);

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

        assertEquals("Karten vor Stich", 5, spiel.getS1().Hand.size());
        assertEquals("Karten vor Stich", 5, spiel.getS2().Hand.size());

        Karte ausgespieltS1 = spiel.getS1().Hand.get(0);
        Karte ausgespieltS2 = spiel.getS2().Hand.get(0);

        spiel.Auspielen(spiel.getS1().Hand.get(0), spiel.getS1());
        spiel.Auspielen(spiel.getS2().Hand.get(0), spiel.getS2());

        //Zug1
        spiel.ZugAuswerten(ausgespieltS1, ausgespieltS2, 0);
        spiel.istSpielzuEnde(bummerl);
        assertEquals("Karten nach Stich", 5, spiel.getS1().Hand.size());
        assertEquals("Karten nach Stich", 5, spiel.getS2().Hand.size());
    }

    @Test
    public void ZugAuswerten_keine_KarteNachStichAufheben_zugedreht() throws WrongGameException {
        Spiel2 spiel = new Spiel2(0);

        while (spiel.getTrumpf() != "Herz") {
            spiel = new Spiel2(0);
        }

        spiel.getS1().Hand.removeAll(spiel.getS1().Hand);
        spiel.getS2().Hand.removeAll(spiel.getS2().Hand);

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

        spiel.Zudrehen(spiel.getS1());

        assertEquals("Karten vor Stich", 5, spiel.getS1().Hand.size());
        assertEquals("Karten vor Stich", 5, spiel.getS2().Hand.size());

        Karte ausgespieltS1 = spiel.getS1().Hand.get(0);
        Karte ausgespieltS2 = spiel.getS2().Hand.get(0);

        spiel.Auspielen(spiel.getS1().Hand.get(0), spiel.getS1());
        spiel.Auspielen(spiel.getS2().Hand.get(0), spiel.getS2());

        //Zug1
        spiel.ZugAuswerten(ausgespieltS1, ausgespieltS2, 0);
        spiel.istSpielzuEnde(bummerl);
        assertEquals("Karten nach Stich", 4, spiel.getS1().Hand.size());
        assertEquals("Karten nach Stich", 4, spiel.getS2().Hand.size());
    }

    @Test
    public void ZugAuswerten_S1Gewinner_nachStichAmZug() throws WrongGameException {
        Spiel2 spiel = new Spiel2(0);

        while (spiel.getTrumpf() != "Herz") {
            spiel = new Spiel2(0);
        }

        spiel.getS1().Hand.removeAll(spiel.getS1().Hand);
        spiel.getS2().Hand.removeAll(spiel.getS2().Hand);

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

        assertEquals("Karten vor Stich", 5, spiel.getS1().Hand.size());
        assertEquals("Karten vor Stich", 5, spiel.getS2().Hand.size());

        Karte ausgespieltS1 = spiel.getS1().Hand.get(0);
        Karte ausgespieltS2 = spiel.getS2().Hand.get(0);

        spiel.Auspielen(spiel.getS1().Hand.get(0), spiel.getS1());
        spiel.Auspielen(spiel.getS2().Hand.get(0), spiel.getS2());

        //Zug1
        spiel.ZugAuswerten(ausgespieltS1, ausgespieltS2, 0);
        spiel.istSpielzuEnde(bummerl);
        assertTrue("S1 (Gewinner) ist am Zug", spiel.getS1().isIstdran());
    }

    @Test
    public void ZugAuswerten_S2Gewinner_nachStichAmZug() throws WrongGameException {
        Spiel2 spiel = new Spiel2(0);

        while (spiel.getTrumpf() != "Karo") {
            spiel = new Spiel2(0);
        }

        spiel.getS1().Hand.removeAll(spiel.getS1().Hand);
        spiel.getS2().Hand.removeAll(spiel.getS2().Hand);

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

        assertEquals("Karten vor Stich", 5, spiel.getS1().Hand.size());
        assertEquals("Karten vor Stich", 5, spiel.getS2().Hand.size());

        Karte ausgespieltS1 = spiel.getS1().Hand.get(0);
        Karte ausgespieltS2 = spiel.getS2().Hand.get(0);

        spiel.Auspielen(spiel.getS1().Hand.get(0), spiel.getS1());
        spiel.Auspielen(spiel.getS2().Hand.get(0), spiel.getS2());

        //Zug1
        spiel.ZugAuswerten(ausgespieltS1, ausgespieltS2, 0);
        spiel.istSpielzuEnde(bummerl);
        assertTrue("S2 (Gewinner) ist am Zug", spiel.getS2().isIstdran());
    }
}