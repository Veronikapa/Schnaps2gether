package appsolutegamesgmbh.schnaps2gether;

import org.junit.Before;
import org.junit.Test;

import appsolutegamesgmbh.schnaps2gether.DataStructure.Bummerl3;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Karte;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Rufspiel;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Spiel3;
import appsolutegamesgmbh.schnaps2gether.DataStructure.WrongGameException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by n15r2 on 27.05.15.
 */
public class Spiel3istSpielzuEndeRufspielTest {

    Bummerl3 bummerl = new Bummerl3();

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
    public void istSpielzuEnde_normal() throws WrongGameException {
        Spiel3 spiel = new Spiel3(0);
        spiel.Trumpfansagen("Pik", 0);
        spiel.getS1().Hand.removeAll(spiel.getS1().Hand);
        spiel.getS2().Hand.removeAll(spiel.getS2().Hand);
        spiel.getS3().Hand.removeAll(spiel.getS3().Hand);

        //Karten Spieler1
        spiel.getS1().Hand.add(herzbube);
        spiel.getS1().Hand.add(herzdame);
        spiel.getS1().Hand.add(herzkoenig);
        spiel.getS1().Hand.add(herz10);
        spiel.getS1().Hand.add(herzass);
        spiel.getS1().Hand.add(karobube);

        //Karten Spieler2
        spiel.getS2().Hand.add(karodame);
        spiel.getS2().Hand.add(karokoenig);
        spiel.getS2().Hand.add(karo10);
        spiel.getS2().Hand.add(karoass);
        spiel.getS2().Hand.add(pikbube);
        spiel.getS2().Hand.add(pikdame);

        //Karten Spieler3
        spiel.getS3().Hand.add(pikkoenig);
        spiel.getS3().Hand.add(pik10);
        spiel.getS3().Hand.add(pikass);
        spiel.getS3().Hand.add(kreuzbube);
        spiel.getS3().Hand.add(kreuzdame);
        spiel.getS3().Hand.add(kreuzkoenig);

        Karte ausgespieltS1 = spiel.getS1().Hand.get(0);
        Karte ausgespieltS2 = spiel.getS2().Hand.get(0);
        Karte ausgespieltS3 = spiel.getS3().Hand.get(0);

        spiel.Auspielen(spiel.getS1().Hand.get(0), spiel.getS1());
        spiel.Auspielen(spiel.getS2().Hand.get(0), spiel.getS2());
        spiel.Auspielen(spiel.getS3().Hand.get(0), spiel.getS3());

        spiel.setSpiel(new Rufspiel("normal"));

        //Zug1
        spiel.ZugAuswerten(ausgespieltS1, ausgespieltS2, ausgespieltS3);
        assertTrue(!spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 0", 0, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 0", 0, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 0", 0, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 0", 0, bummerl.getPunkteS3());
        assertEquals("Punkte Spieler3: 9", 9, spiel.getS3().getPunkte());
        assertTrue(spiel.getS3().isIstdran());
        //Zug2
        spiel.ZugAuswerten(spiel.getS1().Hand.get(0), spiel.getS2().Hand.get(0), spiel.getS3().Hand.get(0));
        assertTrue(!spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 0", 0, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 0", 0, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 0", 0, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 0", 0, bummerl.getPunkteS3());
        assertEquals("Punkte Spieler3: 26", 26, spiel.getS3().getPunkte());
        assertTrue(spiel.getS3().isIstdran());
        //Zug3
        spiel.ZugAuswerten(spiel.getS1().Hand.get(0), spiel.getS2().Hand.get(0), spiel.getS3().Hand.get(0));
        assertTrue(!spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 0", 0, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 0", 0, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 0", 0, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 0", 0, bummerl.getPunkteS3());
        assertEquals("Punkte Spieler3: 43", 43, spiel.getS3().getPunkte());
        assertTrue(spiel.getS3().isIstdran());
        //Zug4
        spiel.ZugAuswerten(spiel.getS1().Hand.get(0), spiel.getS2().Hand.get(0), spiel.getS3().Hand.get(0));
        assertTrue(!spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 0", 0, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 0", 0, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 0", 0, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 0", 0, bummerl.getPunkteS3());
        assertEquals("Punkte Spieler3: 60", 60, spiel.getS3().getPunkte());
        assertTrue(spiel.getS3().isIstdran());
        //Zug5
        spiel.ZugAuswerten(spiel.getS1().Hand.get(0), spiel.getS2().Hand.get(0), spiel.getS3().Hand.get(0));
        assertTrue(spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 1", 1, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 0", 0, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 3", 3, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 3", 3, bummerl.getPunkteS3());
        assertEquals("Punkte Spieler3: 77", 77, spiel.getS3().getPunkte());

    }

