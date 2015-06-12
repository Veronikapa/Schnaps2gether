package appsolutegamesgmbh.schnaps2gether;

import org.junit.Before;
import org.junit.Test;

import appsolutegamesgmbh.schnaps2gether.DataStructure.Bummerl4;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Karte;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Rufspiel;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Spiel4;
import appsolutegamesgmbh.schnaps2gether.DataStructure.WrongGameException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by n15r2 on 27.05.15.
 */
public class Spiel4istSpielzuEndeRufspielTest {

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
    public void istSpielzuEnde_normal_GewinnerS3_S1_Gegner0Punkte() throws WrongGameException {
        Spiel4 spiel = new Spiel4(0);
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

        //Zug1
        spiel.ZugAuswerten(ausgespieltS1, ausgespieltS2, ausgespieltS3, ausgespieltS4);
        assertTrue(!spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 0", 0, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 0", 0, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 0", 0, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 0", 0, bummerl.getPunkteS3());
        assertEquals("Bummerl Punkte Spieler4: 0", 0, bummerl.getPunkteS4());
        assertEquals("Punkte Spieler3: 8", 8, spiel.getS3().getPunkte());
        assertTrue(spiel.getS3().isIstdran());
        //Zug2
        spiel.ZugAuswerten(spiel.getS1().Hand.get(0), spiel.getS2().Hand.get(0), spiel.getS3().Hand.get(0), spiel.getS4().Hand.get(0));
        spiel.getS1().Hand.remove(0);
        spiel.getS2().Hand.remove(0);
        spiel.getS3().Hand.remove(0);
        spiel.getS4().Hand.remove(0);
        assertTrue(!spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 0", 0, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 0", 0, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 0", 0, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 0", 0, bummerl.getPunkteS3());
        assertEquals("Bummerl Punkte Spieler4: 0", 0, bummerl.getPunkteS4());
        assertEquals("Punkte Spieler3: 20", 20, spiel.getS3().getPunkte());
        assertTrue(spiel.getS3().isIstdran());
        //Zug3
        spiel.ZugAuswerten(spiel.getS1().Hand.get(0), spiel.getS2().Hand.get(0), spiel.getS3().Hand.get(0), spiel.getS4().Hand.get(0));
        spiel.getS1().Hand.remove(0);
        spiel.getS2().Hand.remove(0);
        spiel.getS3().Hand.remove(0);
        spiel.getS4().Hand.remove(0);
        assertTrue(!spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 0", 0, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 0", 0, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 0", 0, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 0", 0, bummerl.getPunkteS3());
        assertEquals("Bummerl Punkte Spieler4: 0", 0, bummerl.getPunkteS4());
        assertEquals("Punkte Spieler3: 36", 36, spiel.getS3().getPunkte());
        assertTrue(spiel.getS3().isIstdran());
        //Zug4
        spiel.ZugAuswerten(spiel.getS1().Hand.get(0), spiel.getS2().Hand.get(0), spiel.getS3().Hand.get(0), spiel.getS4().Hand.get(0));
        spiel.getS1().Hand.remove(0);
        spiel.getS2().Hand.remove(0);
        spiel.getS3().Hand.remove(0);
        spiel.getS4().Hand.remove(0);
        assertTrue(spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 1", 1, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 3", 3, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 0", 0, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 3", 3, bummerl.getPunkteS3());
        assertEquals("Bummerl Punkte Spieler4: 0", 0, bummerl.getPunkteS4());
        assertEquals("Punkte Spieler3: 76", 76, spiel.getS3().getPunkte());
        assertTrue(spiel.getS3().isIstdran());
    }

    @Test
    public void istSpielzuEnde_normal_GewinnerS2_S4_Gegner0Punkte() throws WrongGameException {
        Spiel4 spiel = new Spiel4(0);
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


        //Zug1
        spiel.ZugAuswerten(ausgespieltS1, ausgespieltS2, ausgespieltS3, ausgespieltS4);
        assertTrue(!spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 0", 0, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 0", 0, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 0", 0, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 0", 0, bummerl.getPunkteS3());
        assertEquals("Bummerl Punkte Spieler4: 0", 0, bummerl.getPunkteS4());
        assertEquals("Punkte Spieler2: 8", 8, spiel.getS2().getPunkte());
        assertTrue(spiel.getS2().isIstdran());
        //Zug2
        spiel.ZugAuswerten(spiel.getS1().Hand.get(0), spiel.getS2().Hand.get(0), spiel.getS3().Hand.get(0), spiel.getS4().Hand.get(0));
        spiel.getS1().Hand.remove(0);
        spiel.getS2().Hand.remove(0);
        spiel.getS3().Hand.remove(0);
        spiel.getS4().Hand.remove(0);
        assertTrue(!spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 0", 0, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 0", 0, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 0", 0, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 0", 0, bummerl.getPunkteS3());
        assertEquals("Bummerl Punkte Spieler4: 0", 0, bummerl.getPunkteS4());
        assertEquals("Punkte Spieler2: 20", 20, spiel.getS2().getPunkte());
        assertTrue(spiel.getS2().isIstdran());
        //Zug3
        spiel.ZugAuswerten(spiel.getS1().Hand.get(0), spiel.getS2().Hand.get(0), spiel.getS3().Hand.get(0), spiel.getS4().Hand.get(0));
        spiel.getS1().Hand.remove(0);
        spiel.getS2().Hand.remove(0);
        spiel.getS3().Hand.remove(0);
        spiel.getS4().Hand.remove(0);
        assertTrue(!spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 0", 0, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 0", 0, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 0", 0, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 0", 0, bummerl.getPunkteS3());
        assertEquals("Bummerl Punkte Spieler4: 0", 0, bummerl.getPunkteS4());
        assertEquals("Punkte Spieler2: 36", 36, spiel.getS2().getPunkte());
        assertTrue(spiel.getS2().isIstdran());
        //Zug4
        spiel.ZugAuswerten(spiel.getS1().Hand.get(0), spiel.getS2().Hand.get(0), spiel.getS3().Hand.get(0), spiel.getS4().Hand.get(0));
        spiel.getS1().Hand.remove(0);
        spiel.getS2().Hand.remove(0);
        spiel.getS3().Hand.remove(0);
        spiel.getS4().Hand.remove(0);
        assertTrue(spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 1", 1, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 0", 0, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 3", 3, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 0", 0, bummerl.getPunkteS3());
        assertEquals("Bummerl Punkte Spieler4: 3", 3, bummerl.getPunkteS4());
        assertEquals("Punkte Spieler2: 76", 76, spiel.getS2().getPunkte());
        assertTrue(spiel.getS2().isIstdran());
    }

    @Test
    public void istSpielzuEnde_normal_GewinnerS2_S4_Gegner20Punkte() throws WrongGameException {
        Spiel4 spiel = new Spiel4(0);
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


        //Gegner 20 Punkte
        spiel.getS1().setPunkte(20);
        //Zug1
        spiel.ZugAuswerten(ausgespieltS1, ausgespieltS2, ausgespieltS3, ausgespieltS4);
        assertTrue(!spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 0", 0, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 0", 0, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 0", 0, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 0", 0, bummerl.getPunkteS3());
        assertEquals("Bummerl Punkte Spieler4: 0", 0, bummerl.getPunkteS4());
        assertEquals("Punkte Spieler2: 8", 8, spiel.getS2().getPunkte());
        assertTrue(spiel.getS2().isIstdran());
        //Zug2
        spiel.ZugAuswerten(spiel.getS1().Hand.get(0), spiel.getS2().Hand.get(0), spiel.getS3().Hand.get(0), spiel.getS4().Hand.get(0));
        spiel.getS1().Hand.remove(0);
        spiel.getS2().Hand.remove(0);
        spiel.getS3().Hand.remove(0);
        spiel.getS4().Hand.remove(0);
        assertTrue(!spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 0", 0, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 0", 0, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 0", 0, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 0", 0, bummerl.getPunkteS3());
        assertEquals("Bummerl Punkte Spieler4: 0", 0, bummerl.getPunkteS4());
        assertEquals("Punkte Spieler2: 20", 20, spiel.getS2().getPunkte());
        assertTrue(spiel.getS2().isIstdran());
        //Zug3
        spiel.ZugAuswerten(spiel.getS1().Hand.get(0), spiel.getS2().Hand.get(0), spiel.getS3().Hand.get(0), spiel.getS4().Hand.get(0));
        spiel.getS1().Hand.remove(0);
        spiel.getS2().Hand.remove(0);
        spiel.getS3().Hand.remove(0);
        spiel.getS4().Hand.remove(0);
        assertTrue(!spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 0", 0, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 0", 0, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 0", 0, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 0", 0, bummerl.getPunkteS3());
        assertEquals("Bummerl Punkte Spieler4: 0", 0, bummerl.getPunkteS4());
        assertEquals("Punkte Spieler2: 36", 36, spiel.getS2().getPunkte());
        assertTrue(spiel.getS2().isIstdran());
        //Zug4
        spiel.ZugAuswerten(spiel.getS1().Hand.get(0), spiel.getS2().Hand.get(0), spiel.getS3().Hand.get(0), spiel.getS4().Hand.get(0));
        spiel.getS1().Hand.remove(0);
        spiel.getS2().Hand.remove(0);
        spiel.getS3().Hand.remove(0);
        spiel.getS4().Hand.remove(0);
        assertTrue(spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 1", 1, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 0", 0, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 2", 2, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 0", 0, bummerl.getPunkteS3());
        assertEquals("Bummerl Punkte Spieler4: 2", 2, bummerl.getPunkteS4());
        assertEquals("Punkte Spieler2: 76", 76, spiel.getS2().getPunkte());
        assertTrue(spiel.getS2().isIstdran());
    }

