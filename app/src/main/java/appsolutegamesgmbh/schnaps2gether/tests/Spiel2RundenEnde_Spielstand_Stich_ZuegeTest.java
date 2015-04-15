package appsolutegamesgmbh.schnaps2gether.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class Spiel2RundenEnde_Spielstand_Stich_ZuegeTest {
	private Spiel2RundenEnde spiel = new Spiel2RundenEnde();
	private Spieler s1 = new Spieler();
	private Spieler s2 = new Spieler();
	private ArrayList<Karte> kartendeck = Karte.erstelleKartendeck();
	private ArrayList<Karte> stapel = new ArrayList<Karte>(20);
    private Bummerl2 bummerl = new Bummerl2();
    private String trumpf;
    private Karte aufgedeckterTrumpf;
  

	@Before
	public void setUp() {
		
		stapel = kartendeck;
        //Spieler 1 bekommt 3 Karten
        s1.Hand.add(stapel.get(0)); //Herz","Bube",2
        stapel.remove(0);
        s1.Hand.add(stapel.get(0)); //Herz","Dame",3
        stapel.remove(0);
        s1.Hand.add(stapel.get(0)); //Herz","König",4
        stapel.remove(0);


        //Spieler 2 bekommt 3 Karten
        s2.Hand.add(stapel.get(0)); //Herz","10er",10
        stapel.remove(0);
        s2.Hand.add(stapel.get(0)); //Herz","Ass",11
        stapel.remove(0);
        s2.Hand.add(stapel.get(0)); //Karo","Bube",2
        stapel.remove(0);
        
      //Trumpfkarte ausgeben
        aufgedeckterTrumpf = stapel.get(0); //Karo
        trumpf = aufgedeckterTrumpf.getFarbe();
        stapel.remove(0);


        //Spieler 1 bekommt 2 Karten
        s1.Hand.add(stapel.get(0)); //Karo","König",4
        stapel.remove(0);
        s1.Hand.add(stapel.get(0)); //Karo","10er",10
        stapel.remove(0);


        //Spieler 2 bekommt 2 Karten
        s2.Hand.add(stapel.get(0)); //Karo","Ass",11
        stapel.remove(0);
        s2.Hand.add(stapel.get(0)); //Pik","Bube",2
        stapel.remove(0);
        
	}

	@Test
	public void testIstSpielzuEnde_Spielablauftest66Pkt() {
		spiel.Auspielen(spiel.getS1().Hand.get(0)); //Herz","Bube",2
		spiel.ZugAuswerten(spiel.getS1().Hand.get(0), spiel.getS2().Hand.get(0)); // S2 = 10 Herz 10er
		spiel.getS2().Hand.remove(0);
		assertTrue("Spiel ist nicht zu Ende", !spiel.istSpielzuEnde(bummerl));
		//Spieler 2 ist dran
		//S2 = 11 Karo Ass
		//S1 = 10 Karo 10er
		spiel.ZugAuswerten(spiel.getS1().Hand.get(3), spiel.getS2().Hand.get(2));
		spiel.getS1().Hand.remove(3);
		spiel.getS2().Hand.remove(2);
		assertTrue("Spiel ist nicht zu Ende", !spiel.istSpielzuEnde(bummerl));
		//Spieler 2 ist dran
		//S2 = 10 Pik 10er
		//S1 = 4 Pik König
		spiel.ZugAuswerten(spiel.getS1().Hand.get(3), spiel.getS2().Hand.get(4));
		spiel.getS1().Hand.remove(3);
		spiel.getS2().Hand.remove(4);
		assertTrue("Spiel ist nicht zu Ende", !spiel.istSpielzuEnde(bummerl));
		//Spieler 2 ist dran 
		//S2 = 11 Herz Ass
		//S1 = 4 Herz König
		spiel.ZugAuswerten(spiel.getS1().Hand.get(1), spiel.getS2().Hand.get(0));
		spiel.getS1().Hand.remove(1);
		spiel.getS2().Hand.remove(0);
		assertTrue("Spiel ist nicht zu Ende", !spiel.istSpielzuEnde(bummerl));
		//Spieler 2 ist dran (Punkte 63)
		//S2 = 4 Kreuz König
		//S1 = 3 Kreuz Dame
		spiel.ZugAuswerten(spiel.getS1().Hand.get(3), spiel.getS2().Hand.get(4));
		spiel.getS1().Hand.remove(3);
		spiel.getS2().Hand.remove(4);
		assertTrue("Spiel ist zu Ende", spiel.istSpielzuEnde(bummerl));
		
		/**
		System.out.println("S1 ist dran " + spiel.getS1().isIstdran() + " S2 ist dran " + spiel.getS2().isIstdran());
		System.out.println("S1 Punkte: " + spiel.getS1().getPunkte() + " S2 Punkte " + spiel.getS2().getPunkte());
	
		getCards("S1", spiel.getS1().Hand);
		getCards("S2", spiel.getS2().Hand);
		*/
	}
	
	@Test
	public void test_akutellerSpielstandnachStich_PunkteFuerStich_PunktefuerZweiKarten() {
		//Spieler 1 ist dran
		//S1 = 3 Herz Dame
		//S2 = 10 Herz 10er
		spiel.Auspielen(spiel.getS1().Hand.get(0)); 
		spiel.ZugAuswerten(spiel.getS1().Hand.get(0), spiel.getS2().Hand.get(0)); // S2 = 10 Herz 10er
		spiel.getS2().Hand.remove(0);
		assertEquals("Punkte der Karten: 3 + 10", 13, spiel.getS2().getPunkte());
		assertEquals("S2 Punktestand: 13",13, spiel.getS2().getPunkte());
		assertEquals("S1 Punktestand: 0",0, spiel.getS1().getPunkte());
		spiel.istSpielzuEnde(bummerl);
		//Spieler 2 ist dran
		//S2 = 11 Karo Ass
		//S1 = 10 Karo 10er
		spiel.ZugAuswerten(spiel.getS1().Hand.get(3), spiel.getS2().Hand.get(2));
		spiel.getS1().Hand.remove(3);
		spiel.getS2().Hand.remove(2);
		assertEquals("S2 Punktestand: 34",34, spiel.getS2().getPunkte());
		assertEquals("S1 Punktestand: 0",0, spiel.getS1().getPunkte());
		spiel.istSpielzuEnde(bummerl);
		//Spieler 2 ist dran
		//S2 = 10 Pik 10er
		//S1 = 4 Pik König
		spiel.ZugAuswerten(spiel.getS1().Hand.get(3), spiel.getS2().Hand.get(4));
		spiel.getS1().Hand.remove(3);
		spiel.getS2().Hand.remove(4);
		assertEquals("S2 Punktestand: 48",48, spiel.getS2().getPunkte());
		assertEquals("S1 Punktestand: 0",0, spiel.getS1().getPunkte());
		spiel.istSpielzuEnde(bummerl);
		//Spieler 2 ist dran 
		//S2 = 11 Herz Ass
		//S1 = 4 Herz König
		spiel.ZugAuswerten(spiel.getS1().Hand.get(1), spiel.getS2().Hand.get(0));
		spiel.getS1().Hand.remove(1);
		spiel.getS2().Hand.remove(0);
		assertEquals("S2 Punktestand: 63",63, spiel.getS2().getPunkte());
		assertEquals("S1 Punktestand: 0",0, spiel.getS1().getPunkte());
		spiel.istSpielzuEnde(bummerl);
		//Spieler 2 ist dran (Punkte 63)
		//S2 = 4 Kreuz König
		//S1 = 3 Kreuz Dame
		spiel.ZugAuswerten(spiel.getS1().Hand.get(3), spiel.getS2().Hand.get(4));
		spiel.getS1().Hand.remove(3);
		spiel.getS2().Hand.remove(4);
		assertEquals("S2 Punktestand: 70",70, spiel.getS2().getPunkte());
		assertEquals("S1 Punktestand: 0",0, spiel.getS1().getPunkte());
		spiel.istSpielzuEnde(bummerl);
		//getCards("S2", spiel.getS2().Hand);
		assertEquals("Bube entspricht 2 Punkte", 2, spiel.getS2().Hand.get(0).getPunkte());
		
	}
	
	@Test
	public void test_neueKartewirdnachStichAufgenommen() {
		//Spieler 1 ist dran
		//S1 = 3 Herz Dame
		//S2 = 10 Herz 10er
		spiel.Auspielen(spiel.getS1().Hand.get(0)); 
		spiel.ZugAuswerten(spiel.getS1().Hand.get(0), spiel.getS2().Hand.get(0)); 
		spiel.getS2().Hand.remove(0);
		assertEquals("S1 Handkarten: 4", 4, spiel.getS1().Hand.size());
		assertEquals("S2 Handkarten: 4", 4, spiel.getS2().Hand.size());
		spiel.istSpielzuEnde(bummerl);
		//Nach einem Stich wird eine neue Karte aufgenommen
		assertEquals("S1 Handkarten: 5", 5, spiel.getS1().Hand.size());
		assertEquals("S2 Handkarten: 5", 5, spiel.getS2().Hand.size());
		//Spieler 2 ist dran
		//S2 = 11 Karo Ass
		//S1 = 10 Karo 10er
		spiel.ZugAuswerten(spiel.getS1().Hand.get(3), spiel.getS2().Hand.get(2));
		spiel.getS1().Hand.remove(3);
		spiel.getS2().Hand.remove(2);
		assertEquals("S1 Handkarten: 4", 4, spiel.getS1().Hand.size());
		assertEquals("S2 Handkarten: 4", 4, spiel.getS2().Hand.size());
		spiel.istSpielzuEnde(bummerl);
		//Nach einem Stich wird eine neue Karte aufgenommen
		assertEquals("S1 Handkarten: 5", 5, spiel.getS1().Hand.size());
		assertEquals("S2 Handkarten: 5", 5, spiel.getS2().Hand.size());
		//Spieler 2 ist dran
		//S2 = 10 Pik 10er
		//S1 = 4 Pik König
		spiel.ZugAuswerten(spiel.getS1().Hand.get(3), spiel.getS2().Hand.get(4));
		spiel.getS1().Hand.remove(3);
		spiel.getS2().Hand.remove(4);
		assertEquals("S1 Handkarten: 4", 4, spiel.getS1().Hand.size());
		assertEquals("S2 Handkarten: 4", 4, spiel.getS2().Hand.size());
		spiel.istSpielzuEnde(bummerl);
		//Nach einem Stich wird eine neue Karte aufgenommen
		assertEquals("S1 Handkarten: 5", 5, spiel.getS1().Hand.size());
		assertEquals("S2 Handkarten: 5", 5, spiel.getS2().Hand.size());
		//Spieler 2 ist dran 
		//S2 = 11 Herz Ass
		//S1 = 4 Herz König
		spiel.ZugAuswerten(spiel.getS1().Hand.get(1), spiel.getS2().Hand.get(0));
		spiel.getS1().Hand.remove(1);
		spiel.getS2().Hand.remove(0);
		assertEquals("S2 Punktestand: 63",63, spiel.getS2().getPunkte());
		assertEquals("S1 Punktestand: 0",0, spiel.getS1().getPunkte());
		assertEquals("S1 Handkarten: 4", 4, spiel.getS1().Hand.size());
		assertEquals("S2 Handkarten: 4", 4, spiel.getS2().Hand.size());
		spiel.istSpielzuEnde(bummerl);
		//Nach einem Stich wird eine neue Karte aufgenommen
		assertEquals("S1 Handkarten: 5", 5, spiel.getS1().Hand.size());
		assertEquals("S2 Handkarten: 5", 5, spiel.getS2().Hand.size());
		//Spieler 2 ist dran (Punkte 63)
		//S2 = 4 Kreuz König
		//S1 = 3 Kreuz Dame
		spiel.ZugAuswerten(spiel.getS1().Hand.get(3), spiel.getS2().Hand.get(4));
		spiel.getS1().Hand.remove(3);
		spiel.getS2().Hand.remove(4);
		assertEquals("S1 Handkarten: 4", 4, spiel.getS1().Hand.size());
		assertEquals("S2 Handkarten: 4", 4, spiel.getS2().Hand.size());
		spiel.istSpielzuEnde(bummerl);
		//Rundenende
		
	}
	
	@Test
	public void test_GewinnerIstNachStichAmZug_GewinnerWirdAngezeigt() {
		//Spieler 1 ist dran
		//S1 = 3 Herz Dame
		//S2 = 10 Herz 10er
		spiel.Auspielen(spiel.getS1().Hand.get(0)); 
		spiel.ZugAuswerten(spiel.getS1().Hand.get(0), spiel.getS2().Hand.get(0)); 
		spiel.getS2().Hand.remove(0);
		spiel.istSpielzuEnde(bummerl);
		//Gewinner S2 - S2 ist am Zug
		assertTrue("Gewinner (S2) ist am Zug und wird angezeigt", spiel.getS2().isIstdran());
		//Spieler 2 ist dran
		//S2 = 11 Karo Ass
		//S1 = 10 Karo 10er
		spiel.ZugAuswerten(spiel.getS1().Hand.get(3), spiel.getS2().Hand.get(2));
		spiel.getS1().Hand.remove(3);
		spiel.getS2().Hand.remove(2);
		spiel.istSpielzuEnde(bummerl);
		//Gewinner S2 - S2 ist am Zug
		assertTrue("Gewinner (S2) ist am Zug und wird angezeigt", spiel.getS2().isIstdran());
		//Spieler 2 ist dran
		//S2 = 10 Pik 10er
		//S1 = 4 Pik König
		spiel.ZugAuswerten(spiel.getS1().Hand.get(3), spiel.getS2().Hand.get(4));
		spiel.getS1().Hand.remove(3);
		spiel.getS2().Hand.remove(4);
		spiel.istSpielzuEnde(bummerl);
		//Gewinner S2 - S2 ist am Zug
		assertTrue("Gewinner (S2) ist am Zug und wird angezeigt", spiel.getS2().isIstdran());
		//Spieler 2 ist dran 
		//S2 = 11 Herz Ass
		//S1 = 4 Herz König
		spiel.ZugAuswerten(spiel.getS1().Hand.get(1), spiel.getS2().Hand.get(0));
		spiel.getS1().Hand.remove(1);
		spiel.getS2().Hand.remove(0);
		spiel.istSpielzuEnde(bummerl);
		//Gewinner S2 - S2 ist am Zug
		assertTrue("Gewinner (S2) ist am Zug und wird angezeigt", spiel.getS2().isIstdran());
		//Spieler 2 ist dran (Punkte 63)
		//S2 = 4 Kreuz König
		//S1 = 3 Kreuz Dame
		spiel.ZugAuswerten(spiel.getS1().Hand.get(3), spiel.getS2().Hand.get(4));
		spiel.getS1().Hand.remove(3);
		spiel.getS2().Hand.remove(4);
		spiel.istSpielzuEnde(bummerl);
		//Rundenende
	}
	
	@Test
	public void test_HandkarteAusspielen_WennSpielerAmZug_KarteGegenAusgespielteKarteAusspielen() {
		//Spieler 1 ist dran
		//S1 = 3 Herz Dame
		//S2 = 10 Herz 10er
		assertTrue("S1 kann Handkarte ausspielen", spiel.getS1().isIstdran());
		assertTrue("S2 kann Handkarte nicht ausspielen", !spiel.getS2().isIstdran());
		spiel.Auspielen(spiel.getS1().Hand.get(0)); 
		spiel.ZugAuswerten(spiel.getS1().Hand.get(0), spiel.getS2().Hand.get(0)); 
		spiel.getS2().Hand.remove(0);
		spiel.istSpielzuEnde(bummerl);
		//Gewinner S2 - S2 ist am Zug
		assertTrue("S2 kann Handkarte ausspielen", spiel.getS2().isIstdran());
		assertTrue("S1 kann Handkarte nicht ausspielen", !spiel.getS1().isIstdran());
		//Spieler 2 ist dran
		//S2 = 11 Karo Ass
		//S1 = 10 Karo 10er
		spiel.ZugAuswerten(spiel.getS1().Hand.get(3), spiel.getS2().Hand.get(2));
		spiel.getS1().Hand.remove(3);
		spiel.getS2().Hand.remove(2);
		spiel.istSpielzuEnde(bummerl);
		//Gewinner S2 - S2 ist am Zug
		assertTrue("S2 kann Handkarte ausspielen", spiel.getS2().isIstdran());
		assertTrue("S1 kann Handkarte nicht ausspielen", !spiel.getS1().isIstdran());
		//Spieler 2 ist dran
		//S2 = 10 Pik 10er
		//S1 = 4 Pik König
		spiel.ZugAuswerten(spiel.getS1().Hand.get(3), spiel.getS2().Hand.get(4));
		spiel.getS1().Hand.remove(3);
		spiel.getS2().Hand.remove(4);
		spiel.istSpielzuEnde(bummerl);
		//Gewinner S2 - S2 ist am Zug
		assertTrue("S2 kann Handkarte ausspielen", spiel.getS2().isIstdran());
		assertTrue("S1 kann Handkarte nicht ausspielen", !spiel.getS1().isIstdran());
		//Spieler 2 ist dran 
		//S2 = 11 Herz Ass
		//S1 = 4 Herz König
		spiel.ZugAuswerten(spiel.getS1().Hand.get(1), spiel.getS2().Hand.get(0));
		spiel.getS1().Hand.remove(1);
		spiel.getS2().Hand.remove(0);
		spiel.istSpielzuEnde(bummerl);
		//Gewinner S2 - S2 ist am Zug
		assertTrue("S2 kann Handkarte ausspielen", spiel.getS2().isIstdran());
		assertTrue("S1 kann Handkarte nicht ausspielen", !spiel.getS1().isIstdran());
		//Spieler 2 ist dran (Punkte 63)
		//S2 = 4 Kreuz König
		//S1 = 3 Kreuz Dame
		spiel.ZugAuswerten(spiel.getS1().Hand.get(3), spiel.getS2().Hand.get(4));
		spiel.getS1().Hand.remove(3);
		spiel.getS2().Hand.remove(4);
		spiel.istSpielzuEnde(bummerl);
		//Rundenende
	}
	
	@Test
	public void test_StechenWertigkeiten() {
		//Spieler 1 ist dran
		//S1 = 3 Herz Dame
		//S2 = 2 Karo Bube (Trumpf)
		spiel.Auspielen(spiel.getS1().Hand.get(0)); 
		spiel.ZugAuswerten(spiel.getS1().Hand.get(0), spiel.getS2().Hand.get(2)); 
		spiel.getS2().Hand.remove(2);
		//Stich wurde mit Trumpf von S2 gewonnen
		spiel.istSpielzuEnde(bummerl);
		assertTrue("S2 hat Gewonnen Karo (Trumpf) sticht Herz", spiel.getS2().isIstdran());
		//Gewinner S2 - S2 ist am Zug
		//Spieler 2 ist dran
		//S2 = 11 Karo Ass
		//S1 = 10 Karo 10er
		spiel.ZugAuswerten(spiel.getS1().Hand.get(3), spiel.getS2().Hand.get(2));
		spiel.getS1().Hand.remove(3);
		spiel.getS2().Hand.remove(2);
		spiel.istSpielzuEnde(bummerl);
		assertTrue("S2 hat Gewonnen Ass sticht 10er", spiel.getS2().isIstdran());
		//Gewinner S2 - S2 ist am Zug
		//Spieler 2 ist dran
		//S2 = 10 Pik 10er
		//S1 = 4 Pik König
		spiel.ZugAuswerten(spiel.getS1().Hand.get(3), spiel.getS2().Hand.get(4));
		spiel.getS1().Hand.remove(3);
		spiel.getS2().Hand.remove(4);
		spiel.istSpielzuEnde(bummerl);
		assertTrue("S2 hat Gewonnen 10er sticht König", spiel.getS2().isIstdran());
		//Gewinner S2 - S2 ist am Zug
		//Spieler 2 ist dran 
		//S2 = 11 Herz Ass
		//S1 = 4 Herz König
		spiel.ZugAuswerten(spiel.getS1().Hand.get(1), spiel.getS2().Hand.get(0));
		spiel.getS1().Hand.remove(1);
		spiel.getS2().Hand.remove(0);
		spiel.istSpielzuEnde(bummerl);
		assertTrue("S2 hat Gewonnen Ass sticht König", spiel.getS2().isIstdran());
		//Gewinner S2 - S2 ist am Zug
		//Spieler 2 ist dran (Punkte 63)
		//S2 = 4 Kreuz König
		//S1 = 3 Kreuz Dame
		spiel.ZugAuswerten(spiel.getS1().Hand.get(3), spiel.getS2().Hand.get(4));
		spiel.getS1().Hand.remove(3);
		spiel.getS2().Hand.remove(4);
		//spiel.istSpielzuEnde(bummerl);
		//Rundenende
	}
	
	public void getCards(String spieler, ArrayList<Karte> karten) {
		System.out.println(spieler);
		for (Karte k : karten) {
			System.out.println(k.getPunkte() + " " + k.getFarbe() + " " + k.getWertigkeit());
			
		}
	}

}