    @Test
    public void istSpielzuEnde_Schnapser_RuferVerliert() throws WrongGameException {
        Spiel3 spiel = new Spiel3(0);
        spiel.Trumpfansagen("Pik", 0);
        spiel.getS1().Hand.removeAll(spiel.getS1().Hand);
        spiel.getS2().Hand.removeAll(spiel.getS2().Hand);
        spiel.getS3().Hand.removeAll(spiel.getS3().Hand);

        //Karten Spieler1
        spiel.getS1().Hand.add(herzbube);
        spiel.getS1().Hand.add(herzdame);
        spiel.getS1().Hand.add(herzkoenig);
        spiel.getS1().Hand.add(herz10);
        spiel.getS1().Hand.add(herzass);
        spiel.getS1().Hand.add(karobube);

        //Karten Spieler2
        spiel.getS2().Hand.add(karodame);
        spiel.getS2().Hand.add(karokoenig);
        spiel.getS2().Hand.add(karo10);
        spiel.getS2().Hand.add(karoass);
        spiel.getS2().Hand.add(pikbube);
        spiel.getS2().Hand.add(pikdame);

        //Karten Spieler3
        spiel.getS3().Hand.add(pikkoenig);
        spiel.getS3().Hand.add(pik10);
        spiel.getS3().Hand.add(pikass);
        spiel.getS3().Hand.add(kreuzbube);
        spiel.getS3().Hand.add(kreuzdame);
        spiel.getS3().Hand.add(kreuzkoenig);

        Karte ausgespieltS1 = spiel.getS1().Hand.get(0);
        Karte ausgespieltS2 = spiel.getS2().Hand.get(0);
        Karte ausgespieltS3 = spiel.getS3().Hand.get(0);

        spiel.Auspielen(spiel.getS1().Hand.get(0), spiel.getS1());
        spiel.Auspielen(spiel.getS2().Hand.get(0), spiel.getS2());
        spiel.Auspielen(spiel.getS3().Hand.get(0), spiel.getS3());

        spiel.setSpiel(new Rufspiel("Schnapser"));

        //Zug1
        spiel.ZugAuswerten(ausgespieltS1, ausgespieltS2, ausgespieltS3);
        assertTrue(spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 1", 1, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 0", 0, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 6", 6, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 6", 6, bummerl.getPunkteS3());
        assertEquals("Punkte Spieler3: 9", 9, spiel.getS3().getPunkte());
        assertTrue(spiel.getS3().isIstdran());
    }

    @Test
    public void istSpielzuEnde_Land_RuferVerliert() throws WrongGameException {
        Spiel3 spiel = new Spiel3(0);
        spiel.Trumpfansagen("Pik", 0);
        spiel.getS1().Hand.removeAll(spiel.getS1().Hand);
        spiel.getS2().Hand.removeAll(spiel.getS2().Hand);
        spiel.getS3().Hand.removeAll(spiel.getS3().Hand);

        //Karten Spieler1
        spiel.getS1().Hand.add(herzbube);
        spiel.getS1().Hand.add(herzdame);
        spiel.getS1().Hand.add(herzkoenig);
        spiel.getS1().Hand.add(herz10);
        spiel.getS1().Hand.add(herzass);
        spiel.getS1().Hand.add(karobube);

        //Karten Spieler2
        spiel.getS2().Hand.add(karodame);
        spiel.getS2().Hand.add(karokoenig);
        spiel.getS2().Hand.add(karo10);
        spiel.getS2().Hand.add(karoass);
        spiel.getS2().Hand.add(pikbube);
        spiel.getS2().Hand.add(pikdame);

        //Karten Spieler3
        spiel.getS3().Hand.add(pikkoenig);
        spiel.getS3().Hand.add(pik10);
        spiel.getS3().Hand.add(pikass);
        spiel.getS3().Hand.add(kreuzbube);
        spiel.getS3().Hand.add(kreuzdame);
        spiel.getS3().Hand.add(kreuzkoenig);

        Karte ausgespieltS1 = spiel.getS1().Hand.get(0);
        Karte ausgespieltS2 = spiel.getS2().Hand.get(0);
        Karte ausgespieltS3 = spiel.getS3().Hand.get(0);

        spiel.Auspielen(spiel.getS1().Hand.get(0), spiel.getS1());
        spiel.Auspielen(spiel.getS2().Hand.get(0), spiel.getS2());
        spiel.Auspielen(spiel.getS3().Hand.get(0), spiel.getS3());

        spiel.setSpiel(new Rufspiel("Land"));

        //Zug1
        spiel.ZugAuswerten(ausgespieltS1, ausgespieltS2, ausgespieltS3);
        assertTrue(spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 1", 1, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 0", 0, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 9", 9, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 9", 9, bummerl.getPunkteS3());
        assertEquals("Punkte Spieler3: 9", 9, spiel.getS3().getPunkte());
        assertTrue(spiel.getS3().isIstdran());
    }