    @Test
    public void istSpielzuEnde_normal_GewinnerS2_S4_Gegner50Punkte() throws WrongGameException {
        Spiel4 spiel = new Spiel4(0);
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


        //Gegner 50 Punkte
        spiel.getS1().setPunkte(50);
        //Zug1
        spiel.ZugAuswerten(ausgespieltS1, ausgespieltS2, ausgespieltS3, ausgespieltS4);
        assertTrue(!spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 0", 0, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 0", 0, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 0", 0, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 0", 0, bummerl.getPunkteS3());
        assertEquals("Bummerl Punkte Spieler4: 0", 0, bummerl.getPunkteS4());
        assertEquals("Punkte Spieler2: 8", 8, spiel.getS2().getPunkte());
        assertTrue(spiel.getS2().isIstdran());
        //Zug2
        spiel.ZugAuswerten(spiel.getS1().Hand.get(0), spiel.getS2().Hand.get(0), spiel.getS3().Hand.get(0), spiel.getS4().Hand.get(0));
        spiel.getS1().Hand.remove(0);
        spiel.getS2().Hand.remove(0);
        spiel.getS3().Hand.remove(0);
        spiel.getS4().Hand.remove(0);
        assertTrue(!spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 0", 0, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 0", 0, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 0", 0, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 0", 0, bummerl.getPunkteS3());
        assertEquals("Bummerl Punkte Spieler4: 0", 0, bummerl.getPunkteS4());
        assertEquals("Punkte Spieler2: 20", 20, spiel.getS2().getPunkte());
        assertTrue(spiel.getS2().isIstdran());
        //Zug3
        spiel.ZugAuswerten(spiel.getS1().Hand.get(0), spiel.getS2().Hand.get(0), spiel.getS3().Hand.get(0), spiel.getS4().Hand.get(0));
        spiel.getS1().Hand.remove(0);
        spiel.getS2().Hand.remove(0);
        spiel.getS3().Hand.remove(0);
        spiel.getS4().Hand.remove(0);
        assertTrue(!spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 0", 0, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 0", 0, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 0", 0, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 0", 0, bummerl.getPunkteS3());
        assertEquals("Bummerl Punkte Spieler4: 0", 0, bummerl.getPunkteS4());
        assertEquals("Punkte Spieler2: 36", 36, spiel.getS2().getPunkte());
        assertTrue(spiel.getS2().isIstdran());
        //Zug4
        spiel.ZugAuswerten(spiel.getS1().Hand.get(0), spiel.getS2().Hand.get(0), spiel.getS3().Hand.get(0), spiel.getS4().Hand.get(0));
        spiel.getS1().Hand.remove(0);
        spiel.getS2().Hand.remove(0);
        spiel.getS3().Hand.remove(0);
        spiel.getS4().Hand.remove(0);
        assertTrue(spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 1", 1, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 0", 0, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 1", 1, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 0", 0, bummerl.getPunkteS3());
        assertEquals("Bummerl Punkte Spieler4: 1", 1, bummerl.getPunkteS4());
        assertEquals("Punkte Spieler2: 76", 76, spiel.getS2().getPunkte());
        assertTrue(spiel.getS2().isIstdran());
    }

    @Test
    public void istSpielzuEnde_normal_GewinnerS3_S1_Gegner10Punkte() throws WrongGameException {
        Spiel4 spiel = new Spiel4(0);
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

        //Gegner 10 Punkte
        spiel.getS2().setPunkte(10);

        //Zug1
        spiel.ZugAuswerten(ausgespieltS1, ausgespieltS2, ausgespieltS3, ausgespieltS4);
        assertTrue(!spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 0", 0, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 0", 0, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 0", 0, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 0", 0, bummerl.getPunkteS3());
        assertEquals("Bummerl Punkte Spieler4: 0", 0, bummerl.getPunkteS4());
        assertEquals("Punkte Spieler3: 8", 8, spiel.getS3().getPunkte());
        assertTrue(spiel.getS3().isIstdran());
        //Zug2
        spiel.ZugAuswerten(spiel.getS1().Hand.get(0), spiel.getS2().Hand.get(0), spiel.getS3().Hand.get(0), spiel.getS4().Hand.get(0));
        spiel.getS1().Hand.remove(0);
        spiel.getS2().Hand.remove(0);
        spiel.getS3().Hand.remove(0);
        spiel.getS4().Hand.remove(0);
        assertTrue(!spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 0", 0, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 0", 0, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 0", 0, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 0", 0, bummerl.getPunkteS3());
        assertEquals("Bummerl Punkte Spieler4: 0", 0, bummerl.getPunkteS4());
        assertEquals("Punkte Spieler3: 20", 20, spiel.getS3().getPunkte());
        assertTrue(spiel.getS3().isIstdran());
        //Zug3
        spiel.ZugAuswerten(spiel.getS1().Hand.get(0), spiel.getS2().Hand.get(0), spiel.getS3().Hand.get(0), spiel.getS4().Hand.get(0));
        spiel.getS1().Hand.remove(0);
        spiel.getS2().Hand.remove(0);
        spiel.getS3().Hand.remove(0);
        spiel.getS4().Hand.remove(0);
        assertTrue(!spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 0", 0, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 0", 0, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 0", 0, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 0", 0, bummerl.getPunkteS3());
        assertEquals("Bummerl Punkte Spieler4: 0", 0, bummerl.getPunkteS4());
        assertEquals("Punkte Spieler3: 36", 36, spiel.getS3().getPunkte());
        assertTrue(spiel.getS3().isIstdran());
        //Zug4
        spiel.ZugAuswerten(spiel.getS1().Hand.get(0), spiel.getS2().Hand.get(0), spiel.getS3().Hand.get(0), spiel.getS4().Hand.get(0));
        spiel.getS1().Hand.remove(0);
        spiel.getS2().Hand.remove(0);
        spiel.getS3().Hand.remove(0);
        spiel.getS4().Hand.remove(0);
        assertTrue(spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 1", 1, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 2", 2, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 0", 0, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 2", 2, bummerl.getPunkteS3());
        assertEquals("Bummerl Punkte Spieler4: 0", 0, bummerl.getPunkteS4());
        assertEquals("Punkte Spieler3: 76", 76, spiel.getS3().getPunkte());
        assertTrue(spiel.getS3().isIstdran());
    }

