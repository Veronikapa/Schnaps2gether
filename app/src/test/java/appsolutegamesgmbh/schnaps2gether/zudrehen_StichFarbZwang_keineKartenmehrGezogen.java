package appsolutegamesgmbh.schnaps2gether;

import appsolutegamesgmbh.schnaps2gether.DataStructure.Spieler;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Karte;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Bummerl2;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class zudrehen_StichFarbZwang_keineKartenmehrGezogen {

    private Spiel2 spiel = new Spiel2();
    private Spieler s1 = new Spieler();
    private Spieler s2 = new Spieler();
    private Bummerl2 bummerl = new Bummerl2();
    private String trumpf;
    private Karte aufgedeckterTrumpf;


    @Before
    public void setUp() {


    }

    @Test
    public void zugedreht_farb_stich_Zwang() {
        assertTrue("Es ist nicht zugedreht", !spiel.isZugedreht());
        spiel.Auspielen(spiel.getS1().Hand.get(0)); //Herz","Bube",2
        spiel.ZugAuswerten(spiel.getS1().Hand.get(0), spiel.getS2().Hand.get(0)); // S2 = 10 Herz 10er
        spiel.getS2().Hand.remove(0);
        spiel.istSpielzuEnde(bummerl);
        //Spieler 2 ist dran
        //S2 = 11 Karo Ass
        //S1 = 10 Karo 10er
        assertTrue("Es ist nicht zugedreht", !spiel.isZugedreht());
        spiel.ZugAuswerten(spiel.getS1().Hand.get(3), spiel.getS2().Hand.get(2));
        spiel.getS1().Hand.remove(3);
        spiel.getS2().Hand.remove(2);
        spiel.istSpielzuEnde(bummerl);
        //Spieler 2 ist dran
        //S2 = 10 Pik 10er
        //S1 = 4 Pik König
        spiel.Zudrehen();
        assertTrue("Es ist zugedreht", spiel.isZugedreht());
        spiel.ZugAuswerten(spiel.getS1().Hand.get(3), spiel.getS2().Hand.get(4));
        spiel.getS1().Hand.remove(3);
        spiel.getS2().Hand.remove(4);
        spiel.istSpielzuEnde(bummerl);
        assertEquals("keine neuen Karten werden ausgeteilt", 4, spiel.getS1().Hand.size());
        assertEquals("keine neuen Karten werden ausgeteilt", 4, spiel.getS2().Hand.size());
        //Spieler 2 ist dran
        //S2 = 11 Herz Ass
        //S1 = 4 Herz König
        spiel.ZugAuswerten(spiel.getS1().Hand.get(1), spiel.getS2().Hand.get(0));
        spiel.getS1().Hand.remove(1);
        spiel.getS2().Hand.remove(0);
        spiel.istSpielzuEnde(bummerl);
        assertEquals("keine neuen Karten werden ausgeteilt", 3, spiel.getS1().Hand.size());
        assertEquals("keine neuen Karten werden ausgeteilt", 3, spiel.getS2().Hand.size());
        //Spieler 2 ist dran (Punkte 63)
        //S2 = 4 Kreuz König
        //S1 = 3 Kreuz Dame

        /**
        spiel.ZugAuswerten(spiel.getS1().Hand.get(3), spiel.getS2().Hand.get(4));
        spiel.getS1().Hand.remove(3);
        spiel.getS2().Hand.remove(4);
        spiel.istSpielzuEnde(bummerl);


 System.out.println("S1 ist dran " + spiel.getS1().isIstdran() + " S2 ist dran " + spiel.getS2().isIstdran());
 System.out.println("S1 Punkte: " + spiel.getS1().getPunkte() + " S2 Punkte " + spiel.getS2().getPunkte());

 getCards("S1", spiel.getS1().Hand);
 getCards("S2", spiel.getS2().Hand);
 */

    }

    }