    @Test
    public void istSpielzuEnde_Bauernschnapser_RuferVerliert() throws WrongGameException {
        Spiel3 spiel = new Spiel3(0);
        spiel.Trumpfansagen("Pik", 0);
        spiel.getS1().Hand.removeAll(spiel.getS1().Hand);
        spiel.getS2().Hand.removeAll(spiel.getS2().Hand);
        spiel.getS3().Hand.removeAll(spiel.getS3().Hand);

        //Karten Spieler1
        spiel.getS1().Hand.add(herzbube);
        spiel.getS1().Hand.add(herzdame);
        spiel.getS1().Hand.add(herzkoenig);
        spiel.getS1().Hand.add(herz10);
        spiel.getS1().Hand.add(herzass);
        spiel.getS1().Hand.add(karobube);

        //Karten Spieler2
        spiel.getS2().Hand.add(karodame);
        spiel.getS2().Hand.add(karokoenig);
        spiel.getS2().Hand.add(karo10);
        spiel.getS2().Hand.add(karoass);
        spiel.getS2().Hand.add(pikbube);
        spiel.getS2().Hand.add(pikdame);

        //Karten Spieler3
        spiel.getS3().Hand.add(pikkoenig);
        spiel.getS3().Hand.add(pik10);
        spiel.getS3().Hand.add(pikass);
        spiel.getS3().Hand.add(kreuzbube);
        spiel.getS3().Hand.add(kreuzdame);
        spiel.getS3().Hand.add(kreuzkoenig);

        Karte ausgespieltS1 = spiel.getS1().Hand.get(0);
        Karte ausgespieltS2 = spiel.getS2().Hand.get(0);
        Karte ausgespieltS3 = spiel.getS3().Hand.get(0);

        spiel.Auspielen(spiel.getS1().Hand.get(0), spiel.getS1());
        spiel.Auspielen(spiel.getS2().Hand.get(0), spiel.getS2());
        spiel.Auspielen(spiel.getS3().Hand.get(0), spiel.getS3());

        spiel.setSpiel(new Rufspiel("Bauernschnapser"));

        //Zug1
        spiel.ZugAuswerten(ausgespieltS1, ausgespieltS2, ausgespieltS3);
        assertTrue(spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 1", 1, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 0", 0, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 12", 12, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 12", 12, bummerl.getPunkteS3());
        assertEquals("Punkte Spieler3: 9", 9, spiel.getS3().getPunkte());
        assertTrue(spiel.getS3().isIstdran());
    }

