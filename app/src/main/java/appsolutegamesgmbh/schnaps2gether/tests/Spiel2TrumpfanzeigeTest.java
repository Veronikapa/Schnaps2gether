package DataStructure;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class Spiel2TrumpfanzeigeTest {
	private Spieler s1 = new Spieler();
	private Spieler s2 = new Spieler();
	private ArrayList<Karte> kartendeck = Karte.erstelleKartendeck();
	private ArrayList<Karte> stapel = new ArrayList<Karte>(20);
	private String trumpf;
    private Karte aufgedeckterTrumpf;

	@Before
	public void setUp()  {
			int random = 0;
	        for(int a = 20; a > 0; a--)
	        {
	            random = (int) (Math.random()*a);
	            stapel.add(kartendeck.get(random));
	            kartendeck.remove(random);
	        }
	        //Spieler 1 bekommt 3 Karten
	        s1.Hand.add(stapel.get(0));
	        stapel.remove(0);
	        s1.Hand.add(stapel.get(0));
	        stapel.remove(0);
	        s1.Hand.add(stapel.get(0));
	        stapel.remove(0);


	        //Spieler 2 bekommt 3 Karten
	        s2.Hand.add(stapel.get(0));
	        stapel.remove(0);
	        s2.Hand.add(stapel.get(0));
	        stapel.remove(0);
	        s2.Hand.add(stapel.get(0));
	        stapel.remove(0);

	        //Trumpfkarte ausgeben
	        aufgedeckterTrumpf = stapel.get(0);
	        trumpf = aufgedeckterTrumpf.getFarbe();
	        stapel.remove(0);

	        //Spieler 1 bekommt 2 Karten
	        s1.Hand.add(stapel.get(0));
	        stapel.remove(0);
	        s1.Hand.add(stapel.get(0));
	        stapel.remove(0);


	        //Spieler 2 bekommt 2 Karten
	        s2.Hand.add(stapel.get(0));
	        stapel.remove(0);
	        s2.Hand.add(stapel.get(0));
	        stapel.remove(0);
	        
		
	}

	@Test
	public void testGetTrumpf_isteindeutig() {
		int trumpfS1 = (counttrumpf(s1.Hand));
		int trumpfS2 = (counttrumpf(s2.Hand));
		int trumpfStapel = (counttrumpf(stapel));
		int countall = (trumpfS1 + trumpfS2 + trumpfStapel);
		
		assertEquals("Es sind noch 4 Trümpfe verfügbar", 4, countall);
	}

	@Test
	public void testGetAufgedeckterTrumpf() {
		assertNotNull(aufgedeckterTrumpf);
	}
	
	public int counttrumpf(ArrayList<Karte> karte) {
		int count = 0;
		for (Karte k : karte) {
			if (k.getFarbe() == trumpf) {
				count++;
			}
		}
		
		return count;
	}
	
	 

}