    @Test
    public void istSpielzuEnde_normal_GewinnerS3_S1_Gegner35Punkte() throws WrongGameException {
        Spiel4 spiel = new Spiel4(0);
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

        //Gegner 35 Punkte
        spiel.getS2().setPunkte(35);

        //Zug1
        spiel.ZugAuswerten(ausgespieltS1, ausgespieltS2, ausgespieltS3, ausgespieltS4);
        assertTrue(!spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 0", 0, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 0", 0, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 0", 0, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 0", 0, bummerl.getPunkteS3());
        assertEquals("Bummerl Punkte Spieler4: 0", 0, bummerl.getPunkteS4());
        assertEquals("Punkte Spieler3: 8", 8, spiel.getS3().getPunkte());
        assertTrue(spiel.getS3().isIstdran());
        //Zug2
        spiel.ZugAuswerten(spiel.getS1().Hand.get(0), spiel.getS2().Hand.get(0), spiel.getS3().Hand.get(0), spiel.getS4().Hand.get(0));
        spiel.getS1().Hand.remove(0);
        spiel.getS2().Hand.remove(0);
        spiel.getS3().Hand.remove(0);
        spiel.getS4().Hand.remove(0);
        assertTrue(!spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 0", 0, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 0", 0, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 0", 0, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 0", 0, bummerl.getPunkteS3());
        assertEquals("Bummerl Punkte Spieler4: 0", 0, bummerl.getPunkteS4());
        assertEquals("Punkte Spieler3: 20", 20, spiel.getS3().getPunkte());
        assertTrue(spiel.getS3().isIstdran());
        //Zug3
        spiel.ZugAuswerten(spiel.getS1().Hand.get(0), spiel.getS2().Hand.get(0), spiel.getS3().Hand.get(0), spiel.getS4().Hand.get(0));
        spiel.getS1().Hand.remove(0);
        spiel.getS2().Hand.remove(0);
        spiel.getS3().Hand.remove(0);
        spiel.getS4().Hand.remove(0);
        assertTrue(!spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 0", 0, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 0", 0, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 0", 0, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 0", 0, bummerl.getPunkteS3());
        assertEquals("Bummerl Punkte Spieler4: 0", 0, bummerl.getPunkteS4());
        assertEquals("Punkte Spieler3: 36", 36, spiel.getS3().getPunkte());
        assertTrue(spiel.getS3().isIstdran());
        //Zug4
        spiel.ZugAuswerten(spiel.getS1().Hand.get(0), spiel.getS2().Hand.get(0), spiel.getS3().Hand.get(0), spiel.getS4().Hand.get(0));
        spiel.getS1().Hand.remove(0);
        spiel.getS2().Hand.remove(0);
        spiel.getS3().Hand.remove(0);
        spiel.getS4().Hand.remove(0);
        assertTrue(spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 1", 1, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 1", 1, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 0", 0, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 1", 1, bummerl.getPunkteS3());
        assertEquals("Bummerl Punkte Spieler4: 0", 0, bummerl.getPunkteS4());
        assertEquals("Punkte Spieler3: 76", 76, spiel.getS3().getPunkte());
        assertTrue(spiel.getS3().isIstdran());
    }

    @Test
    public void istSpielzuEnde_Schnapser_VerliererS3_S1() throws WrongGameException {
        Spiel4 spiel = new Spiel4(0);
        spiel.Trumpfansagen("Pik", 0);
        spiel.SpielAnsagen(new Rufspiel("Schnapser"), spiel.getS3());
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

        //Gegner 0 Punkte


        //Zug1
        spiel.ZugAuswerten(ausgespieltS1, ausgespieltS2, ausgespieltS3, ausgespieltS4);
        assertEquals("Handkarten 4", 4, spiel.getS1().Hand.size());
        assertTrue(!spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 0", 0, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 0", 0, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 0", 0, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 0", 0, bummerl.getPunkteS3());
        assertEquals("Bummerl Punkte Spieler4: 0", 0, bummerl.getPunkteS4());
        assertEquals("Punkte Spieler3: 8", 8, spiel.getS3().getPunkte());
        assertEquals("Punkte Spieler1: ", 0, spiel.getS1().getPunkte());
        assertEquals("Punkte Spieler2: ", 0, spiel.getS2().getPunkte());
        assertEquals("Punkte Spieler4: ", 0, spiel.getS4().getPunkte());
        assertTrue(spiel.getS3().isIstdran());
        //Zug2
        spiel.ZugAuswerten(spiel.getS1().Hand.get(0), spiel.getS2().Hand.get(0), spiel.getS3().Hand.get(0), spiel.getS4().Hand.get(0));
        spiel.getS1().Hand.remove(0);
        spiel.getS2().Hand.remove(0);
        spiel.getS3().Hand.remove(0);
        spiel.getS4().Hand.remove(0);
        assertTrue(!spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 0", 0, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 0", 0, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 0", 0, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 0", 0, bummerl.getPunkteS3());
        assertEquals("Bummerl Punkte Spieler4: 0", 0, bummerl.getPunkteS4());
        assertEquals("Punkte Spieler3: 20", 20, spiel.getS3().getPunkte());
        assertTrue(spiel.getS3().isIstdran());
        //Zug3
        spiel.ZugAuswerten(spiel.getS1().Hand.get(0), spiel.getS2().Hand.get(0), spiel.getS3().Hand.get(0), spiel.getS4().Hand.get(0));
        spiel.getS1().Hand.remove(0);
        spiel.getS2().Hand.remove(0);
        spiel.getS3().Hand.remove(0);
        spiel.getS4().Hand.remove(0);
        assertTrue(spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 1", 1, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 0", 0, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 6", 6, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 0", 0, bummerl.getPunkteS3());
        assertEquals("Bummerl Punkte Spieler4: 6", 6, bummerl.getPunkteS4());
        assertEquals("Punkte Spieler3: 36", 36, spiel.getS3().getPunkte());
        assertTrue(spiel.getS3().isIstdran());
    }

    @Test
    public void istSpielzuEnde_Schnapser_GewinnerS3_S1_Gegner0Punkte() throws WrongGameException {
        Spiel4 spiel = new Spiel4(0);
        spiel.Trumpfansagen("Pik", 0);
        spiel.SpielAnsagen(new Rufspiel("Schnapser"), spiel.getS3());
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

        Karte ausgespieltS1 = spiel.getS1().Hand.get(2);
        Karte ausgespieltS2 = spiel.getS2().Hand.get(2);
        Karte ausgespieltS3 = spiel.getS3().Hand.get(2);
        Karte ausgespieltS4 = spiel.getS4().Hand.get(2);

        spiel.Auspielen(spiel.getS1().Hand.get(2), spiel.getS1());
        spiel.Auspielen(spiel.getS2().Hand.get(2), spiel.getS2());
        spiel.Auspielen(spiel.getS3().Hand.get(2), spiel.getS3());
        spiel.Auspielen(spiel.getS4().Hand.get(2), spiel.getS4());

        //Gegner 0 Punkte


        //Zug1
        spiel.ZugAuswerten(ausgespieltS1, ausgespieltS2, ausgespieltS3, ausgespieltS4);
        assertEquals("Handkarten 4", 4, spiel.getS1().Hand.size());
        assertTrue(!spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 0", 0, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 0", 0, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 0", 0, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 0", 0, bummerl.getPunkteS3());
        assertEquals("Bummerl Punkte Spieler4: 0", 0, bummerl.getPunkteS4());
        assertEquals("Punkte Spieler3: 16", 16, spiel.getS3().getPunkte());
        assertEquals("Punkte Spieler1: ", 0, spiel.getS1().getPunkte());
        assertEquals("Punkte Spieler2: ", 0, spiel.getS2().getPunkte());
        assertEquals("Punkte Spieler4: ", 0, spiel.getS4().getPunkte());
        assertTrue(spiel.getS3().isIstdran());
        //Zug2
        spiel.ZugAuswerten(spiel.getS1().Hand.get(2), spiel.getS2().Hand.get(2), spiel.getS3().Hand.get(2), spiel.getS4().Hand.get(2));
        spiel.getS1().Hand.remove(2);
        spiel.getS2().Hand.remove(2);
        spiel.getS3().Hand.remove(2);
        spiel.getS4().Hand.remove(2);
        assertTrue(!spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 0", 0, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 0", 0, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 0", 0, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 0", 0, bummerl.getPunkteS3());
        assertEquals("Bummerl Punkte Spieler4: 0", 0, bummerl.getPunkteS4());
        assertEquals("Punkte Spieler3: 56", 56, spiel.getS3().getPunkte());
        assertTrue(spiel.getS3().isIstdran());
        //Zug3
        spiel.ZugAuswerten(spiel.getS1().Hand.get(2), spiel.getS2().Hand.get(2), spiel.getS3().Hand.get(2), spiel.getS4().Hand.get(2));
        spiel.getS1().Hand.remove(2);
        spiel.getS2().Hand.remove(2);
        spiel.getS3().Hand.remove(2);
        spiel.getS4().Hand.remove(2);
        assertTrue(spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 1", 1, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 6", 6, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 0", 0, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 6", 6, bummerl.getPunkteS3());
        assertEquals("Bummerl Punkte Spieler4: 0", 0, bummerl.getPunkteS4());
        assertTrue(spiel.getS3().isIstdran());
    }