    @Test
    public void istSpielzuEnde_Kontraschnapser_RuferVerliert() throws WrongGameException {
        Spiel3 spiel = new Spiel3(0);
        spiel.Trumpfansagen("Pik", 0);
        spiel.getS1().Hand.removeAll(spiel.getS1().Hand);
        spiel.getS2().Hand.removeAll(spiel.getS2().Hand);
        spiel.getS3().Hand.removeAll(spiel.getS3().Hand);

        //Karten Spieler1
        spiel.getS1().Hand.add(herzbube);
        spiel.getS1().Hand.add(herzdame);
        spiel.getS1().Hand.add(herzkoenig);spiel.SpielAnsagen(new Rufspiel("Schnapser"), spiel.getS2());
        spiel.getS1().Hand.add(herz10);
        spiel.getS1().Hand.add(herzass);
        spiel.getS1().Hand.add(karobube);

        //Karten Spieler2
        spiel.getS2().Hand.add(karodame);
        spiel.getS2().Hand.add(karokoenig);
        spiel.getS2().Hand.add(karo10);
        spiel.getS2().Hand.add(karoass);
        spiel.getS2().Hand.add(pikbube);
        spiel.getS2().Hand.add(pikdame);

        //Karten Spieler3
        spiel.getS3().Hand.add(pikkoenig);
        spiel.getS3().Hand.add(pik10);
        spiel.getS3().Hand.add(pikass);
        spiel.getS3().Hand.add(kreuzbube);
        spiel.getS3().Hand.add(kreuzdame);
        spiel.getS3().Hand.add(kreuzkoenig);

        Karte ausgespieltS1 = spiel.getS1().Hand.get(0);
        Karte ausgespieltS2 = spiel.getS2().Hand.get(0);
        Karte ausgespieltS3 = spiel.getS3().Hand.get(0);

        spiel.Auspielen(spiel.getS1().Hand.get(0), spiel.getS1());
        spiel.Auspielen(spiel.getS2().Hand.get(0), spiel.getS2());
        spiel.Auspielen(spiel.getS3().Hand.get(0), spiel.getS3());

        spiel.setSpiel(new Rufspiel("Schnapser"));
        spiel.SpielAnsagen(new Rufspiel("Kontraschnapser"), spiel.getS3());

        //Zug1
        spiel.ZugAuswerten(ausgespieltS1, ausgespieltS2, ausgespieltS3);
        assertTrue(!spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 0", 0, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 0", 0, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 0", 0, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 0", 0, bummerl.getPunkteS3());
        assertEquals("Punkte Spieler3: 9", 9, spiel.getS3().getPunkte());
        assertTrue(spiel.getS3().isIstdran());
        //Zug2
        spiel.ZugAuswerten(spiel.getS1().Hand.get(0), spiel.getS2().Hand.get(0), spiel.getS3().Hand.get(0));
        spiel.getS1().Hand.remove(0);
        spiel.getS2().Hand.remove(0);
        spiel.getS3().Hand.remove(0);
        assertTrue(!spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 0", 0, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 0", 0, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 0", 0, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 0", 0, bummerl.getPunkteS3());
        assertEquals("Punkte Spieler3: 26", 26, spiel.getS3().getPunkte());
        assertTrue(spiel.getS3().isIstdran());
        //Zug3
        spiel.ZugAuswerten(spiel.getS1().Hand.get(0), spiel.getS2().Hand.get(0), spiel.getS3().Hand.get(0));
        spiel.getS1().Hand.remove(0);
        spiel.getS2().Hand.remove(0);
        spiel.getS3().Hand.remove(0);
        assertTrue(spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 1", 1, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 12", 12, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 12", 12, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 0", 0, bummerl.getPunkteS3());
        assertEquals("Punkte Spieler3: 51", 51, spiel.getS3().getPunkte());
        assertTrue(spiel.getS3().isIstdran());
    }

