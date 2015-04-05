package DataStructure;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class KarteTest {
	private ArrayList<Karte> Kartendeck = new ArrayList<Karte>(20);
	private Karte testKarte;
	
	@Before
	public void setUp() {
	        Kartendeck = Karte.erstelleKartendeck();
	}

	@Test
	public void testGueltigeFarbe() {
		assertEquals("Herz ist gültig", "Herz", Kartendeck.get(0).getFarbe()); //Element 0 = Herz
		assertEquals("Karo ist gültig", "Karo", Kartendeck.get(5).getFarbe()); //Element 5 = Karo
		assertEquals("Pik ist gültig", "Pik", Kartendeck.get(14).getFarbe()); //Element 14 = Pik
		assertEquals("Kreuz ist gültig", "Kreuz", Kartendeck.get(19).getFarbe()); //Element 19 = Kreuz
	}


	@Test
	public void testGueltigeWertigkeit() {
		int random = (int) (Math.random()*18+1);
		testKarte = Kartendeck.get(random);
		switch(testKarte.getWertigkeit()) {
		case "Bube":
			assertEquals("Bube", testKarte.getWertigkeit());
			break;
		case "Dame":
			assertEquals("Dame", testKarte.getWertigkeit());
			break;
		case "König":
			assertEquals("König", testKarte.getWertigkeit());
			break;
		case "10er":
			assertEquals("10er", testKarte.getWertigkeit());
			break;
		case "Ass":
			assertEquals("Ass", testKarte.getWertigkeit());
			break;
		default:
			assertTrue(false);
		}
	}

	@Test
	public void testGueltigePunkte() {
		int random = (int) (Math.random()*18+1);
		testKarte = Kartendeck.get(random);
		switch(testKarte.getPunkte()) {
		case 2:
			assertEquals(2, testKarte.getPunkte());
			break;
		case 3:
			assertEquals(3, testKarte.getPunkte());
			break;
		case 4:
			assertEquals(4, testKarte.getPunkte());
			break;
		case 10:
			assertEquals(10, testKarte.getPunkte());
			break;
		case 11:
			assertEquals(11, testKarte.getPunkte());
			break;
		default:
			assertTrue(false);
		}
	}

	@Test
	public void testEqualsObject() {
		Karte testKarte1 = Kartendeck.get(0);
		Karte testKarte2 = Kartendeck.get(10);
		assertNotEquals(testKarte1, testKarte2);
	}

	@Test
	public void testGueltigenKonstruktorKarte() {
		Karte erzeugeKarte = new Karte("Herz", "Bube", 2);
		assertTrue(Kartendeck.contains(erzeugeKarte));
		
		
	}

	@Test
	public void testErstelleKartendeck() {
		assertEquals(Kartendeck, Karte.erstelleKartendeck());
	}

}