    @Test
    public void istSpielzuEnde_Schnapser_GewinnerS2_S4_Gegner0Punkte() throws WrongGameException {
        Spiel4 spiel = new Spiel4(0);
        spiel.Trumpfansagen("Karo", 0);
        spiel.SpielAnsagen(new Rufspiel("Schnapser"), spiel.getS2());
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

        Karte ausgespieltS1 = spiel.getS1().Hand.get(2);
        Karte ausgespieltS2 = spiel.getS2().Hand.get(2);
        Karte ausgespieltS3 = spiel.getS3().Hand.get(2);
        Karte ausgespieltS4 = spiel.getS4().Hand.get(2);

        spiel.Auspielen(spiel.getS1().Hand.get(2), spiel.getS1());
        spiel.Auspielen(spiel.getS2().Hand.get(2), spiel.getS2());
        spiel.Auspielen(spiel.getS3().Hand.get(2), spiel.getS3());
        spiel.Auspielen(spiel.getS4().Hand.get(2), spiel.getS4());

        //Gegner 0 Punkte


        //Zug1
        spiel.ZugAuswerten(ausgespieltS1, ausgespieltS2, ausgespieltS3, ausgespieltS4);
        assertEquals("Handkarten 4", 4, spiel.getS1().Hand.size());
        assertTrue(!spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 0", 0, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 0", 0, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 0", 0, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 0", 0, bummerl.getPunkteS3());
        assertEquals("Bummerl Punkte Spieler4: 0", 0, bummerl.getPunkteS4());
        assertEquals("Punkte Spieler2: 16", 16, spiel.getS2().getPunkte());
        assertEquals("Punkte Spieler1: ", 0, spiel.getS1().getPunkte());
        assertEquals("Punkte Spieler4: ", 0, spiel.getS4().getPunkte());
        assertTrue(spiel.getS2().isIstdran());
        //Zug2
        spiel.ZugAuswerten(spiel.getS1().Hand.get(2), spiel.getS2().Hand.get(2), spiel.getS3().Hand.get(2), spiel.getS4().Hand.get(2));
        spiel.getS1().Hand.remove(2);
        spiel.getS2().Hand.remove(2);
        spiel.getS3().Hand.remove(2);
        spiel.getS4().Hand.remove(2);
        assertTrue(!spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 0", 0, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 0", 0, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 0", 0, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 0", 0, bummerl.getPunkteS3());
        assertEquals("Bummerl Punkte Spieler4: 0", 0, bummerl.getPunkteS4());
        assertEquals("Punkte Spieler2: 56", 56, spiel.getS2().getPunkte());
        assertTrue(spiel.getS2().isIstdran());
        //Zug3
        spiel.ZugAuswerten(spiel.getS1().Hand.get(2), spiel.getS2().Hand.get(2), spiel.getS3().Hand.get(2), spiel.getS4().Hand.get(2));
        spiel.getS1().Hand.remove(2);
        spiel.getS2().Hand.remove(2);
        spiel.getS3().Hand.remove(2);
        spiel.getS4().Hand.remove(2);
        assertTrue(spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 1", 1, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 0", 0, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 6", 6, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 0", 0, bummerl.getPunkteS3());
        assertEquals("Bummerl Punkte Spieler4: 6", 6, bummerl.getPunkteS4());
        assertTrue(spiel.getS2().isIstdran());
    }

    @Test
    public void istSpielzuEnde_Schnapser_VerliererS2_S4_Gegner0Punkte() throws WrongGameException {
        Spiel4 spiel = new Spiel4(0);
        spiel.Trumpfansagen("Karo", 0);
        spiel.SpielAnsagen(new Rufspiel("Schnapser"), spiel.getS2());
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

        //Gegner 0 Punkte


        //Zug1
        spiel.ZugAuswerten(ausgespieltS1, ausgespieltS2, ausgespieltS3, ausgespieltS4);
        assertEquals("Handkarten 4", 4, spiel.getS1().Hand.size());
        assertTrue(!spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 0", 0, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 0", 0, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 0", 0, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 0", 0, bummerl.getPunkteS3());
        assertEquals("Bummerl Punkte Spieler4: 0", 0, bummerl.getPunkteS4());
        assertEquals("Punkte Spieler2: 8", 8, spiel.getS2().getPunkte());
        assertEquals("Punkte Spieler1: ", 0, spiel.getS1().getPunkte());
        assertEquals("Punkte Spieler4: ", 0, spiel.getS4().getPunkte());
        assertTrue(spiel.getS2().isIstdran());
        //Zug2
        spiel.ZugAuswerten(spiel.getS1().Hand.get(0), spiel.getS2().Hand.get(0), spiel.getS3().Hand.get(0), spiel.getS4().Hand.get(0));
        spiel.getS1().Hand.remove(0);
        spiel.getS2().Hand.remove(0);
        spiel.getS3().Hand.remove(0);
        spiel.getS4().Hand.remove(0);
        assertTrue(!spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 0", 0, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 0", 0, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 0", 0, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 0", 0, bummerl.getPunkteS3());
        assertEquals("Bummerl Punkte Spieler4: 0", 0, bummerl.getPunkteS4());
        assertEquals("Punkte Spieler2: 20", 20, spiel.getS2().getPunkte());
        assertTrue(spiel.getS2().isIstdran());
        //Zug3
        spiel.ZugAuswerten(spiel.getS1().Hand.get(0), spiel.getS2().Hand.get(0), spiel.getS3().Hand.get(0), spiel.getS4().Hand.get(0));
        spiel.getS1().Hand.remove(0);
        spiel.getS2().Hand.remove(0);
        spiel.getS3().Hand.remove(0);
        spiel.getS4().Hand.remove(0);
        assertTrue(spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 1", 1, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 6", 6, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 0", 0, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 6", 6, bummerl.getPunkteS3());
        assertEquals("Bummerl Punkte Spieler4: 0", 0, bummerl.getPunkteS4());
        assertEquals("Punkte Spieler2: 36", 36, spiel.getS2().getPunkte());
        assertTrue(spiel.getS2().isIstdran());
    }

    @Test
    public void istSpielzuEnde_Kontraschnapser_VerliererS3_S1() throws WrongGameException {
        Spiel4 spiel = new Spiel4(0);
        spiel.Trumpfansagen("Pik", 0);
        spiel.SpielAnsagen(new Rufspiel("Schnapser"), spiel.getS2());
        spiel.SpielAnsagen(new Rufspiel("Kontraschnapser"), spiel.getS3());
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

        //Gegner 0 Punkte


        //Zug1
        spiel.ZugAuswerten(ausgespieltS1, ausgespieltS2, ausgespieltS3, ausgespieltS4);
        assertEquals("Handkarten 4", 4, spiel.getS1().Hand.size());
        assertTrue(!spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 0", 0, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 0", 0, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 0", 0, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 0", 0, bummerl.getPunkteS3());
        assertEquals("Bummerl Punkte Spieler4: 0", 0, bummerl.getPunkteS4());
        assertEquals("Punkte Spieler3: 8", 8, spiel.getS3().getPunkte());
        assertEquals("Punkte Spieler1: ", 0, spiel.getS1().getPunkte());
        assertEquals("Punkte Spieler2: ", 0, spiel.getS2().getPunkte());
        assertEquals("Punkte Spieler4: ", 0, spiel.getS4().getPunkte());
        assertTrue(spiel.getS3().isIstdran());
        //Zug2
        spiel.ZugAuswerten(spiel.getS1().Hand.get(0), spiel.getS2().Hand.get(0), spiel.getS3().Hand.get(0), spiel.getS4().Hand.get(0));
        spiel.getS1().Hand.remove(0);
        spiel.getS2().Hand.remove(0);
        spiel.getS3().Hand.remove(0);
        spiel.getS4().Hand.remove(0);
        assertTrue(!spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 0", 0, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 0", 0, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 0", 0, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 0", 0, bummerl.getPunkteS3());
        assertEquals("Bummerl Punkte Spieler4: 0", 0, bummerl.getPunkteS4());
        assertEquals("Punkte Spieler3: 20", 20, spiel.getS3().getPunkte());
        assertTrue(spiel.getS3().isIstdran());
        //Zug3
        spiel.ZugAuswerten(spiel.getS1().Hand.get(0), spiel.getS2().Hand.get(0), spiel.getS3().Hand.get(0), spiel.getS4().Hand.get(0));
        spiel.getS1().Hand.remove(0);
        spiel.getS2().Hand.remove(0);
        spiel.getS3().Hand.remove(0);
        spiel.getS4().Hand.remove(0);
        assertTrue(spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 1", 1, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 0", 0, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 12", 12, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 0", 0, bummerl.getPunkteS3());
        assertEquals("Bummerl Punkte Spieler4: 12", 12, bummerl.getPunkteS4());
        assertEquals("Punkte Spieler3: 36", 36, spiel.getS3().getPunkte());
        assertTrue(spiel.getS3().isIstdran());
    }