    @Test
    public void istSpielzuEnde_Kontraschnapser_RuferGewinnt() throws WrongGameException {
        Spiel3 spiel = new Spiel3(0);
        spiel.Trumpfansagen("Pik", 0);
        spiel.getS1().Hand.removeAll(spiel.getS1().Hand);
        spiel.getS2().Hand.removeAll(spiel.getS2().Hand);
        spiel.getS3().Hand.removeAll(spiel.getS3().Hand);

        //Karten Spieler1
        spiel.getS1().Hand.add(herzbube);
        spiel.getS1().Hand.add(herzdame);
        spiel.getS1().Hand.add(herzkoenig);
        spiel.getS1().Hand.add(herz10);
        spiel.getS1().Hand.add(herzass);
        spiel.getS1().Hand.add(karobube);

        //Karten Spieler2
        spiel.getS2().Hand.add(karodame);
        spiel.getS2().Hand.add(karokoenig);
        spiel.getS2().Hand.add(karo10);
        spiel.getS2().Hand.add(karoass);
        spiel.getS2().Hand.add(pikbube);
        spiel.getS2().Hand.add(pikdame);

        //Karten Spieler3
        spiel.getS3().Hand.add(pikkoenig);
        spiel.getS3().Hand.add(pik10);
        spiel.getS3().Hand.add(pikass);
        spiel.getS3().Hand.add(kreuzbube);
        spiel.getS3().Hand.add(kreuzdame);
        spiel.getS3().Hand.add(kreuzkoenig);

        Karte ausgespieltS1 = herzass;
        Karte ausgespieltS2 = karoass;
        Karte ausgespieltS3 = pikass;

        spiel.Auspielen(spiel.getS1().Hand.get(0), spiel.getS1());
        spiel.Auspielen(spiel.getS2().Hand.get(0), spiel.getS2());
        spiel.Auspielen(spiel.getS3().Hand.get(0), spiel.getS3());

        spiel.setSpiel(new Rufspiel("Schnapser"));
        spiel.SpielAnsagen(new Rufspiel("Kontraschnapser"), spiel.getS3());

        //Zug1
        spiel.ZugAuswerten(ausgespieltS1, ausgespieltS2, ausgespieltS3);
        assertTrue(!spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 0", 0, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 0", 0, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 0", 0, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 0", 0, bummerl.getPunkteS3());
        assertEquals("Punkte Spieler3: 33", 33, spiel.getS3().getPunkte());
        assertTrue(spiel.getS3().isIstdran());
        //Zug2
        spiel.ZugAuswerten(spiel.getS1().Hand.get(0), spiel.getS2().Hand.get(0), spiel.getS3().Hand.get(0));
        spiel.getS1().Hand.remove(0);
        spiel.getS2().Hand.remove(0);
        spiel.getS3().Hand.remove(0);
        assertTrue(!spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 0", 0, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 0", 0, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 0", 0, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 0", 0, bummerl.getPunkteS3());
        assertEquals("Punkte Spieler3: 50", 50, spiel.getS3().getPunkte());
        assertTrue(spiel.getS3().isIstdran());
        //Zug3
        spiel.ZugAuswerten(spiel.getS1().Hand.get(0), spiel.getS2().Hand.get(0), spiel.getS3().Hand.get(0));
        spiel.getS1().Hand.remove(0);
        spiel.getS2().Hand.remove(0);
        spiel.getS3().Hand.remove(0);
        assertTrue(spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 1", 1, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 0", 0, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 0", 0, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 12", 12, bummerl.getPunkteS3());
        assertEquals("Punkte Spieler3: 75", 75, spiel.getS3().getPunkte());
        assertTrue(spiel.getS3().isIstdran());
    }

