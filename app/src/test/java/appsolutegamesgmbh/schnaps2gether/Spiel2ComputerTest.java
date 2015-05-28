package appsolutegamesgmbh.schnaps2gether;

import appsolutegamesgmbh.schnaps2gether.DataStructure.Karte;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Spieler;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Bummerl2;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Spiel2ComputerTest {

    //private Spiel2 spiel = new Spiel2();
    private Spiel2random spiel = new Spiel2random();
    private Spieler s1 = new Spieler();
    private Spieler s2 = new Spieler();
    private Bummerl2 bummerl = new Bummerl2();
    private String trumpf;
    private Karte aufgedeckterTrumpf;

    public void getCards(String spieler, ArrayList<Karte> karten) {
        System.out.println(spieler);
        for (Karte k : karten) {
            System.out.println(k.getPunkte() + " " + k.getFarbe() + " " + k.getWertigkeit());

        }
    }


    @Before
    public void setUp() {


    }

   /** @Test
    public void computer_kartenzugeben_stechen() {

        //Test mit bestimmten Karten

        //Runde 1
        Karte ausgespieltS1 = spiel.getS1().Hand.get(0);
        spiel.Auspielen(spiel.getS1().Hand.get(0)); //2 Herz Bube
        Karte ausgespieltC = spiel.AuspielenComputer(ausgespieltS1); //11 Herz Ass
        spiel.ZugAuswerten(ausgespieltS1, ausgespieltC);
        spiel.istSpielzuEnde(bummerl);
        //Runde 2
        ausgespieltC = spiel.AuspielenComputer(null); //2 Pik Bube
        spiel.ZugAuswerten(spiel.getS1().Hand.get(0), ausgespieltC); //4 Pik König
        spiel.getS1().Hand.remove(0);
        spiel.istSpielzuEnde(bummerl);
        //Runde 3
        ausgespieltS1 = spiel.getS1().Hand.get(4); //4 Herz König
        spiel.getS1().Hand.remove(4);
        ausgespieltC = spiel.AuspielenComputer(ausgespieltS1); //10 Herz 10er
        spiel.ZugAuswerten(ausgespieltS1, ausgespieltC);
        spiel.istSpielzuEnde(bummerl);
        //Runde 4
        ausgespieltC = spiel.AuspielenComputer(null); //2 Kreuz Bube
        spiel.ZugAuswerten(spiel.getS1().Hand.get(0), ausgespieltC); //3 Kreuz Dame
        spiel.getS1().Hand.remove(0);
        spiel.istSpielzuEnde(bummerl);
        //Runde 5
        ausgespieltS1 = spiel.getS1().Hand.get(4); //3 Herz Dame
        ausgespieltC = spiel.AuspielenComputer(ausgespieltS1); //2 Karo Bube
        spiel.ZugAuswerten(ausgespieltS1, ausgespieltC);
        spiel.getS1().Hand.remove(4);
        spiel.istSpielzuEnde(bummerl);
        //Runde 6
        ausgespieltC = spiel.AuspielenComputer(null); //3 Pik Dame
        spiel.ZugAuswerten(spiel.getS1().Hand.get(2), ausgespieltC); //3 Karo Dame
        spiel.getS1().Hand.remove(2);
        spiel.istSpielzuEnde(bummerl);
        //Runde 7
        ausgespieltS1 = spiel.getS1().Hand.get(0); //4 Kreuz König
        ausgespieltC = spiel.AuspielenComputer(ausgespieltS1); // 11 Kreuz Ass
        spiel.ZugAuswerten(ausgespieltS1, ausgespieltC);
        spiel.getS1().Hand.remove(0);
        spiel.istSpielzuEnde(bummerl);
        //Runde 8
        ausgespieltC = spiel.AuspielenComputer(null); //10 Kreuz 10er
        ausgespieltS1 = spiel.getS1().Hand.get(1); //4 Karo König
        spiel.getS2().setIstdran(true);
        spiel.ZugAuswerten(ausgespieltS1, ausgespieltC);
        spiel.getS1().Hand.remove(1);
        spiel.istSpielzuEnde(bummerl);
        //Runde 9
        ausgespieltS1 = spiel.getS1().Hand.get(1); //10 Karo 10er
        ausgespieltC = spiel.AuspielenComputer(ausgespieltS1); // 11 Karo Ass
        spiel.ZugAuswerten(ausgespieltS1, ausgespieltC);
        spiel.getS1().Hand.remove(1);
        spiel.istSpielzuEnde(bummerl);

    } */

    @Test
    public void zudrehen_computer_farbstich_zwang() {
        int runde = 0;
        int zudrehen = 0;
        boolean print = true;

        //Runde 1
        Karte ausgespielt1 = spiel.getS1().Hand.get(0);
        spiel.Auspielen(spiel.getS1().Hand.get(0));
        Karte ausgespielt2 = spiel.AuspielenComputer(ausgespielt1);
        spiel.ZugAuswerten(ausgespielt1, ausgespielt2);
        spiel.istSpielzuEnde(bummerl);

        do {

            if (spiel.getS2().isIstdran() && !spiel.getS2().Hand.isEmpty()) {
                ausgespielt2 = spiel.AuspielenComputer(null);
                if (spiel.isZugedreht() && print) {
                    getCards("Computer: ", spiel.getS2().Hand);
                    System.out.println(spiel.getAufgedeckterTrumpf().getFarbe());
                    print = false;
                }
                spiel.ZugAuswerten(spiel.getS1().Hand.get(0), ausgespielt2);
                spiel.getS1().Hand.remove(0);
                spiel.istSpielzuEnde(bummerl);
            }
            if (spiel.getS1().isIstdran() && !spiel.getS1().Hand.isEmpty()) {
                ausgespielt1 = spiel.getS1().Hand.get(0);
                spiel.getS1().Hand.remove(0);
                ausgespielt2 = spiel.AuspielenComputer(ausgespielt1);
                if (spiel.isZugedreht()) {
                    System.out.println("Spieler1: " + ausgespielt1.getFarbe()+ausgespielt1.getWertigkeit());
                    System.out.println("Spieler2: " + ausgespielt2.getFarbe()+ausgespielt2.getWertigkeit());
                    assertTrue("Farb/Stich-Zwang", checkColor(ausgespielt1, ausgespielt2, spiel.getS2().Hand));
                }
                spiel.ZugAuswerten(ausgespielt1, ausgespielt2);
                spiel.istSpielzuEnde(bummerl);
            }
            zudrehen++;
        }
        while (!spiel.istSpielzuEnde(bummerl));







    }

    public boolean checkColor (Karte ks1, Karte ks2, ArrayList<Karte> hands2) {
        if (ks1.getFarbe() == ks2.getFarbe()) {
            return true;
        }
        for (Karte k : hands2) {
            if (ks1.getFarbe() == k.getFarbe()) {
                return false;

            }

        }

        return true;

    }

}