    @Test
    public void istSpielzuEnde_Kontraschnapser_GewinnerS3_S1_Gegner0Punkte() throws WrongGameException {
        Spiel4 spiel = new Spiel4(0);
        spiel.Trumpfansagen("Pik", 0);
        spiel.SpielAnsagen(new Rufspiel("Schnapser"), spiel.getS2());
        spiel.SpielAnsagen(new Rufspiel("Kontraschnapser"), spiel.getS3());
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

        Karte ausgespieltS1 = spiel.getS1().Hand.get(2);
        Karte ausgespieltS2 = spiel.getS2().Hand.get(2);
        Karte ausgespieltS3 = spiel.getS3().Hand.get(2);
        Karte ausgespieltS4 = spiel.getS4().Hand.get(2);

        spiel.Auspielen(spiel.getS1().Hand.get(2), spiel.getS1());
        spiel.Auspielen(spiel.getS2().Hand.get(2), spiel.getS2());
        spiel.Auspielen(spiel.getS3().Hand.get(2), spiel.getS3());
        spiel.Auspielen(spiel.getS4().Hand.get(2), spiel.getS4());

        //Gegner 0 Punkte


        //Zug1
        spiel.ZugAuswerten(ausgespieltS1, ausgespieltS2, ausgespieltS3, ausgespieltS4);
        assertEquals("Handkarten 4", 4, spiel.getS1().Hand.size());
        assertTrue(!spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 0", 0, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 0", 0, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 0", 0, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 0", 0, bummerl.getPunkteS3());
        assertEquals("Bummerl Punkte Spieler4: 0", 0, bummerl.getPunkteS4());
        assertEquals("Punkte Spieler3: 16", 16, spiel.getS3().getPunkte());
        assertEquals("Punkte Spieler1: ", 0, spiel.getS1().getPunkte());
        assertEquals("Punkte Spieler2: ", 0, spiel.getS2().getPunkte());
        assertEquals("Punkte Spieler4: ", 0, spiel.getS4().getPunkte());
        assertTrue(spiel.getS3().isIstdran());
        //Zug2
        spiel.ZugAuswerten(spiel.getS1().Hand.get(2), spiel.getS2().Hand.get(2), spiel.getS3().Hand.get(2), spiel.getS4().Hand.get(2));
        spiel.getS1().Hand.remove(2);
        spiel.getS2().Hand.remove(2);
        spiel.getS3().Hand.remove(2);
        spiel.getS4().Hand.remove(2);
        assertTrue(!spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 0", 0, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 0", 0, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 0", 0, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 0", 0, bummerl.getPunkteS3());
        assertEquals("Bummerl Punkte Spieler4: 0", 0, bummerl.getPunkteS4());
        assertEquals("Punkte Spieler3: 56", 56, spiel.getS3().getPunkte());
        assertTrue(spiel.getS3().isIstdran());
        //Zug3
        spiel.ZugAuswerten(spiel.getS1().Hand.get(2), spiel.getS2().Hand.get(2), spiel.getS3().Hand.get(2), spiel.getS4().Hand.get(2));
        spiel.getS1().Hand.remove(2);
        spiel.getS2().Hand.remove(2);
        spiel.getS3().Hand.remove(2);
        spiel.getS4().Hand.remove(2);
        assertTrue(spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 1", 1, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 12", 12, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 0", 0, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 12", 12, bummerl.getPunkteS3());
        assertEquals("Bummerl Punkte Spieler4: 0", 0, bummerl.getPunkteS4());
        assertTrue(spiel.getS3().isIstdran());
    }

    @Test
    public void istSpielzuEnde_Kontraschnapser_GewinnerS2_S4_Gegner0Punkte() throws WrongGameException {
        Spiel4 spiel = new Spiel4(0);
        spiel.Trumpfansagen("Karo", 0);
        spiel.SpielAnsagen(new Rufspiel("Schnapser"), spiel.getS1());
        spiel.SpielAnsagen(new Rufspiel("Kontraschnapser"), spiel.getS2());
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

        Karte ausgespieltS1 = spiel.getS1().Hand.get(2);
        Karte ausgespieltS2 = spiel.getS2().Hand.get(2);
        Karte ausgespieltS3 = spiel.getS3().Hand.get(2);
        Karte ausgespieltS4 = spiel.getS4().Hand.get(2);

        spiel.Auspielen(spiel.getS1().Hand.get(2), spiel.getS1());
        spiel.Auspielen(spiel.getS2().Hand.get(2), spiel.getS2());
        spiel.Auspielen(spiel.getS3().Hand.get(2), spiel.getS3());
        spiel.Auspielen(spiel.getS4().Hand.get(2), spiel.getS4());

        //Gegner 0 Punkte


        //Zug1
        spiel.ZugAuswerten(ausgespieltS1, ausgespieltS2, ausgespieltS3, ausgespieltS4);
        assertEquals("Handkarten 4", 4, spiel.getS1().Hand.size());
        assertTrue(!spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 0", 0, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 0", 0, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 0", 0, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 0", 0, bummerl.getPunkteS3());
        assertEquals("Bummerl Punkte Spieler4: 0", 0, bummerl.getPunkteS4());
        assertEquals("Punkte Spieler2: 16", 16, spiel.getS2().getPunkte());
        assertEquals("Punkte Spieler1: ", 0, spiel.getS1().getPunkte());
        assertEquals("Punkte Spieler4: ", 0, spiel.getS4().getPunkte());
        assertTrue(spiel.getS2().isIstdran());
        //Zug2
        spiel.ZugAuswerten(spiel.getS1().Hand.get(2), spiel.getS2().Hand.get(2), spiel.getS3().Hand.get(2), spiel.getS4().Hand.get(2));
        spiel.getS1().Hand.remove(2);
        spiel.getS2().Hand.remove(2);
        spiel.getS3().Hand.remove(2);
        spiel.getS4().Hand.remove(2);
        assertTrue(!spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 0", 0, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 0", 0, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 0", 0, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 0", 0, bummerl.getPunkteS3());
        assertEquals("Bummerl Punkte Spieler4: 0", 0, bummerl.getPunkteS4());
        assertEquals("Punkte Spieler2: 56", 56, spiel.getS2().getPunkte());
        assertTrue(spiel.getS2().isIstdran());
        //Zug3
        spiel.ZugAuswerten(spiel.getS1().Hand.get(2), spiel.getS2().Hand.get(2), spiel.getS3().Hand.get(2), spiel.getS4().Hand.get(2));
        spiel.getS1().Hand.remove(2);
        spiel.getS2().Hand.remove(2);
        spiel.getS3().Hand.remove(2);
        spiel.getS4().Hand.remove(2);
        assertTrue(spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 1", 1, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 0", 0, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 12", 12, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 0", 0, bummerl.getPunkteS3());
        assertEquals("Bummerl Punkte Spieler4: 12", 12, bummerl.getPunkteS4());
        assertTrue(spiel.getS2().isIstdran());
    }