    @Test
    public void istSpielzuEnde_Kontrabauernschnapser_RuferGewinnt() throws WrongGameException {
        Spiel3 spiel = new Spiel3(0);
        spiel.Trumpfansagen("Pik", 0);
        spiel.getS1().Hand.removeAll(spiel.getS1().Hand);
        spiel.getS2().Hand.removeAll(spiel.getS2().Hand);
        spiel.getS3().Hand.removeAll(spiel.getS3().Hand);

        //Karten Spieler1
        spiel.getS1().Hand.add(herzbube);
        spiel.getS1().Hand.add(herzdame);
        spiel.getS1().Hand.add(herzkoenig);
        spiel.getS1().Hand.add(herz10);
        spiel.getS1().Hand.add(herzass);
        spiel.getS1().Hand.add(karobube);

        //Karten Spieler2
        spiel.getS2().Hand.add(karodame);
        spiel.getS2().Hand.add(karokoenig);
        spiel.getS2().Hand.add(karo10);
        spiel.getS2().Hand.add(karoass);
        spiel.getS2().Hand.add(pikbube);
        spiel.getS2().Hand.add(pikdame);

        //Karten Spieler3
        spiel.getS3().Hand.add(pikkoenig);
        spiel.getS3().Hand.add(pik10);
        spiel.getS3().Hand.add(pikass);
        spiel.getS3().Hand.add(kreuzbube);
        spiel.getS3().Hand.add(kreuzdame);
        spiel.getS3().Hand.add(kreuzkoenig);

        Karte ausgespieltS1 = herzass;
        Karte ausgespieltS2 = karoass;
        Karte ausgespieltS3 = pikass;

        spiel.Auspielen(spiel.getS1().Hand.get(0), spiel.getS1());
        spiel.Auspielen(spiel.getS2().Hand.get(0), spiel.getS2());
        spiel.Auspielen(spiel.getS3().Hand.get(0), spiel.getS3());

        spiel.setSpiel(new Rufspiel("Bauernschnapser"));
        spiel.SpielAnsagen(new Rufspiel("Kontrabauernschnapser"), spiel.getS3());

        //Zug1
        spiel.ZugAuswerten(ausgespieltS1, ausgespieltS2, ausgespieltS3);
        assertTrue(!spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 0", 0, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 0", 0, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 0", 0, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 0", 0, bummerl.getPunkteS3());
        assertEquals("Punkte Spieler3: 33", 33, spiel.getS3().getPunkte());
        assertTrue(spiel.getS3().isIstdran());
        //Zug2
        spiel.ZugAuswerten(spiel.getS1().Hand.get(0), spiel.getS2().Hand.get(0), spiel.getS3().Hand.get(0));
        spiel.getS1().Hand.remove(0);
        spiel.getS2().Hand.remove(0);
        spiel.getS3().Hand.remove(0);
        assertTrue(!spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 0", 0, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 0", 0, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 0", 0, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 0", 0, bummerl.getPunkteS3());
        assertEquals("Punkte Spieler3: 50", 50, spiel.getS3().getPunkte());
        assertTrue(spiel.getS3().isIstdran());
        //Zug3
        spiel.ZugAuswerten(spiel.getS1().Hand.get(0), spiel.getS2().Hand.get(0), spiel.getS3().Hand.get(0));
        spiel.getS1().Hand.remove(0);
        spiel.getS2().Hand.remove(0);
        spiel.getS3().Hand.remove(0);
        assertTrue(!spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 0", 0, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 0", 0, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 0", 0, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 0", 0, bummerl.getPunkteS3());
        assertEquals("Punkte Spieler3: 75", 75, spiel.getS3().getPunkte());
        assertTrue(spiel.getS3().isIstdran());

        //Zug4
        spiel.ZugAuswerten(spiel.getS1().Hand.get(0), spiel.getS2().Hand.get(0), spiel.getS3().Hand.get(0));
        spiel.getS1().Hand.remove(0);
        spiel.getS2().Hand.remove(0);
        spiel.getS3().Hand.remove(0);
        assertTrue(!spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 0", 0, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 0", 0, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 0", 0, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 0", 0, bummerl.getPunkteS3());
        assertEquals("Punkte Spieler3: 98", 98, spiel.getS3().getPunkte());
        assertTrue(spiel.getS3().isIstdran());

        //Zug5
        spiel.ZugAuswerten(spiel.getS1().Hand.get(0), spiel.getS2().Hand.get(0), spiel.getS3().Hand.get(0));
        spiel.getS1().Hand.remove(0);
        spiel.getS2().Hand.remove(0);
        spiel.getS3().Hand.remove(0);
        assertTrue(!spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 0", 0, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 0", 0, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 0", 0, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 0", 0, bummerl.getPunkteS3());
        assertEquals("Punkte Spieler3: 114", 114, spiel.getS3().getPunkte());
        assertTrue(spiel.getS3().isIstdran());

        //Zug6
        spiel.ZugAuswerten(spiel.getS1().Hand.get(0), spiel.getS2().Hand.get(0), spiel.getS3().Hand.get(0));
        spiel.getS1().Hand.remove(0);
        spiel.getS2().Hand.remove(0);
        spiel.getS3().Hand.remove(0);
        assertTrue(spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 1", 1, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 0", 0, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 0", 0, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 24", 24, bummerl.getPunkteS3());
        assertEquals("Punkte Spieler3: 123", 123, spiel.getS3().getPunkte());
        assertTrue(spiel.getS3().isIstdran());
    }

