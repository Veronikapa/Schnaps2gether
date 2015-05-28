
package appsolutegamesgmbh.schnaps2gether;

import appsolutegamesgmbh.schnaps2gether.DataStructure.Karte;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class KarteTest {
	private ArrayList<Karte> kartendeck = new ArrayList<Karte>(20);
	private Karte testKarte;
	
	@Before
	public void setUp() {
	        kartendeck = Karte.erstelleKartendeck();
	}

	@Test
	public void testGueltigeFarbe() {
		assertEquals("Herz ist gültig", "Herz", kartendeck.get(0).getFarbe()); //Element 0 = Herz
		assertEquals("Karo ist gültig", "Karo", kartendeck.get(5).getFarbe()); //Element 5 = Karo
		assertEquals("Pik ist gültig", "Pik", kartendeck.get(14).getFarbe()); //Element 14 = Pik
		assertEquals("Kreuz ist gültig", "Kreuz", kartendeck.get(19).getFarbe()); //Element 19 = Kreuz
	}


	@Test
	public void testGueltigeWertigkeit() {
		int random = (int) (Math.random()*18+1);
		testKarte = kartendeck.get(random);
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
		testKarte = kartendeck.get(random);
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
		Karte testKartesprint11 = kartendeck.get(0);
		Karte testKartesprint12 = kartendeck.get(10);
		assertNotEquals(testKartesprint11, testKartesprint12);
	}

	@Test
	public void testGueltigenKonstruktorKarte() {
		Karte erzeugeKarte = new Karte("Herz", "Bube", 2);
		assertTrue(kartendeck.contains(erzeugeKarte));
		
		
	}

	@Test
	public void testErstelleKartendeck() {
		assertEquals(kartendeck, Karte.erstelleKartendeck());
	}

}