    @Test
    public void istSpielzuEnde_Kontraschnapser_VerliererS2_S4_Gegner0Punkte() throws WrongGameException {
        Spiel4 spiel = new Spiel4(0);
        spiel.Trumpfansagen("Karo", 0);
        spiel.SpielAnsagen(new Rufspiel("Schnapser"), spiel.getS1());
        spiel.SpielAnsagen(new Rufspiel("Kontraschnapser"), spiel.getS2());
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

        //Gegner 0 Punkte


        //Zug1
        spiel.ZugAuswerten(ausgespieltS1, ausgespieltS2, ausgespieltS3, ausgespieltS4);
        assertEquals("Handkarten 4", 4, spiel.getS1().Hand.size());
        assertTrue(!spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 0", 0, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 0", 0, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 0", 0, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 0", 0, bummerl.getPunkteS3());
        assertEquals("Bummerl Punkte Spieler4: 0", 0, bummerl.getPunkteS4());
        assertEquals("Punkte Spieler2: 8", 8, spiel.getS2().getPunkte());
        assertEquals("Punkte Spieler1: ", 0, spiel.getS1().getPunkte());
        assertEquals("Punkte Spieler4: ", 0, spiel.getS4().getPunkte());
        assertTrue(spiel.getS2().isIstdran());
        //Zug2
        spiel.ZugAuswerten(spiel.getS1().Hand.get(0), spiel.getS2().Hand.get(0), spiel.getS3().Hand.get(0), spiel.getS4().Hand.get(0));
        spiel.getS1().Hand.remove(0);
        spiel.getS2().Hand.remove(0);
        spiel.getS3().Hand.remove(0);
        spiel.getS4().Hand.remove(0);
        assertTrue(!spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 0", 0, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 0", 0, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 0", 0, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 0", 0, bummerl.getPunkteS3());
        assertEquals("Bummerl Punkte Spieler4: 0", 0, bummerl.getPunkteS4());
        assertEquals("Punkte Spieler2: 20", 20, spiel.getS2().getPunkte());
        assertTrue(spiel.getS2().isIstdran());
        //Zug3
        spiel.ZugAuswerten(spiel.getS1().Hand.get(0), spiel.getS2().Hand.get(0), spiel.getS3().Hand.get(0), spiel.getS4().Hand.get(0));
        spiel.getS1().Hand.remove(0);
        spiel.getS2().Hand.remove(0);
        spiel.getS3().Hand.remove(0);
        spiel.getS4().Hand.remove(0);
        assertTrue(spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 1", 1, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 12", 12, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 0", 0, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 12", 12, bummerl.getPunkteS3());
        assertEquals("Bummerl Punkte Spieler4: 0", 0, bummerl.getPunkteS4());
        assertEquals("Punkte Spieler2: 36", 36, spiel.getS2().getPunkte());
        assertTrue(spiel.getS2().isIstdran());
    }

    @Test
    public void istSpielzuEnde_Bauernschnapser_GewinnerS3_S1_Gegner() throws WrongGameException {
        Spiel4 spiel = new Spiel4(2);
        spiel.Trumpfansagen("Pik", 0);
        spiel.SpielAnsagen(new Rufspiel("Bauernschnapser"), spiel.getS3());
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

        //Zug1
        spiel.ZugAuswerten(ausgespieltS1, ausgespieltS2, ausgespieltS3, ausgespieltS4);
        assertTrue(!spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 0", 0, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 0", 0, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 0", 0, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 0", 0, bummerl.getPunkteS3());
        assertEquals("Bummerl Punkte Spieler4: 0", 0, bummerl.getPunkteS4());
        assertEquals("Punkte Spieler3: 8", 8, spiel.getS3().getPunkte());
        assertTrue(spiel.getS3().isIstdran());
        //Zug2
        spiel.ZugAuswerten(spiel.getS1().Hand.get(0), spiel.getS2().Hand.get(0), spiel.getS3().Hand.get(0), spiel.getS4().Hand.get(0));
        spiel.getS1().Hand.remove(0);
        spiel.getS2().Hand.remove(0);
        spiel.getS3().Hand.remove(0);
        spiel.getS4().Hand.remove(0);
        assertTrue(!spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 0", 0, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 0", 0, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 0", 0, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 0", 0, bummerl.getPunkteS3());
        assertEquals("Bummerl Punkte Spieler4: 0", 0, bummerl.getPunkteS4());
        assertEquals("Punkte Spieler3: 20", 20, spiel.getS3().getPunkte());
        assertTrue(spiel.getS3().isIstdran());
        //Zug3
        spiel.ZugAuswerten(spiel.getS1().Hand.get(0), spiel.getS2().Hand.get(0), spiel.getS3().Hand.get(0), spiel.getS4().Hand.get(0));
        spiel.getS1().Hand.remove(0);
        spiel.getS2().Hand.remove(0);
        spiel.getS3().Hand.remove(0);
        spiel.getS4().Hand.remove(0);
        assertTrue(!spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 0", 0, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 0", 0, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 0", 0, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 0", 0, bummerl.getPunkteS3());
        assertEquals("Bummerl Punkte Spieler4: 0", 0, bummerl.getPunkteS4());
        assertEquals("Punkte Spieler3: 36", 36, spiel.getS3().getPunkte());
        assertTrue(spiel.getS3().isIstdran());
        //Zug4
        spiel.ZugAuswerten(spiel.getS1().Hand.get(0), spiel.getS2().Hand.get(0), spiel.getS3().Hand.get(0), spiel.getS4().Hand.get(0));
        spiel.getS1().Hand.remove(0);
        spiel.getS2().Hand.remove(0);
        spiel.getS3().Hand.remove(0);
        spiel.getS4().Hand.remove(0);
        assertTrue(!spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 0", 0, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 0", 0, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 0", 0, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 0", 0, bummerl.getPunkteS3());
        assertEquals("Bummerl Punkte Spieler4: 0", 0, bummerl.getPunkteS4());
        assertEquals("Punkte Spieler3: 76", 76, spiel.getS3().getPunkte());
        assertTrue(spiel.getS3().isIstdran());
        //Zug5
        spiel.ZugAuswerten(spiel.getS1().Hand.get(0), spiel.getS2().Hand.get(0), spiel.getS3().Hand.get(0), spiel.getS4().Hand.get(0));
        spiel.getS1().Hand.remove(0);
        spiel.getS2().Hand.remove(0);
        spiel.getS3().Hand.remove(0);
        spiel.getS4().Hand.remove(0);
        assertTrue(spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 1", 1, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 12", 12, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 0", 0, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 12", 12, bummerl.getPunkteS3());
        assertEquals("Bummerl Punkte Spieler4: 0", 0, bummerl.getPunkteS4());
        assertTrue(spiel.getS3().isIstdran());
    }