    @Test
    public void istSpielzuEnde_Land_RuferGewinnt() throws WrongGameException {
        Spiel3 spiel = new Spiel3(0);
        spiel.Trumpfansagen("Pik", 0);
        spiel.getS1().Hand.removeAll(spiel.getS1().Hand);
        spiel.getS2().Hand.removeAll(spiel.getS2().Hand);
        spiel.getS3().Hand.removeAll(spiel.getS3().Hand);

        //Karten Spieler1
        spiel.getS1().Hand.add(herzbube);
        spiel.getS1().Hand.add(herzdame);
        spiel.getS1().Hand.add(herzkoenig);
        spiel.getS1().Hand.add(herz10);
        spiel.getS1().Hand.add(herzass);
        spiel.getS1().Hand.add(karobube);

        //Karten Spieler2
        spiel.getS2().Hand.add(karodame);
        spiel.getS2().Hand.add(karokoenig);
        spiel.getS2().Hand.add(karo10);
        spiel.getS2().Hand.add(karoass);
        spiel.getS2().Hand.add(pikbube);
        spiel.getS2().Hand.add(pikdame);

        //Karten Spieler3
        spiel.getS3().Hand.add(pikkoenig);
        spiel.getS3().Hand.add(pik10);
        spiel.getS3().Hand.add(pikass);
        spiel.getS3().Hand.add(kreuzbube);
        spiel.getS3().Hand.add(kreuzdame);
        spiel.getS3().Hand.add(kreuzkoenig);

        Karte ausgespieltS1 = spiel.getS1().Hand.get(0);
        Karte ausgespieltS2 = spiel.getS2().Hand.get(0);
        Karte ausgespieltS3 = spiel.getS3().Hand.get(0);

        spiel.Auspielen(spiel.getS1().Hand.get(0), spiel.getS1());
        spiel.Auspielen(spiel.getS2().Hand.get(0), spiel.getS2());
        spiel.Auspielen(spiel.getS3().Hand.get(0), spiel.getS3());

        spiel.SpielAnsagen(new Rufspiel("Land"), spiel.getS3());

        //Zug1
        spiel.ZugAuswerten(ausgespieltS1, ausgespieltS2, ausgespieltS3);
        assertTrue(!spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 0", 0, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 0", 0, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 0", 0, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 0", 0, bummerl.getPunkteS3());
        assertEquals("Punkte Spieler3: 9", 9, spiel.getS3().getPunkte());
        assertTrue(spiel.getS3().isIstdran());

        //Zug2
        spiel.ZugAuswerten(spiel.getS1().Hand.get(0), spiel.getS2().Hand.get(0), spiel.getS3().Hand.get(0));
        spiel.getS1().Hand.remove(0);
        spiel.getS2().Hand.remove(0);
        spiel.getS3().Hand.remove(0);
        assertTrue(!spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 0", 0, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 0", 0, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 0", 0, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 0", 0, bummerl.getPunkteS3());
        assertEquals("Punkte Spieler3: 26", 26, spiel.getS3().getPunkte());
        assertTrue(spiel.getS3().isIstdran());

        //Zug3
        spiel.ZugAuswerten(spiel.getS1().Hand.get(0), spiel.getS2().Hand.get(0), spiel.getS3().Hand.get(0));
        spiel.getS1().Hand.remove(0);
        spiel.getS2().Hand.remove(0);
        spiel.getS3().Hand.remove(0);
        assertTrue(!spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 0", 0, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 0", 0, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 0", 0, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 0", 0, bummerl.getPunkteS3());
        assertEquals("Punkte Spieler3: 51", 51, spiel.getS3().getPunkte());
        assertTrue(spiel.getS3().isIstdran());

        //Zug4
        spiel.ZugAuswerten(spiel.getS1().Hand.get(0), spiel.getS2().Hand.get(0), spiel.getS3().Hand.get(0));
        spiel.getS1().Hand.remove(0);
        spiel.getS2().Hand.remove(0);
        spiel.getS3().Hand.remove(0);
        assertTrue(!spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 0", 0, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 0", 0, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 0", 0, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 0", 0, bummerl.getPunkteS3());
        assertEquals("Punkte Spieler3: 74", 74, spiel.getS3().getPunkte());
        assertTrue(spiel.getS3().isIstdran());

        //Zug5
        spiel.ZugAuswerten(spiel.getS1().Hand.get(0), spiel.getS2().Hand.get(0), spiel.getS3().Hand.get(0));
        spiel.getS1().Hand.remove(0);
        spiel.getS2().Hand.remove(0);
        spiel.getS3().Hand.remove(0);
        assertTrue(!spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 0", 0, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 0", 0, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 0", 0, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 0", 0, bummerl.getPunkteS3());
        assertEquals("Punkte Spieler3: 90", 90, spiel.getS3().getPunkte());
        assertTrue(spiel.getS3().isIstdran());

        //Zug6
        spiel.ZugAuswerten(spiel.getS1().Hand.get(0), spiel.getS2().Hand.get(0), spiel.getS3().Hand.get(0));
        spiel.getS1().Hand.remove(0);
        spiel.getS2().Hand.remove(0);
        spiel.getS3().Hand.remove(0);
        assertTrue(spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 1", 1, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 0", 0, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 0", 0, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 9", 9, bummerl.getPunkteS3());
        assertEquals("Punkte Spieler3: 99", 99, spiel.getS3().getPunkte());
        assertTrue(spiel.getS3().isIstdran());
    }