    @Test
    public void istSpielzuEnde_Bauernschnapser_GewinnerS2_S4_Gegner() throws WrongGameException {
        Spiel4 spiel = new Spiel4(2);
        spiel.Trumpfansagen("Karo", 0);
        spiel.SpielAnsagen(new Rufspiel("Bauernschnapser"), spiel.getS2());
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

        //Zug1
        spiel.ZugAuswerten(ausgespieltS1, ausgespieltS2, ausgespieltS3, ausgespieltS4);
        assertTrue(!spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 0", 0, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 0", 0, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 0", 0, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 0", 0, bummerl.getPunkteS3());
        assertEquals("Bummerl Punkte Spieler4: 0", 0, bummerl.getPunkteS4());
        assertEquals("Punkte Spieler2: 8", 8, spiel.getS2().getPunkte());
        assertTrue(spiel.getS2().isIstdran());
        //Zug2
        spiel.ZugAuswerten(spiel.getS1().Hand.get(0), spiel.getS2().Hand.get(0), spiel.getS3().Hand.get(0), spiel.getS4().Hand.get(0));
        spiel.getS1().Hand.remove(0);
        spiel.getS2().Hand.remove(0);
        spiel.getS3().Hand.remove(0);
        spiel.getS4().Hand.remove(0);
        assertTrue(!spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 0", 0, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 0", 0, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 0", 0, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 0", 0, bummerl.getPunkteS3());
        assertEquals("Bummerl Punkte Spieler4: 0", 0, bummerl.getPunkteS4());
        assertEquals("Punkte Spieler2: 20", 20, spiel.getS2().getPunkte());
        assertTrue(spiel.getS2().isIstdran());
        //Zug3
        spiel.ZugAuswerten(spiel.getS1().Hand.get(0), spiel.getS2().Hand.get(0), spiel.getS3().Hand.get(0), spiel.getS4().Hand.get(0));
        spiel.getS1().Hand.remove(0);
        spiel.getS2().Hand.remove(0);
        spiel.getS3().Hand.remove(0);
        spiel.getS4().Hand.remove(0);
        assertTrue(!spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 0", 0, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 0", 0, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 0", 0, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 0", 0, bummerl.getPunkteS3());
        assertEquals("Bummerl Punkte Spieler4: 0", 0, bummerl.getPunkteS4());
        assertEquals("Punkte Spieler2: 36", 36, spiel.getS2().getPunkte());
        assertTrue(spiel.getS2().isIstdran());
        //Zug4
        spiel.ZugAuswerten(spiel.getS1().Hand.get(0), spiel.getS2().Hand.get(0), spiel.getS3().Hand.get(0), spiel.getS4().Hand.get(0));
        spiel.getS1().Hand.remove(0);
        spiel.getS2().Hand.remove(0);
        spiel.getS3().Hand.remove(0);
        spiel.getS4().Hand.remove(0);
        assertTrue(!spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 0", 0, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 0", 0, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 0", 0, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 0", 0, bummerl.getPunkteS3());
        assertEquals("Bummerl Punkte Spieler4: 0", 0, bummerl.getPunkteS4());
        assertEquals("Punkte Spieler2: 76", 76, spiel.getS2().getPunkte());
        assertTrue(spiel.getS2().isIstdran());
        //Zug5
        spiel.ZugAuswerten(spiel.getS1().Hand.get(0), spiel.getS2().Hand.get(0), spiel.getS3().Hand.get(0), spiel.getS4().Hand.get(0));
        spiel.getS1().Hand.remove(0);
        spiel.getS2().Hand.remove(0);
        spiel.getS3().Hand.remove(0);
        spiel.getS4().Hand.remove(0);
        assertTrue(spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 1", 1, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 0", 0, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 12", 12, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 0", 0, bummerl.getPunkteS3());
        assertEquals("Bummerl Punkte Spieler4: 12", 12, bummerl.getPunkteS4());
        assertTrue(spiel.getS2().isIstdran());
    }

    @Test
    public void istSpielzuEnde_Bauernschnapser_VerliererS2_S4_Gegner() throws WrongGameException {
        Spiel4 spiel = new Spiel4(1);
        spiel.Trumpfansagen("Herz", 0);
        spiel.SpielAnsagen(new Rufspiel("Bauernschnapser"), spiel.getS2());
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

        //Zug1
        spiel.ZugAuswerten(ausgespieltS1, ausgespieltS2, ausgespieltS3, ausgespieltS4);
        assertTrue(spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 1", 1, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 12", 12, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 0", 0, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 12", 12, bummerl.getPunkteS3());
        assertEquals("Bummerl Punkte Spieler4: 0", 0, bummerl.getPunkteS4());
        assertEquals("Punkte Spieler1: 8", 8, spiel.getS1().getPunkte());
        assertTrue(spiel.getS1().isIstdran());
    }

    @Test
    public void istSpielzuEnde_Bauernschnapser_VerliererS1_S3_Gegner() throws WrongGameException {
        Spiel4 spiel = new Spiel4(0);
        spiel.Trumpfansagen("Karo", 0);
        spiel.SpielAnsagen(new Rufspiel("Bauernschnapser"), spiel.getS1());
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

        //Zug1
        spiel.ZugAuswerten(ausgespieltS1, ausgespieltS2, ausgespieltS3, ausgespieltS4);
        assertTrue(spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 1", 1, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 0", 0, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 12", 12, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 0", 0, bummerl.getPunkteS3());
        assertEquals("Bummerl Punkte Spieler4: 12", 12, bummerl.getPunkteS4());
        assertEquals("Punkte Spieler2: 8", 8, spiel.getS2().getPunkte());
        assertTrue(spiel.getS2().isIstdran());
    }

    @Test
    public void istSpielzuEnde_Kontrabauernschnapser_GewinnerS3_S1_Gegner() throws WrongGameException {
        Spiel4 spiel = new Spiel4(1);
        spiel.Trumpfansagen("Pik", 0);
        spiel.SpielAnsagen(new Rufspiel("Bauernschnapser"), spiel.getS2());
        spiel.SpielAnsagen(new Rufspiel("Kontrabauernschnapser"), spiel.getS3());
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

        //Zug1
        spiel.ZugAuswerten(ausgespieltS1, ausgespieltS2, ausgespieltS3, ausgespieltS4);
        assertTrue(!spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 0", 0, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 0", 0, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 0", 0, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 0", 0, bummerl.getPunkteS3());
        assertEquals("Bummerl Punkte Spieler4: 0", 0, bummerl.getPunkteS4());
        assertEquals("Punkte Spieler3: 8", 8, spiel.getS3().getPunkte());
        assertTrue(spiel.getS3().isIstdran());
        //Zug2
        spiel.ZugAuswerten(spiel.getS1().Hand.get(0), spiel.getS2().Hand.get(0), spiel.getS3().Hand.get(0), spiel.getS4().Hand.get(0));
        spiel.getS1().Hand.remove(0);
        spiel.getS2().Hand.remove(0);
        spiel.getS3().Hand.remove(0);
        spiel.getS4().Hand.remove(0);
        assertTrue(!spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 0", 0, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 0", 0, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 0", 0, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 0", 0, bummerl.getPunkteS3());
        assertEquals("Bummerl Punkte Spieler4: 0", 0, bummerl.getPunkteS4());
        assertEquals("Punkte Spieler3: 20", 20, spiel.getS3().getPunkte());
        assertTrue(spiel.getS3().isIstdran());
        //Zug3
        spiel.ZugAuswerten(spiel.getS1().Hand.get(0), spiel.getS2().Hand.get(0), spiel.getS3().Hand.get(0), spiel.getS4().Hand.get(0));
        spiel.getS1().Hand.remove(0);
        spiel.getS2().Hand.remove(0);
        spiel.getS3().Hand.remove(0);
        spiel.getS4().Hand.remove(0);
        assertTrue(!spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 0", 0, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 0", 0, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 0", 0, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 0", 0, bummerl.getPunkteS3());
        assertEquals("Bummerl Punkte Spieler4: 0", 0, bummerl.getPunkteS4());
        assertEquals("Punkte Spieler3: 36", 36, spiel.getS3().getPunkte());
        assertTrue(spiel.getS3().isIstdran());
        //Zug4
        spiel.ZugAuswerten(spiel.getS1().Hand.get(0), spiel.getS2().Hand.get(0), spiel.getS3().Hand.get(0), spiel.getS4().Hand.get(0));
        spiel.getS1().Hand.remove(0);
        spiel.getS2().Hand.remove(0);
        spiel.getS3().Hand.remove(0);
        spiel.getS4().Hand.remove(0);
        assertTrue(!spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 0", 0, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 0", 0, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 0", 0, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 0", 0, bummerl.getPunkteS3());
        assertEquals("Bummerl Punkte Spieler4: 0", 0, bummerl.getPunkteS4());
        assertEquals("Punkte Spieler3: 76", 76, spiel.getS3().getPunkte());
        assertTrue(spiel.getS3().isIstdran());
        //Zug5
        spiel.ZugAuswerten(spiel.getS1().Hand.get(0), spiel.getS2().Hand.get(0), spiel.getS3().Hand.get(0), spiel.getS4().Hand.get(0));
        spiel.getS1().Hand.remove(0);
        spiel.getS2().Hand.remove(0);
        spiel.getS3().Hand.remove(0);
        spiel.getS4().Hand.remove(0);
        assertTrue(spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 1", 1, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 24", 24, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 0", 0, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 24", 24, bummerl.getPunkteS3());
        assertEquals("Bummerl Punkte Spieler4: 0", 0, bummerl.getPunkteS4());
        assertTrue(spiel.getS3().isIstdran());
    }