    @Test
    public void istSpielzuEnde_Schnapser_RuferGewinnt() throws WrongGameException {
        Spiel3 spiel = new Spiel3(0);
        spiel.Trumpfansagen("Pik", 0);
        spiel.getS1().Hand.removeAll(spiel.getS1().Hand);
        spiel.getS2().Hand.removeAll(spiel.getS2().Hand);
        spiel.getS3().Hand.removeAll(spiel.getS3().Hand);

        //Karten Spieler1
        spiel.getS1().Hand.add(herzbube);
        spiel.getS1().Hand.add(herzdame);
        spiel.getS1().Hand.add(herzkoenig);
        spiel.getS1().Hand.add(herz10);
        spiel.getS1().Hand.add(herzass);
        spiel.getS1().Hand.add(karobube);

        //Karten Spieler2
        spiel.getS2().Hand.add(karodame);
        spiel.getS2().Hand.add(karokoenig);
        spiel.getS2().Hand.add(karo10);
        spiel.getS2().Hand.add(karoass);
        spiel.getS2().Hand.add(pikbube);
        spiel.getS2().Hand.add(pikdame);

        //Karten Spieler3
        spiel.getS3().Hand.add(pikkoenig);
        spiel.getS3().Hand.add(pik10);
        spiel.getS3().Hand.add(pikass);
        spiel.getS3().Hand.add(kreuzbube);
        spiel.getS3().Hand.add(kreuzdame);
        spiel.getS3().Hand.add(kreuzkoenig);

        Karte ausgespieltS1 = herzass;
        Karte ausgespieltS2 = karoass;
        Karte ausgespieltS3 = pikass;

        spiel.Auspielen(spiel.getS1().Hand.get(0), spiel.getS1());
        spiel.Auspielen(spiel.getS2().Hand.get(0), spiel.getS2());
        spiel.Auspielen(spiel.getS3().Hand.get(0), spiel.getS3());

        spiel.SpielAnsagen(new Rufspiel("Schnapser"), spiel.getS3());

        //Zug1
        spiel.ZugAuswerten(ausgespieltS1, ausgespieltS2, ausgespieltS3);
        assertTrue(!spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 0", 0, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 0", 0, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 0", 0, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 0", 0, bummerl.getPunkteS3());
        assertEquals("Punkte Spieler3: 33", 33, spiel.getS3().getPunkte());
        assertTrue(spiel.getS3().isIstdran());
        //Zug2
        spiel.ZugAuswerten(spiel.getS1().Hand.get(0), spiel.getS2().Hand.get(0), spiel.getS3().Hand.get(0));
        spiel.getS1().Hand.remove(0);
        spiel.getS2().Hand.remove(0);
        spiel.getS3().Hand.remove(0);
        assertTrue(!spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 0", 0, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 0", 0, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 0", 0, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 0", 0, bummerl.getPunkteS3());
        assertEquals("Punkte Spieler3: 50", 50, spiel.getS3().getPunkte());
        assertTrue(spiel.getS3().isIstdran());
        //Zug3
        spiel.ZugAuswerten(spiel.getS1().Hand.get(0), spiel.getS2().Hand.get(0), spiel.getS3().Hand.get(0));
        spiel.getS1().Hand.remove(0);
        spiel.getS2().Hand.remove(0);
        spiel.getS3().Hand.remove(0);
        assertTrue(spiel.istSpielzuEnde(bummerl));
        assertEquals("Anzahl Spiele 1", 1, bummerl.getAnzahlSpiele());
        assertEquals("Bummerl Punkte Spieler1: 0", 0, bummerl.getPunkteS1());
        assertEquals("Bummerl Punkte Spieler2: 0", 0, bummerl.getPunkteS2());
        assertEquals("Bummerl Punkte Spieler3: 6", 6, bummerl.getPunkteS3());
        assertEquals("Punkte Spieler3: 75", 75, spiel.getS3().getPunkte());
        assertTrue(spiel.getS3().isIstdran());
    }


}