    @Test
    public void istSpielzuEnde_Kontrabauernschnapser_GewinnerS2_S4_Gegner() throws WrongGameException {
        Spiel4 spiel = new Spiel4(0);
        spiel.Trumpfansagen("Karo", 0);
        spiel.SpielAnsagen(new Rufspiel("Bauernschnapser"), spiel.getS1());
        spiel.SpielAnsagen(new Rufspiel("Kontrabauernschnapser"), spiel.getS2());
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

        //Zug1
        spiel.ZugAuswerten(ausgespieltS1, ausgespieltS2, ausgespieltS3, ausgespieltS4);
        assertTrue(!spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 0", 0, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 0", 0, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 0", 0, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 0", 0, bummerl.getPunkteS3());
        assertEquals("Bummerl Punkte Spieler4: 0", 0, bummerl.getPunkteS4());
        assertEquals("Punkte Spieler2: 8", 8, spiel.getS2().getPunkte());
        assertTrue(spiel.getS2().isIstdran());
        //Zug2
        spiel.ZugAuswerten(spiel.getS1().Hand.get(0), spiel.getS2().Hand.get(0), spiel.getS3().Hand.get(0), spiel.getS4().Hand.get(0));
        spiel.getS1().Hand.remove(0);
        spiel.getS2().Hand.remove(0);
        spiel.getS3().Hand.remove(0);
        spiel.getS4().Hand.remove(0);
        assertTrue(!spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 0", 0, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 0", 0, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 0", 0, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 0", 0, bummerl.getPunkteS3());
        assertEquals("Bummerl Punkte Spieler4: 0", 0, bummerl.getPunkteS4());
        assertEquals("Punkte Spieler2: 20", 20, spiel.getS2().getPunkte());
        assertTrue(spiel.getS2().isIstdran());
        //Zug3
        spiel.ZugAuswerten(spiel.getS1().Hand.get(0), spiel.getS2().Hand.get(0), spiel.getS3().Hand.get(0), spiel.getS4().Hand.get(0));
        spiel.getS1().Hand.remove(0);
        spiel.getS2().Hand.remove(0);
        spiel.getS3().Hand.remove(0);
        spiel.getS4().Hand.remove(0);
        assertTrue(!spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 0", 0, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 0", 0, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 0", 0, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 0", 0, bummerl.getPunkteS3());
        assertEquals("Bummerl Punkte Spieler4: 0", 0, bummerl.getPunkteS4());
        assertEquals("Punkte Spieler2: 36", 36, spiel.getS2().getPunkte());
        assertTrue(spiel.getS2().isIstdran());
        //Zug4
        spiel.ZugAuswerten(spiel.getS1().Hand.get(0), spiel.getS2().Hand.get(0), spiel.getS3().Hand.get(0), spiel.getS4().Hand.get(0));
        spiel.getS1().Hand.remove(0);
        spiel.getS2().Hand.remove(0);
        spiel.getS3().Hand.remove(0);
        spiel.getS4().Hand.remove(0);
        assertTrue(!spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 0", 0, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 0", 0, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 0", 0, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 0", 0, bummerl.getPunkteS3());
        assertEquals("Bummerl Punkte Spieler4: 0", 0, bummerl.getPunkteS4());
        assertEquals("Punkte Spieler2: 76", 76, spiel.getS2().getPunkte());
        assertTrue(spiel.getS2().isIstdran());
        //Zug5
        spiel.ZugAuswerten(spiel.getS1().Hand.get(0), spiel.getS2().Hand.get(0), spiel.getS3().Hand.get(0), spiel.getS4().Hand.get(0));
        spiel.getS1().Hand.remove(0);
        spiel.getS2().Hand.remove(0);
        spiel.getS3().Hand.remove(0);
        spiel.getS4().Hand.remove(0);
        assertTrue(spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 1", 1, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 0", 0, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 24", 24, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 0", 0, bummerl.getPunkteS3());
        assertEquals("Bummerl Punkte Spieler4: 24", 24, bummerl.getPunkteS4());
        assertTrue(spiel.getS2().isIstdran());
    }

    @Test
    public void istSpielzuEnde_Kontrabauernschnapser_VerliererS2_S4_Gegner() throws WrongGameException {
        Spiel4 spiel = new Spiel4(0);
        spiel.Trumpfansagen("Herz", 0);
        spiel.SpielAnsagen(new Rufspiel("Bauernschnapser"), spiel.getS1());
        spiel.SpielAnsagen(new Rufspiel("Kontrabauernschnapser"), spiel.getS2());
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

        //Zug1
        spiel.ZugAuswerten(ausgespieltS1, ausgespieltS2, ausgespieltS3, ausgespieltS4);
        assertTrue(spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 1", 1, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 24", 24, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 0", 0, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 24", 24, bummerl.getPunkteS3());
        assertEquals("Bummerl Punkte Spieler4: 0", 0, bummerl.getPunkteS4());
        assertEquals("Punkte Spieler1: 8", 8, spiel.getS1().getPunkte());
        assertTrue(spiel.getS1().isIstdran());
    }

    @Test
    public void istSpielzuEnde_Kontrabauernschnapser_VerliererS1_S3_Gegner() throws WrongGameException {
        Spiel4 spiel = new Spiel4(1);
        spiel.Trumpfansagen("Karo", 0);
        spiel.SpielAnsagen(new Rufspiel("Bauernschnapser"), spiel.getS2());
        spiel.SpielAnsagen(new Rufspiel("Kontrabauernschnapser"), spiel.getS1());
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

        //Zug1
        spiel.ZugAuswerten(ausgespieltS1, ausgespieltS2, ausgespieltS3, ausgespieltS4);
        assertTrue(spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 1", 1, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 0", 0, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 24", 24, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 0", 0, bummerl.getPunkteS3());
        assertEquals("Bummerl Punkte Spieler4: 24", 24, bummerl.getPunkteS4());
        assertEquals("Punkte Spieler2: 8", 8, spiel.getS2().getPunkte());
        assertTrue(spiel.getS2().isIstdran());
    }

    @Test
    public void istSpielzuEnde_Farbenjodler_GewinnerS1_S3_Gegner() throws WrongGameException {
        Spiel4 spiel = new Spiel4(1);
        spiel.Trumpfansagen("Karo", 0);
        spiel.SpielAnsagen(new Rufspiel("Farbenjodler"), spiel.getS1());
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

        //Zug1
        assertTrue(spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 1", 1, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 18", 18, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 0", 0, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 18", 18, bummerl.getPunkteS3());
        assertEquals("Bummerl Punkte Spieler4: 0", 0, bummerl.getPunkteS4());
    }

    @Test
    public void istSpielzuEnde_Farbenjodler_GewinnerS2_S4_Gegner() throws WrongGameException {
        Spiel4 spiel = new Spiel4(1);
        spiel.Trumpfansagen("Karo", 0);
        spiel.SpielAnsagen(new Rufspiel("Farbenjodler"), spiel.getS2());
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

        //Zug1
        assertTrue(spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 1", 1, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 0", 0, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 18", 18, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 0", 0, bummerl.getPunkteS3());
        assertEquals("Bummerl Punkte Spieler4: 18", 18, bummerl.getPunkteS4());
        assertTrue(spiel.getS2().isIstdran());
    }

    @Test
    public void istSpielzuEnde_Herrenjodler_GewinnerS1_S3_Gegner() throws WrongGameException {
        Spiel4 spiel = new Spiel4(1);
        spiel.Trumpfansagen("Herz", 0);
        spiel.SpielAnsagen(new Rufspiel("Herrenjodler"), spiel.getS1());
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

        //Zug1
        assertTrue(spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 1", 1, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 24", 24, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 0", 0, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 24", 24, bummerl.getPunkteS3());
        assertEquals("Bummerl Punkte Spieler4: 0", 0, bummerl.getPunkteS4());
    }

    @Test
    public void istSpielzuEnde_Herrenjodler_GewinnerS2_S4_Gegner() throws WrongGameException {
        Spiel4 spiel = new Spiel4(1);
        spiel.Trumpfansagen("Karo", 0);
        spiel.SpielAnsagen(new Rufspiel("Herrenjodler"), spiel.getS2());
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

        //Zug1
        assertTrue(spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 1", 1, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 0", 0, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 24", 24, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 0", 0, bummerl.getPunkteS3());
        assertEquals("Bummerl Punkte Spieler4: 24", 24, bummerl.getPunkteS4());
        assertTrue(spiel.getS2().isIstdran());
    }



}
